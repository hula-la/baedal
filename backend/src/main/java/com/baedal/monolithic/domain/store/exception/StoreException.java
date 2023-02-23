package com.baedal.monolithic.domain.store.exception;

public class StoreException extends RuntimeException{

    private final StoreStatusCode storeStatusCode;

    public StoreException(StoreStatusCode storeStatusCode) {
        super(storeStatusCode.getMessage());
        this.storeStatusCode = storeStatusCode;
    }

}
