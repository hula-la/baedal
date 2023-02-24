package com.baedal.monolithic.domain.store.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
//    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum StoreStatusCode {

    NO_STORE(400,"store.no-store","해당 가게가 존재하지 않습니다"),
    NO_MENU(400,"store.no-menu","해당 메뉴가 존재하지 않습니다"),
    NO_OPTION(400,"store.no-option","해당 옵션이 존재하지 않습니다"),
    NO_OPTION_GROUP(400,"store.no-option-group","해당 옵션 그룹이 존재하지 않습니다"),
    NO_DELIVERY_REGION(400, "store.on-delivery-region", "주문 지역이 아닙니다."),
    NOT_EXCEED_MIN_PRICE(400, "store.not-exceed-min-price", "최소 가격을 넘지 않습니다.");

    private final int status;
    private final String code;
    private final String message;
}