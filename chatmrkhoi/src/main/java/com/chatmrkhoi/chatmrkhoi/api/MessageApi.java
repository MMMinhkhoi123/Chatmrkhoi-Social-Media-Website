package com.chatmrkhoi.chatmrkhoi.api;


import com.chatmrkhoi.chatmrkhoi.Data.reponse.DataMessageRep;
import com.chatmrkhoi.chatmrkhoi.Data.request.DataSaveMessReq;
import com.chatmrkhoi.chatmrkhoi.Data.request.DatatransactionMessReq;
import com.chatmrkhoi.chatmrkhoi.service.impl.MessageSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/mess-center")
public class MessageApi {

    @Autowired MessageSer MESS_SER;
    @PostMapping("/sendmess")
    public ResponseEntity<List<DataMessageRep>> SaveMessage(@RequestBody Optional<DataSaveMessReq> data) {
        return MESS_SER.save(data.orElseThrow());
    }
    @PostMapping("/notifymess")
    public void notifyMessage(@RequestBody Optional<DataSaveMessReq> data) {
        MESS_SER.notifyNew(data.orElseThrow());
    }
    @PostMapping("/move")
    public ResponseEntity<DataMessageRep> transactionMess(@RequestBody Optional<DatatransactionMessReq> data) {
        return MESS_SER.transaction(data.orElseThrow());
    }
    @GetMapping("/messall")
    public ResponseEntity<List<DataMessageRep>> getAllMess() {
        return MESS_SER.getAll();
    }
}
