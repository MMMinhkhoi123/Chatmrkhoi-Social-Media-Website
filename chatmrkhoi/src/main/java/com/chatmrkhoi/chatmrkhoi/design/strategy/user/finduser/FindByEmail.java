package com.chatmrkhoi.chatmrkhoi.design.strategy.user.finduser;

import com.chatmrkhoi.chatmrkhoi.Data.reponse.DataInfoUserOtherRep;
import com.chatmrkhoi.chatmrkhoi.Util.convert.UserConvert;
import com.chatmrkhoi.chatmrkhoi.common.Common;
import com.chatmrkhoi.chatmrkhoi.entity.UserEn;
import com.chatmrkhoi.chatmrkhoi.repositories.IUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FindByEmail implements ITypeFindUser {

    private final Common COMMON;
    private final IUserRepo USER_REPO;
    private  final UserConvert CONVERT;


    @Autowired
    public  FindByEmail(Common dataCommon, IUserRepo dataUserRepo, UserConvert userConvert) {
        this.COMMON = dataCommon;
        this.USER_REPO = dataUserRepo;
        this.CONVERT = userConvert;
    }

    @Override
    public List<DataInfoUserOtherRep> findAllUser(String gmail) {
        String status_fake = "not";
        UserEn User = COMMON.getUserAuthentication();
        List<DataInfoUserOtherRep> DataRep = new ArrayList<DataInfoUserOtherRep>();
        Optional<UserEn> user = USER_REPO.findByEmail(gmail);
        user.ifPresent(e -> {
            if (CONVERT.checkStatus(e.getFriend_entities(), User.getId()).equalsIgnoreCase("not")) {
                CONVERT.checkStatus(User.getFriend_entities(),e.getId());
            }
            if(CONVERT.convertDataInfoUser(e.getId(), status_fake, CONVERT.getRoom(User.getId(), e.getId())) != null) {
                DataRep.add(CONVERT.convertDataInfoUser(e.getId(), status_fake, CONVERT.getRoom(User.getId(), e.getId())));
            }
        });
        return  DataRep;
    }





}
