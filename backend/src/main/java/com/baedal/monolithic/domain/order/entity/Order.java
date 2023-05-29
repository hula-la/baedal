package com.baedal.monolithic.domain.order.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
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
    @Builder.Default
    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.WAIT;

    private Long deliveryTip;
    private Long orderPrice;
    private Long totalPrice;

    @CreationTimestamp
    private Timestamp orderAt;

    private Timestamp exArrivalTime;

    @OneToMany(mappedBy = "order")
    private List<OrderMenu> orderMenus;

}
