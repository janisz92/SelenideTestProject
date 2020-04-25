package helpers;

import com.codeborne.selenide.SelenideElement;
import org.apache.log4j.Logger;
import org.openqa.selenium.support.ui.WebDriverWait;


import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class PageHelper {

    private WebDriverWait wait = new WebDriverWait(getWebDriver(), 5L);
    protected Logger log = Logger.getLogger(PageHelper.class);

    public void waitABit(int millisecondToWait) {
        try {
            Thread.sleep(millisecondToWait);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void waitForNextPageToLoad(SelenideElement elementFromNextPage) {
        wait.until(x -> elementFromNextPage.exists());
    }
}
