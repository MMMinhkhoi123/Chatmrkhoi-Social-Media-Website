package com.chatmrkhoi.chatmrkhoi.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.chatmrkhoi.chatmrkhoi.Util.convert.MessageConverts;
import com.chatmrkhoi.chatmrkhoi.common.Common;
import com.chatmrkhoi.chatmrkhoi.design.observer.CenterOb;
import com.chatmrkhoi.chatmrkhoi.design.observer.NotifyEmail;
import com.chatmrkhoi.chatmrkhoi.design.strategy.message.move.ContextMoveStrategy;
import com.chatmrkhoi.chatmrkhoi.design.strategy.message.move.MoveFileStrategy;
import com.chatmrkhoi.chatmrkhoi.design.strategy.message.move.MoveTextStrategy;
import com.chatmrkhoi.chatmrkhoi.design.strategy.message.notify.ContextNotify;
import com.chatmrkhoi.chatmrkhoi.design.strategy.message.notify.NotifyGroup;
import com.chatmrkhoi.chatmrkhoi.design.strategy.message.notify.NotifyPrivate;
import com.chatmrkhoi.chatmrkhoi.design.strategy.message.save.ContextSaveStrategy;
import com.chatmrkhoi.chatmrkhoi.design.strategy.message.save.SaveGroupStrategy;
import com.chatmrkhoi.chatmrkhoi.design.strategy.message.save.SavePrivateStrategy;
import com.chatmrkhoi.chatmrkhoi.entity.*;
import com.chatmrkhoi.chatmrkhoi.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.chatmrkhoi.chatmrkhoi.Data.reponse.DataMessageRep;
import com.chatmrkhoi.chatmrkhoi.Data.request.DataSaveMessReq;
import com.chatmrkhoi.chatmrkhoi.Data.request.DatatransactionMessReq;
import com.chatmrkhoi.chatmrkhoi.service.IMess;

@Service
public class MessageSer implements IMess {

	@Autowired IMessageRepo MESSAGE_REPO;
	@Autowired IFriendRepo FRIEND_REPO;
	@Autowired IUserRepo USER_REPO;
	@Autowired FileSer FILE_SER;
	@Autowired IFileRepo FILE_REPO;
	@Autowired IGroupRepo GROUP_REPO;
	@Autowired Common COMMON;
	@Autowired MessageConverts CONVERT;
	@Autowired IInfoRepo INFO;
	@Autowired ContextSaveStrategy contextSaveStrategy;
	@Autowired SaveGroupStrategy saveGroupStrategy;
	@Autowired SavePrivateStrategy savePrivateStrategy;
	@Autowired IActionRepo ACTION_REPO;
	@Autowired NotifyEmail notifyEmail;


@Override
public ResponseEntity<List<DataMessageRep>> save(DataSaveMessReq datas) {
	 List<DataMessageRep> messRep = new ArrayList<DataMessageRep>();
	if (datas.getStatus().equalsIgnoreCase("group") ) {
		contextSaveStrategy.setStrategy(saveGroupStrategy);
		messRep = contextSaveStrategy.executeStrategy(datas);
	} else  {
		contextSaveStrategy.setStrategy(savePrivateStrategy);
		messRep = contextSaveStrategy.executeStrategy(datas);
	}
	return ResponseEntity.status(200).body(messRep);
}


	@Override
	public ResponseEntity<List<DataMessageRep>> getAll() {
		List<DataMessageRep> dataRep = new ArrayList<DataMessageRep>();
		MESSAGE_REPO.findAll().forEach((e) -> {
			dataRep.add(CONVERT.convertMessRep(e));
		});
		return ResponseEntity.status(200).body(dataRep);
	}


	@Autowired NotifyGroup notifyGroup;
    @Autowired NotifyPrivate notifyPrivate;

	@Override
	public void notifyNew(DataSaveMessReq data) {
		ContextNotify context = null;
		if (data.getStatus().equals("group"))
		{
			notifyGroup.setData(data);
			context = new ContextNotify(notifyGroup);
		}
		else
		{
			notifyPrivate.setData(data);
			context = new ContextNotify(notifyPrivate);
		}
		context.active();
	}


	@Autowired 	ContextMoveStrategy ContextMoveStrategy;
	@Autowired 	MoveTextStrategy StrategyText;
	@Autowired 	MoveFileStrategy StrategyFile;
	@Override
	public ResponseEntity<DataMessageRep> transaction(DatatransactionMessReq data) {
		MessageEn messxx = new MessageEn();
		if(data.getType().equalsIgnoreCase("text")) {
			ContextMoveStrategy.setMoveStrategy(StrategyText);
			messxx = ContextMoveStrategy.executeStrategy(data);
		} else {
			ContextMoveStrategy.setMoveStrategy(StrategyFile);
			messxx = ContextMoveStrategy.executeStrategy(data);
		}
		return ResponseEntity.ok(CONVERT.convertMessRep(messxx));
	}



	public List<DataMessageRep> getListMessFromCode(String code) {
		List<DataMessageRep> messRep = new ArrayList<DataMessageRep>();
		FRIEND_REPO.findByCodeRoom(code).ifPresentOrElse((e) -> {
			MESSAGE_REPO.findAllByFriendId(e.getId()).orElseThrow().forEach((item) -> {
				messRep.add(CONVERT.convertMessRep(item));
			});
		}, () -> {
			MESSAGE_REPO.findAllByGroupId(GROUP_REPO.findByCodeRoom(code).orElseThrow().getId()).orElseThrow().forEach((item) -> {
				messRep.add(CONVERT.convertMessRep(item));
			});
		});
		return messRep;
	}


	


}
