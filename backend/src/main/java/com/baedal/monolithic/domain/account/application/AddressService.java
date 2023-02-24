package com.baedal.monolithic.domain.account.application;

import com.baedal.monolithic.domain.account.entity.Address;
import com.baedal.monolithic.domain.account.entity.UserAddress;
import com.baedal.monolithic.domain.account.exception.AccountException;
import com.baedal.monolithic.domain.account.exception.AccountExceptionCode;
import com.baedal.monolithic.domain.account.repository.AccountRepository;
import com.baedal.monolithic.domain.account.repository.AddressRepository;
import com.baedal.monolithic.domain.account.repository.UserAddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final UserAddressRepository userAddressRepository;
    private final AddressRepository addressRepository;



    public String[] getUserAddressName(Long userAddressId){
        UserAddress userAddress = userAddressRepository.findById(userAddressId)
                .orElseThrow(() -> new AccountException(AccountExceptionCode.NO_ADDRESS));

        return new String[] {
                getAddressName(userAddress.getAddressId()),
                userAddress.getAddressDetail()
        };
    }
    public Long getAddressId(Long userAddressId){
        UserAddress userAddress = userAddressRepository.findById(userAddressId)
                .orElseThrow(() -> new AccountException(AccountExceptionCode.NO_ADDRESS));

        return userAddress.getAddressId();
    }
    public String getAddressName(Long addressId){
        return addressRepository.findById(addressId)
                .orElseThrow(() -> new AccountException(AccountExceptionCode.NO_ADDRESS))
                .getName();
    }



}
