package com.baedal.monolithic.domain.store.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(indexes = {
        @Index(name = "IX_store_01",columnList = "categoryId")
})
public class Store implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long ownerId;

    @NotNull
    private Long categoryId;

    @NotNull
    private String name;
    @NotNull
    private int minPrice;
    private int recentOrder;
    private int recentReview;
    private int heartNum;
    private float rating = 0;
    private String notice;
    @NotNull
    private String time;
    @NotNull
    private String closedDay;
    @NotNull
    private String tel;
    @NotNull
    private Long addressId;

    private String addressDetail;
    private String deliveryRegion;

    private String info;

    @NotNull
    @Enumerated(EnumType.STRING)
    private StoreStatus storeStatus = StoreStatus.CLOSE;
    private String img;

}
