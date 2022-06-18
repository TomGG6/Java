package com.example.App.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static Connection connection;

    public DatabaseConnection() throws ClassNotFoundException {
        String url = "jdbc:sqlite:database.db";
        try {
            this.connection =  DriverManager.getConnection(url);
            System.out.println(connection);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Connection getConnection(){ return this.connection;}
}
