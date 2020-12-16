package ucll.project.domain.db;

import ucll.project.domain.model.Lesson;
import ucll.project.domain.model.Student;
import ucll.project.util.DbConnectionService;

import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Formatter;
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
    public void zetAanwezigheid(String aanwezigheid, int studentId, int lesId, String datum) {
        String sql = "UPDATE " + this.schema + ".lesstudent" + " SET aanwezigheid = ? WHERE studentid = ? AND lesid = ? AND datum = ?";
        LocalDate date = LocalDate.parse(datum, DateTimeFormatter.ofPattern("YYYY-MM-dd"));
        try {
            PreparedStatement statementsql = connection.prepareStatement(sql);
            statementsql.setBoolean(1, aanwezigheid.equals("ja"));
            statementsql.setInt(2, studentId);
            statementsql.setInt(3, lesId);
            statementsql.setDate(4, Date.valueOf(date));
            statementsql.execute();

        } catch (SQLException e){
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public void zetBevestiging(String bevestiging, int studentId, int lesId, String datum) {
        String sql = "UPDATE " + this.schema + ".lesstudent" + " SET bevestiging = ? WHERE studentid = ? AND lesid = ? AND datum = ?";
        LocalDate date = LocalDate.parse(datum, DateTimeFormatter.ofPattern("YYYY-MM-dd"));
        try {
            PreparedStatement statementsql = connection.prepareStatement(sql);
            statementsql.setBoolean(1, bevestiging.equals("ja"));
            statementsql.setInt(2, studentId);
            statementsql.setInt(3, lesId);
            statementsql.setDate(4, Date.valueOf(date));
            statementsql.execute();

        } catch (SQLException e){
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public List<Student> getAllAanwezigheid(int lesId, String datum) {
        String sql = "SELECT * FROM " + this.schema + ".lesstudent" + " INNER JOIN" + this.schema + ".student ON lesstudent.studentid = student.id WHERE aanwezigheid = true AND lesid = ? AND bevestiging is null AND datum = ?";
        List<Student> students = new ArrayList<>();
        LocalDate date = LocalDate.parse(datum, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        try {
            PreparedStatement statementsql = connection.prepareStatement(sql);
            statementsql.setInt(1, lesId);
            statementsql.setDate(2, Date.valueOf(date));
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
    public List<Student> getAllNietAanwezigheid(int lesId, String datum) {
        String sql = "SELECT * FROM " + this.schema + ".lesstudent" + " INNER JOIN" + this.schema + ".student ON lesstudent.studentid = student.id WHERE lesid = ? AND bevestiging is null AND datum = ? AND aanwezigheid = false OR aanwezigheid is null";
        List<Student> students = new ArrayList<>();
        LocalDate date = LocalDate.parse(datum, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        try {
            PreparedStatement statementsql = connection.prepareStatement(sql);
            statementsql.setInt(1, lesId);
            statementsql.setDate(2, Date.valueOf(date));
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
    public List<Lesson> getLessenVoorStudent(int studentid, String datum) {
        String sql = "SELECT * FROM " + this.schema + ".lesstudent INNER JOIN " + this.schema + ".les ON lesstudent.lesid = les.id"  + " WHERE studentid = ? AND datum = ?";
        List<Lesson> lessons = new ArrayList<>();
        LocalDate date = LocalDate.parse(datum, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        try {
            PreparedStatement statementsql = connection.prepareStatement(sql);
            statementsql.setInt(1, studentid);
            statementsql.setDate(2, Date.valueOf(date));
            ResultSet result = statementsql.executeQuery();
            while (result.next()) {
                makeLesson(result, lessons);
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        return lessons;
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

    private void makeLesson(ResultSet result, List<Lesson> lessons) throws SQLException {
        String name = result.getString("naam");
        int studiepunten = Integer.parseInt(result.getString("studiepunten"));
        String studierichting = result.getString("studierichting");
        String tijd = result.getString("tijd");
        Lesson lesson = new Lesson(name, studiepunten, studierichting, tijd);
        lessons.add(lesson);
    }
}
