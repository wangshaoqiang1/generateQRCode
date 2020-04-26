package com.feiwangSpring.dao;

import com.feiwangSpring.entity.SysLog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
@Mapper
@Component
public interface SysLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysLog record);

    SysLog selectByPrimaryKey(Integer id);

    List<SysLog> selectAll();

    int updateByPrimaryKey(SysLog record);
}