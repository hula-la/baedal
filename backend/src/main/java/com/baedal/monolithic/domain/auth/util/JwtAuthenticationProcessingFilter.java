package com.baedal.monolithic.domain.auth.util;

import com.baedal.monolithic.domain.account.entity.Account;
import com.baedal.monolithic.domain.account.exception.AccountException;
import com.baedal.monolithic.domain.account.exception.AccountExceptionCode;
import com.baedal.monolithic.domain.account.repository.AccountRepository;
import com.baedal.monolithic.domain.auth.application.UserPrincipal;
import com.baedal.monolithic.domain.auth.exception.OAuthProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
import java.util.Collections;

@RequiredArgsConstructor
@Component
@Slf4j
public class JwtAuthenticationProcessingFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;
    private final AccountRepository accountRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        if (request.getRequestURI().equals("/actuator/prometheus")) return;

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
                .orElseThrow(() -> new OAuthProcessingException("????????? ???????????? ????????????."));

        Account account = accountRepository.findById(Long.valueOf(userId))
                .orElseThrow(() -> new AccountException(AccountExceptionCode.NO_USER));

        if (!account.getRefreshToken().equals(refreshToken))
            throw new OAuthProcessingException("RefreshToken not match");

        jwtProvider.refreshAccessAndRefreshToken(response, userId);
    }

    public void checkAccessTokenAndAuthentication(HttpServletRequest request) {
        log.info(request.getRequestURI()+" checkAccessTokenAndAuthentication() ??????");
        jwtProvider.extractAccessTokenFromHeader(request)
                .filter(jwtProvider::isTokenValid)
                .flatMap(jwtProvider::extractUserId)
                .map(Long::valueOf)
                .map(accountId -> accountRepository.findById(accountId)
                        .orElseThrow(() ->  new AccountException(AccountExceptionCode.NO_USER)))
                .ifPresent(this::saveAuthentication);
    }

    public void saveAuthentication(Account myUser) {
//        UserPrincipal userPrincipal = new UserPrincipal(myUser,
//                Collections.singleton(new SimpleGrantedAuthority(myUser.getRoleKey())),
//                        null);
        // SecurityContextHolder??? ???????????? Authentication ??? ????????? ???????????? UserPrincipal??? ??????
        // ??????
        // ????????? JWT ?????? ????????? ?????? ???????????? contextHolder??? ?????? ???
        // UserDetails ???????????? Authentication ??? ?????? ?????? ??????
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