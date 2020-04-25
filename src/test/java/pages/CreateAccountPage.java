package pages;

import com.codeborne.selenide.SelenideElement;
import helpers.PageHelper;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.WebDriverRunner.url;

public class CreateAccountPage extends PageHelper {

    @FindBy(id = "c_elem_0")
    private SelenideElement emailInput;

    @FindBy(id = "a_elem_1")
    private SelenideElement passwordInput;

    @FindBy(id = "a_elem_2")
    private SelenideElement confirmPasswordInput;

    @FindBy(id = "elem_3")
    private SelenideElement jobsCorpsCenterSelect;

    @FindBy(xpath = "//span[contains(@class, 'checkbox-custom')]")
    private SelenideElement termsAndConditionsCheckbox;

    @FindBy(xpath = "//button[contains(text(),'Create an account')]")
    private SelenideElement confirmCreateAccountButton;

    @FindBy(xpath = "//*[contains(text(),'My Checklist')]")
    private SelenideElement dashboardPageElement;



    public CreateAccountPage verifyUrl(String url) {
        Assert.assertEquals(url(), url);
        return this;
    }

    public CreateAccountPage fillCorrectEmailAddressField(String email) {
        emailInput.sendKeys(email);
        return this;
    }

    public CreateAccountPage fillCorrectPasswordAndConfirmField(String password) {
        passwordInput.clear();
        passwordInput.sendKeys(password);
        confirmPasswordInput.clear();
        confirmPasswordInput.sendKeys(password);
        return this;
    }

    public CreateAccountPage selectJobsCorpsCenter() {
        jobsCorpsCenterSelect.selectOption(1);
        return this;
    }

    public CreateAccountPage confirmTermsAndConditions() {
        termsAndConditionsCheckbox.click();
        return this;
    }

    public DashboardPage confirmCreateAccount() {
        confirmCreateAccountButton.click();
        waitForNextPageToLoad(dashboardPageElement);
        return page(DashboardPage.class);
    }
}
