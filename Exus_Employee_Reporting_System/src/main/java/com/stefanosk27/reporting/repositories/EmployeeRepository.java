package com.stefanosk27.reporting.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.stefanosk27.reporting.domain.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
	
	Page<Employee> findByUsername(String username, Pageable pageable);
	

}
