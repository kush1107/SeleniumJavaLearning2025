package SeleniumAllConcepts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.List;

import static org.testng.Assert.fail;

public class BrokenImages_test {

    public WebDriver driver;
    private static String url = "https://www.lambdatest.com/selenium-playground/broken-image";
    private static int iBrokenImageCount = 0;

    @BeforeTest
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(25));
    }

    @AfterTest
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void VerifyBrokenImages() {
        driver.get(url);
        try {
            iBrokenImageCount = 0;
            List<WebElement> image_list = driver.findElements(By.xpath("//div[@class='learn_right']//img"));
            /* Print the total number of images on the page */
            System.out.println("The page under test has " + image_list.size() + " images");
            for (WebElement img : image_list) {
                if (img != null) {
                    String src = img.getAttribute("src");
                    if (isImageBroken(src) || (img.getAttribute("naturalWidth").equals("0") && img.getAttribute("naturalHeight").equals("0"))) {
                        System.out.println(img.getAttribute("outerHTML") + " is broken.");
                        iBrokenImageCount++;
                    }
                }
            }
            System.out.println("The page " + url + " has " + iBrokenImageCount + " broken images");
        } catch (Exception e) {
            fail("Test is failed due to : " + e.getMessage());
        }
    }

    private boolean isImageBroken(String imageUrl) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(imageUrl).openConnection();
            connection.setRequestMethod("HEAD");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            connection.connect();

            int responseCode = connection.getResponseCode();
            return responseCode == 404 || responseCode >= 400; // Check for 404 and any 4xx or 5xx errors
        } catch (IOException e) {
            return true; // Treat any exception as a broken image
        }
    }
}
