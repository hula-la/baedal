package com.baedal.monolithic.domain.account.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {

    GUEST("ROLE_GUEST", "가입 전 사용자"),
    USER("ROLE_USER", "일반 사용자"),
    OWNER("ROLE_OWNER", "사장님"),
    ADMIN("ROLE_ADMIN", "관리자");

    private final String key;
    private final String title;
}
