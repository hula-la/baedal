package com.baedal.accountservice.security.exception;

import com.baedal.accountservice.exception.ExceptionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AuthControllerAdvice {

    @ExceptionHandler(AuthException.class)
    public ResponseEntity<ExceptionResponse> orderException(AuthException exception) {
        AuthStatusCode code = exception.getAuthStatusCode();
        return ResponseEntity.status(code.getStatus()).body(new ExceptionResponse(code));
    }


}
