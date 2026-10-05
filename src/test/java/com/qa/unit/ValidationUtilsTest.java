
package com.qa.unit;

import com.qa.utils.ValidationUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class ValidationUtilsTest {

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"   ", "\t", "\n"})
    @DisplayName("Should return true for null, empty or blank strings")
    void testIsNullOrEmpty_WithInvalidValues(String input) {
        assertTrue(ValidationUtils.isNullOrEmpty(input));
    }

    @ParameterizedTest
    @ValueSource(strings = {"Hello", "John", "Test User", "a"})
    @DisplayName("Should return false for valid strings")
    void testIsNullOrEmpty_WithValidStrings(String input) {
        assertFalse(ValidationUtils.isNullOrEmpty(input));
    }

    @ParameterizedTest
    @CsvSource({
        "0, true",
        "18, true",
        "25, true",
        "120, true",
        "-1, false",
        "121, false",
        "150, false"
    })
    @DisplayName("Should validate age correctly")
    void testIsValidAge(int age, boolean expected) {
        assertEquals(expected, ValidationUtils.isValidAge(age));
    }
}