package com.my.entity;

import java.util.Date;

public class Group {
    private String gid;

    private String gname;

    private String introduce;

    private String ico;

    private Integer maxcount;

    private Integer currentcount;

    private Integer maxmanagercount;

    private Integer managercount;

    private String active;

    private String createuser;

    private Date createtime;

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid == null ? null : gid.trim();
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname == null ? null : gname.trim();
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce == null ? null : introduce.trim();
    }

    public String getIco() {
        return ico;
    }

    public void setIco(String ico) {
        this.ico = ico == null ? null : ico.trim();
    }

    public Integer getMaxcount() {
        return maxcount;
    }

    public void setMaxcount(Integer maxcount) {
        this.maxcount = maxcount;
    }

    public Integer getCurrentcount() {
        return currentcount;
    }

    public void setCurrentcount(Integer currentcount) {
        this.currentcount = currentcount;
    }

    public Integer getMaxmanagercount() {
        return maxmanagercount;
    }

    public void setMaxmanagercount(Integer maxmanagercount) {
        this.maxmanagercount = maxmanagercount;
    }

    public Integer getManagercount() {
        return managercount;
    }

    public void setManagercount(Integer managercount) {
        this.managercount = managercount;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active == null ? null : active.trim();
    }

    public String getCreateuser() {
        return createuser;
    }

    public void setCreateuser(String createuser) {
        this.createuser = createuser == null ? null : createuser.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}