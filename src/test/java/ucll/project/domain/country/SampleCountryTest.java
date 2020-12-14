package ucll.project.domain.country;

import org.junit.Test;
import ucll.project.domain.model.Country;
import ucll.project.domain.model.DomainException;

import static org.junit.Assert.assertEquals;

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
}
