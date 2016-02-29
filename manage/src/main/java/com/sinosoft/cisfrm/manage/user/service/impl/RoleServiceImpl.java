package com.sinosoft.cisfrm.manage.user.service.impl;

import com.sinosoft.cisfrm.manage.component.Constants;
import com.sinosoft.cisfrm.manage.user.entity.Role;
import com.sinosoft.cisfrm.manage.user.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Dawn on 16/2/29.
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RedisTemplate<String, Role> redisTemplate;

    @Override
    public List<Role> findValidRoles() {
        return redisTemplate.boundListOps(Constants.ROLES_IN_REDIS).range(0, -1);
    }
}
