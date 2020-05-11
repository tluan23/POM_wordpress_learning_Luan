package pageObjects.bankguru;

import commons.AbstractPage;
import org.openqa.selenium.WebDriver;

public class RegisterPageObject extends AbstractPage {
    WebDriver driver;

    public RegisterPageObject(WebDriver driver) {
        this.driver = driver;
    }
}
