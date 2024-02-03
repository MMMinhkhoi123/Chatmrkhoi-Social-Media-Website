package com.chatmrkhoi.chatmrkhoi.security.OAuth2;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;

public class CustomerOAuthen2User implements OAuth2User {
    private  OAuth2User oAuth2User;

    public CustomerOAuthen2User(OAuth2User oAuth2User) {
        this.oAuth2User = oAuth2User;
    }
    @Override
    public Map<String, Object> getAttributes() {
        return oAuth2User.getAttributes();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return oAuth2User.getAuthorities();
    }

    @Override
    public String getName() {
        return oAuth2User.getName();
    }
}
