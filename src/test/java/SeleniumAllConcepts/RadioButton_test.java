package SeleniumAllConcepts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class RadioButton_test {

    WebDriver driver;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void radioButtonSelection() {
        driver.get("https://www.tutorialspoint.com/selenium/practice/radio-button.php");

        // Locators
        WebElement yesRadio = driver.findElement(By.xpath("//input[@value='igottwo']"));
        WebElement impressiveRadio = driver.findElement(By.xpath("//input[@value='igotthree']"));
        WebElement noRadio = driver.findElement(By.cssSelector("input[value='option3']")); // Disabled

        // Actions
        yesRadio.click();
        System.out.println("Clicked YES → isSelected(): " + yesRadio.isSelected());

        impressiveRadio.click();
        System.out.println("Clicked IMPRESSIVE → isSelected(): " + impressiveRadio.isSelected());

        // Disabled element check
        System.out.println("NO Radio Enabled? → " + noRadio.isEnabled());

        try {
            Thread.sleep(1500); // avoid in real-time, use WebDriverWait
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("====== Radio Button Test Completed ======");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
