package com.baedal.monolithic.domain.order.entity;

import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
@Setter
@Table(indexes = {
                @Index(name = "IX_orders_01",columnList = "orderId,menuId")
        })
public class OrderMenu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long orderId;
    @NotNull
    private Long menuId;
    @NotNull
    private Integer count;
    private String options;

}
