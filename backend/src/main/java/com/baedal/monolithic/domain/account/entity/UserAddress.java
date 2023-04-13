package com.baedal.monolithic.domain.account.entity;

import com.baedal.monolithic.domain.account.dto.AddressDto;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
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


    public void updateDetail(final AddressDto.PutReq accountPutReq) {
        this.addressDetail = accountPutReq.getAddressDetail();
    }
}
