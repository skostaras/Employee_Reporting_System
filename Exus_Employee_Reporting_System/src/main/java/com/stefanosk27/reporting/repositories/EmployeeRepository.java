package com.stefanosk27.reporting.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.stefanosk27.reporting.domain.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
	
//	Optional<Employee> findByUsername(String username);
	
	Page<Employee> findByUsername(String username, Pageable pageable);
	
	Optional<Employee> findByUsername(String username);

//	void save(Optional<Employee> editedEmployee);

//	void save(Optional<Employee> storedEmployee, Integer employeeId);
	

}
