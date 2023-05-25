package com.baedal.monolithic.domain.auth.util;

import com.baedal.monolithic.domain.auth.application.AuthService;
import com.baedal.monolithic.domain.auth.dto.AuthDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationProcessingFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;
    private final AuthService authService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

//            String refreshToken = jwtProvider.extractRefreshTokenFromHeader(request)
//                    .filter(jwtProvider::isTokenValid)
//                    .orElse(null);
//
//            if (refreshToken != null) {
//                checkRefreshTokenAndReIssueAccessToken(response, refreshToken);
//                return;
//            }

            checkAccessTokenAndAuthentication(request);
            filterChain.doFilter(request, response);
    }

//    public void checkRefreshTokenAndReIssueAccessToken(HttpServletResponse response, String refreshToken) {
//        String accountId = jwtProvider.extractUserId(refreshToken)
//                .orElseThrow(() -> new OAuthProcessingException("토큰이 유효하지 않습니다."));
//
//        AuthDto.GetRes authDto = authService.findAuth(Long.valueOf(accountId));
//
//        if (!authDto.getRefreshToken().equals(refreshToken))
//            throw new OAuthProcessingException("RefreshToken not match");
//
//        jwtProvider.refreshRefreshToken(response, accountId);
//    }

    public void checkAccessTokenAndAuthentication(HttpServletRequest request) {
        log.info(request.getRequestURI()+" checkAccessTokenAndAuthentication() 호출");
        jwtProvider.extractAccessTokenFromHeader(request)
                .filter(jwtProvider::isTokenValid)
                .flatMap(jwtProvider::extractUserId)
                .map(Long::valueOf)
                .map(authService::findAuth)
                .ifPresent(this::saveAuthentication);
    }

    public void saveAuthentication(AuthDto.GetRes myUser) {
//        UserPrincipal userPrincipal = new UserPrincipal(myUser,
//                Collections.singleton(new SimpleGrantedAuthority(myUser.getRoleKey())),
//                        null);
        // SecurityContextHolder에 들어가는 Authentication 도 기존과 통일해서 UserPrincipal로 할지
        // 고민
        // 우선은 JWT 에서 사용자 정보 추출해서 contextHolder에 넣을 땐
        // UserDetails 타입으로 Authentication 에 담는 걸로 구현
        UserDetails userDetails = User.builder()
                .username(String.valueOf(myUser.getId()))
                .password("")
                .roles(myUser.getRole().name())
                .build();

        Authentication authentication =
                new UsernamePasswordAuthenticationToken(userDetails, null,
                        userDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}