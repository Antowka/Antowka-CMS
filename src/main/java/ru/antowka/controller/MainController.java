package ru.antowka.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by anton on 24.07.15.
 */

@Controller
public class MainController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView homePage() {

        Map<String, Object> model = new HashMap<String, Object>();

        model.put("title", "Antowka CMS");
        model.put("msg", "HELLO WORLD");

        return new ModelAndView("main", "msg", model);
    }
}
