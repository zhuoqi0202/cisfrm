package com.sinosoft.cisfrm.server.validation;

import com.sinosoft.cisfrm.common.ValidatorUtil;
import com.sinosoft.cisfrm.manage.area.entity.Area;
import com.sinosoft.cisfrm.manage.area.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by Dawn on 16/3/11.
 */
public class AreaNameUniqueValidator implements ConstraintValidator<AreaNameUnique, Area> {
    @Autowired
    private AreaService areaService;

    @Override
    public void initialize(AreaNameUnique constraintAnnotation) {

    }

    @Override
    public boolean isValid(Area value, ConstraintValidatorContext context) {
        if (value == null || ValidatorUtil.isEmptyIgnoreBlank(value.getAreaName())) return true;
        Area checkArea = areaService.findByAreaName(value.getAreaName());
        if (value.getModuleId() > 0) {
            Area area = areaService.find(value.getModuleId());
            return null == checkArea || area.getModuleId() == checkArea.getModuleId();
        } else {
            return null == checkArea;
        }
    }
}
