package com.chatmrkhoi.chatmrkhoi.api;

import com.chatmrkhoi.chatmrkhoi.Data.Send.DataSendNotice;
import com.chatmrkhoi.chatmrkhoi.service.impl.NoticeSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/notice-center")
public class NoticeApi {

    @Autowired NoticeSer noticeSer;

    @GetMapping("/all")
    public ResponseEntity<List<DataSendNotice>> getAll() {
        return noticeSer.getAllList();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteNotice(@PathVariable(value = "id")Optional<String> id) {
        noticeSer.delete(Long.parseLong(id.orElseThrow()));
    }

    @PutMapping("/view/{id}")
    public void updateNotice(@PathVariable(value = "id")Optional<String> id) {
        System.out.println("he;;");
        noticeSer.updateView(Long.parseLong(id.orElseThrow()));
    }
}
