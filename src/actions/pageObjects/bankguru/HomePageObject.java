package pageObjects.bankguru;

import commons.AbstractPage;
import org.openqa.selenium.WebDriver;

public class HomePageObject extends AbstractPage {
    WebDriver driver;

    public HomePageObject(WebDriver driver) {
        this.driver = driver;
        System.out.println("Show me your driver:" + driver.toString());
    }
}
