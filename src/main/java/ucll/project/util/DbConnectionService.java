package ucll.project.util;

import java.sql.Connection;
import java.sql.SQLException;

public class DbConnectionService {
    private static String dbURL = "jdbc:postgresql://databanken.ucll.be:62021/webontwerp";
    private static String searchPath = "web3b";
    private static Connection dbConnection;

    public static Connection getDbConnection() {
        return dbConnection;
    }

    public static String getSearchPath() {
        return  searchPath;
    }

    public static void connect() {
        DBConnectionManager connectionManager = DBConnectionManager.getInstance(dbURL, searchPath);
        dbConnection = connectionManager.getConnection();
    }

    public static void disconnect() {
        try {
            dbConnection.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
