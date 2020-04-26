package com.feiwangSpring.service.impl;

import com.feiwangSpring.dao.SysUserMapper;
import com.feiwangSpring.entity.SysUser;
import com.feiwangSpring.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author wsq
 * @version 1.0
 * @date 2020/4/26 11:17
 */
@Component
@Service
public class SysUserServiceImpl implements ISysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public SysUser findById(Long id) {
        // TODO Auto-generated method stub
        int back = Integer.parseInt(String.valueOf(id));
        com.feiwangSpring.entity.SysUser user=sysUserMapper.selectByPrimaryKey(back);
        return user;
    }
}
