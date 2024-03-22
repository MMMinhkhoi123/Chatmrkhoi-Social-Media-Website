package com.chatmrkhoi.chatmrkhoi.api;


import com.chatmrkhoi.chatmrkhoi.Data.reponse.DataInfoGroupRep;
import com.chatmrkhoi.chatmrkhoi.Data.reponse.DataInfoGroupNewAndRoomConnectRep;
import com.chatmrkhoi.chatmrkhoi.Data.reponse.DataMessNewAndInfoNumberRep;
import com.chatmrkhoi.chatmrkhoi.Data.reponse.DataMessageRep;
import com.chatmrkhoi.chatmrkhoi.Data.request.DataAddGroupReq;
import com.chatmrkhoi.chatmrkhoi.Data.request.DataKickOrAddAnNumberReq;
import com.chatmrkhoi.chatmrkhoi.Data.request.DataUpdateNameGropReq;
import com.chatmrkhoi.chatmrkhoi.Data.request.DataInfoGroupUpdateReq;
import com.chatmrkhoi.chatmrkhoi.service.impl.GroupSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/group-center")
public class GroupApi {

    @Autowired GroupSer GROUP_SER;
    @DeleteMapping("/group/{id}")
    public void deleteGroup(@PathVariable(value = "id") Optional<Long> id) {
        GROUP_SER.delete(id.orElseThrow());
    }
    @PostMapping("/group")
    public ResponseEntity<DataInfoGroupNewAndRoomConnectRep> createGroup(@RequestBody Optional<DataAddGroupReq> data) {
        return GROUP_SER.save(data.orElseThrow());
    }
    @PostMapping("/out-group/{id}/{code}")
    public ResponseEntity<DataMessageRep> outGroup(@PathVariable(value = "id") Optional<Long> id, @PathVariable(value = "code") Optional<String> code) {
        return GROUP_SER.out(id.orElseThrow(), code.orElseThrow());
    }
    @GetMapping("/mygroup")
    public ResponseEntity<List<DataInfoGroupRep>> getListMyGroup() {
        return GROUP_SER.getInfo();
    }
    @PutMapping("/group-name")
    public ResponseEntity<DataInfoGroupRep> updateNameGroup(@RequestBody Optional<DataUpdateNameGropReq> data) {
        return GROUP_SER.updateNameGroup(data.orElseThrow());
    }
    @PostMapping("/group-person")
    public ResponseEntity<DataMessNewAndInfoNumberRep> addNumber(@RequestBody Optional<DataKickOrAddAnNumberReq> data) {
        return GROUP_SER.addNumber(data.orElseThrow());
    }
    @PostMapping("/group-person-kich")
    public ResponseEntity<DataMessNewAndInfoNumberRep> kickNumber(@RequestBody Optional<DataKickOrAddAnNumberReq> data) {
        return GROUP_SER.kickNumber(data.orElseThrow());
    }
    @PostMapping("/upload-group")
    public ResponseEntity<DataInfoGroupRep> uploadGroup(
            @RequestParam(name = "file") Optional<MultipartFile> file,
            @RequestParam(name = "id") Optional<Long> id,
            @RequestParam(name = "name") Optional<String> name) throws IOException {
        DataInfoGroupUpdateReq data = DataInfoGroupUpdateReq.builder().file(file.orElseThrow()).id(id.orElseThrow()).name(name.orElseThrow()).build();
        return GROUP_SER.updateImgGroup(data);
    }
}
