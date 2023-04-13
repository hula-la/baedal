package com.baedal.monolithic.domain.account.dto;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class AccountDto {

    @Getter
    @Builder
    public static class GetRes {
        private Long id;
        private String nickname;
        private String email;
        private String tel;
        private String profile;
    }

    @Getter
    public static class PutReq {
        @NotNull(message = "{notnull}")
        private String nickname;

        @NotNull(message = "{notnull}")
        @Pattern(regexp = "^01([0|1|6|7|8|9]?)-([0-9]{3,4})-([0-9]{4})$",
                message = "{pattern.tel}")
        private String tel;

        private String profile;
    }

}
