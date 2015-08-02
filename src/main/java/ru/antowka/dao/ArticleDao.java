package ru.antowka.dao;

import ru.antowka.entity.Article;

import java.util.Set;

/**
 * Created by anton on 03.08.15.
 */
public interface ArticleDao {

    Article findArticleById(int articleId);
    Set<Article> findArticlesByCategoryId(int categoryId);
}
