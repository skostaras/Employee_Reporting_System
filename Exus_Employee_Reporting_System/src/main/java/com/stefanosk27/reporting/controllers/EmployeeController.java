package com.stefanosk27.reporting.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.stefanosk27.reporting.ErrorMessage;
import com.stefanosk27.reporting.ResourceNotFoundException;
import com.stefanosk27.reporting.domain.Employee;
import com.stefanosk27.reporting.repositories.EmployeeRepository;

@RestController
public class EmployeeController {

	@Autowired
	EmployeeRepository employeeRepository;

	@GetMapping(value = "/employees")
	public Page<Employee> all(Pageable pageable) {
		return employeeRepository.findAll(pageable);
	}

	@GetMapping(value = "/employees/{username}")
	public Page<Employee> findByUsername(@PathVariable String username, Pageable pageable) {
		// TODO why doesn;t work?
		// () -> new ResourceNotFoundException("Can't find an employee with the
		// username: " + username + ".")
		return employeeRepository.findByUsername(username, pageable);
	}

	@PostMapping(value = "/employees")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Employee save(@RequestBody Employee employee) {
		return employeeRepository.save(employee);
	}

	@PutMapping(value = "/employees/{employeeId}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable Integer employeeId,
			@RequestBody Employee newEmployee) {

		return employeeRepository.findById(employeeId).map(employee -> {
			employee.setEmail(newEmployee.getEmail());
			employee.setFirstName(newEmployee.getFirstName());
			employee.setGender(newEmployee.getGender());
			employee.setLastName(newEmployee.getLastName());
			employee.setTitle(newEmployee.getTitle());
			employee.setUsername(newEmployee.getUsername());
			return ResponseEntity.ok(employee);
		}).orElseThrow(() -> new ResourceNotFoundException(ErrorMessage.EMPLOYEE_NOT_FOUND.getValue() + employeeId));

	}

	@DeleteMapping(value = "/employees/{employeeId}")
	public ResponseEntity<?> deleteEmployee(@PathVariable Integer employeeId) {

		return employeeRepository.findById(employeeId).map(employee -> {
			employeeRepository.delete(employee);
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new ResourceNotFoundException(ErrorMessage.EMPLOYEE_NOT_FOUND.getValue() + employeeId));

	}

}
