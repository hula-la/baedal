package com.baedal.accountservice.entity;

import com.baedal.accountservice.dto.AccountDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Account {

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

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

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
