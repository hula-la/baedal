package com.baedal.monolithic.domain.store.dto;

import com.baedal.monolithic.domain.store.entity.StoreStatus;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

public class StoreDto {

    @Getter
    @Builder
    @ToString
    @EqualsAndHashCode(of = {"id"})
    public static class DetailedInfo {

        private Long id;
        private String ownerName;
        private String name;
        private int minPrice;
        private int recentOrder;
        private int recentReview;
        private int heartNum;
        private float rating;
        private String notice;
        private String time;
        private String closedDay;
        private String tel;
        private String deliveryRegion;
        private String address;
        private String info;
        private StoreStatus storeStatus;
        private String img;
        private List<MenuGetDto.MenuGroup> storeMenuGroups;

    }

    @Getter
    @Builder
    @ToString
    @EqualsAndHashCode(of = {"id"})
    public static class SummarizedInfo implements Serializable {

        private Long id;
        private String name;
        private float rating;
        private String img;

    }

    @AllArgsConstructor
    @Getter
    public static class GetRes {

        private Long results;
        private List<SummarizedInfo> stores;

    }

    @Getter
    @Setter
    @ToString
    @EqualsAndHashCode(of = {"categoryId","addressId","pageVO"})
    public static class GetReq {

        @NotNull(message = "{notnull}")
        private Long categoryId;

        @NotNull(message = "{notnull}")
        private Long addressId;

        private PageVO pageVO = new PageVO();

    }

    @Getter
    @Builder
    public static class PostPutReq {

        @NotNull
        private String name;

        @NotNull
        private Long categoryId;

        @NotNull
        private int minPrice;

        @NotNull
        private String time;

        @NotNull
        private String closedDay;

        @NotNull
        private Long addressId;

        @NotNull
        private String tel;

        @NotNull
        private String deliveryRegion;

        @NotNull
        private String addressDetail;

        private String img;
        private String notice;
        private String info;

    }
}
