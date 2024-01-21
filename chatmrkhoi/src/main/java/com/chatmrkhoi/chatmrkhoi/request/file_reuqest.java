package com.chatmrkhoi.chatmrkhoi.request;

import lombok.Builder;
import lombok.Data;
@Builder
@Data
public class file_reuqest {
	 private String namefile;
	 private String type;
	 private Long id;
	 private Long size;
	 private Long idfile;
	 private boolean status;
}
