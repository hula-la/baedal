package com.baedal.accountservice.security.exception;

import lombok.Getter;

@Getter
public class AuthException extends RuntimeException{

    private final AuthStatusCode authStatusCode;

    public AuthException(AuthStatusCode authStatusCode) {
        super(authStatusCode.getMessage());
        this.authStatusCode = authStatusCode;
    }

}
