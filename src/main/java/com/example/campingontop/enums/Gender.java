package com.example.campingontop.enums;

public enum Gender {
    M, F;

    public static Gender fromValue(String value) {
        for (Gender gender : Gender.values()) {
            if (gender.name().equalsIgnoreCase(value)) {
                return gender;
            }
        }
        throw new IllegalArgumentException("No enum constant for value: " + value);
    }
}
