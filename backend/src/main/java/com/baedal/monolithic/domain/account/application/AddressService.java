package com.baedal.monolithic.domain.account.application;

import com.baedal.monolithic.domain.account.dto.AddressDto;
import com.baedal.monolithic.domain.account.entity.Account;
import com.baedal.monolithic.domain.account.entity.Address;
import com.baedal.monolithic.domain.account.entity.UserAddress;
import com.baedal.monolithic.domain.account.exception.AccountException;
import com.baedal.monolithic.domain.account.exception.AccountExceptionCode;
import com.baedal.monolithic.domain.account.repository.AddressRepository;
import com.baedal.monolithic.domain.account.repository.UserAddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final UserAddressRepository userAddressRepository;
    private final AddressRepository addressRepository;
    private final AccountMapper accountMapper;
    private final AccountService accountService;

    @Transactional(readOnly = true)
    public List<AddressDto.UserAddressInfo> findAllAddress(final Long accountId) {
        return userAddressRepository.findAllByAccountId(accountId)
                .stream()
                .map(userAddress ->
                    accountMapper.mapUserAddressEntityToGetInfoDto(userAddress,
                            getAddressName(userAddress.getAddressId()))
                ).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<AddressDto.AddressInfo> searchAllAddress(final String addressKeyword) {
        return addressRepository.searchAddress(addressKeyword)
                .stream()
                .map(accountMapper::mapAddressEntityToGetInfoDto)
                .collect(Collectors.toList());
    }

    public Long createUserAddress(final Long accountId, final AddressDto.PostReq addressPostReq) {
        UserAddress userAddress = accountMapper.mapPostDtoToUserAddressEntity(addressPostReq, accountId);

        if (!addressRepository.existsById(addressPostReq.getAddressId()))
            throw new AccountException(AccountExceptionCode.NO_ADDRESS);

        UserAddress savedUserAddress = userAddressRepository.save(userAddress);

        return savedUserAddress.getId();
    }

    @Transactional
    public void updateAddress(final Long accountId, final AddressDto.PutReq accountPutReq) {
        UserAddress userAddress = getUserAddressEntity(accountPutReq.getUserAddressId());

        if (!userAddress.getAccountId().equals(accountId))
            throw new AccountException(AccountExceptionCode.NOT_MATCH_USER_ID);

        userAddress.updateDetail(accountPutReq);
    }

    @Transactional
    public void deleteUserAddress(final Long accountId, final Long userAddressId) {
        if (!userAddressId.equals(accountService.getAddressIdOfUser(accountId)))
            throw new AccountException(AccountExceptionCode.NOT_MATCH_USER_SELECTED_ADDRESS);

        userAddressRepository.delete(getUserAddressEntity(userAddressId));
    }


    @Transactional(readOnly = true)
    public Long getAddressIdByAccountId(final Long accountId){
        return getAddressIdByUserAddressId(accountService.getUserEntity(accountId).getUserAddressId());
    }

    private Long getAddressIdByUserAddressId(final Long userAddressId){
        UserAddress userAddress = userAddressRepository.findById(userAddressId)
                .orElseThrow(() -> new AccountException(AccountExceptionCode.NO_ADDRESS));

        return userAddress.getAddressId();
    }

    private String getAddressName(final Long addressId){
        return getAddressEntity(addressId).getAddressName();
    }

    private UserAddress getUserAddressEntity(final Long userAddressId) {
        return userAddressRepository.findById(userAddressId)
                .orElseThrow(() -> new AccountException(AccountExceptionCode.NO_ADDRESS));
    }

    private Address getAddressEntity(final Long addressId) {
        return addressRepository.findById(addressId)
                .orElseThrow(() -> new AccountException(AccountExceptionCode.NO_ADDRESS));
    }

}
