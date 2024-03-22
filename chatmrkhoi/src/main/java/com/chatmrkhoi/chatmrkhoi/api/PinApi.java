package com.chatmrkhoi.chatmrkhoi.api;

import com.chatmrkhoi.chatmrkhoi.Data.reponse.DataInfoMessPinRep;
import com.chatmrkhoi.chatmrkhoi.Data.reponse.DataMessNewAndListInfoPinAndInfoPinNewRep;
import com.chatmrkhoi.chatmrkhoi.Data.request.DataSavePinReq;
import com.chatmrkhoi.chatmrkhoi.service.impl.PinSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pin-center")
public class PinApi {

    @Autowired PinSer PIN_SER;
    @PostMapping("/pin")
    public ResponseEntity<DataMessNewAndListInfoPinAndInfoPinNewRep> addPin(@RequestBody Optional<DataSavePinReq> data) {
        return PIN_SER.save(data.orElseThrow());
    }
    @DeleteMapping("/pin-del/{id}")
    public ResponseEntity<DataMessNewAndListInfoPinAndInfoPinNewRep> deletePin(@PathVariable("id") Optional<Long> id) {
        return PIN_SER.delete(id.orElseThrow());
    }
    @GetMapping("/pin")
    public ResponseEntity<List<DataInfoMessPinRep>> getAllPin() {
        return PIN_SER.getAll();
    }
}
