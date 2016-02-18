package com.sinosoft.cisfrm.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
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
    private TestService testService;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String index() {
        System.out.println(testService.hello());
        return "index";
    }

    @Autowired
    public void setTestService(TestService testService) {
        this.testService = testService;
    }
}
