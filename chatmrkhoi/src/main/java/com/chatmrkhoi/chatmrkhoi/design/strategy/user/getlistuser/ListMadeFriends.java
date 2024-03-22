package com.chatmrkhoi.chatmrkhoi.design.strategy.user.getlistuser;

import com.chatmrkhoi.chatmrkhoi.Data.reponse.DataInfoUserOtherRep;
import com.chatmrkhoi.chatmrkhoi.Util.convert.UserConvert;
import com.chatmrkhoi.chatmrkhoi.common.Common;
import com.chatmrkhoi.chatmrkhoi.common.EAccount;
import com.chatmrkhoi.chatmrkhoi.entity.UserEn;
import com.chatmrkhoi.chatmrkhoi.repositories.IFriendRepo;
import com.chatmrkhoi.chatmrkhoi.repositories.IUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ListMadeFriends implements ITypeListUser {
    private final IUserRepo USER_REPO;
    private  final UserConvert CONVERT;
    private  final IFriendRepo FRIEND_REPO;
    private final Common COMMON;
    @Autowired
    public ListMadeFriends(IUserRepo userRepo, UserConvert userConvert, IFriendRepo friendRepo, Common common) {
        this.USER_REPO = userRepo;
        this.CONVERT = userConvert;
        this.FRIEND_REPO = friendRepo;
        this.COMMON = common;
    }

    @Override
    public List<DataInfoUserOtherRep> getData() {
        Long id = COMMON.getUserAuthentication().getId();
        List<DataInfoUserOtherRep> DataRep = new ArrayList<DataInfoUserOtherRep>();
        UserEn user = USER_REPO.findById(id).orElseThrow();

        user.getFriend_entities().forEach((e) -> {
            if(e.getStatus().equalsIgnoreCase(EAccount.FRIEND.getValue())) {
                DataRep.add(CONVERT.convertDataInfoUser(e.getIdfriend(), e.getStatus(), e.getCoderoom()));
            }
        });
        FRIEND_REPO.findAllByFriendId(id).orElseThrow().forEach((e) -> {
            if(e.getStatus().equalsIgnoreCase(EAccount.FRIEND.getValue())) {
                DataRep.add(CONVERT.convertDataInfoUser(e.getUsersentities().getId(), e.getStatus(), e.getCoderoom()));
            }
        });
        return DataRep;
    }
}
