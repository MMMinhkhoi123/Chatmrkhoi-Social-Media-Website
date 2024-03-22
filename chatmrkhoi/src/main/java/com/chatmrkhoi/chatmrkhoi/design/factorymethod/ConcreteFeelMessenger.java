package com.chatmrkhoi.chatmrkhoi.design.factorymethod;
import com.chatmrkhoi.chatmrkhoi.Data.request.DataFeelReq;
import com.chatmrkhoi.chatmrkhoi.common.Common;
import com.chatmrkhoi.chatmrkhoi.entity.FeelingEn;
import com.chatmrkhoi.chatmrkhoi.repositories.IMessageRepo;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Setter
public class ConcreteFeelMessenger extends ACenterActivityMess {
    private DataFeelReq data;
    @Autowired Common COMMON;
    @Autowired IMessageRepo MESSAGE_REPO;
    @Override
    public IActivityMessenger setupObject() {
        return FeelingEn.builder()
                .time(System.currentTimeMillis())
                .type(data.getType())
                .mess(data.getFeel())
                .messentity(MESSAGE_REPO.findById(data.getIdmess()).orElseThrow())
                .iduser(COMMON.getUserAuthentication())
        .build();
    }
}
