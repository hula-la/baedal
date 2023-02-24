package com.baedal.monolithic.domain.order.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
public class OrderFindDetailDto {

    private Long id;

    // 직접 추가해야 하는 필드
    private String storeName;
    private String address;
    private List<OrderMenuDto> menus;
    ///
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
