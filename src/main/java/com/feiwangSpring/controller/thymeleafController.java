package com.feiwangSpring.controller;

import com.feiwangSpring.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;

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
    @RequestMapping(value = "/message/member_show", method = RequestMethod.GET)
    public String memberShow(Model model) {
        User vo = new User();
        vo.setUid(12345678L);
        vo.setName("尼古拉丁.赵四");
        vo.setAge(59);
        vo.setSalary(1000.00);
        vo.setBirthday(new Date());
        model.addAttribute("member", vo);
        return "message/member_show";
    }
}
