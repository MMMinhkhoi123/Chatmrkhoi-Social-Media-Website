package com.chatmrkhoi.chatmrkhoi.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.chatmrkhoi.chatmrkhoi.Data.reponse.DataInfoActionUserRep;

public interface IAction {
	ResponseEntity< List<DataInfoActionUserRep>> getAll();
}
