package com.chatmrkhoi.chatmrkhoi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.chatmrkhoi.chatmrkhoi.entity.FeelingEn;

import jakarta.transaction.Transactional;

public interface IFeelRepo extends JpaRepository<FeelingEn, Long> {

	@Modifying
	@Transactional
	@Query(value = "DELETE FROM feeling_en WHERE mess_id = :id and iduser = :idMaster and type = :types ", nativeQuery = true)
	void deleteByIdMessAndTypeAndIdMaster(@Param(value = "id") Long id, @Param(value = "idMaster") Long idMaster, @Param("types") String types);

	@Modifying
	@Transactional
	@Query(value = "DELETE FROM feeling_en WHERE mess_id = :id", nativeQuery = true)
	void deleteByIdMess(@Param(value = "id") Long id);
}
