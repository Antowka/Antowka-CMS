package ru.antowka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.antowka.service.ArticleCategoryService;
import ru.antowka.service.ArticleService;

/**
 * Created by anton on 06.08.15.
 */
@Controller
public class ArticlesController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ArticleCategoryService articleCategoryService;


}
