/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50522
Source Host           : 127.0.0.1:3306
Source Database       : db_mblog

Target Server Type    : MYSQL
Target Server Version : 50522
File Encoding         : 65001

Date: 2015-09-18 15:04:45
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
<<<<<<< HEAD
  `username` varchar(64) DEFAULT NULL,
  `name` varchar(64) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT '/assets/images/ava/default.png',
  `updated` datetime DEFAULT NULL,
  `gender` int(11) NOT NULL,
  `role_id` int(11) DEFAULT NULL,
  `source` int(11) NOT NULL,
  `active_email` int(11) DEFAULT NULL,
  `comments` int(11) NOT NULL,
  `fans` int(11) NOT NULL,
  `favors` int(11) NOT NULL,
  `follows` int(11) NOT NULL,
  `posts` int(11) NOT NULL,
=======
  `store` int(11) NOT NULL,
  `to_id` bigint(20) DEFAULT NULL,
  `width` int(11) NOT NULL,
>>>>>>> 4122929d0ce3bd7af3c1bf6429bbe598ea4e6813
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mto_attachs
-- ----------------------------

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
  `icon` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_fynq2bfwabynqmnauw69b7ulc` (`parent_id`),
  CONSTRAINT `FK_fynq2bfwabynqmnauw69b7ulc` FOREIGN KEY (`parent_id`) REFERENCES `mto_auth_menu` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mto_auth_menu
-- ----------------------------
INSERT INTO `mto_auth_menu` VALUES ('1', '根目录', '', '', '1', '/', null, null);
INSERT INTO `mto_auth_menu` VALUES ('2', '后台管理', null, 'admin', '1', 'admin', '1', null);
INSERT INTO `mto_auth_menu` VALUES ('3', '我的主页', null, '', '1', '', '1', null);
INSERT INTO `mto_auth_menu` VALUES ('4', '文章管理', null, 'admin:posts:view', '2', 'admin/posts/list', '2', 'fa fa-edit icon-xlarge');
INSERT INTO `mto_auth_menu` VALUES ('5', '文章修改', null, 'admin:posts:edit', '1', null, '4', null);
INSERT INTO `mto_auth_menu` VALUES ('6', '用户管理', null, 'admin:users:view', '3', 'admin/users/list', '2', 'fa fa-user icon-xlarge');
INSERT INTO `mto_auth_menu` VALUES ('7', '禁用用户', null, 'admin:users:edit', '1', null, '6', null);
INSERT INTO `mto_auth_menu` VALUES ('8', '修改密码', null, 'admin:users:edit', '1', null, '6', null);
INSERT INTO `mto_auth_menu` VALUES ('9', '评论管理', null, 'admin:comments:view', '4', 'admin/comments/list', '2', 'fa fa-comments-o icon-xlarge');
INSERT INTO `mto_auth_menu` VALUES ('10', '删除评论', null, 'admin:comments:edit', '1', null, '9', null);
INSERT INTO `mto_auth_menu` VALUES ('11', '标签管理', null, 'admin:tags:view', '5', 'admin/tags/list', '2', 'fa fa-tags icon-xlarge');
INSERT INTO `mto_auth_menu` VALUES ('12', '删除标签', null, 'admin:tags:edit', '1', null, '11', null);
INSERT INTO `mto_auth_menu` VALUES ('13', '修改标签', null, 'admin:tags:edit', '2', null, '11', null);
INSERT INTO `mto_auth_menu` VALUES ('14', '推荐标签', null, 'admin:tags:edit', '3', null, '11', null);
INSERT INTO `mto_auth_menu` VALUES ('15', '系统配置', null, 'admin:config:view', '6', 'admin/config/', '2', 'fa fa-cog icon-xlarge');
INSERT INTO `mto_auth_menu` VALUES ('16', '修改配置', null, 'admin:config:edit', '1', null, '15', null);
INSERT INTO `mto_auth_menu` VALUES ('17', 'dashboard', null, 'admin', '1', 'admin', '2', 'fa fa-dashboard icon-xlarge');
INSERT INTO `mto_auth_menu` VALUES ('18', '角色管理', null, 'admin:roles:view', '7', 'admin/roles/list', '2', 'fa fa-dashboard icon-xlarge');
INSERT INTO `mto_auth_menu` VALUES ('19', '菜单管理', null, 'admin:authMenus:view', '8', 'admin/authMenus/list', '2', 'fa fa-edit icon-xlarge');

-- ----------------------------
-- Table structure for mto_comments
-- ----------------------------
DROP TABLE IF EXISTS `mto_comments`;
CREATE TABLE `mto_comments` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `author_id` bigint(20) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `pid` bigint(20) NOT NULL,
  `status` int(11) NOT NULL,
  `to_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mto_comments
