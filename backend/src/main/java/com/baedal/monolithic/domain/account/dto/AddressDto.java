package com.baedal.monolithic.domain.account.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

public class AddressDto {


    @Getter
    @Setter
    @AllArgsConstructor
    public static class AddressInfo {

        private Long id;
        private String addressName;

    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class UserAddressInfo {

        private Long id;
        private String addressName;
        private String addressDetail;

    }

    @AllArgsConstructor
    @Getter
    public static class GetRes {
        private List<UserAddressInfo> addresses;

    }

    @Setter
    @Getter
    public static class PostReq {
        @NotNull(message = "{notnull}")
        private Long addressId;
        @NotNull(message = "{notnull}")
        private String addressDetail;
    }

    @Setter
    @Getter
    public static class PutReq {
        @NotNull(message = "{notnull}")
        private Long userAddressId;
        @NotNull(message = "{notnull}")
        private String addressDetail;
    }


    @Setter
    @Getter
    @AllArgsConstructor
    public static class SearchRes {
        private List<AddressInfo> addresses;
    }
}
