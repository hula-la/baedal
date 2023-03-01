package com.baedal.monolithic.domain.auth.util;

import com.baedal.monolithic.domain.account.entity.Account;
import com.baedal.monolithic.domain.account.exception.AccountException;
import com.baedal.monolithic.domain.account.exception.AccountExceptionCode;
import com.baedal.monolithic.domain.account.repository.AccountRepository;
import com.baedal.monolithic.domain.auth.exception.OAuthProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
@Component
@Slf4j
public class JwtAuthenticationProcessingFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;
    private final AccountRepository accountRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String refreshToken = jwtProvider.extractRefreshTokenFromHeader(request)
                .filter(jwtProvider::isTokenValid)
                .orElse(null);

        if (refreshToken != null) {
            checkRefreshTokenAndReIssueAccessToken(response, refreshToken);
            return;
        }

        checkAccessTokenAndAuthentication(request);
        filterChain.doFilter(request, response);

    }

//    @Transactional
    public void checkRefreshTokenAndReIssueAccessToken(HttpServletResponse response, String refreshToken) {
        String userId = jwtProvider.extractUserId(refreshToken)
                .orElseThrow(() -> new OAuthProcessingException("토큰이 유효하지 않습니다."));

        Account account = accountRepository.findById(Long.valueOf(userId))
                .orElseThrow(() -> new AccountException(AccountExceptionCode.NO_USER));

        if (!account.getRefreshToken().equals(refreshToken))
            throw new OAuthProcessingException("RefreshToken not match");

        jwtProvider.refreshAccessAndRefreshToken(response, userId);
    }

    public void checkAccessTokenAndAuthentication(HttpServletRequest request) {
        log.info("checkAccessTokenAndAuthentication() 호출");
        jwtProvider.extractAccessTokenFromHeader(request)
                .filter(jwtProvider::isTokenValid)
                .flatMap(jwtProvider::extractUserId)
                .map(Long::valueOf)
                .flatMap(accountRepository::findById)
                .ifPresent(this::saveAuthentication);
    }

    public void saveAuthentication(Account myUser) {
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