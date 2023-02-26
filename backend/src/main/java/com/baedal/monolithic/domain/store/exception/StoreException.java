package com.baedal.monolithic.domain.store.exception;

import com.baedal.monolithic.global.exception.ExceptionCode;
import lombok.Getter;

@Getter
public class StoreException extends RuntimeException{

    private final ExceptionCode exceptionCode;

    public StoreException(ExceptionCode exceptionCode) {
        super(exceptionCode.getMessage());
        this.exceptionCode = exceptionCode;
    }

}
