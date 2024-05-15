package tests;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import java.time.Duration;

public class HomeTest {

    @Test
    public void runTests() throws InterruptedException, IOException {
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // Navigate to Home Page
            driver.get("https://tiki.vn/");

            // Đăng nhập
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Tài khoản']"))).click();
            WebElement phoneInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("tel")));
            phoneInput.clear();
            phoneInput.sendKeys("0858895157");
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Tiếp Tục']"))).click();
            WebElement passwordInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Mật khẩu']")));
            passwordInput.sendKeys("Tranquangduy2002");
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Đăng Nhập']"))).click();
            Thread.sleep(10000);

            // Case 1: Tìm kiếm và chụp hình cho "iphone 15 pro max"
            searchAndCapture(driver, wait, "iphone 15 pro max", "src/files/HomePageScreenShot/search-result-iphone.png");

            // Case 2: Xóa kết quả tìm kiếm và nhập vào " " rồi chụp hình
            WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[data-view-id='main_search_form_input']")));
            searchInput.sendKeys(Keys.CONTROL + "a");
            searchInput.sendKeys(Keys.DELETE);
            searchInput.sendKeys(" " + Keys.ENTER);
            waitForSearchResults(wait);  // Đợi kết quả tìm kiếm hiện lên
            Thread.sleep(5000);  // Đợi thêm 5 giây để đảm bảo trang tải hoàn toàn
            captureScreen(driver, "src/files/HomePageScreenShot/search-result-blank.png");

            // Case 3: Áp dụng bộ lọc và sắp xếp
            applyFilterAndSort(driver, wait);
            captureScreen(driver, "src/files/HomePageScreenShot/filtered-result.png");

        } finally {
            driver.quit();
        }
    }

    public void searchAndCapture(WebDriver driver, WebDriverWait wait, String keyword, String filePath) throws InterruptedException, IOException {
        WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[data-view-id='main_search_form_input']")));
        searchInput.clear();
        searchInput.sendKeys(keyword + Keys.ENTER);
        waitForSearchResults(wait);
        Thread.sleep(5000);  // Đợi thêm 5 giây để đảm bảo trang tải hoàn toàn
        captureScreen(driver, filePath);
    }

    public void waitForSearchResults(WebDriverWait wait) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a.breadcrumb-item")));
    }

    public void applyFilterAndSort(WebDriver driver, WebDriverWait wait) throws InterruptedException {
        WebElement categoryLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='điện gia dụng']")));
        categoryLink.click();
        WebElement filterOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Có remote điều khiển']")));
        filterOption.click();
        Thread.sleep(5000);  // Đợi thêm 5 giây để áp dụng bộ lọc

        // Mở dropdown sắp xếp
        WebElement sortingDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Phổ biến']")));
        sortingDropdown.click();

        // Chọn tùy chọn sắp xếp "Giá thấp đến cao"
        WebElement sortingOptionElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Giá thấp đến cao']")));
        sortingOptionElement.click();
        Thread.sleep(5000);  // Đợi thêm 5 giây để áp dụng sắp xếp
    }

    public static void captureScreen(WebDriver driver, String fileName) throws IOException {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File(fileName));
    }
}
