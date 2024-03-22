package com.chatmrkhoi.chatmrkhoi.service;

import org.springframework.http.ResponseEntity;

import com.chatmrkhoi.chatmrkhoi.Data.reponse.DataMessageRep;
import com.chatmrkhoi.chatmrkhoi.Data.request.DataSaveRevokeReq;

public interface IRevoke {
	
	ResponseEntity<DataMessageRep> save(DataSaveRevokeReq data);
}
