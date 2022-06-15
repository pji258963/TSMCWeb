package com.tsmc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tsmc.entity.MainForWinnerEntity;



@Repository
@Transactional
public interface MainRepository extends JpaRepository<MainForWinnerEntity,Long>{
	
	@Modifying
	@Query(value = "UPDATE  MAIN_FOR_WINNER SET TOTAL_SET=?1 WHERE WINNG_DATE = ?2 ", nativeQuery = true)
	void upDateByDate(String totalSet, String today);
	
	@Modifying
	@Query(value = "UPDATE  MAIN_FOR_WINNER SET TOTAL_SET=?1 , CURRENT_PEOPLE=?2  WHERE WINNG_DATE = ?3 AND ID= (SELECT MAX(ID) FROM  MAIN_FOR_WINNER  WHERE WINNG_DATE = ?3)", nativeQuery = true)
	void updatePeopleAndCurrentPoppleByDate(String totalSet, String currentPoeple,String today);
	
	@Query(value = "SELECT * FROM  MAIN_FOR_WINNER  WHERE WINNG_DATE = ?1  AND ID= (SELECT MAX(ID) FROM  MAIN_FOR_WINNER  WHERE WINNG_DATE = ?1) ", nativeQuery = true)
	MainForWinnerEntity findByDate(String today);

	@Query(value = "SELECT * FROM  MAIN_FOR_WINNER  WHERE WINNG_DATE = ?1 ORDER BY ID ASC", nativeQuery = true)
	List<MainForWinnerEntity> findTodyMainID(String formatToday);

}
