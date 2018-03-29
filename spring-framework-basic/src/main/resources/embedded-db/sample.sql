SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
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


DROP TABLE IF EXISTS `tag_project_tag_group`;
CREATE TABLE `tag_project_tag_group` (
    `id` INT(11) NOT NULL,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8;


DROP TABLE IF EXISTS `tag_project_tag_user_backup`;
CREATE TABLE `tag_project_tag_user_backup` (
    `id`      INT(11) NOT NULL AUTO_INCREMENT,
    `tag_id`  VARCHAR(255)     DEFAULT NULL,
    `user_id` VARCHAR(255)     DEFAULT NULL,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8;


DROP TABLE IF EXISTS `tag_project_user_tag_checked`;
CREATE TABLE `tag_project_user_tag_checked` (
    `id`          VARCHAR(40) NOT NULL DEFAULT '',
    `name`        VARCHAR(60)          DEFAULT NULL,
    `is_delete`   TINYINT(1)           DEFAULT NULL,
    `modify_time` DATETIME             DEFAULT NULL,
    `create_time` DATETIME             DEFAULT NULL,
    `is_checked`  TINYINT(1)           DEFAULT NULL,
    `is_group`    TINYINT(1)           DEFAULT '0',
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8;

-- ----------------------------
-- Table structure for user_group
-- ----------------------------
DROP TABLE IF EXISTS `user_group`;
CREATE TABLE `user_group` (
    `id`             INT(11) NOT NULL AUTO_INCREMENT,
    `group_id`       INT(11)          DEFAULT NULL,
    `application_id` VARCHAR(11)      DEFAULT NULL,
    `is_delete`      TINYINT(1)       DEFAULT '0',
    `create_time`    DATETIME         DEFAULT NULL,
    `modify_time`    DATETIME         DEFAULT NULL,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8;

-- ----------------------------
-- Table structure for user_group_event_group
-- ----------------------------
DROP TABLE IF EXISTS `user_group_event_group`;
CREATE TABLE `user_group_event_group` (
    `id`            INT(11) NOT NULL AUTO_INCREMENT,
    `user_group_id` INT(11)          DEFAULT NULL,
    `event_id`      INT(11)          DEFAULT NULL,
    `create_time`   DATETIME         DEFAULT NULL,
    `modify_time`   DATETIME         DEFAULT NULL,
    `is_delete`     TINYINT(1)       DEFAULT NULL,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8;

-- ----------------------------
-- Table structure for user_group_event_status
-- ----------------------------
DROP TABLE IF EXISTS `user_group_event_status`;
CREATE TABLE `user_group_event_status` (
    `id`          INT(11) NOT NULL AUTO_INCREMENT,
    `event_id`    INT(11)          DEFAULT NULL,
    `code`        INT(11)          DEFAULT NULL,
    `name`        VARCHAR(255)     DEFAULT NULL,
    `modify_time` DATETIME         DEFAULT NULL,
    `create_time` DATETIME         DEFAULT NULL,
    `is_delete`   TINYINT(1)       DEFAULT '0',
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8;

-- ----------------------------
-- Table structure for user_group_events
-- ----------------------------
DROP TABLE IF EXISTS `user_group_events`;
CREATE TABLE `user_group_events` (
    `id`          INT(11) NOT NULL AUTO_INCREMENT,
    `name`        VARCHAR(255)     DEFAULT NULL,
    `is_delete`   TINYINT(1)       DEFAULT '0',
    `modify_time` DATETIME         DEFAULT NULL,
    `create_time` DATETIME         DEFAULT NULL,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8;

-- ----------------------------
-- Table structure for user_group_groups
-- ----------------------------
DROP TABLE IF EXISTS `user_group_groups`;
CREATE TABLE `user_group_groups` (
    `id`          INT(11) NOT NULL AUTO_INCREMENT,
    `name`        VARCHAR(255)     DEFAULT NULL,
    `is_delete`   TINYINT(1)       DEFAULT '0',
    `modify_time` DATETIME         DEFAULT NULL,
    `create_time` DATETIME         DEFAULT NULL,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8;


INSERT INTO `user_group` VALUES ('7', '12', '1', '0', '2017-08-28 17:29:51', '2017-08-28 17:29:51');
INSERT INTO `user_group` VALUES ('8', '13', '1', '0', '2017-08-28 17:37:45', '2017-08-28 17:37:45');