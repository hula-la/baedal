package com.baedal.monolithic.domain.owner.exception;

import com.baedal.monolithic.global.exception.ExceptionCode;
import com.baedal.monolithic.global.exception.ExceptionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class OwnerControllerAdvice {

    @ExceptionHandler(OwnerException.class)
    public ResponseEntity<ExceptionResponse> reviewException(OwnerException exception) {

        ExceptionCode code = exception.getExceptionCode();

        return ResponseEntity.status(code.getStatus()).body(new ExceptionResponse(code));
    }


}
