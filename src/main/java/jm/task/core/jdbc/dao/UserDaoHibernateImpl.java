package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        Session session = null;
        try {
            session = Util.getSessionFactory().openSession();
            Transaction tr = session.beginTransaction();
            Query query = session.createSQLQuery("CREATE TABLE IF NOT EXISTS users (id INTEGER not NULL AUTO_INCREMENT, name VARCHAR(50), lastName VARCHAR (50), age INTEGER not NULL, PRIMARY KEY (id));").addEntity(User.class);
            query.executeUpdate();
            tr.commit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void dropUsersTable() {
        Session session = null;
        try {
            session = Util.getSessionFactory().openSession();
            Transaction tr = session.beginTransaction();
            Query query = session.createSQLQuery("DROP TABLE IF EXISTS users;").addEntity(User.class);
            query.executeUpdate();
            tr.commit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }


    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = null;
        Transaction transaction;
        try {
            session = Util.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.saveOrUpdate(new User(name, lastName, age));
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }


    @Override
    public void removeUserById(long id) {
        Session session = null;
        Transaction transaction;
        try {
            session = Util.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.delete(id);
            transaction.commit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        Session session = null;
        try {
            session = Util.getSessionFactory().openSession();
            Transaction tr = session.beginTransaction();
            users = session.createCriteria(User.class).list();
            tr.commit();
        } catch (Exception ex) {
            ex.getMessage();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        Session session = null;
        try {
            session = Util.getSessionFactory().openSession();
            Transaction tr = session.beginTransaction();
            Query query = session.createSQLQuery("TRUNCATE table users");
            query.executeUpdate();
            tr.commit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
