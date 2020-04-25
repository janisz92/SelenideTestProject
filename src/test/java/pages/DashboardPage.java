package pages;

import com.codeborne.selenide.SelenideElement;
import helpers.PageHelper;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.WebDriverRunner.url;

public class DashboardPage extends PageHelper {

    @FindBy(xpath = "//a[contains(text(),'Philips Jobs')]")
    private SelenideElement philipsJobsLink;

    @FindBy(xpath = "//div[@class='mux-search-results']")
    private SelenideElement searchPageElement;

    public DashboardPage verifyUrl(String url) {
        Assert.assertEquals(url(), url);
        return this;
    }

    public SearchPage goForPhilipsJobs() {
        philipsJobsLink.click();
        waitForNextPageToLoad(searchPageElement);
        return page(SearchPage.class);
    }
}
