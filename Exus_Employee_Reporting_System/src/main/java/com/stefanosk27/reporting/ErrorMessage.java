package com.stefanosk27.reporting;

public enum ErrorMessage {
	
	EMPLOYEE_NOT_FOUND("Can't find an employee with the following username: "),
	REPORT_NOT_FOUND("Can't find a report with the following id: ");
	
	private final String value;

	private ErrorMessage(String value) {

		this.value = value;
	}

	public String getValue() {
		return value;
	}
	
	

}
