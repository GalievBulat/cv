package Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionHandler {
    private static String url = "jdbc:postgresql://localhost:5432/postgres";
    private static String username = "postgres";
    private static String password = "";

    private static Connection connection = null;
    private ConnectionHandler(){
    }

    public static synchronized Connection getConnection() throws SQLException {
        if (connection==null) {
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            connection = DriverManager.getConnection(url, username, password);

        }
        return connection;
    }

}
