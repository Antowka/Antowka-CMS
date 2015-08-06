package ru.antowka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.antowka.dao.CategoryArticleDao;
import ru.antowka.dao.SettingDao;
import ru.antowka.entity.Article;
import ru.antowka.entity.CategoryArticle;
import ru.antowka.entity.Setting;
import ru.antowka.entity.User;
import ru.antowka.service.ArticleService;
import ru.antowka.service.CategoryArticlesService;
import ru.antowka.service.SettingsService;

import java.util.*;

/**
 * Created by anton on 24.07.15.
 */
@Controller
public class MainController {

    @Autowired
    private SettingsService settingsService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CategoryArticlesService categoryArticlesService;

    private String[] settingsList;



    /**
     ******************************* Getters and Setters *******************************************
     */

    public void setSettingsList(String[] settingsList) {
        this.settingsList = settingsList;
    }


    /**
     ******************************* Logic *********************************************************
     */

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView mainPage() {

        //****************************Experement**********************************

        User user = new User();
        user.setUserId(1);
        List<Article> articles = articleService.getArticlesByUser(user);
        Set<CategoryArticle> categories = articles.get(0).getCategories();

        Set<Article> articles1 = categoryArticlesService.getArticlesByCategoryId(1);


        //****************************end Experement******************************

        Map<String, Object> model = new HashMap<String, Object>();

        Map<String, String> settings = settingsService.getSettings(this.settingsList);

        model.put("settings", settings);

        return new ModelAndView("main", "vars", model);
    }
}
