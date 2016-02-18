package com.sinosoft.cisfrm.manage.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by Dawn on 16/2/18.
 */
public class NotBlankValidator implements ConstraintValidator<NotBlank, CharSequence> {

    @Override
    public void initialize(NotBlank constraintAnnotation) {

    }

    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
        if (value instanceof String) {
            return value.toString().trim().length() > 0;
        }
        return value.toString().trim().length() > 0;
    }
}
