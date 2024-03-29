package com.stefanosk27.reporting.enums;

import java.util.HashMap;
import java.util.Map;

public enum Priority {

	LOW("Low"), HIGH("High");

	private final String priorityValue;

	private static final Map<String, Priority> findEnum = new HashMap<String, Priority>();

	static {
		for (Priority priority : Priority.values()) {
			findEnum.put(priority.getPriorityValue(), priority);
		}
	}

	private Priority(String value) {
		this.priorityValue = value;
	}

	public String getPriorityValue() {
		return priorityValue;
	}

	public static Priority getEnumFromValue(String value) {
		return findEnum.get(value);
	}

}
