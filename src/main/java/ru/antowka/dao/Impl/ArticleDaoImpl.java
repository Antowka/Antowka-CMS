package ru.antowka.dao.Impl;


import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.antowka.dao.ArticleDao;
import ru.antowka.dao.HibernateSessionFactory;
import ru.antowka.entity.Article;
import ru.antowka.entity.User;

import javax.transaction.Transactional;
import java.util.Set;


/**
 * Created by Anton Nik on 03.08.15.
 */

@Repository
public class ArticleDaoImpl implements ArticleDao {

    @Autowired
    public HibernateSessionFactory hibernateSessionFactory;

    @Override
    @Transactional
    public Article findArticleById(int articleId) {

        Article article = null;
        Session session = hibernateSessionFactory.getSession();
        article = (Article) session.get(Article.class, articleId);

        return article;
    }

    @Override
    @Transactional
    public Set<Article> findArticlesByCategoryId(int categoryId) {

        Set<Article> articles = null;
        Session session = hibernateSessionFactory.getSession();
        articles = (Set<Article>)session.get(Article.class, categoryId);

        return articles;
    }

    @Override
    public Set<Article> findArticlesByUserOwnerId(User user) {
        return null;
    }
}

