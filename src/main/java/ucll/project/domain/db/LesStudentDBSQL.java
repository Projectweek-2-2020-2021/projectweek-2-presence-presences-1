package ucll.project.domain.db;

import ucll.project.domain.model.Lesson;
import ucll.project.domain.model.Student;
import ucll.project.util.DbConnectionService;

import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
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
    public void zetAanwezigheid(String aanwezigheid, int studentId, int lesId, java.util.Date datum) {
        String sql = "UPDATE " + this.schema + ".lesstudent" + " SET aanwezigheid = ? WHERE studentid = ? AND lesid = ? AND datum = ?";
        try {
            PreparedStatement statementsql = connection.prepareStatement(sql);
            statementsql.setBoolean(1, aanwezigheid.equals("ja"));
            statementsql.setInt(2, studentId);
            statementsql.setInt(3, lesId);
            statementsql.setDate(4, (Date) datum);
            statementsql.execute();

        } catch (SQLException e){
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public void zetBevestiging(String bevestiging, int studentId, int lesId, java.util.Date datum) {
        String sql = "UPDATE " + this.schema + ".lesstudent" + " SET aanwezigheid = ?, bevestiging = ?, gewettigdafwezig = false WHERE studentid = ? AND lesid = ? AND datum = ?";
        try {
            PreparedStatement statementsql = connection.prepareStatement(sql);
            statementsql.setBoolean(1, bevestiging.equals("ja"));
            if (bevestiging.equals("ja")) {
                statementsql.setBoolean(2, true);
            } else {
                statementsql.setNull(2, Types.BOOLEAN);
            }
            statementsql.setInt(3, studentId);
            statementsql.setInt(4, lesId);
            statementsql.setDate(5, (Date) datum);
            statementsql.execute();

        } catch (SQLException e){
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public List<Student> getAllAanwezigheid(int lesId, java.util.Date datum) {
        String sql = "SELECT * FROM " + this.schema + ".lesstudent" + " INNER JOIN" + this.schema + ".student ON lesstudent.studentid = student.id WHERE aanwezigheid = true AND lesid = ? AND bevestiging is not true AND datum = ?";
        List<Student> students = new ArrayList<>();
        try {
            PreparedStatement statementsql = connection.prepareStatement(sql);
            statementsql.setInt(1, lesId);
            statementsql.setDate(2, (Date) datum);
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
    public List<Student> getAllNietAanwezigheid(int lesId, java.util.Date datum) {
        String sql = "SELECT * FROM " + this.schema + ".lesstudent" + " INNER JOIN" + this.schema + ".student ON lesstudent.studentid = student.id WHERE lesid = ? AND datum = ?";
        List<Student> students = new ArrayList<>();
        try {
            PreparedStatement statementsql = connection.prepareStatement(sql);
            statementsql.setInt(1, lesId);
            statementsql.setDate(2, (Date) datum);
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
    public List<Lesson> getLessenVoorStudent(int studentid, java.util.Date datum) {
        String sql = "SELECT * FROM " + this.schema + ".lesstudent INNER JOIN " + this.schema + ".les ON lesstudent.lesid = les.id"  + " WHERE studentid = ? AND datum = ?";
        List<Lesson> lessons = new ArrayList<>();
        try {
            PreparedStatement statementsql = connection.prepareStatement(sql);
            statementsql.setInt(1, studentid);
            statementsql.setDate(2, (Date) datum);
            ResultSet result = statementsql.executeQuery();
            while (result.next()) {
                makeLesson(result, lessons);
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        return lessons;

    }

    @Override
    public String getGroep(int lesid){
        String groep = null;
        String sql = "SELECT groep FROM " + this.schema + ".lesstudent WHERE lesstudent.lesid = ?";
        try {
            PreparedStatement statementsql = connection.prepareStatement(sql);
            statementsql.setInt(1, lesid);
            ResultSet result = statementsql.executeQuery();
            while (result.next()) {
                groep = result.getString("groep");
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        return groep;
    }

    @Override
    public String getLokaal(int lesid){
        String lokaal = null;
        String sql = "SELECT lokaal FROM " + this.schema + ".lesstudent WHERE lesstudent.lesid = ?";
        try {
            PreparedStatement statementsql = connection.prepareStatement(sql);
            statementsql.setInt(1, lesid);
            ResultSet result = statementsql.executeQuery();
            while (result.next()) {
                lokaal = result.getString("lokaal");
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        return lokaal;
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

    private void makeLesson(ResultSet result, List<Lesson> lessons) throws SQLException {
        String name = result.getString("naam");
        int studiepunten = Integer.parseInt(result.getString("studiepunten"));
        String studierichting = result.getString("studierichting");
        String tijd = result.getString("tijd");
        int lesduur = result.getInt("lesduur");
        Lesson lesson = new Lesson(name, studiepunten, studierichting, tijd, lesduur);
        lessons.add(lesson);
    }

    @Override
    public List<java.util.Date> getAllDatums() {
        String sql = "SELECT distinct datum FROM " + this.schema + ".lesstudent";
        List<java.util.Date> datums = new ArrayList<>();
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
    public void zetGewettigdeAfwezigheid(int studentId, int lesId, java.util.Date datum) {
        String sql = "UPDATE " + this.schema + ".lesstudent" + " SET gewettigdafwezig = true WHERE studentid = ? AND lesid = ? AND datum = ?";
        try {
            PreparedStatement statementsql = connection.prepareStatement(sql);
            statementsql.setInt(1, studentId);
            statementsql.setInt(2, lesId);
            statementsql.setDate(3, (Date) datum);
            statementsql.execute();

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    public void setStudentCommentaar(int studentId, int lesId, java.sql.Date date, String opmerking) {
        String sql = "UPDATE " + this.schema + ".lesstudent" + " SET opmerking = ? WHERE studentid = ? AND lesid = ? AND datum = ?";
        try {
            PreparedStatement statementSql = connection.prepareStatement(sql);
            statementSql.setString(1, opmerking);
            statementSql.setInt(2, studentId);
            statementSql.setInt(3, lesId);
            statementSql.setDate(4, date);
            statementSql.execute();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }


    private String bepaalStatus(boolean aanwezigheid, boolean bevestiging, boolean gewettigdafwezig) {
        String status = "Pending";
        if (aanwezigheid) { // TRUE TRUE
            if (bevestiging) {
                status = "Aanwezig";
            }
        }
        if (!aanwezigheid) { // FALSE TRUE
            if (bevestiging) {
                status = "Aanwezig";
            }
            if (!bevestiging) { // FALSE FALSE
                status = "Afwezig";
            }
        }
        if (gewettigdafwezig) {
            status = "Gewettigd afwezig";
        }
        return status;
    }

}
