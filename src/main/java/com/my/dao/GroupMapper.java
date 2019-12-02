package com.my.dao;

import com.my.entity.Group;

public interface GroupMapper {
    int deleteByPrimaryKey(String gid);

    int insert(Group record);

    int insertSelective(Group record);

    Group selectByPrimaryKey(String gid);

    int updateByPrimaryKeySelective(Group record);

    int updateByPrimaryKey(Group record);
}