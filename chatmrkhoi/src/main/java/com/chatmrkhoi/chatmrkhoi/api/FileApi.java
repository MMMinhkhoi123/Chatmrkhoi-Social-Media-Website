package com.chatmrkhoi.chatmrkhoi.api;

import com.chatmrkhoi.chatmrkhoi.Data.reponse.DataListImgAndVideoAndFileRep;
import com.chatmrkhoi.chatmrkhoi.Data.reponse.DataInfoFileRep;
import com.chatmrkhoi.chatmrkhoi.Data.request.DataInfoFileReq;
import com.chatmrkhoi.chatmrkhoi.service.impl.FileSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/file-center")
public class FileApi {

    @Autowired FileSer FILE_SER;
    @PostMapping("/upload")
    public ResponseEntity<DataInfoFileReq> upload(
            @RequestParam(name = "file") Optional<MultipartFile> file,
            @RequestParam(name = "type") Optional<String> type) throws IOException {
        return FILE_SER.upload(file.orElseThrow(), type.orElseThrow());
    }

    @GetMapping("/data-detail-zoom/{room}")
    public ResponseEntity<List<DataInfoFileRep>> getImgAndVideoFormRoom(@PathVariable(value = "room") Optional<String> room) {
        return FILE_SER.getImgAndVideoFormRoom(room.orElseThrow());
    }
    @GetMapping("/data-detail/{room}")
    public ResponseEntity<DataListImgAndVideoAndFileRep> getAllDataFormRoom(@PathVariable(value = "room") Optional<String> room) {
        return FILE_SER.getAllDataFromRoom(room.orElseThrow());
    }

}
