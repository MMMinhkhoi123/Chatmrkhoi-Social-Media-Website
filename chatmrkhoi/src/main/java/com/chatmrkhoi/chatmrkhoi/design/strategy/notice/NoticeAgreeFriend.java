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
public class NoticeAgreeFriend implements INotice{
    @Autowired
    IInfoRepo INFO_REPO;
    @Autowired
    Common COMMON;
    @Autowired
    INoticeRepo NOTICE_REPO;
    @Autowired
    IUserRepo USER_REPO;
    @Autowired
    NoticeConvert convert;


        @Override
    public void Notice(Object data) {
            NoticeEn notice = new NoticeEn();
            FriendEn friendEn = (FriendEn) data;
            InfoEn userEn = INFO_REPO.findByIdUser(COMMON.getUserAuthentication().getId()).orElseThrow();
            String describe =  "Người dùng "+userEn.getFullnames() +" đã đồng ý kết bạn với yêu cầu của bạn";
            notice.setDescribeNotice(describe);
            notice.setTypeNotice("Agree");
            notice.setTitleNotice("Everyone");
            notice.setDate_create(new Date());
            notice.setKeyNotice(friendEn.getIdfriend().toString());
            notice.setUserEntity(friendEn.getUsersentities());
            convert.notify(NOTICE_REPO.save(notice),friendEn.getUsersentities().getId());
    }
}
