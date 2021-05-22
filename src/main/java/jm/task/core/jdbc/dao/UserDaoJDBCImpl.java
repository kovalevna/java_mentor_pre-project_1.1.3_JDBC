package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try {
            String SQL = "CREATE TABLE IF NOT EXISTS users " +
                    "(id INTEGER not NULL AUTO_INCREMENT, " +
                    " name VARCHAR(50), " +
                    " lastName VARCHAR (50), " +
                    " age INTEGER not NULL, " +
                    " PRIMARY KEY (id))";
            Util.getStmt().execute(SQL);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void dropUsersTable() {
        try {
            String SQL = "DROP TABLE IF EXISTS users;";
            Util.getStmt().execute(SQL);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            String SQL = "INSERT INTO users (name, lastName, age) \n" +
                    " VALUES ('" + name + " ', '" + lastName + "', '" + age + "');";
            Util.getStmt().execute(SQL);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void removeUserById(long id) {
        try {
            String SQL = "DELETE * FROM user WHERE id = " + id;
            Util.getStmt().execute(SQL);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<User> getAllUsers() {
        try {
            List<User> result = new ArrayList<>();
            String SQL = "SELECT name, lastName, age from users;";
            ResultSet resultSet = Util.getStmt().executeQuery(SQL);
            while (resultSet.next()) {
                result.add(new User(resultSet.getString("name"), resultSet.getString("lastName"), (byte) resultSet.getInt("age")));
            }
            return result;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }


    public void cleanUsersTable() {
        try {
            String SQL = "TRUNCATE TABLE users;";
            Util.getStmt().execute(SQL);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
