package com.baedal.monolithic.domain.account.entity;

import com.baedal.monolithic.domain.account.dto.AccountDto;
import com.baedal.monolithic.global.entity.BaseTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Account extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userAddressId;
    private String socialId;
    private String provider;
    private String name;
    private String nickname;
    private String email;
    private String tel;
    private String profile;
    private String refreshToken;
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    public String getRoleKey(){
        return role.getKey();
    }

    public void updateRefreshToken(String refreshToken){
        this.refreshToken = refreshToken;
    }

    public void updateInfo(AccountDto.PutReq accountPutReq){
        this.nickname = accountPutReq.getNickname();
        this.tel = accountPutReq.getTel();
        this.profile = accountPutReq.getProfile();
    }

}
