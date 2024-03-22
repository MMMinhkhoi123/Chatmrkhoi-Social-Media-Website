package com.chatmrkhoi.chatmrkhoi.service;

import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.chatmrkhoi.chatmrkhoi.entity.FileEn;
import com.chatmrkhoi.chatmrkhoi.Data.reponse.DataListImgAndVideoAndFileRep;
import com.chatmrkhoi.chatmrkhoi.Data.reponse.DataInfoFileRep;
import com.chatmrkhoi.chatmrkhoi.Data.request.DataInfoFileReq;
public interface IFile {
	FileEn save(String name, String type, Long size);
	ResponseEntity<DataListImgAndVideoAndFileRep> getAllDataFromRoom(String room);
	ResponseEntity<List<DataInfoFileRep>> getImgAndVideoFormRoom(String room);
	ResponseEntity<DataInfoFileReq> upload(MultipartFile file, String type) throws IOException;
	void delete(String name, String type);
}