-- ----------------------------
<<<<<<< HEAD
INSERT INTO `mto_users` VALUES ('1', '2015-08-06 17:52:41', 'admin@mtons.com', '2015-08-19 21:06:55', null, '3TGCQF25BLHU9R7IQUITN0FCC5', '0', 'admin', '小豆丁', '/assets/images/ava/default.png', '2015-07-26 11:08:36', '0', '1', '0', '1', '0', '0', '0', '0', '0');
=======
>>>>>>> 4122929d0ce3bd7af3c1bf6429bbe598ea4e6813

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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mto_config
-- ----------------------------
INSERT INTO `mto_config` VALUES ('1', 'site_name', '0', 'Mtons');
INSERT INTO `mto_config` VALUES ('2', 'site_welcomes', '0', 'Mtons, 轻松分享你的兴趣');
INSERT INTO `mto_config` VALUES ('3', 'site_domain', '0', 'http://mtons.com');
INSERT INTO `mto_config` VALUES ('4', 'site_keywords', '0', 'mtons,博客,社区,摄影,旅游,艺术,娱乐');
INSERT INTO `mto_config` VALUES ('5', 'site_description', '0', 'Mtons, 轻松分享你的兴趣. 便捷的文字、图片发布,扁平化的响应式设计,美观、大气,是您记录生活的最佳选择');
INSERT INTO `mto_config` VALUES ('6', 'site_editor', '1', 'ueditor');

-- ----------------------------
-- Table structure for mto_favors
-- ----------------------------
DROP TABLE IF EXISTS `mto_favors`;
CREATE TABLE `mto_favors` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime DEFAULT NULL,
  `own_id` bigint(20) DEFAULT NULL,
  `post_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mto_favors
-- ----------------------------

-- ----------------------------
-- Table structure for mto_feeds
-- ----------------------------
DROP TABLE IF EXISTS `mto_feeds`;
CREATE TABLE `mto_feeds` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `author_id` bigint(20) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `own_id` bigint(20) DEFAULT NULL,
  `post_id` bigint(20) DEFAULT NULL,
  `type` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mto_feeds
-- ----------------------------

-- ----------------------------
-- Table structure for mto_follows
-- ----------------------------
DROP TABLE IF EXISTS `mto_follows`;
CREATE TABLE `mto_follows` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime DEFAULT NULL,
  `follow_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_t38um6ivtsbk34xph98levx4x` (`follow_id`),
  KEY `FK_3grh3868ek909r524m0kphmux` (`user_id`),
  CONSTRAINT `FK_3grh3868ek909r524m0kphmux` FOREIGN KEY (`user_id`) REFERENCES `mto_users` (`id`),
  CONSTRAINT `FK_t38um6ivtsbk34xph98levx4x` FOREIGN KEY (`follow_id`) REFERENCES `mto_users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mto_follows
-- ----------------------------

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
  `status` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mto_group
