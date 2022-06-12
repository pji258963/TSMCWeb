package com.tsmc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tsmc.entity.WinnerListEntiry;


@Repository
@Transactional
public interface WinnerListRepository extends JpaRepository<WinnerListEntiry,Long>{
	
	@Query(value = "SELECT * FROM WINNER_LIST WHERE WINNER_DATE = ?1 AND MAIN_ID= ?2 ORDER BY ID ASC", nativeQuery = true)
	List<WinnerListEntiry> getWinnerListByDate(String today,String mainID);

	@Modifying
	@Query(value = "DELETE  FROM WINNER_LIST WHERE WINNER_DATE = ?1 ", nativeQuery = true)
	void deleteByDate(String format);

}
