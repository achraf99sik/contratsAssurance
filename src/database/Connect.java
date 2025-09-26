package database;

import java.sql.Connection;
import java.sql.SQLException;

public class Connect {
    private final Connection connection;
    private static Connect instance;

    private Connect() {
        try {
            this.connection = JdbcConfig.createConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to create DB connection", e);
        }
    }

    public static Connect getInstance() {
        if (instance == null) {
            instance = new Connect();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
