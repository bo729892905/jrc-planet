package com.jrcplanet.domain;

/**
 * 字典
 * Created by rxb on 2016/4/20.
 */
public class Dictionary extends BaseEntity {
    /**
     * 中文名称
     */
    private String cnName;
    /**
     * 名称名称
     */
    private String enName;
    /**
     * 编码
     */
    private String code;
    /**
     * 上级id
     */
    private String parentId;
    /**
     * 上级字典
     */
    private Dictionary parent;
    /**
     * 排序
     */
    private Integer order;
    /**
     * 级别
     * 0：集合，1字典项
     */
    private Integer dLevel;
    /**
     * 状态
     */
    private Integer status;

    public String getCnName() {
        return cnName;
    }

    public void setCnName(String cnName) {
        this.cnName = cnName;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public Dictionary getParent() {
        return parent;
    }

    public void setParent(Dictionary parent) {
        this.parent = parent;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Integer getdLevel() {
        return dLevel;
    }

    public void setdLevel(Integer dLevel) {
        this.dLevel = dLevel;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
