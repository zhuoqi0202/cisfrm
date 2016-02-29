package com.sinosoft.cisfrm.server.vo;

import java.io.Serializable;
import java.util.Collection;

/**
 * Created by Dawn on 16/2/29.
 */
public class DataGridVo<T> implements Serializable {
    private long total;
    private Collection<T> rows;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public Collection<T> getRows() {
        return rows;
    }

    public void setRows(Collection<T> rows) {
        this.rows = rows;
    }
}
