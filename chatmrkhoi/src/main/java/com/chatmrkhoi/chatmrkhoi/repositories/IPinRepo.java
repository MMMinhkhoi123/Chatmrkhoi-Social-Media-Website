package com.chatmrkhoi.chatmrkhoi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.chatmrkhoi.chatmrkhoi.entity.PinEn;

import jakarta.transaction.Transactional;

public interface IPinRepo extends JpaRepository<PinEn, Long> {
	@Modifying
	@Transactional
	@Query(value = "DELETE FROM pin_en WHERE mess_id = :id", nativeQuery = true)
	void deleteByIdMess(@Param(value = "id") Long idMess);
}
