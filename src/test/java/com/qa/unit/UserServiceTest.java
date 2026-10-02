
package com.qa.unit;

import com.qa.model.User;
import com.qa.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {

    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserService();
    }

    @Test
    void testIsAdult_WhenAgeIs18_ShouldReturnTrue() {
        // Arrange
        User user = new User("John", "Doe", "john@example.com", 18);

        // Act
        boolean result = userService.isAdult(user);

        // Assert
        assertTrue(result);
    }

    @Test
    void testIsAdult_WhenAgeIs17_ShouldReturnFalse() {
        // Arrange
        User user = new User("Jane", "Doe", "jane@example.com", 17);

        // Act
        boolean result = userService.isAdult(user);

        // Assert
        assertFalse(result);
    }

    @Test
    void testIsValidEmail_WithValidEmail_ShouldReturnTrue() {
        // Arrange
        User user = new User("John", "Doe", "john.doe@gmail.com", 25);

        // Act
        boolean result = userService.isValidEmail(user);

        // Assert
        assertTrue(result);
    }

    @Test
    void testIsValidEmail_WithInvalidEmail_ShouldReturnFalse() {
        // Arrange
        User user = new User("John", "Doe", "invalidemail", 25);

        // Act
        boolean result = userService.isValidEmail(user);

        // Assert
        assertFalse(result);
    }

    @Test
    void testGetDisplayName_ShouldReturnFullName() {
        // Arrange
        User user = new User("Thabo", "Mokoena", "thabo@example.com", 30);

        // Act
        String result = userService.getDisplayName(user);

        // Assert
        assertEquals("Thabo Mokoena", result);
    }
}