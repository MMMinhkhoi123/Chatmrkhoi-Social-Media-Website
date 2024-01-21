package com.chatmrkhoi.chatmrkhoi.reponsitory;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.chatmrkhoi.chatmrkhoi.entity.Users_entity;

import jakarta.transaction.Transactional;


public interface User_repo extends JpaRepository<Users_entity, Long> {
//	@Modifying
//	@Transactional
	@Query(value = "select * from  users_entity where gmails = :gmail ", nativeQuery = true)
	public Optional<Users_entity> findbygmail(@Param(value = "gmail") String gmail);
	
	@Query(value = "select * from  users_entity where gmails = :gmail ", nativeQuery = true)
	public Optional<List<Users_entity>> findallbygmail(@Param(value = "gmail") String gmail);
	
	@Modifying
	@Transactional
	@Query(value = "update users_entity set passwords = :pass where gmails = :gmail ", nativeQuery = true)
	public void update_ref(@Param(value = "gmail") String gmail,@Param(value = "pass") String pass);
	
	@Modifying
	@Transactional
	@Query(value = "update users_entity set verify = true where gmails = :gmail ", nativeQuery = true)
	public void update_verify(@Param(value = "gmail") String gmail);
}
