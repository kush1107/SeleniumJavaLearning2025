package SeleniumAllConcepts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class UploadFile_test {

    public static WebDriver driver;
    String baseUrl = "https://www.tutorialspoint.com/selenium/practice/upload-download.php";

    @BeforeTest
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void uploadFile() throws Exception {

        driver.get(baseUrl);

        // Create file for uploading
        String uploadFolder = System.getProperty("user.dir") + File.separator + "upload";
        String uploadFile = uploadFolder + File.separator + "testUpload.txt";
        Files.createDirectories(Paths.get(uploadFolder));
        Path filePath = Paths.get(uploadFile);
        if (!Files.exists(filePath)) Files.createFile(filePath);

        List<String> content = Arrays.asList(
                "Sample file for upload testing",
                "Line 1", "Line 2"
        );
        Files.write(filePath, content, StandardOpenOption.TRUNCATE_EXISTING);
        driver.findElement(By.id("uploadFile")).sendKeys(filePath.toString());
        System.out.println("Uploaded File: " + filePath);
    }

    @AfterTest
    public void closeDriver() {
        driver.quit();
    }
}
