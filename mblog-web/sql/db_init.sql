/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50530
Source Host           : localhost:3306
Source Database       : db_mblog

Target Server Type    : MYSQL
Target Server Version : 50530
File Encoding         : 65001

Date: 2015-05-09 14:43:26
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for mto_users
-- ----------------------------
DROP TABLE IF EXISTS `mto_users`;
CREATE TABLE `mto_users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime DEFAULT NULL,
  `email` varchar(128) DEFAULT NULL,
  `last_login` datetime DEFAULT NULL,
  `mobile` varchar(11) DEFAULT NULL,
  `password` varchar(32) DEFAULT NULL,
  `status` int(11) NOT NULL,
  `username` varchar(64) DEFAULT NULL,
  `name` varchar(64) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT '/dist/images/ava/default.png',
  `updated` datetime DEFAULT NULL,
  `gender` int(11) NOT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mto_users
-- ----------------------------
INSERT INTO `mto_users` VALUES ('1', null, null, null, null, '3TGCQF25BLHU9R7IQUITN0FCC5', '0', 'admin', 'admin', '/dist/images/ava/default.png', '2015-05-09 10:39:45', '0', '1');

-- ----------------------------
-- Table structure for mto_configs
-- ----------------------------
DROP TABLE IF EXISTS `mto_config`;
CREATE TABLE `mto_config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `key_` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `value` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_99vo6d7ci4wlxruo3gd0q2jq8` (`key_`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mto_configs
-- ----------------------------
INSERT INTO `mto_config` VALUES ('1', 'site_name', '0', 'Mtons');
INSERT INTO `mto_config` VALUES ('2', 'site_version', '0', '1.0.1');
INSERT INTO `mto_config` VALUES ('3', 'site_welcomes', '0', 'Mtons, 轻松分享你的兴趣');
INSERT INTO `mto_config` VALUES ('4', 'site_domain', '0', 'http://mtons.com');
INSERT INTO `mto_config` VALUES ('5', 'site_keywords', '0', 'mtons,博客,社区,摄影,旅游,艺术,娱乐');
INSERT INTO `mto_config` VALUES ('6', 'site_description', '0', 'Mtons, 轻松分享你的兴趣. 便捷的文字、图片发布,扁平化的响应式设计,美观、大气,是您记录生活的最佳选择');
INSERT INTO `mto_config` VALUES ('7', 'setting_editor', '1', 'ueditor');

-- ----------------------------
-- Table structure for mto_tags
-- ----------------------------
DROP TABLE IF EXISTS `mto_tags`;
CREATE TABLE `mto_tags` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `featured` int(11) NOT NULL,
  `hots` int(11) NOT NULL,
  `last_post_id` bigint(20) DEFAULT NULL,
  `posts` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of mto_tags
-- ----------------------------
INSERT INTO `mto_tags` VALUES ('1', '清新', '1', '0', '0', '0');
INSERT INTO `mto_tags` VALUES ('2', '电影', '1', '0', '0', '0');
INSERT INTO `mto_tags` VALUES ('3', '宠物', '1', '0', '0', '0');
INSERT INTO `mto_tags` VALUES ('4', '语录', '1', '0', '0', '0');
INSERT INTO `mto_tags` VALUES ('5', '美女', '1', '0', '0', '0');
INSERT INTO `mto_tags` VALUES ('6', '旅行', '1', '0', '0', '0');
INSERT INTO `mto_tags` VALUES ('7', '北京', '1', '0', '0', '0');
INSERT INTO `mto_tags` VALUES ('8', '艺术', '1', '0', '0', '0');
INSERT INTO `mto_tags` VALUES ('9', '美图', '1', '0', '0', '0');
INSERT INTO `mto_tags` VALUES ('10', '模特', '1', '0', '0', '0');
INSERT INTO `mto_tags` VALUES ('11', '心情', '1', '0', '0', '0');
INSERT INTO `mto_tags` VALUES ('12', '摄影', '1', '0', '0', '0');

-- ----------------------------
-- Table structure for mto_group
-- ----------------------------
DROP TABLE IF EXISTS `mto_group`;
CREATE TABLE `mto_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `key_` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `alias` varchar(255) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `template` varchar(16) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mto_group
-- ----------------------------
INSERT INTO `mto_group` VALUES ('1', 'blog', '文章', '写文章', 'fa fa-pencil-square', 'blog');
INSERT INTO `mto_group` VALUES ('2', 'image', '图片', '发图片', 'fa fa-camera', 'image');
INSERT INTO `mto_group` VALUES ('3', 'video', '视频', '分享视频', 'fa fa-toggle-right', 'video');
INSERT INTO `mto_group` VALUES ('4', 'ask', '问答', '提问题', 'fa fa-question-circle', 'ask');

