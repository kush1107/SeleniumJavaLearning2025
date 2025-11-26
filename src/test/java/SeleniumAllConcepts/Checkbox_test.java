package SeleniumAllConcepts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Checkbox_test {

    WebDriver driver;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void handleCheckBoxes() {
        driver.get("https://www.tutorialspoint.com/selenium/practice/check-box.php");

        // ==== Locators ====
        WebElement mainLevel1 = driver.findElement(By.id("c_bs_1"));
        WebElement mainLevel2 = driver.findElement(By.id("c_bs_2"));


        WebElement plusMainLevel1 = driver.findElement(By.xpath("(//span[@class='plus'])[1]"));
        WebElement plusSubLevel1 = driver.findElement(By.cssSelector("li[id='bf_1'] span[class='plus']"));
        WebElement plusSubLevel2 = driver.findElement(By.cssSelector("li[id='bf_2'] span[class='plus']"));

        // ==== Actions ====
        clickCheckbox(mainLevel1); // Clicking checkbox -1
        plusMainLevel1.click(); // Expanding checkbox
        plusSubLevel1.click(); // Expanding checkbox
        plusSubLevel2.click(); // Expanding checkbox
        clickCheckbox(mainLevel2); // Clicking checkbox -2

        System.out.println("====== All Checkboxes Selected Successfully ======");

        unCheck_Checkbox(mainLevel1); // Uncheck checkbox -1
        unCheck_Checkbox(mainLevel2); // Uncheck checkbox -2

        System.out.println("====== All Checkboxes UnSelected Successfully ======");
    }

    // Reusable Method to click checkbox properly
    public void clickCheckbox(WebElement element) {
        if (!element.isSelected()) {
            element.click();
        }
    }

    // Reusable Method to uncheck checkbox properly
    public void unCheck_Checkbox(WebElement element) {
        if (element.isSelected()) {
            element.click();
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
