package com.sinosoft.cisfrm.server.validation;

import com.sinosoft.cisfrm.common.ValidatorUtil;
import com.sinosoft.cisfrm.manage.company.entity.Company;
import com.sinosoft.cisfrm.manage.company.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by Dawn on 16/3/8.
 */
public class CompanyCpycodeUniqueValidator implements ConstraintValidator<CompanyCpycodeUnique, Company> {
    @Autowired
    private CompanyService companyService;

    @Override
    public void initialize(CompanyCpycodeUnique constraintAnnotation) {

    }

    @Override
    public boolean isValid(Company value, ConstraintValidatorContext context) {
        if (ValidatorUtil.isEmptyIgnoreBlank(value.getCpyCode())) return true;
        Company company = companyService.findByCpyCode(value.getCpyCode());
        return null == company || company.getModuleId() == value.getModuleId();
    }
}
