package com.baedal.monolithic.domain.account.application;

import com.baedal.monolithic.domain.account.dto.AccountDto;
import com.baedal.monolithic.domain.account.entity.Account;
import com.baedal.monolithic.domain.account.exception.AccountException;
import com.baedal.monolithic.domain.account.exception.AccountExceptionCode;
import com.baedal.monolithic.domain.account.repository.AccountRepository;
import com.baedal.monolithic.global.util.S3Util;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.io.IOException;


@Service
@RequiredArgsConstructor
@Slf4j
public class AccountService {

    private static final String PROFILE_UPLOAD_DIR = "image/profile";
    private static final String DEFAULT_PROFILE_NAME = "defaultProfile.png";

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final S3Util s3Util;

    private String DEFAULT_PROFILE_URL;

    @PostConstruct
    private void init() {
        DEFAULT_PROFILE_URL = s3Util.getS3Url(PROFILE_UPLOAD_DIR, DEFAULT_PROFILE_NAME);
    }


    @Transactional(readOnly = true)
    public AccountDto.GetRes findAccount(final Long accountId) {

        AccountDto.GetRes accountGetRes = accountMapper.mapAccountEntityToGetDto(getUserEntity(accountId));
        accountGetRes.setDefaultProfileIfEmpty(DEFAULT_PROFILE_URL);

        return accountGetRes;
    }

    @Transactional
    public void deleteAccount(final Long accountId) {

        Account account = getUserEntity(accountId);
        accountRepository.delete(account);
    }

    @Transactional
    public void updateAccount(final Long accountId, final AccountDto.PutReq accountPutReq) {

        Account account = getUserEntity(accountId);
        String profileUrl = account.getProfile();

        if (accountPutReq.isProfileUpdated()){
            s3Util.remove(account.getProfile(), PROFILE_UPLOAD_DIR);

            if (accountPutReq.getProfile().isEmpty()){
                profileUrl = "";
            } else if (!accountPutReq.getProfile().isEmpty()){
                profileUrl = uploadNewProfileFile(account, accountPutReq);
            }
        }

        getUserEntity(accountId).updateInfo(
                accountPutReq.getNickname(),
                accountPutReq.getTel(),
                profileUrl
        );
    }

    private String uploadNewProfileFile(final Account account, final AccountDto.PutReq accountPutReq) {

        try {
            s3Util.remove(account.getProfile(), PROFILE_UPLOAD_DIR);
            return s3Util.upload(accountPutReq.getProfile(), PROFILE_UPLOAD_DIR);
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new AccountException(AccountExceptionCode.NOT_IMAGE_UPLOAD);
        }
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
