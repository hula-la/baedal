package com.baedal.monolithic.domain.account.entity;

import com.baedal.monolithic.global.entity.BaseTime;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    public void updateRefreshToken(final String refreshToken){
        this.refreshToken = refreshToken;
    }

    public void updateInfo(final String nickname, final String tel, final String profileUrl){
        this.nickname = nickname;
        this.tel = tel;
        this.profile = profileUrl;
    }

}
