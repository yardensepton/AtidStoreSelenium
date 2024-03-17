package testPack;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseTest {

    public static WebDriver initializeDriver() {
        WebDriver driver = new ChromeDriver();
        return driver;
    }
}

