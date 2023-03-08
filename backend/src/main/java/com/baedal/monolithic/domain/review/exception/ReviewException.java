package com.baedal.monolithic.domain.review.exception;

import com.baedal.monolithic.global.exception.ExceptionCode;
import lombok.Getter;

@Getter
public class ReviewException extends RuntimeException{

    private final ExceptionCode exceptionCode;

    public ReviewException(ExceptionCode exceptionCode) {
        super(exceptionCode.getMessage());
        this.exceptionCode = exceptionCode;
    }

}
