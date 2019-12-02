package com.my.service;

import com.my.entity.User;

import java.util.List;

/**
 * @Description:
 * @Author: wangqiang
 * @Date:2019/11/15 10:26
 */
public interface XTXService {
    void regist(User user);

    User login(User user);

    List<User> getFriends(String uid);
}
