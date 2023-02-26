package com.baedal.monolithic.domain.order.exception;

import com.baedal.monolithic.global.exception.ExceptionCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum OrderStatusCode implements ExceptionCode {

    NO_ACCESS(HttpStatus.FORBIDDEN,"order.no-access","접근 권한이 없습니다."),
    NO_ORDER(HttpStatus.BAD_REQUEST,"order.no-order","해당 주문이 존재하지 않습니다."),
    ALREADY_RECEIVED(HttpStatus.BAD_REQUEST, "order.already-received", "해당 주문이 이미 접수되었습니다."),
    NOT_MATCH_OPTION_GROUP(HttpStatus.BAD_REQUEST, "order.not-match-option-group",
            "해당 옵션은 해당 옵션 그룹 내에 존재하지 않습니다."),
    NOT_MATCH_OPTION_CONDITION(HttpStatus.BAD_REQUEST,"order.not-match-option-condition" ,
            "조건을 충족하지 않습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;

}