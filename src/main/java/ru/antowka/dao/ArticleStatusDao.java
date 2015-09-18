package ru.antowka.dao;

import ru.antowka.entity.ArticleStatus;


/**
 * Created by anton on 10.08.15.
 */
public interface ArticleStatusDao {

    ArticleStatus getStatusById(int statusId);
    int createArticleStatus(ArticleStatus articleStatus);
    void removeArticleStatus(ArticleStatus articleStatus);
}
