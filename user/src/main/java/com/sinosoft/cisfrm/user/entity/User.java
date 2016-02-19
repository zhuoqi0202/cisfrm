package com.sinosoft.cisfrm.user.entity;

import javax.persistence.Entity;
import java.io.Serializable;

/**
 * Created by Dawn on 16/2/19.
 */

public class User implements Serializable{
    private int id;
    private String username;
    private String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
