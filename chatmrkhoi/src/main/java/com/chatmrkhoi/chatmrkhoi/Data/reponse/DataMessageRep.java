package com.chatmrkhoi.chatmrkhoi.Data.reponse;


import java.util.List;

import com.chatmrkhoi.chatmrkhoi.Data.request.DataInfoFileReq;
import com.chatmrkhoi.chatmrkhoi.Data.request.DataSaveRevokeReq;
import com.chatmrkhoi.chatmrkhoi.Data.request.DataFeelReq;

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
public class DataMessageRep {
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
	private List<DataSaveRevokeReq> listrevoke;
	private String typereply;
	private List<DataInfoWatchRep> listwatch;
	private List<DataFeelReq> listfeel;
	private List<DataInfoFileReq> img;
	private String pin;
}
