package com.qa.utils;

public class ValidationUtils {

    public static boolean isNullOrEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }

    public static boolean isValidAge(int age) {
        return age >= 0 && age <= 120;
    }
}