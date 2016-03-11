package com.sinosoft.cisfrm.server.controller;

import com.google.code.kaptcha.Producer;
import com.google.common.collect.Lists;
import com.sinosoft.cisfrm.manage.user.entity.User;
import com.sinosoft.cisfrm.manage.user.service.UserService;
import com.sinosoft.cisfrm.server.component.Constants;
import com.sinosoft.cisfrm.server.component.message.Error;
import com.sinosoft.cisfrm.server.component.message.Message;
import com.sinosoft.cisfrm.server.component.message.MessageFactory;
import com.sinosoft.cisfrm.server.service.TestService;
import com.sinosoft.cisfrm.server.validation.group.Second;
import com.sinosoft.cisfrm.server.vo.LoginVo;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.util.List;

/**
 * Created by Dawn on 16/2/23.
 */
@Controller
public class HomeController {
    private static final Logger log = LogManager.getLogger(HomeController.class);
    @Autowired
    private Producer captchaProducer;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/isLogin", method = RequestMethod.GET)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public boolean isLogin() {
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getSession().getAttribute(Constants.SESSION_USER);
        return null != user && null != user.getUsername();
    }


    @RequestMapping("/captcha")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");
        String capText = captchaProducer.createText();
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        session.setAttribute(Constants.CAPTCHA, capText);
        BufferedImage bi = captchaProducer.createImage(capText);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(bi, "jpg", out);
        try {
            out.flush();
        } finally {
            out.close();
        }
        return null;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Message login(@Validated({Second.class}) @RequestBody LoginVo login, Errors errors) {
        Message message = null;
        if (errors.hasErrors()) {
            message = MessageFactory.error(errors);
        } else {
            Subject subject = SecurityUtils.getSubject();
            Session session = subject.getSession();
            UsernamePasswordToken token = new UsernamePasswordToken(login.getUser().getUsername(), login.getUser().getPassword());
            List<Error> result = Lists.newArrayList();
            try {
                subject.login(token);
            } catch (UnknownAccountException e) {
                Error error = new Error();
                error.setCode(50000);
                error.setMsg("用户名不存在");
                result.add(error);
            } catch (LockedAccountException e) {
                Error error = new Error();
                error.setCode(50001);
                error.setMsg("锁定的用户");
                result.add(error);
            } catch (IncorrectCredentialsException e) {
                Error error = new Error();
                error.setCode(50002);
                error.setMsg("密码错误");
                result.add(error);
            } finally {
                if (result.size() == 0) {
                    User user = userService.findByUsername(login.getUser().getUsername());
                    session.setAttribute(Constants.SESSION_USER, user);
                    message = MessageFactory.success();
                } else {
                    message = MessageFactory.error(result);
                }
            }
        }
        return message;
    }

    @RequestMapping(value = "/getCurrent", method = RequestMethod.GET)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Message getCurrent() {
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        return MessageFactory.success(session.getAttribute(Constants.SESSION_USER));
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Message logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return MessageFactory.success();
    }

    @Autowired
    private TestService testService;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Message test() {
        testService.test(null);
        return MessageFactory.success();
    }

}
