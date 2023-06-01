package com.baedal.monolithic.domain.store.dto;

import com.baedal.monolithic.domain.store.entity.StoreMenuStatus;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;

public class MenuPutPostDto {

    @Getter
    @Builder
    public static class MenuGroupReq {

        @NotNull
        private String name;

        @NotNull
        private Integer priority;

        private String detail;
    }

    @Getter
    @Builder
    public static class MenuReq {

        @NotNull
        private String name;

        @NotNull
        private Long price;

        @NotNull
        private Integer priority;

        private String img;
        private String expIntro;
        private String expDetail;
        private StoreMenuStatus status;
    }

    @Getter
    @Builder
    public static class OptionGroupReq{

        @NotNull
        private String name;

        @NotNull
        private Integer priority;

        @NotNull
        private Boolean isRadio;

        private Integer min;
        private Integer max;

    }

    @Getter
    @Builder
    public static class OptionReq {

        @NotNull
        private String name;

        @NotNull
        private Integer priority;

        @NotNull
        private Long price;
    }
}
