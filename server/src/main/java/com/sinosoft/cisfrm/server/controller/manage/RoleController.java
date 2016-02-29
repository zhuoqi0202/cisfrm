package com.sinosoft.cisfrm.server.controller.manage;

import com.sinosoft.cisfrm.manage.user.entity.Role;
import com.sinosoft.cisfrm.manage.user.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

/**
 * Created by Dawn on 16/2/29.
 */
@Controller
@RequestMapping("/manage/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @RequestMapping(path = "/getAll", method = RequestMethod.GET)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<Role> gets() {
        List<Role> roles = roleService.findValidRoles();
        return roles;
    }
}
