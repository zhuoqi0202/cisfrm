package com.sinosoft.cisfrm.server.controller.manage;

import com.sinosoft.cisfrm.common.ValidatorUtil;
import com.sinosoft.cisfrm.manage.user.entity.User;
import com.sinosoft.cisfrm.manage.user.service.UserService;
import com.sinosoft.cisfrm.server.component.Constants;
import com.sinosoft.cisfrm.server.component.message.Message;
import com.sinosoft.cisfrm.server.component.message.MessageFactory;
import com.sinosoft.cisfrm.server.validation.UserIdNotExist;
import com.sinosoft.cisfrm.server.validation.group.Add;
import com.sinosoft.cisfrm.server.vo.DataGridVo;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by Dawn on 16/2/24.
 */
@Controller
@RequestMapping("/manage/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private SecureRandomNumberGenerator randomNumberGenerator;

    @Value("#{configProperties['iterations']}")
    private int iterations;

    @Value("#{configProperties['algorithmName']}")
    private String algorithmName;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public DataGridVo<User> get() {
        DataGridVo<User> dataGridVo = new DataGridVo<>();
        dataGridVo.setRows(userService.findAll());
        return dataGridVo;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Message add(@Validated(Add.class) @RequestBody User user) {
        Message message = null;
        if (ValidatorUtil.isEmptyIgnoreBlank(user.getPassword())) {
            user.setPassword(Constants.DEFAULT_PASSWORD);
        }
        String salt = generatorSalt();
        String password = encrypt(user.getPassword(), salt);
        user.setPassword(password);
        user.setSalt(salt);
        user.setStatus(Constants.DEFAULT_STATUS);
        user.setCreateTime(new Date());
        userService.save(user);
        message = MessageFactory.success();
        return message;
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Message update(@RequestBody User user) {
        userService.update(user);
        return MessageFactory.success();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Message delete(@NotNull(message = "{validation.userController.delete.notnull}") @UserIdNotExist(message = "{validation.userController.delete.useridnotexist}") @PathVariable("id") int moduleId) {
        userService.delete(moduleId);
        return MessageFactory.success();
    }

    private String generatorSalt() {
        return randomNumberGenerator.nextBytes().toHex();
    }

    private String encrypt(String password, String salt) {
        return new SimpleHash(algorithmName, password, salt, iterations).toHex();
    }

}
