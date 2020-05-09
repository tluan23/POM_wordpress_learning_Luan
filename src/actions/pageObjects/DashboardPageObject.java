package pageObjects;

import commons.AbstractPage;
import org.openqa.selenium.WebDriver;
import pageUI.DashboardPageUI;

public class DashboardPageObject extends AbstractPage {
    WebDriver driver;

    public DashboardPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isHeaderDisplayed() {
        waitForElementVisible(driver, DashboardPageUI.HEADER_TEXT);
        return isElementDisplayed(driver, DashboardPageUI.HEADER_TEXT);
    }
}
