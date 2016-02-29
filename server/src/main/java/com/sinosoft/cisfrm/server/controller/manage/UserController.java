package com.sinosoft.cisfrm.server.controller.manage;

import com.sinosoft.cisfrm.manage.user.entity.User;
import com.sinosoft.cisfrm.manage.user.service.UserService;
import com.sinosoft.cisfrm.server.component.message.Message;
import com.sinosoft.cisfrm.server.component.message.MessageFactory;
import com.sinosoft.cisfrm.server.validation.group.Add;
import com.sinosoft.cisfrm.server.vo.DataGridVo;
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

/**
 * Created by Dawn on 16/2/24.
 */
@Controller
@RequestMapping("/manage/user")
public class UserController {
    @Autowired
    private UserService userService;

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
    public Message add(@Validated({Add.class}) @RequestBody User user, Errors errors) {
        Message message = MessageFactory.success();

        return message;
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Message update() {
        Message message = MessageFactory.success();
        return message;
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Message delete() {
        Message message = MessageFactory.success();

        return message;
    }
}
