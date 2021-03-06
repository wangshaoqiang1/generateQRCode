package com.feiwangSpring.controller;

import com.feiwangSpring.entity.SysUser;
import com.feiwangSpring.service.GenerateCodeService;
import com.feiwangSpring.service.ISysUserService;
import com.feiwangSpring.service.TestService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author wsq
 * @version 1.0
 * @date 2020/4/17 13:36
 */
@Controller
@RequestMapping("/feiwangCode")
public class TestController {
    @Autowired
    private GenerateCodeService generateCodeService;
    @Autowired
    private ISysUserService sysUserService;
    @Resource(name = "userService2")
    private TestService userService;
    @ResponseBody
    @RequestMapping(value="/user",method = RequestMethod.GET)
    public SysUser getUser() {
        userService.findById(1L);
        return sysUserService.findById(1L);
    }
    @ResponseBody
    @ApiOperation(value = "根据文本内容生成二维码",notes = "根据文本内容生成二维码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Content", value = "Content", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "size", value = "size", dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "color", value = "color", dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "type", value = "type", dataType = "String", paramType = "query")
    })
    @RequestMapping(value = "/generateCodeByContent2",method = RequestMethod.GET)
    public Map generateCodeByContent2( String Content, Integer size,Integer color,String type) {
        return generateCodeService.generateCodeByContent(Content,size,color,type);
    }
}
