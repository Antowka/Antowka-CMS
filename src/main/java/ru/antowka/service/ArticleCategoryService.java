package ru.antowka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.antowka.dao.ArticleCategoryDao;
import ru.antowka.entity.Article;
import ru.antowka.entity.ArticleCategory;

import java.util.Set;

/**
 * Created by anton on 06.08.15.
 */
@Service
public class ArticleCategoryService {

    @Autowired
    ArticleCategoryDao categoryArticleDao;

    public Set<Article> getArticlesByCategoryId(int categoryId){

        ArticleCategory category = null;
        category = categoryArticleDao.findCategoryById(categoryId);
        return category.getArticles();
    }
}
