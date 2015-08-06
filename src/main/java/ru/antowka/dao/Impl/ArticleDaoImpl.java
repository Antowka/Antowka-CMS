package ru.antowka.dao.Impl;


import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.antowka.dao.ArticleDao;
import ru.antowka.dao.HibernateSessionFactory;
import ru.antowka.entity.Article;
import ru.antowka.entity.CategoryArticle;
import ru.antowka.entity.User;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
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
    @SuppressWarnings("unchecked")
    public List<Article> findArticlesByUserOwnerId(User user) {

        List<Article> articles = null;
        Session session = hibernateSessionFactory.getSession();
        articles = (List<Article>)session.createCriteria(Article.class)
                .add(Restrictions.eq("userOwnerId", user.getUserId()))
                .list();

        return articles;
    }
}

