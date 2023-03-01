package com.baedal.monolithic.domain.auth.exception;

public class OAuthProcessingException extends RuntimeException {

    public OAuthProcessingException(String message) {
        super(message);
    }
}