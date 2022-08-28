package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.Main;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Statement statement = Util.getConnection().createStatement()) {
            String sql = "CREATE TABLE USERS" + "(id integer not null auto_increment unique key, "
                    + "name varchar (30) not null," + "lastName varchar(30) not null," + "age tinyint not null)";
            statement.executeUpdate(sql);
            System.out.println("Таблица создана");
        } catch (SQLSyntaxErrorException e) {
            System.out.printf("Таблица уже создана \n");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        try (Statement statement = Util.getConnection().createStatement()) {
            String sql = "drop table USERS";
            statement.executeUpdate(sql);
            System.out.println("Таблица удалена");
        } catch (SQLSyntaxErrorException e) {
            System.out.println("Предлагаемая таблица для удаления не найдена");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "insert into USERS (name, lastName, age) values (?, ?, ?)";
        try (PreparedStatement preparedStatement = Util.getConnection().prepareStatement(sql))  {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.printf("Пользователь с именем %s добавлен в базу данных \n", name);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        String sql = "delete from users where id = ?";
        try (PreparedStatement preparedStatement = Util.getConnection().prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        String sql = "SELECT id, name, lastName, age from users";
        try (Statement statement = Util.getConnection().createStatement()) {
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong(1));
                user.setName(rs.getString(2));
                user.setLastName(rs.getString(3));
                user.setAge(rs.getByte(4));
                userList.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println(userList);
        return userList;
    }

    public void cleanUsersTable() {
        String sql = "delete from users";
        try (Statement statement = Util.getConnection().createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
