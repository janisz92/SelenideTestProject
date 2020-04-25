package pages;

import com.codeborne.selenide.SelenideElement;
import model.JobOffer;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.$$;

public class SavedJobsPage {

    private final String jobTitleXPath = ".//a";
    private final String companyNameXPath = ".//*[@title='Company']";
    private final String companyLocationXPath = ".//*[@title='Location']";
    private final String jobOfferCardXPath = "//div[contains(@class,'main')]//div[@class='list-body']";

    private List<JobOffer> visibleJobOffersElements() {
        List<JobOffer> visibleJobsOffers = new ArrayList<>();
        List<SelenideElement> numberOfVisibleJobOffers = $$(By.xpath(jobOfferCardXPath));
        numberOfVisibleJobOffers.forEach(x -> {
            String title = x.$(By.xpath(jobTitleXPath)).text();
            String company = x.$(By.xpath(companyNameXPath)).text();
            String location = x.$(By.xpath(companyLocationXPath)).text();
            JobOffer jobOffer = new JobOffer(title, company, location);
            visibleJobsOffers.add(jobOffer);
        });
        return visibleJobsOffers;
    }

    public void verifyVisibilityOfSavedJobs(List<JobOffer> savedJobOffers) {
        List<JobOffer> visibleJobsOffers = visibleJobOffersElements();

        Assert.assertEquals(visibleJobsOffers.size(), savedJobOffers.size());

        for (int y = 0; y < savedJobOffers.size(); y++) {
            int finalY = y;
            Assert.assertTrue(savedJobOffers.stream().anyMatch(x ->
                    x.getCompany().equals(visibleJobsOffers.get(finalY).getCompany())));
            Assert.assertTrue(savedJobOffers.stream().anyMatch(x ->
                    x.getJobTitle().equals(visibleJobsOffers.get(finalY).getJobTitle())));
            Assert.assertTrue(savedJobOffers.stream().anyMatch(x ->
                    x.getLocation().equals(visibleJobsOffers.get(finalY).getLocation())));
        }

    }
}
