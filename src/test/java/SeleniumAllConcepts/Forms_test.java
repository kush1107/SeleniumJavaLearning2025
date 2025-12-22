package SeleniumAllConcepts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;

public class Forms_test {

    WebDriver driver;
    String url = "https://www.tutorialspoint.com/selenium/practice/selenium_automation_practice.php";

    @BeforeTest
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void studentRegistrationFormTest() {

        driver.get(url);

        // FluentWait configuration
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(Exception.class);

        // Name
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("name")))
                .sendKeys("Kushal Parikh");

        // Email
        driver.findElement(By.name("email"))
                .sendKeys("kushal@test.com");

        // Gender
        driver.findElement(By.xpath("//input[@id='gender']")).click();

        // Mobile Number
        driver.findElement(By.name("mobile"))
                .sendKeys("9876543210");

        // Date of Birth
        driver.findElement(By.name("dob"))
                .sendKeys("11-07-1999");

        // Subjects
        driver.findElement(By.name("subjects"))
                .sendKeys("Automation Testing");

        // Hobbies
        driver.findElement(By.xpath("//input[@id='hobbies']")).click();
        driver.findElement(By.xpath("//label[normalize-space()='Reading']/preceding-sibling::input")).click();


        // Create file for uploading
        String uploadFile = System.getProperty("user.dir") + File.separator + "upload"+ File.separator + "testUpload.txt";

        driver.findElement(By.xpath("//input[@id='picture']")).sendKeys(uploadFile);
        System.out.println("Uploaded File: " + uploadFile);

        // Current Address
        driver.findElement(By.xpath("//textarea[@id='picture']"))
                .sendKeys("Automation Testing is the Future of Testing");

        // State dropdown
        Select state = new Select(driver.findElement(By.xpath("//select[@id='state']")));
        state.selectByVisibleText("NCR");

        // City dropdown
        Select city = new Select(driver.findElement(By.xpath("//select[@id='city']")));
        city.selectByVisibleText("Agra");

        // Submit Button
        WebElement submitBtn = wait.until(
                ExpectedConditions.elementToBeClickable(By.cssSelector("input[value='Login']"))
        );
        submitBtn.click();

        System.out.println("Form submitted successfully!");
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
