package com.chatmrkhoi.chatmrkhoi.api;

import com.chatmrkhoi.chatmrkhoi.Data.reponse.DataMessageRep;
import com.chatmrkhoi.chatmrkhoi.Data.request.DataSaveWatchReq;
import com.chatmrkhoi.chatmrkhoi.service.impl.WatchSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/watch-center")
public class WatchApi {
    @Autowired WatchSer WATCH_SER;
    @PostMapping("/watch")
    public ResponseEntity<DataMessageRep> addWatch(@RequestBody Optional<DataSaveWatchReq> data) {
        return WATCH_SER.save(data.orElseThrow());
    }
}
