package com.baedal.monolithic.domain.auth.application;

import com.baedal.monolithic.domain.account.entity.Account;
import com.baedal.monolithic.domain.account.entity.Role;
import com.baedal.monolithic.domain.account.repository.AccountRepository;
import com.baedal.monolithic.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final AccountRepository accountRepository;
    private final HttpSession httpSession;
    private final ReviewRepository reviewRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();

        String socialId = getSocialId(registrationId,userNameAttributeName,oAuth2User);

        Account user = saveIfNewUser(socialId);

        return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey())),
                oAuth2User.getAttributes(),
                userNameAttributeName);
    }

    private Account saveIfNewUser(String socialId) {
        Account user = accountRepository.findBySocialId(socialId)
                .orElse(Account.builder()
                        .socialId(socialId)
                        .role(Role.USER)
                        .build());

        return accountRepository.save(user);
    }

    private String getSocialId(String registrationId, String userNameAttributeName, OAuth2User oAuth2User) {
        Long userIdFromProvider = (Long) oAuth2User.getAttributes().get(userNameAttributeName);
        return registrationId + "_" + userIdFromProvider;
    }


}
