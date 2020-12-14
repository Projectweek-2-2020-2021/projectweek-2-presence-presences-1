package ucll.project.ui;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class FirefoxDriverHelper {
    static FirefoxDriver getDriver() {
        // Setup the Chrome driver for the whole class
        WebDriverManager.firefoxdriver().setup();
        FirefoxDriver driver;
        if (System.getenv("DEBIAN_FRONTEND") != null && System.getenv("DEBIAN_FRONTEND").equals("noninteractive")) {
            // These options are required for running the selenium tests in a docker container
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("--headless", "--window-size=1200,1100");
            driver = new FirefoxDriver(options);
        } else {
            // These are useful for running it locally
            driver = new FirefoxDriver();
            driver.manage().window().maximize();
        }
        return driver;
    }
}
