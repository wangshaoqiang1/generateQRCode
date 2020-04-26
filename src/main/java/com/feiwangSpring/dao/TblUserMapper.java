package com.feiwangSpring.dao;

import com.feiwangSpring.entity.TblUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
@Mapper
@Component
public interface TblUserMapper {
    int deleteByPrimaryKey(Integer uid);

    int insert(TblUser record);

    TblUser selectByPrimaryKey(Integer uid);

    List<TblUser> selectAll();

    int updateByPrimaryKey(TblUser record);
}