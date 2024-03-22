package com.chatmrkhoi.chatmrkhoi.service.impl;

import com.chatmrkhoi.chatmrkhoi.design.Signleton.Signleton;
import com.chatmrkhoi.chatmrkhoi.repositories.IUserRepo;
import com.chatmrkhoi.chatmrkhoi.Data.request.DataLoginReq;
import com.chatmrkhoi.chatmrkhoi.security.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chatmrkhoi.chatmrkhoi.entity.JwtEn;
import com.chatmrkhoi.chatmrkhoi.repositories.IJwtRepo;
import com.chatmrkhoi.chatmrkhoi.service.IJwt;

import java.util.Optional;

@Service
public class JwtSer implements IJwt {

	@Autowired IJwtRepo JWT_REPO;
	@Autowired IUserRepo USER_REPO;
	@Autowired JwtToken CenterToken;

	@Override
	public void save(DataLoginReq loginData) {
		String TokenDatabase = CenterToken.getToken(loginData.getGmail());
		JwtEn data = JwtEn.builder()
				.id_user(USER_REPO.findByEmail(loginData.getGmail()).orElseThrow().getId())
				.jwts(TokenDatabase)
				.time(System.currentTimeMillis() + Signleton.getInstance().getTimeTokenVerify())
				.build();
		Optional<JwtEn> DataFindEd = JWT_REPO.findByUserId(USER_REPO.findByEmail(loginData.getGmail()).orElseThrow().getId());
		DataFindEd.ifPresent((e) -> {
			data.setId(e.getId());
		});
		JWT_REPO.save(data);
	}
}
