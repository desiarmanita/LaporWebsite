//import io.github.bonigarcia.wdm.WebDriverManager;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeDriverService;
//import org.openqa.selenium.chrome.ChromeOptions;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.time.Duration;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterClass;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.Duration;


public class Main {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeClass
    public void setup() {

        System.setProperty("webdriver.chrome.driver", "C:\\Windows\\chromedriver\\chromedriver-win64\\chromedriver.exe");
        WebDriverManager.chromedriver().setup();// Ganti dengan path yang sesuai
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://lapor.folkatech.com");
    }

    @Test(priority = 1)
    public void testValidLogin() {
        driver.get("https://lapor.folkatech.com");

        WebElement emailField = driver.findElement(By.name("email"));
        emailField.sendKeys("admin@example.com");

        WebElement passwordField = driver.findElement(By.name("password"));
        passwordField.sendKeys("password");

        WebElement signIn = driver.findElement(By.xpath("//button[text()='Sign in']"));
        signIn.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dashboard")));

        String expectedUrl = "https://lapor.folkatech.com/dashboard";
        Assert.assertEquals(driver.getCurrentUrl(), expectedUrl, "Login berhasil.");
    }

   @Test(priority = 2)
    public void testInvalidLogin() {
        driver.get("https://lapor.folkatech.com");

       WebElement emailField = driver.findElement(By.id("email"));
       emailField.sendKeys("admin@example.com");

       WebElement passwordField = driver.findElement(By.name("password"));
       passwordField.sendKeys("password");

       WebElement signIn = driver.findElement(By.xpath("//button[text()='Sign in']"));
       signIn.click();
       WebElement errorMessage = driver.findElement(By.xpath("//label[contains(text(), 'Login Gagal')]"));
       System.out.println(errorMessage.getText());
    }

    @AfterMethod
    public void takeScreenshotOnFailure(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            try {
                TakesScreenshot screenshot = (TakesScreenshot) driver;
                File source = screenshot.getScreenshotAs(OutputType.FILE);
                String screenshotPath = "screenshots/" + result.getName() + ".png";
                Files.copy(source.toPath(), new File(screenshotPath).toPath());
                System.out.println("Screenshot disimpan: " + screenshotPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
 }
}
}