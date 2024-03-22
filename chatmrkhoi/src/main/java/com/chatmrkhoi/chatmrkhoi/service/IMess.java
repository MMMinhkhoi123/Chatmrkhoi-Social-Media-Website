package com.chatmrkhoi.chatmrkhoi.service;

import java.util.List;

import com.chatmrkhoi.chatmrkhoi.entity.MessageEn;
import org.springframework.http.ResponseEntity;

import com.chatmrkhoi.chatmrkhoi.Data.reponse.DataMessageRep;
import com.chatmrkhoi.chatmrkhoi.Data.request.DataSaveMessReq;
import com.chatmrkhoi.chatmrkhoi.Data.request.DatatransactionMessReq;

public interface IMess {

	ResponseEntity< List<DataMessageRep>> save(DataSaveMessReq sendmess_request);
	
	ResponseEntity<DataMessageRep> transaction(DatatransactionMessReq data);
	
	ResponseEntity<List<DataMessageRep>> getAll();

   public void notifyNew(DataSaveMessReq data);

	

	
}
