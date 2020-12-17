package ucll.project.domain.db;

import ucll.project.domain.model.Lesson;
import ucll.project.util.DbConnectionService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LessonDBSQL implements LessonDB {
    private Connection connection;
    private final String schema;

    public LessonDBSQL() {

        this.connection = DbConnectionService.getDbConnection();
        this.schema = DbConnectionService.getSearchPath();
    }

    public static void makeLesson(ResultSet result, List<Lesson> lessons) throws SQLException {
        String name = result.getString("naam");
        int studiepunten = Integer.parseInt(result.getString("studiepunten"));
        String studierichting = result.getString("studierichting");
        String tijd = result.getString("tijd");
        int lesduur = result.getInt("lesduur");
        Lesson lesson = new Lesson(name, studiepunten, studierichting, tijd, lesduur);
        lessons.add(lesson);
    }

    /**
     * @return the connection with the db, if there is one
     */
    @Override
    public Connection getConnection() {
        return this.connection;
    }

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

    public List<Lesson> getLessenVoorLector(int id, Date datum) {
        List<Lesson> lessons = new ArrayList<>();
        String sql = "SELECT DISTINCT id, tijd, naam, studiepunten, studierichting, datum, lesduur FROM " + this.schema + ".les INNER JOIN " + this.schema + ".leslector on les.id = leslector.lesid INNER JOIN " + this.schema + ".lesstudent ON leslector.lesid = lesstudent.lesid WHERE leslector.lectorid = ? AND datum = ? ";
        try{
            PreparedStatement statementsql = connection.prepareStatement(sql);
            statementsql.setInt(1, id);
            statementsql.setDate(2, (java.sql.Date) datum);
            ResultSet result = statementsql.executeQuery();
            while (result.next()){
                makeLesson(result, lessons);
            }
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }
        return lessons;
    }

    @Override
    public int getLesId(String vaknaam) {
        String sql = "SELECT id FROM " + this.schema + ".les" + " WHERE naam = ?";
        int id = 0;
        try {
            PreparedStatement statementsql = connection.prepareStatement(sql);
            statementsql.setString(1, vaknaam);
            ResultSet result = statementsql.executeQuery();
            while (result.next()) {
                id = result.getInt("id");
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }

        return id;
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
}
