package com.sinosoft.cisfrm.server.validation;

import com.sinosoft.cisfrm.server.component.Constants;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by Dawn on 16/2/28.
 */
public class CaptchaValidator implements ConstraintValidator<Captcha, CharSequence> {
    @Override
    public void initialize(Captcha constraintAnnotation) {

    }

    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        String captcha = (String) session.getAttribute(Constants.CAPTCHA);
        String val = (String) value;
        return captcha.equalsIgnoreCase(val.trim());
    }
}
