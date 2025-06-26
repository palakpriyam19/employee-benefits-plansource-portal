package pageObject;

import baseclass.BaseClass;
import drivers.DriverFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.ScreenshotUtil;

import java.time.Duration;

public class DashboardPage extends BaseClass {

    private static final Logger logger = LoggerFactory.getLogger(DriverFactory.class);
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(id = "search-simple-text")
    private WebElement searchInput;

    @FindBy(linkText="Dashboard")
    private WebElement dashboardNavSidebar;

    public DashboardPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);
    }

    /**
     * Waits for the dashboard to load based on a known element or page title.
     */
    public void waitForDashboardToLoadAndClick() {
        logger.info("Waiting for Dashboard to load... ");
        wait.until(ExpectedConditions.visibilityOf(dashboardNavSidebar));
        logger.info("Clicking on Dashboard...");
        dashboardNavSidebar.click();
    }

    /**
     * Checks whether the employee's details are listed in the results.
     */
    public boolean isEmployeeListed(String expectedNameOrSsn) {
        logger.info("Take a screenshot...");
        ScreenshotUtil.captureAndReturnPath(driver, "Employee Listed");
        logger.info("Checking if employee created is listed or not...");
        return driver.getPageSource().contains(expectedNameOrSsn);
    }
}
