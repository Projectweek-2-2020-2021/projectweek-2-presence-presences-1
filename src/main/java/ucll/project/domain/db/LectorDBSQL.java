package ucll.project.domain.db;

import ucll.project.domain.model.Lector;
import ucll.project.util.DbConnectionService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LectorDBSQL implements LectorDB {
    private Connection connection;
    private final String schema;

    public LectorDBSQL() {
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
    public List<Lector> getAllLectors() {
        List<Lector> lectors = new ArrayList<>();
        String sql = "SELECT * FROM " + this.schema + ".lector";
        try {
            PreparedStatement statementSql = connection.prepareStatement(sql);
            ResultSet result = statementSql.executeQuery();
            while (result.next()) {
                makeLector(result, lectors);
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage(), e);
        }
        return lectors;
    }


    @Override
    public Lector getLector(String unummer) {
        String sql = "select * from " + this.schema + ".lector where nummer = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, unummer);
            ResultSet resultset = preparedStatement.executeQuery();
            resultset.next();
            return makeLector(resultset, new ArrayList<>());
        } catch (SQLException e) {
            throw new DbException("Lector bestaat niet!");
        }
    }

    @Override
    public List<Lector> getLectorPerVak(int vakid) {
        List<Lector> lectors = new ArrayList<>();
        String sql = "select * from " + this.schema + ".lector AS L inner join " + this.schema + ".leslector AS LL on L.id = LL.lectorid inner join " + this.schema + ".les AS LE on LE.id = LL.lesid where LE.id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, vakid);
            ResultSet resultset = preparedStatement.executeQuery();
            while (resultset.next()){
                makeLector(resultset, lectors);
            }
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }
        return lectors;
    }

    @Override
    public int getLectorId(String nummer) {
        String sql = "SELECT id FROM " + this.schema + ".lector" + " WHERE nummer = ?";
        int id;
        try {
            PreparedStatement statementsql = connection.prepareStatement(sql);
            statementsql.setString(1, nummer);
            ResultSet result = statementsql.executeQuery();
            result.next();
            id = result.getInt("id");
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }

        return id;
    }

    private Lector makeLector(ResultSet resultSet, List<Lector> lectors) throws SQLException {
        String voornaam = resultSet.getString("voornaam");
        String achternaam = resultSet.getString("achternaam");
        String nummer = resultSet.getString("nummer");
        String wachtwoord = resultSet.getString("wachtwoord");
        boolean stc = resultSet.getBoolean("stc");
        Lector lector = new Lector(voornaam, achternaam, nummer, wachtwoord, stc);
        lectors.add(lector);
        return lector;
    }
}
