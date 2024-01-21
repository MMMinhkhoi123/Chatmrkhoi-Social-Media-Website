package com.chatmrkhoi.chatmrkhoi.reponse;

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
public class datadetail_response {
	private List<filedetail_reponse> list_img;
	private List<filedetail_reponse> list_file;
	private List<filedetail_reponse> list_video;
}
