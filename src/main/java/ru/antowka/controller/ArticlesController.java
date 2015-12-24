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
 * Created by anton on 06.08.15.
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

        return articleService.getArticles(Integer.parseInt(request.getParameter("limit")),
                Integer.parseInt(request.getParameter("offset")),
                request.getParameter("order"),
                request.getParameter("orderField"));
    }
}
