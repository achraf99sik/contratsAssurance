import controllers.MainMenuController;
import database.Connect;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        Connection conn = Connect.getInstance().getConnection();
        System.out.println("Connected: " + (conn != null && !conn.isClosed()));
        new MainMenuController().start();
    }
}