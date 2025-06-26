package pageObject;

import drivers.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginPage {
    private static final Logger logger = LoggerFactory.getLogger(DriverFactory.class);
    private WebDriver driver;
    private String errorPrompt = "";
    final String INVALID_USERNAME_PASSWORD = "Invalid username or password.";

    @FindBy(id = "user_name")
    WebElement username;

    @FindBy(id = "password")
    WebElement password;

    @FindBy(css = "input[type='submit']")
    WebElement loginButton;

    @FindBy(css = "div[class='col alert-content']")
    WebElement invalidErrorPrompt;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void login(String user, String pass) {
        logger.info("Sending user credentials for successful login");
        username.sendKeys(user);
        password.sendKeys(pass);
        logger.info("Clicking on Login button");
        loginButton.click();
    }

    public boolean validateLoginFailure() {
        if (invalidErrorPrompt.isDisplayed())
            errorPrompt = invalidErrorPrompt.getText();
        return errorPrompt.equals(INVALID_USERNAME_PASSWORD);
    }
}
