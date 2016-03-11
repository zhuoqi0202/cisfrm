package com.sinosoft.cisfrm.manage.user.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.sinosoft.cisfrm.server.validation.group.Add;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Dawn on 16/2/22.
 */
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "entityCache")
@Cacheable(true)
@Entity
@Table(name = "APP_ROLE")
public class Role implements Serializable {
    @NotNull(message = "{validation.role.moduleId.notnull}", groups = Add.class)
    private int moduleId;
    private String name;
    private byte status;
    private Date createTime;

    @Id
    @Column(name = "MODULEID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getModuleId() {
        return moduleId;
    }

    public void setModuleId(int moduleId) {
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Role role = (Role) o;

        if (moduleId != role.moduleId) return false;
        if (status != role.status) return false;
        if (name != null ? !name.equals(role.name) : role.name != null) return false;
        return createTime != null ? createTime.equals(role.createTime) : role.createTime == null;

    }

    @Override
    public int hashCode() {
        int result = moduleId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (int) status;
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        return result;
    }
}
