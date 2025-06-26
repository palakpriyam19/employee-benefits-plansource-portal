package baseclass;

import drivers.DriverFactory;
import models.Employee;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.ScreenshotUtil;
import utils.TestDataUtil;

import java.time.Duration;

public class BaseClass {
    @FindBy(id = "search-simple-text")
    private WebElement searchButton;

    private static String randomSSN = "";

    @FindBy(xpath="//a/div/span")
    private WebElement newEmployeeName;

    private static final Logger logger = LoggerFactory.getLogger(DriverFactory.class);
    private WebDriver driver;
    private final WebDriverWait wait;

    public BaseClass(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    /**
     * Generate a valid and random ssn value
     */
    public static <Random> String generateRandomSSN() {
        java.util.Random random = new java.util.Random();
        int area = random.nextInt(899) + 100;      // 100–999 (avoiding 000)
        int group = random.nextInt(90) + 10;       // 10–99 (avoiding 00)
        int serial = random.nextInt(9000) + 1000;  // 1000–9999 (avoiding 0000)
        System.out.println("SSN: " + String.format("%03d-%02d-%04d", area, group, serial));
        randomSSN = String.format("%03d-%02d-%04d", area, group, serial);
        return randomSSN;
    }

    /**
     * Scroll to Element with offset.
     */
    public static void scrollToElementWithOffset(WebDriver driver, WebElement element, int offsetY) {
        logger.info("Scrolling element with offset...");
        ((JavascriptExecutor) driver).executeScript(
                "const el = arguments[0];" +
                        "const y = el.getBoundingClientRect().top + window.pageYOffset - arguments[1];" +
                        "window.scrollTo({ top: y, behavior: 'smooth' });",
                element, offsetY
        );
        logger.info("Element scrolled to view...");
    }

    /**
     * Performs a search for the employee using a keyword (name or SSN).
     */
    public void searchEmployee() {
        logger.info("Search created employee... ");
        try {
            logger.info("Waiting for search textfield to load...");
            waitForElementToLoad(searchButton);
            logger.info("Sending ssn to be searched in the search textfield...");
            searchButton.sendKeys(randomSSN + Keys.ENTER);
            ScreenshotUtil.captureAndReturnPath(driver, "Entered text");
            logger.info("Dismiss unexpected alert if present...");
            dismissUnexpectedAlertIfPresent();
        } catch (UnhandledAlertException e) {
            dismissUnexpectedAlertIfPresent();
        }
    }

    /**
     * Wait for a web element to load.
     *
     */
    public void waitForElementToLoad(WebElement webElement) {
        logger.info("Waiting for element to load...", webElement);
        wait.until(ExpectedConditions.visibilityOf(webElement)).click();
    }

    /**
     * Fetch new employee name from input json file.
     *
     * @return String
     */
    public String getNewEmployeeNameFromInputJson() {
        logger.info("Fetching new employee name from Input json");
        String inputJsonFileName = "AddEmployeeData.json";
        Employee emp = TestDataUtil.loadEmployeeFromJson(inputJsonFileName);
        return emp.getFirstName().concat(" ").concat(emp.getLastName());
    }

    /**
     * Check if created employee is listed.
     *
     * @return boolean
     */
    public boolean isEmployeeListed(String firstName){
        logger.info("Checking if employee created is present or not... ");
        String employeeProfileTitle = "Employee Profile";
        wait.until(ExpectedConditions.titleContains(employeeProfileTitle));
        if(newEmployeeName.isDisplayed() && newEmployeeName.getText()!=null)
            newEmployeeName.getText().equals(getNewEmployeeNameFromInputJson());
        return true;
    }

    /**
     * Dismiss unexpected alert if present.
     *
     */
    public void dismissUnexpectedAlertIfPresent() {
        try {
            logger.info("Dismissing unexpected Alert, if present");
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            System.out.println("Dismissing unexpected alert: " + alert.getText());
            alert.dismiss();
            logger.info("Dismissed unexpected Alert");
        } catch (TimeoutException ignored) {
            // no alert, safe to proceed
        }
    }

}
