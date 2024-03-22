package com.chatmrkhoi.chatmrkhoi.design.strategy.user.getlistuser;

import com.chatmrkhoi.chatmrkhoi.Data.reponse.DataInfoUserOtherRep;
import com.chatmrkhoi.chatmrkhoi.Util.convert.UserConvert;
import com.chatmrkhoi.chatmrkhoi.common.Common;
import com.chatmrkhoi.chatmrkhoi.common.EAccount;
import com.chatmrkhoi.chatmrkhoi.repositories.IFriendRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ListFriendRequests implements ITypeListUser {
    private final IFriendRepo FRIEND_REPO;
    private  final UserConvert CONVERT;
    private final Common COMMON;

    @Autowired
    public ListFriendRequests(IFriendRepo FriendRepo, UserConvert userConvert, Common common) {
        this.FRIEND_REPO = FriendRepo;
        this.CONVERT = userConvert;
        this.COMMON = common;
    }

    @Override
    public List<DataInfoUserOtherRep> getData() {
        Long id = COMMON.getUserAuthentication().getId();
        List<DataInfoUserOtherRep> DataRep = new ArrayList<DataInfoUserOtherRep>();

        FRIEND_REPO.findAllByFriendId(id).orElseThrow().forEach((e) -> {
            if (e.getStatus().equalsIgnoreCase(EAccount.REQUEST.getValue())) {
                DataRep.add(CONVERT.convertDataInfoUser(e.getUsersentities().getId(), e.getStatus(),e.getCoderoom()));
            }
        });

        return DataRep;
    }
}
