package com.baedal.accountservice.security.exception;

public class OAuthProcessingException extends RuntimeException {

    public OAuthProcessingException(String message) {
        super(message);
    }
}