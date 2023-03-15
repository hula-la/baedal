package com.baedal.accountservice.exception;

import lombok.Getter;

@Getter
public class AccountException extends RuntimeException {

    private final ExceptionCode exceptionCode;

    public AccountException(ExceptionCode exceptionCode){
        super(exceptionCode.getMessage());
        this.exceptionCode = exceptionCode;
    }


}
