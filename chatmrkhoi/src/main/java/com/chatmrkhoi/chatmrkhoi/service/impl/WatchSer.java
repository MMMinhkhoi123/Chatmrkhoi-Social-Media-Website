package com.chatmrkhoi.chatmrkhoi.service.impl;

import com.chatmrkhoi.chatmrkhoi.Util.convert.MessageConverts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.chatmrkhoi.chatmrkhoi.entity.WatchEn;
import com.chatmrkhoi.chatmrkhoi.Data.reponse.DataMessageRep;
import com.chatmrkhoi.chatmrkhoi.repositories.IUserRepo;
import com.chatmrkhoi.chatmrkhoi.repositories.IWatchRepo;
import com.chatmrkhoi.chatmrkhoi.repositories.IMessageRepo;
import com.chatmrkhoi.chatmrkhoi.Data.request.DataSaveWatchReq;
import com.chatmrkhoi.chatmrkhoi.service.IWatch;

@Service
public class WatchSer implements IWatch {

	private final IWatchRepo WATCH_REPO;
	private final IMessageRepo MESSAGE_REPO;
	private final IUserRepo USER_REPO;
    private final MessageConverts CONVERT;

	@Autowired
	public  WatchSer(IWatchRepo WATCH_REPO, IMessageRepo MESSAGE_REPO, IUserRepo USER_REPO , MessageConverts CONVERT ) {
		this.WATCH_REPO = WATCH_REPO;
		this.MESSAGE_REPO = MESSAGE_REPO;
		this.USER_REPO = USER_REPO;
        this.CONVERT = CONVERT;
	}

	@Override
	public ResponseEntity<DataMessageRep> save(DataSaveWatchReq data) {

		WatchEn watch = WatchEn.builder()
				.messentity(MESSAGE_REPO.findById(data.getIdmess()).orElseThrow())
				.timetamp(System.currentTimeMillis())
				.usersentity(USER_REPO.findById(data.getIdperson()).orElseThrow()).build();
		WATCH_REPO.save(watch);

		DataMessageRep mess = CONVERT.convertMessRep(MESSAGE_REPO.findById(data.getIdmess()).orElseThrow());

		return ResponseEntity.ok(mess);
	}

}
