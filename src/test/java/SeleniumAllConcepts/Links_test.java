package SeleniumAllConcepts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class Links_test {

    public WebDriver driver;

    @BeforeClass
    public void setup() {
        driver = new FirefoxDriver();
    }

    @Test
    public void linksInteractionTest(){
        driver.get("https://www.tutorialspoint.com/selenium/practice/links.php");

        System.out.println("=== Testing Links That Open in New Tab ===");
        // 1. Click "Home" (opens a new tab)
        WebElement home = driver.findElement(By.linkText("Home"));
        home.click();
        switchToNewTab();
        System.out.println("Clicked 'Home' → New Tab URL: " + driver.getCurrentUrl());
        driver.close();
        switchBackToMainTab();

        // 2. Click "HomewPWPU" (opens new tab)
        WebElement home2 = driver.findElement(By.linkText("HomewPWPU"));
        home2.click();
        switchToNewTab();
        System.out.println("Clicked 'HomewPWPU' → New Tab URL: " + driver.getCurrentUrl());
        driver.close();
        switchBackToMainTab();

        System.out.println("=== Testing Links That Send an API Call ===");


        clickApiLink("Created"); // Validate request in backend
        clickApiLink("No Content");// Validate request in backend
        clickApiLink("Moved");// Validate request in backend
        clickApiLink("Bad Request");// Validate request in backend
        clickApiLink("Unauthorized");// Validate request in backend
        clickApiLink("Forbidden");// Validate request in backend
        clickApiLink("Not Found");// Validate request in backend

        System.out.println("====== Links Test Completed ======");
    }

    // Helper Method 1 – Switch to new tab
    public void switchToNewTab() {
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
    }

    // Helper Method 2 – Switch back to main tab
    public void switchBackToMainTab() {
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(0));
    }

    // Helper Method 3 – Click API links and capture request to validate status code  - we will see in upcoming learning
    public void clickApiLink(String linkText) {
        try {
            WebElement link = driver.findElement(By.linkText(linkText));
            link.click();
            //try to capture request & intercept it & validate status code

        } catch (Exception e) {
            System.out.println("Error in API validation: " + e.getMessage());
        }
    }


    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
