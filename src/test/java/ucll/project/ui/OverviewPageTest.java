package ucll.project.ui;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class OverviewPageTest {
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

    @Test
    public void overview_contains_Belgium() {
        OverviewPage overviewPage = PageFactory.initElements(driver, OverviewPage.class);
        assertTrue(overviewPage.containsCountryWithName("Belgium"));
    }

    @Test
    public void when_clicked_on_goHome_Homepage_is_shown() {
        //GIVEN
        OverviewPage overviewPage = PageFactory.initElements(driver, OverviewPage.class);
        //WHEN USER CLICKS ON LINK
        HomePage homePage = overviewPage.navigateToHomePage();
        //THEN HOMEPAGE IS SHOWN
        assertEquals("Tourism", homePage.getTitle());
    }
}
