package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateToHomePage() {
        driver.get("https://tiki.vn/");
    }

    public void searchForItem(String keyword) {
        driver.findElement(By.cssSelector("input[data-view-id='main_search_form_input']")).sendKeys(keyword + "\n");
    }

    public boolean verifySearchResult(String keyword) {
        String pageText = driver.findElement(By.tagName("body")).getText();
        if (keyword.trim().isEmpty()) {
            // Trả về true nếu tìm thấy thông báo không tìm thấy sản phẩm
            return pageText.contains("Rất tiếc, không tìm thấy sản phẩm phù hợp với lựa chọn của bạn");
        } else {
            String[] keywords = keyword.toLowerCase().split(" ");
            for (String word : keywords) {
                if (pageText.toLowerCase().contains(word)) {
                    return true;
                }
            }
            return false;
        }
    }
}
