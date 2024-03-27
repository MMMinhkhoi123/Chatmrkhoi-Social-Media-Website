package com.chatmrkhoi.chatmrkhoi.design.strategy.notice;


import com.chatmrkhoi.chatmrkhoi.Util.convert.NoticeConvert;
import com.chatmrkhoi.chatmrkhoi.common.Common;
import com.chatmrkhoi.chatmrkhoi.entity.FriendEn;
import com.chatmrkhoi.chatmrkhoi.entity.InfoEn;
import com.chatmrkhoi.chatmrkhoi.entity.NoticeEn;
import com.chatmrkhoi.chatmrkhoi.entity.UserEn;
import com.chatmrkhoi.chatmrkhoi.repositories.IInfoRepo;
import com.chatmrkhoi.chatmrkhoi.repositories.INoticeRepo;
import com.chatmrkhoi.chatmrkhoi.repositories.IUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

@Service
public class NoticeUnfriend implements  INotice{

    @Autowired IInfoRepo INFO_REPO;
    @Autowired Common COMMON;
    @Autowired INoticeRepo NOTICE_REPO;
    @Autowired IUserRepo USER_REPO;
    @Autowired NoticeConvert convert;
    @Override
    public void Notice(Object data) {
        NoticeEn notice = new NoticeEn();
        FriendEn friendEn = (FriendEn) data;
        notice.setDate_create(new Date());
        notice.setTitleNotice("Friend");
        InfoEn userEn = INFO_REPO.findByIdUser(COMMON.getUserAuthentication().getId()).orElseThrow();

        UserEn userEnMaster = null;
        if(Objects.equals(friendEn.getUsersentities().getId(), COMMON.getUserAuthentication().getId())) {
            userEnMaster = USER_REPO.findById(friendEn.getIdfriend()).orElseThrow();
        } else  {
            userEnMaster = USER_REPO.findById(friendEn.getUsersentities().getId()).orElseThrow();
        }

        String describe = " Người dùng "+ userEn.getFullnames()+ " đã hãy bỏ bạn bè với bạn ";
        notice.setDescribeNotice(describe);
        notice.setTypeNotice("UnFriend");
        notice.setKeyNotice(COMMON.getUserAuthentication().getId().toString());
        notice.setUserEntity(userEnMaster);
        convert.notify(NOTICE_REPO.save(notice), userEnMaster.getId());
    }
}
