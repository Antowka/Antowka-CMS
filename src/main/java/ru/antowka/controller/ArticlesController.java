package ru.antowka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import ru.antowka.entity.Article;
import ru.antowka.service.ArticleService;
import ru.antowka.service.impl.ArticleServiceImpl;

import java.util.List;

/**
 * Controller for Articles on client side
 */
@Controller
@RequestMapping("articles")
public class ArticlesController {

    @Autowired
    private ArticleService articleService;

    /**
     * Response articles by a few params
     *
     * Link: http://localhost:8080/articles/get-articles/?limit=2&offset=5&orderField=creationDate&order=desc
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "get-articles", method = RequestMethod.GET)
    public @ResponseBody List<Article> getArticles(WebRequest request){

        //Make default params if this params isn't exist
        int limit = 10;
        if(request.getParameterMap().containsKey("limit")) {
            limit = Integer.parseInt(request.getParameter("limit"));
        }

        int offset = 0;
        if(request.getParameterMap().containsKey("offset")) {
            offset = Integer.parseInt(request.getParameter("offset"));
        }

        String order = "ASC";
        if(request.getParameterMap().containsKey("order")) {
            order = request.getParameter("order");
        }

        String orderField = "title";
        if(request.getParameterMap().containsKey("orderField")) {
            orderField = request.getParameter("orderField");
        }

        int articleCategoryId = 0;
        if(request.getParameterMap().containsKey("categoryId")) {
            articleCategoryId = Integer.parseInt(request.getParameter("categoryId"));
        }

        return articleService.getArticles(limit, offset, order, orderField, articleCategoryId);
    }
}
