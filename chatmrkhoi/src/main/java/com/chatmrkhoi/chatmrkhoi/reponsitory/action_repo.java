package com.chatmrkhoi.chatmrkhoi.reponsitory;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.chatmrkhoi.chatmrkhoi.entity.action_entity;

public interface action_repo extends JpaRepository<action_entity, Long> {

	@Query(value = "select * from action_entity where id_user = :ids", nativeQuery = true)
	public  Optional<action_entity> findbyiduser(@Param(value = "ids") Long id);
	
	@Query(value = "select * from action_entity where sesstionid = :ids", nativeQuery = true)
	public  Optional<action_entity> findbyidss(@Param(value = "ids") String id);
}
