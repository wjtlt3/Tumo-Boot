/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50729
 Source Host           : localhost:3306
 Source Schema         : tumo_boot

 Target Server Type    : MySQL
 Target Server Version : 50729
 File Encoding         : 65001

 Date: 20/02/2021 15:27:52
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details` (
                                        `client_id` varchar(256) NOT NULL,
                                        `resource_ids` varchar(256) DEFAULT NULL,
                                        `client_secret` varchar(256) DEFAULT NULL,
                                        `scope` varchar(256) DEFAULT NULL,
                                        `authorized_grant_types` varchar(256) DEFAULT NULL,
                                        `web_server_redirect_uri` varchar(256) DEFAULT NULL,
                                        `authorities` varchar(256) DEFAULT NULL,
                                        `access_token_validity` int(11) DEFAULT NULL,
                                        `refresh_token_validity` int(11) DEFAULT NULL,
                                        `additional_information` varchar(4096) DEFAULT NULL,
                                        `autoapprove` varchar(256) DEFAULT NULL,
                                        PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oauth_client_details
-- ----------------------------
BEGIN;
INSERT INTO `oauth_client_details` VALUES ('client', NULL, '$2a$10$22emI3a6/w3a4ZZIa0.pY.dvLsyx4GH.he37wULtW8nJ.TeiGUpC6', 'app', 'password,refresh_token', 'http://tycoding.cn', NULL, NULL, NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
                            `id` bigint(20) NOT NULL COMMENT '??????ID',
                            `parent_id` bigint(20) NOT NULL COMMENT '????????????ID',
                            `name` varchar(20) NOT NULL COMMENT '????????????',
                            `des` varchar(100) DEFAULT NULL COMMENT '??????',
                            `create_time` datetime DEFAULT NULL COMMENT '????????????',
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='?????????';

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
BEGIN;
INSERT INTO `sys_dept` VALUES (1362597682681577473, 0, '????????????', '??????', NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
                           `id` bigint(20) NOT NULL COMMENT '??????',
                           `type` int(10) DEFAULT NULL COMMENT '????????????',
                           `username` varchar(20) DEFAULT NULL COMMENT '????????????',
                           `operation` varchar(20) DEFAULT NULL COMMENT '????????????',
                           `url` varchar(255) DEFAULT NULL COMMENT '??????URL',
                           `time` bigint(20) DEFAULT NULL COMMENT '??????(??????)',
                           `method` varchar(100) DEFAULT NULL COMMENT '????????????',
                           `params` varchar(255) DEFAULT NULL COMMENT '????????????',
                           `ip` varchar(20) DEFAULT NULL COMMENT 'IP??????',
                           `user_agent` varchar(255) DEFAULT NULL COMMENT '????????????',
                           `create_time` datetime DEFAULT NULL COMMENT '????????????',
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='?????????';

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
                            `id` bigint(20) NOT NULL COMMENT '??????',
                            `name` varchar(20) NOT NULL COMMENT '????????????',
                            `parent_id` bigint(20) DEFAULT NULL COMMENT '??????ID',
                            `path` varchar(100) DEFAULT NULL COMMENT '????????????',
                            `perms` text COMMENT '????????????',
                            `type` varchar(20) DEFAULT NULL COMMENT '????????????',
                            `icon` varchar(30) DEFAULT NULL COMMENT '????????????',
                            `component` varchar(255) DEFAULT NULL COMMENT '????????????',
                            `hidden` tinyint(1) DEFAULT NULL COMMENT '????????????',
                            `frame` tinyint(1) DEFAULT NULL COMMENT '???????????????',
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='?????????';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_menu` VALUES (1362304631325192191, '????????????', 0, '/upms', NULL, 'menu', 'safety-certificate', NULL, 0, 0);
INSERT INTO `sys_menu` VALUES (1362304631325192192, '????????????', 1362304631325192191, 'menu', 'menu:list', 'menu', 'cluster', '/modules/upms/menu/index', 0, 0);
INSERT INTO `sys_menu` VALUES (1362311197558341634, '????????????', 1362304631325192191, 'user', 'user:list', 'menu', 'team', '/modules/upms/user/index', 0, 0);
INSERT INTO `sys_menu` VALUES (1362314451879096321, '????????????', 1362304631325192191, 'role', 'role:list', 'menu', 'solution', '/modules/upms/role/index', 0, 0);
INSERT INTO `sys_menu` VALUES (1362316746228355073, '????????????', 1362304631325192191, 'dept', 'dept:list', 'menu', 'audit', '/modules/upms/dept/index', 0, 0);
INSERT INTO `sys_menu` VALUES (1362600462804586498, '????????????', 0, '/system', 'system', 'menu', 'setting', NULL, 0, 0);
INSERT INTO `sys_menu` VALUES (1362601021230026753, 'API??????', 1362600462804586498, 'http://127.0.0.1:8090/doc.html', 'doc:list', 'menu', 'file-search', NULL, 0, 1);
INSERT INTO `sys_menu` VALUES (1362604427059400705, '????????????', 1362600462804586498, 'log/api', 'log:list', 'menu', 'api', '/modules/system/log/index', 0, 0);
INSERT INTO `sys_menu` VALUES (1362663179380875265, 'Test Page', 0, '/test', 'test', 'menu', 'alert', '/modules/test/index', 0, 0);
COMMIT;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
                            `id` bigint(20) NOT NULL COMMENT '??????',
                            `parent_id` bigint(20) DEFAULT NULL COMMENT '????????????',
                            `name` varchar(20) NOT NULL COMMENT '????????????',
                            `alias` varchar(20) DEFAULT NULL COMMENT '????????????',
                            `des` varchar(100) DEFAULT NULL COMMENT '??????',
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='?????????';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_role` VALUES (1362304631325192103, 0, '????????????????????????', 'administrator', '?????????????????????????????????????????????');
INSERT INTO `sys_role` VALUES (1362597571041787906, 0, '???????????????', 'user-admin', '???????????????????????????');
COMMIT;

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
                                 `role_id` bigint(20) NOT NULL COMMENT '??????ID',
                                 `menu_id` bigint(20) NOT NULL COMMENT '??????/??????ID',
                                 PRIMARY KEY (`role_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='?????????????????????';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
                            `id` bigint(20) NOT NULL COMMENT '??????ID',
                            `username` varchar(50) NOT NULL COMMENT '?????????',
                            `password` varchar(100) NOT NULL COMMENT '??????',
                            `real_name` varchar(255) DEFAULT NULL COMMENT '????????????',
                            `sex` varchar(10) DEFAULT NULL COMMENT '??????',
                            `phone` varchar(20) DEFAULT NULL COMMENT '??????',
                            `email` varchar(100) DEFAULT NULL COMMENT '??????',
                            `dept_id` bigint(20) NOT NULL COMMENT '??????ID',
                            `avatar` varchar(100) DEFAULT NULL COMMENT '??????',
                            `status` tinyint(1) DEFAULT '0' COMMENT '?????? 0?????? 1??????',
                            `create_time` datetime NOT NULL COMMENT '????????????',
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='?????????';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` VALUES (1362304631325102103, 'Tumo-Boot', '$2a$10$ZjqngBAeTeUEvd3oe1DrL.I0uKO7X.6IZVWAb3Zc4TmqmID.zOWGe', '???????????????', '???', '19809587839', 'tycoding@sina.com', 3, NULL, 1, '2019-01-01 00:00:00');
INSERT INTO `sys_user` VALUES (1362598312234024962, 'tycoding', '123456', '??????', '???', '19823879128', 'tycoding@sina.com', 1362597682681577473, NULL, 1, '2021-02-19 11:02:08');
COMMIT;

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
                                 `user_id` bigint(20) NOT NULL COMMENT '??????ID',
                                 `role_id` bigint(20) NOT NULL COMMENT '??????ID',
                                 PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='?????????????????????';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_role` VALUES (1362304631325102103, 1362304631325192103);
INSERT INTO `sys_user_role` VALUES (1362598312234024962, 1362597571041787906);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
