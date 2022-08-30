package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    final static SessionFactory sessionFactory = Util.getSessionFactory();
    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        try (Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            String sql = "CREATE TABLE IF NOT EXISTS user "
                    + "(id INTEGER NOT NULL AUTO_INCREMENT UNIQUE KEY, "
                    + "name VARCHAR (30) NOT NULL, " + "lastName VARCHAR(30) NOT NULL, "
                    + "age TINYINT NOT NULL )";
            Query query = session.createSQLQuery(sql).addEntity(User.class);
            query.executeUpdate();
            transaction.commit();
            System.out.println("Таблица успешно создана");
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            String sql = "DROP TABLE IF EXISTS user";
            Query query = session.createSQLQuery(sql).addEntity(User.class);
            query.executeUpdate();
            transaction.commit();
            System.out.println("Таблица успешно удалена");
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            User userToSave = new User(name, lastName, age);
            session.save(userToSave);
            transaction.commit();
            System.out.printf("Пользователь с именем %s добавлен в базу данных \n", name);
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            User userToDelete = session.get(User.class, id);
            session.delete(userToDelete);
            transaction.commit();
            System.out.println("Пользователь удалён");
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try (Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            userList  = session.createQuery("From User ").getResultList();
            transaction.commit();
        }
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            session.createQuery("delete User ").executeUpdate();
            transaction.commit();
            System.out.println("Таблица очищена");
        }
    }
}
