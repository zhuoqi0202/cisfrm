package com.sinosoft.cisfrm.manage.user.service;

import com.sinosoft.cisfrm.manage.user.entity.User;

import java.util.List;

/**
 * Created by Dawn on 16/2/19.
 */
public interface UserService {
    User findByUsername(String username);
    List<User> findAll();
}
