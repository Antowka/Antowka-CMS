package ru.antowka.dao;

import org.hibernate.Session;

/**
 * Created by anton on 25.07.15.
 */
public interface HibernateSessionFactory {

    public Session getSession();
}
