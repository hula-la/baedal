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

    @NotNull
    @Builder.Default
    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.WAIT;

    @CreationTimestamp
    private Timestamp orderAt;

    private Boolean disposableReq;
    private Boolean kimchiReq;
    private String riderMsg;
    private String ownerMsg;
    private Long deliveryTip;
    private Long orderPrice;
    private Long totalPrice;
    private Timestamp exArrivalTime;

    @OneToMany(mappedBy = "order")
    private List<OrderMenu> orderMenus;

    public void updateStatus(OrderStatus status){
        this.status = status;
    }

    public void updateArrivalTime(Timestamp exArrivalTime){
        this.exArrivalTime = exArrivalTime;
    }

}
