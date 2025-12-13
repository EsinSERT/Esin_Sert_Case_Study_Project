package com.insider.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LeverPage extends BasePage {

    private final By leverBody = By.tagName("body");

    public LeverPage(WebDriver driver) {
        super(driver);
    }

    public boolean isLeverApplicationFormOpened() {
        String url = driver.getCurrentUrl().toLowerCase();
        boolean urlOk = url.contains("jobs.lever.co");
        boolean bodyVisible = isElementDisplayed(leverBody);

        return urlOk && bodyVisible;
    }
}
