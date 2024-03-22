package com.chatmrkhoi.chatmrkhoi.design.strategy.user.getlistuser;

import com.chatmrkhoi.chatmrkhoi.Data.reponse.DataInfoUserOtherRep;
import com.chatmrkhoi.chatmrkhoi.Util.convert.UserConvert;
import com.chatmrkhoi.chatmrkhoi.common.Common;
import com.chatmrkhoi.chatmrkhoi.common.EAccount;
import com.chatmrkhoi.chatmrkhoi.entity.UserEn;
import com.chatmrkhoi.chatmrkhoi.repositories.IUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ListSendFriendRequest implements ITypeListUser {

    private final IUserRepo USER_REPO;
    private  final UserConvert CONVERT;
    private final Common COMMON;

    @Autowired
    public ListSendFriendRequest(IUserRepo userRepo, UserConvert userConvert, Common common) {
        this.USER_REPO = userRepo;
        this.CONVERT = userConvert;
        this.COMMON = common;
    }


    @Override
    public List<DataInfoUserOtherRep> getData() {
        Long id = COMMON.getUserAuthentication().getId();
        List<DataInfoUserOtherRep> DataRep = new ArrayList<DataInfoUserOtherRep>();
        UserEn user = USER_REPO.findById(id).orElseThrow();
        user.getFriend_entities().forEach((e) -> {
            if(e.getStatus().equalsIgnoreCase(EAccount.REQUEST.getValue())) {
                DataRep.add(CONVERT.convertDataInfoUser(e.getIdfriend(), e.getStatus(), e.getCoderoom()));
            }
        });
        return DataRep;
    }
}
