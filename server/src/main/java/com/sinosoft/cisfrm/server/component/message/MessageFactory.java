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
    public static Message error(Collection<Error> errors){
        Message result = new Message();
        result.setStatus(FAIL);
        result.setErrors(errors);
        return result;
    }
    public static Message error(Error... errors) {
        return error(errors);
    }

    public static Message error(Errors errors) {
        Message result = new Message();
        result.setStatus(FAIL);
        List<Error> errorList = Lists.newArrayList();
        Iterator<ObjectError> objectErrorIterable = errors.getAllErrors().iterator();
        while (objectErrorIterable.hasNext()) {
            String message = objectErrorIterable.next().getDefaultMessage();
            String[] msg = message.split("_");
            Error e = new Error();
            e.setCode(Integer.valueOf(msg[0]));
            e.setMsg(msg[1]);
            errorList.add(e);
        }
        result.setErrors(errorList);
        return result;
    }

    public static Message success() {
        Message result = new Message();
        result.setStatus(SUCCESS);
        return result;
    }

    public static Message success(Object obj) {
        Message result = new Message();
        result.setStatus(SUCCESS);
        result.setData(obj);
        return result;
    }
}
