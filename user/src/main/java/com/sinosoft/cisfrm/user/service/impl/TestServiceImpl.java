package com.sinosoft.cisfrm.user.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.sinosoft.cisfrm.user.service.TestService;

/**
 * Created by Dawn on 16/2/18.
 */
@Service(version = "1.0.0")
public class TestServiceImpl implements TestService {
    @Override
    public String hello() {
        return "hello world";
    }
}
