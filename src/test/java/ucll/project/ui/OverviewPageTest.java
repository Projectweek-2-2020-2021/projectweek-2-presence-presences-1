package ucll.project.ui;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import ucll.project.domain.service.ApplicationService;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

/*public class OverviewPageTest {
    private static WebDriver driver;
    private static ApplicationService applicationService;


    @BeforeClass
    public static void SetupDriver() {
        // Setup the Chrome driver for the whole class
        driver = DriverHelper.getDriver();
        applicationService = new ApplicationService();

    }

    @AfterClass
    public static void CloseBrowser() {
        // close it in the end, comment this away to keep chrome open
        driver.close();
    }

    //@Test
    //public void overview_contains_Belgium() {
    //    OverviewPage overviewPage = PageFactory.initElements(driver, OverviewPage.class);
    //    assertTrue(overviewPage.containsCountryWithName("Belgium"));
    //}

    //@Test
    //public void when_clicked_on_goHome_Homepage_is_shown() {
    //    //GIVEN
    //    OverviewPage overviewPage = PageFactory.initElements(driver, OverviewPage.class);
    //    //WHEN USER CLICKS ON LINK
    //    HomePage homePage = overviewPage.navigateToHomePage();
    //    //THEN HOMEPAGE IS SHOWN
    //    assertEquals("Tourism", homePage.getTitle());
    //}

    /*@Test
    public void overview_shows_correct_most_popular_country() {
        //GIVEN
        OverviewPage overviewPage = PageFactory.initElements(driver, OverviewPage.class);
        /*
        List<Country> countryList =  countryService.getCountries();
        Country currentcountry = countryList.get(0);
        for (Country country: countryList) {
            if (country.getNumberInhabitants() > currentcountry.getNumberInhabitants()){
                currentcountry = country;
            }
        }


        //WHEN USER LOOKS AT MOST POPULAR COUNTRY
        overviewPage.isMostPopularCountry("Spain");

    }
}

 */
