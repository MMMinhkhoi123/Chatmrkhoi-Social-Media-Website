package com.chatmrkhoi.chatmrkhoi.reponsitory;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.chatmrkhoi.chatmrkhoi.entity.Jwt_entity;

public interface Jwt_repo extends JpaRepository<Jwt_entity, Long> {
	
	@Query(value = "select * from jwt_entity where id_user = :id" , nativeQuery = true)
	public Optional<Jwt_entity> findbyjwt(@Param("id") Long id);
}
