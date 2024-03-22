package com.chatmrkhoi.chatmrkhoi.Data.request;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DataInfoGroupUpdateReq {
	private MultipartFile file;
	private Long id;
	private String name;
}
