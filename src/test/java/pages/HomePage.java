package pages;

import enums.PageWaitElementEnum;
import helpers.AbstractPage;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.page;

public class HomePage extends AbstractPage {

    private final String createAccountButton = "//span[contains(text(),'CREATE ACCOUNT')]";

    public CreateAccountPage createNewAccount() {
        $x(createAccountButton).click();
        waitForNextPageToLoad(PageWaitElementEnum.CREATE_ACCOUNT_PAGE);
        return page(CreateAccountPage.class);
    }

}
