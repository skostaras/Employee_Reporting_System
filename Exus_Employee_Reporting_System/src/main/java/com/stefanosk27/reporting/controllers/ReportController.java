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
import com.stefanosk27.reporting.Priority;
import com.stefanosk27.reporting.ResourceNotFoundException;
import com.stefanosk27.reporting.domain.Employee;
import com.stefanosk27.reporting.domain.Report;
import com.stefanosk27.reporting.repositories.EmployeeRepository;
import com.stefanosk27.reporting.repositories.ReportRepository;

//import com.stefanosk27.reporting.ErrorMessage;
//import com.stefanosk27.reporting.Priority;
//import com.stefanosk27.reporting.ResourceNotFoundException;
//import com.stefanosk27.reporting.domain.Employee;
//import com.stefanosk27.reporting.domain.Report;
//import com.stefanosk27.reporting.repositories.EmployeeRepository;
//import com.stefanosk27.reporting.repositories.ReportRepository;

@RestController
public class ReportController {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private ReportRepository reportRepository;

//	@GetMapping(value = "/employees/{employeeId}/reports")
//	public Page<Report> findByEmployeeId(@PathVariable Integer employeeId, Pageable pageable) {
//		return reportRepository.findByEmployeeId(employeeId, pageable);
//	}

	@GetMapping(value = "/employees/reports/{priority}")
	public Report findByPriority(@PathVariable String priority) {
		if(priority.toLowerCase().equals("low")){
			priority = "Low";
		}
		if(priority.toLowerCase().equals("high")){
			priority = "High";
		}
//		public Page<Report> findByPriority(@PathVariable String priority, Pageable pageable) {
//		return reportRepository.findByPriority(Priority.getEnumFromValue(priority), pageable);
//		return reportRepository.findByPriority(priority, pageable);
		return reportRepository.findByPriority(priority);
	}

	@PostMapping(value = "/employees/{employeeId}/reports")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Report save(@PathVariable Integer employeeId, @RequestBody Report report) {
		return employeeRepository.findById(employeeId).map(employee -> {
			report.setEmployee(employee);
			return reportRepository.save(report);
		}).orElseThrow(() -> new ResourceNotFoundException(ErrorMessage.EMPLOYEE_NOT_FOUND.getValue() + employeeId));

	}

	@PutMapping(value = "/employees/{employeeId}/reports/{reportId}")
	public ResponseEntity<Report> updateReport(@PathVariable Integer employeeId, @PathVariable Integer reportId,
			@RequestBody Report newReport) {

		Employee employee = employeeRepository.findById(employeeId).orElseThrow(
				() -> new ResourceNotFoundException(ErrorMessage.EMPLOYEE_NOT_FOUND.getValue() + employeeId));

		return employeeRepository.findById(reportId).map(report -> {
			newReport.setEmployee(employee);
			reportRepository.save(newReport);
			return ResponseEntity.ok(newReport);
		}).orElseThrow(() -> new ResourceNotFoundException(ErrorMessage.REPORT_NOT_FOUND.getValue() + reportId));

	}

	@DeleteMapping(value = "/employees/{employeeId}/reports/{reportId}")
	public ResponseEntity<?> deleteReport(@PathVariable Integer employeeId, @PathVariable Integer reportId) {

		if (!employeeRepository.existsById(employeeId)) {
			throw new ResourceNotFoundException(ErrorMessage.EMPLOYEE_NOT_FOUND.getValue() + employeeId);
		}

		return reportRepository.findById(reportId).map(account -> {
			reportRepository.delete(account);
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new ResourceNotFoundException(ErrorMessage.REPORT_NOT_FOUND.getValue() + reportId));

	}

}
