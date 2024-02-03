package com.chatmrkhoi.chatmrkhoi.api;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

import com.chatmrkhoi.chatmrkhoi.design.Signgleton;
import com.chatmrkhoi.chatmrkhoi.entity.Users_entity;
import com.chatmrkhoi.chatmrkhoi.reponsitory.User_repo;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.chatmrkhoi.chatmrkhoi.entity.file_entity;
import com.chatmrkhoi.chatmrkhoi.reponse.get_group_reponse;
import com.chatmrkhoi.chatmrkhoi.reponsitory.file_repo;
import com.chatmrkhoi.chatmrkhoi.request.file_reuqest;
import com.chatmrkhoi.chatmrkhoi.request.update_img_request;
import com.chatmrkhoi.chatmrkhoi.service.impl.File_service;
import com.chatmrkhoi.chatmrkhoi.service.impl.Group_service;

import jakarta.persistence.criteria.CriteriaBuilder.In;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
@RestController
@RequestMapping("/file")
public class file_api {

	@Autowired
	File_service file_service;
	
	@Autowired
	file_repo file_repo;

	@Autowired
	Group_service group_service;
	@Autowired
	User_repo user_repo;
	@PostMapping("/upload")
	public ResponseEntity<file_reuqest> upload(
			@RequestParam(name = "file") Optional<MultipartFile> file,
			@RequestParam(name = "type") Optional<String> type) throws IOException {
	   return file_service.upload(file.get(), type.get());
	}
		
	@PostMapping("/upload-group")
	public ResponseEntity<get_group_reponse> uploadgroup(
			@RequestParam(name = "file") Optional<MultipartFile> file, 
			@RequestParam(name = "id") Optional<Long> id,
			@RequestParam(name = "name") Optional<String> name) throws IOException {
		MultipartFile files = null;
		if(file.isPresent() == true) {
			files = file.get();
		}
		update_img_request data = update_img_request.builder().file(files).id(id.get()).name(name.get()).build();
		return group_service.updateimggroup(data);
	}


	@GetMapping(value = "/get-png/{imgname}", produces = MediaType.IMAGE_PNG_VALUE)
	public @ResponseBody byte[] GetImage( @PathVariable("imgname") Optional<String> name) throws IOException {
	      return getClass().getResourceAsStream("/static/img/"+ name.get()).readAllBytes();
	}

	@GetMapping(value = "/geturl-video/{imgname}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public  @ResponseBody byte[] DownLoadVideo(@PathVariable("imgname") Optional<String> name) throws IOException {
		Signgleton signgleton = Signgleton.getInstance();
		return Files.readAllBytes(Paths.get(signgleton.getUrlVideo() + name.get()));
	}
	@GetMapping(value = "/filedowloadid/{name}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public  ResponseEntity<byte[]> DownLoadImage(@PathVariable("name") Optional<String> name) throws IOException {
		Signgleton signgleton = Signgleton.getInstance();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		String [] namearray = name.get().split(  "&3&");
		String nameread = namearray[namearray.length -1];
		headers.setContentDisposition(ContentDisposition.attachment().filename(nameread).build());
		byte[] contents = Files.readAllBytes(Paths.get(signgleton.getUrlImg() + name.get()));
		return  ResponseEntity.status(200).headers(headers).body(contents);
	}

	@GetMapping(value = "/filedowload/{namefile}")
	public ResponseEntity<byte[]> DownLoadFile(@PathVariable("namefile") Optional<String> name) throws IOException {
		Signgleton signgleton = Signgleton.getInstance();
		 HttpHeaders headers = new HttpHeaders();
		 headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		 String [] namearray = name.get().split(  "&3&");
		 String nameread = namearray[namearray.length -1];
		 headers.setContentDisposition(ContentDisposition.attachment().filename(nameread).build());
		 byte[] contents = Files.readAllBytes(Paths.get(signgleton.getUrlFile() + name.get()));
		return ResponseEntity.status(200).headers(headers).body(contents);
	}

}
