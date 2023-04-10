package com.baedal.monolithic.global.exception;

import com.baedal.monolithic.domain.order.exception.OrderException;
import com.baedal.monolithic.domain.order.exception.OrderStatusCode;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.stream.Collectors;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> validationFailureException(final MethodArgumentNotValidException exception){
        return ResponseEntity.badRequest().body(extractErrorMessages(exception));
    }

    private ExceptionResponse extractErrorMessages(MethodArgumentNotValidException e) {
        return new ExceptionResponse(
                e.getBindingResult().getFieldError().getDefaultMessage(),
                "argument-not-valid",
                HttpStatus.BAD_REQUEST
        );
    }


}
