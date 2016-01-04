package ru.antowka.service;

import org.springframework.web.context.request.WebRequest;
import ru.antowka.entity.Article;
import ru.antowka.entity.MessageResponse;
import ru.antowka.entity.User;

import java.util.List;

/**
 * Created by Anton Nik on 25.12.15.
 */
public interface ArticleService {

    /**
     * Create new article
     *
     * @param article
     * @return
     */
    Article createArticle(Article article);

    /**
     * Update / edit article
     *
     * @param article
     * @return
     */
    Article updateArticle(Article article);

    /**
     * Remove article from db
     *
     * @param article
     * @return
     */
    MessageResponse removeArticle(Article article);

    /**
     * Get articles by params and sort
     *
     * @param limit
     * @param offset
     * @param order
     * @param orderField
     * @return
     */
    List<Article> getArticles(WebRequest request);

    /**
     * Get full Article Object
     *
     * @param article
     * @return
     */
    Article getArticle(Article article);


    /**
     * Get articles by User
     *
     * @param user
     * @return
     */
    List<Article> getArticlesByUser(User user);
}
