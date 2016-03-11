package com.sinosoft.cisfrm.manage.user.service.impl;

import com.google.common.collect.Lists;
import com.sinosoft.cisfrm.common.ValidatorUtil;
import com.sinosoft.cisfrm.manage.user.entity.Role;
import com.sinosoft.cisfrm.manage.user.entity.User;
import com.sinosoft.cisfrm.manage.user.repository.RoleRepository;
import com.sinosoft.cisfrm.manage.user.repository.UserRepository;
import com.sinosoft.cisfrm.manage.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Dawn on 16/2/19.
 */
@Service(value = "userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> findAll() {
        return Lists.newArrayList(userRepository.findAll());
    }

    @Override
    public User findById(int moduleId) {
        return userRepository.findOne(moduleId);
    }

    @Override
    @Transactional
    public void save(User user) {
        List<Role> roles = user.getRoles();
        Iterator<Role> roleIterable = roles.iterator();
        List<Role> roleList = Lists.newArrayList();
        while (roleIterable.hasNext()) {
            Role role = roleRepository.findOne(roleIterable.next().getModuleId());
            roleList.add(role);
        }
        user.setRoles(roleList);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void update(User user) {
        User userUpdate = userRepository.findOne(user.getModuleId());
        if (ValidatorUtil.isNotEmptyIgnoreBlank(user.getNickname())) {
            userUpdate.setNickname(user.getNickname().trim());
        }
        if (ValidatorUtil.isNotEmptyIgnoreBlank(user.getUsername())) {
            userUpdate.setUsername(user.getUsername().trim());
        }
        if (ValidatorUtil.isNotEmptyIgnoreBlank(user.getEmail())) {
            userUpdate.setEmail(user.getEmail());
        }
        if (ValidatorUtil.isNotEmptyIgnoreBlank(user.getSalt())) {
            userUpdate.setSalt(user.getSalt());
            userUpdate.setPassword(user.getPassword());
        }
        List<Role> roleList = user.getRoles();
        if (null != roleList && roleList.size() > 0) {
            List<Role> roles = userUpdate.getRoles();
            Iterator<Role> roleIterator = roles.iterator();
            while (roleIterator.hasNext()) {
                Role role = roleIterator.next();
                Iterator<Role> userIterator = roleList.iterator();
                boolean flag = false;
                while (userIterator.hasNext()) {
                    Role useRole = userIterator.next();
                    if (role.getModuleId() == useRole.getModuleId()) {
                        userIterator.remove();
                        flag = true;
                    }
                }
                if (!flag) {
                    roleIterator.remove();
                }
            }
            if (roleList.size() > 0) {
                Iterator<Role> iterator = roleList.iterator();
                while (iterator.hasNext()) {
                    userUpdate.getRoles().add(roleRepository.findOne(iterator.next().getModuleId()));
                }
            }
        }
        userRepository.save(userUpdate);
    }

    @Override
    @Transactional
    public void delete(int moduleId) {
        userRepository.delete(moduleId);
    }
}
