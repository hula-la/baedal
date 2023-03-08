package com.baedal.monolithic.domain.order.exception;

import lombok.Getter;

@Getter
public class OrderException extends RuntimeException{

    private final OrderStatusCode storeStatusCode;

    public OrderException(OrderStatusCode storeStatusCode) {
        super(storeStatusCode.getMessage());
        this.storeStatusCode = storeStatusCode;
    }

}
