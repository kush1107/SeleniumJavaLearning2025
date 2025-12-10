package SeleniumAllConcepts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;

public class DynamicProperties_test {

    WebDriver driver;
    String url = "https://www.tutorialspoint.com/selenium/practice/dynamic-prop.php";

    @BeforeTest
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void dynamicPropertiesTest() {

        driver.get(url);

        WebElement colorBtn = driver.findElement(By.id("colorChange"));
        colorBtn.click();
        //FluentWait for "Visible After 5 Seconds" Button  - Use Fluent wait over Explicit wait
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(40))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(Exception.class);
        WebElement visibleBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("visibleAfter")));
        visibleBtn.click();
        System.out.println("Clicked visible button!");
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
