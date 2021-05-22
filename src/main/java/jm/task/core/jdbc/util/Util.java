package jm.task.core.jdbc.util;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Util {
    private static Connection conn = null;
    private static Statement stmt = null;

    public static Connection getConn() {
        return conn;
    }

    public static Statement getStmt() {
        return stmt;
    }

    public static void connect() throws ClassNotFoundException, NoSuchMethodException, SQLException, InvocationTargetException, InstantiationException, IllegalAccessException {
        String url = "jdbc:mysql://localhost/user";
        String username = "root";
        String password = "Nikita1211";
        Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
        conn = DriverManager.getConnection(url, username, password);
        System.out.println("Connection to User DB succesfull!");
        stmt = conn.createStatement();
    }

    public static void close() throws SQLException {
        if (stmt != null) {
            stmt.close();
        }
        if (conn != null) {
            conn.close();
        }
    }
}
