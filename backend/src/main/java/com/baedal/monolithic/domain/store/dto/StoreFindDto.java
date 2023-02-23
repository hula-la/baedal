package com.baedal.monolithic.domain.store.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of = {"id"})
public class StoreFindDto {

    private Long id;
    private String ownerName;
    private String name;
    private int minPrice;
    private String deliveryTypByPrice;
    private int recentOrder;
    private int recentReview;
    private int heartNum;
    private float rating = 0;
    private String notice;
    private String time;
    private String closedDay;
    private String tel;
    private String deliveryRegion;
    private String address;
    private String info;
    @Enumerated(EnumType.STRING)
    private StoreStatus storeStatus = StoreStatus.CLOSE;
    private String img;

}
