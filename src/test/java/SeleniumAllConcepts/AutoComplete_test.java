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

public class AutoComplete_test {

    WebDriver driver;
    WebDriverWait wait;
    String searchKey="as"; //Base on search key auto-suggestion list is displayed
    String selectOption="JavaScript"; //This is needed to be selected from auto-suggestion list

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void autoCompleteTest() {

        // 1. Navigate to Auto Complete practice page
        driver.get("https://www.tutorialspoint.com/selenium/practice/auto-complete.php");

        // 2. Locate Auto Complete input box
        WebElement autoCompleteInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='tags']")));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='tags']")));

        // 3. Enter partial text
        autoCompleteInput.click();
        autoCompleteInput.sendKeys(searchKey);

        // 4. Wait for auto suggestions
        List<WebElement> suggestions = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(
                        By.xpath("//ul[@id='ui-id-1']/li")
                )
        );

        // 5. Iterate and select required option
        for (WebElement suggestion : suggestions) {
            String text = suggestion.getText();
            System.out.println("Suggestion: " + text);

            if (text.equalsIgnoreCase(selectOption)) {
                suggestion.click();
                break;
            }
        }

        // 6. Validation
        String selectedValue = autoCompleteInput.getAttribute("value");
        System.out.println("Selected Value: " + selectedValue);

        System.out.println("====== Auto Complete Test Completed ======");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
