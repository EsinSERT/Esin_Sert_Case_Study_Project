package com.insider.qa.tests;

import com.insider.qa.pages.CareersPage;
import com.insider.qa.pages.HomePage;
import com.insider.qa.pages.LeverPage;
import com.insider.qa.pages.QaJobsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class InsiderUiTest extends BaseTest {

    @Test
    public void should_list_qa_jobs_after_applying_filters() {

        String location = "Berlin";
        String department = "Quality Assurance";

        // 1) Home page
        HomePage homePage = new HomePage(getDriver());
        homePage.open();

        Assert.assertTrue(
                homePage.isHomePageLoaded(),
                "Home page is not loaded or main blocks are missing."
        );

        // 2) Navigate to QA Careers
        CareersPage careersPage = homePage.goToQaCareers();

        // 3) Open QA jobs and apply filters
        QaJobsPage qaJobsPage = careersPage
                .clickSeeAllQaJobs()
                .filterByLocationAndDepartment(location, department);

        // 4) Verify job list is present
        Assert.assertTrue(
                qaJobsPage.isJobListPresent(),
                "Job list is not present after filtering."
        );

        // 5) Verify job cards match filters
        Assert.assertTrue(
                qaJobsPage.allJobsMatchFilters(location, department),
                "Not all jobs match the expected filters."
        );
        Assert.assertTrue(
                qaJobsPage.atLeastOneJobMatches(location, department),
                "No job matches the expected Location/Department after filtering."
        );

        // 6) Open first job detail (View Role)
        LeverPage leverPage = qaJobsPage.clickFirstJobViewRole();

        // 7) Verify Lever application page opened
        Assert.assertTrue(
                leverPage.isLeverApplicationFormOpened(),
                "Lever application form page is not opened."
        );
    }
}
