package com.sinosoft.cisfrm.server.controller;

import com.google.common.collect.Lists;
import com.sinosoft.cisfrm.server.component.message.Message;
import com.sinosoft.cisfrm.server.component.message.MessageFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Dawn on 16/3/3.
 */
@ControllerAdvice
public class GlobalExceptionController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Message handle(MethodArgumentNotValidException e) {
        Message message = MessageFactory.error(e.getBindingResult());
        return message;
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Message handle(ConstraintViolationException e) {
        Iterator<ConstraintViolation<?>> iterator = e.getConstraintViolations().iterator();
        List<String> list = Lists.newArrayList();
        while (iterator.hasNext()) {
            list.add(iterator.next().getMessage());
        }
//        List<String> messages = e.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());
        return MessageFactory.errorMessage(list);
    }
}
