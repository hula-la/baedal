package com.baedal.monolithic.domain.store.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(indexes = {
        @Index(name = "IX_store_01",columnList = "categoryId")
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
    private int recentOrder;
    private int recentReview;
    private int heartNum;

    @Builder.Default
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

    @NotNull
    private String deliveryRegion;

    private String info;

    @OneToMany(mappedBy = "store")
    @OrderBy("priority ASC")
    private Set<StoreMenuGroup> menuGroups;


    @NotNull
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private StoreStatus storeStatus = StoreStatus.CLOSE;
    private String img;

    public void updateHeartNum(int plus) {
        this.heartNum += plus;
    }

}
