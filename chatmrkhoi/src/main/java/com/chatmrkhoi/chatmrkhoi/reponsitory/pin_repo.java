package com.chatmrkhoi.chatmrkhoi.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.chatmrkhoi.chatmrkhoi.entity.pin_entity;

import jakarta.transaction.Transactional;

import java.util.Optional;

public interface pin_repo extends JpaRepository<pin_entity, Long> {


	@Modifying
	@Transactional
	@Query(value = "DELETE FROM pin_entity WHERE mess_id = :id", nativeQuery = true)
	public void deletepinall(@Param(value = "id") Long id);


	
	
}
