package com.chatmrkhoi.chatmrkhoi.repositories;

import com.chatmrkhoi.chatmrkhoi.entity.NoticeEn;
import org.springframework.data.jpa.repository.JpaRepository;

public interface INoticeRepo extends JpaRepository<NoticeEn,Long> {
}
