package com.my.controller;

import com.my.common.ItvJsonUtil;
import com.my.entity.User;
import com.my.service.XTXService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class XTXController {
	private Logger logger = Logger.getLogger(this.getClass());
    @Autowired
    private XTXService xtxService;

    @RequestMapping("/regist")
	public String regist(User user, HttpServletRequest req){
        logger.info("开始注册》 user=" + ItvJsonUtil.toJson(user));
        try {
            xtxService.regist(user);
            logger.info("注册结束》 重定向到登录页");
            return "redirect:login.jsp";
        }catch (Exception e){
            e.printStackTrace();
            return e.getMessage();
        }
	}
	@RequestMapping("/login")
	public String login(User user, HttpServletRequest req){
        logger.info("开始登录》 user=" + ItvJsonUtil.toJson(user));
        try {
            User loginUser = xtxService.login(user);
            List<User> friends = xtxService.getFriends(loginUser.getUid());
            req.getSession().setAttribute("user", loginUser);
            req.getSession().setAttribute("friends", friends);
            return "redirect:static/mode/index.jsp";
        }catch (Exception e){
            e.printStackTrace();
            return e.getMessage();
        }
	}

	@ResponseBody
    @RequestMapping("/getFriends")
	public List<User> getFriends(String uid){
        logger.info("开始获取好友列表》 uid=" + uid);
        try {
            //List<User> friends = xtxService.getFriends(uid);
            return null;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
