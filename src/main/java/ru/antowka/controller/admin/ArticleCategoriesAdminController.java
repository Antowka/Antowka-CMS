package ru.antowka.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.antowka.entity.ArticleCategory;
import ru.antowka.service.ArticleCategoryService;

import java.util.List;

/**
 * Created by Anton Nik on 09.12.15.
 */
@Controller
@RequestMapping("panel/article-category")
public class ArticleCategoriesAdminController {

    @Autowired
    private ArticleCategoryService articleCategoryService;

    /**
     * Method get list categories for articles
     *
     * Link: http://localhost:8080/panel/article-category/get-article-categories
     *
     * @return
     */
    @RequestMapping(value = "get-article-categories", method = RequestMethod.GET)
    public @ResponseBody List<ArticleCategory> getAllArticleCategories(){

        return articleCategoryService.getAllCategories();
    }

    /**
     * Method create new ArticleCategory with parentCategory param
     *
     * @param articleCategory
     * @return
     */
    @RequestMapping(value = "create", method = RequestMethod.POST)
    public @ResponseBody ArticleCategory createArticleCategory(@RequestBody ArticleCategory articleCategory){
        String test = "";
        return articleCategoryService.createArticleCategory(articleCategory);
    }
}
