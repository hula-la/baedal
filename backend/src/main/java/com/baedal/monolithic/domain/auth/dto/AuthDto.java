package com.baedal.monolithic.domain.auth.dto;

import com.baedal.monolithic.domain.account.entity.Role;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

public class AuthDto {

    @Getter
    @Builder
    public static class GetRes implements Serializable {

        private static final long serialVersionUID = -3423412341234127459L;

        private Long id;
        private Role role;
        private String refreshToken;
    }

}
