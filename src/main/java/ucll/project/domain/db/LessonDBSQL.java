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
     * Indeed
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

    //Les zou een kolom lectorennummer moeten bevatten
    @Override
    public List<Lesson> getAllForLector(String nummer) {
        List<Lesson> lessons = new ArrayList<>();
        String sql = "SELECT * FROM " + this.schema + ".les" + " WHERE lector_nummer = ?";
        try{
            PreparedStatement statementsql = connection.prepareStatement(sql);
            statementsql.setString(1, nummer.toLowerCase());
            ResultSet result = statementsql.executeQuery();
            while (result.next()){
                makeLesson(result, lessons);
            }
        }catch (SQLException e){
            throw new DbException(e.getMessage());
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

    @Override
    public int getLesId(String vaknaam) {
        String sql = "SELECT id FROM " + this.schema + ".les" + " WHERE naam = ?";
        int id = 0;
        try {
            PreparedStatement statementsql = connection.prepareStatement(sql);
            statementsql.setString(1, vaknaam);
            ResultSet result = statementsql.executeQuery();
            while (result.next()){
                id = result.getInt("id");
            }
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }

        return id;
    }

    private void makeLesson(ResultSet result, List<Lesson> lessons) throws SQLException {
        String name = result.getString("naam");
        int studiepunten = Integer.parseInt(result.getString("studiepunten"));
        String studierichting = result.getString("studierichting");
        Lesson lesson = new Lesson(name, studiepunten, studierichting);
        lessons.add(lesson);
    }
}
