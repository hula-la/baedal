package com.baedal.monolithic.domain.order.dto;

import com.baedal.monolithic.domain.order.entity.OrderStatus;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

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
        private Boolean disposableReq;
        private Boolean kimchiReq;
        private String riderMsg;
        private String ownerMsg;
        private OrderStatus status;
        private Long deliveryTip;
        private Long orderPrice;
        private Long totalPrice;
        private Timestamp orderAt;
        private Timestamp exArrivalTime;

        // 직접 추가해야 하는 필드
        private String storeName;
        private String address;
        private List<Menu> menus;
    }

    @Getter
    @Builder
    @EqualsAndHashCode(of = {"id"})
    public static class SummarizedInfo {

        private Long id;
        private Timestamp orderAt;
        private Timestamp exArrivalTime;
        private OrderStatus status;

        // 추가해줘야함
        private String name;
        private String menuSummary;
        private Long totalPrice;

    }

    @Getter
    @Builder
    @EqualsAndHashCode(of = {"id"})
    public static class Menu {

        private Long id;
        private Integer cnt;
        private String options;

        // 직접 추가
        private String menuName;

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
