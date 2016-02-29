package com.sinosoft.cisfrm.server.component.message;

import java.io.Serializable;
import java.lang.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Dawn on 16/2/25.
 */
public class Message implements Serializable {
    private int status;
    private Collection<Error> errors;
    private Object data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Collection<Error> getErrors() {
        return errors;
    }

    public void setErrors(Collection<Error> errors) {
        this.errors = errors;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
