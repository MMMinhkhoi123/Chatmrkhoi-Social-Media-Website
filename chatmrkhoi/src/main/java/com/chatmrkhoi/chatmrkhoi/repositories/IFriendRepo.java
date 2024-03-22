package com.chatmrkhoi.chatmrkhoi.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.chatmrkhoi.chatmrkhoi.entity.FriendEn;

import jakarta.transaction.Transactional;

public interface IFriendRepo extends JpaRepository<FriendEn, Long> {

	@Query(value = "select * from  friend_en where friend_id = :id ", nativeQuery = true)
	Optional<List<FriendEn>> findAllByFriendId(@Param(value = "id") Long id);
	
	@Query(value = "select * from  friend_en where coderoom = :code ", nativeQuery = true)
	Optional<FriendEn> findByCodeRoom(@Param(value = "code") String code);
	
	@Query(value = "select * from  friend_en where friend_id = :friendId and user_id = :userId", nativeQuery = true)
	Optional<FriendEn> findByFriendIdAndUserId(@Param(value = "friendId") Long friendId, @Param(value = "userId") Long userid);

	@Modifying
	@Transactional
	@Query(value = "DELETE FROM friend_en WHERE user_id = :userId  and  friend_id = :fiendId ", nativeQuery = true)
	void deleteByUserIdAndFriendId(@Param(value = "userId") Long userId, @Param(value = "fiendId") Long friendId);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE friend_en SET status = 'friend' WHERE id = :id ", nativeQuery = true)
	void updateById(@Param(value = "id") Long id);
	
}
