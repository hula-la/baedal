package com.baedal.gateway.exception;

import lombok.Getter;

@Getter
public class AuthException extends RuntimeException {
    private AuthStatusCode authStatusCode;

    public AuthException(AuthStatusCode authStatusCode) {
        super(authStatusCode.getMessage());
        this.authStatusCode = authStatusCode;
    }

}
