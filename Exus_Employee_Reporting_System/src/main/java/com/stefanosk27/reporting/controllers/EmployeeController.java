package com.stefanosk27.reporting.controllers;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
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
import com.stefanosk27.reporting.Gender;
import com.stefanosk27.reporting.ResourceNotFoundException;
import com.stefanosk27.reporting.domain.Employee;
import com.stefanosk27.reporting.repositories.EmployeeRepository;
import com.stefanosk27.reporting.services.EmployeeService;

@RestController
public class EmployeeController {

	@Autowired
	EmployeeRepository employeeRepository;
	

    @Autowired
    private EmployeeService employeeService;

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

//	@PutMapping(value = "/employees/{employeeId}")
//	public ResponseEntity<?> updateEmployee(@PathVariable Integer employeeId, @RequestBody Employee employee) {
//		employee.setEmployeeId(employeeId);
//		Optional<Employee> editedEmployee = employeeService.updateEmployee(employee);
//		editedEmployee.get().setEmployeeId(employeeId);
//		employeeRepository.save(editedEmployee);
//		return ResponseEntity.ok().body(editedEmployee);
//	}

//	private static void copyNonNullProperties(Object src, Object target) {
//		BeanUtils.copyProperties(src, target, getNullPropertyNames(src));
//	}
//
//	private static String[] getNullPropertyNames(Object source) {
//		final BeanWrapper src = new BeanWrapperImpl(source);
//		java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();
//
//		Set<String> emptyNames = new HashSet<String>();
//		for (java.beans.PropertyDescriptor pd : pds) {
//			Object srcValue = src.getPropertyValue(pd.getName());
//			if (srcValue == null)
//				emptyNames.add(pd.getName());
//		}
//		String[] result = new String[emptyNames.size()];
//		return emptyNames.toArray(result);
//	}

	@PutMapping(value = "/employees/{employeeId}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable Integer employeeId,
			@RequestBody Employee employee) {

		return employeeRepository.findById(employeeId).map(editedEmployee -> {
			editedEmployee.setEmail(employee.getEmail());
			editedEmployee.setFirstName(employee.getFirstName());
			editedEmployee.setGender(Gender.getEnumFromValue(employee.getGender()));
			editedEmployee.setLastName(employee.getLastName());
			editedEmployee.setTitle(employee.getTitle());
			editedEmployee.setUsername(employee.getUsername());
			employeeRepository.save(editedEmployee);
			return ResponseEntity.ok(editedEmployee);
		}).orElseThrow(() -> new ResourceNotFoundException(ErrorMessage.EMPLOYEE_NOT_FOUND.getValue() + employeeId));

	}
	

	
//	@PutMapping(value = "/employees/{employeeId}")
//	public ResponseEntity<?> updateEmployee(@PathVariable Integer employeeId, @RequestBody Employee employee) {
//		employee.setEmployeeId(employeeId);
//		Optional<Employee> editedEmployee = employeeService.updateEmployee(employee);
//		editedEmployee.get().setEmployeeId(employeeId);
//		employeeRepository.save(editedEmployee);
//		return ResponseEntity.ok().body(editedEmployee);
//	}

	@DeleteMapping(value = "/employees/{employeeId}")
	public ResponseEntity<?> deleteEmployee(@PathVariable Integer employeeId) {
		return employeeRepository.findById(employeeId).map(employee -> {
			employeeRepository.delete(employee);
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new ResourceNotFoundException(ErrorMessage.EMPLOYEE_NOT_FOUND.getValue() + employeeId));

	}

}
