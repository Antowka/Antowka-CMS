package ru.antowka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.antowka.dao.CategoryArticleDao;
import ru.antowka.entity.Article;
import ru.antowka.entity.CategoryArticle;

import java.util.Set;

/**
 * Created by anton on 06.08.15.
 */
@Service
public class CategoryArticlesService {

    @Autowired
    CategoryArticleDao categoryArticleDao;

    public Set<Article> getArticlesByCategoryId(int categoryId){

        CategoryArticle category = null;
        category = categoryArticleDao.findCategoryById(categoryId);
        return category.getArticles();
    }
}
