package com.jrcplanet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by rxb on 2016/4/27.
 */
@Controller
@RequestMapping(value = "/")
public class TemplateController {
    @RequestMapping(value = "/")
    public ModelAndView index() {
        return new ModelAndView("index");
    }
}
