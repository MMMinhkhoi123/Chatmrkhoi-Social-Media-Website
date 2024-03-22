package com.chatmrkhoi.chatmrkhoi.service.impl;

import com.chatmrkhoi.chatmrkhoi.Data.reponse.DataMessOldAndNewRep;
import com.chatmrkhoi.chatmrkhoi.Util.convert.MessageConverts;
import com.chatmrkhoi.chatmrkhoi.common.Common;
import com.chatmrkhoi.chatmrkhoi.design.factorymethod.ACenterActivityMess;
import com.chatmrkhoi.chatmrkhoi.design.factorymethod.ConcreteFeelMessenger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.chatmrkhoi.chatmrkhoi.entity.MessageEn;
import com.chatmrkhoi.chatmrkhoi.entity.FeelingEn;
import com.chatmrkhoi.chatmrkhoi.Data.reponse.DataMessageRep;
import com.chatmrkhoi.chatmrkhoi.repositories.IUserRepo;
import com.chatmrkhoi.chatmrkhoi.repositories.IFeelRepo;
import com.chatmrkhoi.chatmrkhoi.repositories.IMessageRepo;
import com.chatmrkhoi.chatmrkhoi.Data.request.DataFeelReq;
import com.chatmrkhoi.chatmrkhoi.service.IFeel;

@Service
public class FeelSer implements IFeel {

	@Autowired IMessageRepo MESSAGE_REPO;
	@Autowired IFeelRepo FEELING_REPO;
	@Autowired IUserRepo USER_REPO;
	@Autowired MessageSer MESSAGE_SER;
	@Autowired Common COMMON;
	@Autowired MessageConverts CONVERT;
	@Autowired ConcreteFeelMessenger feelMessenger;


	@Override
	public ResponseEntity<DataMessOldAndNewRep> save(DataFeelReq data) {
		feelMessenger.setData(data);
		ACenterActivityMess dataUse = feelMessenger;
		FEELING_REPO.save((FeelingEn) dataUse.setupObject());
		MessageEn messEntity = MESSAGE_REPO.save(dataUse.getMessageNew());

		return ResponseEntity.ok(DataMessOldAndNewRep
				.builder()
					.messnew(CONVERT.convertMessRep(messEntity))
				    .messupdate(CONVERT.convertMessRep(MESSAGE_REPO.findById(data.getIdmess()).orElseThrow()))
				.build());
	}



	@Override
	public ResponseEntity<DataMessageRep> delete(Long idMess, String type) {
		Long id = COMMON.getUserAuthentication().getId();
		FEELING_REPO.deleteByIdMessAndTypeAndIdMaster(idMess,id, type);
		MessageEn mess = MESSAGE_REPO.findById(idMess).orElseThrow();
		return ResponseEntity.ok(CONVERT.convertMessRep(mess));
	}
}
