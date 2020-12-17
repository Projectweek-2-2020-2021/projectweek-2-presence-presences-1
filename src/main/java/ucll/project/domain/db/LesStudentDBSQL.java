package ucll.project.domain.db;

import ucll.project.domain.model.LesStudent;
import ucll.project.domain.model.Lesson;
import ucll.project.domain.model.Student;
import ucll.project.util.DbConnectionService;

import java.security.NoSuchAlgorithmException;
import java.sql.*;
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
        DbConnectionService.disconnect();   // close connection with db properly
        DbConnectionService.connect();      // reconnect application to db server
        this.connection = DbConnectionService.getDbConnection();    // assign connection to DBSQL
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
                LessonDBSQL.makeLesson(result, lessons);
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
    public List<String> getLokaal(int lesid, int studentid, java.util.Date date){
        List<String> lokalen = new ArrayList<>();
        String sql = "SELECT DISTINCT lokaal FROM " + this.schema + ".lesstudent WHERE lesstudent.lesid = ? AND lesstudent.studentid = ? AND datum = ?";
        try {
            PreparedStatement statementsql = connection.prepareStatement(sql);
            statementsql.setInt(1, lesid);
            statementsql.setInt(2, studentid);
            statementsql.setDate(3, (Date) date);
            ResultSet result = statementsql.executeQuery();
            while (result.next()) {
                lokalen.add(result.getString("lokaal"));
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        return lokalen;
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

    @Override
    public List<java.util.Date> getAllDatumsStudent(int studentId) {
        String sql = "SELECT distinct datum FROM " + this.schema + ".lesstudent WHERE studentid = ?";
        List<java.util.Date> datums = new ArrayList<>();
        try {
            PreparedStatement statementsql = connection.prepareStatement(sql);
            statementsql.setInt(1, studentId);
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
    public List<java.util.Date> getAllDatumsLector() {
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

    @Override
    public List<LesStudent> getLesStudentVoorStudentenVanStc(java.sql.Date van, java.sql.Date tot, String nummer) {
        List<LesStudent> lesStudentList = new ArrayList<>();
        String sql = "SELECT " + this.schema + ".les.naam as naam, r_nummer, datum, lokaal, opmerking, aanwezigheid, bevestiging, gewettigdafwezig FROM " + this.schema + ".lesstudent inner join " + this.schema + ".student on lesstudent.studentid = student.id inner join " + this.schema + ".les on les.id = lesstudent.lesid WHERE stc = ? AND datum >= ? AND datum <= ?";
        try {
            PreparedStatement statementsql = connection.prepareStatement(sql);
            statementsql.setString(1, nummer);
            statementsql.setDate(2, van);
            statementsql.setDate(3, tot);
            ResultSet result = statementsql.executeQuery();
            while (result.next()) {
                LesStudent lesStudent = maakLesStudent(result);
                lesStudentList.add(lesStudent);
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        return lesStudentList;
    }

    private LesStudent maakLesStudent(ResultSet result) throws SQLException {
        String lesnaam = result.getString("naam");
        String rnummer = result.getString("r_nummer");
        java.util.Date datum = result.getDate("datum");
        String klaslokaal = result.getString("lokaal");
        String opmerking = result.getString("opmerking");
        boolean aanwezigheid = result.getBoolean("aanwezigheid");
        boolean bevestiging = result.getBoolean("bevestiging");
        boolean gewettigdafwezig = result.getBoolean("gewettigdafwezig");
        String status = bepaalStatus(aanwezigheid, bevestiging, gewettigdafwezig);
        LesStudent lesStudent = new LesStudent(lesnaam, rnummer, datum, klaslokaal, opmerking, status);
        return lesStudent;
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
