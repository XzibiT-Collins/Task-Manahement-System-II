package org.example.taskmanagementsystem.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
    private static final String URL = "jdbc:mysql://localhost:3306/taskdb";
    private static final String USER = "root";
    private static final String PASSWORD = "collinsxzibit1?";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL JDBC driver not found", e);
        }
    }

    public static Connection getConnection() throws SQLException {
         return DriverManager.getConnection(URL,USER,PASSWORD);
    }
}
