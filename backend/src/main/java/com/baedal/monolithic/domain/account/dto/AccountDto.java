package com.baedal.monolithic.domain.account.dto;

import com.baedal.monolithic.domain.store.dto.PageVO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.List;

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
