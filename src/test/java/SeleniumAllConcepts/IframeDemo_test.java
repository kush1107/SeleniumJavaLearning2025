package SeleniumAllConcepts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class IframeDemo_test {

    WebDriver driver;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void iframeDemoTest() throws InterruptedException {
        // 1. Navigate to iframe demo
        driver.get("https://www.lambdatest.com/selenium-playground/iframe-demo/");

        // 2. Switch to iframe using index / id / WebElement
        WebElement iframeElement = driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame(iframeElement);

        // 3. Inside iframe – interact with element
        WebElement textAreaInsideIframe =
                driver.findElement(By.xpath("//div[@class='rsw-ce']"));
        textAreaInsideIframe.click();
        textAreaInsideIframe.clear();
        textAreaInsideIframe.sendKeys("Hello I'm an Automation Tester.....");

        Thread.sleep(2000);

        // 4. Switch back to main content
        driver.switchTo().defaultContent();

        System.out.println("Returned to main page: " + driver.getTitle());
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
