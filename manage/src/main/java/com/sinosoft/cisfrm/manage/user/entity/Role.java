package com.sinosoft.cisfrm.manage.user.entity;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Dawn on 16/2/22.
 */
@Entity
@Table(name = "APP_ROLE")
public class Role implements Serializable {
    private long moduleId;
    private String name;
    private int status;
    private Date createTime;
    private List<Menu> menus;

    public Role() {
    }

    public Role(long moduleId, String name) {
        this.moduleId = moduleId;
        this.name = name;
    }

    @Id
    @Column(name = "MODULEID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getModuleId() {
        return moduleId;
    }

    public void setModuleId(long moduleId) {
        this.moduleId = moduleId;
    }

    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "STATUS")
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
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

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "APP_ROLE_MENU",
            joinColumns = @JoinColumn(name = "ROLEID", referencedColumnName = "MODULEID"),
            inverseJoinColumns = @JoinColumn(name = "MENUID", referencedColumnName = "MODULEID"))
    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }
}
