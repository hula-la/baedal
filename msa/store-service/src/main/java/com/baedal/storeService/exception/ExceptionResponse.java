package com.baedal.storeService.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
public class ExceptionResponse {
    private String message;
    private String code;
    private HttpStatus status;

    public ExceptionResponse(StoreStatusCode code) {
        this.message = code.getMessage();
        this.status = code.getStatus();
        this.code = code.getCode();
    }

    public ExceptionResponse(String message) {
        this.message = message;
    }
    public ExceptionResponse(String message, String code, HttpStatus status) {
        this.message = message;
        this.code = code;
        this.status = status;
    }

}
