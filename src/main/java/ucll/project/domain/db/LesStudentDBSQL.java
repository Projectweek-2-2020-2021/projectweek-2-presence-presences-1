package ucll.project.domain.db;

import ucll.project.domain.model.Lesson;
import ucll.project.domain.model.Student;
import ucll.project.util.DbConnectionService;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LesStudentDBSQL implements LesStudentDB{
    private final Connection connection;
    private final String schema;

    public LesStudentDBSQL() {
        this.connection = DbConnectionService.getDbConnection();
        this.schema = DbConnectionService.getSearchPath();
    }

    @Override
    public void reConnect() {

    }

    @Override
    public void zetAanwezigheid(String aanwezigheid, String rnummer, int lesId) {
        String sql = "UPDATE " + this.schema + ".lesstudent as L " +
                "SET aanwezigheid = ? FROM " + this.schema + ".student AS S " +
                "WHERE L.studentid = S.id AND S.r_nummer = ? AND L.lesid = ?";

        try {
            PreparedStatement statementsql = connection.prepareStatement(sql);
            statementsql.setBoolean(1, aanwezigheid.equals("ja"));
            statementsql.setString(2, rnummer);
            statementsql.setInt(3, lesId);

            statementsql.execute();

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public void zetBevestiging(String bevestiging, int studentId, int lesId) {
        String sql = "UPDATE " + this.schema + ".lesstudent" + " SET bevestiging = ? WHERE studentid = ? AND lesid = ?";

        try {
            PreparedStatement statementsql = connection.prepareStatement(sql);
            statementsql.setBoolean(1, bevestiging.equals("ja"));
            statementsql.setInt(2, studentId);
            statementsql.setInt(3, lesId);

            statementsql.execute();

        } catch (SQLException e){
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public List<Student> getAllAanwezigheid(int lesId) {
        String sql = "SELECT * FROM " + this.schema + ".lesstudent" + " INNER JOIN" + this.schema + ".student ON lesstudent.studentid = student.id WHERE aanwezigheid = true AND lesid = ? AND bevestiging is not true";
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

    @Override
    public List<Date> getAllDatums() {
        String sql = "SELECT distinct datum FROM " + this.schema + ".lesstudent";
        List<Date> datums = new ArrayList<>();
        try {
            PreparedStatement statementsql = connection.prepareStatement(sql);
            ResultSet result = statementsql.executeQuery();
            while (result.next()) {
                datums.add(result.getDate("datum"));
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        return datums;
    }

    @Override
    public List<Lesson> getLessenVoorStudent(int studentid) {
        return null;
    }

    @Override
    public void zetGewettigdeAfwezigheid(int studentId, int lesId) {
        String sql = "UPDATE " + this.schema + ".lesstudent" + " SET gewettigdafwezig = true WHERE studentid = ? AND lesid = ?";

        try {
            PreparedStatement statementsql = connection.prepareStatement(sql);
            statementsql.setInt(1, studentId);
            statementsql.setInt(2, lesId);
            statementsql.execute();

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public List<Student> getAllStudentsStatus(int lesId) {
        String sql = "SELECT * FROM " + this.schema + ".lesstudent" + " INNER JOIN" + this.schema + ".student ON lesstudent.studentid = student.id WHERE lesid = ?";
        List<Student> students = new ArrayList<>();

        try {
            PreparedStatement statementSql = connection.prepareStatement(sql);
            statementSql.setInt(1, lesId);
            ResultSet result = statementSql.executeQuery();
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
        boolean aanwezigheid = result.getBoolean("aanwezigheid");
        boolean bevestiging = result.getBoolean("bevestiging");
        boolean gewettigdafwezig = result.getBoolean("gewettigdafwezig");
        String status = bepaalStatus(aanwezigheid, bevestiging, gewettigdafwezig);
        Student student = new Student(rnummer, naam, voornaam, email, adres, telefoonNummer, wachtwoord, status);
        students.add(student);
    }

    private String bepaalStatus(boolean aanwezigheid, boolean bevestiging, boolean gewettigdafwezig) {
        String status = "Pending";
        if (aanwezigheid) {
            if (bevestiging) {
                status = "Aanwezig";
            }
        }
        if (!aanwezigheid) {
            if (bevestiging) {
                status = "Aanwezig";
            }
            if (!bevestiging) {
                status = "Afwezig";
            }
        }
        if (gewettigdafwezig) {
            status = "Gewettigd afwezig";
        }
        return status;
    }
}
