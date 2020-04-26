package com.feiwangSpring.service.impl;

import com.feiwangSpring.dao.SysLogMapper;
import com.feiwangSpring.entity.SysLog;
import com.feiwangSpring.service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wsq
 * @version 1.0
 * @date 2020/4/26 16:10
 */
@Service("sysLogService")
public class SysLogServiceImpl implements ISysLogService {

    @Autowired
    private SysLogMapper sysLogMapper;

    @Override
    public int insertLog(SysLog entity) {
        // TODO Auto-generated method stub
        return sysLogMapper.insert(entity);
    }
}
