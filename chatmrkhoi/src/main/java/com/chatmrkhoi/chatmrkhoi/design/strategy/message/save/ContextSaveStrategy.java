package com.chatmrkhoi.chatmrkhoi.design.strategy.message.save;


import com.chatmrkhoi.chatmrkhoi.Data.reponse.DataMessageRep;
import com.chatmrkhoi.chatmrkhoi.Data.request.DataSaveMessReq;
import com.chatmrkhoi.chatmrkhoi.repositories.IFileRepo;
import com.chatmrkhoi.chatmrkhoi.repositories.IMessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class ContextSaveStrategy {
    private  ISaveStrategy iSaveStrategy;

    @Autowired IMessageRepo MESSAGE_REPO;

    @Autowired IFileRepo FILE_REPO;
    public void setStrategy(ISaveStrategy ISaveStrategy) {
        this.iSaveStrategy = ISaveStrategy;
    }

    public List<DataMessageRep> executeStrategy(DataSaveMessReq data) {
        List<DataMessageRep> listMessRep = new ArrayList<DataMessageRep>();
        iSaveStrategy.SaveMessage(data, listMessRep);
        return  listMessRep;
    }
}
