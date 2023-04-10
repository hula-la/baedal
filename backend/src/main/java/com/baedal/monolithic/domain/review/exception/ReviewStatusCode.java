package com.baedal.monolithic.domain.review.exception;

import com.baedal.monolithic.global.exception.ExceptionCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
//    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ReviewStatusCode implements ExceptionCode {

    NO_ACCESS(HttpStatus.FORBIDDEN,"review.no-access","접근 권한이 없습니다."),
    NO_ORDER(HttpStatus.BAD_REQUEST,"review.no-order","해당 리뷰가 존재하지 않습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;

}