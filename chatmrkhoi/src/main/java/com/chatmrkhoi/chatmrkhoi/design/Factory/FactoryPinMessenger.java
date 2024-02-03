package com.chatmrkhoi.chatmrkhoi.design.Factory;
import  com.chatmrkhoi.chatmrkhoi.entity.pin_entity;
public class FactoryPinMessenger extends AFactoryActionMess{
    @Override
    public IActionMessenger CreateActionMess() {
        return pin_entity.builder()
                .time(System.currentTimeMillis())
                .type(type)
                .userEntity(usersEntity)
                .messentity(messEntity)
                .status(key)
                .build();
    }
}
