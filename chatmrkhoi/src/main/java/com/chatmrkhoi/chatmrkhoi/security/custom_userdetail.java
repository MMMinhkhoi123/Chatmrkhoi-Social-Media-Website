package com.chatmrkhoi.chatmrkhoi.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.chatmrkhoi.chatmrkhoi.entity.Users_entity;

public class custom_userdetail implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	Users_entity users_entity;

    public custom_userdetail(Users_entity user) {
    	this.users_entity = user;
    }
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return users_entity.getPasswords();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return users_entity.getGmails();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
