package com.sinosoft.cisfrm.server.vo;

import com.sinosoft.cisfrm.manage.user.entity.User;
import com.sinosoft.cisfrm.server.validation.Captcha;
import com.sinosoft.cisfrm.server.validation.group.First;
import com.sinosoft.cisfrm.server.validation.group.Login;
import com.sinosoft.cisfrm.server.validation.group.Second;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.GroupSequence;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;

/**
 * Created by Dawn on 16/2/26.
 */
@GroupSequence({LoginVo.class, Second.class})
public class LoginVo {
    @NotNull
    @Valid
    @ConvertGroup(from = Second.class, to = Login.class)
    private User user;

    @NotBlank(message = "{validation.login.captcha.notblank}")
    @Captcha(message = "{validation.login.captcha.captcha}", groups = {Second.class})
    private String captcha;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }
}
