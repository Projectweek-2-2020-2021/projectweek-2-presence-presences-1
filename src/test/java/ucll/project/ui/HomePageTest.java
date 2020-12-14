package ucll.project.ui;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import static org.junit.Assert.assertEquals;

public class HomePageTest {

    private static WebDriver driver;

    @BeforeClass
    public static void SetupDriver() {
        // Setup the Chrome driver for the whole class
        driver = ChromeDriverHelper.getDriver();
    }

    @AfterClass
    public static void CloseBrowser() {
        // close it in the end, comment this away to keep chrome open
        driver.close();
    }

    /**
     * This is a sample test, remove this test and write your own!
     */

    @Test
    public void NavigationTest_Index_Gives_Homepage() {
        // GIVEN
        HomePage homePage = PageFactory.initElements(driver,HomePage.class);
        //WHEN

        //THEN
        assertEquals("Tourism", driver.getTitle());
    }

}
