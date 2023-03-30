package com.baedal.monolithic.domain.account.application;

import com.baedal.monolithic.domain.account.dto.AccountDto;
import com.baedal.monolithic.domain.account.dto.AddressDto;
import com.baedal.monolithic.domain.account.entity.Account;
import com.baedal.monolithic.domain.account.entity.Address;
import com.baedal.monolithic.domain.account.entity.UserAddress;
import org.springframework.stereotype.Component;

@Component
class AccountMapper {

    AccountDto.GetRes mapAccountEntityToGetDto(Account account){
        return AccountDto.GetRes.builder()
                .id(account.getId())
                .nickname(account.getNickname())
                .email(account.getEmail())
                .tel(account.getTel())
                .profile(account.getProfile())
                .build();
    }

    AddressDto.AddressInfo mapAddressEntityToGetInfoDto(Address address){
        return AddressDto.AddressInfo.builder()
                .id(address.getId())
                .addressName(address.getAddressName())
                .build();
    }

    AddressDto.UserAddressInfo mapUserAddressEntityToGetInfoDto(UserAddress userAddress, String addressName){
        return AddressDto.UserAddressInfo.builder()
                .id(userAddress.getId())
                .addressName(addressName)
                .addressDetail(userAddress.getAddressDetail())
                .build();
    }
    UserAddress mapPostDtoToUserAddressEntity(AddressDto.PostReq address, Long accountId){
        return UserAddress.builder()
                .accountId(accountId)
                .addressId(address.getAddressId())
                .addressDetail(address.getAddressDetail())
                .build();
    }
}
