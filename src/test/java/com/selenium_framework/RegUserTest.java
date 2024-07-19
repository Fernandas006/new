package com.selenium_framework;
import org.testng.annotations.*;

import com.selenium_framework.listeners.TestListener;

import org.testng.Assert;
import org.testng.Reporter;
import java.util.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Listeners(TestListener.class)
public class RegUserTest  {
    private WebDriver driver;
    private BasePage basePage;
    private UtilitiesPage utility;

    @BeforeClass
    public void setUp() {
        try {
            System.out.println("Setting up the driver...");
            driver = DriverFactory.getDriver();
            if (driver != null) {
                basePage = new BasePage(driver);
                System.out.println("Driver setup completed.");
            } else {
                System.err.println("Driver is null after setup.");
            }
        } catch (Exception e) {
            System.err.println("Error setting up driver: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Test
    public void testRegUserPage(){
        try {
            String url = UtilitiesPage.getPropertyFileValue("registerUserUrl");
            System.out.println("Navigating to URL: " + url);
            driver.get(url);
            driver.manage().window().maximize();
            Assert.assertEquals(driver.getTitle(),"ParaBank | Register for Free Online Account Access");
            Reporter.log("Match found",true);
            //tearDown();

        } catch (Exception e) {
            System.err.println("Error during test execution: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    @AfterClass
    public void tearDown() {
        System.out.println("Closing the browser");
        DriverFactory.quitDriver();
    }
}
