package com.sinosoft.cisfrm.server.validation;

import com.sinosoft.cisfrm.manage.user.entity.User;
import com.sinosoft.cisfrm.manage.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by Dawn on 16/3/4.
 */
public class UserIdNotExistValidator implements ConstraintValidator<UserIdNotExist, Integer> {
    @Autowired
    private UserService userService;

    @Override
    public void initialize(UserIdNotExist constraintAnnotation) {
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if (value != null) {
            User user = userService.findById(value);
            return null != user;
        } else {
            return true;
        }
    }
}
