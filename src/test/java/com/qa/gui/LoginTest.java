package com.qa.gui;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {

    private WebDriver driver;

    @BeforeEach
    void setUp() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        // options.addArguments("--headless"); // uncomment if you want headless
        options.addArguments("--start-maximized");

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    @DisplayName("Should open Sauce Demo login page successfully")
    void testOpenLoginPage() {
        driver.get("https://www.saucedemo.com/");

        String title = driver.getTitle();
        assertTrue(title.contains("Swag Labs"));

        WebElement usernameField = driver.findElement(By.id("user-name"));
        assertTrue(usernameField.isDisplayed());
    }

    @Test
    @DisplayName("Should login successfully with valid credentials")
    void testSuccessfulLogin() {
        driver.get("https://www.saucedemo.com/");

        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        // After login we should see the products page
        WebElement productsTitle = driver.findElement(By.className("title"));
        assertEquals("Products", productsTitle.getText());
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}