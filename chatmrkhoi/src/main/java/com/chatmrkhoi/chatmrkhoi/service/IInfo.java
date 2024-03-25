package com.chatmrkhoi.chatmrkhoi.service;

import com.chatmrkhoi.chatmrkhoi.entity.InfoEn;
import com.chatmrkhoi.chatmrkhoi.Data.request.DataInfoReq;

public interface IInfo {
	void save(String name, Long idUser);
	void update(InfoEn data);
	void update(DataInfoReq upRequest);
	void updateNotify(boolean status);
	void updateTheme(boolean status);
}
