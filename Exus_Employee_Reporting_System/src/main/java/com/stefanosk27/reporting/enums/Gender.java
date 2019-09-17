package com.stefanosk27.reporting.enums;

import java.util.HashMap;
import java.util.Map;

public enum Gender {

	MALE("Male"), FEMALE("Female"), UNSPECIFIED("Unspecified");

	private final String genderValue;

	private static final Map<String, Gender> findEnum = new HashMap<String, Gender>();

	static {
		for (Gender gender : Gender.values()) {
			findEnum.put(gender.getGenderValue(), gender);
		}
	}

	private Gender(String value) {
		this.genderValue = value;
	}

	public String getGenderValue() {
		return genderValue;
	}

	public static Gender getEnumFromValue(String value) {
		return findEnum.get(value);
	}

}
