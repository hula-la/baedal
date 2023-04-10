package com.baedal.monolithic.domain.order.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@Table(name = "ORDERS",
        indexes = {
        @Index(name = "IX_orders_01",columnList = "accountId,storeId")
})
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long accountId;
    @NotNull
    private Long storeId;
    @NotNull
    private Long addressId;
    private String addressDetail;
    @NotNull
    private String menuSummary;
    private Boolean disposableReq;
    private Boolean kimchiReq;
    private String riderMsg;
    private String ownerMsg;
    @NotNull
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    private Long deliveryTip;
    private Long orderPrice;
    private Long totalPrice;

    @NotNull
    @CreationTimestamp
    private Timestamp orderAt;
    private Timestamp exArrivalTime;

}
