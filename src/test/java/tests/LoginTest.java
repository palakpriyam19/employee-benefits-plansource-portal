package tests;

import baseclass.BaseTest;
import config.ConfigManager;
import drivers.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.*;
import pageObject.LoginPage;
import utils.ScreenshotUtil;

import java.lang.reflect.Method;
import java.time.Duration;

public class LoginTest extends BaseTest {

    static WebDriver driver;
    private static final Logger logger = LoggerFactory.getLogger(DriverFactory.class);
    private static String dashboardPage = "Dashboard";

    @BeforeMethod
    public void setup(Method method) {
        driver = DriverFactory.getDriver();
        driver.get(ConfigManager.get("baseUrl"));
        test = extent.createTest(method.getName());
    }

    @Test
    public static void testLoginSuccess() {
        LoginPage login = new LoginPage(driver);
        logger.info("Take screenshot before logging in...");
        ScreenshotUtil.captureAndReturnPath(driver, "01_Login_page");

        logger.info("Input username and password to perform login operation");
        login.login(ConfigManager.get("username"), ConfigManager.get("password"));

        logger.info("Optional wait to let the dashboard load");
        new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.titleContains(dashboardPage));

        logger.info("Take screenshot after login");
        ScreenshotUtil.captureAndReturnPath(driver, "02_Dashboard_page");

        Assert.assertTrue(driver.getPageSource().contains(dashboardPage), "Login Successful!");
    }

    @Test
    public static void testLoginFailure() {
        LoginPage login = new LoginPage(driver);

        logger.info("Take screenshot before logging in");
        ScreenshotUtil.captureAndReturnPath(driver, "01_Login_page");

        logger.info("Sending incorrect credentials");
        login.login(ConfigManager.get("username"), ConfigManager.get("incorrect_password"));

        logger.info("Take screenshot after logging in");
        ScreenshotUtil.captureAndReturnPath(driver, "03_Failed_page");

        Assert.assertTrue(login.validateLoginFailure(), "Login failure! Unauthenticated user detected!");
    }

    @AfterMethod
    public void tearDown() {
        logger.info("Quitting driver...");
        DriverFactory.quitDriver();
    }
}
