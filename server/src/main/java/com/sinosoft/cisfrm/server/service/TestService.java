package com.sinosoft.cisfrm.server.service;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

/**
 * Created by Dawn on 16/3/3.
 */
@Validated
public interface TestService {
    void test(@NotBlank String val);
}
