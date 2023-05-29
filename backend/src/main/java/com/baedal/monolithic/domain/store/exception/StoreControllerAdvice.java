package com.baedal.monolithic.domain.store.exception;

import com.baedal.monolithic.global.exception.ExceptionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class StoreControllerAdvice {

    @ExceptionHandler(StoreException.class)
    public ResponseEntity<ExceptionResponse> reviewException(StoreException exception) {

        StoreStatusCode code = exception.getStoreStatusCode();

        return ResponseEntity.status(code.getStatus()).body(new ExceptionResponse(code));
    }


}
