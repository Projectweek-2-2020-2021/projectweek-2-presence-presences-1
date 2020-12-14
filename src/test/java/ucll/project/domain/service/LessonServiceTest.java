package ucll.project.domain.service;

import org.junit.*;
import org.junit.runners.MethodSorters;
import ucll.project.domain.db.DbException;
import ucll.project.domain.model.Lesson;
import ucll.project.util.DbConnectionService;

import static org.junit.Assert.assertEquals;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class LessonServiceTest {
    LessonService service = new LessonService();


    @Before
    public void setup(){
        DbConnectionService.connect();
    }

    @Test
    public void E_disconnect(){
        DbConnectionService.disconnect();
    }

//    @Test (expected = DbException.class)
//    public void A_Add_NULL_Country_Throws_DBException(){
//        Lesson lesson = null;
//        service.addCountry(lesson);
//    }
//
//    @Test
//    public void B_Add_Correct_Country_Adds_Country_To_Database(){
//        Lesson lesson = new Lesson("Frankrijk", 69000000, "Parijs", 1);
//        service.addCountry(lesson);
//        Assert.assertTrue(service.getLessons().contains(lesson));
//    }

    @Test
    public void C_CountryDB_With_Countries_Gives_List_Of_Country_Objects() {
        Assert.assertNotNull(service.getLessons());
    }

//    @Test
//    public void D_Get_Most_Popular_Country_Gives_Country_With_Most_Votes() {
//        Assert.assertEquals("Spain", service.getMostPopularCountry().getName());
//    }

}