-- ----------------------------
-- Table structure for mto_menu
-- ----------------------------
DROP TABLE IF EXISTS `mto_menu`;
CREATE TABLE `mto_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(18) DEFAULT NULL,
  `target` varchar(18) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `weight` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mto_menu
-- ----------------------------
INSERT INTO `mto_menu` VALUES ('1', '文章', '_self', 'g/blog', '0');
INSERT INTO `mto_menu` VALUES ('2', '视频', '_self', 'g/video', '0');
INSERT INTO `mto_menu` VALUES ('3', '问答', '_self', 'g/ask', '0');
INSERT INTO `mto_menu` VALUES ('4', '发现', '_self', 'tags', '0');
INSERT INTO `mto_menu` VALUES ('5', '走廊', '_self', 'gallery?g=2', '0');

-- ----------------------------
-- Table structure for mto_auth_menu
-- ----------------------------
DROP TABLE IF EXISTS `mto_auth_menu`;
CREATE TABLE `mto_auth_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `parent_ids` varchar(255) DEFAULT NULL,
  `permission` varchar(255) DEFAULT NULL,
  `sort` int(11) NOT NULL,
  `url` varchar(255) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_fynq2bfwabynqmnauw69b7ulc` (`parent_id`),
  CONSTRAINT `FK_fynq2bfwabynqmnauw69b7ulc` FOREIGN KEY (`parent_id`) REFERENCES `mto_auth_menu` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mto_auth_menu
-- ----------------------------
INSERT INTO `mto_auth_menu` VALUES ('1', '后台管理', '', 'admin', '1', '/admin', null);

-- ----------------------------
-- Table structure for mto_role
-- ----------------------------
DROP TABLE IF EXISTS `mto_role`;
CREATE TABLE `mto_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mto_role
-- ----------------------------
INSERT INTO `mto_role` VALUES ('1', '管理员');
INSERT INTO `mto_role` VALUES ('2', '普通用户');

-- ----------------------------
-- Table structure for mto_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `mto_role_menu`;
CREATE TABLE `mto_role_menu` (
  `role_id` bigint(20) NOT NULL,
  `menu_id` bigint(20) NOT NULL,
  KEY `FK_td8ih8aorlkpyop3gemfqxmbt` (`menu_id`),
  KEY `FK_5o5vaxfyg0d1qa0142dnkruiv` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mto_role_menu
-- ----------------------------
INSERT INTO `mto_role_menu` VALUES ('1', '1');

-- ----------------------------
-- Table structure for mto_user_role
-- ----------------------------
DROP TABLE IF EXISTS `mto_user_role`;
CREATE TABLE `mto_user_role` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  KEY `FK_fhtla2vc199mv0ru2r2kvakha` (`role_id`),
  KEY `FK_b4m7ef0uvkr4efrscf8r1ehy2` (`user_id`),
  CONSTRAINT `FK_b4m7ef0uvkr4efrscf8r1ehy2` FOREIGN KEY (`user_id`) REFERENCES `mto_users` (`id`),
  CONSTRAINT `FK_fhtla2vc199mv0ru2r2kvakha` FOREIGN KEY (`role_id`) REFERENCES `mto_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mto_user_role
-- ----------------------------
INSERT INTO `mto_user_role` VALUES ('1', '1');