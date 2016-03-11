package com.sinosoft.cisfrm.manage.codetype.service.impl;

import com.sinosoft.cisfrm.manage.codetype.entity.CodeType;
import com.sinosoft.cisfrm.manage.codetype.service.CodeTypeService;
import com.sinosoft.cisfrm.manage.component.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Dawn on 16/3/7.
 */
@Service("codeTypeService")
public class CodeTypeServiceImpl implements CodeTypeService {
    @Autowired
    private RedisTemplate<String, CodeType> redisTemplate;

    public List<CodeType> findByCodeTyeIsStatus(String code) {
        return redisTemplate.boundListOps(code).range(0, -1);
    }

    @Override
    public List<CodeType> findOrgTypeIsValid() {
        return findByCodeTyeIsStatus(Constants.ORG_TYPE_IN_REDIS);
    }

    @Override
    public List<CodeType> findCpyTypeIsValid() {
        return findByCodeTyeIsStatus(Constants.CPY_TYPE_IN_REDIS);
    }

    @Override
    public List<CodeType> findCapitalTypeIsValid() {
        return findByCodeTyeIsStatus(Constants.CAPITAL_TYPE_IN_REDIS);
    }
}
