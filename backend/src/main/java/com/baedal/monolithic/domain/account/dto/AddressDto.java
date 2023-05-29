package com.baedal.monolithic.domain.account.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.util.List;

public class AddressDto {

    @Getter
    @Builder
    public static class AddressInfo {

        private Long id;
        private String addressName;
    }

    @Getter
    @Builder
    public static class UserAddressInfo {

        private Long id;
        private String addressName;
        private String addressDetail;
//        private Boolean isSelect;
    }

    @Getter
    @AllArgsConstructor
    public static class GetRes {

        private List<UserAddressInfo> addresses;
    }

    @Getter
    @AllArgsConstructor
    public static class SearchRes {

        private List<AddressInfo> addresses;
    }

    @Getter
    public static class PostReq {

        @NotNull(message = "{notnull}")
        private Long addressId;

        @NotNull(message = "{notnull}")
        private String addressDetail;
    }

    @Getter
    public static class PutReq {

        @NotNull(message = "{notnull}")
        private Long userAddressId;

        @NotNull(message = "{notnull}")
        private String addressDetail;
    }
}
