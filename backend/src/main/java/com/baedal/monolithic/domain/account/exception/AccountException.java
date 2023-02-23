package com.baedal.monolithic.domain.account.exception;

public class AccountException extends RuntimeException {

    private final AccountExceptionCode accountExceptionCode;

    public AccountException(AccountExceptionCode accountExceptionCode){
        super(accountExceptionCode.getMsg());
        this.accountExceptionCode = accountExceptionCode;
    }

    public AccountExceptionCode getCode(){
        return this.accountExceptionCode;
    }

}
