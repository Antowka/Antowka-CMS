package ru.antowka.dao.Impl;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.antowka.dao.HibernateSessionFactory;
import ru.antowka.dao.UserDao;
import ru.antowka.entity.User;

import javax.transaction.Transactional;


/**
 * Created by Anton Nik on 25.07.15.
 */
@Repository
public class UserDaoImpl implements UserDao{

    @Autowired
    private HibernateSessionFactory hibernateSessionFactory;

    @Override
    @Transactional
    public User findByUserName(String login) {

        Session session = hibernateSessionFactory.getSession();
        User user = (User) session.get(User.class, login);
        return user;
    }
}
