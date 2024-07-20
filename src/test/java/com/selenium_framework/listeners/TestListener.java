package com.selenium_framework.listeners;
import org.apache.logging.log4j.*;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {
    private static final Logger logger= LogManager.getLogger(TestListener.class); 
    @Override
    public void onTestStart(ITestResult result) {
        logger.info("Test Started" + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logger.info("Test passed: " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        logger.info("Test failed: " + result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        logger.info("Test skipped: " + result.getName());
    }

    @Override
    public void onStart(ITestContext context) {
        logger.info("------------------------------------Test Run Initiated------------------------------------");
    }

    @Override
    public void onFinish(ITestContext context) {
        logger.info("------------------------------------Test Run Completed------------------------------------" );
    }
}
