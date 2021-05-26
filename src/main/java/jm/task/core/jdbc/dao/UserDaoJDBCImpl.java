package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.lang.reflect.InvocationTargetException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    Util util = new Util();

    public void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            util.setConn(DriverManager.getConnection(util.getURL(), util.getUSERNAME(), util.getPASSWORD()));
            System.out.println("Connection to User DB succesfull!");
            util.setStmt(util.getConn().createStatement());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void close() {
        try {
            if (util.getStmt() != null) {
                util.getStmt().close();
            }
            if (util.getConn() != null) {
                util.getConn().close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

        public void createUsersTable () {
            try {
                String SQL = "CREATE TABLE IF NOT EXISTS users " +
                        "(id INTEGER not NULL AUTO_INCREMENT, " +
                        " name VARCHAR(50), " +
                        " lastName VARCHAR (50), " +
                        " age INTEGER not NULL, " +
                        " PRIMARY KEY (id))";
                util.getStmt().execute(SQL);
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }

        public void dropUsersTable () {
            try {
                String SQL = "DROP TABLE IF EXISTS users;";
                util.getStmt().execute(SQL);
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }

        public void saveUser (String name, String lastName,byte age){
            try {
                String SQL = "INSERT INTO users (name, lastName, age) \n" +
                        " VALUES ('" + name + "', '" + lastName + "', '" + age + "');";
                util.getStmt().execute(SQL);
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }

        public void removeUserById ( long id){
            try {
                String SQL = "DELETE * FROM users WHERE id = " + id;
                util.getStmt().execute(SQL);
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }

        public List<User> getAllUsers () {
            try {
                List<User> result = new ArrayList<>();
                String SQL = "SELECT name, lastName, age from users;";
                ResultSet resultSet = util.getStmt().executeQuery(SQL);
                while (resultSet.next()) {
                    result.add(new User(resultSet.getString("name"), resultSet.getString("lastName"), resultSet.getByte("age")));
                }
                return result;
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
                return null;
            }
        }


        public void cleanUsersTable () {
            try {
                String SQL = "TRUNCATE TABLE users;";
                util.getStmt().execute(SQL);
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
