package com.my.entity;

import java.io.Serializable;

/**
 * @Description:
 * @Author: wangqiang
 * @Date:2019/11/21 16:08
 */
public class WebsocketMsg implements Serializable{

    private String code;
    private String type;
    private String msgFrom;
    private String target;
    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMsgFrom() {
        return msgFrom;
    }

    public void setMsgFrom(String msgFrom) {
        this.msgFrom = msgFrom;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
