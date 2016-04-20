package com.jrcplanet.domain;

/**
 * 菜单链接
 * Created by rxb on 2016/4/20.
 */
public class MenuLink {
    /**
     * 链接名称
     */
    private String name;
    /**
     * 链接地址
     */
    private String url;
    /**
     * 链接图片
     */
    private String icon;
    /**
     * 打开方式
     */
    private String target;
    /**
     * 是否可见
     */
    private Boolean visible;
    /**
     * 父菜单
     */
    private MenuLink parent;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public MenuLink getParent() {
        return parent;
    }

    public void setParent(MenuLink parent) {
        this.parent = parent;
    }
}
