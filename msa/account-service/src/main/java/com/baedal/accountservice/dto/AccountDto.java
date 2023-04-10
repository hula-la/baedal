package com.baedal.accountservice.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

public class AccountDto {

    @Getter
    public static class GetRes {
        private Long id;
        private String nickname;
        private String email;
        private String tel;
        private String profile;
    }

    @Setter
    @Getter
    public static class PutReq {
        @NotNull(message = "{notnull}")
        private String nickname;
        @NotNull(message = "{notnull}")
        private String tel;
        @NotNull(message = "{notnull}")
        private String profile;
    }

}
