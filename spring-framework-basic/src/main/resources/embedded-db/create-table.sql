CREATE TABLE IF NOT EXISTS `foo` (
    `id`      INT AUTO_INCREMENT,
    `name`    VARCHAR(32) NOT NULL
    COMMENT '姓名',
    `barname` VARCHAR(32) COMMENT 'bar的姓名',
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8;
CREATE TABLE `t_user` (
    `id`          VARCHAR(64) NOT NULL,
    `org_id`      VARCHAR(32)  DEFAULT NULL
    COMMENT '企业ID',
    `user_name`   VARCHAR(60)  DEFAULT NULL
    COMMENT '用户姓名',
    `user_tag`    VARCHAR(64)  DEFAULT NULL
    COMMENT '姓名标签',
    `user_sign`   VARCHAR(64)  DEFAULT NULL
    COMMENT '人员唯一标志（学号，学籍号，工号。。。） 现在暂为 学生：学号，老师：工号',
    `photo_url`   VARCHAR(200) DEFAULT NULL
    COMMENT '头像地址 ../user_id.jpg',
    `born_date`   VARCHAR(50)  DEFAULT NULL
    COMMENT '出生日期',
    `sex`         VARCHAR(32)  DEFAULT NULL
    COMMENT '姓别  1:男; 2:女',
    `is_delete`   VARCHAR(1)   DEFAULT NULL
    COMMENT '是否删除 1:删除；0：正常',
    `create_user` VARCHAR(64)  DEFAULT NULL,
    `gmt_create`  DATETIME     DEFAULT NULL,
    `modify_user` VARCHAR(64)  DEFAULT NULL,
    `gmt_modify`  DATETIME     DEFAULT NULL,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8;