package ucll.project.domain.db;


import ucll.project.domain.model.Country;

import java.sql.Connection;
import java.util.List;

public interface CountryDB {
    /**
     * Stores the given country
     * @param country The country to be added
     * @throws DbException if the given country is null
     * @throws DbException if the given country can not be added
     */
    void add(Country country);


    /**
     * Returns a list with all countries stored in the database
     * @return An arraylist with all countries stored in the database
     * @throws DbException if something went wrong
     */

    List<Country> getAll();

    /**
     * @return the connection with the db, if there is one
     */
    Connection getConnection();


    /**
     * Reconnects application to db
     */
    void reConnect();

}
