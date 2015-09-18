package ru.antowka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.antowka.dao.ArticleDao;
import ru.antowka.entity.Article;
import ru.antowka.entity.User;

import java.util.List;

/**
 * Created by anton on 06.08.15.
 */
@Service
public class ArticleService {

    @Autowired
    private ArticleDao articleDao;

    public List<Article> getArticlesByUser(User user){

        List<Article> articles = null;

        articles = articleDao.findArticlesByUserOwner(user);

        return articles;
    }
}
