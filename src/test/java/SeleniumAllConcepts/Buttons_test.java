package SeleniumAllConcepts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Buttons_test {

    WebDriver driver;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void buttonsInteractionTest() throws InterruptedException {
        driver.get("https://www.tutorialspoint.com/selenium/practice/buttons.php");

        // 1. Simple click button
        WebElement clickMeButton = driver.findElement(By.xpath("//button[text()='Click Me']"));
        clickMeButton.click();
        System.out.println("Clicked 'Click Me' → Current URL / Result: " + driver.getCurrentUrl());

        // 2. Right-click (context click) button
        WebElement rightClickButton = driver.findElement(By.xpath("//button[text()='Right Click Me']"));
        Actions actions = new Actions(driver);
        actions.contextClick(rightClickButton).perform();
        System.out.println("Performed right-click on 'Right Click Me' button.");

        // 3. Double-click button
        WebElement doubleClickButton = driver.findElement(By.xpath("//button[text()='Double Click Me']"));
        actions.doubleClick(doubleClickButton).perform();
        System.out.println("Performed double-click on 'Double Click Me' button.");

        // Optional: wait a bit to observe (not for real testing)
        Thread.sleep(2000);

        System.out.println("====== Buttons Test Completed ======");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
