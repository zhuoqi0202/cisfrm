package com.sinosoft.cisfrm.server.service.impl;

import com.sinosoft.cisfrm.server.service.TestService;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.stereotype.Service;

/**
 * Created by Dawn on 16/3/3.
 */
@Service("testService")
public class TestServiceImpl implements TestService {
    @Override
    public void test(String val) {
        System.out.println(val);
    }
}
