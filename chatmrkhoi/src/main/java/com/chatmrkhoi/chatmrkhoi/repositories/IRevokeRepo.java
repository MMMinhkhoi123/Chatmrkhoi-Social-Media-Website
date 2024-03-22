package com.chatmrkhoi.chatmrkhoi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.chatmrkhoi.chatmrkhoi.entity.RevokeEn;

import jakarta.transaction.Transactional;

public interface IRevokeRepo extends JpaRepository<RevokeEn, Long> {
	@Modifying
	@Transactional
	@Query(value = "DELETE FROM revoke_en WHERE mess_id = :id", nativeQuery = true)
	void deleteByIdMess(@Param(value = "id") Long idMess);
}
