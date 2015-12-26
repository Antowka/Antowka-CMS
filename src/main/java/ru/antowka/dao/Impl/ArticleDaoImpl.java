package ru.antowka.dao.Impl;


import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.antowka.dao.ArticleDao;
import ru.antowka.dao.HibernateSessionFactory;
import ru.antowka.entity.Article;
import ru.antowka.entity.User;

import javax.transaction.Transactional;
import java.util.List;


/**
 * Created by Anton Nik on 03.08.15.
 */

@Repository
public class ArticleDaoImpl implements ArticleDao {

    @Autowired
    public HibernateSessionFactory hibernateSessionFactory;

    @Override
    @Transactional
    @SuppressWarnings("unchecked")
    public List<Article> getAllArticles(int limit, int offset, Order order, String orderField) {

        List<Article> articles = null;
        Session session = hibernateSessionFactory.getSession();
        articles = (List<Article>)session.createCriteria(Article.class)
                                         .addOrder(order)
                                         .setMaxResults(limit)
                                         .setFirstResult(offset)
                                         .list();

        return articles;

    }

    @Override
    @Transactional
    public Article getArticle(Article article) {

        return (Article)hibernateSessionFactory
                .getSession()
                .get(Article.class, article.getArticleId());
    }


    @Override
    @Transactional
    @SuppressWarnings("unchecked")
    public List<Article> getArticlesByUserOwner(User user) {

        List<Article> articles = null;
        Session session = hibernateSessionFactory.getSession();
        articles = (List<Article>)session.createCriteria(Article.class)
                .add(Restrictions.eq("userOwnerId", user.getUserId()))
                .list();

        return articles;
    }

    /**
     ********************************************** Admin Panel ******************************************************
     */

    @Override
    @Transactional
    public Article createArticle(Article article) {

        hibernateSessionFactory
                .getSession()
                .save(article);

        return article;
    }

    @Override
    @Transactional
    public Article updateArticle(Article article) {

        hibernateSessionFactory
                .getSession()
                .saveOrUpdate(article);

        return article;
    }

    @Override
    @Transactional
    public Article removeArticle(Article article) {
        hibernateSessionFactory
                .getSession()
                .delete(article);

        article.setDeleted(true);

        return article;
    }
}

