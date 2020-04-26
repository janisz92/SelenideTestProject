package helpers;

import enums.PageWaitElementEnum;
import org.apache.log4j.Logger;
import org.openqa.selenium.support.ui.WebDriverWait;


import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class AbstractPage {

    private WebDriverWait wait = new WebDriverWait(getWebDriver(), 5L);
    protected Logger log = Logger.getLogger(AbstractPage.class);

    public void waitABit(int millisecondToWait) {
        try {
            Thread.sleep(millisecondToWait);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void waitForNextPageToLoad(PageWaitElementEnum pageToWait) {
        wait.until(x -> $x(pageToWait.getXPathElement()).exists());
    }
}
