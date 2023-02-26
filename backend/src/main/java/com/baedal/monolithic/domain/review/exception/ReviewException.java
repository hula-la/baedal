package com.baedal.monolithic.domain.review.exception;

public class ReviewException extends RuntimeException{

    private final ReviewStatusCode storeStatusCode;

    public ReviewException(ReviewStatusCode storeStatusCode) {
        super(storeStatusCode.getMessage());
        this.storeStatusCode = storeStatusCode;
    }

}
