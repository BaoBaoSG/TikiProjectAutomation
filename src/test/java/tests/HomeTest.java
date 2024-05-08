package tests;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.HomePage;
import utilities.WebDriverUtil;

import java.time.Duration;

public class HomeTest {

    @Test
    public void testSearch() {
        WebDriver driver = WebDriverUtil.getDriver();
        try {
            HomePage homePage = new HomePage(driver);
            homePage.navigateToHomePage();

            // Thực hiện tìm kiếm với khoảng trắng
            homePage.searchForItem(" ");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("body")));  // Đợi nội dung trang web hiển thị

            boolean searchSuccess = homePage.verifySearchResult(" ");
            if (searchSuccess) {
                System.out.println("Tìm thất bại: Không tìm thấy sản phẩm phù hợp.");
            } else {
                System.out.println("Tìm thất bại.");
            }

            Assert.assertFalse("Không nên tìm thấy sản phẩm khi nhập khoảng trắng", searchSuccess);
        } finally {
            driver.quit();
        }
    }
}
