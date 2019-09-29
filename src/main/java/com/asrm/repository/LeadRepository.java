package com.asrm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.asrm.model.Emp;
import com.asrm.model.Lead;

public interface LeadRepository extends JpaRepository<Lead, Long> {
	 @Query(value ="SELECT *  FROM lead  WHERE YEAR(update_time) = YEAR(CURRENT_DATE()) AND  MONTH(update_time) = MONTH(CURRENT_DATE()) AND lead_status='DN' AND emp_id= ?1 ", nativeQuery = true)
	List<Lead> monthclose(String empid);
	 
	 @Query(value="SELECT * FROM lead WHERE  DATE_FORMAT(follow_date, '%Y-%m-%d')  = CURDATE() AND  emp_id= ?1",nativeQuery = true)
	 List<Lead> todaylead(String empid);
	 
	 @Query(value ="SELECT *  FROM lead  WHERE YEAR(update_time) = YEAR(CURRENT_DATE()) AND  MONTH(update_time) = MONTH(CURRENT_DATE()) AND emp_id= ?1 ORDER BY (CASE WHEN `lead_status` = 'DN' THEN 1 ELSE 0 END)", nativeQuery = true)
	 List<Lead> expect_Rev(String empid);
	 
	 @Query(value ="SELECT *  FROM lead  WHERE DATE(update_time) BETWEEN  ?1 AND  ?2 AND emp_id= ?3 ", nativeQuery = true)
	 List<Lead> exportCsv(String sdate,String edate,String empid);
	 
	 @Query(value ="SELECT *  FROM lead  WHERE id=?1 ", nativeQuery = true)
	 Lead findByLid(Long id);
	 
	 @Query(value="SELECT * FROM lead WHERE  follow_date BETWEEN DATE_ADD(CURDATE(),INTERVAL 1 DAY) AND DATE_ADD(follow_date, INTERVAL 7 DAY) AND  emp_id=?", nativeQuery = true)
	 List<Lead> featureData(String id);
}