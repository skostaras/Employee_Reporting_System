package com.stefanosk27.reporting.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.stefanosk27.reporting.Priority;
import com.stefanosk27.reporting.domain.Employee;
import com.stefanosk27.reporting.domain.Report;

public interface ReportRepository extends JpaRepository<Report, Integer>{
	
	Page<Report> findByPriority(Priority priority, Pageable pageable);
	
	Page<Report> findByEmployeeId(Integer employeeId, Pageable pageable);

}
