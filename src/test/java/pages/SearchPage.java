package pages;

import com.codeborne.selenide.SelenideElement;
import helpers.PageHelper;
import model.JobOffer;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;

public class SearchPage extends PageHelper {

    private final String jobTitleXPath = ".//*[@class='title']";
    private final String companyNameXPath = ".//*[@class='company']";
    private final String companyLocationXPath = ".//*[@class='location']";
    private final String jobOfferCardXPath = "//*[@id='SearchResults']/section";
    private final String totalJobsResultAttribute = "data-results-total";
    private final String jobsResultPerPage = "data-results-per-page";

    @FindBy(id = "ResultsScrollable")
    private SelenideElement searchResultFrame;

    @FindBy(xpath = "//div[@class='mux-search-results']")
    private SelenideElement searchResult;

    @FindBy(xpath = "//*[@id='SaveJob']")
    private SelenideElement saveJobButton;

    @FindBy(xpath = "//a[@id='dropdown-My-job-search']/following-sibling::ul")
    private SelenideElement myJobSearchDropdown;

    @FindBy(xpath = "//a[@id='dropdown-My-job-search']//parent::li")
    private SelenideElement getMyJobSearchTab;

    @FindBy(xpath = "//div[contains(@class,'main')]//div[@class='list-body']")
    private SelenideElement savedJobsPageElementWithSavedJobOffers;




    private List<SelenideElement> visibleJobOffersElements() {
        return $$(By.xpath(jobOfferCardXPath));
    }

    private Integer getPageAmountToDisplayAllOffers() {
        int totalJobsResult = Integer.parseInt(searchResult.getAttribute(totalJobsResultAttribute));
        int offersPerPage = Integer.parseInt(searchResult.getAttribute(jobsResultPerPage));

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

    public SearchPage chooseAndSaveOfferByNumber(List<JobOffer> savedJobOffers, int... offerNumbers) {
        List<SelenideElement> allJobOffersElements = visibleJobOffersElements();
        for (int offerNumber : offerNumbers) {
            allJobOffersElements.get(offerNumber - 1).click();

            String title = allJobOffersElements.get(offerNumber - 1).$(By.xpath(jobTitleXPath)).text();
            String company = allJobOffersElements.get(offerNumber - 1).$(By.xpath(companyNameXPath)).text();
            String location = allJobOffersElements.get(offerNumber - 1).$(By.xpath(companyLocationXPath)).text();

            JobOffer jobOffer = new JobOffer(title, company, location);
            savedJobOffers.add(jobOffer);
            saveJobButton.click();
            log.info("Saved job title: " + jobOffer.getJobTitle());
            log.info("Saved job company: " + jobOffer.getCompany());
            log.info("Saved job location: " + jobOffer.getLocation());
        }
        return this;
    }

    public SearchPage chooseAndSaveLastOffer(List<JobOffer> savedJobOffers) {
        List<SelenideElement> allJobOffersElements = visibleJobOffersElements();
        allJobOffersElements.get(allJobOffersElements.size() - 1).click();

        String title = allJobOffersElements.get(allJobOffersElements.size() - 1).$(By.xpath(jobTitleXPath)).text();
        String company = allJobOffersElements.get(allJobOffersElements.size() - 1).$(By.xpath(companyNameXPath)).text();
        String location = allJobOffersElements.get(allJobOffersElements.size() - 1).$(By.xpath(companyLocationXPath)).text();

        JobOffer jobOffer = new JobOffer(title, company, location);
        savedJobOffers.add(jobOffer);
        log.info("Saved job title: " + jobOffer.getJobTitle());
        log.info("Saved job company: " + jobOffer.getCompany());
        log.info("Saved job location: " + jobOffer.getLocation());
        saveJobButton.click();
        return this;
    }

    public SavedJobsPage goToMySavedJobsPage(String page) {
        getMyJobSearchTab.hover();
        myJobSearchDropdown.$(By.xpath(".//*[contains(text(),'" + page + "')]")).click();
        waitForNextPageToLoad(savedJobsPageElementWithSavedJobOffers);
        return page(SavedJobsPage.class);
    }

}
