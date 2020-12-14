package ucll.project.domain.service;

import junit.framework.TestCase;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import ucll.project.domain.db.DbException;
import ucll.project.domain.model.Country;

import static org.junit.Assert.assertEquals;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class CountryServiceTest extends TestCase {

    public void setUp() throws Exception {
        super.setUp();
    }

    @Test (expected = DbException.class)
    public void B_Add_NULL_Country_Throws_DBException(){
    }

    @Test
    public void C_Add_Correct_Country_Adds_Country_To_Database(){

    }

    @Test
    public void D_CountryDB_With_Countries_Gives_List_Of_Country_Objects() {
    }

    @Test
    public void A_CountryDB_Without_Countries_Gives_Empty_List(){

    }

    @Test
    public void E_Get_Most_Popular_Country_Gives_Country_With_Most_Votes() {

    }
}