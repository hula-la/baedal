package com.baedal.monolithic.domain.review.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
//    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ReviewStatusCode {

    NO_ACCESS(403,"review.no-access","접근 권한이 없습니다."),
    NO_ORDER(400,"review.no-order","해당 리뷰가 존재하지 않습니다.");

    private final int status;
    private final String code;
    private final String message;

}