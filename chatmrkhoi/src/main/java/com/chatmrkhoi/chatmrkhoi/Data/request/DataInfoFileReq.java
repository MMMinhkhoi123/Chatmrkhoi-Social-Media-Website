package com.chatmrkhoi.chatmrkhoi.Data.request;

import lombok.Builder;
import lombok.Data;
@Builder
@Data
public class DataInfoFileReq {
	 private String namefile;
	 private String type;
	 private Long id;
	 private Long size;
	 private Long idfile;
	 private boolean status;
}
