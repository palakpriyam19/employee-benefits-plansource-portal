package pageObject;

import baseclass.BaseClass;
import dev.failsafe.internal.util.Assert;
import drivers.DriverFactory;
import models.Employee;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.ScreenshotUtil;

import java.awt.*;
import java.time.Duration;

public class EmployeePage extends BaseClass {

    private static final Logger logger = LoggerFactory.getLogger(DriverFactory.class);
    private WebDriver driver;
    private final WebDriverWait wait;
    private final String randomSSN = generateRandomSSN();
    private final String hireDate = getHireDateMinusTwoDays();

    @FindBy(linkText = "Add a New Employee")
    private WebElement addNewEmployeeLink;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(id = "first_name")
    private WebElement firstName;

    @FindBy(id = "last_name")
    private WebElement lastName;

    @FindBy(id = "ssn_text")
    private WebElement ssn;

    @FindBy(id = "address_1")
    private WebElement address1;

    @FindBy(id = "city")
    private WebElement city;

    @FindBy(id = "stateTypeahead")
    private WebElement state;

    @FindBy(id = "zip_code")
    private WebElement zip;

    @FindBy(id = "countryTypeahead")
    private WebElement country;

    @FindBy(id = "current_salary")
    private WebElement currentSalary;

    @FindBy(id = "benefit_salary")
    private WebElement benefitSalary;

    //Calendar widgets with input type: text
    @FindBy(id = "birthdate")
    private WebElement birthDate;

    @FindBy(id = "hire_date")
    private WebElement hireDateInput;

    @FindBy(id = "benefits_start_date")
    private WebElement eligibleDateInput;

    //dropdowns
    @FindBy(id = "gender")
    private WebElement genderDropdown;

    @FindBy(id = "marital_status")
    private WebElement maritalStatusDropdown;

    @FindBy(id = "employment_level")
    private WebElement employmentLevelDropdown;

    @FindBy(id = "location")
    private WebElement locationDropdown;

    @FindBy(id = "org_payroll_id")
    private WebElement payrollScheduleDropdown;

    //buttons
    @FindBy(css = "button[type='submit']")
    private WebElement saveButton;

    @FindBy(linkText="New Hire Enrollment")
    private WebElement newHireEnrollmentLink;

    public EmployeePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    /**
     * Navigates to the Add New Employee form
     */
    public void clickAddNewEmployeeLink() {
        logger.info("Take screenshot before clicking on Add a new employee link... ");
        ScreenshotUtil.captureAndReturnPath(driver, "04_click_on_add_new_emp_link");
        logger.info("Waiting for Add a New Employee Link to be clickable, then clicking it");
        wait.until(ExpectedConditions.elementToBeClickable(addNewEmployeeLink)).click();
    }

    /**
     * Fills the employee creation form
     */
    public void enterEmployeeDetails(Employee emp) throws InterruptedException{
        logger.info("Take screenshot before filling the new employee details.. ");
        ScreenshotUtil.captureAndReturnPath(driver, "05_Employee_Details_Form");

        logger.info("Wait for the password field to be visible");
        wait.until(ExpectedConditions.visibilityOf(password)).clear();

        logger.info("Send input from json file to password field");
        password.sendKeys(emp.getPassword());

        logger.info("Send input from json file to first name field");
        firstName.clear();
        firstName.sendKeys(emp.getFirstName());

        logger.info("Send input from json file to last name field");
        lastName.clear();
        lastName.sendKeys(emp.getLastName());

        logger.info("Scroll to ssn element in view");
        scrollToElementWithOffset(driver, ssn, 300);
        Thread.sleep(500);
        logger.info("Input generated random ssn to ssn field");
        ssn.click();
        ssn.sendKeys(randomSSN);

        logger.info("Send input from json file to address1 field");
        address1.sendKeys(emp.getAddress1());

        logger.info("Send input from json file to city field");
        city.sendKeys(emp.getCity());

        logger.info("Send input from json file to state field");
        state.sendKeys(emp.getState());

        logger.info("Send input from json file to zipcode field");
        zip.click();
        zip.sendKeys(emp.getZipCode());

        logger.info("Send input from json file to country field");
        country.sendKeys(emp.getCountry());

        logger.info("Send input from json file to birthDate field");
        birthDate.sendKeys(emp.getBirthDate());

        logger.info("Clicking Gender dropdown and selecting value from json file");
        new Select(genderDropdown).selectByVisibleText(emp.getGender());

        logger.info("Clicking Marital Status dropdown and selecting value from json file");
        new Select(maritalStatusDropdown).selectByVisibleText(emp.getMaritalStatus());

        logger.info("Send input from json file to hireDate field");
        hireDateInput.sendKeys(hireDate);

        logger.info("Send input from json file to eligibleDate field");
        eligibleDateInput.sendKeys(hireDate);

        logger.info("Clicking Employment Level dropdown and selecting value from json file");
        new Select(employmentLevelDropdown).selectByVisibleText(emp.getEmploymentLevel());

        logger.info("Clicking Location dropdown and selecting value from json file");
        new Select(locationDropdown).selectByVisibleText(emp.getLocation());

        logger.info("Scrolling to view currentSalary field");
        scrollToElementWithOffset(driver, currentSalary, 300);

        logger.info("Send input from json file to currentSalary field");
        currentSalary.click();
        currentSalary.sendKeys(emp.getCurrentSalary());

        logger.info("Send input from json file to benefitSalary field");
        benefitSalary.click();
        benefitSalary.sendKeys(emp.getBenefitSalary());

        logger.info("Clicking payrollScheduleDropdown dropdown and selecting value from json file");
        new Select(payrollScheduleDropdown).selectByVisibleText(emp.getPayrollSchedule());
        Thread.sleep(500);
    }

    /**
     * E2E flow for onboarding a new Employee
     *
     */
    public void createEmployee(Employee emp) throws InterruptedException, AWTException{
        logger.info("Click on Add New Employee Link...");
        clickAddNewEmployeeLink();
        logger.info("Start entering employee details in the New Employee Form...");
        enterEmployeeDetails(emp);
        logger.info("Wait for 10 seconds for the page to load...");
        new WebDriverWait(driver, Duration.ofSeconds(10));
        logger.info("Required fields filled, initiating click on Save button...");
        clickSave();
    }

    /**
     * Clicks the Save button to submit employee creation form
     */
    public void clickSave() {
        logger.info("Scrolling to view Save button");
        scrollToElementWithOffset(driver, saveButton, 150);
        logger.info("Clicking on Save button");
        saveButton.click();
        logger.info("Waiting till Employee Profile Page is visible");
        new WebDriverWait(driver, Duration.ofSeconds(35)).until(ExpectedConditions.titleContains("Employee Profile"));
        logger.info("Take screenshot after hitting Save button");
        ScreenshotUtil.captureAndReturnPath(driver, "06_Employee_created");
    }

    public void clickOnNewHireEnrollment() {
        scrollToElementWithOffset(driver, newHireEnrollmentLink, 150);
        logger.info("Clicking the New Hire Enrollment Link");
        if(newHireEnrollmentLink.isDisplayed() && newHireEnrollmentLink.isEnabled())
            newHireEnrollmentLink.click();
        ScreenshotUtil.captureAndReturnPath(driver, "07_Benefits");
    }
}