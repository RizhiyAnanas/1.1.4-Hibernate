package jm.task.core.jdbc;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Олег", "Федоренко", (byte)24);
        userService.saveUser("Андрей", "Гагарин", (byte)24);
        userService.saveUser("Юрий", "Иванов", (byte)24);
        userService.saveUser("Анастасия", "Абрамова", (byte)24);
        List<User> userList = userService.getAllUsers();
        for(User u:userList){
            System.out.println(u.toString());
        }
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
