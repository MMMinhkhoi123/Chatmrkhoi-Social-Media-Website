package com.chatmrkhoi.chatmrkhoi.Util.convert;


import com.chatmrkhoi.chatmrkhoi.Data.Send.DataSendNotice;
import com.chatmrkhoi.chatmrkhoi.entity.NoticeEn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class NoticeConvert {
    @Autowired SimpMessagingTemplate template;
    public void notify(NoticeEn noticeEn, Long idUser) {
        template.convertAndSend("/user/notice/" + idUser, convert(noticeEn));
    }

    public  DataSendNotice convert(NoticeEn noticeEn) {
        return DataSendNotice.builder()
                .Date(noticeEn.getDate_create().getTime())
                .describe(noticeEn.getDescribeNotice())
                .key(noticeEn.getKeyNotice())
                .type(noticeEn.getTypeNotice())
                .title(noticeEn.getTitleNotice())
                .status(noticeEn.isStatusNotice())
                .id(noticeEn.getId())
                .build();
    }
}
