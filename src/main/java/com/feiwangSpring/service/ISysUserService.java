package com.feiwangSpring.service;

import com.feiwangSpring.entity.SysUser;

/**
 * @author wsq
 * @version 1.0
 * @date 2020/4/26 11:13
 */
public interface ISysUserService {
    SysUser findById(Long id);
}
