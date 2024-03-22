package com.chatmrkhoi.chatmrkhoi.service;

import org.springframework.http.ResponseEntity;

import com.chatmrkhoi.chatmrkhoi.Data.reponse.DataInfoRoomRep;

public interface IFriend {
	void save(Long idFriend);
	void refuse(Long idFriend);
	ResponseEntity<DataInfoRoomRep> accept(long IdFriend);
	void delete(Long idFriend);
	
}
