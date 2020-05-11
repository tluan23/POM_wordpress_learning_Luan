package pageObjects.bankguru;

import commons.AbstractPage;
import org.openqa.selenium.WebDriver;

public class LoginPageObject extends AbstractPage {
    WebDriver driver;

    public LoginPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public RegisterPageObject clickToHereLink() {
        return new RegisterPageObject(driver);
    }
}
