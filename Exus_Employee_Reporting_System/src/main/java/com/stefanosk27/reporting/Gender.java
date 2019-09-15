package com.stefanosk27.reporting;

public enum Gender {

	MALE("Male"), FEMALE("Female"), UNSPECIFIED("Unspecified");

	private final String value;

	private Gender(String value) {

		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
