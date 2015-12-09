package ru.antowka.service;

import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.antowka.dao.ArticleDao;
import ru.antowka.entity.Article;
import ru.antowka.entity.User;

import java.util.List;

/**
 * Created by anton on 06.08.15.
 */
@Service
public class ArticleService {

    @Autowired
    private ArticleDao articleDao;

    /**
     * Method create new article
     *
     * @param article
     * @return
     */
    public Article createArticle(Article article){

        return articleDao.createArticle(article);
    }

    /**
     * Method get articles by pages
     *
     * @return
     */
    public List<Article> getArticles(int limit, int offset, String order, String orderField){

        return articleDao.getAllArticles(limit, offset, order, orderField);
    }

    /**
     * Method for get full article by simple articleId
     *
     * @param article
     * @return
     */
    public Article getArticle(Article article){

        return articleDao.getArticle(article);
    }

    /**
     * Methd get list articles by user owner
     *
     * @param user
     * @return
     */
    public List<Article> getArticlesByUser(User user){

        List<Article> articles = null;

        articles = articleDao.getArticlesByUserOwner(user);

        return articles;
    }
}
