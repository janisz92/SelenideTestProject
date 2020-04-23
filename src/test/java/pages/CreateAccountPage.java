package pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.WebDriverRunner.url;

public class CreateAccountPage {

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


    public CreateAccountPage verifyUrl() {
        Assert.assertEquals(url(), "https://www.monsterworksdemo.com/account/account-lite");
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

    public DashboardPage confirmCreateAccount() throws InterruptedException {
        confirmCreateAccountButton.click();
        Thread.sleep(5000);
        return page(DashboardPage.class);
    }

}
