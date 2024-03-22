package com.chatmrkhoi.chatmrkhoi.api;

import com.chatmrkhoi.chatmrkhoi.Data.reponse.DataMessageRep;
import com.chatmrkhoi.chatmrkhoi.Data.request.DataSaveRevokeReq;
import com.chatmrkhoi.chatmrkhoi.service.impl.RevokeSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/revoke-center")
public class RevokeApi {

    @Autowired RevokeSer REVOKE_SER;
    @PostMapping("/unmess")
    public ResponseEntity<DataMessageRep> revokeMessage(@RequestBody Optional<DataSaveRevokeReq> data) {
        return REVOKE_SER.save(data.orElseThrow());
    }
}
