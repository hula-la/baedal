package com.baedal.monolithic.domain.order.dto;

import com.baedal.monolithic.domain.order.entity.OrderStatus;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public class OrderDto {

    @Getter
    @Builder
    @EqualsAndHashCode(of = {"id"})
    public static class DetailedInfo {

        private Long id;

        // 직접 추가해야 하는 필드
        private String storeName;
        private String address;
        private List<Menu> menus;

        private Boolean disposableReq;
        private Boolean kimchiReq;
        private String riderMsg;
        private String ownerMsg;
        @Enumerated(EnumType.STRING)
        private OrderStatus status;
        private Long deliveryTip;
        private Long orderPrice;
        private Long totalPrice;

        @NotNull
        private Timestamp orderAt;
        private Timestamp exArrivalTime;

    }

    @Getter
    @Builder
    @EqualsAndHashCode(of = {"id"})
    public static class SummarizedInfo {

        private Long id;
        private String name; // 추가해줘야함
        private String menuSummary;
        private Long totalPrice;

        @NotNull(message = "{notnull}")
        private Timestamp orderAt;
        private Timestamp exArrivalTime;

    }

    @Getter
    @Builder
    @EqualsAndHashCode(of = {"id"})
    public static class Menu {

        private Long id;

        // 직접 추가
        private String menuName;

        private Integer cnt;
        private String options;

    }

    @Getter
    public static class PostReq {

        @NotNull(message = "{notnull}")
        private Long storeId;

        @NotNull(message = "{notnull}")
        private Boolean disposableReq;

        @NotNull(message = "{notnull}")
        private Boolean kimchiReq;

        private String riderMsg;
        private String ownerMsg;
        private List<MenuPostReq> menus;
        private String addressDetail;

    }

    @Getter
    public static class MenuPostReq {

        private Long menuId;
        private Integer count;
        private Map<Long,List<Long>> options;

    }
}
