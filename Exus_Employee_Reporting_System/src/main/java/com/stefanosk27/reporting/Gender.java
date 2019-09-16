package com.stefanosk27.reporting;

import java.util.HashMap;
import java.util.Map;

public enum Gender {

	MALE("Male"), FEMALE("Female"), UNSPECIFIED("Unspecified");

//	private final String value;
//
//	private Gender(String value) {
//
//		this.value = value;
//	}
//
//	public String getValue() {
//		return value;
//	}
//	
//	public Gender getGender(String value) {
//		return Gender.valueOf(enumType, name)
//	}
	
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
