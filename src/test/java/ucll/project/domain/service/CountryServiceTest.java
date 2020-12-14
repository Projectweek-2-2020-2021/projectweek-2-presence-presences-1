package ucll.project.domain.service;

import junit.framework.TestCase;
import org.junit.*;
import org.junit.runners.MethodSorters;
import ucll.project.domain.db.CountryDBSQL;
import ucll.project.domain.db.DbException;
import ucll.project.domain.model.Country;
import ucll.project.util.DBConnectionManager;
import ucll.project.util.DbConnectionService;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class CountryServiceTest {
    CountryService service = new CountryService();


    @Before
    public void setup(){
        DbConnectionService.connect();
    }

    @Test
    public void E_disconnect(){
        DbConnectionService.disconnect();
    }

    @Test (expected = DbException.class)
    public void A_Add_NULL_Country_Throws_DBException(){
        Country country = null;
        service.addCountry(country);
    }

    @Test
    public void B_Add_Correct_Country_Adds_Country_To_Database(){
        Country country = new Country("Frankrijk", 69000000, "Parijs", 1);
        service.addCountry(country);
        Assert.assertTrue(service.getCountries().contains(country));
    }

    @Test
    public void C_CountryDB_With_Countries_Gives_List_Of_Country_Objects() {
        Assert.assertNotNull(service.getCountries());
    }

    @Test
    public void D_Get_Most_Popular_Country_Gives_Country_With_Most_Votes() {
        Assert.assertEquals("Spain", service.getMostPopularCountry().getName());
    }

}