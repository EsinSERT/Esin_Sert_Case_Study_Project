package com.insider.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CareersPage extends BasePage {

    private final By seeAllQaJobsButton = By.xpath("//a[contains(text(),'See all QA jobs')]");

    public CareersPage(WebDriver driver) {
        super(driver);
    }

    public QaJobsPage clickSeeAllQaJobs() {
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(seeAllQaJobsButton));


        scrollToElement(btn);
        jsClick(btn);

        return new QaJobsPage(driver);
    }
}
