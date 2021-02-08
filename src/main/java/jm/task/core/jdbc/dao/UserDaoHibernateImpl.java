package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        Session session = null;
        try{
        String sql = "CREATE TABLE `Users` (" +
                "  `id` INT NOT NULL AUTO_INCREMENT," +
                "  `name` VARCHAR(25) NOT NULL," +
                "  `lastName` VARCHAR(25) NOT NULL," +
                "  `age` INT(3) NOT NULL," +
                "PRIMARY KEY (`id`)" +
                ");";
        SessionFactory factory = Util.hibernateConnect();
        session = factory.openSession();
        Transaction tx = session.beginTransaction();
        session.createSQLQuery(sql).executeUpdate();
        tx.commit();
        }catch (Exception e){

        }finally {
            session.close();
        }
    }
    @Override
    public void dropUsersTable() {
        Session session = null;
        try {
            String sql = "DROP TABLE IF EXISTS Users";
            SessionFactory factory = Util.hibernateConnect();
            session = factory.openSession();
            Transaction tx = session.beginTransaction();
            session.createSQLQuery(sql).executeUpdate();
            tx.commit();
        }catch (Exception e){

        }finally {
            session.close();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
            User user = new User(name, lastName, age);
            SessionFactory factory = Util.hibernateConnect();
            Session session = factory.openSession();
            Transaction tx = session.beginTransaction();
            session.save(user);
            tx.commit();
            session.close();
            return;
    }

    @Override
    public void removeUserById(long id) {
        Session session = Util.hibernateConnect().openSession();
        Transaction tx = session.beginTransaction();
        Query deleteQuery = session.createQuery("delete from User e where e.id=" + id);
        deleteQuery.executeUpdate();
        tx.commit();
        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        List<User> list = null;
        try {
            Session session = Util.hibernateConnect().openSession();
            Query query = session.createQuery("select e from User e");
            list = query.list();
            session.close();

        }catch (Exception e){

        }
        return list;
    }

    @Override
    public void cleanUsersTable() {
        Session session = Util.hibernateConnect().openSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("DELETE from User e");
        query.executeUpdate();
        tx.commit();
        session.close();
    }
}
