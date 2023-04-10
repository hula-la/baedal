package com.baedal.monolithic.domain.auth.application;

import com.baedal.monolithic.domain.account.entity.Account;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import reactor.util.annotation.Nullable;

import java.util.*;

@Getter
public class UserPrincipal implements OAuth2User, UserDetails {

    private Account account;
    private Set<GrantedAuthority> authorities;
    private Map<String, Object> attributes;

    public UserPrincipal(Account account, Set<GrantedAuthority> authorities,
                          Map<String, Object> attributes) {
        this.account = account;
        this.authorities = authorities;
        this.attributes = attributes;
    }

    @Override
    public String getPassword() {
        return account.getPassword();
    }

    @Override
    public String getUsername() {
        return String.valueOf(account.getId());
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return Collections.unmodifiableMap(attributes);
    }

    @Override
    @Nullable
    @SuppressWarnings("unchecked")
    public <A> A getAttribute(String name) {
        return (A) attributes.get(name);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getName() {
        return String.valueOf(account.getEmail());
    }
}
