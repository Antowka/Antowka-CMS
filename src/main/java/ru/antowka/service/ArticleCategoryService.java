package ru.antowka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.antowka.dao.ArticleCategoryDao;
import ru.antowka.entity.ArticleCategory;

import java.util.List;

/**
 * Created by anton on 06.08.15.
 */
@Service
public class ArticleCategoryService {

    @Autowired
    ArticleCategoryDao categoryArticleDao;

    public List<ArticleCategory> getAllCategories(){

         return categoryArticleDao.getAllCategories();
    }

    public ArticleCategory getArticlesByCategoryId(int categoryId){

        ArticleCategory category = null;
        category = categoryArticleDao.getCategoryById(categoryId);
        return category;
    }
}
