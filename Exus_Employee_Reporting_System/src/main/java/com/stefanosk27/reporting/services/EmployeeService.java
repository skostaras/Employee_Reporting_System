package com.stefanosk27.reporting.services;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stefanosk27.reporting.Gender;
import com.stefanosk27.reporting.domain.Employee;
import com.stefanosk27.reporting.repositories.EmployeeRepository;

@Service
public class EmployeeService {

	private final Logger logger = LoggerFactory.getLogger(EmployeeService.class);

	@Autowired
	private EmployeeRepository employeeRepository;

	public Optional<Employee> updateEmployee(Employee employee) {

		return Optional.of(employeeRepository.findById(employee.getEmployeeId())).filter(Optional::isPresent)
				.map(Optional::get).map(editedEmployee -> {
					editedEmployee.setFirstName(employee.getFirstName());
					editedEmployee.setLastName(employee.getLastName());
					editedEmployee.setUsername(employee.getUsername());
					editedEmployee.setEmail(employee.getEmail());
					editedEmployee.setGender(Gender.getEnumFromValue(employee.getGender()));
					editedEmployee.setTitle(employee.getTitle());
					return editedEmployee;
				}).map(Employee::new);
	}

}
