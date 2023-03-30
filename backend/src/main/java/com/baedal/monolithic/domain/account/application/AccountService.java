package com.baedal.monolithic.domain.account.application;

import com.baedal.monolithic.domain.account.dto.AccountDto;
import com.baedal.monolithic.domain.account.entity.Account;
import com.baedal.monolithic.domain.account.exception.AccountException;
import com.baedal.monolithic.domain.account.exception.AccountExceptionCode;
import com.baedal.monolithic.domain.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    @Transactional(readOnly = true)
    public AccountDto.GetRes findAccount(final Long accountId) {
        return accountMapper.mapAccountEntityToGetDto(getUserEntity(accountId));
    }

    @Transactional
    public void deleteAccount(final Long accountId) {
        Account account = getUserEntity(accountId);
        accountRepository.delete(account);
    }

    @Transactional
    public void updateAccount(final Long accountId, final AccountDto.PutReq accountPutReq) {
        getUserEntity(accountId).updateInfo(accountPutReq);
    }

    @Transactional(readOnly = true)
    public String getUserNickname(final Long userId){
        return getUserEntity(userId).getNickname();
    }

    @Transactional(readOnly = true)
    public Long getAddressIdOfUser(final Long userId){
        return getUserEntity(userId).getUserAddressId();
    }

    protected Account getUserEntity(final Long userId){
        return accountRepository.findById(userId)
                .orElseThrow(() -> new AccountException(AccountExceptionCode.NO_USER));
    }

}
