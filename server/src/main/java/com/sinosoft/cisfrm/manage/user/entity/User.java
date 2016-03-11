package com.sinosoft.cisfrm.manage.user.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.sinosoft.cisfrm.server.validation.UsernameUnique;
import com.sinosoft.cisfrm.server.validation.group.Add;
import com.sinosoft.cisfrm.server.validation.group.Login;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Dawn on 16/2/19.
 */
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "entityCache")
@Cacheable(true)
@Entity
@Table(name = "APP_USER")
public class User implements Serializable {
    private int moduleId;
    @NotBlank(message = "{validation.user.nickname.notblank}", groups = {Add.class})
    private String nickname;
    @NotBlank(message = "{validation.user.username.notblank}", groups = {Login.class, Add.class})
    @UsernameUnique(message = "{validation.user.username.usernameunique}", groups = {Add.class})
    private String username;
    @NotBlank(message = "{validation.user.password.notblank}", groups = {Login.class})
    private String password;

    private String email;

    private byte status;

    private Date createTime;

    private String salt;
    @Valid
    private List<Role> roles = new ArrayList<>();

    @Id
    @Column(name = "MODULEID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getModuleId() {
        return moduleId;
    }

    public void setModuleId(int moduleId) {
        this.moduleId = moduleId;
    }

    @Column(name = "NICKNAME")
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Column(name = "USERNAME")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "PASSWORD")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "EMAIL")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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


    @Column(name = "SALT")
    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "APP_USER_ROLE",
            joinColumns = @JoinColumn(name = "USERID", referencedColumnName = "MODULEID"),
            inverseJoinColumns = @JoinColumn(name = "ROLEID", referencedColumnName = "MODULEID"))
    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (moduleId != user.moduleId) return false;
        if (status != user.status) return false;
        if (nickname != null ? !nickname.equals(user.nickname) : user.nickname != null) return false;
        if (username != null ? !username.equals(user.username) : user.username != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (createTime != null ? !createTime.equals(user.createTime) : user.createTime != null) return false;
        if (salt != null ? !salt.equals(user.salt) : user.salt != null) return false;
        return roles != null ? roles.equals(user.roles) : user.roles == null;

    }

    @Override
    public int hashCode() {
        int result = moduleId;
        result = 31 * result + (nickname != null ? nickname.hashCode() : 0);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (int) status;
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (salt != null ? salt.hashCode() : 0);
        result = 31 * result + (roles != null ? roles.hashCode() : 0);
        return result;
    }
}
