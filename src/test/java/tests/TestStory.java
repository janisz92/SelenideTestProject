package tests;

import configuration.TestConfiguration;
import enums.MyJobSearchOptionEnum;
import enums.UrlEnum;
import helpers.TestCasesHelper;
import model.JobOffer;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.HomePage;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.*;

public class TestStory extends TestConfiguration {

    HomePage homePage;

    @BeforeTest
    public void openWebPage() {
        homePage = open(UrlEnum.HOME_PAGE.getUrl(), HomePage.class);
    }

    @Test(dataProvider="getEmailAndPassword")
    public void verifyOfSavedSecondAndLastJobTest(String validEmail, String validPassword) {
        List<JobOffer> savedJobOffers = new ArrayList<>();

        homePage.createNewAccount()
                .verifyUrl(UrlEnum.CREATE_ACCOUNT_PAGE.getUrl())
                .fillCorrectEmailAddressField(validEmail)
                .fillCorrectPasswordAndConfirmField(validPassword)
                .selectJobsCorpsCenter(1)
                .confirmTermsAndConditions()
                .confirmCreateAccount()
                .verifyUrl(UrlEnum.DASHBOARD_PAGE.getUrl())
                .goForPhilipsJobs()
                .getJobsOffersFromAllPages()
                .chooseAndSaveOfferByNumber(savedJobOffers, 2)
                .chooseAndSaveLastOffer(savedJobOffers)
                .goToMySavedJobsPage(MyJobSearchOptionEnum.SAVED_JOBS.getText())
                .verifyVisibilityOfSavedJobs(savedJobOffers);
    }


    @DataProvider(name = "getEmailAndPassword")
    public Object[][] dataProviderMethod() {
        return new Object[][]{
                {TestCasesHelper.generateValidMail(), "Password1234!"}
        };
    }
}
