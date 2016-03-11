package com.sinosoft.cisfrm.server.validation;

import com.sinosoft.cisfrm.common.ValidatorUtil;
import com.sinosoft.cisfrm.manage.company.entity.Company;
import com.sinosoft.cisfrm.manage.company.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by Dawn on 16/3/7.
 */
public class CpyCodeUniqueValidator implements ConstraintValidator<CpyCodeUnique, String> {
    @Autowired
    private CompanyService companyService;

    @Override
    public void initialize(CpyCodeUnique constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (ValidatorUtil.isEmptyIgnoreBlank(value)) return true;
        Company company = companyService.findByCpyCode(value);
        return null == company;
    }
}
