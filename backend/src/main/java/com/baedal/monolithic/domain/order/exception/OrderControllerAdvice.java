package com.baedal.monolithic.domain.order.exception;

import com.baedal.monolithic.global.exception.ExceptionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class OrderControllerAdvice {

    @ExceptionHandler(OrderException.class)
    public ResponseEntity<ExceptionResponse> orderException(OrderException exception) {

        OrderStatusCode code = exception.getStoreStatusCode();

        return ResponseEntity.status(code.getStatus()).body(new ExceptionResponse(code));
    }


}
