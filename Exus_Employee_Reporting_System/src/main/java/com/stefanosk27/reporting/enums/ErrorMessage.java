package com.stefanosk27.reporting.enums;

public enum ErrorMessage {

	EMPLOYEE_NOT_FOUND("Can't find an employee with the username: "), 
	EMPLOYEES_NOT_FOUND("Can't find any employees."),
	REPORT_NOT_FOUND("Can't a report with the ID: "),
	REPORTS_NOT_FOUND("Can't find any reports for the employee with the username: "), 
	USERNAME_EXISTS(
			" belongs to another employee. If you want to update the existing employee, please use the PUT method.");

	private final String value;

	private ErrorMessage(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
