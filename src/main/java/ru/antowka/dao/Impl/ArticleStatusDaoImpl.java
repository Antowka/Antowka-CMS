package ru.antowka.dao.Impl;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.antowka.dao.ArticleStatusDao;
import ru.antowka.dao.HibernateSessionFactory;
import ru.antowka.entity.ArticleStatus;


/**
 * Created by Anton Nik on 10.08.15.
 */
@Repository
public class ArticleStatusDaoImpl implements ArticleStatusDao {

    @Autowired
    private HibernateSessionFactory hibernateSessionFactory;


    @Override
    public ArticleStatus getStatusById(int statusId) {

        ArticleStatus articleStatus = null;
        Session session = hibernateSessionFactory.getSession();
        articleStatus = (ArticleStatus)session.get(ArticleStatus.class, statusId);

        return articleStatus;
    }

    @Override
    public int createArticleStatus(ArticleStatus articleStatus) {

        Session session = hibernateSessionFactory.getSession();

        return (int)session.save(articleStatus);
    }

    @Override
    public void removeArticleStatus(ArticleStatus articleStatus) {

        Session session = hibernateSessionFactory.getSession();
        session.delete(articleStatus);
    }
}
