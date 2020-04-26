package pages;

import enums.PageWaitElementEnum;
import helpers.AbstractPage;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.page;

public class DashboardPage extends AbstractPage {

    private final String philipsJobsLink = "//a[contains(text(),'Philips Jobs')]";


    public SearchPage goForPhilipsJobs() {
        $x(philipsJobsLink).click();
        waitForNextPageToLoad(PageWaitElementEnum.SEARCH_PAGE);
        return page(SearchPage.class);
    }
}
