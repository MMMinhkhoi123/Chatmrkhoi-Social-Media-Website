package com.chatmrkhoi.chatmrkhoi.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.chatmrkhoi.chatmrkhoi.entity.InfoEn;

public interface IInfoRepo extends JpaRepository<InfoEn, Long> {
	@Query(value = "select * from info_en where idusers = :id ", nativeQuery = true)
	Optional<InfoEn> findByIdUser(@Param(value = "id") Long id);
}
