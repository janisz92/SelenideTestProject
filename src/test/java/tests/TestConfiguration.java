package tests;

import com.codeborne.selenide.Configuration;
import org.testng.annotations.BeforeSuite;

public class TestConfiguration {

    @BeforeSuite
    public void setUpConfiguration() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
        Configuration.browser = "chrome";
    }
}
