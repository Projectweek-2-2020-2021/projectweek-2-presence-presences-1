package ucll.project.domain.db;

import ucll.project.domain.model.Lesson;
import ucll.project.domain.model.Student;
import ucll.project.util.DbConnectionService;

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
        } catch (SQLException e) {
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
        String sql = "select * from student inner join lesstudent on student.id = lesstudent.studentid inner join les on les.id = lesstudent.lesid where les.id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultset = preparedStatement.executeQuery();
            while (resultset.next()){
                makeStudent(resultset, students);
            }
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }
        return students;
    }

    private void makeStudent(ResultSet result, List<Student> students) throws SQLException {
        String name = result.getString("naam");
        Student student = new Student();
        students.add(student);
    }



}