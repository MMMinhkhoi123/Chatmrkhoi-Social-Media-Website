package com.chatmrkhoi.chatmrkhoi.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.chatmrkhoi.chatmrkhoi.Data.reponse.DataInfoActionUserRep;
import com.chatmrkhoi.chatmrkhoi.repositories.IActionRepo;
import com.chatmrkhoi.chatmrkhoi.service.IAction;

@Service
public class ActionSer implements IAction {

	@Autowired IActionRepo ACTION_REPO;


	// lấy thông tin trực tuyến của người dùng vd ( online, offline)
	@Override
	public ResponseEntity<List<DataInfoActionUserRep>> getAll() {

		List<DataInfoActionUserRep> list = new ArrayList<DataInfoActionUserRep>();
		ACTION_REPO.findAll().forEach((e) -> {
			DataInfoActionUserRep x = new DataInfoActionUserRep();
			x.setId(e.getId_user());
			x.setStatus(e.getStatus());
			x.setTimetamp(e.getTimetamp());
			list.add(x);
		});
		return ResponseEntity.ok(list);
	}

}
