package com.feiwangSpring.service.impl;

import com.feiwangSpring.dao.SysUserMapper;
import com.feiwangSpring.entity.SysUser;
import com.feiwangSpring.service.ISysUserService;
import com.feiwangSpring.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wsq
 * @version 1.0
 * @date 2020/4/26 15:48
 */
@Service("userService2")
public class service2 implements TestService {
    @Autowired
    private SysUserMapper sysUserMapper;
    @Override
    public SysUser findById(Long id) {
        System.out.println("进入service2 service组件");
        // TODO Auto-generated method stub
        int back = Integer.parseInt(String.valueOf(id));
        com.feiwangSpring.entity.SysUser user=sysUserMapper.selectByPrimaryKey(back);
        return user;
    }
}
