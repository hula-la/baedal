package com.baedal.storeService.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum StoreStatusCode {

    NO_STORE(HttpStatus.BAD_REQUEST,"store.no-store","해당 가게가 존재하지 않습니다"),
    NO_MENU(HttpStatus.BAD_REQUEST,"store.no-menu","해당 메뉴가 존재하지 않습니다"),
    NO_OPTION(HttpStatus.BAD_REQUEST,"store.no-option","해당 옵션이 존재하지 않습니다"),
    NO_OPTION_GROUP(HttpStatus.BAD_REQUEST,"store.no-option-group","해당 옵션 그룹이 존재하지 않습니다"),
    NO_DELIVERY_REGION(HttpStatus.BAD_REQUEST, "store.on-delivery-region", "주문 지역이 아닙니다."),
    NOT_EXCEED_MIN_PRICE(HttpStatus.BAD_REQUEST, "store.not-exceed-min-price", "최소 가격을 넘지 않습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}