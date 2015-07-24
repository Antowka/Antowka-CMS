package ru.antowka.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * Created by anton on 24.07.15.
 */

@Controller
public class MainController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String main(Map<String, Object> model) {

        model.put("title", "Antowka CMS");
        model.put("msg", "HELLO WORLD");
        return "home";
    }
}
