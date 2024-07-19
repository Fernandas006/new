package com.selenium_framework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class DriverFactory {
    private static WebDriver driver;

    public static WebDriver getDriver() {
        if (driver == null) {
            try {
                String chromeDriverPath = UtilitiesPage.getPropertyFileValue("chromeDriverPath");
                System.out.println("ChromeDriver Path: " + chromeDriverPath);
                System.setProperty("webdriver.chrome.driver", chromeDriverPath);
                driver = new EdgeDriver();
                System.out.println("ChromeDriver initialized successfully.");
            } catch (Exception e) {
                System.err.println("Error initializing ChromeDriver: " + e.getMessage());
                e.printStackTrace();
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

