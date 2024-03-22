package com.chatmrkhoi.chatmrkhoi.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.chatmrkhoi.chatmrkhoi.repositories.IUserRepo;

@Service
public class ConfigUserDetail implements UserDetailsService {
	@Autowired IUserRepo USER_REPO;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		USER_REPO.findByEmail(username).orElseThrow(() ->
		new UsernameNotFoundException("Not find username !"));
		return new CustomUserDetail(USER_REPO.findByEmail(username).orElseThrow());
	}

}
