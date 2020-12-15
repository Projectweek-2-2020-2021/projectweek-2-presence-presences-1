package ucll.project.domain.db;

import ucll.project.domain.model.Student;

import java.util.List;

public interface LesStudentDB {

    void reConnect();

    void zetAanwezigheid(String aanwezigheid, int studentId, int lesId);

    void zetBevestiging(String bevestiging, int studentId, int lesId);

    List<Student> getAllAanwezigheid(int lesId);

    List<Student> getAllNietAanwezigheid(int lesId);
}
