package com.baedal.monolithic.global.msg;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ValidatorMsg {

    EMPTY_MESSAGE("비어있는 항목을 입력해주세요."),
    EMAIL_MESSAGE("올바른 이메일 형식이 아닙니다."),
    NAME_MESSAGE("이름은 특수문자(-_!?.,)를 포함하여 공백없이 20자 이내로 작성 가능합니다.");

    private String name;

}
