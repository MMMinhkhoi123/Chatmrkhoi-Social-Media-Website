package com.chatmrkhoi.chatmrkhoi.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.chatmrkhoi.chatmrkhoi.entity.JwtEn;

public interface IJwtRepo extends JpaRepository<JwtEn, Long> {
	@Query(value = "select * from jwt_en where id_user = :id" , nativeQuery = true)
	Optional<JwtEn> findByUserId(@Param("id") Long userId);
}
