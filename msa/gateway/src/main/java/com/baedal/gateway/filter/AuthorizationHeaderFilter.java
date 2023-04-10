package com.baedal.gateway.filter;

import com.baedal.gateway.exception.AuthException;
import com.baedal.gateway.exception.AuthStatusCode;
import com.baedal.gateway.util.JwtProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

@Component
@Slf4j
public class AuthorizationHeaderFilter extends AbstractGatewayFilterFactory<AuthorizationHeaderFilter.Config> {

    @Value("${jwt.user-id.header}")
    private String userIdHeader;

    private final JwtProvider jwtProvider;

    @Autowired
    public AuthorizationHeaderFilter(JwtProvider jwtProvider) {
        super(Config.class);
        this.jwtProvider = jwtProvider;
    }

    public static class Config {
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            String token = jwtProvider.extractAccessTokenFromHeader(exchange.getRequest());

            if (jwtProvider.isTokenValid(token)) {
                String userId = jwtProvider.extractUserId(token)
                        .orElseThrow(()->new AuthException(AuthStatusCode.NO_ACCESS_TOKEN));

                addAuthorizationHeaders(exchange.getRequest(), userId);// 파싱된 토큰의 claim을 추출해 아이디 값을 가져온다.
            }

            return chain.filter(exchange);
        };
    }

    // 성공적으로 검증이 되었기 때문에 인증된 헤더로 요청을 변경해준다. 서비스는 해당 헤더에서 아이디를 가져와 사용한다.
    private void addAuthorizationHeaders(ServerHttpRequest request, String userId) {
        request.mutate()
                .header(userIdHeader, userId)
                .build();
    }

    // 토큰 검증 요청을 실행하는 도중 예외가 발생했을 때 예외처리하는 핸들러
    @Bean
    public ErrorWebExceptionHandler tokenValidation() {
        return new JwtTokenExceptionHandler();
    }

    // 실제 토큰이 null, 만료 등 예외 상황에 따른 예외처리
    public class JwtTokenExceptionHandler implements ErrorWebExceptionHandler {
        private String getErrorCode(String errorCode) {
            return "{\"errorCode\":" + errorCode + "}";
        }

        @Override
        public Mono<Void> handle(
                ServerWebExchange exchange, Throwable ex) {
            AuthException authException = (AuthException) ex;
            AuthStatusCode authStatusCode = authException.getAuthStatusCode();

            ServerHttpResponse response = exchange.getResponse();

            // response header 수정
            response.setStatusCode(authStatusCode.getStatus());
            // header 강제 주입
            response.getHeaders().add(HttpHeaders.CONTENT_TYPE, String.valueOf(MediaType.APPLICATION_JSON));

            byte [] bytes = getErrorCode(authStatusCode.getCode()).getBytes(StandardCharsets.UTF_8);
            DataBuffer buffer = response.bufferFactory().wrap(bytes);

            log.info("인가에 실패했습니다.");
            return response.writeWith(Flux.just(buffer));
        }
    }



}
