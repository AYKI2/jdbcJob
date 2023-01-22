package org.example.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String url = "jdbc:postgresql://localhost:5432/java8";
    private static final String username = "postgres";
    private static final String password = "admin123";
    public static Connection getConnection(){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url,username,password);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return connection;
    }
}
