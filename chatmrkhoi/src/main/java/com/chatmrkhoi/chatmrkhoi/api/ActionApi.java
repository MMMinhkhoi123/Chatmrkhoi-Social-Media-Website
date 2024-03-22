package com.chatmrkhoi.chatmrkhoi.api;

import com.chatmrkhoi.chatmrkhoi.Data.reponse.DataInfoActionUserRep;
import com.chatmrkhoi.chatmrkhoi.service.impl.ActionSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/action-center")
public class ActionApi {
    @Autowired ActionSer ACTION_SER;
    @GetMapping("/actionall")
    public ResponseEntity<List<DataInfoActionUserRep>> action() {
        return ACTION_SER.getAll();
    }
}
