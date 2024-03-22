package com.chatmrkhoi.chatmrkhoi.api;

import com.chatmrkhoi.chatmrkhoi.Data.reponse.DataMessOldAndNewRep;
import com.chatmrkhoi.chatmrkhoi.Data.reponse.DataMessageRep;
import com.chatmrkhoi.chatmrkhoi.Data.request.DataFeelReq;
import com.chatmrkhoi.chatmrkhoi.service.impl.FeelSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/feel-center")
public class FeelingApi {

    @Autowired FeelSer FEEL_SER;
    @PostMapping("/feel")
    public ResponseEntity<DataMessOldAndNewRep> addFeeling(@RequestBody Optional<DataFeelReq> data) {
        return FEEL_SER.save(data.orElseThrow());
    }
    @DeleteMapping("/feel/{id}/{type}")
    public ResponseEntity<DataMessageRep> deleteFeeling(@PathVariable("id") Optional<Long> id, @PathVariable("type") Optional<String> type) {
        return FEEL_SER.delete(id.orElseThrow(), type.orElseThrow());
    }

}
