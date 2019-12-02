package com.my.service;

import com.my.common.Identification;
import com.my.dao.GroupMapper;
import com.my.dao.MessageMapper;
import com.my.dao.UserMapper;
import com.my.entity.User;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @Author: wangqiang
 * @Date:2019/11/15 10:43
 */
@Service
public class XTXServiceImpl implements XTXService {
    private Logger logger = Logger.getLogger(this.getClass());
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MessageMapper messageMapper;
    @Autowired
    private GroupMapper groupMapper;

    @Override
    public void regist(User user) {
        user.setUid(Identification.dateNumberId(32, null));
        user.setCreatetime(new Date());
        user.setOnline("0");
        userMapper.insertSelective(user);
    }

    @Override
    public User login(User user) {
        User dbUser = userMapper.selectByNickName(user.getNickname());
        if (dbUser != null){
            if (!StringUtils.equals(dbUser.getPassword(), user.getPassword())){
                throw new RuntimeException("密码错误");
            }else {
                return dbUser;
            }
        }else {
            throw new RuntimeException("无此账号");
        }
    }

    @Override
    public List<User> getFriends(String uid) {
        return userMapper.getFriends(uid);
    }


    public UserMapper getUserMapper() {
        return userMapper;
    }

    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public MessageMapper getMessageMapper() {
        return messageMapper;
    }

    public void setMessageMapper(MessageMapper messageMapper) {
        this.messageMapper = messageMapper;
    }

    public GroupMapper getGroupMapper() {
        return groupMapper;
    }

    public void setGroupMapper(GroupMapper groupMapper) {
        this.groupMapper = groupMapper;
    }
}
