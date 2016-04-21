package com.jrcplanet.model.easyui;

import java.util.List;

/**
 * Created by rxb on 2016/4/20.
 */
public class DataGrid {
    public DataGrid() {

    }

    public DataGrid(Integer total, List<?> rows) {
        this.total = total;
        this.rows = rows;
    }

    /**
     * 总数
     */
    private Integer total;
    /**
     * 列
     */
    private List<?> rows;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }
}
