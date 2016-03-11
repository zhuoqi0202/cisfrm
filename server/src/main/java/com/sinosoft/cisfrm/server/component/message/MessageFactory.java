package com.sinosoft.cisfrm.server.component.message;

import com.google.common.collect.Lists;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Dawn on 16/2/26.
 */
public class MessageFactory {
    private static final int SUCCESS = 0;
    private static final int FAIL = -1;
    private static final String FLAG = "_";

    public static Message error(Collection<Error> errors) {
        Message result = buildErrorMessage();
        result.setErrors(errors);
        return result;
    }

    public static Message error(Error... errors) {
        Message result = buildErrorMessage();
        result.setErrors(Arrays.asList(errors));
        return result;
    }

    public static Message error(Errors errors) {
        Message result = buildErrorMessage();
        List<Error> errorList = Lists.newArrayList();
        Iterator<ObjectError> objectErrorIterable = errors.getAllErrors().iterator();
        while (objectErrorIterable.hasNext()) {
            String message = objectErrorIterable.next().getDefaultMessage();
            errorList.add(handle(message));
        }
        result.setErrors(errorList);
        return result;
    }

    public static Message errorMessage(List<String> messages) {
        Message result = buildErrorMessage();
        Iterator<String> iterator = messages.iterator();
        List<Error> errorList = Lists.newArrayList();
        while (iterator.hasNext()) {
            Error error = handle(iterator.next());
            errorList.add(error);
        }
        result.setErrors(errorList);
        return result;
    }

    public static Message success() {
        return buildSuccessMessage();
    }

    public static Message success(Object data) {
        Message result = buildSuccessMessage();
        result.setData(data);
        return result;
    }

    private static Message buildSuccessMessage() {
        return buildMessage(SUCCESS);
    }

    private static Message buildMessage(int status) {
        Message message = new Message();
        message.setStatus(status);
        return message;
    }

    private static Message buildErrorMessage() {
        return buildMessage(FAIL);
    }

    private static Error handle(String message) {
        String[] msg = message.split(FLAG);
        Error e = new Error();
        e.setCode(Integer.valueOf(msg[0]));
        e.setMsg(msg[1]);
        return e;
    }
}
