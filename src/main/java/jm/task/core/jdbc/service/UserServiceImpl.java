package jm.task.core.jdbc.service;

import jm.task.core.jdbc.Main;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.sql.Connection;
import java.util.List;

public class UserServiceImpl implements UserService {

    UserDaoJDBCImpl userDaoJDBCImpl = new UserDaoJDBCImpl();


    public void createUsersTable() {

    }

    public void dropUsersTable() {

    }

    public void saveUser(String name, String lastName, byte age) {

    }

    public void removeUserById(long id) {

    }

    public List<User> getAllUsers() {
        return userDaoJDBCImpl.getAllUsers();
    }

    public void cleanUsersTable() {

    }
}


