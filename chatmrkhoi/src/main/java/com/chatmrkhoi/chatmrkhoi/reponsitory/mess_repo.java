package com.chatmrkhoi.chatmrkhoi.reponsitory;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.chatmrkhoi.chatmrkhoi.entity.Mess_entity;

import jakarta.transaction.Transactional;

public interface mess_repo  extends JpaRepository<Mess_entity, Long>{

	
	@Modifying
	@Transactional
	@Query(value = "update mess_entity set feel_file = :feel where id = :idmess ", nativeQuery = true)
	public void update_feelfile(@Param(value = "feel") String feel, Long idmess);
	
	@Query(value = "select * from mess_entity where friend_id = :id ", nativeQuery = true)
	public Optional<List<Mess_entity>> getmessfriend(@Param(value = "id") Long id);
	
	@Query(value = "select * from mess_entity where group_id = :id " ,nativeQuery = true)
	public Optional<List<Mess_entity>> getmessgroup(@Param(value = "id") Long id);
	
	@Modifying
	@Transactional
	@Query(value = "update mess_entity set revokess = :type where id = :idmess ", nativeQuery = true)
	public void unmess(@Param(value = "type") String type, Long idmess);

	
}
