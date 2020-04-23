package pages;

import org.testng.Assert;

import static com.codeborne.selenide.WebDriverRunner.url;

public class DashboardPage {

    public DashboardPage verifyUrl() {
        Assert.assertEquals(url(), "https://www.monsterworksdemo.com/dashboard/");
        return this;
    }
}
