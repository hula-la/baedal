package com.baedal.monolithic.domain.account.exception;

import com.baedal.monolithic.global.exception.ExceptionCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum AccountExceptionCode implements ExceptionCode {
    NO_USER(HttpStatus.BAD_REQUEST, "user.no-user","해당 유저가 존재하지 않습니다."),
    NO_ADDRESS(HttpStatus.BAD_REQUEST, "user.no-address","해당 주소가 존재하지 않습니다."),
    NOT_MATCH_USER_SELECTED_ADDRESS(HttpStatus.BAD_REQUEST, "user.not-match-user-selected-address",
            "사용자가 선택한 주소와 일치하지 않습니다."),
    ID_PWD_MISMATCH(HttpStatus.BAD_REQUEST, "user.id-pwd-mismatch","해당 유저가 존재하지 않습니다."),
    NOT_MATCH_USER_ID(HttpStatus.UNAUTHORIZED, "user.not-match-user-id","접근 권한이 없습니다."),
    DUPLICATE_NICKNAME(HttpStatus.BAD_REQUEST, "user.duplicate-nickname","이미 사용 중인 닉네임 입니다.");


    private final HttpStatus status;
    private final String code;
    private final String message;
}
