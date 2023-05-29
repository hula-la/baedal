package com.baedal.monolithic.domain.auth.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.baedal.monolithic.domain.account.entity.Account;
import com.baedal.monolithic.domain.account.exception.AccountException;
import com.baedal.monolithic.domain.account.exception.AccountExceptionCode;
import com.baedal.monolithic.domain.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Optional;

@RequiredArgsConstructor
@Component
@Slf4j
public class JwtProvider {

    @Value("${jwt.secretKey}")
    private String secretKey;

    @Value("${jwt.access.expiration}")
    private int accessTokenExpirationPeriod;

    @Value("${jwt.refresh.expiration}")
    private int refreshTokenExpirationPeriod;

    @Value("${jwt.access.header}")
    private String accessHeader;

    @Value("${jwt.refresh.header}")
    private String refreshHeader;

    private static final String ACCESS_TOKEN_SUBJECT = "AccessToken";
    private static final String REFRESH_TOKEN_SUBJECT = "RefreshToken";
    private static final String USERID_CLAIM = "userId";
    private static final String BEARER = "Bearer ";

    private final AccountRepository accountRepository;
    public String createAccessToken(String userId) {
        Date now = new Date();
        return JWT.create()
                .withSubject(ACCESS_TOKEN_SUBJECT)
                .withExpiresAt(new Date(now.getTime() + accessTokenExpirationPeriod))
                .withClaim(USERID_CLAIM, userId)
                .sign(Algorithm.HMAC512(secretKey));
    }

    protected String createRefreshToken(String userId) {
        Date now = new Date();
        return JWT.create()
                .withSubject(REFRESH_TOKEN_SUBJECT)
                .withExpiresAt(new Date(now.getTime() + refreshTokenExpirationPeriod))
                .withClaim(USERID_CLAIM, userId)
                .sign(Algorithm.HMAC512(secretKey));
    }

    @CacheEvict(value = "auth", key = "#userId")
    public void refreshRefreshToken(HttpServletResponse response, String userId) {
        String refreshToken = createRefreshToken(userId);

        updateRefreshTokenInAccount(Long.valueOf(userId), refreshToken);

        CookieUtil.addCookie(response, refreshHeader, refreshToken, refreshTokenExpirationPeriod);
    }

//    public String refreshAccessToken(HttpServletResponse response, String userId) {
//        String accessToken = createAccessToken(userId);
//        CookieUtil.addCookie(response, accessHeader, accessToken, accessTokenExpirationPeriod);
//        return accessToken;
//    }

    protected void updateRefreshTokenInAccount(Long userId, String refreshToken) {
        Account account = accountRepository.findById(userId)
                .orElseThrow(() -> new AccountException(AccountExceptionCode.NO_USER));

        account.updateRefreshToken(refreshToken);
        accountRepository.save(account);
    }

    public Optional<String> extractRefreshTokenFromHeader(HttpServletRequest request) {
        return Optional.ofNullable(request.getHeader(refreshHeader))
                .filter(refreshToken -> refreshToken.startsWith(BEARER))
                .map(refreshToken -> refreshToken.replace(BEARER, ""));
    }

    public Optional<String> extractAccessTokenFromHeader(HttpServletRequest request) {
        return Optional.ofNullable(request.getHeader(accessHeader))
                .filter(refreshToken -> refreshToken.startsWith(BEARER))
                .map(refreshToken -> refreshToken.replace(BEARER, ""));
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
//
//    public boolean isTokenValid(String token) {
//
//            JWT.require(Algorithm.HMAC512(secretKey))
//                    .build()
//                    .verify(token);
//            return true;
//
//    }

}
