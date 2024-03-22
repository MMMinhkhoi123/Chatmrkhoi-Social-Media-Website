package com.chatmrkhoi.chatmrkhoi.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.chatmrkhoi.chatmrkhoi.entity.FileEn;

import jakarta.transaction.Transactional;

public interface IFileRepo extends JpaRepository<FileEn, Long> {
	@Modifying
	@Transactional
	@Query(value = "update file_en set mess_id = :value, status = true where id = :id", nativeQuery = true)
	void updateById(@Param(value = "value") Long idMess, @Param(value = "id") Long id);

	@Modifying
	@Transactional
	@Query(value = "DELETE FROM file_en WHERE mess_id = :id", nativeQuery = true)
	void deleteAllByIdMess(@Param(value = "id") Long id);

	@Query(value = "select * from file_en where mess_id = :id", nativeQuery = true)
	Optional<List<FileEn>> getAllByIdMess(@Param(value = "id") Long id);
}
