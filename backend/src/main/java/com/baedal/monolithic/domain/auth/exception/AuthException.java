package com.baedal.monolithic.domain.auth.exception;

import lombok.Getter;

@Getter
public class AuthException extends RuntimeException{

    private final AuthStatusCode authStatusCode;

    public AuthException(AuthStatusCode authStatusCode) {
        super(authStatusCode.getMessage());
        this.authStatusCode = authStatusCode;
    }

}
