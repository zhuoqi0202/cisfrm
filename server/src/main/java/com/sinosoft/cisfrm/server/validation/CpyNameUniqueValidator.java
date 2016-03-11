package com.sinosoft.cisfrm.server.validation;

import com.sinosoft.cisfrm.common.ValidatorUtil;
import com.sinosoft.cisfrm.manage.company.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by Dawn on 16/3/8.
 */
public class CpyNameUniqueValidator implements ConstraintValidator<CpyNameUnique,String> {
    @Autowired
    private CompanyService companyService;

    @Override
    public void initialize(CpyNameUnique constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (ValidatorUtil.isEmptyIgnoreBlank(value)) return true;
        return null == companyService.findByCpyName(value);
    }
}
