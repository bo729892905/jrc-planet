package com.jrcplanet.controller.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by rxb on 2016/4/30.
 */
@Controller
@RequestMapping(value = "home")
public class HomeController {
    @RequestMapping(value = "{modelName}.html")
    public ModelAndView render(@PathVariable String modelName) {
        return new ModelAndView("index");
    }
}
