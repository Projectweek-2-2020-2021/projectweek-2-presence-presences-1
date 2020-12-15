package ucll.project.domain.db;

import ucll.project.domain.model.Student;

import java.sql.Connection;
import java.util.List;

public interface StudentDB {

    /**
     * Returns a list with all students stored in the database
     * @return An arraylist with all students stored in the database
     * @throws DbException if something went wrong
     */

    List<Student> getAll();

    /**
     * @return the connection with the db, if there is one
     */
    Connection getConnection();


    /**
     * Reconnects application to db
     */
    void reConnect();

    List<Student> getStudentenPerVak(int id);

    Student getStudent(String rnummer);

     int getStudentId(String rnummer);
}
