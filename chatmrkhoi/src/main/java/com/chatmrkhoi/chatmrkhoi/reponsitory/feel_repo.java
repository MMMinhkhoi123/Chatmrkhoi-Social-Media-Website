package com.chatmrkhoi.chatmrkhoi.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.chatmrkhoi.chatmrkhoi.entity.feel_entity;

import jakarta.transaction.Transactional;

public interface feel_repo extends JpaRepository<feel_entity, Long> {

	
	@Modifying
	@Transactional
	@Query(value = "DELETE FROM feel_entity WHERE mess_id = :id and iduser = :idmaster and type = :types ", nativeQuery = true)
	public void deletefeel(@Param(value = "id") Long id,@Param(value = "idmaster") Long iddmaster, @Param("types") String types);
	
	
	@Modifying
	@Transactional
	@Query(value = "DELETE FROM feel_entity WHERE mess_id = :id", nativeQuery = true)
	public void deletefeelall(@Param(value = "id") Long id);
}
