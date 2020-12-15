package ucll.project.domain.db;

import ucll.project.util.DbConnectionService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}
