package com.bankguru;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.bankguru.HomePageObject;
import pageObjects.bankguru.LoginPageObject;
import pageObjects.bankguru.RegisterPageObject;

import java.util.concurrent.TimeUnit;

public class Login_01_RegisterAndLogin {
    WebDriver driver;
    LoginPageObject loginPage;
    RegisterPageObject registerPage;
    String userIDValue, passwordValue, loginPageUrl;
    @BeforeClass
    public void beforeClass() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        driver.get("http://demo.guru99.com/v4");
        loginPage = new LoginPageObject(driver);
        loginPageUrl = loginPage.getLoginPageUrl();
    }

    @Test
    public void TC_01_Register() {

        loginPage.clickToHereLink();
        //
        registerPage = new RegisterPageObject(driver);
        registerPage.inputToEmailTextBox();
        registerPage.clickToSubmitButton();
        userIDValue = registerPage.getUserIdText();
        passwordValue = registerPage.getPasswordText();
        //back to login page
        registerPage.openLoginPage(loginPageUrl);
        //khoi tao lai login page
        loginPage = new LoginPageObject(driver);

    }

    @Test
    public void TC_02_Login() {
        loginPage.inputToUserIDTextBox();
        loginPage.inputToPasswordTextBox();
        loginPage.clickToLoginButton();

        homePage = new HomePageObject(driver);
        homePage.isGreetingMessageDisplayed();
    }

    @Test
    public void TC_03_Something_TODO() {

    }
}
