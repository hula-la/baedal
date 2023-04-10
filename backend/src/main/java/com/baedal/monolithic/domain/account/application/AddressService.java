package com.baedal.monolithic.domain.account.application;

import com.baedal.monolithic.domain.account.dto.AccountDto;
import com.baedal.monolithic.domain.account.dto.AddressDto;
import com.baedal.monolithic.domain.account.entity.Address;
import com.baedal.monolithic.domain.account.entity.UserAddress;
import com.baedal.monolithic.domain.account.exception.AccountException;
import com.baedal.monolithic.domain.account.exception.AccountExceptionCode;
import com.baedal.monolithic.domain.account.repository.AccountRepository;
import com.baedal.monolithic.domain.account.repository.AddressRepository;
import com.baedal.monolithic.domain.account.repository.UserAddressRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final UserAddressRepository userAddressRepository;
    private final AddressRepository addressRepository;
    private final ModelMapper modelMapper;
    private final AccountRepository accountRepository;

    //    public String[] getUserAddressName(Long userAddressId){
//        UserAddress userAddress = userAddressRepository.findById(userAddressId)
//                .orElseThrow(() -> new AccountException(AccountExceptionCode.NO_ADDRESS));
//
//        return new String[] {
//                getAddressName(userAddress.getAddressId()),
//                userAddress.getAddressDetail()
//        };
//    }
    public Long getAddressId(Long userAddressId){
        UserAddress userAddress = userAddressRepository.findById(userAddressId)
                .orElseThrow(() -> new AccountException(AccountExceptionCode.NO_ADDRESS));

        return userAddress.getAddressId();
    }
    public String getAddressName(Long addressId){
        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new AccountException(AccountExceptionCode.NO_ADDRESS));
        return concatAddressName(address);
    }

    public String concatAddressName(Address address) {
        return address.getSido() + " " + address.getSigungu() + " " + address.getDong();
    }

    public List<AddressDto.UserAddressInfo> findAllAddress(Long accountId) {
        return userAddressRepository.findAllByAccountId(accountId)
                .stream()
                .map(userAddress ->
                    new AddressDto.UserAddressInfo(userAddress.getId(),
                            getAddressName(userAddress.getAddressId()),
                            userAddress.getAddressDetail())
                ).collect(Collectors.toList());
    }

    @Transactional
    public void updateAddress(Long accountId, AddressDto.PutReq accountPutReq) {
        UserAddress userAddress = userAddressRepository.findById(accountPutReq.getUserAddressId())
                .orElseThrow(() -> new AccountException(AccountExceptionCode.NO_ADDRESS));

        if (!userAddress.getAccountId().equals(accountId))
            throw new AccountException(AccountExceptionCode.NOT_MATCH_USER_ID);

        userAddress.updateDetail(accountPutReq);
    }

    public Long createAddress(Long accountId, AddressDto.PostReq addressPutReq) {
        UserAddress userAddress = modelMapper.map(addressPutReq, UserAddress.class);
        userAddress.setAccountId(accountId);

        if (!accountRepository.existsById(addressPutReq.getAddressId()))
            throw new AccountException(AccountExceptionCode.NO_ADDRESS);

        UserAddress savedUserAddress = userAddressRepository.save(userAddress);

        return savedUserAddress.getId();
    }

    public void deleteAddress(Long userAddressId) {
        UserAddress userAddress = userAddressRepository.findById(userAddressId)
                .orElseThrow(() -> new AccountException(AccountExceptionCode.NO_ADDRESS));

        userAddressRepository.delete(userAddress);
    }

    public List<AddressDto.AddressInfo> searchAllAddress(String addressKeyword) {
        return addressRepository.searchAddress(addressKeyword)
                .stream()
                .map(address -> new AddressDto.AddressInfo(address.getId(), concatAddressName(address)))
                .collect(Collectors.toList());
    }
}
