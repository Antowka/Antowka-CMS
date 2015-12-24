package ru.antowka.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.antowka.entity.ArticleCategory;
import ru.antowka.entity.MessageResponse;
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
        return articleCategoryService.createArticleCategory(articleCategory);
    }

    /**
     * Method create new ArticleCategory with parentCategory param
     *
     * @param articleCategory
     * @return
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public @ResponseBody ArticleCategory updateArticleCategory(@RequestBody ArticleCategory articleCategory){
        return articleCategoryService.updateArticleCategory(articleCategory);
    }

    /**
     * Remove category
     *
     * Link:  http://localhost:8080/panel/article-category/remove?articleCategoryId=1
     *
     * @param articleCategory
     * @return
     */
    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public @ResponseBody MessageResponse removeArticleCategory(@ModelAttribute ArticleCategory articleCategory){
        return articleCategoryService.removeArticleCategory(articleCategory);
    }
}
