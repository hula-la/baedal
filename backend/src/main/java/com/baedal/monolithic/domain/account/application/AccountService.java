package com.baedal.monolithic.domain.account.application;

import com.baedal.monolithic.domain.account.entity.Account;
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
public class AccountService {

    private final AccountRepository accountRepository;

    public Account getUserEntity(Long userId){
        return accountRepository.findById(userId)
                .orElseThrow(() -> new AccountException(AccountExceptionCode.NO_USER));
    }


}
