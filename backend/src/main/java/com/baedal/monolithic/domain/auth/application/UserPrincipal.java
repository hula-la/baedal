package com.baedal.monolithic.domain.auth.application;

import com.baedal.monolithic.domain.account.entity.Account;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

public class UserPrincipal implements OAuth2User, UserDetails {

    private final Account account;
    private final Set<GrantedAuthority> authorities;
    private final Map<String, Object> attributes;

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
