package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String url = "CREATE TABLE `Users` (" +
                "  `id` INT NOT NULL AUTO_INCREMENT," +
                "  `name` VARCHAR(25) NOT NULL," +
                "  `lastName` VARCHAR(25) NOT NULL," +
                "  `age` INT(3) NOT NULL," +
                "PRIMARY KEY (`id`)" +
                ");";
        try (Connection connection = Util.connect(); Statement statement = connection.createStatement()) {
            statement.execute(url);
        } catch (SQLException e) {

        }

    }

    public void dropUsersTable() {
        String url = "DROP TABLE users";

        try (Connection connection = Util.connect(); Statement statement = connection.createStatement()) {
            statement.execute(url);
        } catch (SQLException e) {
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO users (name, lastName, age) values ('" + name + "', '" + lastName +"','" + age + "');";
        try (Connection connection = Util.connect(); Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
        }
        System.out.println("Юзер с именем " + name + " добавлен в базу");


    }

    public void removeUserById(long id) {
        String sql = "DELETE FROM users WHERE id = " + id+ ";";
        try (Connection connection = Util.connect(); Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException throwables) {
        }

    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        String sql = "SELECT * from users;";
        try (Connection connection = Util.connect(); Statement statement = connection.createStatement();ResultSet resultSet = statement.executeQuery(sql);) {
            while(resultSet.next()){
                User user = new User();
                user.setName(resultSet.getString("name"));
                user.setId(resultSet.getLong("id"));
                user.setAge(resultSet.getByte("age"));
                user.setLastName(resultSet.getString("lastName"));
                list.add(user);
            }
        } catch (SQLException throwables) {
        }
        return list;
    }

    public void cleanUsersTable() {
        String sql = "TRUNCATE TABLE users";
        try (Connection connection = Util.connect(); Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
        }

    }
}
