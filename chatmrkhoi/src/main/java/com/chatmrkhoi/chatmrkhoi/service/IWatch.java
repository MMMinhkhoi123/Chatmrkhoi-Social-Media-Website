package com.chatmrkhoi.chatmrkhoi.service;

import org.springframework.http.ResponseEntity;

import com.chatmrkhoi.chatmrkhoi.Data.reponse.DataMessageRep;
import com.chatmrkhoi.chatmrkhoi.Data.request.DataSaveWatchReq;

public interface IWatch {
	ResponseEntity<DataMessageRep> save(DataSaveWatchReq data);
}
