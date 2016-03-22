package ru.antowka.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ru.antowka.dao.ArticleDao;
import ru.antowka.dao.UserDao;
import ru.antowka.entity.Article;
import ru.antowka.entity.MessageResponse;
import ru.antowka.entity.User;
import ru.antowka.service.ArticleService;
import ru.antowka.service.MessageResponseService;
import ru.antowka.service.UserService;
import ru.antowka.utils.UtilsHibernate;

import java.util.List;

/**
 * Created by Anton Nik on 06.08.15.
 */
@Service
public class ArticleServiceImpl implements ArticleService{

    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private UserService userService;

    @Autowired
    private MessageResponseService messageResponseService;

    /**
     * Service for create new article
     *
     * @param article
     * @return
     */
    @Override
    public Article createArticle(Article article){

        //Set current authorized user
        User user = userService.getAuthorizedUser();

        article.setUserOwner(user);

        return articleDao.createArticle(article);
    }

    /**
     * Service for update article
     *
     * @param article
     * @return
     */
    @Override
    public Article updateArticle(Article article) {

        return articleDao.updateArticle(article);
    }

    /**
     * Service for remove article
     *
     * @param article
     * @return
     */
    @Override
    public MessageResponse removeArticle(Article article) {

        article = articleDao.removeArticle(article);

        return messageResponseService.getResponseForRemoveEntity(
                article.isDeleted(),
                article.getEntityName(),
                article.getArticleId()
        );
    }

    /**
     * Method get articles by pages
     *
     * @return
     */
    @Override
    public List<Article> getArticles(int limit, int offset, String order, String orderField, int articleCategoryId) {

        return articleDao.getAllArticles(
                limit,
                offset,
                UtilsHibernate.getOrderByString(order, orderField),
                orderField,
                articleCategoryId
        );
    }

    /**
     * Method for get full article by simple articleId
     *
     * @param article
     * @return
     */
    @Override
    public Article getArticle(Article article){

        return articleDao.getArticle(article);
    }

    /**
     * Methd get list articles by user owner
     *
     * @param user
     * @return
     */
    @Override
    public List<Article> getArticlesByUser(User user){

        return articleDao.getArticlesByUserOwner(user);
    }




    /**
     *  ******************************* Getters and Setters ***********************************
     */

    public ArticleDao getArticleDao() {
        return articleDao;
    }

    public void setArticleDao(ArticleDao articleDao) {
        this.articleDao = articleDao;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public MessageResponseService getMessageResponseService() {
        return messageResponseService;
    }

    public void setMessageResponseService(MessageResponseService messageResponseService) {
        this.messageResponseService = messageResponseService;
    }
}
