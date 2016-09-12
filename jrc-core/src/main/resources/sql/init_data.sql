DROP TABLE IF EXISTS t_res_dict;
DROP TABLE IF EXISTS t_res_menu_link;
DROP TABLE IF EXISTS t_res_user_role;
DROP TABLE IF EXISTS t_res_role_permission;
DROP TABLE IF EXISTS t_res_user;
DROP TABLE IF EXISTS t_res_role;
DROP TABLE IF EXISTS t_res_permission;

CREATE TABLE
  t_res_dict
(
  id        VARCHAR(32)   NOT NULL,
  cn_name   VARCHAR(32)   NOT NULL
  COMMENT '中文名',
  en_name   VARCHAR(32)   NOT NULL
  COMMENT '英文名',
  code      VARCHAR(16) COMMENT '编码',
  parent_id VARCHAR(32) COMMENT '父id',
  seq_no    INT DEFAULT 0
  COMMENT '排序',
  d_level   INT DEFAULT 1 NOT NULL
  COMMENT '级别：0集合，1字典项',
  status    INT COMMENT '状态',
  PRIMARY KEY (id),
  CONSTRAINT fk_res_dp FOREIGN KEY (parent_id) REFERENCES `t_res_dict` (`id`)
    ON
    DELETE
    NO ACTION
    ON
    UPDATE
    NO ACTION,
  INDEX fk_res_dp_idx (parent_id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT = '字典表';

CREATE TABLE
  t_res_menu_link
(
  id        VARCHAR(32) NOT NULL,
  name      VARCHAR(32) NOT NULL
  COMMENT '名称',
  en_name   VARCHAR(32) COMMENT '英文名',
  url       VARCHAR(128) COMMENT '链接',
  is_home   TINYINT(1) DEFAULT 0
  COMMENT '是否为首页',
  icon_cls  VARCHAR(45) COMMENT '菜单图标样式',
  target    VARCHAR(16) COMMENT '打开方式',
  visible   TINYINT(1) DEFAULT 1
  COMMENT '是否可见',
  parent_id VARCHAR(32) COMMENT '父id',
  seq_no    INT(4) COMMENT '排序',
  PRIMARY KEY (id),
  CONSTRAINT fk_res_mp FOREIGN KEY (parent_id) REFERENCES `t_res_menu_link` (`id`)
    ON
    DELETE
    NO ACTION
    ON
    UPDATE
    NO ACTION,
  CONSTRAINT en_name UNIQUE (en_name),
  INDEX fk_res_mp_idx (parent_id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE
  t_res_user
(
  id            VARCHAR(32) NOT NULL,
  username      VARCHAR(32) NOT NULL
  COMMENT '用户名',
  password      VARCHAR(32) NOT NULL
  COMMENT '密码',
  real_name     VARCHAR(32) COMMENT '真实姓名',
  gender        INT(1) COMMENT '性别',
  mobile_phone  VARCHAR(20) COMMENT '手机号',
  email         VARCHAR(64) COMMENT '邮箱',
  salt          VARCHAR(16) COMMENT '盐',
  locked        TINYINT(1) DEFAULT 0
  COMMENT '是否锁定',
  register_date DATETIME COMMENT '注册时间',
  PRIMARY KEY (id),
  CONSTRAINT username_UNIQUE UNIQUE (username)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE
  t_res_role
(
  id        VARCHAR(32) NOT NULL,
  name      VARCHAR(32) NOT NULL
  COMMENT '角色名',
  code      VARCHAR(16) NOT NULL
  COMMENT '角色代码',
  remark    VARCHAR(128) COMMENT '角色描述',
  role_type INT(4) COMMENT '类型',
  PRIMARY KEY (id),
  CONSTRAINT name_UNIQUE UNIQUE (name),
  CONSTRAINT code_UNIQUE UNIQUE (code)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT = '角色表';

CREATE TABLE
  t_res_permission
(
  id        VARCHAR(32) NOT NULL
  COMMENT '权限表',
  url       VARCHAR(45) COMMENT '权限地址',
  name      VARCHAR(45) NOT NULL
  COMMENT '权限名称',
  per_type  INT(4) COMMENT '类型',
  parent_id VARCHAR(45) COMMENT '父节点id',
  visible   TINYINT(1) DEFAULT 1
  COMMENT '是否可见，默认可见',
  remark    VARCHAR(128) COMMENT '描述',
  PRIMARY KEY (id),
  CONSTRAINT url_UNIQUE UNIQUE (url)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE
  t_res_user_role
(
  user_id VARCHAR(32) NOT NULL
  COMMENT '用户id',
  role_id VARCHAR(32) NOT NULL
  COMMENT '角色id',
  CONSTRAINT fk_res_ur_r FOREIGN KEY (role_id) REFERENCES `t_res_role` (`id`)
    ON
    DELETE
    CASCADE
    ON
    UPDATE
    NO ACTION,
  CONSTRAINT fk_res_ur_u FOREIGN KEY (user_id) REFERENCES `t_res_user` (`id`)
    ON
    DELETE
    CASCADE
    ON
    UPDATE
    NO ACTION,
  CONSTRAINT index_ur UNIQUE (user_id, role_id),
  INDEX fk_res_ur_r_idx (role_id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT = '用户角色关联表';

CREATE TABLE
  t_res_role_permission
(
  role_id       VARCHAR(32) COMMENT '角色id',
  permission_id VARCHAR(32) COMMENT '权限id',
  CONSTRAINT fk_res_rp_r FOREIGN KEY (role_id) REFERENCES `t_res_role` (`id`)
    ON
    DELETE
    CASCADE
    ON
    UPDATE
    NO ACTION,
  CONSTRAINT fk_rex_rp_p FOREIGN KEY (permission_id) REFERENCES `t_res_permission` (`id`)
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
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT = '角色权限关联表';

INSERT INTO t_res_menu_link (id, name, en_name, url, is_home, icon_cls, target, visible, parent_id, seq_no)
VALUES ('93b901a0dfcd46a292431d625a3f8cef', '基础管理', NULL, NULL, FALSE, NULL, NULL, NULL, NULL, 1);
INSERT INTO t_res_menu_link (id, name, en_name, url, is_home, icon_cls, target, visible, parent_id, seq_no)
VALUES ('f1ea27fb771c4947b1343d5346a1b3af', '系统监控', '', '', FALSE, '', NULL, NULL, NULL, NULL);
INSERT INTO t_res_menu_link (id, name, en_name, url, is_home, icon_cls, target, visible, parent_id, seq_no)
VALUES ('d5871e0121cb4aeb849f2317907404d3', '系统管理', NULL, NULL, FALSE, NULL, NULL, NULL, NULL, 2);
INSERT INTO t_res_menu_link (id, name, en_name, url, is_home, icon_cls, target, visible, parent_id, seq_no) VALUES
  ('01eb21c80a004149b875895d8aa227cd', '字典管理', 'dictManage', 'partials/system/druid.html', FALSE, 'icon-user-white', '',
   NULL, 'd5871e0121cb4aeb849f2317907404d3', 1);
INSERT INTO t_res_menu_link (id, name, en_name, url, is_home, icon_cls, target, visible, parent_id, seq_no) VALUES
  ('174a4fa2907a4a0381be62b66ee6f477', '用户管理', 'userManage', 'partials/user/userList.html', FALSE, 'icon-user-white',
   NULL, NULL, '93b901a0dfcd46a292431d625a3f8cef', 1);
INSERT INTO t_res_menu_link (id, name, en_name, url, is_home, icon_cls, target, visible, parent_id, seq_no) VALUES
  ('4be92680a52042f09812227d4412d054', '权限管理', 'permissionManage', 'partials/perm/perm-list.html', FALSE,
   'icon-lock-white', '', NULL, '93b901a0dfcd46a292431d625a3f8cef', 3);
INSERT INTO t_res_menu_link (id, name, en_name, url, is_home, icon_cls, target, visible, parent_id, seq_no) VALUES
  ('96d657001b3248148a4f399b3eab4158', '实时监控', 'monitor', 'partials/system/monitor.html', FALSE, 'icon-user-white',
   NULL, NULL, 'f1ea27fb771c4947b1343d5346a1b3af', NULL);
INSERT INTO t_res_menu_link (id, name, en_name, url, is_home, icon_cls, target, visible, parent_id, seq_no) VALUES
  ('a766905a5f3443f6b4db6779d0961cda', '角色管理', 'roleManage', 'partials/role/role-list.html', FALSE,
   'icon-hand-left-white', '', NULL, '93b901a0dfcd46a292431d625a3f8cef', 2);
INSERT INTO t_res_menu_link (id, name, en_name, url, is_home, icon_cls, target, visible, parent_id, seq_no) VALUES
  ('e34d2e8940a046ab9685c3b8384a15cb', '菜单管理', 'menuManage', 'partials/menu/menuTreeList.html', FALSE,
   'icon-hand-left-white', '', NULL, 'd5871e0121cb4aeb849f2317907404d3', 2);
INSERT INTO t_res_menu_link (id, name, en_name, url, is_home, icon_cls, target, visible, parent_id, seq_no) VALUES
  ('ef4ff1a9e1da467ebb2474fca1bb23fa', 'Druid监控', 'druidMonitor', 'partials/system/druid.html', FALSE,
   'icon-user-white', NULL, NULL, 'f1ea27fb771c4947b1343d5346a1b3af', NULL);


INSERT INTO t_res_user (id, username, password, real_name, gender, mobile_phone, email, salt, locked, register_date)
VALUES
  ('5acca77bb3e5413ca91c2dc4baab1403', 'test1', '96e79218965eb72c92a549dd5a330112', '测试用户', NULL, NULL, '', NULL, FALSE,
   '2016-06-13 13:09:43');
INSERT INTO t_res_user (id, username, password, real_name, gender, mobile_phone, email, salt, locked, register_date)
VALUES ('73cd362b39dd402ea05a74357fbfe5d2', 'test', 'e10adc3949ba59abbe56e057f20f883e', '测试', 0, '123456', '123@qq.com',
        '123', FALSE, '2016-05-09 09:30:05');
INSERT INTO t_res_user (id, username, password, real_name, gender, mobile_phone, email, salt, locked, register_date)
VALUES
  ('8cfd1b69d5ce4799b5731026c9578ff9', 'admin', '96e79218965eb72c92a549dd5a330112', '管理员', NULL, NULL, '', NULL, FALSE,
   '2016-06-13 11:21:17');


INSERT INTO t_res_role (id, name, code, remark, role_type)
VALUES ('3f1424d50a46455082db1015c4b05c0d', '管理员', 'admin', '', NULL);
INSERT INTO t_res_role (id, name, code, remark, role_type)
VALUES ('df22ff5c261947a6abd4c261faf2a848', '超级管理员', 'superAdmin', '', NULL);
INSERT INTO t_res_role (id, name, code, remark, role_type)
VALUES ('fbb14672ddb34c4c906c05e53c751e90', '测试角色', 'test', '', NULL);

INSERT INTO t_res_permission (id, url, name, per_type, parent_id, visible, remark)
VALUES ('0b5feaaa599849ddaf654fa01facb461', 'user:add', '新建用户', NULL, 'e2853611e54448898789a78977a0fe7f', TRUE, NULL);
INSERT INTO t_res_permission (id, url, name, per_type, parent_id, visible, remark)
VALUES ('0c32d3bdf2174428921d4987f70f3bf5', 'menu:add', '新建菜单', NULL, 'a74e722fcf0642408a96330dfe66d9ac', TRUE, NULL);
INSERT INTO t_res_permission (id, url, name, per_type, parent_id, visible, remark) VALUES
  ('55180f2f7bc24e559cbb0d428b8ab848', 'menu:delete', '删除菜单', NULL, 'a74e722fcf0642408a96330dfe66d9ac', TRUE, NULL);
INSERT INTO t_res_permission (id, url, name, per_type, parent_id, visible, remark) VALUES
  ('8028abc2950c4db68353dc5424982e6d', 'user:delete', '删除用户', NULL, 'e2853611e54448898789a78977a0fe7f', TRUE, NULL);
INSERT INTO t_res_permission (id, url, name, per_type, parent_id, visible, remark)
VALUES ('a74e722fcf0642408a96330dfe66d9ac', NULL, '菜单权限', NULL, NULL, TRUE, NULL);
INSERT INTO t_res_permission (id, url, name, per_type, parent_id, visible, remark)
VALUES ('c19a0c85bc204740802946e1bca7b913', NULL, '角色权限', NULL, NULL, TRUE, NULL);
INSERT INTO t_res_permission (id, url, name, per_type, parent_id, visible, remark)
VALUES ('d1825b0ae4584e249cb2a55707b21e7e', 'role:add', '新建角色', NULL, 'c19a0c85bc204740802946e1bca7b913', TRUE, NULL);
INSERT INTO t_res_permission (id, url, name, per_type, parent_id, visible, remark)
VALUES ('e2853611e54448898789a78977a0fe7f', '', '用户权限', NULL, NULL, TRUE, '');
INSERT INTO t_res_permission (id, url, name, per_type, parent_id, visible, remark) VALUES
  ('faea736064f94041bc59d744ebc037a7', 'menu:update', '修改菜单', NULL, 'a74e722fcf0642408a96330dfe66d9ac', TRUE, NULL);


INSERT INTO t_res_user_role (user_id, role_id)
VALUES ('8cfd1b69d5ce4799b5731026c9578ff9', '3f1424d50a46455082db1015c4b05c0d');
INSERT INTO t_res_user_role (user_id, role_id)
VALUES ('5acca77bb3e5413ca91c2dc4baab1403', 'fbb14672ddb34c4c906c05e53c751e90');


INSERT INTO t_res_role_permission (role_id, permission_id)
VALUES ('3f1424d50a46455082db1015c4b05c0d', '0b5feaaa599849ddaf654fa01facb461');
INSERT INTO t_res_role_permission (role_id, permission_id)
VALUES ('3f1424d50a46455082db1015c4b05c0d', '0c32d3bdf2174428921d4987f70f3bf5');
INSERT INTO t_res_role_permission (role_id, permission_id)
VALUES ('3f1424d50a46455082db1015c4b05c0d', '55180f2f7bc24e559cbb0d428b8ab848');
INSERT INTO t_res_role_permission (role_id, permission_id)
VALUES ('3f1424d50a46455082db1015c4b05c0d', '8028abc2950c4db68353dc5424982e6d');
INSERT INTO t_res_role_permission (role_id, permission_id)
VALUES ('3f1424d50a46455082db1015c4b05c0d', 'a74e722fcf0642408a96330dfe66d9ac');
INSERT INTO t_res_role_permission (role_id, permission_id)
VALUES ('3f1424d50a46455082db1015c4b05c0d', 'e2853611e54448898789a78977a0fe7f');
INSERT INTO t_res_role_permission (role_id, permission_id)
VALUES ('3f1424d50a46455082db1015c4b05c0d', 'faea736064f94041bc59d744ebc037a7');
INSERT INTO t_res_role_permission (role_id, permission_id)
VALUES ('df22ff5c261947a6abd4c261faf2a848', '0b5feaaa599849ddaf654fa01facb461');
INSERT INTO t_res_role_permission (role_id, permission_id)
VALUES ('df22ff5c261947a6abd4c261faf2a848', '0c32d3bdf2174428921d4987f70f3bf5');
INSERT INTO t_res_role_permission (role_id, permission_id)
VALUES ('df22ff5c261947a6abd4c261faf2a848', '55180f2f7bc24e559cbb0d428b8ab848');
INSERT INTO t_res_role_permission (role_id, permission_id)
VALUES ('df22ff5c261947a6abd4c261faf2a848', '8028abc2950c4db68353dc5424982e6d');
INSERT INTO t_res_role_permission (role_id, permission_id)
VALUES ('df22ff5c261947a6abd4c261faf2a848', 'a74e722fcf0642408a96330dfe66d9ac');
INSERT INTO t_res_role_permission (role_id, permission_id)
VALUES ('df22ff5c261947a6abd4c261faf2a848', 'e2853611e54448898789a78977a0fe7f');
INSERT INTO t_res_role_permission (role_id, permission_id)
VALUES ('df22ff5c261947a6abd4c261faf2a848', 'faea736064f94041bc59d744ebc037a7');
