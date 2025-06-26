package drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DriverFactory {
    private static final Logger logger = LoggerFactory.getLogger(DriverFactory.class);
    private static WebDriver driver;

    public static WebDriver getDriver() {
        if (driver == null) {
            logger.info("Initializing Firefox WebDriver...");
            setupFirefoxWithNoAutofill();
        }
        return driver;
    }

    private static void setupFirefoxWithNoAutofill() {
        logger.info("Setting up Firefox profile...");
        FirefoxProfile profile = new FirefoxProfile();

        logger.debug("Disabling Firefox autofill preferences...");
        profile.setPreference("signon.autofillForms", false);
        profile.setPreference("browser.formfill.enable", false);
        profile.setPreference("extensions.formautofill.addresses.enabled", false);
        profile.setPreference("extensions.formautofill.creditCards.enabled", false);

        logger.info("Setting up Firefox Options...");
        FirefoxOptions options = new FirefoxOptions();
        options.setProfile(profile);
        options.addArguments("--window-size=1920,1080");

        driver = new FirefoxDriver(options);
        driver.manage().window().maximize();
    }

    public static void quitDriver() {
        if (driver != null) {
            logger.info("Quitting WebDriver...");
            driver.quit();
            driver = null;
        }
    }
}