package com.chatmrkhoi.chatmrkhoi.reponsitory;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.chatmrkhoi.chatmrkhoi.entity.verifycode_entity;

public interface verifycode_repo extends JpaRepository<verifycode_entity, Long> {

//	@Modifying
//	@Transactional
	@Query(value = "select * from  verifycode_entity where gmail = :gmail ", nativeQuery = true)
	public Optional<verifycode_entity> findbygmail(@Param(value = "gmail") String gmail);
	
}
