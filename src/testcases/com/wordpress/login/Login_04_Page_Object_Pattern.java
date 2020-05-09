package com.wordpress.login;

import commons.AbstractPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pageObjects.DashboardPageObject;
import pageObjects.LoginPageObject;

import java.util.concurrent.TimeUnit;

public class Login_04_Page_Object_Pattern extends AbstractPage {
    AbstractPage abstractPage;
    WebDriver driver;
    LoginPageObject loginPage;
    DashboardPageObject dashboardPage;


    @BeforeClass
    public void beforeClass() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        abstractPage = new AbstractPage();
        loginPage = new LoginPageObject(driver);
        abstractPage.openURL(driver,"https://automationfc.wordpress.com/wp-admin");

    }

    @Test
    public void validate_01_EmptyEmail() {
        abstractPage.openURL(driver,"https://automationfc.wordpress.com/wp-admin");

        loginPage.inputToEmailTextBox("");
        loginPage.clickToContinueOrLoginButton();
        String errorMessage = loginPage.getErrorMessage();

        Assert.assertEquals(errorMessage, "Please enter a username or email address.");
    }

    @Test
    public void validate_03_EmailNotExists() {
        abstractPage.openURL(driver,"https://automationfc.wordpress.com/wp-admin");

        loginPage.inputToEmailTextBox("123@123");
        loginPage.clickToContinueOrLoginButton();
        String errorMessage = loginPage.getErrorMessage();

        Assert.assertEquals(errorMessage, "User does not exist. Would you like to create a new account?");
    }

    @Test
    public void validate_04_EmptyPassword() {
        abstractPage.openURL(driver,"https://automationfc.wordpress.com/wp-admin");

        loginPage.inputToEmailTextBox("123@123");
        loginPage.clickToContinueOrLoginButton();

        loginPage.inputPasswordTextBox("");
        loginPage.clickToContinueOrLoginButton();
        String errorMessage = loginPage.getErrorMessage();

        Assert.assertEquals(errorMessage, "Don't forget to enter your password.");
    }

    @Test
    public void Validate_05_PasswordLessThan6Chars() {
        abstractPage.openURL(driver,"https://automationfc.wordpress.com/wp-admin");

        loginPage.inputToEmailTextBox("automationeditor");
        loginPage.clickToContinueOrLoginButton();

        loginPage.inputPasswordTextBox("zxc");
        loginPage.clickToContinueOrLoginButton();
        String errorMessage = loginPage.getErrorMessage();
        Assert.assertEquals(errorMessage, "Oops, that's not the right password. Please try again!");

    }

    @Test
    public void Validate_06_ValidPassword() {
        abstractPage.openURL(driver,"https://automationfc.wordpress.com/wp-admin");

        loginPage.inputToEmailTextBox("automationeditor");
        loginPage.clickToContinueOrLoginButton();

        loginPage.inputPasswordTextBox("automationfc");
        loginPage.clickToContinueOrLoginButton();

        dashboardPage = new DashboardPageObject(driver);
        boolean isHeaderDashboardDisplayed = dashboardPage.isHeaderDisplayed();
        Assert.assertTrue(isHeaderDashboardDisplayed);
    }

    @AfterTest
    public void tearDown() throws Exception {
        driver.quit();
    }
}
