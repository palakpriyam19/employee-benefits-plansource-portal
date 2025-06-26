# Test Automation Framework – UI + API Integration

This project is a modular, Java-based automation framework designed to test employee creation and search functionality via the UI, using TestNG, Selenium, and JSON-driven test data.

---

## 📦 Features

- ✅ Employee creation & search via UI
- ✅ Random SSN generation & reuse
- ✅ TestNG-based execution flow
- ✅ Page Object Model (POM) structure
- ✅ JSON-based test data (read/write)
- ✅ ExtentReports for HTML test results
- ✅ SLF4J + Logback logging
- ✅ Screenshot capture on failure
- ✅ Browser setup with Firefox + no autofill
- ✅ Modular utility classes for reuse

---

## 🧪 Tech Stack

| Tool         | Usage                          |
|--------------|---------------------------------|
| Java 11+     | Base language                   |
| Maven        | Dependency management           |
| Selenium     | UI automation                   |
| TestNG       | Test execution                  |
| Gson         | JSON serialization/deserialization |
| ExtentReports| Test reporting                  |
| Logback      | Logging implementation          |

---

## 🗂️ Project Structure
screenshots/
logs/
src/
├── main/
│ ├── java/
│ │ ├── baseclass / # Base class
│ │ ├── pageObject/ # Page Object classes (DashboardPage, EmployeePage, etc.)
│ │ ├── models/ # POJO for Employee and SSNRecord
│ │ ├── config/ # ConfigManager
│ │ ├── utils/ # Helpers like ExtentManager, TestDataUtil, ScreenshotUtil
│ │ └── drivers/ # DriverFactory.java
│ └── resources/
│ └── logback.xml # Logging configuration
├── test/
│ ├── java/
│ │ └── baseclass/ # base test class
│ │ └── tests/ # Test classes (EmployeeCreationTest, etc.)
│ └── resources/ # json files
target/
test-output/ # extent report
config.properties
pom.xml
readme.md
testng.xml

## How to run the tests?
✅ **1. Using IntelliJ (Recommended)**
- Ensure your project is a Maven project and dependencies are resolved.
- Right-click testng.xml → Run 'testng.xml'
- Or right-click EmployeeCreationTest.java → Run 'EmployeeCreationTest'
- Or run from the Maven window: Lifecycle > test

✅ **2. Using Maven CLI (Terminal)**
Basic Test Execution:
- bash
mvn clean test