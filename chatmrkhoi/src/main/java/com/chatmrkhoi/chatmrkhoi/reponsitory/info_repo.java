package com.chatmrkhoi.chatmrkhoi.reponsitory;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.chatmrkhoi.chatmrkhoi.entity.cakeinfo_entity;

public interface info_repo extends JpaRepository<cakeinfo_entity, Long> {

	
	@Query(value = "select * from cakeinfo_entity where idusers = :id ", nativeQuery = true)
	public Optional<cakeinfo_entity> findbyiduser(@Param(value = "id") Long iduser);
}
