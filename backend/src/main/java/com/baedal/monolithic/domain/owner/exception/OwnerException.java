package com.baedal.monolithic.domain.owner.exception;

import com.baedal.monolithic.global.exception.ExceptionCode;
import lombok.Getter;

@Getter
public class OwnerException extends RuntimeException{

    private final ExceptionCode exceptionCode;

    public OwnerException(ExceptionCode exceptionCode) {
        super(exceptionCode.getMessage());
        this.exceptionCode = exceptionCode;
    }

}
