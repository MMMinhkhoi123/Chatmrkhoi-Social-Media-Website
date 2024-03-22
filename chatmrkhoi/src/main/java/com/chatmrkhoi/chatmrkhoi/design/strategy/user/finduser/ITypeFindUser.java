package com.chatmrkhoi.chatmrkhoi.design.strategy.user.finduser;

import com.chatmrkhoi.chatmrkhoi.Data.reponse.DataInfoUserOtherRep;

import java.util.List;

public interface ITypeFindUser {
    List<DataInfoUserOtherRep> findAllUser(String key);
}
