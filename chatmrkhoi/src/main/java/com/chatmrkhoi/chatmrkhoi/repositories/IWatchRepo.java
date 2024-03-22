package com.chatmrkhoi.chatmrkhoi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.chatmrkhoi.chatmrkhoi.entity.WatchEn;

import jakarta.transaction.Transactional;

public interface IWatchRepo extends JpaRepository<WatchEn, Long> {
	@Modifying
	@Transactional
	@Query(value = "DELETE FROM watch_en WHERE mess_id = :id", nativeQuery = true)
	void deleteByIdMess(@Param(value = "id") Long idMess);
}
