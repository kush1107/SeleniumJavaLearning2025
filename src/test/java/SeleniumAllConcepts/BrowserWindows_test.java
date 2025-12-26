package SeleniumAllConcepts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Set;

public class BrowserWindows_test {

    WebDriver driver;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void browserWindowsTest() throws InterruptedException {

        driver.get("https://www.tutorialspoint.com/selenium/practice/browser-windows.php");

        String parentWindow = driver.getWindowHandle();
        System.out.println("Parent Window Handle: " + parentWindow);

        // =============================
        // 1. New Tab
        // =============================
        WebElement newTabBtn = driver.findElement(By.xpath("//button[text()='New Tab']"));
        newTabBtn.click();
        Thread.sleep(1500);

        switchToChildAndClose(parentWindow, "New Tab");

        // =============================
        // 2. New Window
        // =============================
        WebElement newWindowBtn = driver.findElement(By.xpath("//button[text()='New Window']"));
        newWindowBtn.click();
        Thread.sleep(1500);

        switchToChildAndClose(parentWindow, "New Window");

        // =============================
        // 3. New Window Message
        // =============================
        WebElement newWindowMsgBtn =
                driver.findElement(By.xpath("//button[text()='New Window Message']"));
        newWindowMsgBtn.click();
        Thread.sleep(1500);

        switchToChildAndClose(parentWindow, "New Window Message");

        System.out.println("====== Browser Windows Test Completed ======");
    }

    // =============================
    // Helper Method
    // =============================
    private void switchToChildAndClose(String parentWindow, String actionName) {

        Set<String> allWindows = driver.getWindowHandles();

        for (String window : allWindows) {
            if (!window.equals(parentWindow)) {
                driver.switchTo().window(window);

                System.out.println(actionName + " → Title: " + driver.getTitle());
                System.out.println(actionName + " → Body Text: "
                        + driver.findElement(By.tagName("body")).getText());

                driver.close();
                break;
            }
        }
        driver.switchTo().window(parentWindow);
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
