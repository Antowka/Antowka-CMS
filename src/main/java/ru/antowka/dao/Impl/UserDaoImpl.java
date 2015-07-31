package ru.antowka.dao.Impl;

import org.hibernate.Session;
import ru.antowka.dao.HibernateSessionFactory;
import ru.antowka.dao.UserDao;
import ru.antowka.entity.User;


/**
 * Created by Anton Nik on 25.07.15.
 */
public class UserDaoImpl implements UserDao{


    private HibernateSessionFactory hibernateSessionFactory;



    public HibernateSessionFactory getHibernateSessionFactory() {
        return hibernateSessionFactory;
    }

    public void setHibernateSessionFactory(HibernateSessionFactory hibernateSessionFactory) {
        this.hibernateSessionFactory = hibernateSessionFactory;
    }



    @SuppressWarnings("unchecked")
    public User findByUserName(String login) {

        User user = null;

        Session session = hibernateSessionFactory.getSession();

        user = (User) session.get(User.class, login);

        return user;
    }
}
