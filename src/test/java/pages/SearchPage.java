package pages;

import com.codeborne.selenide.SelenideElement;
import enums.MyJobSearchOptionEnum;
import enums.PageWaitElementEnum;
import helpers.AbstractPage;
import model.JobOffer;
import org.openqa.selenium.By;

import java.util.List;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;

public class SearchPage extends AbstractPage {

    private final String searchResult = "//div[@class='mux-search-results']";
    private final String saveJobButton = "//*[@id='SaveJob']";
    private final String myJobSearchDropdown = "//a[@id='dropdown-My-job-search']/following-sibling::ul";
    private final String getMyJobSearchTab = "//a[@id='dropdown-My-job-search']//parent::li";
    private final String jobTitleXPath = ".//*[@class='title']";
    private final String companyNameXPath = ".//*[@class='company']";
    private final String companyLocationXPath = ".//*[@class='location']";
    private final String jobOfferCardXPath = "//*[@id='SearchResults']/section";
    private final String totalJobsResultAttribute = "data-results-total";
    private final String jobsResultPerPage = "data-results-per-page";



    public List<SelenideElement> visibleJobOffersElements() {
        return $$(By.xpath(jobOfferCardXPath));
    }

    private Integer getPageAmountToDisplayAllOffers() {
        int totalJobsResult = Integer.parseInt($x(searchResult).getAttribute(totalJobsResultAttribute));
        int offersPerPage = Integer.parseInt($x(searchResult).getAttribute(jobsResultPerPage));

        if (totalJobsResult % offersPerPage != 0)
            return (totalJobsResult / offersPerPage) + 1;
        else
            return totalJobsResult / offersPerPage;
    }

    public SearchPage getJobsOffersFromAllPages() {
        //TODO bug to report, to get all job offers I need to change url, its impossible via scroll, even manually i can scroll to 4 page max
        open(String.format("%s&stpage=1&page=%s",url(),getPageAmountToDisplayAllOffers()));
        return this;
    }

    public JobOffer chooseAndSaveOfferByNumber(int offerNumber) {
        List<SelenideElement> allJobOffersElements = visibleJobOffersElements();

            allJobOffersElements.get(offerNumber - 1).click();

            String title = allJobOffersElements.get(offerNumber - 1).$(By.xpath(jobTitleXPath)).text();
            String company = allJobOffersElements.get(offerNumber - 1).$(By.xpath(companyNameXPath)).text();
            String location = allJobOffersElements.get(offerNumber - 1).$(By.xpath(companyLocationXPath)).text();

            $x(saveJobButton).click();
            log.info("Saved job title: " + title);
            log.info("Saved job company: " + company);
            log.info("Saved job location: " + location);

            return new JobOffer(title, company, location);
    }

    public SavedJobsPage chooseOptionFromMyJobSearchDropdown(MyJobSearchOptionEnum searchOption) {
        $x(getMyJobSearchTab).hover();
        $x(myJobSearchDropdown).$(By.xpath(".//*[contains(text(),'" + searchOption.getText() + "')]")).click();
        waitForNextPageToLoad(PageWaitElementEnum.SAVED_JOBS_PAGE_FULL);
        return page(SavedJobsPage.class);
    }

}
