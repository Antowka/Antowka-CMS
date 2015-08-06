package ru.antowka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.antowka.entity.*;
import ru.antowka.service.*;

import java.util.*;

/**
 * Created by anton on 24.07.15.
 */
@Controller
public class MainController {

    @Autowired
    private SettingsService settingsService;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private TicketCategoryService ticketCategoryService;

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

        List<Ticket> tickets = ticketService.getTicketsByUser(user);
        TicketCategory category = ticketCategoryService.getCategoryById(1);

        //****************************end Experement******************************

        Map<String, Object> model = new HashMap<String, Object>();

        Map<String, String> settings = settingsService.getSettings(this.settingsList);

        model.put("settings", settings);

        return new ModelAndView("main", "vars", model);
    }
}
