package com.feiwangSpring.service;

import com.feiwangSpring.entity.SysLog;

/**
 * @author wsq
 * @version 1.0
 * @date 2020/4/26 16:09
 */
public interface ISysLogService {

    /**
     * 插入日志
     * @param entity
     * @return
     */
    int insertLog(SysLog entity);
}
