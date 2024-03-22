package com.chatmrkhoi.chatmrkhoi.Data.reponse;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DataListImgAndVideoAndFileRep {
	private List<DataInfoFileRep> list_img;
	private List<DataInfoFileRep> list_file;
	private List<DataInfoFileRep> list_video;
}
