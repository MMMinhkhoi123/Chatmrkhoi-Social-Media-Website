package com.chatmrkhoi.chatmrkhoi.design.factorymethod;
import com.chatmrkhoi.chatmrkhoi.Data.request.DataSavePinReq;
import com.chatmrkhoi.chatmrkhoi.common.Common;
import com.chatmrkhoi.chatmrkhoi.entity.PinEn;
import com.chatmrkhoi.chatmrkhoi.repositories.IMessageRepo;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Setter
public class ConcretePinMessenger extends ACenterActivityMess {

    @Autowired Common COMMON;
    @Autowired IMessageRepo MESSAGE_REPO;
    private DataSavePinReq data;
    private String key;
    @Override
    public IActivityMessenger setupObject() {
        return PinEn.builder()
                .time(System.currentTimeMillis())
                    .type(data.getType())
                    .userEntity(COMMON.getUserAuthentication())
                    .messentity( MESSAGE_REPO.findById(data.getIdmess()).orElseThrow())
                    .status(key)
                .build();
    }
}
