package com.baedal.monolithic.domain.auth.application;

import com.baedal.monolithic.domain.account.dto.AccountDto;
import com.baedal.monolithic.domain.account.entity.Account;
import com.baedal.monolithic.domain.auth.dto.AuthDto;
import org.springframework.stereotype.Component;

@Component
class AuthMapper {

    AuthDto.GetRes mapEntityToAuthDto(final Account account){
        return AuthDto.GetRes.builder()
                .id(account.getId())
                .role(account.getRole())
                .refreshToken(account.getRefreshToken())
                .build();
    }

}
