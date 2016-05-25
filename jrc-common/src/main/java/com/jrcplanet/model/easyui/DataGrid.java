package com.jrcplanet.model.easyui;

import java.util.List;

/**
 * Created by rxb on 2016/4/20.
 */
public class DataGrid {
    public DataGrid() {

    }

    public DataGrid(long total, List<?> rows) {
        this.total = total;
        this.rows = rows;
    }

    /**
     * 总数
     */
    private long total;
    /**
     * 列
     */
    private List<?> rows;

    private List<?> footer;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }

    public List<?> getFooter() {
        return footer;
    }

    public void setFooter(List<?> footer) {
        this.footer = footer;
    }
}
