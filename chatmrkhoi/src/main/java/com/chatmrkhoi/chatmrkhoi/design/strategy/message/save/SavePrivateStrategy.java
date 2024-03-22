package com.chatmrkhoi.chatmrkhoi.design.strategy.message.save;

import com.chatmrkhoi.chatmrkhoi.Data.reponse.DataMessageRep;
import com.chatmrkhoi.chatmrkhoi.Data.request.DataSaveMessReq;
import com.chatmrkhoi.chatmrkhoi.design.teamplatemethod.TemplateSaveMessPrivate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class SavePrivateStrategy implements  ISaveStrategy  {
    @Autowired TemplateSaveMessPrivate Save;

    @Override
    public void SaveMessage(DataSaveMessReq data, List<DataMessageRep> listMessRep) {
        Save.setData(data,listMessRep);
        Save.run();
    }
}
