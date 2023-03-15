package com.baedal.storeService.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
@Getter
@Table(name = "DeliveryAddress", indexes = {
        @Index(name = "IX_delivery_address_01", columnList = "addressId"),
        @Index(name = "IX_delivery_address_02", columnList = "storeId, addressId")
})
public class DeliveryAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Positive
    @NotNull
    private Long storeId;

    @Positive
    @NotNull
    private Long addressId;

    @Positive
    @NotNull
    private Long tip;

}
