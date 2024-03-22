package com.chatmrkhoi.chatmrkhoi.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.chatmrkhoi.chatmrkhoi.Data.reponse.DataInfoMessPinRep;
import com.chatmrkhoi.chatmrkhoi.Data.reponse.DataMessNewAndListInfoPinAndInfoPinNewRep;
import com.chatmrkhoi.chatmrkhoi.Data.request.DataSavePinReq;

public interface IPin {
	ResponseEntity<DataMessNewAndListInfoPinAndInfoPinNewRep> save(DataSavePinReq data);
	ResponseEntity<List<DataInfoMessPinRep>> getAll();
	ResponseEntity<DataMessNewAndListInfoPinAndInfoPinNewRep> delete(Long pin);
}
