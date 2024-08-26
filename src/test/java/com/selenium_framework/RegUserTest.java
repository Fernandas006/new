package com.selenium_framework;
import org.testng.annotations.*;
import org.apache.logging.log4j.*;
import com.selenium_framework.listeners.TestListener;

import org.testng.Assert;
import java.util.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Listeners(TestListener.class)
public class RegUserTest extends BasePage {
    //private WebDriver driver;
    private static final Logger logger= LogManager.getLogger(RegUserTest.class);
    RegisterUserPage regPage;
    UtilitiesPage utility;
    //BasePage basePage=new BasePage(driver);

    public RegUserTest(){
        super(DriverFactory.getDriver());
        this.driver = DriverFactory.getDriver();
        this.regPage = new RegisterUserPage();
        this.utility = new UtilitiesPage(driver);
    }
    
    @BeforeClass
    public void setUp() {
        try {
            System.out.println("Setting up the driver...");
            //
            if (driver != null) {
                //basePage = new BasePage(this.driver);
                logger.info("Driver setup completed.");
            } else {
                logger.error("Driver is null after setup.");
            }
        } catch (Exception e) {
            logger.error("Error setting up driver: " + e.getMessage(), e);
        }
    }

    @Test
    public void testRegUserPage(){
        try {
            String url = utility.getPropertyFileValue("registerUserUrl");
            logger.info("Navigating to URL: " + url);
            this.driver.get(url);
            this.driver.manage().window().maximize();
            try {
                Assert.assertEquals(driver.getTitle(), "ParaBank | Register for Free Online Account Access");
                logger.info("Title matched successfully");
                utility.takeScreenshot("RestrationPageTest");
                logger.info("Screenshot taken successfully");
            } catch (AssertionError e) {
                logger.error("Title did not match.", e);
                throw e; 
            }
            

        } catch (Exception e) {
            logger.error("Error during test execution: " + e.getMessage(),e);
        }
    }

    //@Test
    public void testCreateNewUser(){
        String excelFilePath=utility.getPropertyFileValue("userDataExcel");
        List<By> tempBy=regPage.elementList();
        List<String> tempValue=utility.getExcelValues(excelFilePath);
        System.out.println(tempBy);
        System.out.println(tempValue);
        regPage.fillRegUserPage(tempBy, tempValue);
        logger.info("New User fields have been filled");
        clickElement(regPage.registerButton);
    }
    
    @AfterClass
    public void tearDown() {
        logger.info("Closing the browser");
        DriverFactory.quitDriver();
    }
}
