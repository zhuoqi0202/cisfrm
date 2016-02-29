package com.sinosoft.cisfrm.manage.user.service;

import com.sinosoft.cisfrm.manage.user.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by Dawn on 16/2/24.
 */
public interface UserService {
    User findByUsername(String username);

    List<User> findAll();
}
