package jm.task.core.jdbc.util;

import com.mysql.cj.Session;
import com.mysql.fabric.jdbc.FabricMySQLDriver;
import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import java.sql.*;
import java.util.Properties;

public class Util {
    private static final String URL="jdbc:mysql://localhost:3306/users?autoReconnect=true&useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static SessionFactory sessionFactory;
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


    public static SessionFactory hibernateConnect() {
        if (sessionFactory == null){
            try{
            Properties properties = new Properties();
            properties.setProperty(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
            properties.setProperty(Environment.HBM2DDL_AUTO,"update");
            properties.setProperty(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
            properties.setProperty(Environment.USER, USERNAME);
            properties.setProperty(Environment.PASS, PASSWORD);
            properties.setProperty(Environment.URL, URL);
            Configuration cfg = new Configuration();
            cfg.setProperties(properties);
            cfg.addAnnotatedClass(User.class);
                ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(properties).buildServiceRegistry();
                sessionFactory = cfg.buildSessionFactory(serviceRegistry);
            }catch (Exception e){
                System.err.println("Ошибка" + e);
            }
        }
        return sessionFactory;
    }
    // реализуйте настройку соеденения с БД
}
