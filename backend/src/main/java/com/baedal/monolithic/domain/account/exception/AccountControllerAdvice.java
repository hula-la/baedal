package com.baedal.monolithic.domain.account.exception;

import com.baedal.monolithic.global.exception.ExceptionCode;
import com.baedal.monolithic.global.exception.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AccountControllerAdvice {


    @ExceptionHandler(AccountException.class)
    public ResponseEntity<ExceptionResponse> processValidationError(AccountException exception) {
        ExceptionCode exceptionCode = exception.getExceptionCode();
        return ResponseEntity.status(exceptionCode.getStatus()).body(new ExceptionResponse(exceptionCode));
    }


}