-- ----------------------------
INSERT INTO `mto_group` VALUES ('1', 'blog', '文章', '写文章', 'fa fa-pencil-square', 'blog', '0');
INSERT INTO `mto_group` VALUES ('2', 'image', '图片', '发图片', 'fa fa-camera', 'image', '0');
INSERT INTO `mto_group` VALUES ('3', 'video', '视频', '搬视频', 'fa fa-toggle-right', 'video', '0');
INSERT INTO `mto_group` VALUES ('4', 'ask', '问答', '提问题', 'fa fa-question-circle', 'ask', '0');

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
-- Table structure for mto_menu
-- ----------------------------
DROP TABLE IF EXISTS `mto_menu`;
CREATE TABLE `mto_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(18) DEFAULT NULL,
  `target` varchar(18) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `weight` int(11) NOT NULL,
  `status` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mto_menu
-- ----------------------------
INSERT INTO `mto_menu` VALUES ('1', '文章', '_self', 'g/blog', '0', '0');
INSERT INTO `mto_menu` VALUES ('2', '视频', '_self', 'g/video', '0', '0');
INSERT INTO `mto_menu` VALUES ('3', '问答', '_self', 'g/ask', '0', '0');
INSERT INTO `mto_menu` VALUES ('4', '发现', '_self', 'tags', '0', '0');
INSERT INTO `mto_menu` VALUES ('5', '走廊', '_self', 'gallery?g=2', '0', '0');

-- ----------------------------
-- Table structure for mto_posts
-- ----------------------------
DROP TABLE IF EXISTS `mto_posts`;
CREATE TABLE `mto_posts` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `author_id` bigint(20) DEFAULT NULL,
  `comments` int(11) NOT NULL,
  `content` longtext,
  `created` datetime DEFAULT NULL,
  `editor` varchar(255) DEFAULT NULL,
  `favors` int(11) NOT NULL,
  `featured` int(11) NOT NULL,
  `group_` int(11) DEFAULT NULL,
  `images` int(11) NOT NULL,
  `last_image_id` bigint(20) DEFAULT NULL,
  `markdown` longtext,
  `privacy` int(11) NOT NULL,
  `status` int(11) NOT NULL,
  `summary` varchar(255) DEFAULT NULL,
  `tags` varchar(255) DEFAULT NULL,
  `title` varchar(64) DEFAULT NULL,
  `views` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mto_posts
-- ----------------------------

-- ----------------------------
-- Table structure for mto_role
-- ----------------------------
DROP TABLE IF EXISTS `mto_role`;
CREATE TABLE `mto_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

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
INSERT INTO `mto_role_menu` VALUES ('1', '4');
INSERT INTO `mto_role_menu` VALUES ('1', '6');
INSERT INTO `mto_role_menu` VALUES ('1', '2');
INSERT INTO `mto_role_menu` VALUES ('1', '1');
INSERT INTO `mto_role_menu` VALUES ('1', '3');
INSERT INTO `mto_role_menu` VALUES ('1', '5');
INSERT INTO `mto_role_menu` VALUES ('1', '7');
INSERT INTO `mto_role_menu` VALUES ('1', '8');
INSERT INTO `mto_role_menu` VALUES ('1', '9');
INSERT INTO `mto_role_menu` VALUES ('1', '10');
INSERT INTO `mto_role_menu` VALUES ('1', '11');
INSERT INTO `mto_role_menu` VALUES ('1', '12');
INSERT INTO `mto_role_menu` VALUES ('1', '13');
INSERT INTO `mto_role_menu` VALUES ('1', '14');
INSERT INTO `mto_role_menu` VALUES ('1', '15');
INSERT INTO `mto_role_menu` VALUES ('1', '16');
INSERT INTO `mto_role_menu` VALUES ('1', '17');
INSERT INTO `mto_role_menu` VALUES ('1', '18');
INSERT INTO `mto_role_menu` VALUES ('1', '19');

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
INSERT INTO `mto_tags` VALUES ('5', '美女', '1', '1', '3', '2', '0');
INSERT INTO `mto_tags` VALUES ('6', '旅行', '1', '0', '0', '0', '0');
INSERT INTO `mto_tags` VALUES ('7', '北京', '1', '0', '0', '0', '0');
INSERT INTO `mto_tags` VALUES ('8', '艺术', '1', '0', '0', '0', '0');
INSERT INTO `mto_tags` VALUES ('9', '美图', '1', '0', '0', '0', '0');
INSERT INTO `mto_tags` VALUES ('10', '模特', '1', '0', '0', '0', '0');
INSERT INTO `mto_tags` VALUES ('11', '心情', '1', '0', '0', '0', '0');
INSERT INTO `mto_tags` VALUES ('12', '摄影', '1', '0', '0', '0', '0');

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
  `source` int(11) NOT NULL,
  `active_email` int(11) DEFAULT NULL,
  `comments` int(11) NOT NULL,
  `fans` int(11) NOT NULL,
  `favors` int(11) NOT NULL,
  `follows` int(11) NOT NULL,
  `posts` int(11) NOT NULL,
  `signature` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mto_users
