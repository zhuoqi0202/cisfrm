package com.sinosoft.cisfrm.manage.user.service;

import com.sinosoft.cisfrm.manage.user.entity.Role;

import java.util.List;

/**
 * Created by Dawn on 16/2/29.
 */
public interface RoleService {
    List<Role> findValidRoles();

}
