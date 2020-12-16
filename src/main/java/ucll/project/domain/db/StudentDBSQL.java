package ucll.project.domain.db;

import ucll.project.domain.model.Student;
import ucll.project.util.DbConnectionService;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDBSQL implements StudentDB {
    private Connection connection;
    private final String schema;

    public StudentDBSQL() {
        this.connection = DbConnectionService.getDbConnection();
        this.schema = DbConnectionService.getSearchPath();
    }

    @Override
    public List<Student> getAll() {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM " + this.schema + ".student";
        try {
            PreparedStatement statementSql = connection.prepareStatement(sql);
            ResultSet result = statementSql.executeQuery();
            while (result.next()) {
                makeStudent(result, students);
            }
        } catch (SQLException | NoSuchAlgorithmException e) {
            throw new DbException(e.getMessage(), e);
        }
        return students;
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
    public List<Student> getStudentenPerVak(int id) {
        List<Student> students = new ArrayList<>();
        String sql = "select * from " + this.schema + ".student inner join " + this.schema + ".lesstudent on student.id = lesstudent.studentid inner join " + this.schema + ".les on les.id = lesstudent.lesid where les.id = ?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultset = preparedStatement.executeQuery();
            while (resultset.next()){
                makeStudent(resultset, students);
            }
        }catch (SQLException | NoSuchAlgorithmException e){
            throw new DbException(e.getMessage());
        }
        return students;
    }

    @Override
    public Student getStudent(String rnummer) {
        List<Student> students = new ArrayList<>();
        String sql = "select * from student where r_nummer = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, rnummer);
            ResultSet resultset = preparedStatement.executeQuery();
            while (resultset.next()){
                makeStudent(resultset, students);
            }
        } catch (SQLException | NoSuchAlgorithmException e) {
            throw new DbException(e.getMessage());
        }
        return students.get(0);
    }

    private void makeStudent(ResultSet result, List<Student> students) throws SQLException, NoSuchAlgorithmException {
        String naam = result.getString("naam");
        String rnummer = result.getString("r_nummer");
        String voornaam = result.getString("voornaam");
        String email = result.getString("email");
        String adres = result.getString("adres");
        String telefoonNummer = result.getString("telefoonnummer");
        String wachtwoord = result.getString("wachtwoord");
        Student student = new Student(rnummer, naam, voornaam, email, adres, telefoonNummer, wachtwoord, "status");
        students.add(student);
    }

    @Override
    public int getStudentId(String rnummer) {
        String sql = "SELECT id FROM " + this.schema + ".student" + " WHERE r_nummer = ?";

        int id = 0;
        try {
            PreparedStatement statementsql = connection.prepareStatement(sql);
            statementsql.setString(1, rnummer);
            ResultSet result = statementsql.executeQuery();
            while (result.next()){
                id = result.getInt("id");
            }
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }

        return id;
    }


}
