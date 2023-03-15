package com.baedal.accountservice.exception;


import org.springframework.http.HttpStatus;

public interface ExceptionCode {

    HttpStatus getStatus();
    String getCode();
    String getMessage();

}

