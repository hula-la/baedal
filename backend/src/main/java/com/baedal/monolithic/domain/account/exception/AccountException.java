package com.baedal.monolithic.domain.account.exception;

import com.baedal.monolithic.global.exception.ExceptionCode;
import lombok.Getter;

@Getter
public class AccountException extends RuntimeException {

    private final ExceptionCode exceptionCode;

    public AccountException(ExceptionCode exceptionCode){
        super(exceptionCode.getMessage());
        this.exceptionCode = exceptionCode;
    }


}
