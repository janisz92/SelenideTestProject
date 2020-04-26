package tests;

import configuration.TestConfiguration;
import enums.MyJobSearchOptionEnum;
import enums.UrlEnum;
import helpers.TestCasesHelper;
import model.JobOffer;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.SearchPage;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.*;

public class TestStory extends TestConfiguration {

    HomePage homePage;
    SearchPage searchPage;

    @BeforeTest
    public void openWebPage() {
        homePage = open(UrlEnum.HOME_PAGE.getUrl(), HomePage.class);
    }

    @Test(dataProvider = "getEmailAndPassword")
    public void verifyOfSavedSecondAndLastJobTest(String validEmail, String validPassword) {
        List<JobOffer> savedJobOffers = new ArrayList<>();
        List<JobOffer> visibleSavedJobsInSavedJobsPage;

        searchPage = homePage
                .createNewAccount()
                .fillCorrectEmailAddressField(validEmail)
                .fillCorrectPasswordAndConfirmField(validPassword)
                .selectJobsCorpsCenter("To edit the items in here change messages 389442-389565")
                .confirmTermsAndConditions()
                .confirmCreateAccount()
                .goForPhilipsJobs()
                .getJobsOffersFromAllPages();


        int lastOfferOnPage = searchPage.visibleJobOffersElements().size();
        savedJobOffers.add(searchPage.chooseAndSaveOfferByNumber(2));
        savedJobOffers.add(searchPage.chooseAndSaveOfferByNumber(lastOfferOnPage));

        visibleSavedJobsInSavedJobsPage = searchPage
                .chooseOptionFromMyJobSearchDropdown(MyJobSearchOptionEnum.SAVED_JOBS)
                .visibleJobOffersElements();

        Assert.assertTrue(JobOffer.compareListOfJobOfferObjects(savedJobOffers,visibleSavedJobsInSavedJobsPage));

    }


    @DataProvider(name = "getEmailAndPassword")
    public Object[][] dataProviderMethod() {
        return new Object[][]{
                {TestCasesHelper.generateValidMail(), "Password1234!"}
        };
    }
}
