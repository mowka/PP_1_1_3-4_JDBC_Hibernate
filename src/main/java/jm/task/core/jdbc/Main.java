package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {

//    public static void main(String[] args) throws SQLException, ClassNotFoundException {
//        String user = "root";
//        String password = "root";
//        String connectionUrl = "jdbc:mysql://localhost:3306/db lesson_1.1.3";
//        Class.forName("com.mysql.cj.jdbc.Driver");
//        try (Connection connection = DriverManager.getConnection(connectionUrl, user, password)) {
//            System.out.printf("Connection successfully");
//        }
//    }
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/db lesson_1.1.3";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "root";

    public static void main(String[] args) throws SQLException {

////        Main.getConnection();
        UserDao userDao = new UserDaoJDBCImpl();
        UserServiceImpl userService = new UserServiceImpl();

        userService.createUsersTable();
        userService.saveUser("Name1", "LastName1", (byte) 20);
        userService.saveUser("Name2", "LastName2", (byte) 25);
        userService.saveUser("Name3", "LastName3", (byte) 31);
        userService.saveUser("Name4", "LastName4", (byte) 38);

        userService.removeUserById(1);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();

//        userDao.createUsersTable();
//
//        userDao.saveUser("Name1", "LastName1", (byte) 20);
//        userDao.saveUser("Name2", "LastName2", (byte) 25);
//        userDao.saveUser("Name3", "LastName3", (byte) 31);
//        userDao.saveUser("Name4", "LastName4", (byte) 38);
//
//        userDao.removeUserById(1);
//        userDao.getAllUsers();
//        userDao.cleanUsersTable();
//        userDao.dropUsersTable();

    }

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            System.out.println("Connection OK");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Connection ERROR");
        }
        return connection;
    }
}
