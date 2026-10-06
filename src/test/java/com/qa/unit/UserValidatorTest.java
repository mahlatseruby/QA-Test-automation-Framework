
package com.qa.unit;

import com.qa.model.User;
import com.qa.utils.UserValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class UserValidatorTest {

    @Test
    @DisplayName("Should return false when user is null")
    void testIsValidUser_NullUser() {
        assertFalse(UserValidator.isValidUser(null));
    }

    @Test
    @DisplayName("Should return true for a valid user")
    void testIsValidUser_ValidUser() {
        User user = new User("Lerato", "Dlamini", "lerato@example.com", 24);
        assertTrue(UserValidator.isValidUser(user));
    }

    @ParameterizedTest
    @CsvSource({
        "John, Doe, 25, true",
        "'', Doe, 25, false",
        "John, '', 25, false",
        "John, Doe, -5, false",
        "John, Doe, 150, false"
    })
    @DisplayName("Should validate user with different inputs")
    void testIsValidUser_MultipleCases(String firstName, String lastName, int age, boolean expected) {
        User user = new User(firstName, lastName, "test@example.com", age);
        assertEquals(expected, UserValidator.isValidUser(user));
    }
}