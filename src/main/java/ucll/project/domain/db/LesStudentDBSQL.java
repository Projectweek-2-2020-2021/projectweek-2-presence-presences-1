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

public class LesStudentDBSQL implements LesStudentDB{
    private Connection connection;
    private final String schema;

    public LesStudentDBSQL() {
        this.connection = DbConnectionService.getDbConnection();
        this.schema = DbConnectionService.getSearchPath();
    }

    @Override
    public void reConnect() {

    }

    @Override
    public void zetAanwezigheid(String aanwezigheid, int studentId, int lesId) {
        String sql = "UPDATE " + this.schema + ".lesstudent" + " SET aanwezigheid = ? WHERE studentid = ? AND lesid = ?";

        try {
            PreparedStatement statementsql = connection.prepareStatement(sql);
            if (aanwezigheid.equals("ja")) {
                statementsql.setBoolean(1, true);
            } else {
                statementsql.setBoolean(1, false);
            }
            statementsql.setInt(2, studentId);
            statementsql.setInt(3, lesId);

            statementsql.execute();

        } catch (SQLException e){
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public void zetBevestiging(String bevestiging, int studentId, int lesId) {
        String sql = "UPDATE " + this.schema + ".lesstudent" + " SET bevestiging = ? WHERE studentid = ? AND lesid = ?";

        try {
            PreparedStatement statementsql = connection.prepareStatement(sql);
            if (bevestiging.equals("ja")) {
                statementsql.setBoolean(1, true);
            } else {
                statementsql.setBoolean(1, false);
            }
            statementsql.setInt(2, studentId);
            statementsql.setInt(3, lesId);

            statementsql.execute();

        } catch (SQLException e){
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public List<Student> getAllAanwezigheid(int lesId) {
        String sql = "SELECT * FROM " + this.schema + ".lesstudent" + " INNER JOIN" + this.schema + ".student ON lesstudent.studentid = student.id WHERE aanwezigheid = true AND lesid = ? AND bevestiging is null";
        List<Student> students = new ArrayList<>();
        try {
            PreparedStatement statementsql = connection.prepareStatement(sql);
            statementsql.setInt(1, lesId);

            ResultSet result = statementsql.executeQuery();
            while (result.next()) {
                makeStudent(result, students);
            }
        } catch (SQLException | NoSuchAlgorithmException e) {
            throw new DbException(e.getMessage());
        }
        return students;
    }

    @Override
    public List<Student> getAllNietAanwezigheid(int lesId) {
        String sql = "SELECT * FROM " + this.schema + ".lesstudent" + " INNER JOIN" + this.schema + ".student ON lesstudent.studentid = student.id WHERE aanwezigheid = false OR aanwezigheid is null AND lesid = ? AND bevestiging is null";
        List<Student> students = new ArrayList<>();
        try {
            PreparedStatement statementsql = connection.prepareStatement(sql);
            statementsql.setInt(1, lesId);

            ResultSet result = statementsql.executeQuery();
            while (result.next()) {
                makeStudent(result, students);
            }
        } catch (SQLException | NoSuchAlgorithmException e) {
            throw new DbException(e.getMessage());
        }
        return students;
    }

    private void makeStudent(ResultSet result, List<Student> students) throws SQLException, NoSuchAlgorithmException {
        String naam = result.getString("naam");
        String rnummer = result.getString("r_nummer");
        String voornaam = result.getString("voornaam");
        String email = result.getString("email");
        String adres = result.getString("adres");
        String telefoonNummer = result.getString("telefoonnummer");
        String wachtwoord = result.getString("wachtwoord");
        Student student = new Student(rnummer, naam, voornaam, email, adres, telefoonNummer, wachtwoord);
        students.add(student);
    }
}
