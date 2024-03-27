package com.chatmrkhoi.chatmrkhoi.design.strategy.notice;

import com.chatmrkhoi.chatmrkhoi.Util.convert.NoticeConvert;
import com.chatmrkhoi.chatmrkhoi.common.Common;
import com.chatmrkhoi.chatmrkhoi.entity.GroupEn;
import com.chatmrkhoi.chatmrkhoi.entity.InfoEn;
import com.chatmrkhoi.chatmrkhoi.entity.NoticeEn;
import com.chatmrkhoi.chatmrkhoi.entity.UserEn;
import com.chatmrkhoi.chatmrkhoi.repositories.IInfoRepo;
import com.chatmrkhoi.chatmrkhoi.repositories.INoticeRepo;
import com.chatmrkhoi.chatmrkhoi.service.impl.NoticeSer;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Setter
public class NoticeKickMember implements  INotice {

    @Autowired IInfoRepo INFO_REPO;

    @Autowired Common COMMON;

    @Autowired INoticeRepo NOTICE_REPO;
    @Autowired NoticeConvert convert;

    private UserEn userKick;
    @Override
    public void Notice(Object data) {
        GroupEn groupEn = (GroupEn) data;
        NoticeEn notice = new NoticeEn();
        notice.setDate_create(new Date());
        notice.setTitleNotice("Group");
        InfoEn infoUser = INFO_REPO.findByIdUser(COMMON.getUserAuthentication().getId()).orElseThrow();
        String describe =  " Người dùng "+ infoUser.getFullnames()+" đã buộc bạn rời khỏi nhóm " + groupEn.getName();
        notice.setDescribeNotice(describe);
        notice.setTypeNotice("KickMemberGroup");
        notice.setKeyNotice(groupEn.getId().toString());
        notice.setUserEntity(userKick);
        convert.notify(NOTICE_REPO.save(notice), userKick.getId());
    }
}