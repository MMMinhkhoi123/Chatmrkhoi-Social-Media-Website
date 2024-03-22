package com.chatmrkhoi.chatmrkhoi.design.strategy.user.finduser;


import com.chatmrkhoi.chatmrkhoi.Data.reponse.DataInfoUserOtherRep;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Setter
public class ContextFindUser {
    private ITypeFindUser typeFind;
    public List<DataInfoUserOtherRep> findUser(String key) {
       return  typeFind.findAllUser(key);
    }
}
