package commons;

import org.openqa.selenium.WebDriver;

public abstract class AbstractPage {

   public void openURL(WebDriver driver, String urlValue) {
        driver.get(urlValue);
   }

   public String getPageTitle(WebDriver driver) {
       return driver.getTitle();
   }

   public String getCurrentUrl(WebDriver driver) {
       return driver.getCurrentUrl();
   }

   
}
