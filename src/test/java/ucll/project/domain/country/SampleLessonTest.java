package ucll.project.domain.country;

import org.junit.Test;
import ucll.project.domain.model.Lesson;
import ucll.project.domain.model.DomainException;

import static org.junit.Assert.assertEquals;

/**
 * This is a sample unit test, write your own!
 */
public class SampleLessonTest {

    @Test
    public void CreateCountryTest() {
        Lesson lesson = new Lesson("Name");
        assertEquals("Name", lesson.getName());
    }

    @Test(expected = DomainException.class)
    public void CreateCountryWithEmptyNameThrowsException(){
        Lesson lesson = new Lesson("");
    }
}
