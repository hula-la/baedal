package com.baedal.monolithic.domain.account.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum AccountExceptionCode {
    NO_USER(HttpStatus.BAD_REQUEST, "user.no-user","해당 유저가 존재하지 않습니다."),
    ID_PWD_MISMATCH(HttpStatus.BAD_REQUEST, "user.id-pwd-mismatch","해당 유저가 존재하지 않습니다."),
    DUPLICATE_NICKNAME(HttpStatus.BAD_REQUEST, "user.duplicate-nickname","이미 사용 중인 닉네임 입니다.");


    private HttpStatus status;
    private String code;
    private String msg;
}
