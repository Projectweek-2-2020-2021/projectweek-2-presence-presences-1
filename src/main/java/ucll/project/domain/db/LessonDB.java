package ucll.project.domain.db;


import ucll.project.domain.model.Lector;
import ucll.project.domain.model.Lesson;
import ucll.project.domain.model.Student;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

public interface LessonDB {
//    /**
//     * Stores the given country
//     * @param country The country to be added
//     * @throws DbException if the given country is null
//     * @throws DbException if the given country can not be added
//     */
//    void add(Country country);


    /**
     * Returns a list with all lessons stored in the database
     * @return An arraylist with all lessons stored in the database
     * @throws DbException if something went wrong
     */

    List<Lesson> getAll();

    List<Lesson> getLessenVoorLector(int id, Date datum);
    /**
     * @return the connection with the db, if there is one
     */
    Connection getConnection();


    /**
     * Reconnects application to db
     */
    void reConnect();

    int getLesId(String vaknaam);

    

}
