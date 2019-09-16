package com.stefanosk27.reporting.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.stefanosk27.reporting.Priority;
import com.stefanosk27.reporting.domain.Employee;
import com.stefanosk27.reporting.domain.Report;

public interface ReportRepository extends JpaRepository<Report, Integer> {



	//must be ?1 not 0
//	@Query("SELECT r FROM Report r WHERE r.priority = :priority")
//	@Query(value = "SELECT * FROM Report WHERE priority = ?0", countQuery = "SELECT count(*) FROM Report WHERE priority = ?0", nativeQuery = true)
//	@Query(value = "SELECT u FROM Report u WHERE u.priority = ?0")
	@Query(value = "SELECT * FROM Report WHERE priority = ?1", nativeQuery = true)
	Report findByPriority(String priority);
	//	Page<Report> findByPriority(String priority);
//	Page<Report> findByPriority(String priority, Pageable pageable);

//	Page<Report> findByEmployeeId(Integer employeeId, Pageable pageable);

}
