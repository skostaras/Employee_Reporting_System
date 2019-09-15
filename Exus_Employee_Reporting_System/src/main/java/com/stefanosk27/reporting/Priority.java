package com.stefanosk27.reporting;

public enum Priority {
	
	LOW("Low"), HIGH("High");

	private final String value;

	private Priority(String value) {

		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
