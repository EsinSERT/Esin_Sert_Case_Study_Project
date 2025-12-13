package com.insider.qa.tests;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TestListener implements ITestListener {

    private static final String SCREENSHOT_DIR = "screenshots";

    @Override
    public void onTestFailure(ITestResult result) {
        Object instance = result.getInstance();

        if (!(instance instanceof BaseTest)) {
            return;
        }

        WebDriver driver = ((BaseTest) instance).getDriver();
        if (driver == null) {
            return;
        }

        saveScreenshot(driver, result.getMethod().getMethodName());
    }

    private void saveScreenshot(WebDriver driver, String methodName) {
        if (!(driver instanceof TakesScreenshot)) {
            return;
        }

        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        try {
            Files.createDirectories(Paths.get(SCREENSHOT_DIR));

            String fileName = methodName + "_" + System.currentTimeMillis() + ".png";
            Path target = Paths.get(SCREENSHOT_DIR, fileName);

            Files.copy(src.toPath(), target);
        } catch (IOException ignored) {

        }
    }
}
