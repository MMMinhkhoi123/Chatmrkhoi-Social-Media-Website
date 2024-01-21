package com.chatmrkhoi.chatmrkhoi.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.chatmrkhoi.chatmrkhoi.entity.revoke_entity;

import jakarta.transaction.Transactional;

public interface revoke_repo extends JpaRepository<revoke_entity, Long> {
	@Modifying
	@Transactional
	@Query(value = "DELETE FROM revoke_entity WHERE mess_id = :id", nativeQuery = true)
	public void deleterevokeeall(@Param(value = "id") Long id);
	
}
