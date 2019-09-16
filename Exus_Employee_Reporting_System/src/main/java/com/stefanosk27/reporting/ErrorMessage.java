package com.stefanosk27.reporting;

public enum ErrorMessage {
	
	EMPLOYEE_NOT_FOUND("Can't find an employee with the username: "),
	EMPLOYEES_NOT_FOUND("Can't find any employees."),
	REPORT_NOT_FOUND("Can't find a report with the following id: "),
	USERNAME_EXISTS(" belongs to another employee. If you want to update the existing employee, use PUT method.");
	
	private final String value;

	private ErrorMessage(String value) {

		this.value = value;
	}

	public String getValue() {
		return value;
	}
	
	

}
