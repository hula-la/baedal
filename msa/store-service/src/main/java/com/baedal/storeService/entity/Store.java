package com.baedal.storeService.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(indexes = {
        @Index(name = "IX_store_01",columnList = "ownerId")
})
public class Store implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Positive
    @NotNull
    private Long ownerId;

    @Positive
    @NotNull
    private Long categoryId;

    @NotNull
    private String name;

    @NotNull
    private int minPrice;
    private int recentOrder;
    private int recentReview;
    private int heartNum;
    private float rating;
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
    @Builder.Default()
    private StoreStatus storeStatus = StoreStatus.CLOSE;
    private String img;

    public void updateHeartNum(int plus) {
        this.heartNum += plus;
    }

}
