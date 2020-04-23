package tests;

import enums.UrlEnum;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.HomePage;

import static com.codeborne.selenide.Selenide.*;

public class TestStory {

    HomePage homePage;

    @BeforeTest
    public void openWebPage() {
        homePage = open(UrlEnum.HOME_PAGE.getUrl(), HomePage.class);
    }

    @Test
    public void verifySavedJobsTest() throws InterruptedException {
        homePage.createNewAccount()
                .verifyUrl()
                .fillCorrectEmailAddressField("test@gmail.com")
                .fillCorrectPasswordAndConfirmField("Polska2017!")
                .selectJobsCorpsCenter()
                .confirmTermsAndConditions()
                .confirmCreateAccount()
                .verifyUrl();
        Thread.sleep(5000);
    }
}
