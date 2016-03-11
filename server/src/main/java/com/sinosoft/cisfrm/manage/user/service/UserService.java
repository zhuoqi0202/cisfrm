package com.sinosoft.cisfrm.manage.user.service;

import com.sinosoft.cisfrm.manage.user.entity.User;
import com.sinosoft.cisfrm.server.validation.UpdateUsernameUnique;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * Created by Dawn on 16/2/19.
 */
@Validated
public interface UserService {
    User findByUsername(String username);

    List<User> findAll();

    User findById(int moduleId);

    void save(User user);

    void update(@UpdateUsernameUnique(message = "{validation.user.username.usernameunique}") User user);

    void delete(int moduleId);
}
