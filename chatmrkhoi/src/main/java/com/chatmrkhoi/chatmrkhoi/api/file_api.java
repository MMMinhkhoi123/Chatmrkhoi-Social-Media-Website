package com.chatmrkhoi.chatmrkhoi.api;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

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

@CrossOrigin(origins = { "http://localhost:5173" }, methods = { 
		RequestMethod.POST,
		RequestMethod.GET,
		RequestMethod.DELETE,
		RequestMethod.PUT,
} )
@RestController
@RequestMapping("/file")
public class file_api {

	@Autowired
	File_service file_service;
	
	@Autowired
	file_repo file_repo;
	@Autowired
	Group_service group_service;
	
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
	public @ResponseBody byte[] getpng ( @PathVariable("imgname") Optional<String> name) throws IOException {
	      return getClass().getResourceAsStream("/static/img/"+ name.get()).readAllBytes();
	}
	
	
    @GetMapping( value = "/geturl-file/{imgname}", produces = MediaType.ALL_VALUE)
	private void imguploadfile(HttpServletResponse response, @PathVariable("imgname") Optional<String> name) throws IOException {
		  File files = new File("");
	      String currentDirectory = files.getAbsolutePath() + file_service.convertpath("\\src\\main\\resources\\static\\file\\");
		 InputStream resoures = file_service.getresoure(currentDirectory , name.get());
		 response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		 StreamUtils.copy(resoures, response.getOutputStream());
		 resoures.close();
	}


	@GetMapping(value = "/geturl-video/{imgname}")
	public  ResponseEntity<Resource> getvideo(@PathVariable("imgname") Optional<String> name) throws IOException {
		 File files = new File("");
	      String currentDirectory = files.getAbsolutePath() + file_service.convertpath("\\target\\classes\\static\\video\\");
	 	 byte[] contents = Files.readAllBytes(Paths.get(currentDirectory + name.get()));
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM).body(new ByteArrayResource(contents));
	}
	

	@GetMapping(value = "/filedowloadid/{id}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE )
	public ResponseEntity<byte[]> dowloasd(@PathVariable("id") Optional<Long> id) throws IOException {
		 file_entity filx =  file_repo.findById(id.get()).get();
		 File files = new File("");
		   HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
	        headers.setContentDisposition(ContentDisposition.attachment().filename(filx.getNamefile()).build());
	      String currentDirectory = files.getAbsolutePath() + file_service.convertpath("\\target\\classes\\static\\img\\");
		 byte[] contents = Files.readAllBytes(Paths.get(currentDirectory + filx.getNamefile()));
		return ResponseEntity.status(200).headers(headers).body(contents);
	}
	
	
	@GetMapping(value = "/filedowload/{namefile}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE )
	public ResponseEntity<byte[]> dowload(@PathVariable("namefile") Optional<String> name) throws IOException {
		 File files = new File("");
		   HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
	        headers.setContentDisposition(ContentDisposition.attachment().filename(name.get()).build());
	      String currentDirectory = files.getAbsolutePath() + file_service.convertpath("\\target\\classes\\static\\file\\");
		 byte[] contents = Files.readAllBytes(Paths.get(currentDirectory + name.get()));
		return ResponseEntity.status(200).headers(headers).body(contents);
	}
	
	

}
