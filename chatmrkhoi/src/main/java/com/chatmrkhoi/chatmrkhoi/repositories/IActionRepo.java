package com.chatmrkhoi.chatmrkhoi.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.chatmrkhoi.chatmrkhoi.entity.ActionEn;

public interface IActionRepo extends JpaRepository<ActionEn, Long> {

	@Query(value = "select * from action_en where id_user = :ids", nativeQuery = true)
	Optional<ActionEn> findByIdUser(@Param(value = "ids") Long id);
	
	@Query(value = "select * from action_en where sesstionid = :ids", nativeQuery = true)
	Optional<ActionEn> findBySesId(@Param(value = "ids") String id);
}
