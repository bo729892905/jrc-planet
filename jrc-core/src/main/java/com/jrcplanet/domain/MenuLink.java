package com.jrcplanet.domain;

import com.jrcplanet.model.easyui.annotation.TreeId;
import com.jrcplanet.model.easyui.annotation.TreeText;

import java.util.UUID;

/**
 * 菜单链接
 * Created by rxb on 2016/4/20.
 */
public class MenuLink extends BaseEntity {
    @TreeId
    private String id= UUID.randomUUID().toString().replace("-","");
    /**
     * 链接名称
     */
    @TreeText
    private String name;
    /**
     * 英文名
     */
    private String enName;
    /**
     * 链接地址
     */
    private String url;
    /**
     * 是否为首页
     */
    private boolean isHome;
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
     * 父菜单id
     */
    private String parentId;
    /**
     * 排序
     */
    private Integer seqNo;

    public MenuLink() {
    }

    public MenuLink(String id,String name) {
        this.id = id;
        this.name = name;
    }

    public MenuLink(String id, String name, String iconCls) {
        this.id = id;
        this.name = name;
        this.iconCls = iconCls;
    }

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
        this.name = name;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isHome() {
        return isHome;
    }

    public void setHome(boolean home) {
        isHome = home;
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

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public Integer getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(Integer seqNo) {
        this.seqNo = seqNo;
    }
}
