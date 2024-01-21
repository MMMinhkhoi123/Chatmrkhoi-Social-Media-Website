package com.chatmrkhoi.chatmrkhoi.reponsitory;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.chatmrkhoi.chatmrkhoi.entity.file_entity;

import jakarta.transaction.Transactional;

public interface file_repo extends JpaRepository<file_entity, Long> {
	@Modifying
	@Transactional
	@Query(value = "update file_entity set mess_id = :value, status = true where id = :id", nativeQuery = true)
	public void update_file(@Param(value = "id") Long id, @Param(value = "value") Long idmess);
	
	
	@Modifying
	@Transactional
	@Query(value = "DELETE FROM file_entity WHERE mess_id = :id", nativeQuery = true)
	public void deletefileall(@Param(value = "id") Long id);
	
	
	
	@Query(value = "select * from file_entity where mess_id = :id", nativeQuery = true)
	public Optional<List<file_entity>> getallimg(@Param(value = "id") Long id);

	
	
	
}
