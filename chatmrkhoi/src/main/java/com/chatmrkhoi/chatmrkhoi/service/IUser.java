package com.chatmrkhoi.chatmrkhoi.service;

import java.util.List;

import com.chatmrkhoi.chatmrkhoi.Data.reponse.*;
import com.chatmrkhoi.chatmrkhoi.common.EUserInitial;
import org.springframework.http.ResponseEntity;

import com.chatmrkhoi.chatmrkhoi.Data.request.DataLoginReq;
import com.chatmrkhoi.chatmrkhoi.Data.request.DataRegisterReq;
import com.chatmrkhoi.chatmrkhoi.Data.request.DataInfoUpdateReq;


public interface IUser {

	void save(DataRegisterReq DataRegister);

	ResponseEntity<DataLoginRep> login(DataLoginReq loginData);

	ResponseEntity<String> getStatusVerify(String email);

	ResponseEntity<DataInfoAuthenRep> getInfo(String token);

	boolean checkValidToken(String token);
	
	ResponseEntity<List<DataInfoUserOtherRep>> findAllByType(String type, String data);

	ResponseEntity<List<DataInfoUserOtherRep>> getAllDataInitialByType(String type);

	ResponseEntity<List<DataInfoRoomRep>> getListRoomChat();

	ResponseEntity<DataInfoAuthenRep> updateInfo(DataInfoUpdateReq infoNewReq);

	ResponseEntity<List<DataInfoUserOtherRep>> getusergroup(Long idgroup);


	ResponseEntity<List<DataInfoUserOtherRep>> getListSugFiend();
}
