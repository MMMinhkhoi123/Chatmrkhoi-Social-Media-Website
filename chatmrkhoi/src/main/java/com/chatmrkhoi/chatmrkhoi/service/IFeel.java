package com.chatmrkhoi.chatmrkhoi.service;

import com.chatmrkhoi.chatmrkhoi.Data.reponse.DataMessOldAndNewRep;
import org.springframework.http.ResponseEntity;


import com.chatmrkhoi.chatmrkhoi.Data.reponse.DataMessageRep;
import com.chatmrkhoi.chatmrkhoi.Data.request.DataFeelReq;

public interface IFeel {
	
	ResponseEntity<DataMessOldAndNewRep> save(DataFeelReq data);
	
	ResponseEntity<DataMessageRep> delete(Long idMess, String type);
}
