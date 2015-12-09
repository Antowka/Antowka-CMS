package ru.antowka.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import ru.antowka.entity.Article;
import ru.antowka.service.ArticleService;

import java.util.List;

/**
 * Created by Anton Nik on 09.12.15.
 */
@Controller
@RequestMapping("panel/articles")
public class ArticlesAdminController {

    @Autowired
    private ArticleService articleService;

    /**
     * Create new articles
     *
     * @param article
     * @return
     */
    @RequestMapping(value = "create", method = RequestMethod.POST)
    public @ResponseBody
    Article createArticle(@RequestBody Article article){

        return articleService.createArticle(article);
    }

    /**
     * Response articles by a few params
     *
     * Link: http://localhost:8080/panel/articles/get-articles/?limit=2&offset=5&orderField=creationDate&order=desc
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "get-articles", method = RequestMethod.GET)
    public @ResponseBody
    List<Article> getArticles(WebRequest request){

        return articleService.getArticles(Integer.parseInt(request.getParameter("limit")),
                Integer.parseInt(request.getParameter("offset")),
                request.getParameter("order"),
                request.getParameter("orderField"));
    }
}
