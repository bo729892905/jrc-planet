CREATE TABLE
    t_res_user
    (
        id VARCHAR(32) NOT NULL,
        username VARCHAR(32) NOT NULL COMMENT '用户名',
        password VARCHAR(32) NOT NULL COMMENT '密码',
        real_name VARCHAR(32) COMMENT '真实姓名',
        gender INT(1) COMMENT '性别',
        mobile_phone VARCHAR(20) COMMENT '手机号',
        email VARCHAR(64) COMMENT '邮箱',
        salt VARCHAR(16) COMMENT '盐',
        locked TINYINT(1) DEFAULT '0' COMMENT '是否锁定',
        register_date DATETIME COMMENT '注册时间',
        PRIMARY KEY (id),
        CONSTRAINT username_UNIQUE UNIQUE (username)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE
    t_res_role
    (
        id VARCHAR(32) NOT NULL,
        name VARCHAR(32) NOT NULL COMMENT '角色名',
        code VARCHAR(8) NOT NULL COMMENT '角色代码',
        remark VARCHAR(128) COMMENT '角色描述',
        role_type INT(4) COMMENT '类型',
        PRIMARY KEY (id),
        CONSTRAINT name_UNIQUE UNIQUE (name),
        CONSTRAINT code_UNIQUE UNIQUE (code)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

CREATE TABLE
    t_res_permission
    (
        id VARCHAR(32) NOT NULL COMMENT '权限表',
        url VARCHAR(45) COMMENT '权限地址',
        name VARCHAR(45) NOT NULL COMMENT '权限名称',
        per_type INT(4) COMMENT '类型',
        parent_id VARCHAR(45) COMMENT '父节点id',
        visible TINYINT(1) DEFAULT '1' COMMENT '是否可见，默认可见',
        remark VARCHAR(128) COMMENT '描述',
        PRIMARY KEY (id),
        CONSTRAINT url_UNIQUE UNIQUE (url)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE
    t_res_user_role
    (
        user_id VARCHAR(32) NOT NULL COMMENT '用户id',
        role_id VARCHAR(32) NOT NULL COMMENT '角色id',
        CONSTRAINT fk_res_ur_r FOREIGN KEY (role_id) REFERENCES t_res_role (id) ON
    DELETE
        CASCADE
    ON
    UPDATE
        NO ACTION,
        CONSTRAINT fk_res_ur_u FOREIGN KEY (user_id) REFERENCES t_res_user (id)
    ON
    DELETE
        CASCADE
    ON
    UPDATE
        NO ACTION,
        CONSTRAINT index_ur UNIQUE (user_id, role_id),
        INDEX fk_res_ur_r_idx (role_id)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色关联表';

CREATE TABLE
    t_res_role_permission
    (
        role_id VARCHAR(32) COMMENT '角色id',
        permission_id VARCHAR(32) COMMENT '权限id',
        CONSTRAINT fk_res_rp_r FOREIGN KEY (role_id) REFERENCES t_res_role (id) ON
    DELETE
        CASCADE
    ON
    UPDATE
        NO ACTION,
        CONSTRAINT fk_rex_rp_p FOREIGN KEY (permission_id) REFERENCES t_res_permission (id)
    ON
    DELETE
        CASCADE
    ON
    UPDATE
        NO ACTION,
        CONSTRAINT index_rp UNIQUE (role_id, permission_id),
        INDEX fk_rp_r_idx (role_id),
        INDEX fk_rex_rp_p_idx (permission_id)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色权限关联表';

CREATE TABLE
    t_res_dict
    (
        id VARCHAR(32) NOT NULL,
        cn_name VARCHAR(32) NOT NULL COMMENT '中文名',
        en_name VARCHAR(32) NOT NULL COMMENT '英文名',
        code VARCHAR(16) COMMENT '编码',
        parent_id VARCHAR(32) COMMENT '父id',
        seq_no INT DEFAULT '0' COMMENT '排序',
        d_level INT DEFAULT '1' NOT NULL COMMENT '级别：0集合，1字典项',
        status INT COMMENT '状态',
        PRIMARY KEY (id),
        CONSTRAINT fk_res_dp FOREIGN KEY (parent_id) REFERENCES t_res_dict (id) ON
    DELETE
        NO ACTION
    ON
    UPDATE
        NO ACTION,
        INDEX fk_res_dp_idx (parent_id)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='字典表';

CREATE TABLE
    t_res_menu_link
    (
        id VARCHAR(32) NOT NULL,
        name VARCHAR(32) NOT NULL COMMENT '名称',
        en_name VARCHAR(32) COMMENT '英文名',
        url VARCHAR(128) COMMENT '链接',
        is_home TINYINT(1) DEFAULT '0' COMMENT '是否为首页',
        icon_cls VARCHAR(45) COMMENT '菜单图标样式',
        target VARCHAR(16) COMMENT '打开方式',
        visible TINYINT(1) DEFAULT '1' COMMENT '是否可见',
        parent_id VARCHAR(32) COMMENT '父id',
        seq_no INT(4) COMMENT '排序',
        PRIMARY KEY (id),
        CONSTRAINT fk_res_mp FOREIGN KEY (parent_id) REFERENCES t_res_menu_link (id) ON
    DELETE
        NO ACTION
    ON
    UPDATE
        NO ACTION,
        CONSTRAINT en_name UNIQUE (en_name),
        INDEX fk_res_mp_idx (parent_id)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8;