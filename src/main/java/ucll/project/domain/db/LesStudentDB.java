package ucll.project.domain.db;

import ucll.project.domain.model.LesStudent;
import ucll.project.domain.model.Lesson;
import ucll.project.domain.model.Student;

import java.util.Date;
import java.util.List;

public interface LesStudentDB {

    void reConnect();

    void zetAanwezigheid(String aanwezigheid, int studentId, int lesId, Date datum);

    void zetBevestiging(String bevestiging, int studentId, int lesId, Date datum);

    List<Student> getAllNietAanwezigheid(int lesId, Date datum);

    List<Lesson> getLessenVoorStudent(int studentid, Date datum);

    List<Date> getAllDatumsStudent(int studentId);

    List<Date> getAllDatumsLector();

    List<String> getLokaal(int lesid, int studentid, Date date);

    String getGroep(int lesid);

    void zetGewettigdeAfwezigheid(int studentId, int lesId, Date date);

    void setStudentCommentaar(int studentId, int lesId, java.sql.Date date, String opmerking);

    List<LesStudent> getLesStudentVoorStudentenVanStc(java.sql.Date van, java.sql.Date tot, String nummer);
}
