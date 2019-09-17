package com.stefanosk27.reporting.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.stefanosk27.reporting.customExceptions.ResourceNotFoundException;
import com.stefanosk27.reporting.customExceptions.StorageFailedException;
import com.stefanosk27.reporting.domain.Employee;
import com.stefanosk27.reporting.enums.ErrorMessage;
import com.stefanosk27.reporting.enums.Gender;
import com.stefanosk27.reporting.repositories.EmployeeRepository;

@RestController
public class EmployeeController {

	@Autowired
	EmployeeRepository employeeRepository;

	@GetMapping(value = "/employees/all")
	public Page<Employee> all(Pageable pageable) {

		Page<Employee> results = employeeRepository.findAll(pageable);
		if (results.isEmpty())
			throw new ResourceNotFoundException(ErrorMessage.EMPLOYEES_NOT_FOUND.getValue());

		return employeeRepository.findAll(pageable);
	}

	@GetMapping(value = "/employees")
	@ResponseBody
	public Page<Employee> findByUsername(@RequestParam String username, Pageable pageable) {

		Page<Employee> results = employeeRepository.findByUsername(username, pageable);
		if (results.isEmpty())
			throw new ResourceNotFoundException(ErrorMessage.EMPLOYEE_NOT_FOUND.getValue() + username);

		return employeeRepository.findByUsername(username, pageable);
	}

	@PostMapping(value = "/employees")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Employee save(@RequestBody Employee employee) {

		Optional<Employee> searchResults = employeeRepository.findByUsername(employee.getUsername());
		if (searchResults.isPresent())
			throw new StorageFailedException(employee.getUsername() + ErrorMessage.USERNAME_EXISTS.getValue());

		return employeeRepository.save(employee);
	}

	@PutMapping(value = "/employees")
	@ResponseBody
	public ResponseEntity<Employee> updateEmployee(@RequestParam String username,
			@RequestBody Employee employeeFromRequest) {
		return employeeRepository.findByUsername(username).map(editedEmployee -> {
			editedEmployee.setEmail(employeeFromRequest.getEmail());
			editedEmployee.setFirstName(employeeFromRequest.getFirstName());
			editedEmployee.setGender(Gender.getEnumFromValue(employeeFromRequest.getGender()));
			editedEmployee.setLastName(employeeFromRequest.getLastName());
			editedEmployee.setTitle(employeeFromRequest.getTitle());
			editedEmployee.setUsername(employeeFromRequest.getUsername());
			employeeRepository.save(editedEmployee);
			return ResponseEntity.ok(editedEmployee);
		}).orElseThrow(() -> new ResourceNotFoundException(ErrorMessage.EMPLOYEE_NOT_FOUND.getValue() + username));
	}

	@DeleteMapping(value = "/employees")
	public ResponseEntity<?> deleteEmployee(@RequestParam String username) {
		return employeeRepository.findByUsername(username).map(employee -> {
			employeeRepository.delete(employee);
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new ResourceNotFoundException(ErrorMessage.EMPLOYEE_NOT_FOUND.getValue() + username));
	}

}
