package com.chatmrkhoi.chatmrkhoi.service;

import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.chatmrkhoi.chatmrkhoi.entity.file_entity;
import com.chatmrkhoi.chatmrkhoi.reponse.datadetail_response;
import com.chatmrkhoi.chatmrkhoi.reponse.filedetail_reponse;
import com.chatmrkhoi.chatmrkhoi.request.file_reuqest;
public interface File_inter {
	public file_entity savefile(String namefile, String typefile, Long size);
	public ResponseEntity<datadetail_response> data_detail(String room);
	public ResponseEntity<List<filedetail_reponse>> data_detail_zoom(String room);
	public ResponseEntity<file_reuqest> upload(MultipartFile file, String type) throws IOException;
	public void deletefile(String namefile, String type);
}
