package ru.antowka.dao;

import ru.antowka.entity.Article;
import ru.antowka.entity.User;

import java.util.List;

/**
 * Created by anton on 03.08.15.
 */
public interface ArticleDao {

    List<Article> getAllArticles(int limit, int offset, String order, String orderField);
    Article getArticle(Article articleId);
    List<Article> getArticlesByUserOwner(User user);

    /**
     ********************************************** Admin Panel ******************************************************
     */

    Article createArticle(Article article);

    Article updateArticle(Article article);

    void removeArticle(Article article);
}
