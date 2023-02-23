package com.baedal.monolithic.domain.store.entity;

import com.baedal.monolithic.domain.store.dto.StoreStatus;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(indexes = {
        @Index(name = "IX_store_01",columnList = "ownerId")
})
public class Store {

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
    @NotNull
    private String deliveryTipByPrice;
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
    private String deliveryRegion;
    @NotNull
    private Long addressId;

    private String addressDetail;

    private String info;

    @NotNull
    @Enumerated(EnumType.STRING)
    private StoreStatus storeStatus = StoreStatus.CLOSE;
    private String img;

}
