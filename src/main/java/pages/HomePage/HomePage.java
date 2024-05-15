package pages.HomePage;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void navigateToHomePage() {
        driver.get("https://tiki.vn/");
    }

    public void clickAccountButton() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Tài khoản']"))).click();
    }

    public void enterPhoneNumber(String phoneNumber) {
        WebElement phoneInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("tel")));
        phoneInput.clear();
        phoneInput.sendKeys(phoneNumber);
    }

    public void clickContinueButton() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Tiếp Tục']"))).click();
    }

    public void enterPassword(String password) {
        WebElement passwordInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Mật khẩu']")));
        passwordInput.sendKeys(password);
    }

    public void clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Đăng Nhập']"))).click();
    }

    public WebElement getSearchInput() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[data-view-id='main_search_form_input']")));
    }

    public void searchForItem(String keyword) {
        WebElement searchInput = getSearchInput();
        searchInput.clear();
        searchInput.sendKeys(keyword + Keys.ENTER);
    }

    public void waitForSearchResults() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a.breadcrumb-item"))); // Điều chỉnh locator nếu cần
    }

    public By getSearchResultLocator() {
        return By.cssSelector("a.breadcrumb-item"); // Điều chỉnh locator tùy theo cấu trúc của trang web
    }

    // Thêm các phương thức cho Bộ Lọc và Sắp Xếp
    public void applyFilter() {
        WebElement categoryLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='điện gia dụng']")));
        categoryLink.click();
        WebElement filterOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Có remote điều khiển']")));
        filterOption.click();
    }

    public void applySorting(String sortingOption) {
        WebElement sortingDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".sorting-dropdown"))); // Điều chỉnh selector nếu cần
        sortingDropdown.click();
        WebElement sortingOptionElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'" + sortingOption + "')]")));
        sortingOptionElement.click();
    }
}
