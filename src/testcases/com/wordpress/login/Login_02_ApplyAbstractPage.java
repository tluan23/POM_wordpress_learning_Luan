package com.wordpress.login;

import commons.AbstractPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Login_02_ApplyAbstractPage {
    AbstractPage abstractPage;
    WebDriver driver;
    String emailTextBoxBy = "//input[@id='usernameOrEmail']";
    String passwordTextBoxBy = "//input[@id='password']";
    String loginButtonBy = "//div[@class='login__form-action']/button";
    String emailErrorMessageBy = "//div[@class='form-input-validation is-error']/span";
    String passwordErrorMessageBy = "//div[@class='form-input-validation is-error']/span";


    @BeforeClass
    public void beforeClass() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        abstractPage = new AbstractPage();
    }

    @BeforeMethod
    public void beforeMethod() {
        abstractPage.openURL(driver,"https://automationfc.wordpress.com/wp-admin");
    }

    @Test
    public void validate_01_EmptyEmail() {
        abstractPage.sendKeyToElement(driver, emailTextBoxBy, "");
        abstractPage.clickToElement(driver, loginButtonBy);

        Assert.assertEquals(abstractPage.getElementText(driver, emailErrorMessageBy)," Please log in using your");
    }

    @Test
    public void Validate_02_InvalidEmail() {
        abstractPage.sendKeyToElement(driver, emailTextBoxBy, "123@123.com");
        abstractPage.clickToElement(driver, loginButtonBy);

        Assert.assertEquals(abstractPage.getElementText(driver, emailErrorMessageBy)," User does not exist.");
    }

    @Test
    public void Validate_05_PasswordLEssThan6Chars() {
        abstractPage.sendKeyToElement(driver, emailTextBoxBy, "automationeditor");
        abstractPage.clickToElement(driver, loginButtonBy);

        abstractPage.sendKeyToElement(driver, passwordTextBoxBy, "123");
        abstractPage.clickToElement(driver, loginButtonBy);

    }

    @AfterMethod
    public void tearDown() throws Exception {
        driver.close();
    }
}
