package com.chatmrkhoi.chatmrkhoi.design.strategy.notice;

import com.chatmrkhoi.chatmrkhoi.Data.Send.DataSendNotice;
import com.chatmrkhoi.chatmrkhoi.Util.convert.NoticeConvert;
import com.chatmrkhoi.chatmrkhoi.common.Common;
import com.chatmrkhoi.chatmrkhoi.entity.GroupEn;
import com.chatmrkhoi.chatmrkhoi.entity.InfoEn;
import com.chatmrkhoi.chatmrkhoi.entity.NoticeEn;
import com.chatmrkhoi.chatmrkhoi.entity.UserEn;
import com.chatmrkhoi.chatmrkhoi.repositories.IFriendRepo;
import com.chatmrkhoi.chatmrkhoi.repositories.IInfoRepo;
import com.chatmrkhoi.chatmrkhoi.repositories.INoticeRepo;
import com.chatmrkhoi.chatmrkhoi.service.impl.NoticeSer;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Getter
@Setter
public class NoticeAddGroup implements INotice {

    @Autowired IInfoRepo INFO_REPO;
    @Autowired Common COMMON;
    @Autowired INoticeRepo NOTICE_REPO;
    @Autowired NoticeConvert convert;


    @Override
    public void Notice(Object data) {
        GroupEn groupEn = (GroupEn) data;
        groupEn.getUsersentitiesx().forEach((item) -> {
            NoticeEn notice = new NoticeEn();
            notice.setDate_create(new Date());
            notice.setTitleNotice("Group");
            InfoEn infoUser = INFO_REPO.findByIdUser(COMMON.getUserAuthentication().getId()).orElseThrow();
            String describe =  "Người dùng " + infoUser.getFullnames()+" đã thêm bạn vào nhóm " + groupEn.getName() ;
            notice.setDescribeNotice(describe);
            notice.setTypeNotice("AddGroup");
            notice.setKeyNotice(groupEn.getId().toString());
            notice.setUserEntity(item);
            convert.notify(NOTICE_REPO.save(notice), item.getId());
        });
    }

}
