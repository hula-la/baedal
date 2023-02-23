package com.baedal.monolithic.domain.store.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
//    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum StoreStatusCode {

    NO_STORE(400,"store.no-store","해당 가게가 존재하지 않습니다");

    private int status;
    private String code;
    private String message;
}