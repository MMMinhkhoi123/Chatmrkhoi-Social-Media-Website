package com.chatmrkhoi.chatmrkhoi.api;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

import com.chatmrkhoi.chatmrkhoi.design.Signleton.Signleton;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/file")
public class ByteApi {

	@GetMapping(value = "/get-png/{imgname}", produces = MediaType.IMAGE_PNG_VALUE)
	public @ResponseBody byte[] GetImage( @PathVariable("imgname") Optional<String> name) throws IOException {
	      return getClass().getResourceAsStream("/static/img/"+ name.orElseThrow()).readAllBytes();
	}
	@GetMapping(value = "/geturl-video/{imgname}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public  @ResponseBody byte[] DownLoadVideo(@PathVariable("imgname") Optional<String> name) throws IOException {
		return Files.readAllBytes(Paths.get(Signleton.getInstance().getUrlVideo() + name.orElseThrow()));
	}
	@GetMapping(value = "/filedowloadid/{name}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public  ResponseEntity<byte[]> DownLoadImage(@PathVariable("name") Optional<String> name) throws IOException {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		String [] nameArray = name.orElseThrow().split(  "&3&");
		String nameRead = nameArray[nameArray.length -1];
		headers.setContentDisposition(ContentDisposition.attachment().filename(nameRead).build());
		byte[] contents = Files.readAllBytes(Paths.get(Signleton.getInstance().getUrlImg() + name.get()));
		return  ResponseEntity.status(200).headers(headers).body(contents);
	}
	@GetMapping(value = "/filedowload/{namefile}")
	public ResponseEntity<byte[]> DownLoadFile(@PathVariable("namefile") Optional<String> name) throws IOException {
		 HttpHeaders headers = new HttpHeaders();
		 headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		 String [] nameArray = name.orElseThrow().split(  "&3&");
		 String nameRead = nameArray[nameArray.length -1];
		 headers.setContentDisposition(ContentDisposition.attachment().filename(nameRead).build());
		 byte[] contents = Files.readAllBytes(Paths.get(Signleton.getInstance().getUrlFile() + name.get()));
		return ResponseEntity.status(200).headers(headers).body(contents);
	}

}
