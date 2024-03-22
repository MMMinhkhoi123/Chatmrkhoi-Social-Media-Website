package com.chatmrkhoi.chatmrkhoi.service.impl;

import com.chatmrkhoi.chatmrkhoi.Util.convert.MessageConverts;
import com.chatmrkhoi.chatmrkhoi.common.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.chatmrkhoi.chatmrkhoi.entity.RevokeEn;
import com.chatmrkhoi.chatmrkhoi.Data.reponse.DataMessageRep;
import com.chatmrkhoi.chatmrkhoi.repositories.IUserRepo;
import com.chatmrkhoi.chatmrkhoi.repositories.IMessageRepo;
import com.chatmrkhoi.chatmrkhoi.repositories.IRevokeRepo;
import com.chatmrkhoi.chatmrkhoi.Data.request.DataSaveRevokeReq;
import com.chatmrkhoi.chatmrkhoi.service.IRevoke;


@Service
public class RevokeSer implements IRevoke  {

	@Autowired IRevokeRepo REVOKE_REPO;
	@Autowired MessageSer MESSAGE_SER;
	@Autowired IMessageRepo MESS_REPO;
	@Autowired IUserRepo USER_REPO;
	@Autowired Common COMMON;
	@Autowired MessageConverts CONVERT;

	@Override
	public ResponseEntity<DataMessageRep> save(DataSaveRevokeReq dataReq) {
		RevokeEn data = RevokeEn.builder()
				.messentity(MESS_REPO.findById(dataReq.getIdmess()).orElseThrow())
				.time(System.currentTimeMillis())
				.userentity( COMMON.getUserAuthentication())
				.type(dataReq.getType())
				.build();
		REVOKE_REPO.save(data);
		return ResponseEntity.ok(CONVERT.convertMessRep(MESS_REPO.findById(dataReq.getIdmess()).orElseThrow()));
	}


}
