package com.baedal.storeService.dto;

import com.baedal.storeService.entity.StoreStatus;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

public class StoreDto {

    @Getter
    @Setter
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
        private float rating = 0;
        private String notice;
        private String time;
        private String closedDay;
        private String tel;
        private String deliveryRegion;
        private String address;
        private String info;
        @Enumerated(EnumType.STRING)
        private StoreStatus storeStatus = StoreStatus.CLOSE;
        private String img;

    }

    @Getter
    @Setter
    @ToString
    @EqualsAndHashCode(of = {"id"})
    public static class SummarizedInfo implements Serializable {

        private Long id;
        private String name;
        private float rating = 0;
        private String img;

    }

    @AllArgsConstructor
    @Getter
    public static class GetRes {

        private Long results; // 총 갯수
        private List<SummarizedInfo> stores; // 가게 목록

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
}
