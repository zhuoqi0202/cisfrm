package com.sinosoft.cisfrm.server.validation;

import com.sinosoft.cisfrm.manage.user.entity.User;
import com.sinosoft.cisfrm.manage.user.service.UserService;
import com.sinosoft.cisfrm.server.annotation.CustomValidation;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by Dawn on 16/2/26.
 */
@CustomValidation
public class UsernameUniqueValidator implements ConstraintValidator<UsernameUnique, CharSequence> {
    @Autowired
    private UserService userService;

    @Override
    public void initialize(UsernameUnique constraintAnnotation) {

    }

    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
        User user = userService.findByUsername(String.valueOf(value));
        return null != user;
    }
}
