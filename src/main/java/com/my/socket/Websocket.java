package com.my.socket;

import com.my.common.ItvJsonUtil;
import com.my.dao.GroupMapper;
import com.my.dao.MessageMapper;
import com.my.dao.UserMapper;
import com.my.entity.Message;
import com.my.entity.User;
import com.my.entity.WebsocketMsg;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.server.standard.SpringConfigurator;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Component
@Scope("prototype")
@ServerEndpoint(value = "/{uid}/chat.do", configurator = SpringConfigurator.class)
public class Websocket {
    private Logger logger = Logger.getLogger(this.getClass());
    private static Map<String, Session> clients = new ConcurrentHashMap<String, Session>();
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MessageMapper messageMapper;
    @Autowired
    private GroupMapper groupMapper;

    @OnOpen  
    public void onOpen(@PathParam("uid") String uid, Session session) throws IOException {
        logger.info(uid+"上线了");
        userMapper.modifyOnline("1", uid);
        clients.put(uid, session);
        Message message = new Message();
        message.setFromtarget(uid);
        message.setType("3");
        message.setCreatetime(new Date());
        message.setContent((uid + "上线了").getBytes());
        messageMapper.insertSelective(message);
        // 组装信息
        WebsocketMsg websocketMsg = new WebsocketMsg();
        websocketMsg.setCode("00");
        websocketMsg.setType("3"); //上线
        websocketMsg.setMsg(uid);
        List<User> friends = userMapper.getFriends(uid);
        if (CollectionUtils.isNotEmpty(friends)){
            for (User friend : friends) {
                sendMessageTo(websocketMsg, friend.getUid());
            }
        }
    }  
  
    @OnClose  
    public void onClose(@PathParam("uid") String uid) throws IOException {
        logger.info(uid+"下线了");
        userMapper.modifyOnline("0", uid);
        clients.remove(uid);

        Message message = new Message();
        message.setFromtarget(uid);
        message.setType("4");
        message.setCreatetime(new Date());
        message.setContent((uid + "下线了").getBytes());
        messageMapper.insertSelective(message);

        WebsocketMsg websocketMsg = new WebsocketMsg();
        websocketMsg.setCode("00");
        websocketMsg.setType("4"); //下线
        websocketMsg.setMsg(uid);
        List<User> friends = userMapper.getFriends(uid);
        if (CollectionUtils.isNotEmpty(friends)){
            for (User friend : friends) {
                sendMessageTo(websocketMsg, friend.getUid());
            }
        }
    }  
  
    @OnMessage  
    public void onMessage(@PathParam("uid") String uid, String message, Session session){
        logger.info(uid + "发送消息" + message);
        WebsocketMsg websocketMsg = ItvJsonUtil.jsonToObj(message, WebsocketMsg.class);
        String type = websocketMsg.getType();
        websocketMsg.setMsgFrom(uid);
        websocketMsg.setCode("00");
        boolean flag = true;
        if (StringUtils.equals(type,"1")){//点对点消息
            flag = sendMessageTo(websocketMsg, websocketMsg.getTarget());
        }
        if (StringUtils.equals(type,"2")){//点对点消息

        }
        if (StringUtils.equals(type,"5")){//订阅消息通知

        }
        if (StringUtils.equals(type,"6")){//系统消息通知

        }

        if (flag){//发送成功添加数据
            Message messageDB = new Message();
            messageDB.setType(type);
            messageDB.setCreatetime(new Date());
            messageDB.setFromtarget(uid);
            messageDB.setContent(websocketMsg.getMsg().getBytes());
            messageDB.setTotarget(websocketMsg.getTarget());
            messageMapper.insertSelective(messageDB);
        }
    }  
  
    @OnError  
    public void onError(Session session, Throwable error) {  
        error.printStackTrace();  
    }  
  
    public boolean sendMessageTo(WebsocketMsg websocketMsg, String target){
        boolean flag = true;
        try {
            logger.info("消息发送" + ItvJsonUtil.toJson(websocketMsg));
            Session session = clients.get(target);
            if (session != null){
                session.getBasicRemote().sendText(ItvJsonUtil.toJson(websocketMsg));
            }
        }catch (Exception e){
            logger.error("消息发送给" + target + "失败");
            e.printStackTrace();
            flag = false;
        }
        return flag;
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
      

    /*public static synchronized int getOnlineCount() {
        return onlineCount;  
    }  
  
    public static synchronized void addOnlineCount() {  
    	Websocket.onlineCount++;  
    }  
  
    public static synchronized void subOnlineCount() {  
    	Websocket.onlineCount--;  
    }  
  
    public static synchronized Map<UserInfo, Websocket> getClients() {  
        return clients;  
    }*/
}
