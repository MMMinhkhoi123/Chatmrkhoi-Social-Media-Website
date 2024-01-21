package com.chatmrkhoi.chatmrkhoi.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.chatmrkhoi.chatmrkhoi.entity.watch_entity;

import jakarta.transaction.Transactional;

public interface Watch_repo extends JpaRepository<watch_entity, Long> {
	@Modifying
	@Transactional
	@Query(value = "DELETE FROM watch_entity WHERE mess_id = :id", nativeQuery = true)
	public void deleteallwatch(@Param(value = "id") Long id);
}
