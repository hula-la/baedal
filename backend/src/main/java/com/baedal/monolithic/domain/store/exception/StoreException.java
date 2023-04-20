package com.baedal.monolithic.domain.store.exception;

import com.baedal.monolithic.global.exception.ExceptionCode;
import lombok.Getter;

@Getter
public class StoreException extends RuntimeException{

    private final StoreStatusCode storeStatusCode;

    public StoreException(StoreStatusCode storeStatusCode) {
        super(storeStatusCode.getMessage());
        this.storeStatusCode = storeStatusCode;
    }

}
