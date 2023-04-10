package com.baedal.monolithic.global.exception;

import com.baedal.monolithic.domain.order.exception.OrderException;
import com.baedal.monolithic.domain.order.exception.OrderStatusCode;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Objects;
import java.util.stream.Collectors;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(BindException.class)
    public ResponseEntity<ExceptionResponse> bindFailureException(final BindException exception){
        return ResponseEntity.badRequest().body(extractErrorMessages(exception));
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> validationFailureException(final MethodArgumentNotValidException exception){
        return ResponseEntity.badRequest().body(extractErrorMessages(exception));
    }

    private ExceptionResponse extractErrorMessages(BindException e) {
        return new ExceptionResponse(
                Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage(),
                "argument-not-valid",
                HttpStatus.BAD_REQUEST
        );
    }


}
