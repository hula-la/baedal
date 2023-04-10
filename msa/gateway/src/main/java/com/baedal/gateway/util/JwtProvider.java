package com.baedal.gateway.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.baedal.gateway.exception.AuthException;
import com.baedal.gateway.exception.AuthStatusCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
@Slf4j
public class JwtProvider {

    @Value("${jwt.secretKey}")
    private String secretKey;

    @Value("${jwt.access.header}")
    private String accessHeader;

    private static final String USERID_CLAIM = "userId";
    private static final String BEARER = "Bearer ";


    public String extractAccessTokenFromHeader(ServerHttpRequest request) {
        try{
            return request.getHeaders().get(accessHeader).get(0).replace(BEARER, "");
        } catch (NullPointerException ex) {
            throw new AuthException(AuthStatusCode.NO_ACCESS_TOKEN);
        }
    }


    public Optional<String> extractUserId(String token) {
        try {
            // 토큰 유효성 검사하는 데에 사용할 알고리즘이 있는 JWT verifier builder 반환
            return Optional.ofNullable(JWT.require(Algorithm.HMAC512(secretKey))
                    .build() // 반환된 빌더로 JWT verifier 생성
                    .verify(token) // accessToken을 검증하고 유효하지 않다면 예외 발생
                    .getClaim(USERID_CLAIM) // claim(Emial) 가져오기
                    .asString());
        } catch (Exception e) {
            log.error("액세스 토큰이 유효하지 않습니다.");
            return Optional.empty();
        }
    }

    public boolean isTokenValid(String token) {
        try {
            JWT.require(Algorithm.HMAC512(secretKey))
                    .build()
                    .verify(token);
            return true;
        } catch (TokenExpiredException ex) {
            throw new AuthException(AuthStatusCode.ACCESS_TOKEN_EXPIRED);
        } catch (Exception ex) {
            throw new AuthException(AuthStatusCode.INVALID_TOKEN);
        }

    }


}
