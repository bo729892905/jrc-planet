package com.jrcplanet.domain;

import com.jrcplanet.model.easyui.annotation.TreeId;
import com.jrcplanet.model.easyui.annotation.TreeText;
import com.jrcplanet.util.ValidateUtil;

import java.util.List;
import java.util.UUID;

/**
 * 权限
 * Created by rxb on 2016/1/28.
 */
public class Permission extends BaseEntity {
    @TreeId
    private String id= UUID.randomUUID().toString().replace("-","");
    /**
     * 权限名称
     */
    @TreeText
    private String name;
    /**
     * 权限地址
     */
    private String url;
    /**
     * 权限类型
     */
    private Integer perType;
    /**
     * 上级权限id
     */
    private String parentId;
    /**
     * 是否可见
     */
    private Boolean visible = true;
    /**
     * 描述
     */
    private String remark;
    /**
     * 树形结构状态
     */
    private String state;

    private List<Permission> children;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = ValidateUtil.isEmpty(name) ? null : name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = ValidateUtil.isEmpty(url) ? null : url;
    }

    public Integer getPerType() {
        return perType;
    }

    public void setPerType(Integer perType) {
        this.perType = perType;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = ValidateUtil.isEmpty(parentId) ? null : parentId;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = ValidateUtil.isEmpty(remark) ? null : remark;
    }

    public List<Permission> getChildren() {
        return children;
    }

    public void setChildren(List<Permission> children) {
        this.children = children;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
