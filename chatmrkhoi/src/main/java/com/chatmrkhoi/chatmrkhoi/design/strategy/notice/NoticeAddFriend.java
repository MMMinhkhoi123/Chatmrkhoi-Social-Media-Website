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
import com.chatmrkhoi.chatmrkhoi.service.impl.NoticeSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class NoticeAddFriend implements INotice{
    @Autowired IInfoRepo INFO_REPO;
    @Autowired Common COMMON;
    @Autowired INoticeRepo NOTICE_REPO;
    @Autowired IUserRepo USER_REPO;
    @Autowired NoticeConvert convert;
    @Override
    public void Notice(Object data) {
        NoticeEn notice = new NoticeEn();
        FriendEn friendEn = (FriendEn) data;
        InfoEn userEn = INFO_REPO.findByIdUser(friendEn.getUsersentities().getId()).orElseThrow();
        UserEn userEnMaster = USER_REPO.findById(friendEn.getIdfriend()).orElseThrow();
        String describe = "Người dùng " + userEn.getFullnames()  + " vừa gửi cho bạn một yêu cầu kết bạn";
        notice.setDescribeNotice(describe);
        notice.setTitleNotice("Everyone");
        notice.setTypeNotice("AddFriend");
        notice.setKeyNotice(userEnMaster.getId().toString());
        notice.setUserEntity(userEnMaster);
        notice.setDate_create(new Date());
        convert.notify(NOTICE_REPO.save(notice), userEnMaster.getId());
    }
}
