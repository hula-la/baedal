package com.baedal.storeService.exception;

import lombok.Getter;

@Getter
public class StoreException extends RuntimeException{

    private final StoreStatusCode storeStatusCode;

    public StoreException(StoreStatusCode exceptionCode) {
        super(exceptionCode.getMessage());
        this.storeStatusCode = exceptionCode;
    }

}
