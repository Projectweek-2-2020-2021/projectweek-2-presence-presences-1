package ucll.project.domain.country;

import org.junit.Test;
import ucll.project.domain.model.DomainException;
import ucll.project.domain.model.Lesson;

import static org.junit.Assert.assertEquals;

/**
 * This is a sample unit test, write your own!
 */
public class SampleLessonTest {

    @Test
    public void CreateLessonTest() {
        Lesson lesson = new Lesson("Name", 3, "TI");
        assertEquals("Name", lesson.getNaam());
    }

    @Test(expected = DomainException.class)
    public void CreateCountryWithEmptyNameThrowsException(){
        Lesson lesson = new Lesson("", 3, "TI");
    }
}
