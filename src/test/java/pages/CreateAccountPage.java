package pages;

import enums.PageWaitElementEnum;
import helpers.AbstractPage;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.Condition.enabled;

public class CreateAccountPage extends AbstractPage {


    private final String emailInput = "//*[@id='c_elem_0']";
    private final String passwordInput = "//*[@id='a_elem_1']";
    private final String confirmPasswordInput = "//*[@id='a_elem_2']";
    private final String jobsCorpsCenterSelect = "//*[@id='elem_3']";
    private final String termsAndConditionsCheckbox = "//span[contains(@class, 'checkbox-custom')]";
    private final String confirmCreateAccountButton = "//button[contains(text(),'Create an account')]";

    public CreateAccountPage fillCorrectEmailAddressField(String email) {
        log.info("Email: " + email);
        $x(emailInput).sendKeys(email);
        return this;
    }

    public CreateAccountPage fillCorrectPasswordAndConfirmField(String password) {
        log.info("Password: " + password);
        $x(passwordInput).clear();
        $x(passwordInput).sendKeys(password);
        $x(confirmPasswordInput).clear();
        $x(confirmPasswordInput).sendKeys(password);
        return this;
    }

    public CreateAccountPage selectJobsCorpsCenter(String text) {
        $x(jobsCorpsCenterSelect).selectOption(text);
        return this;
    }

    public CreateAccountPage confirmTermsAndConditions() {
        $x(termsAndConditionsCheckbox).click();
        return this;
    }

    public DashboardPage confirmCreateAccount() {
        $x(confirmCreateAccountButton).should(enabled);
        $x(confirmCreateAccountButton).click();
        waitForNextPageToLoad(PageWaitElementEnum.DASHBOARD_PAGE);
        return page(DashboardPage.class);
    }
}
