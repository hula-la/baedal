package com.baedal.accountservice.security.exception;

import com.baedal.accountservice.exception.ExceptionCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum AuthStatusCode implements ExceptionCode {

    NO_REFRESH_TOKEN(HttpStatus.FORBIDDEN,"auth.no-refresh-token","리프레쉬토큰이 없습니다."),
    NO_VALID_TOKEN(HttpStatus.FORBIDDEN,"order.no-valid-token","토큰이 유효하지 않습니다."),
    NOT_MATCH_TOKEN(HttpStatus.FORBIDDEN,"order.not-match-token","리프레쉬토큰이 일치하지 않습니다."),
    ACCESS_TOKEN_EXPIRED(HttpStatus.FORBIDDEN,"order.access-token-expired","만료된 토큰입니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;

}