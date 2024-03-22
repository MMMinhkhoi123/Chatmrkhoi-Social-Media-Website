package com.chatmrkhoi.chatmrkhoi.design.strategy.user.finduser;

import com.chatmrkhoi.chatmrkhoi.Data.reponse.DataInfoUserOtherRep;
import com.chatmrkhoi.chatmrkhoi.Util.convert.UserConvert;
import com.chatmrkhoi.chatmrkhoi.common.Common;
import com.chatmrkhoi.chatmrkhoi.entity.InfoEn;
import com.chatmrkhoi.chatmrkhoi.entity.UserEn;
import com.chatmrkhoi.chatmrkhoi.repositories.IInfoRepo;
import com.chatmrkhoi.chatmrkhoi.repositories.IUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FindByName implements  ITypeFindUser {

    private final Common COMMON;
    private final IUserRepo USER_REPO;
    private  final UserConvert CONVERT;
    private  final IInfoRepo INFO_REPO;

    @Autowired
    public  FindByName(Common dataCommon, IUserRepo dataUserRepo, UserConvert userConvert, IInfoRepo iInfoRepo) {
        this.COMMON = dataCommon;
        this.USER_REPO = dataUserRepo;
        this.CONVERT = userConvert;
        this.INFO_REPO = iInfoRepo;
    }

    @Override
    public List<DataInfoUserOtherRep> findAllUser(String name) {
        UserEn user = COMMON.getUserAuthentication();
        List<DataInfoUserOtherRep> array = new ArrayList<DataInfoUserOtherRep>();
        USER_REPO.findAll().forEach((e) -> {
            InfoEn info = INFO_REPO.findByIdUser(e.getId()).orElseThrow();
            if(e.isVerify() && info.getAvatars() != null) {
                if (CONVERT.checkStatus(e.getFriend_entities(),user.getId()).equalsIgnoreCase("not")) {
                    CONVERT.checkStatus(user.getFriend_entities(),e.getId());
                }
                if(CONVERT.convertDataInfoUser(e.getId(), CONVERT.status_fake, CONVERT.getRoom(user.getId(), e.getId())) != null
                        && info.getFullnames().toUpperCase().contains(name.toUpperCase())
                        && !e.getId().equals(user.getId())) {

                    array.add(CONVERT.convertDataInfoUser(e.getId(), CONVERT.status_fake, CONVERT.getRoom(user.getId(), e.getId())));
                }
            }
        });
        return  array;
    }
}
