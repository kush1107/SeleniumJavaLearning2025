package SeleniumAllConcepts;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Alerts_test {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void alertsHandlingTest() throws InterruptedException {
        // 1. Navigate to alerts practice
        driver.get("https://www.tutorialspoint.com/selenium/practice/alerts.php");

        // =========================
        // Simple Alert
        // =========================
        WebElement simpleAlertBtn = driver.findElement(By.xpath("//button[normalize-space()='Alert']"));
        simpleAlertBtn.click();
        Alert simpleAlert = driver.switchTo().alert();
        System.out.println("Simple Alert Text: " + simpleAlert.getText());
        simpleAlert.accept();  // Click OK

        Thread.sleep(1000);

        // =========================
        // Button where alert appears after 5 seconds
        // =========================
        WebElement delayedAlertBtn = driver.findElement(By.xpath("//button[@onclick='myMessage()']"));
        delayedAlertBtn.click();
        System.out.println("Clicked button. Waiting for alert...");
        // Explicit wait for alert to appear
        Alert delayedAlert = wait.until(ExpectedConditions.alertIsPresent());

        System.out.println("Delayed Alert Text: " + delayedAlert.getText());
        delayedAlert.accept();
        System.out.println("====== Delayed Alert Handled Successfully ======");

        // =========================
        //Confirmation Alert
        // =========================
        WebElement confirmBtn = driver.findElement(By.xpath("//button[@onclick='myDesk()']"));
        confirmBtn.click();
        Alert confirmAlert = driver.switchTo().alert();
        System.out.println("Confirmation Alert Text: " + confirmAlert.getText());
        confirmAlert.accept();  // Accept OK
        // OR confirmAlert.dismiss();  // Click Cancel

        Thread.sleep(1000);

        // =========================
        //Prompt Alert
        // =========================
        WebElement promptBtn = driver.findElement(By.xpath("//button[@onclick='myPromp()']"));
        promptBtn.click();

        Alert promptAlert = driver.switchTo().alert();
        System.out.println("Prompt Alert Text: " + promptAlert.getText());
        promptAlert.sendKeys("Selenium");  // Enter text
        promptAlert.accept();  // Click OK

        Thread.sleep(1000);

        System.out.println("====== Alerts Handling Completed ======");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
