package com.selenium_framework;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    private int defaultWaitTime;
    protected String url;    
    public UtilitiesPage utility;

    public BasePage(WebDriver driver){
        this.driver=DriverFactory.getDriver();
        this.defaultWaitTime=20;
        this.wait= new WebDriverWait(driver, Duration.ofSeconds(defaultWaitTime));
        this.url=new String();
    }
    
    protected WebElement findElement(By by){
        WebElement element = null;
        int attempts = 0;
        int maxAttempts = 3; 

        while (attempts <= maxAttempts) {
            try {
                Wait<WebDriver> fluentWait = new FluentWait<>(driver)
                        .withTimeout(Duration.ofSeconds(defaultWaitTime))
                        .pollingEvery(Duration.ofMillis(500))
                        .ignoring(StaleElementReferenceException.class);

                element = fluentWait.until(ExpectedConditions.visibilityOfElementLocated(by));
                element.getText(); 
                break; 
            } catch (StaleElementReferenceException e) {
                System.out.println("Stale element reference detected, retrying...");
                e.printStackTrace();
                attempts++;
                System.out.println("Element not found after " + maxAttempts + " attempts. Refreshing page...");
                driver.get(utility.getPropertyFileValue(url));
            }
        }
        return element;
    }

    protected void clickElement(By by){
        WebElement element=findElement(by);
        element.click();
    }

    protected void enterText(By by,String text){
        WebElement element=findElement(by);
        clearField(by);
        element.sendKeys(text);
    }

    protected void clearField(By by){
        WebElement element=findElement(by);
        element.clear();
    }

}
