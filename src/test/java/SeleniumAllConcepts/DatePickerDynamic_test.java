package SeleniumAllConcepts;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DatePickerDynamic_test {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.manage().window().maximize();
        driver.get("https://www.tutorialspoint.com/selenium/practice/date-picker.php");

        // Open Date Picker
        WebElement dateInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("datetimepicker1")));
        wait.until(ExpectedConditions.elementToBeClickable(By.id("datetimepicker1")));
        dateInput.click();

        // ===== TARGET Calender Values =====

        int targetYear =2018;
        int targetDate =2;
        String targetMonth ="December";
        String targetHour = "09";
        String targetMin = "15";
        String target_am_pm = "am";



        while (true) {

            // Get displayed month
            WebElement dropdownMonth = driver.findElement(By.xpath("(//select[@aria-label='Month'])[1]"));
            Select select = new Select(dropdownMonth);
            String selectedMonth = select.getFirstSelectedOption().getText();

            // Get displayed year
            WebElement yearInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input.numInput.cur-year")));
            JavascriptExecutor js = (JavascriptExecutor) driver;
            Object yearObj = js.executeScript("return arguments[0].value;", yearInput);
            String selectedYear=yearObj.toString();
            int displayedYear = Integer.parseInt(selectedYear);

            // Year navigation logic
            if (targetYear == displayedYear && selectedMonth.equals(targetMonth)){
                System.out.println("Selected Month: " + selectedMonth);
                System.out.println("Selected Year: " + displayedYear);
                break;
            } else if (targetYear > displayedYear ) {
                // Click next month button
                WebElement nextBtn = driver.findElement(By.cssSelector(".flatpickr-next-month"));
                nextBtn.click();
            } else if (targetYear < displayedYear ) {
                // Click previous month button
                WebElement prevBtn = driver.findElement(By.cssSelector(".flatpickr-prev-month"));
                prevBtn.click();
            }
        }

        WebElement date = driver.findElement(By.xpath("(//span[@class='flatpickr-day' and normalize-space()="+targetDate+"])[1]"));
        date.click();

        WebElement hour = driver.findElement(By.xpath("(//input[@aria-label='Hour'])[1]"));
        hour.sendKeys(targetHour);
        WebElement min = driver.findElement(By.xpath("(//input[@aria-label='Minute'])[1]"));
        min.sendKeys(targetMin);

        WebElement am_pm_select = driver.findElement(By.xpath("(//span[@title='Click to toggle'][normalize-space()='PM'])[1]"));

        if (!am_pm_select.getText().equalsIgnoreCase(target_am_pm)) {
            am_pm_select.click();
        }


    }
}
