package com.insider.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.List;
import java.util.Locale;
import java.util.Set;

public class QaJobsPage extends BasePage {

    private final By locationSelect = By.id("filter-by-location");
    private final By departmentSelect = By.id("filter-by-department");

    private final By jobItemsLocator = By.cssSelector(".position-list-item");
    private final By viewRoleButton = By.xpath(".//a[contains(.,'View Role')]");

    public QaJobsPage(WebDriver driver) {
        super(driver);
    }

    public QaJobsPage filterByLocationAndDepartment(String location, String department) {

        // Location dropdown
        Select locationDropdown = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(locationSelect)));
        wait.until(d -> locationDropdown.getOptions().size() > 1);
        selectByContainsText(locationDropdown, location, "Location");

        // Department dropdown
        Select departmentDropdown = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(departmentSelect)));
        wait.until(d -> departmentDropdown.getOptions().size() > 1);
        selectByContainsText(departmentDropdown, department, "Department");

        // Filtre sonrası listenin gelmesini bekledim
        wait.until(d -> !d.findElements(jobItemsLocator).isEmpty());

        return this;
    }

    private void selectByContainsText(Select dropdown, String wantedText, String fieldName) {
        String wanted = normalize(wantedText);

        for (WebElement option : dropdown.getOptions()) {
            String text = option.getText().trim();
            if (normalize(text).contains(wanted)) {
                dropdown.selectByVisibleText(text);
                return;
            }
        }

        throw new RuntimeException(fieldName + " option not found for: " + wantedText);
    }

    public boolean isJobListPresent() {
        return !driver.findElements(jobItemsLocator).isEmpty();
    }

    public boolean allJobsMatchFilters(String expectedLocation, String expectedDepartment) {
        List<WebElement> jobItems = driver.findElements(jobItemsLocator);
        if (jobItems.isEmpty()) return false;

        String loc = normalize(expectedLocation);
        String dept = normalize(expectedDepartment);

        for (WebElement job : jobItems) {
            String allText = normalize(job.getText());
            if (allText.isBlank()) continue;

            boolean locOk = allText.contains(loc);
            boolean deptOk = allText.contains(dept) || allText.contains("quality assurance") || allText.contains("qa");

            if (!locOk || !deptOk) {
                return false;
            }
        }
        return true;
    }

    public boolean atLeastOneJobMatches(String expectedLocation, String expectedDepartment) {
        List<WebElement> jobItems = driver.findElements(jobItemsLocator);
        if (jobItems.isEmpty()) return false;

        String loc = normalize(expectedLocation);
        String dept = normalize(expectedDepartment);

        for (WebElement job : jobItems) {
            String allText = normalize(job.getText());
            if (allText.isBlank()) continue;

            boolean locOk = allText.contains(loc);
            boolean deptOk = allText.contains(dept) || allText.contains("quality assurance") || allText.contains("qa");

            if (locOk && deptOk) {
                return true;
            }
        }
        return false;
    }


    public LeverPage clickFirstJobViewRole() {
        List<WebElement> jobItems = driver.findElements(jobItemsLocator);
        if (jobItems.isEmpty()) {
            throw new RuntimeException("No job items found.");
        }

        WebElement firstJob = null;
        for (WebElement job : jobItems) {
            if (!job.getText().trim().isEmpty()) {
                firstJob = job;
                break;
            }
        }
        if (firstJob == null) {
            throw new RuntimeException("No non-empty job items found.");
        }

        WebElement viewRole = firstJob.findElement(viewRoleButton);

        String originalWindow = driver.getWindowHandle();
        Set<String> oldWindows = driver.getWindowHandles();

        viewRole.click();

        // Yeni sekme
        wait.withTimeout(Duration.ofSeconds(10))
                .until(d -> d.getWindowHandles().size() > oldWindows.size());

        // Yeni sekmeye geç
        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(originalWindow)) {
                driver.switchTo().window(handle);
                break;
            }
        }

        return new LeverPage(driver);
    }

    private String normalize(String s) {
        if (s == null) return "";
        return s.toLowerCase(Locale.ROOT)
                .replace("ı", "i")
                .replace("İ", "i")
                .replace("ş", "s")
                .replace("Ş", "s")
                .replace("ğ", "g")
                .replace("Ğ", "g")
                .replace("ç", "c")
                .replace("Ç", "c")
                .replace("ö", "o")
                .replace("Ö", "o")
                .replace("ü", "u")
                .replace("Ü", "u")
                .trim();
    }
}
