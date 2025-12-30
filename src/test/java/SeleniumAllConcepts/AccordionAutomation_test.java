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
import java.util.List;

public class AccordionAutomation_test {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void accordionTest() throws InterruptedException {

        // 1️⃣ Navigate to Accordion page
        driver.get("https://www.tutorialspoint.com/selenium/practice/accordion.php");

        // 2️⃣ Locate all accordion headers
        List<WebElement> accordionHeaders = driver.findElements(
                By.xpath("//div[@id='accordionExample']/following::button"));

        System.out.println("Total Accordion Sections: " + accordionHeaders.size());

        // 3️⃣ Iterate through all accordion items
        for (int i = 0; i < accordionHeaders.size(); i++) {
            WebElement header = accordionHeaders.get(i);
            // Expand section
            wait.until(ExpectedConditions.elementToBeClickable(header)).click();
            // Wait for content to be visible
            WebElement body = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[@id='collapseTwentyOne']//p[@class='text-justify']")));
            System.out.println(body.getText());
        }
        System.out.println("\nAccordion test completed successfully!");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
