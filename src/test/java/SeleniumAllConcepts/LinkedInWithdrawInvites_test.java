package SeleniumAllConcepts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class LinkedInWithdrawInvites_test {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(12));
    }

    @Test
    public void withdrawSentInvitesTest() throws InterruptedException {

        driver.get("https://www.linkedin.com/mynetwork/invitation-manager/sent/");

        // 🔐 LOGIN (not recommended to hardcode – keeping as per your code)
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")))
                .sendKeys("kushalparikh123@gmail.com");

        driver.findElement(By.id("password"))
                .sendKeys("Dark.Web1107@1999kp");

        driver.findElement(By.xpath("//button[@type='submit']")).click();

        // Wait for sent invitations page to load
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@data-testid='lazy-column']")));

        int count = 0;

        // 🔁 LOOP UNTIL NO WITHDRAW BUTTON EXISTS
        while (true) {

            List<WebElement> withdrawButtons = driver.findElements(
                    By.xpath("//button[.//span[normalize-space()='Withdraw']]"));

            // ❌ Exit condition
            if (withdrawButtons.size() == 0) {
                System.out.println("No more Withdraw buttons found");
                break;
            }

            // Always click FIRST visible Withdraw button
            WebElement withdrawBtn = withdrawButtons.get(0);
            wait.until(ExpectedConditions.elementToBeClickable(withdrawBtn)).click();

            // Wait for confirmation popup
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//h2[normalize-space()='Withdraw invitation']")));

            WebElement confirmBtn = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[contains(@aria-label,'Withdraw')]")));

            confirmBtn.click();

            count++;
            System.out.println("Withdrawn invitation count: " + count);

            // ⏳ Slow down to avoid LinkedIn restriction
            Thread.sleep(2500);
        }

        System.out.println("Finished withdrawing all sent invitations!");
    }


    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
