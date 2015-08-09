package ru.antowka.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by anton on 25.07.15.
 */
@Controller
public class AdminController {

    @RequestMapping(value = "/panel", method = RequestMethod.GET)
    public ModelAndView login() {

        ModelAndView model = new ModelAndView();
        model.setViewName("panel");
        return model;
    }
}
