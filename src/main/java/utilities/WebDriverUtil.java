package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverUtil {

    public static WebDriver getDriver() {
        return new ChromeDriver();
    }
}
