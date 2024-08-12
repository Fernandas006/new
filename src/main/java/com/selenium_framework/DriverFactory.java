package com.selenium_framework;
import org.apache.logging.log4j.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
    private static WebDriver driver;
    private static final Logger logger = LogManager.getLogger(DriverFactory.class);
    static UtilitiesPage utility=new UtilitiesPage(driver);

    public static WebDriver getDriver() {
        if (driver == null) {
            try {
                String chromeDriverPath = utility.getPropertyFileValue("chromeDriverPath");
                logger.info("ChromeDriver Path: " + chromeDriverPath);
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                logger.info("ChromeDriver initialized successfully.");
            } catch (Exception e) {
                logger.error("Error initializing ChromeDriver: " + e.getMessage(),e);
            }
        }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}

