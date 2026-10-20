package com.qa.gui;

import com.qa.pages.LoginPage;
import com.qa.pages.ProductsPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {

    private WebDriver driver;
    private LoginPage loginPage;
    private ProductsPage productsPage;

    @BeforeEach
    void setUp() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://www.saucedemo.com/");

        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
    }

    @Test
    @DisplayName("Should login successfully using Page Object Model")
    void testSuccessfulLoginWithPOM() {
        loginPage.loginAs("standard_user", "secret_sauce");

        assertTrue(productsPage.isProductsPageDisplayed());
        assertEquals("Products", productsPage.getPageTitle());
    }

    @Test
    @DisplayName("Login page should display login button")
    void testLoginPageElements() {
        assertTrue(loginPage.isLoginButtonDisplayed());
    }

    @Test
    @DisplayName("Should show error with invalid credentials")
    void testLoginWithInvalidCredentials() {
        loginPage.loginAs("wrong_user", "wrong_password");

        // Only check things that exist on the login page after failed login
        assertTrue(loginPage.isLoginButtonDisplayed());
        assertTrue(loginPage.isErrorMessageDisplayed());
        assertTrue(loginPage.getErrorMessage().toLowerCase().contains("epic sadface")
                || loginPage.getErrorMessage().toLowerCase().contains("username and password do not match"));
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}