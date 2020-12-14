package ucll.project.domain.country;

import ucll.project.domain.model.Country;
import org.junit.*;
import ucll.project.domain.model.DomainException;

public class SampleCountryTest {

    @Test
    public void CreateCountryTest() {
        Country country = new Country("Name",1000,"Capital",3);
        Assert.assertEquals("Name",country.getName());
        Assert.assertEquals(1000,country.getNumberInhabitants());
        Assert.assertEquals("Capital",country.getCapital());
        Assert.assertEquals(3,country.getVotes());
    }

    @Test(expected = DomainException.class)
    public void CreateCountryWithEmptyNameThrowsException(){
        Country country = new Country("",1000,"Capital",3);
    }

    @Test
    public void Create_Country_With_Empty_Capital_Gives_Capital_Empty_String_Value(){
        Country country = new Country("Name", 1000, "", 3);
        Assert.assertEquals("", country.getCapital());
    }

    @Test (expected = DomainException.class)
    public void Create_Country_With_Negative_Inhabitants_Throws_Exception(){
        Country country = new Country("Name", -100, "Capital", 3);
    }

    @Test (expected = DomainException.class)
    public void Create_Country_With_Votes_Higher_Than_5_Throws_Exception(){
        Country country = new Country("Name", 1000, "Capital", 6);
    }
}
