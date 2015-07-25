package ru.antowka.dao.Impl;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.antowka.dao.HibernateSessionFactory;
import ru.antowka.dao.UserDao;
import ru.antowka.entity.User;


/**
 * Created by Anton Nik on 25.07.15.
 */

@Repository
public class UserDaoImpl implements UserDao{

    @Autowired
    HibernateSessionFactory hibernateSessionFactory;

    @Override
    @Transactional
    public boolean addUser(User user) {
        return false;
    }

    @Override
    @Transactional
    public boolean updateUser(User user) {
        return false;
    }

    @Override
    @Transactional
    public User getUserById(long userId) {
        return null;
    }

    @Override
    @Transactional
    public User getUserByLogin(String login) {

        User user = null;

        try {
            Session session = hibernateSessionFactory.getSession();
            user = (User) session.get(User.class, login);
        } catch (RuntimeException e) {
            String test = "0";
        }

        return user;
    }

    @Override
    @Transactional
    public User getUserByEmail(String email) {
        return null;
    }

    @Override
    @Transactional
    public boolean deleteUser(User user) {
        return false;
    }
}
