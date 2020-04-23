package com.feiwangSpring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author wsq
 * @version 1.0
 * @date 2020/4/23 9:47
 */
@Controller
public class thymeleafController {
    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public String test(Model model) {
        model.addAttribute("msg","wsq test thymeleaf");
    return  "hello";
    }
}
