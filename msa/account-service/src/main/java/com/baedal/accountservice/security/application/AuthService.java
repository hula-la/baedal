package com.baedal.accountservice.security.application;

import com.baedal.accountservice.entity.Account;
import com.baedal.accountservice.exception.AccountException;
import com.baedal.accountservice.exception.AccountExceptionCode;
import com.baedal.accountservice.repository.AccountRepository;
import com.baedal.accountservice.security.exception.AuthException;
import com.baedal.accountservice.security.exception.AuthStatusCode;
import com.baedal.accountservice.security.util.CookieUtil;
import com.baedal.accountservice.security.util.JwtProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Log4j2
@Service
@RequiredArgsConstructor
public class AuthService {

    @Value("${jwt.refresh.header}")
    private String refreshKey;

    private final AccountRepository accountRepository;
    private final JwtProvider jwtProvider;

    public String refreshToken(HttpServletRequest request, HttpServletResponse response, String oldAccessToken) {
        // 1. Validation Refresh Token
        String oldRefreshToken = CookieUtil.getCookie(request, refreshKey)
                .map(Cookie::getValue).orElseThrow(() -> new AuthException(AuthStatusCode.NO_REFRESH_TOKEN));

        if (!jwtProvider.isTokenValid(oldRefreshToken)) {
            throw new AuthException(AuthStatusCode.NO_VALID_TOKEN);
        }

        // 2. 유저정보 얻기
        String userId = jwtProvider.extractUserId(oldAccessToken)
                .orElseThrow(() -> new AuthException(AuthStatusCode.NO_VALID_TOKEN));

        // 3. Match Refresh Token
        Account account = accountRepository.findById(Long.valueOf(userId))
                .orElseThrow(() -> new AccountException(AccountExceptionCode.NO_USER));

        if (!account.getRefreshToken().equals(oldRefreshToken)) {
            throw new AuthException(AuthStatusCode.NOT_MATCH_TOKEN);
        }

        // 4. JWT 갱신
        String accessToken = jwtProvider.createAccessToken(userId);
        jwtProvider.refreshRefreshToken(response, userId);

        return accessToken;
    }

}
