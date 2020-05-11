package pageObjects.wordpress;

import commons.AbstractPage;
import org.openqa.selenium.WebDriver;
import pageUI.wordpress.LoginPageUI;

public class LoginPageObject extends AbstractPage {
    WebDriver driver;

    public LoginPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void inputToEmailTextBox(String value) {
        waitForElementVisible(driver, LoginPageUI.EMAIL_TEXTBOX);
        sendKeyToElement(driver, LoginPageUI.EMAIL_TEXTBOX, value);
    }

    public void clickToContinueOrLoginButton() {
        waitForElementVisible(driver, LoginPageUI.CONTINUE_LOGIN_BUTTON);
        clickToElement(driver, LoginPageUI.CONTINUE_LOGIN_BUTTON);
    }

    public void inputPasswordTextBox(String value) {
        waitForElementVisible(driver, LoginPageUI.PASSWORD_TEXTBOX);
        sendKeyToElement(driver, LoginPageUI.PASSWORD_TEXTBOX, value);
    }

    public String getErrorMessage() {
        waitForElementVisible(driver, LoginPageUI.ERROR_MESSAGE);
        return getElementText(driver, LoginPageUI.ERROR_MESSAGE);
    }
}
