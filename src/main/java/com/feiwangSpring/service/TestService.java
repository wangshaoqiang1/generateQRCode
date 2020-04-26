package com.feiwangSpring.service;

import com.feiwangSpring.entity.SysUser;

/**
 * @author wsq
 * @version 1.0
 * @date 2020/4/26 15:50
 */
public interface TestService {
    SysUser findById(Long id);
}
