package tests;

import baseclass.BaseTest;
import config.ConfigManager;
import drivers.DriverFactory;
import models.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObject.DashboardPage;
import pageObject.EmployeePage;
import pageObject.LoginPage;
import utils.ScreenshotUtil;
import utils.TestDataUtil;

import java.awt.*;
import java.lang.reflect.Method;

public class EmployeeCreationTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(DriverFactory.class);

    @BeforeMethod
    public void setUp(Method method) {
        logger.info("Initialize driver...");
        driver = DriverFactory.getDriver();
        logger.info("Load Login page...");
        driver.get(ConfigManager.get("baseUrl"));
        test = extent.createTest(method.getName());
    }

    @BeforeMethod
    public void testLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(ConfigManager.get("username"), ConfigManager.get("password"));
    }

    @Test
    public void testCreateEmployeeUsingJsonData() throws InterruptedException, AWTException{
        logger.info("1. Load employee from JSON");
        Employee emp = TestDataUtil.loadEmployeeFromJson("AddEmployeeData.json");
        test.info("Loaded employee: " + emp.getFirstName() + " " + emp.getLastName());

        logger.info("2. Create employee via UI");
        EmployeePage employeePage = new EmployeePage(driver);
        employeePage.createEmployee(emp);

        logger.info("Take a Screenshot");
        ScreenshotUtil.captureAndReturnPath(driver, "3_Create Employee via UI");
        test.pass("Employee created successfully");

        logger.info("3. Wait for Dashboard to appear and click");
        DashboardPage dashboard = new DashboardPage(driver);
        dashboard.waitForDashboardToLoadAndClick();
        test.info("Dashboard loaded successfully");

        logger.info("4. Validate search result using SSN");
        dashboard.searchEmployee();
        test.info("Search executed for SSN");

        logger.info("5. Assert employee is listed");
        Assert.assertTrue(dashboard.isEmployeeListed(emp.getFirstName()), "Employee not found after creation");
        test.pass("Employee listed successfully");
    }

    @AfterMethod
    public void tearDown() {
        logger.info("Quitting driver...");
        DriverFactory.quitDriver();
    }
}
