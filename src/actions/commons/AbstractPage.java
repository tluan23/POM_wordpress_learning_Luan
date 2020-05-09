package commons;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;

import static com.sun.jmx.snmp.ThreadContext.contains;

public class AbstractPage {
    private Select select;
    private JavascriptExecutor jsExecutor;
    private WebDriverWait waitExplicit;
    private List<WebElement> elements;
    private WebElement element;
    public Actions action;
    private long shortTimeout = 5;
    private long longTimeout = 30;

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
               new WebDriverWait(driver, longTimeout);
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

   public String getElementAttribute(WebDriver driver, String locator) {
       return findElementByXpath(locator, driver).getText();
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
        element = findElementByXpath(locator,driver);
        element.clear();
        element.click();
    }

    public String getElementText(WebDriver driver, String locator) {
       return findElementByXpath(locator,driver).getText().trim();
    }

    public void selectValueInDropdown(WebDriver driver, String locator, String value) {
       Select select = new Select( findElementByXpath(locator,driver));
       select.selectByVisibleText(value);
    }

    public String getSelectItemInDropdown( WebDriver driver, String locator) {
       select = new Select( findElementByXpath(locator,driver));
       return select.getFirstSelectedOption().getText();
    }

    public void selectItemInCustomDropDown(WebDriver driver, String parentXpath, String allItemXpath, String expectedValueItem) throws InterruptedException {
       WebElement parentDropdown = findElementByXpath(parentXpath,driver);
       jsExecutor = (JavascriptExecutor) driver;
       jsExecutor.executeScript("arguments[0].click();", parentDropdown);
       Thread.sleep(1000);
       waitExplicit = new WebDriverWait(driver, longTimeout);
       waitExplicit.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemXpath)));
       List<WebElement> allItems = driver.findElements(By.xpath(allItemXpath));

        for (WebElement childElement : allItems) {
            if (childElement.getText().equals(expectedValueItem)) {
                childElement.click();
            } else {
                jsExecutor.executeScript("arguments[0].scrollIntoView(true);", childElement);
                Thread.sleep(1000);
                jsExecutor.executeScript("arguments[0].click();", childElement);
            }
            Thread.sleep(1000);
            break;
        }
    }

    public int countElementNumber(WebDriver driver, String locator) {
        elements = findElementsByXpath(locator, driver);
        return elements.size();
    }

    private List<WebElement> findElementsByXpath(String locator, WebDriver driver) {
        return driver.findElements(byXpath(locator));
    }

    public void checkToCheckBox(WebDriver driver, String locator) {
        element = findElementByXpath(locator, driver );
        if(!element.isSelected()) {
            element.click();
        }
    }

    public void uncheckToCheckbox(WebDriver driver, String locator) {
       element = findElementByXpath(locator, driver);
       if (element.isSelected()) {
           element.click();
       }
   }

   public boolean isElementDisplayed(WebDriver driver, String locator) {
       return findElementByXpath(locator, driver).isDisplayed();
   }

   public void switchToFrameOrIFrame(WebDriver driver, String locator) {
       driver.switchTo().frame(findElementByXpath(locator, driver));
   }

   public void switchToDefaultContent(WebDriver driver) {
       driver.switchTo().defaultContent();
   }

   public void hoverMouseToElement(WebDriver driver, String locator) {
       action = new Actions(driver);
       action.moveToElement(findElementByXpath(locator, driver)).perform();
   }

    public void doubleClickToElement(WebDriver driver, String locator) {
        action = new Actions(driver);
        action.doubleClick(findElementByXpath(locator, driver)).perform();
    }

    public void rightClickToElement(WebDriver driver, String locator) {
        action = new Actions(driver);
        action.contextClick(findElementByXpath(locator, driver)).perform();
    }

    public Object executeScript(WebDriver driver, String javascript) {
       jsExecutor = (JavascriptExecutor) driver;
       return jsExecutor.executeScript(javascript);
    }

//    public boolean verifyTextInInnerText(WebDriver driver, String textExpect) {
//       jsExecutor = (JavascriptExecutor) driver;
//       String textActual = (String) jsExecutor
//               .executeScript("return document.documentElement.innerText.match(  )
//       jsExecutor.executeScript("")
//    }

    public void scrollToElement(String locator, WebDriver driver) {
       jsExecutor = (JavascriptExecutor) driver;
       element = driver.findElement(By.xpath(locator));
       jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
       jsExecutor = (JavascriptExecutor) driver;
       element = driver.findElement(By.xpath(locator));
       jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", element);
    }

    // isImage loading
//    public boolean isImageLoaded(WebDriver driver, String locator) {
//       jsExecutor = (JavascriptExecutor) driver;
//       element = driver.findElement(By.xpath(locator));
//       boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0]" +
//               ".naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", element);
//    }


    //-------Wait section
    public void waitForElementVisible(WebDriver driver, String locator) {
       waitExplicit = new WebDriverWait(driver, longTimeout);
       waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(byXpath(locator)));
   }

   public void waitForElementInvisible(WebDriver driver, String locator) {
       waitExplicit = new WebDriverWait(driver, longTimeout);
       waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(byXpath(locator)));
   }

   public void waitForElementClickAble(WebDriver driver, String locator) {
       waitExplicit = new WebDriverWait(driver, longTimeout);
       waitExplicit.until(ExpectedConditions.elementToBeClickable(byXpath(locator)));
   }
}


