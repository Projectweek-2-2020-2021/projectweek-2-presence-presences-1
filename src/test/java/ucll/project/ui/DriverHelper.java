package ucll.project.ui;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.UnreachableBrowserException;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class DriverHelper {
    private static WebDriver driver;

    public static WebDriver getDriver(){

        try {
            DesiredCapabilities capability = DesiredCapabilities.firefox();
            driver = new RemoteWebDriver(capability);
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
            return driver;

        } catch (UnreachableBrowserException e) {
            WebDriverManager.chromedriver().setup();
            ChromeDriver driver;
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            Config.BASE_URL = "http://localhost:8080/project_ucll_war_exploded/Controller";
            return driver;
        }
    }
}