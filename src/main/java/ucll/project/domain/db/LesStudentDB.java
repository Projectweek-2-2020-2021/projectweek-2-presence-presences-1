package ucll.project.domain.db;

import ucll.project.domain.model.Lesson;
import ucll.project.domain.model.Student;

import java.util.Date;
import java.util.List;

public interface LesStudentDB {

    void reConnect();

    void zetAanwezigheid(String aanwezigheid, String rnummer, int lesId);

    void zetBevestiging(String bevestiging, int studentId, int lesId);

    void zetGewettigdeAfwezigheid(int studentId, int lesId);

    List<Student> getAllAanwezigheid(int lesId);

    List<Student> getAllNietAanwezigheid(int lesId);

    List<Date> getAllDatums();

    List<Lesson> getLessenVoorStudent(int studentid);

    List<Student> getAllStudentsStatus(int lesId);
}
