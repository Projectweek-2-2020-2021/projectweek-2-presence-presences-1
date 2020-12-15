package ucll.project.domain.service;

import ucll.project.domain.db.LessonDB;
import ucll.project.domain.db.LessonDBSQL;
import ucll.project.domain.db.StudentDB;
import ucll.project.domain.db.StudentDBSQL;
import ucll.project.domain.model.Lesson;
import ucll.project.domain.model.Student;

import java.sql.Connection;
import java.util.List;

public class ApplicationService {
    private LessonDB db = new LessonDBSQL();
    private StudentDB dbstudent = new StudentDBSQL();
//    public void addLesson(Lesson lesson) {
//        db.add(lesson);
//    }

    public List<Lesson> getLessons() {
        return db.getAll();
    }

    public List<Lesson> getLessonForLector(String lectorennummer){
        return db.getAllForLector(lectorennummer);
    }

    /**
     * @return connection with database
     */
    public Connection getConnection() {
        return db.getConnection();
    }

    /**
     * Reconnect DBSQL to database
     */
    public void reConnect() {
        db.reConnect();
    }

    public List<Student> getStudentPerVak(int id){
        return dbstudent.getStudentenPerVak(id);

    }

    public Student getStudent(String rnummer) {
        return dbstudent.getStudent(rnummer);
    }

    public int getVakId(String vaknaam){
        return db.getLesId(vaknaam);
    }
}
