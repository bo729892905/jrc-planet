package com.jrcplanet.domain;

import java.io.Serializable;
import java.util.UUID;

/**
 * 基础实体类
 * Created by rxb on 2016/1/26.
 */
public class BaseEntity implements Serializable {
    /**
     * id
     */
    private String id = UUID.randomUUID().toString().replace("-", "");
    /**
     * 版本号
     */
    private Integer version = 0;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
