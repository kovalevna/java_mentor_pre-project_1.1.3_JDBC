package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;
import jm.task.core.jdbc.model.User;

import java.sql.SQLException;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        User first = new User("Ivan", "Ivanov", (byte) 15);
        User second = new User("Petr", "Petrov", (byte) 20);
        User third = new User("Denis", "Smirnov", (byte) 5);
        User forth = new User("Kirill", "Volkov", (byte) 59);
        userService.saveUser(first.getName(), first.getLastName(), first.getAge());
        System.out.println("User с именем - " + first.getName() + " добавлен в базу данных");
        userService.saveUser(second.getName(), second.getLastName(), second.getAge());
        System.out.println("User с именем - " + second.getName() + " добавлен в базу данных");
        userService.saveUser(third.getName(), third.getLastName(), third.getAge());
        System.out.println("User с именем - " + third.getName() + " добавлен в базу данных");
        userService.saveUser(forth.getName(), forth.getLastName(), forth.getAge());
        System.out.println("User с именем - " + forth.getName() + " добавлен в базу данных");
        List<User> result = userService.getAllUsers();
        for (User user : result) {
            System.out.println(user);
        }
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
