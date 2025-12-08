package SeleniumAllConcepts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class WebTables_test {

    WebDriver driver;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void webTableReadTest() {

        driver.get("https://www.tutorialspoint.com/selenium/practice/webtables.php");

        // Locate the table
        WebElement table = driver.findElement(By.xpath("//table"));

        // Fetch all rows (header + body)
        List<WebElement> rows = table.findElements(By.xpath(".//tr"));
        System.out.println("Total Rows (including header): " + rows.size());

        System.out.println("===== Web Table Data =====");

        for (WebElement row : rows) {

            // Header cells <th>
            List<WebElement> headers = row.findElements(By.xpath(".//th"));
            if (!headers.isEmpty()) {
                for (WebElement h : headers) {
                    System.out.print(h.getText() + "\t");
                }
                System.out.println();
            }

            // Data cells <td>
            List<WebElement> cells = row.findElements(By.xpath(".//td"));
            if (!cells.isEmpty()) {
                for (WebElement c : cells) {
                    System.out.print(c.getText() + "\t");
                }
                System.out.println();
            }
        }

        System.out.println("===== Web Table Read Test Completed =====");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
