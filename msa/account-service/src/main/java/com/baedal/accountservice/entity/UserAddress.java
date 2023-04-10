package com.baedal.accountservice.entity;

import com.baedal.accountservice.dto.AddressDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@Table(indexes = {
                @Index(name = "IX_user_address_01",columnList = "accountId,addressId")
        })
public class UserAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long accountId;
    @NotNull
    private Long addressId;
    @NotNull
    private String addressDetail;


    public void updateDetail(AddressDto.PutReq accountPutReq) {
        this.addressDetail = accountPutReq.getAddressDetail();
    }
}
