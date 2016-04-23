package com.jrcplanet.domain;

/**
 * 菜单链接
 * Created by rxb on 2016/4/20.
 */
public class MenuLink extends BaseEntity {
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
    private String iconCls;
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

    public MenuLink() {
    }

    public MenuLink(String id,String name) {
        super(id);
        this.name = name;
    }

    public MenuLink(String id, String name, String iconCls) {
        super(id);
        this.name = name;
        this.iconCls = iconCls;
    }

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

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
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
