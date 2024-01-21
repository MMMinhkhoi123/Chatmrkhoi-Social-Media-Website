package com.chatmrkhoi.chatmrkhoi.reponsitory;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.chatmrkhoi.chatmrkhoi.entity.Friend_entity;

import jakarta.transaction.Transactional;

public interface Friend_repo extends JpaRepository<Friend_entity, Long> {

	
	@Query(value = "select * from  friend_entity where friend_id = :id ", nativeQuery = true)
	public Optional<List<Friend_entity>> findallbyiduser(@Param(value = "id") Long id);
	
	@Query(value = "select * from  friend_entity where coderoom = :code ", nativeQuery = true)
	public Optional<Friend_entity> findallbycode(@Param(value = "code") String code);
	
	@Query(value = "select * from  friend_entity where friend_id = :friendid and user_id = :userids", nativeQuery = true)
	public Optional<Friend_entity> findbydoubleid(@Param(value = "friendid") Long friendid, @Param(value = "userids") Long userid);
	
	@Modifying
	@Transactional
	@Query(value = "DELETE FROM friend_entity WHERE user_id = :idused  and  friend_id = :idfriend ", nativeQuery = true)
	public void destroy(@Param(value = "idused") Long id, @Param(value = "idfriend") Long idfriend);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE friend_entity SET status = 'friend' WHERE id = :idx ", nativeQuery = true)
	public void apply(@Param(value = "idx") Long id);
	
}
