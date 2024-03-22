package com.chatmrkhoi.chatmrkhoi.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.chatmrkhoi.chatmrkhoi.entity.MessageEn;

import jakarta.transaction.Transactional;

public interface IMessageRepo extends JpaRepository<MessageEn, Long>{
	
	@Query(value = "select * from message_en where friend_id = :id ", nativeQuery = true)
	Optional<List<MessageEn>> findAllByFriendId(@Param(value = "id") Long fiendId);
	
	@Query(value = "select * from message_en where group_id = :id " ,nativeQuery = true)
	Optional<List<MessageEn>> findAllByGroupId(@Param(value = "id") Long groupId);

	@Modifying
	@Transactional
	@Query(value = "update message_en set revokess = :type where id = :idMess ", nativeQuery = true)
	void UpdateByIdMess(@Param(value = "type") String type, @Param("idMess") Long idMess);

	
}
