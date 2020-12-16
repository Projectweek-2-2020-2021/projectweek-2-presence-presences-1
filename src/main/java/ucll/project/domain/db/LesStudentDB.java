package ucll.project.domain.db;

import ucll.project.domain.model.Lesson;
import ucll.project.domain.model.Student;

import java.util.Date;
import java.util.List;

public interface LesStudentDB {

    void reConnect();

    void zetAanwezigheid(String aanwezigheid, int studentId, int lesId, Date datum);

    void zetBevestiging(String bevestiging, int studentId, int lesId, Date datum);

    List<Student> getAllAanwezigheid(int lesId, Date datum);

    List<Student> getAllNietAanwezigheid(int lesId, Date datum);

    List<Lesson> getLessenVoorStudent(int studentid, Date datum);

    List<Date> getAllDatums();
}
