package com.baedal.monolithic.domain.store.exception;

import com.baedal.monolithic.domain.review.exception.ReviewException;
import com.baedal.monolithic.global.exception.ExceptionCode;
import com.baedal.monolithic.global.exception.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class StoreControllerAdvice {

    @ExceptionHandler(StoreException.class)
    public ResponseEntity<ExceptionResponse> reviewException(StoreException exception) {
        ExceptionCode code = exception.getExceptionCode();
        return ResponseEntity.status(code.getStatus()).body(new ExceptionResponse(code));
    }


}
