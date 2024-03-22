package com.chatmrkhoi.chatmrkhoi.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.chatmrkhoi.chatmrkhoi.entity.CodeEn;

public interface ICodeRepo extends JpaRepository<CodeEn, Long> {
	@Query(value = "select * from  code_en where gmail = :gmail ", nativeQuery = true)
	Optional<CodeEn> findByEmail(@Param(value = "gmail") String gmail);
	
}
