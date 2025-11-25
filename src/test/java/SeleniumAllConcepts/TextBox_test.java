package SeleniumAllConcepts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TextBox_test {

    WebDriver driver;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void fillTextBoxForm() {
        driver.get("https://www.tutorialspoint.com/selenium/practice/text-box.php");

        // Locators
        WebElement fullName = driver.findElement(By.id("fullname"));
        WebElement email = driver.findElement(By.id("email"));
        WebElement currentAddress = driver.findElement(By.xpath("//textarea[@id='address']"));
        WebElement submitBtn = driver.findElement(By.cssSelector("input[value='Submit']"));
        WebElement password = driver.findElement(By.xpath("//input[@id='password']"));

        // Actions
        fullName.sendKeys("Kushal Parikh");
        email.sendKeys("kushal@example.com");
        currentAddress.click(); // Before inputting text - always on field to make it active
        currentAddress.sendKeys("Bangalore, India, XYZ XYZ XYZ XYZ XYZ XYZ XYZ");
        password.sendKeys("Pass@123"); // In real-time we don't show password(use config file)

        submitBtn.click();

        try {
            Thread.sleep(1500); // avoid for real framework, use waits
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("====== Details Submitted ======");

    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
