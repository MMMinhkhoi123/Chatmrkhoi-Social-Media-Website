package com.chatmrkhoi.chatmrkhoi.service;

import com.chatmrkhoi.chatmrkhoi.Data.Send.DataSendNotice;
import com.chatmrkhoi.chatmrkhoi.entity.NoticeEn;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface INotice {
    void save(String type, Object data, String key, Object userHire);

    ResponseEntity<List<DataSendNotice>> getAllList();

    void delete(Long id);

    void updateView(Long id);
}
