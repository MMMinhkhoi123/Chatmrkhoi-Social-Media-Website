package com.chatmrkhoi.chatmrkhoi.design.strategy.message.save;

import com.chatmrkhoi.chatmrkhoi.Data.reponse.DataMessageRep;
import com.chatmrkhoi.chatmrkhoi.Data.request.DataSaveMessReq;
import com.chatmrkhoi.chatmrkhoi.entity.MessageEn;

import java.util.List;

public interface ISaveStrategy {
    void SaveMessage(DataSaveMessReq data, List<DataMessageRep> list);
}
