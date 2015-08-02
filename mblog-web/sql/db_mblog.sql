/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50530
Source Host           : localhost:3306
Source Database       : db_mblog

Target Server Type    : MYSQL
Target Server Version : 50530
File Encoding         : 65001

Date: 2015-07-21 20:54:21
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for mto_attachs
-- ----------------------------
DROP TABLE IF EXISTS `mto_attachs`;
CREATE TABLE `mto_attachs` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `height` int(11) NOT NULL,
  `original` varchar(255) DEFAULT NULL,
  `preview` varchar(255) DEFAULT NULL,
  `status` int(11) NOT NULL,
  `store` int(11) NOT NULL,
  `to_id` bigint(20) DEFAULT NULL,
  `width` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `index_to_id` (`to_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mto_attachs
-- ----------------------------

-- ----------------------------
-- Table structure for mto_comments
-- ----------------------------
DROP TABLE IF EXISTS `mto_comments`;
CREATE TABLE `mto_comments` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `pid` bigint(20) NOT NULL,
  `status` int(11) NOT NULL,
  `to_id` bigint(20) DEFAULT NULL,
  `author_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `index_to_id` (`to_id`) USING BTREE,
  KEY `index_pid` (`pid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mto_comments
-- ----------------------------

-- ----------------------------
-- Table structure for mto_logs
-- ----------------------------
DROP TABLE IF EXISTS `mto_logs`;
CREATE TABLE `mto_logs` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` date DEFAULT NULL,
  `ip` varchar(255) DEFAULT NULL,
  `target_id` bigint(20) DEFAULT NULL,
  `type` int(11) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mto_logs
-- ----------------------------

-- ----------------------------
-- Table structure for mto_posts
-- ----------------------------
DROP TABLE IF EXISTS `mto_posts`;
CREATE TABLE `mto_posts` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `comments` int(11) NOT NULL,
  `content` longtext,
  `created` datetime DEFAULT NULL,
  `editor` varchar(255) DEFAULT NULL,
  `favors` int(11) NOT NULL,
  `featured` int(11) NOT NULL,
  `group_` int(11) DEFAULT NULL,
  `last_image_id` bigint(20) DEFAULT NULL,
  `markdown` longtext,
  `status` int(11) NOT NULL,
  `summary` varchar(255) DEFAULT NULL,
  `tags` varchar(255) DEFAULT NULL,
  `title` varchar(64) DEFAULT NULL,
  `views` int(11) NOT NULL,
  `author_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `index_author_id` (`author_id`) USING BTREE,
  KEY `index_group` (`group_`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mto_posts
-- ----------------------------
INSERT INTO `mto_posts` VALUES ('1', '0', '<p>从现在开始, 尽情的享用分享的乐趣吧！</p>', '2015-07-21 20:50:48', 'ueditor', '0', '0', '1', '0', null, '0', '从现在开始, 尽情的享用分享的乐趣吧！', '', '欢迎使用Mtons博客', '0', '1');

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
INSERT INTO `mto_users` VALUES ('1', null, null, null, null, '3TGCQF25BLHU9R7IQUITN0FCC5', '0', 'admin', 'admin', '/store/ava/1/0/0/100.jpg', '2015-07-21 20:42:26', '0', '1');

-- ----------------------------
-- Table structure for mto_users_extend
-- ----------------------------
DROP TABLE IF EXISTS `mto_users_extend`;
CREATE TABLE `mto_users_extend` (
  `id` bigint(20) NOT NULL,
  `comments` int(11) NOT NULL,
  `follows` int(11) NOT NULL,
  `married` int(11) NOT NULL,
  `posts` int(11) NOT NULL,
  `praises` int(11) NOT NULL,
  `signature` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mto_users_extend
-- ----------------------------

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
  `locked` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of mto_tags
-- ----------------------------
INSERT INTO `mto_tags` VALUES ('1', '清新', '1', '0', '0', '0', '0');
INSERT INTO `mto_tags` VALUES ('2', '电影', '1', '0', '0', '0', '0');
INSERT INTO `mto_tags` VALUES ('3', '宠物', '1', '0', '0', '0', '0');
INSERT INTO `mto_tags` VALUES ('4', '语录', '1', '0', '0', '0', '0');
INSERT INTO `mto_tags` VALUES ('5', '美女', '1', '0', '0', '0', '0');
INSERT INTO `mto_tags` VALUES ('6', '旅行', '1', '0', '0', '0', '0');
INSERT INTO `mto_tags` VALUES ('7', '北京', '1', '0', '0', '0', '0');
INSERT INTO `mto_tags` VALUES ('8', '艺术', '1', '0', '0', '0', '0');
INSERT INTO `mto_tags` VALUES ('9', '美图', '1', '0', '0', '0', '0');
INSERT INTO `mto_tags` VALUES ('10', '模特', '1', '0', '0', '0', '0');
INSERT INTO `mto_tags` VALUES ('11', '心情', '1', '0', '0', '0', '0');
INSERT INTO `mto_tags` VALUES ('12', '摄影', '1', '0', '0', '0', '0');

-- ----------------------------
-- Table structure for mto_config
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
-- Records of mto_config
-- ----------------------------
INSERT INTO `mto_config` VALUES ('1', 'site_name', '0', 'Mtons');
INSERT INTO `mto_config` VALUES ('2', 'site_version', '0', '1.0.1');
INSERT INTO `mto_config` VALUES ('3', 'site_welcomes', '0', 'Mtons, 轻松分享你的兴趣');
INSERT INTO `mto_config` VALUES ('4', 'site_domain', '0', 'http://mtons.com');
INSERT INTO `mto_config` VALUES ('5', 'site_keywords', '0', 'mtons,博客,社区,摄影,旅游,艺术,娱乐');
INSERT INTO `mto_config` VALUES ('6', 'site_description', '0', 'Mtons, 轻松分享你的兴趣. 便捷的文字、图片发布,扁平化的响应式设计,美观、大气,是您记录生活的最佳选择');
INSERT INTO `mto_config` VALUES ('7', 'setting_editor', '1', 'ueditor');

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
