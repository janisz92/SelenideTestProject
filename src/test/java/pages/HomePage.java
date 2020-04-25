package pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Selenide.page;

public class HomePage {

    @FindBy(xpath = "//span[contains(text(),'CREATE ACCOUNT')]")
    private SelenideElement createAccountButton;

    public CreateAccountPage createNewAccount() {
        createAccountButton.click();
        return page(CreateAccountPage.class);
    }

}
