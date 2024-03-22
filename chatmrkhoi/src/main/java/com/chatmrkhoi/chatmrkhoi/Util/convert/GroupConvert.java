package com.chatmrkhoi.chatmrkhoi.Util.convert;

import com.chatmrkhoi.chatmrkhoi.Data.reponse.DataInfoUserOtherRep;
import com.chatmrkhoi.chatmrkhoi.entity.GroupEn;
import com.chatmrkhoi.chatmrkhoi.entity.InfoEn;
import com.chatmrkhoi.chatmrkhoi.entity.UserEn;
import com.chatmrkhoi.chatmrkhoi.repositories.IInfoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GroupConvert {

    @Autowired IInfoRepo INFO_REPO;

    public DataInfoUserOtherRep ConvertData(UserEn data, String codeRoom) {
        InfoEn info = INFO_REPO.findByIdUser(data.getId()).orElseThrow();
        return DataInfoUserOtherRep
                .builder().coderoom(codeRoom)
                .fullname(info.getFullnames())
                .id(data.getId())
                .images(info.getAvatars())
                .type_img(info.getType_avatas())
                .build();
    }
}
