package com.sinosoft.cisfrm.manage.user.entity;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Dawn on 16/2/24.
 */

@Entity
@Table(name = "APP_MENU")
public class Menu implements Serializable {
    private int moduleId;
    private String url;
    private String name;
    private String icon;
    private byte status;
    private Date createTime;
    private int lft;
    private int rgt;
    private byte type;

    @Id
    @Column(name = "MODULEID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getModuleId() {
        return moduleId;
    }

    public void setModuleId(int moduleId) {
        this.moduleId = moduleId;
    }

    @Column(name = "URL")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "ICON")
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Column(name = "STATUS")
    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATETIME")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Column(name = "LFT")
    public int getLft() {
        return lft;
    }

    public void setLft(int lft) {
        this.lft = lft;
    }

    @Column(name = "RGT")
    public int getRgt() {
        return rgt;
    }

    public void setRgt(int rgt) {
        this.rgt = rgt;
    }

    @Column(name = "TYPE")
    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Menu menu = (Menu) o;

        if (moduleId != menu.moduleId) return false;
        if (status != menu.status) return false;
        if (lft != menu.lft) return false;
        if (rgt != menu.rgt) return false;
        if (type != menu.type) return false;
        if (url != null ? !url.equals(menu.url) : menu.url != null) return false;
        if (name != null ? !name.equals(menu.name) : menu.name != null) return false;
        if (icon != null ? !icon.equals(menu.icon) : menu.icon != null) return false;
        return createTime != null ? createTime.equals(menu.createTime) : menu.createTime == null;

    }

    @Override
    public int hashCode() {
        int result = moduleId;
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (icon != null ? icon.hashCode() : 0);
        result = 31 * result + (int) status;
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + lft;
        result = 31 * result + rgt;
        result = 31 * result + (int) type;
        return result;
    }
}
