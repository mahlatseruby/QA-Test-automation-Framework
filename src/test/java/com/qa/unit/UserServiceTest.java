
package com.qa.unit;

import com.qa.model.User;
import com.qa.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {

    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserService();
    }

    // ========== Normal Tests ==========

    @Test
    @DisplayName("Should return true when user is 18 or older")
    void testIsAdult_WhenAgeIs18OrAbove_ShouldReturnTrue() {
        // Arrange
        User user = new User("John", "Doe", "john@example.com", 18);

        // Act
        boolean result = userService.isAdult(user);

        // Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Should return false when user is under 18")
    void testIsAdult_WhenAgeIsUnder18_ShouldReturnFalse() {
        User user = new User("Jane", "Doe", "jane@example.com", 17);
        assertFalse(userService.isAdult(user));
    }

    // ========== Data-Driven Tests (Impressive part) ==========

    @ParameterizedTest
    @DisplayName("Should correctly identify adults with different ages")
    @CsvSource({
        "18, true",
        "25, true",
        "17, false",
        "0, false",
        "120, true"
    })
    void testIsAdult_WithMultipleAges(int age, boolean expected) {
        // Arrange
        User user = new User("Test", "User", "test@example.com", age);

        // Act
        boolean result = userService.isAdult(user);

        // Assert
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @DisplayName("Should validate different email formats")
    @CsvSource({
        "john.doe@gmail.com, true",
        "invalidemail, false",
        "user@domain, false",
        "user.name@company.co.za, true",
        "'', false"
    })
    void testIsValidEmail_WithMultipleEmails(String email, boolean expected) {
        User user = new User("Test", "User", email, 25);
        assertEquals(expected, userService.isValidEmail(user));
    }

    @ParameterizedTest
    @ValueSource(ints = {18, 21, 30, 45, 60})
    @DisplayName("All these ages should be considered adult")
    void testIsAdult_WithValidAdultAges(int age) {
        User user = new User("Adult", "User", "adult@example.com", age);
        assertTrue(userService.isAdult(user));
    }

    @Test
    @DisplayName("Should return full name correctly")
    void testGetDisplayName() {
        User user = new User("Thabo", "Mokoena", "thabo@example.com", 28);
        assertEquals("Thabo Mokoena", userService.getDisplayName(user));
    }
}