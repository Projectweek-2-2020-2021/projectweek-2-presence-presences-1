package ucll.project.domain.service;

import ucll.project.domain.db.LessonDB;
import ucll.project.domain.db.LessonDBSQL;
import ucll.project.domain.model.Lesson;

import java.sql.Connection;
import java.util.List;

public class ApplicationService {
    private LessonDB db = new LessonDBSQL();

//    public void addLesson(Lesson lesson) {
//        db.add(lesson);
//    }

    public List<Lesson> getLessons() {
        return db.getAll();
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
}
