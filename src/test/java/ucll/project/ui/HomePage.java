package ucll.project.ui;

import org.openqa.selenium.WebDriver;

public class HomePage extends Page{
    public HomePage(WebDriver driver) {
        super(driver);
        this.driver.get(this.path+"?command=Index");
    }
}
