package SeleniumAllConcepts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class Download_test {

    WebDriver driver;
    String baseUrl = "https://www.tutorialspoint.com/selenium/practice/upload-download.php";
    // Set your download path
    String downloadPath = System.getProperty("user.dir") + File.separator + "downloads";

    @BeforeTest
    public void setup() {
        // Create download folder if not exists
        File folder = new File(downloadPath);
        if (!folder.exists()) {
            folder.mkdir();
        }
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("download.default_directory", downloadPath);
        prefs.put("download.prompt_for_download", false);
        prefs.put("download.directory_upgrade", true);
        prefs.put("safebrowsing.enabled", true);
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterTest
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void DownloadTest()  {
        driver.get(baseUrl);
        driver.findElement(By.id("downloadButton")).click();
        // FluentWait: Wait until file is fully downloaded
        File downloadedFile = waitForDownloadedFile(downloadPath);
        if (downloadedFile != null) {
            System.out.println("Downloaded File: " + downloadedFile.getAbsolutePath());
        } else {
            System.out.println("Download failed or timed out.");
        }
    }


    public File waitForDownloadedFile(String downloadPath) {

        Wait<File> wait = new FluentWait<>(new File(downloadPath))
                .withTimeout(Duration.ofSeconds(40))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(Exception.class);

        return wait.until(folder -> {
            File[] files = folder.listFiles();
            if (files == null || files.length == 0) {
                return null;
            }
            for (File file : files) {
                String name = file.getName();
                // Skip temp files
                if (name.endsWith(".crdownload") || name.endsWith(".tmp")) {
                    continue;
                }
                // Found a completed file
                return file;
            }
            return null;
        });
    }
}


