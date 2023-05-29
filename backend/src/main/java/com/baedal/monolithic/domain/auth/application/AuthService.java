package com.baedal.monolithic.domain.auth.application;

import com.baedal.monolithic.domain.account.entity.Account;
import com.baedal.monolithic.domain.account.exception.AccountException;
import com.baedal.monolithic.domain.account.exception.AccountExceptionCode;
import com.baedal.monolithic.domain.account.repository.AccountRepository;
import com.baedal.monolithic.domain.auth.dto.AuthDto;
import com.baedal.monolithic.domain.auth.exception.AuthException;
import com.baedal.monolithic.domain.auth.exception.AuthStatusCode;
import com.baedal.monolithic.domain.auth.util.CookieUtil;
import com.baedal.monolithic.domain.auth.util.JwtProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private final AuthMapper authMapper;

    public String refreshToken(HttpServletRequest request, HttpServletResponse response, String oldAccessToken) {

        // 1. Validation Refresh Token
        String oldRefreshToken = CookieUtil.getCookie(request, refreshKey)
                .map(Cookie::getValue).orElseThrow(() -> new AuthException(AuthStatusCode.NO_REFRESH_TOKEN));

//        if (!jwtProvider.isTokenValid(oldRefreshToken)) {
//            throw new AuthException(AuthStatusCode.NO_VALID_TOKEN);
//        }

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

    @Transactional(readOnly = true)
    @Cacheable(key = "#accountId", value = "auth")
    public AuthDto.GetRes findAuth(final Long accountId) {
        return authMapper.mapEntityToAuthDto(
                accountRepository.findById(accountId)
                .orElseThrow(() ->  new AccountException(AccountExceptionCode.NO_USER))
        );
    }

}
