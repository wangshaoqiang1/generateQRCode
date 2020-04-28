package com.feiwangSpring.controller;

import com.feiwangSpring.config.Log;
import com.feiwangSpring.service.ISysLogService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wsq
 * @version 1.0
 * @date 2020/4/26 16:20
 */
@Controller
public class HomeController {
    private final static Logger log = org.slf4j.LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private ISysLogService logService;

    @RequestMapping("/aop")
    @ResponseBody
    @Log("测试aoplog")
    public Object aop(String name, String nick) {
        Map<String, Object> map =new HashMap<>();
        log.info("我被执行了！");
        map.put("res", "ok");
        map.put("code", "200");
        return map;
    }

    @RequestMapping("/aop2")
    @ResponseBody
    @Log("测试aoplog")
    public String aop2(String name, String nick) {
        Map<String, Object> map =new HashMap<>();
        log.info("aop2 我被执行了！");
       return  "123";
    }

    @RequestMapping("/testaop3")
    @ResponseBody
    public Object testAop3(String name, String nick) {
        Map<String, Object> map = new HashMap<>();

        map.put("res", "ok");
        return map;
    }

    @RequestMapping(value = "/freemarkerHello",method = RequestMethod.GET)
    public String helloFtl(Map<String,Object> map){
        map.put("name","小小");
        return "freemarkerHello";
    }
}
