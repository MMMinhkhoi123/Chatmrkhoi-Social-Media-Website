package com.chatmrkhoi.chatmrkhoi.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.chatmrkhoi.chatmrkhoi.entity.GroupEn;

public interface IGroupRepo extends JpaRepository<GroupEn, Long> {
	
	@Query(value = "select * from  group_en where coderoom = :code ", nativeQuery = true)
	Optional<GroupEn> findByCodeRoom(@Param(value = "code") String code);
}
