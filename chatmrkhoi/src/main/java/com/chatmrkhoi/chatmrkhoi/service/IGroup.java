package com.chatmrkhoi.chatmrkhoi.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.chatmrkhoi.chatmrkhoi.Data.reponse.DataInfoGroupNewAndRoomConnectRep;
import com.chatmrkhoi.chatmrkhoi.Data.reponse.DataMessNewAndInfoNumberRep;
import com.chatmrkhoi.chatmrkhoi.Data.reponse.DataInfoGroupRep;
import com.chatmrkhoi.chatmrkhoi.Data.reponse.DataMessageRep;
import com.chatmrkhoi.chatmrkhoi.Data.request.DataAddGroupReq;
import com.chatmrkhoi.chatmrkhoi.Data.request.DataKickOrAddAnNumberReq;
import com.chatmrkhoi.chatmrkhoi.Data.request.DataUpdateNameGropReq;
import com.chatmrkhoi.chatmrkhoi.Data.request.DataInfoGroupUpdateReq;

public interface IGroup {
	
	ResponseEntity<DataInfoGroupNewAndRoomConnectRep> save(DataAddGroupReq data);

	void delete(Long id);

	ResponseEntity<DataMessageRep> out(Long id, String codeRoom);

	ResponseEntity<DataInfoGroupRep> updateNameGroup(DataUpdateNameGropReq data);
	
	ResponseEntity<DataInfoGroupRep> updateImgGroup(DataInfoGroupUpdateReq data);
	
	ResponseEntity<List<DataInfoGroupRep>> getInfo();
	
	ResponseEntity<DataMessNewAndInfoNumberRep> addNumber(DataKickOrAddAnNumberReq data);
	
	ResponseEntity<DataMessNewAndInfoNumberRep> kickNumber(DataKickOrAddAnNumberReq data);
}
