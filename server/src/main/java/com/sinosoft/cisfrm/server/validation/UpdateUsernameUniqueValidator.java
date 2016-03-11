package com.sinosoft.cisfrm.server.validation;

import com.sinosoft.cisfrm.common.ValidatorUtil;
import com.sinosoft.cisfrm.manage.user.entity.User;
import com.sinosoft.cisfrm.manage.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by Dawn on 16/3/3.
 */
public class UpdateUsernameUniqueValidator implements ConstraintValidator<UpdateUsernameUnique, User> {
    @Autowired
    private UserService userService;

    @Override
    public void initialize(UpdateUsernameUnique constraintAnnotation) {

    }

    @Override
    public boolean isValid(User value, ConstraintValidatorContext context) {
        if (null == value || ValidatorUtil.isEmptyIgnoreBlank(value.getUsername())) {
            return true;
        }
        User user = userService.findByUsername(String.valueOf(value.getUsername()));
        return null == user || user.getModuleId() == value.getModuleId();
    }
}
