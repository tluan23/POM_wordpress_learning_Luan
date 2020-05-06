package com.wordpress.login;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Login_01_ValidateLoginForm {
    WebDriver driver;
    By emailTextBoxBy = By.xpath("//input[@id='usernameOrEmail']");
    By passwordTextBoxBy = By.xpath("//input[@id='password']");
    By loginButtonBy = By.xpath("//div[@class='login__form-action']/button");
    By emailErrorMessageBy = By.xpath("//div[@class='form-input-validation is-error']/span");
    By passwordErrorMessageBy = By.xpath("//div[@class='form-input-validation is-error']/span");
    @BeforeClass
    public void beforeClass() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @BeforeMethod
    public void beforeMethod() {
        driver.get("https://automationfc.wordpress.com/wp-admin");
    }

    @Test
    public void validate_00_validPassword() {
        driver.findElement(emailTextBoxBy).sendKeys("automationeditor");
        driver.findElement(loginButtonBy).click();
        driver.findElement(passwordTextBoxBy).sendKeys("automationfc");
        driver.findElement(loginButtonBy).click();

        Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='Dashboard']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//div[@id='dashboard-widgets-wrap']")).isDisplayed());
    }
    @Test
    public void validate_01_EmptyEmail() {
        driver.findElement(emailTextBoxBy).sendKeys("");
        driver.findElement(loginButtonBy).click();
        String errorMessage = driver.findElement(emailErrorMessageBy).getText().trim();

        Assert.assertEquals(errorMessage, "Please enter a username or email address.");
    }

    @Test
    public void validate_02_InvalidEmail() {

    }

    @Test
    public void validate_03_EmailNotExists() {
        driver.findElement(emailTextBoxBy).sendKeys("automation" + randomNumber() + "@gmail.com");
        driver.findElement(loginButtonBy).click();
        String errorMessage = driver.findElement(emailErrorMessageBy).getText().trim();

        Assert.assertEquals(errorMessage,"User does not exist.\n Would you like to \n create a new account\n");
    }

    @Test
    public void validate_04_EmptyPassword() {
        driver.findElement(emailTextBoxBy).sendKeys("automationeditor");
        driver.findElement(loginButtonBy).click();
        driver.findElement(passwordTextBoxBy).sendKeys("");
        driver.findElement(loginButtonBy).click();

        Assert.assertEquals(driver.findElement(passwordErrorMessageBy).getText().trim(), "Don't forget to enter your password.");

    }

    @Test
    public void validate_05_PasswordLessThan6Chars() {
        driver.findElement(emailTextBoxBy).sendKeys("automationeditor");
        driver.findElement(loginButtonBy).click();
        driver.findElement(passwordTextBoxBy).sendKeys("123");
        driver.findElement(loginButtonBy).click();
        String errorMessage = driver.findElement(passwordErrorMessageBy).getText().trim();

        Assert.assertEquals(errorMessage, "Oops, that's not the right password. Please try again!");
    }

    @AfterTest
    public void afterClass() {
        driver.quit();
    }

    public int randomNumber() {
        Random rand = new Random();
        return rand.nextInt(19999);
    }
}
