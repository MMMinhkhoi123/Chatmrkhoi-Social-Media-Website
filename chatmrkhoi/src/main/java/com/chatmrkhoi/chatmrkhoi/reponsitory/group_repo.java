package com.chatmrkhoi.chatmrkhoi.reponsitory;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.chatmrkhoi.chatmrkhoi.entity.Group_entity;

public interface group_repo extends JpaRepository<Group_entity, Long> {
	
	@Query(value = "select * from  group_entity where coderoom = :code ", nativeQuery = true)
	public Optional<Group_entity> findallbycode(@Param(value = "code") String code);
}
