package ru.antowka.dao;


import ru.antowka.entity.CategoryArticle;

import java.util.Set;

/**
 * Created by anton on 03.08.15.
 */
public interface CategoryArticleDao {

    CategoryArticle findCategoryById(int articleId);
    Set<CategoryArticle> findAllCategories();
}
