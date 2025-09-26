package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConfig {
    private static final String URL = "jdbc:postgresql://localhost:5432/assurance";
    private static final String USER = "postgres";
    private static final String PASSWORD = "root";

    public static Connection createConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
