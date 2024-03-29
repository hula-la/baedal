package com.baedal.monolithic.domain.store.entity;

import com.baedal.monolithic.domain.store.dto.StoreDto;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    @NotNull
    private String time;

    @NotNull
    private String closedDay;

    @NotNull
    private String tel;

    @NotNull
    private Long addressId;

    @NotNull
    private String deliveryRegion;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private StoreStatus storeStatus = StoreStatus.CLOSE;

    @Builder.Default
    private float rating = 0;

    private int recentOrder;
    private int recentReview;
    private int heartNum;
    private String notice;
    private String addressDetail;
    private String info;
    private String img;

    @OneToMany(mappedBy = "store", orphanRemoval = true)
    @OrderBy("priority ASC")
    private Set<StoreMenuGroup> menuGroups;

    public void updateHeartNum(int plus) {
        this.heartNum += plus;
    }

    public void update(StoreDto.PostPutReq postPutReq) {
        this.name = postPutReq.getName();
        this.categoryId = postPutReq.getCategoryId();
        this.minPrice = postPutReq.getMinPrice();
        this.time = postPutReq.getTime();
        this.closedDay = postPutReq.getClosedDay();
        this.addressId = postPutReq.getAddressId();
        this.tel = postPutReq.getTel();
        this.deliveryRegion = postPutReq.getDeliveryRegion();
        this.addressDetail = postPutReq.getAddressDetail();
        this.img = postPutReq.getImg();
        this.notice = postPutReq.getNotice();
        this.info = postPutReq.getInfo();
    }

}
