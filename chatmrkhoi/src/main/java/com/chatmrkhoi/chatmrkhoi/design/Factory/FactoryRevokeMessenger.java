package com.chatmrkhoi.chatmrkhoi.design.Factory;
import  com.chatmrkhoi.chatmrkhoi.entity.revoke_entity;

public class FactoryRevokeMessenger extends AFactoryActionMess{
    @Override
    public IActionMessenger CreateActionMess() {
        return revoke_entity
                .builder()
                    .messentity(messEntity)
                    .time(System.currentTimeMillis())
                    .userentity(usersEntity)
                    .type(type)
                .build();
    }
}
