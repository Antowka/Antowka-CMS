package ru.antowka.dao.Impl;

import org.hibernate.Session;
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


    private HibernateSessionFactory hibernateSessionFactory;



    public HibernateSessionFactory getHibernateSessionFactory() {
        return hibernateSessionFactory;
    }

    public void setHibernateSessionFactory(HibernateSessionFactory hibernateSessionFactory) {
        this.hibernateSessionFactory = hibernateSessionFactory;
    }



    @Transactional
    public User findByUserName(String login) {

        User user = null;



        try {
            Session session = hibernateSessionFactory.getSession();
            user = (User) session.get(User.class, login);
        } catch (RuntimeException e){
            String test ="0";
        }

        return user;
    }
}
