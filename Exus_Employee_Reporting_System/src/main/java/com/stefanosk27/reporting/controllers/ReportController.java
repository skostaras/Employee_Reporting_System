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
import com.stefanosk27.reporting.domain.Employee;
import com.stefanosk27.reporting.domain.Report;
import com.stefanosk27.reporting.enums.ErrorMessage;
import com.stefanosk27.reporting.enums.Priority;
import com.stefanosk27.reporting.repositories.EmployeeRepository;
import com.stefanosk27.reporting.repositories.ReportRepository;

@RestController
public class ReportController {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private ReportRepository reportRepository;

	@GetMapping(value = "/employees/reports/employee")
	public Page<Report> findByEmployeeUsername(@RequestParam String username, Pageable pageable) {

		Optional<Employee> employeeResults = employeeRepository.findByUsername(username);
		if (!employeeResults.isPresent())
			throw new ResourceNotFoundException(ErrorMessage.EMPLOYEE_NOT_FOUND.getValue() + username);

		Page<Report> reportResults = reportRepository.findByEmployeeUsername(username, pageable);
		if (reportResults.isEmpty())
			throw new ResourceNotFoundException(ErrorMessage.REPORTS_NOT_FOUND.getValue() + username);

		return reportResults;
	}

	@GetMapping(value = "/employees/reports")
	public Page<Report> findByPriority(@RequestParam String priority, Pageable pageable) {

		if (priority.toLowerCase().equals("low"))
			priority = "Low";

		if (priority.toLowerCase().equals("high"))
			priority = "High";

		return reportRepository.findByPriority(priority, pageable);
	}

	@PostMapping(value = "/employees/reports")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Report save(@RequestParam String username, @RequestBody Report report) {
		return employeeRepository.findByUsername(username).map(employee -> {
			report.setEmployee(employee);
			return reportRepository.save(report);
		}).orElseThrow(() -> new ResourceNotFoundException(ErrorMessage.EMPLOYEE_NOT_FOUND.getValue() + username));
	}

	@PutMapping(value = "/employees/reports")
	@ResponseBody
	public ResponseEntity<Report> updateReport(@RequestParam String username, @RequestParam Integer reportId,
			@RequestBody Report reportFromRequest) {

		Employee employee = employeeRepository.findByUsername(username).orElseThrow(
				() -> new ResourceNotFoundException(ErrorMessage.EMPLOYEE_NOT_FOUND.getValue() + username));

		return reportRepository.findById(reportId).map(editedReport -> {
			editedReport.setEmployee(employee);
			editedReport.setDescription(reportFromRequest.getDescription());
			editedReport.setPriority(Priority.getEnumFromValue(reportFromRequest.getPriority()));
			editedReport.setTitle(reportFromRequest.getTitle());
			reportRepository.save(editedReport);
			return ResponseEntity.ok(editedReport);
		}).orElseThrow(() -> new ResourceNotFoundException(ErrorMessage.REPORT_NOT_FOUND.getValue() + reportId));
	}

	@DeleteMapping(value = "/employees/reports")
	public ResponseEntity<?> deleteReport(@RequestParam String username, @RequestParam Integer reportId) {

		employeeRepository.findByUsername(username).orElseThrow(
				() -> new ResourceNotFoundException(ErrorMessage.EMPLOYEE_NOT_FOUND.getValue() + username));

		return reportRepository.findById(reportId).map(account -> {
			reportRepository.delete(account);
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new ResourceNotFoundException(
				ErrorMessage.REPORTS_NOT_FOUND.getValue() + username + " and the report ID: " + reportId));
	}

}
