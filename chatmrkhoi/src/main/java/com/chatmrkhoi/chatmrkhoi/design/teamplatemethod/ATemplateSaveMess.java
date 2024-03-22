package com.chatmrkhoi.chatmrkhoi.design.teamplatemethod;

import com.chatmrkhoi.chatmrkhoi.Data.reponse.DataMessageRep;
import com.chatmrkhoi.chatmrkhoi.Data.request.DataSaveMessReq;
import com.chatmrkhoi.chatmrkhoi.entity.MessageEn;

import java.util.ArrayList;
import java.util.List;

public abstract class ATemplateSaveMess {
    private DataSaveMessReq DataSaveMessReq;

    public List<MessageEn> list  = new ArrayList<>();
    public List<DataMessageRep> listMessRep = new ArrayList<>();
    public  abstract  void saveMessNewDate(DataSaveMessReq data);
    public  abstract MessageEn saveMess(DataSaveMessReq data);
    public  abstract  void  updateInfoImage(DataSaveMessReq data, MessageEn messageEn);
    public  void setData(DataSaveMessReq data, List<DataMessageRep> listMessRep) {
        this.DataSaveMessReq = data;
        this.listMessRep = listMessRep;
    }
    public void run() {;
        saveMessNewDate(DataSaveMessReq);
        MessageEn messageEn = saveMess(DataSaveMessReq);
        updateInfoImage(DataSaveMessReq, messageEn);
    }


}
