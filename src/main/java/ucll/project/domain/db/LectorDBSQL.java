package ucll.project.domain.db;

import ucll.project.domain.model.Lector;
import ucll.project.util.DbConnectionService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LectorDBSQL implements LectorDB {
    private final Connection connection;
    private final String schema;

    public LectorDBSQL() {
        this.connection = DbConnectionService.getDbConnection();
        this.schema = DbConnectionService.getSearchPath();
    }


    @Override
    public Lector getLector(String unummer) {
        String sql = "select * from lector where nummer = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, unummer);
            ResultSet resultset = preparedStatement.executeQuery();
            resultset.next();
            return makeLector(resultset);
        } catch (SQLException e) {
            throw new DbException("Lector bestaat niet!");
        }
    }

    private Lector makeLector(ResultSet resultSet) throws SQLException {
        String voornaam = resultSet.getString("voornaam");
        String achternaam = resultSet.getString("achternaam");
        String nummer = resultSet.getString("nummer");
        String wachtwoord = resultSet.getString("wachtwoord");
        return new Lector(voornaam, achternaam, nummer, wachtwoord);
    }
}
