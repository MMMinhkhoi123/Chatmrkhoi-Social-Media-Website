package com.chatmrkhoi.chatmrkhoi.reponse;


import java.util.List;
import java.util.Set;

import com.chatmrkhoi.chatmrkhoi.request.file_reuqest;
import com.chatmrkhoi.chatmrkhoi.request.unmess_request;
import com.chatmrkhoi.chatmrkhoi.request.updatefeel_request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class mess_reponse {
	private Long id;
	private String content;
	private Long id_friend;
	private Long id_group;
	private String feel;
	private List<Long> watch;
	private Long id_user;
	private Long time;
	private boolean start;
	private String room;
	private Long reply;
	private String group_status;
	private List<unmess_request> listrevoke;
	private String typereply;
	private List<watch_reponse> listwatch;
	private List<updatefeel_request> listfeel;
	private List<file_reuqest> img;
	private String pin;
}
