package ucll.project.domain.service;

import ucll.project.domain.db.*;
import ucll.project.domain.model.Lector;
import ucll.project.domain.model.Lesson;
import ucll.project.domain.model.Student;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

public class ApplicationService {
    private final LesStudentDB dbLesStudent = new LesStudentDBSQL();
    private final LessonDB dbLesson = new LessonDBSQL();
    private final StudentDB dbStudent = new StudentDBSQL();
    private final LectorDB dbLector = new LectorDBSQL();

    public List<Lesson> getLessons() {
        return dbLesson.getAll();
    }

    public List<Lesson> getLessonForLector(int id, Date date) {
        return dbLesson.getLessenVoorLector(id, date);
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

    public List<Lector> getAllLectors(){return dbLector.getAllLectors();}

    public int getLectorId(String lectorennummer) {
        return dbLector.getLectorId(lectorennummer);
    }

    public List<Lector> getLectorPerVak(int vakid) {
        return dbLector.getLectorPerVak(vakid);
    }

    public void zetAanwezigheid(String aanwezigheid, int studentId, int lesId, Date datum) {
        dbLesStudent.zetAanwezigheid(aanwezigheid, studentId, lesId, datum);
    }

    public List<Student> getAllAanwezigheid(int lesId, Date datum) {
        return dbLesStudent.getAllAanwezigheid(lesId, datum);
    }

    public List<Student> getAllNietAanwezigheid(int lesId, Date datum) {
        return dbLesStudent.getAllNietAanwezigheid(lesId, datum);
    }

    public void zetBevestiging(String aanwezigheid, int studentId, int lesId, Date datum) {
        dbLesStudent.zetBevestiging(aanwezigheid, studentId, lesId, datum);
    }

    public int getStudentId(String rnummer) {
        return dbStudent.getStudentId(rnummer);
    }

    public List<Lesson> getLessenVoorStudent(int studentid, Date datum) {
        return dbLesStudent.getLessenVoorStudent(studentid, datum);
    }

    public List<Date> getAllDatums() {
        return dbLesStudent.getAllDatums();
    }

    public void zetGewettigdeAfwezigheid(int studentId, int lesId, Date date) {
        dbLesStudent.zetGewettigdeAfwezigheid(studentId, lesId, date);
    }
}
