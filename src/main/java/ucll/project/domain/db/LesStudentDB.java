package ucll.project.domain.db;

import ucll.project.domain.model.Lesson;
import ucll.project.domain.model.Student;

import java.util.List;

public interface LesStudentDB {

    void reConnect();

    void zetAanwezigheid(String aanwezigheid, int studentId, int lesId, String datum);

    void zetBevestiging(String bevestiging, int studentId, int lesId, String datum);

    List<Student> getAllAanwezigheid(int lesId, String datum);

    List<Student> getAllNietAanwezigheid(int lesId, String datum);

    List<Lesson> getLessenVoorStudent(int studentid, String datum);
}
