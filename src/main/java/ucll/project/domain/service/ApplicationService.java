package ucll.project.domain.service;

import ucll.project.domain.db.*;
import ucll.project.domain.model.Lector;
import ucll.project.domain.model.Lesson;
import ucll.project.domain.model.Student;

import java.sql.Connection;
import java.util.List;

public class ApplicationService {
    private final LesStudentDB dbLesStudent = new LesStudentDBSQL();
    private final LessonDB dbLesson = new LessonDBSQL();
    private final StudentDB dbStudent = new StudentDBSQL();
    private final LectorDB dbLector = new LectorDBSQL();

    public List<Lesson> getLessons() {
        return dbLesson.getAll();
    }

    public List<Lesson> getLessonForLector(String lectorennummer) {
        return dbLesson.getAllForLector(lectorennummer);
    }

    /**
     * @return connection with database
     */
    public Connection getConnection() {
        return dbLesson.getConnection();
    }

    /**
     * Reconnect DBSQL to database
     */
    public void reConnect() {
        dbLesson.reConnect();
    }

    public List<Student> getStudentPerVak(int id) {
        return dbStudent.getStudentenPerVak(id);

    }

    public Student getStudent(String rnummer) {
        return dbStudent.getStudent(rnummer);
    }

    public int getVakId(String vaknaam) {
        return dbLesson.getLesId(vaknaam);
    }

    public Lector getLector(String unummer) {
        return dbLector.getLector(unummer);
    }

    public void zetAanwezigheid(String aanwezigheid, int studentId, int lesId) {
        dbLesStudent.zetAanwezigheid(aanwezigheid, studentId, lesId);
    }

    public List<Student> getAllAanwezigheid(int lesId) {
        return dbLesStudent.getAllAanwezigheid(lesId);
    }

    public List<Student> getAllNietAanwezigheid(int lesId) {
        return dbLesStudent.getAllNietAanwezigheid(lesId);
    }

    public void zetBevestiging(String aanwezigheid, int studentId, int lesId) {
        dbLesStudent.zetBevestiging(aanwezigheid, studentId, lesId);
    }

    public int getStudentId(String rnummer) {
        return dbStudent.getStudentId(rnummer);
    }
}
