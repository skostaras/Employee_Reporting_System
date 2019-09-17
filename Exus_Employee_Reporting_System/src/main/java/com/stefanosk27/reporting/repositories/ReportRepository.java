package com.stefanosk27.reporting.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.stefanosk27.reporting.domain.Report;

public interface ReportRepository extends JpaRepository<Report, Integer> {

	@Query(value = "SELECT * FROM Report WHERE priority = ?1", countQuery = "SELECT count(*) FROM Report WHERE priority = ?1", nativeQuery = true)
	Page<Report> findByPriority(String priority, Pageable pageable);

	@Query(value = "SELECT * FROM Report WHERE username = ?1", countQuery = "SELECT count(*) FROM Report WHERE username = ?1", nativeQuery = true)
	Page<Report> findByEmployeeUsername(String username, Pageable pageable);

}
