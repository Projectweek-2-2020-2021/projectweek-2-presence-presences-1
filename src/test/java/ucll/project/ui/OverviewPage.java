package ucll.project.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;

public class OverviewPage extends Page{
    @FindBy(id = "mostPopularCountry")
    private WebElement mostPopularCountry;

    @FindBy(id="goHome")
    private WebElement goHomeLink;

    public OverviewPage(WebDriver driver) {
        super(driver);
        this.driver.get(this.path+"?command=Overview");
    }

    public boolean isMostPopularCountry(String name){
        return mostPopularCountry.getText().contains(name);
    }

    public HomePage navigateToHomePage() {
        goHomeLink.click();
        return PageFactory.initElements(driver, HomePage.class);

    }

    public boolean containsCountryWithName(String name) {
        ArrayList<WebElement> listItems=(ArrayList<WebElement>) this.driver.findElements(By.cssSelector("td"));
        boolean found=false;
        for (WebElement listItem:listItems) {
            if (listItem.getText().contains(name)) {
                found=true;
            }
        }
        return found;
    }
}
