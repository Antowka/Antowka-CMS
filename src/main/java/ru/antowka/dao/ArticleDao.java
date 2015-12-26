package ru.antowka.dao;

import org.hibernate.criterion.Order;
import ru.antowka.entity.Article;
import ru.antowka.entity.User;

import java.util.List;

/**
 * Created by anton on 03.08.15.
 */
public interface ArticleDao {

    List<Article> getAllArticles(int limit, int offset, Order order, String orderField);
    Article getArticle(Article articleId);
    List<Article> getArticlesByUserOwner(User user);

    /**
     ********************************************** Admin Panel ******************************************************
     */

    Article createArticle(Article article);

    Article updateArticle(Article article);

    Article removeArticle(Article article);
}
