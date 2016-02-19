package com.sinosoft.cisfrm.manage.controller;

import com.sinosoft.cisfrm.user.entity.User;
import com.sinosoft.cisfrm.user.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Dawn on 16/2/18.
 */

@Controller
public class HomeController {
    @Autowired
    private TestService testService;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String index() {
        User user = new User();
        user.setUsername("王晨光");
        user.setPassword("123");
        System.out.println(testService.save(user));
        System.out.println(testService.hello());
        return "index";
    }
}
