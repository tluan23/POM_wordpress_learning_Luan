package commons;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;

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

   public void back(WebDriver driver) {
       driver.navigate().back();
   }

   public void refresh(WebDriver driver) {
       driver.navigate().refresh();
   }

   public void forward(WebDriver driver) {
       driver.navigate().forward();
   }

   public void acceptAlert(WebDriver driver) {
       driver.switchTo().alert().accept();
   }

   public void cancelAlert(WebDriver driver) {
       driver.switchTo().alert().dismiss();
   }

   public void sendKeyToAlert(WebDriver driver, String value) {
       driver.switchTo().alert().sendKeys(value);
   }

   public String getTextInAlert(WebDriver driver) {
       return driver.switchTo().alert().getText();
   }
    //presence la de check element da co trong DOM
    // visible la hien len man hinh
   public void waitAlertPresence(WebDriver driver) {
       WebDriverWait explicitWait =
               new WebDriverWait(driver, 15);
       explicitWait.until(ExpectedConditions.alertIsPresent());
   }

   public void switchToWindowByID(WebDriver driver, String parentID) {
       Set<String> allWindows = driver.getWindowHandles();
       for (String runWindow: allWindows) {
           if(!runWindow.equals(parentID)) {
               driver.switchTo().window(runWindow);
               break;
           }
       }
   }

   public void switchToWindowByTitle(WebDriver driver, String title) {
       Set<String> allWindows = driver.getWindowHandles();
       for (String runWindow: allWindows) {
           driver.switchTo().window(runWindow);
           String currentWindow = driver.getTitle();
           if(currentWindow.equals(title)) {
               break;
           }
       }
   }

   public boolean areAllWindowsClosedWithoutParent(WebDriver driver, String parentWindow) {
       Set<String> allWindows = driver.getWindowHandles();
       for (String runWindow: allWindows) {
           if(!runWindow.equals(parentWindow)) {
               driver.switchTo().window(runWindow);
               driver.close();
           }
       }
       driver.switchTo().window(parentWindow);
       if(driver.getWindowHandles().size() == 0)
           return true;
       else
           return false;
   }

   //---------------------ACTION------------------------
    public By byXpath(String locator) {
       return By.xpath(locator);
    }

    public WebElement findElementByXpath(String locator, WebDriver driver) {
       return driver.findElement(byXpath(locator));
    }
    public void clickToElement(WebDriver driver, String locator ) {
       findElementByXpath(locator,driver).click();
    }

    public void sendKeyToElement(WebDriver driver, String locator, String  value) {
       findElementByXpath(locator,driver).sendKeys(value);
    }

    public String getElementText(WebDriver driver, String locator) {
       return findElementByXpath(locator,driver).getText();
    }

    public void selectValueInDropdown(WebDriver driver, String locator, String value) {
       Select select = new Select( findElementByXpath(locator,driver));
       select.selectByVisibleText(value);
    }

    public void getSelectItemInDropdown( WebDriver driver, String locator) {
       Select select = new Select( findElementByXpath(locator,driver));
    }
}


