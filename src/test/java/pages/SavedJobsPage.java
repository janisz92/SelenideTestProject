package pages;

import com.codeborne.selenide.SelenideElement;
import helpers.AbstractPage;
import model.JobOffer;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.$$x;

public class SavedJobsPage extends AbstractPage {

    private final String jobTitleXPath = ".//a";
    private final String companyNameXPath = ".//*[@title='Company']";
    private final String companyLocationXPath = ".//*[@title='Location']";
    private final String jobOfferCardXPath = "//div[contains(@class,'main')]//div[@class='list-body']";

    public List<JobOffer> visibleJobOffersElements() {
        List<JobOffer> visibleJobsOffers = new ArrayList<>();
        List<SelenideElement> numberOfVisibleJobOffers = $$x(jobOfferCardXPath);
        numberOfVisibleJobOffers.forEach(x -> {
            String title = x.$x(jobTitleXPath).text();
            String company = x.$x(companyNameXPath).text();
            String location = x.$x(companyLocationXPath).text();
            JobOffer jobOffer = new JobOffer(title, company, location);
            visibleJobsOffers.add(jobOffer);
            log.info("Visible job title in ma saved jobs: " + jobOffer.getJobTitle());
            log.info("Visible job company in ma saved jobs: " + jobOffer.getCompany());
            log.info("Visible job location in ma saved jobs:: " + jobOffer.getLocation());
        });
        return visibleJobsOffers;
    }
}
