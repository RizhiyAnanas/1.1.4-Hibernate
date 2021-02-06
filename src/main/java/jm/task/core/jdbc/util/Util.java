package jm.task.core.jdbc.util;

import com.mysql.fabric.jdbc.FabricMySQLDriver;

import java.sql.*;

public class Util {
    private static final String URL="jdbc:mysql://localhost:3306/users?autoReconnect=true&useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    public static Connection connect() {
        Connection connection = null;
        try {
            Driver driver = new FabricMySQLDriver();
            DriverManager.registerDriver(driver);
            connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
        } catch (SQLException e) {
            System.err.println("Ошибка соединения");
        }
        return connection;
    }
    // реализуйте настройку соеденения с БД
}
