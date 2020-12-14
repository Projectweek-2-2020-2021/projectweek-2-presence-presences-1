package ucll.project.ui;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.util.Map;

class ChromeDriverHelper {
    static ChromeDriver getDriver() {
        // Setup the Chrome driver for the whole class
        WebDriverManager.chromedriver().setup();
        ChromeDriver driver;
        if (System.getenv("DEBIAN_FRONTEND") != null && System.getenv("DEBIAN_FRONTEND").equals("noninteractive")) {
            // These options are required for running the selenium tests in a docker container
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless", "--no-sandbox", "window-size=1200,1100");
            driver = new ChromeDriver(options);
        } else {
            // These are useful for running it locally
            driver = new ChromeDriver();
            driver.manage().window().maximize();
        }
        return driver;
    }
}
