package com.insider.qa.pages;

import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public HomePage open() {
        driver.get("https://insiderone.com/");
        return this;
    }


    public boolean isHomePageLoaded() {
        String title = driver.getTitle().toLowerCase();
        String url = driver.getCurrentUrl().toLowerCase();

        return (title.contains("insider") || url.contains("insiderone.com"));
    }

    public CareersPage goToQaCareers() {
        // Qa Sayfası git
        driver.get("https://insiderone.com/careers/quality-assurance/");
        return new CareersPage(driver);
    }
}


