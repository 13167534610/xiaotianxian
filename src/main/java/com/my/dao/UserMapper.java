package com.my.dao;

import com.my.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(String uid);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String uid);

    User selectByNickName(String nickName);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    int modifyOnline(@Param("online") String online, @Param("uid") String uid);

    List<User> getFriends(String uid);


}