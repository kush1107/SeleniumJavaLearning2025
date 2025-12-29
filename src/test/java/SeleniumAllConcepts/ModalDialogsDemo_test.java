package SeleniumAllConcepts;

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

public class ModalDialogsDemo_test {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void modalDialogDemoTest() {

        // 1. Navigate to Modal Dialogs page
        driver.get("https://www.tutorialspoint.com/selenium/practice/modal-dialogs.php");

        // 2. Click on Small Modal button
        WebElement smallModalBtn = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//button[normalize-space()='Small Modal']")));

        if (smallModalBtn.isEnabled()) {
            smallModalBtn.click();
        }


        // 3. Wait for small modal to appear
        WebElement smallModal =
                wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//div[@class='modal-dialog modal-sm']//button[@class='btn btn-primary'][normalize-space()='Close']")));

        // 4. Close Small Modal
        smallModal.click();

        // 5. Click on Large Modal button
        WebElement largeModalBtn = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//button[normalize-space()='Large Modal']")));
        if (largeModalBtn.isEnabled()) {
            largeModalBtn.click();
        }

        // 6. Wait for large modal to appear
        WebElement largeModal =
                wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[@data-bs-target='#exampleModalLg'][normalize-space()='Close']")));

        // 7. Close Large Modal
        largeModal.click();

        System.out.println("Modal Dialog test completed successfully");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
