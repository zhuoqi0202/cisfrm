package com.sinosoft.cisfrm.manage.codetype.service;

import com.sinosoft.cisfrm.manage.codetype.entity.CodeType;

import java.util.List;

/**
 * Created by Dawn on 16/3/7.
 */
public interface CodeTypeService {
    List<CodeType> findOrgTypeIsValid();

    List<CodeType> findCpyTypeIsValid();

    List<CodeType> findCapitalTypeIsValid();
}
