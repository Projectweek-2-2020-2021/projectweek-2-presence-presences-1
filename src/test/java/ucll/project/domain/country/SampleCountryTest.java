package ucll.project.domain.country;

import org.junit.Test;
import ucll.project.domain.model.Country;
import ucll.project.domain.model.DomainException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * This is a sample unit test, write your own!
 */
public class SampleCountryTest {

    @Test
    public void CreateCountryTest() {
        Country country = new Country("Name",1000,"Capital",3);
        assertEquals("Name",country.getName());
        assertEquals(1000,country.getNumberInhabitants());
        assertEquals("Capital",country.getCapital());
        assertEquals(3,country.getVotes());
    }

    @Test(expected = DomainException.class)
    public void CreateCountryWithEmptyNameThrowsException(){
        Country country = new Country("",1000,"Capital",3);
    }

    @Test
    public void Create_Country_With_Empty_Capital_Gives_Capital_Empty_String_Value(){
        Country country = new Country("Name", 1000, "", 3);
        assertEquals("", country.getCapital());
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
