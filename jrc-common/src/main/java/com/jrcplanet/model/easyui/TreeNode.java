package com.jrcplanet.model.easyui;

import java.util.List;

/**
 * easyui 树节点
 * Created by rxb on 2016/4/20.
 */
public class TreeNode {
    /**
     * id
     */
    private String id;
    /**
     * 文本
     */
    private String text;
    /**
     * 图标样式
     */
    private String iconCls;
    /**
     * 状态
     */
    private String states;
    /**
     * 是否选中
     */
    private Boolean checked = false;
    /**
     * 属性
     */
    private Object attributes;
    /**
     * 子节点
     */
    private List<TreeNode> children;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public String getStates() {
        return states;
    }

    public void setStates(String states) {
        this.states = states;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public Object getAttributes() {
        return attributes;
    }

    public void setAttributes(Object attributes) {
        this.attributes = attributes;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNode> children) {
        this.children = children;
    }
}