-- ----------------------------
INSERT INTO `mto_users` VALUES ('1', '2015-08-06 17:52:41', 'admin@mtons.com', '2015-09-18 14:38:41', null, '3TGCQF25BLHU9R7IQUITN0FCC5', '0', 'admin', '小豆丁', '/dist/images/ava/default.png', '2015-07-26 11:08:36', '0', '1', '0', '1', '0', '0', '0', '0', '0', null);

-- ----------------------------
-- Table structure for mto_users_open_oauth
-- ----------------------------
DROP TABLE IF EXISTS `mto_users_open_oauth`;
CREATE TABLE `mto_users_open_oauth` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `access_token` varchar(255) DEFAULT NULL,
  `expire_in` varchar(255) DEFAULT NULL,
  `oauth_code` varchar(255) DEFAULT NULL,
  `oauth_type` int(11) DEFAULT NULL,
  `oauth_user_id` varchar(255) DEFAULT NULL,
  `refresh_token` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mto_users_open_oauth
-- ----------------------------

-- ----------------------------
-- Table structure for mto_user_role
-- ----------------------------
DROP TABLE IF EXISTS `mto_user_role`;
CREATE TABLE `mto_user_role` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  UNIQUE KEY `UK_fhtla2vc199mv0ru2r2kvakha` (`role_id`),
  KEY `FK_fhtla2vc199mv0ru2r2kvakha` (`role_id`),
  KEY `FK_b4m7ef0uvkr4efrscf8r1ehy2` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mto_user_role
-- ----------------------------
INSERT INTO `mto_user_role` VALUES ('1', '1');
INSERT INTO `mto_user_role` VALUES ('1', '2');

-- ----------------------------
-- Table structure for mto_verify
-- ----------------------------
DROP TABLE IF EXISTS `mto_verify`;
CREATE TABLE `mto_verify` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(60) NOT NULL,
  `created` datetime NOT NULL,
  `expired` datetime NOT NULL,
  `status` int(11) DEFAULT NULL,
  `target` varchar(96) DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_m7p0b526c4xlgjn787t22om2g` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mto_verify
-- ----------------------------
INSERT INTO `mto_verify` VALUES ('1', '9234319298', '2015-09-08 14:34:19', '2015-09-08 15:04:19', '0', 'i@yuanye.li', null, '1', '2');
INSERT INTO `mto_verify` VALUES ('2', '3488696085', '2015-09-10 16:02:53', '2015-09-10 16:32:53', '0', 'aaa.qq@qq.com', null, '1', '15');
INSERT INTO `mto_verify` VALUES ('3', '9354005478', '2015-09-10 16:03:17', '2015-09-10 16:33:17', '0', 'qqq@qq.com', null, '1', '16');
