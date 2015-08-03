package ru.antowka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.antowka.dao.SettingDao;
import ru.antowka.entity.Setting;
import ru.antowka.service.SettingsService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by anton on 24.07.15.
 */
@Controller
public class MainController {

    @Autowired
    private SettingsService settingsService;

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

        Map<String, Object> model = new HashMap<String, Object>();

        Map<String, String> settings = settingsService.getSettings(this.settingsList);

        model.put("settings", settings);

        return new ModelAndView("main", "vars", model);
    }
}
