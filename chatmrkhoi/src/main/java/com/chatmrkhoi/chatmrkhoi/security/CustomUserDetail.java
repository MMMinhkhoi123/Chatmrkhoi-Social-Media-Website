package com.chatmrkhoi.chatmrkhoi.security;

import java.io.Serial;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.chatmrkhoi.chatmrkhoi.entity.UserEn;

public class CustomUserDetail implements UserDetails {

	@Serial
	private static final long serialVersionUID = 1L;
	UserEn users_entity;

    public CustomUserDetail(UserEn user) {
    	this.users_entity = user;
    }
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getPassword() {
		return users_entity.getPasswords();
	}

	@Override
	public String getUsername() {
		return users_entity.getGmails();
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

}
