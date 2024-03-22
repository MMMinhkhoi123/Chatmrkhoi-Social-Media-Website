package com.chatmrkhoi.chatmrkhoi.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import com.chatmrkhoi.chatmrkhoi.common.Common;
import com.chatmrkhoi.chatmrkhoi.design.Signleton.Signleton;
import com.chatmrkhoi.chatmrkhoi.entity.MessageEn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.chatmrkhoi.chatmrkhoi.entity.FileEn;
import com.chatmrkhoi.chatmrkhoi.Data.reponse.DataListImgAndVideoAndFileRep;
import com.chatmrkhoi.chatmrkhoi.Data.reponse.DataInfoFileRep;
import com.chatmrkhoi.chatmrkhoi.repositories.IUserRepo;
import com.chatmrkhoi.chatmrkhoi.repositories.IFileRepo;
import com.chatmrkhoi.chatmrkhoi.repositories.IMessageRepo;
import com.chatmrkhoi.chatmrkhoi.Data.request.DataInfoFileReq;
import com.chatmrkhoi.chatmrkhoi.service.IFile;

@Service
public class FileSer implements IFile {

	@Autowired IFileRepo FILE_REPO;
	@Autowired IUserRepo USER_REPO;
	@Autowired IMessageRepo MESSAGE_REPO;
	@Autowired Common COMMON;
	
	@Override
	public FileEn save(String name, String type, Long size) {
		FileEn data = FileEn.builder().namefile(name).typefile(type).size(size).status(false).build();
	  return FILE_REPO.save(data);
	}

	@Override
	public ResponseEntity<DataInfoFileReq> upload(MultipartFile file, String type)
		 {
			  Signleton signLeton  = Signleton.getInstance();
			  String name  = "";
			  Long id = null;
			  Long iduser = COMMON.getUserAuthentication().getId();
			  Path paths  = null;
			  if(type.equalsIgnoreCase("file")) {
				  String  namex =  iduser.toString() + "&3&" + file.getOriginalFilename();
				  name = getFileName(signLeton.getUrlFile(), namex);
				  paths = Paths.get(signLeton.getUrlFile() + name);
				  id = save(name, type,file.getSize()).getId();
			  } else if(type.equalsIgnoreCase("video")) {
				  String  namex =  iduser.toString() + "&3&" + file.getOriginalFilename();
				  name = getFileName(signLeton.getUrlVideo(), namex);
				  paths = Paths.get(signLeton.getUrlVideo() + name);
				  id = save(name, type, file.getSize()).getId();
			  } else {
				  String  namex =  iduser.toString() + "&3&" + file.getOriginalFilename();
				   name = getFileName(signLeton.getUrlImg(), namex);
				   paths = Paths.get(signLeton.getUrlImg() + name);
				   id = save(name, type, file.getSize()).getId();
			  }
		  try {
			Files.copy(file.getInputStream(),paths,StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			  System.out.println(e.getMessage());
		}
		return ResponseEntity.status(200).body(DataInfoFileReq.builder().namefile(name).type(type).id(id).build());
	}


	@Override
	public ResponseEntity<DataListImgAndVideoAndFileRep> getAllDataFromRoom(String room) {
		List<DataInfoFileRep> listImg = new ArrayList<DataInfoFileRep>();
		List<DataInfoFileRep> listVideo = new ArrayList<DataInfoFileRep>();
		List<DataInfoFileRep> listFile = new ArrayList<DataInfoFileRep>();
		MESSAGE_REPO.findAll().forEach((e) -> {
			if(e.getFriendmess() == null) {
				if(e.getGroupmess().getCoderoom().equalsIgnoreCase(room)) {
					apply(listImg,listVideo,listFile,e.getId());
				}
			} else if(e.getFriendmess().getCoderoom().equalsIgnoreCase(room)) {
					apply(listImg,listVideo,listFile,e.getId());
			}
		});
		return ResponseEntity.ok(DataListImgAndVideoAndFileRep.builder().list_file(listFile).list_img(listImg).list_video(listVideo).build());
	}


	@Override
	public void delete(String name, String type) {
		String url = "";
		Signleton signLeton  = Signleton.getInstance();
		if(type.equalsIgnoreCase("img") || type.equalsIgnoreCase("image")) {
			url  = signLeton.getUrlImg();
		}
		if(type.equalsIgnoreCase("video")) {
			url  = signLeton.getUrlVideo();
		}
		if(type.equalsIgnoreCase("file")) {
			url  = signLeton.getUrlFile();
		}
		Path paths = Paths.get(url + name);
		try {
			Files.deleteIfExists(paths);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}




	@Override
	public ResponseEntity<List<DataInfoFileRep>> getImgAndVideoFormRoom(String room) {
		List<DataInfoFileRep> data = new ArrayList<DataInfoFileRep>();
		MESSAGE_REPO.findAll().forEach((e) -> {
			if(e.getFriendmess() == null) {
				if(e.getGroupmess().getCoderoom().equalsIgnoreCase(room)) {
					addData(data, e);
				}
			} else {
				if(e.getFriendmess().getCoderoom().equalsIgnoreCase(room)) {
					addData(data, e);
				}	
			}
		});
		return ResponseEntity.ok(data);
	}

	public String getFileName(String url, String name) {
		int i = 0;
		File file  = new File(url + i +  "&3&" + name );
		while (file.exists()) {
			i++;
			file  = new File(url + i + "&3&" + name);
		}
		return   i + "&3&" + name;
	}



	private void apply(List<DataInfoFileRep> img, List<DataInfoFileRep> video, List<DataInfoFileRep> file, Long idmess) {
		FILE_REPO.getAllByIdMess(idmess).ifPresent((e) -> {
			e.forEach((ex) -> {
				DataInfoFileRep x = new DataInfoFileRep();
				x.setId(ex.getId());
				x.setName(ex.getNamefile());
				x.setTime(null);
				if(ex.getTypefile().equalsIgnoreCase("file")) {
					x.setType(ex.getTypefile());
					file.add(x);
				}
				if(ex.getTypefile().equalsIgnoreCase("video")) {
					x.setType(ex.getTypefile());
					video.add(x);
				}
				if(ex.getTypefile().equalsIgnoreCase("image")) {
					x.setType(ex.getTypefile());
					img.add(x);
				}
			});
		});
	}


	private void addData(List<DataInfoFileRep> data, MessageEn e) {
		FILE_REPO.getAllByIdMess(e.getId()).orElseThrow().forEach((ex) -> {
			if(!ex.getTypefile().equalsIgnoreCase("file")) {
				DataInfoFileRep dataAdd = new DataInfoFileRep();
				dataAdd.setName(ex.getNamefile());
				dataAdd.setTime(e.getTime());
				dataAdd.setType(ex.getTypefile());
				dataAdd.setId(ex.getId());
				data.add(dataAdd);
			}
		});
	}
};
