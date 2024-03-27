package com.chatmrkhoi.chatmrkhoi.service.impl;

import com.chatmrkhoi.chatmrkhoi.Data.Send.DataSendNotice;
import com.chatmrkhoi.chatmrkhoi.Util.convert.NoticeConvert;
import com.chatmrkhoi.chatmrkhoi.common.Common;
import com.chatmrkhoi.chatmrkhoi.design.strategy.notice.*;
import com.chatmrkhoi.chatmrkhoi.entity.*;
import com.chatmrkhoi.chatmrkhoi.repositories.IInfoRepo;
import com.chatmrkhoi.chatmrkhoi.repositories.INoticeRepo;
import com.chatmrkhoi.chatmrkhoi.repositories.IUserRepo;
import com.chatmrkhoi.chatmrkhoi.service.INotice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class NoticeSer implements INotice {

    @Autowired INoticeRepo NOTICE_REPO;
    @Autowired Common COMMON;
    @Autowired IInfoRepo FRIEND_REPO;
    @Autowired IUserRepo USER_REPO;

    @Autowired NoticeAddFriend noticeAddFriend;
    @Autowired NoticeAgreeFriend noticeAgreeFriend;
    @Autowired NoticeAddGroup noticeAddGroup;
    @Autowired NoticeRemoveGroup noticeRemoveGroup;
    @Autowired NoticeAddMember noticeAddMember;
    @Autowired NoticeKickMember noticeKickMember;
    @Autowired NoticeUnfriend noticeUnfriend;
    @Autowired NoticeConvert noticeConvert;

    @Override
    public void save(String type, Object data, String key, Object userHire) {
        ContextNotice context = null;
        switch (type) {
            case "Everyone" -> {
                switch (key) {
                    case "AddFriend" -> {
                        context = new ContextNotice(noticeAddFriend);
                    }
                    case "Agree" -> {
                        context = new ContextNotice(noticeAgreeFriend);
                    }
                }
            }
            case "Group" -> {
                switch (key) {
                    case "AddGroup" -> {
                        context = new ContextNotice(noticeAddGroup);
                    }
                    case "RemoveGroup" -> {
                        context = new ContextNotice(noticeRemoveGroup);
                    }
                    case "AddMember" -> {
                        noticeAddMember.setUserApp((UserEn)userHire);
                        context = new ContextNotice(noticeAddMember);
                    }
                    case "KickMember" -> {
                        noticeKickMember.setUserKick((UserEn)userHire);
                        context = new ContextNotice(noticeKickMember);
                    }
                };
            }
            case "Private" -> {
                context = new ContextNotice(noticeUnfriend);
            }
            default -> {}
        }
        context.Notice(data);
    }


    
    @Override
    public ResponseEntity<List<DataSendNotice>> getAllList() {
        List<DataSendNotice> listResult = new ArrayList<>();
        COMMON.getUserAuthentication().getNotification().forEach((item) -> {
            listResult.add(noticeConvert.convert(item));
        });
        return ResponseEntity.ok(listResult);
    }

    @Override
    public void delete(Long id) {
        NOTICE_REPO.deleteById(id);
    }

    @Override
    public void updateView(Long id) {
        NoticeEn notice = NOTICE_REPO.findById(id).orElseThrow();
        notice.setStatusNotice(true);
        NOTICE_REPO.save(notice);
    }
}
