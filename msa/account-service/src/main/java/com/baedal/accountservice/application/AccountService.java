package com.baedal.accountservice.application;

import com.baedal.accountservice.dto.AccountDto;
import com.baedal.accountservice.entity.Account;
import com.baedal.accountservice.exception.AccountException;
import com.baedal.accountservice.exception.AccountExceptionCode;
import com.baedal.accountservice.repository.AccountRepository;
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

    public String findAccountName(Long accountId) {
        Account account = getUserEntity(accountId);
        return account.getName();
    }

}
