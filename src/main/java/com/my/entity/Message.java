package com.my.entity;

import java.util.Date;

public class Message {
    private Integer mid;

    private String fromtarget;

    private String totarget;

    private String type;

    private Date createtime;

    private byte[] content;

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public String getFromtarget() {
        return fromtarget;
    }

    public void setFromtarget(String fromtarget) {
        this.fromtarget = fromtarget == null ? null : fromtarget.trim();
    }

    public String getTotarget() {
        return totarget;
    }

    public void setTotarget(String totarget) {
        this.totarget = totarget == null ? null : totarget.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}