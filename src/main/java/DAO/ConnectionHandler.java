package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionHandler {
    private static final String url = "jdbc:postgresql://localhost:5432/postgres";


    private static Connection connection = null;
    private ConnectionHandler(){
    }

    public static synchronized Connection getConnection(){
        try {
            if (connection == null || connection.isClosed()) {
                try {
                    Class.forName("org.postgresql.Driver");
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                connection = DriverManager.getConnection(url, Secret.username, Secret.password);

            }
            return connection;
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    public static void closeConnection(){
        try {
            connection.close();
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }
    }

}
