package com.baedal.gateway.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum AuthStatusCode  {

    NO_ACCESS_TOKEN(HttpStatus.FORBIDDEN,"auth.no-access-token","액세스 토큰이 없습니다."),
    INVALID_TOKEN(HttpStatus.FORBIDDEN,"order.invalid-token","토큰이 유효하지 않습니다."),
    ACCESS_TOKEN_EXPIRED(HttpStatus.UPGRADE_REQUIRED,"order.access-token-expired","만료된 토큰입니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;

}