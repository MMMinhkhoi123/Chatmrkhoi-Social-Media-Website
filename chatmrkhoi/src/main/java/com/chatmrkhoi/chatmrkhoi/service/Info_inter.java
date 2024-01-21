package com.chatmrkhoi.chatmrkhoi.service;

import com.chatmrkhoi.chatmrkhoi.entity.cakeinfo_entity;
import com.chatmrkhoi.chatmrkhoi.request.upinfo_full_request;

public interface Info_inter {
	public void create_info_user(String fullname, Long iduser);
	public void update_info_user(cakeinfo_entity data);
	public void update_info_user_full(upinfo_full_request uprequest);
}
