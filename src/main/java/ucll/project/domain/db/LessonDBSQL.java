package ucll.project.domain.db;

import ucll.project.domain.model.Lesson;
import ucll.project.util.DbConnectionService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LessonDBSQL implements LessonDB {
    private Connection connection;
    private final String schema;

    public LessonDBSQL() {

        this.connection = DbConnectionService.getDbConnection();
        this.schema = DbConnectionService.getSearchPath();
    }

    /**
     * Returns a list with all countries stored in the database
     * @return An arraylist with all countries stored in the database
     * @throws DbException when there are problems with the connection to the database
     */
    @Override
    public List<Lesson> getAll() {
        List<Lesson> lessons = new ArrayList<>();
        String sql = "SELECT * FROM " + this.schema + ".les";
        try {
            PreparedStatement statementSql = connection.prepareStatement(sql);
            ResultSet result = statementSql.executeQuery();
            while (result.next()) {
                makeLesson(result, lessons);
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage(), e);
        }
        return lessons;
    }

    /**
     * @return the connection with the db, if there is one
     */
    @Override
    public Connection getConnection() {
        return this.connection;
    }

    /**
     * Reconnects application to db
     * Gives DBConnectionService new dbConnection with new dbConnectionManager
     */
    @Override
    public void reConnect() {
        DbConnectionService.disconnect();   // close connection with db properly
        DbConnectionService.connect();      // reconnect application to db server
        this.connection = DbConnectionService.getDbConnection();    // assign connection to DBSQL
    }

    private void makeLesson(ResultSet result, List<Lesson> lessons) throws SQLException {
        String name = result.getString("naam");
        Lesson lesson = new Lesson(name);
        lessons.add(lesson);
    }

    //    /**
//     * Stores the given country in the database
//     *
//     * @param country The country to be added
//     * @throws DbException if the given country is null
//     * @throws DbException if the given country can not be added
//     */
//    @Override
//    public void add(Country country) {
//        if (country == null) {
//            throw new DbException("Nothing to add");
//        }
//        String sql = "INSERT INTO "+this.schema+".country (name, capital, inhabitants, votes) VALUES (?, ?, ?, ?)";
//        try {
//            PreparedStatement statementSQL = connection.prepareStatement(sql);
//            statementSQL.setString(1, country.getName());
//            statementSQL.setString(2, country.getCapital());
//            statementSQL.setInt(3, country.getNumberInhabitants());
//            statementSQL.setInt(4, country.getVotes());
//            statementSQL.execute();
//        } catch (SQLException e) {
//            throw new DbException(e);
//        }
//    }
}
