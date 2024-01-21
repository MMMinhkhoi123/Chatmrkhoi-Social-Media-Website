package com.chatmrkhoi.chatmrkhoi.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.chatmrkhoi.chatmrkhoi.reponsitory.User_repo;

@Service
public class config_userditail implements UserDetailsService {

	
	@Autowired
	User_repo user_repo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		user_repo.findbygmail(username).orElseThrow(() ->
		new UsernameNotFoundException("not find username !"));
		return new custom_userdetail(user_repo.findbygmail(username).get());
	}

}
