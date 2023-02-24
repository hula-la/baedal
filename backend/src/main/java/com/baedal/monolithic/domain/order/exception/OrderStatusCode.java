package com.baedal.monolithic.domain.order.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
//    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum OrderStatusCode {

    NO_ACCESS(403,"order.no-access","접근 권한이 없습니다."),
    NO_ORDER(400,"order.no-order","해당 주문이 존재하지 않습니다."),
    ALREADY_RECEIVED(400, "order.already-received", "해당 주문이 이미 접수되었습니다."),
    NOT_MATCH_OPTION_GROUP(400, "order.not-match-option-group",
            "해당 옵션은 해당 옵션 그룹 내에 존재하지 않습니다."),
    NOT_MATCH_OPTION_CONDITION(400,"order.not-match-option-condition" ,
            "조건을 충족하지 않습니다.");

    private final int status;
    private final String code;
    private final String message;

}