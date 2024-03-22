package com.chatmrkhoi.chatmrkhoi.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.chatmrkhoi.chatmrkhoi.entity.UserEn;

import jakarta.transaction.Transactional;


public interface IUserRepo extends JpaRepository<UserEn, Long> {
	@Query(value = "select * from  user_en where gmails = :gmail ", nativeQuery = true)
	Optional<UserEn> findByEmail(@Param(value = "gmail") String gmail);

	@Modifying
	@Transactional
	@Query(value = "update user_en set passwords = :pass where gmails = :gmail ", nativeQuery = true)
	void updateByEmail(@Param(value = "gmail") String gmail, @Param(value = "pass") String pass);
	
	@Modifying
	@Transactional
	@Query(value = "update user_en set verify = true where gmails = :gmail ", nativeQuery = true)
	void updateByEmail(@Param(value = "gmail") String gmail);
}
