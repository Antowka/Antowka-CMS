package ru.antowka.dao;


import ru.antowka.entity.ArticleCategory;

import java.util.List;
import java.util.Set;

/**
 * Created by anton on 03.08.15.
 */
public interface ArticleCategoryDao {

    ArticleCategory findCategoryById(int categoryId);
    List<ArticleCategory> findAllCategories();
}
