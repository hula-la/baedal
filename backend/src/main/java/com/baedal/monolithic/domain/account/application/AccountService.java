package com.baedal.monolithic.domain.account.application;

import com.baedal.monolithic.domain.account.dto.AccountDto;
import com.baedal.monolithic.domain.account.entity.Account;
import com.baedal.monolithic.domain.account.exception.AccountException;
import com.baedal.monolithic.domain.account.exception.AccountExceptionCode;
import com.baedal.monolithic.domain.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final ModelMapper modelMapper;

    public Account getUserEntity(Long userId){
        return accountRepository.findById(userId)
                .orElseThrow(() -> new AccountException(AccountExceptionCode.NO_USER));
    }

    public void deleteAccount(Long accountId) {
        Account account = getUserEntity(accountId);
        accountRepository.delete(account);
    }

    public AccountDto.GetRes findAccount(Long accountId) {
        Account account = getUserEntity(accountId);
        return modelMapper.map(account, AccountDto.GetRes.class);
    }

    @Transactional
    public void updateAccount(Long accountId, AccountDto.PutReq accountPutReq) {
        Account account = getUserEntity(accountId);
        account.updateInfo(accountPutReq);
    }

}
