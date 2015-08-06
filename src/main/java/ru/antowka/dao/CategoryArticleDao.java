package ru.antowka.dao;


import ru.antowka.entity.Article;
import ru.antowka.entity.CategoryArticle;

import java.util.List;
import java.util.Set;

/**
 * Created by anton on 03.08.15.
 */
public interface CategoryArticleDao {

    CategoryArticle findCategoryById(int categoryId);
    Set<CategoryArticle> findAllCategories();
}
