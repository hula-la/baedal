package com.baedal.monolithic.domain.store.entity;

import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Table(indexes = {
        @Index(name = "IX_delivery_address_01",columnList = "addressId"),
        @Index(name = "IX_delivery_address_02",columnList = "storeId, addressId")
})
public class DeliveryAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long storeId;

    @NotNull
    private Long addressId;

    @NotNull
    private Long tip;

}
