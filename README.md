# QA Test Automation Framework

A complete Java-based Test Automation Framework demonstrating professional QA practices.

## Overview
This project covers the full testing pyramid:
- Unit Testing
- API Testing
- UI (GUI) Testing

It follows clean code principles, the Arrange-Act-Assert pattern, data-driven testing, and the Page Object Model.

## Tech Stack
- **Language:** Java 17+
- **Build Tool:** Maven
- **Unit Testing:** JUnit 5
- **API Testing:** RestAssured
- **UI Testing:** Selenium WebDriver + WebDriverManager
- **Design Pattern:** Page Object Model (POM)

## Project Structure
src/
├── main/java/com/qa/
│   ├── model/
│   ├── pages/          # Page Object Model
│   ├── service/
│   └── utils/
└── test/java/com/qa/
├── unit/           # JUnit unit tests
├── api/            # RestAssured API tests
└── gui/            # Selenium UI tests


## Features
- Data-driven unit tests using `@ParameterizedTest`
- Clean Page Object Model implementation
- Positive and negative test scenarios
- API testing (GET, POST, PUT, DELETE)
- UI login flow automation (Sauce Demo)

## How to Run

# Run all tests
mvn clean test

# Run only unit tests
mvn test "-Dtest=UserServiceTest,UserValidatorTest,ValidationUtilsTest"

# Run only API tests
mvn test "-Dtest=UserApiTest,PostApiTest"

# Run only GUI tests
mvn test "-Dtest=LoginTest"