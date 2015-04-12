/*
Navicat MySQL Data Transfer

Source Server         : mtons
Source Server Version : 50621
Source Host           : localhost:3306
Source Database       : db_mblog

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2015-04-12 19:58:54
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_tags
-- ----------------------------
DROP TABLE IF EXISTS `tb_tags`;
CREATE TABLE `tb_tags` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `featured` int(11) NOT NULL,
  `hots` int(11) NOT NULL,
  `last_post_id` bigint(20) DEFAULT NULL,
  `posts` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of tb_tags
-- ----------------------------
INSERT INTO `tb_tags` VALUES ('1', '清新', '1', '125', '39', '2');
INSERT INTO `tb_tags` VALUES ('2', '电影', '1', '204', '178', '4');
INSERT INTO `tb_tags` VALUES ('3', '宠物', '1', '154', '36', '1');
INSERT INTO `tb_tags` VALUES ('4', '语录', '1', '151', '82', '3');
INSERT INTO `tb_tags` VALUES ('5', '美女', '1', '109', '40', '2');
INSERT INTO `tb_tags` VALUES ('6', '旅行', '1', '119', '41', '1');
INSERT INTO `tb_tags` VALUES ('7', '北京', '1', '104', '72', '2');
INSERT INTO `tb_tags` VALUES ('8', '艺术', '1', '110', '81', '4');
INSERT INTO `tb_tags` VALUES ('9', '美图', '1', '90', '76', '2');
INSERT INTO `tb_tags` VALUES ('10', '模特', '1', '96', '79', '2');
INSERT INTO `tb_tags` VALUES ('11', '心情', '1', '89', '181', '5');
INSERT INTO `tb_tags` VALUES ('12', '摄影', '1', '96', '75', '2');
INSERT INTO `tb_tags` VALUES ('13', '插画', '0', '0', '81', '1');
INSERT INTO `tb_tags` VALUES ('14', '旅游', '0', '0', '86', '1');
INSERT INTO `tb_tags` VALUES ('15', '时尚', '0', '0', '90', '2');
INSERT INTO `tb_tags` VALUES ('16', 'bong2', '0', '1', '92', '1');
INSERT INTO `tb_tags` VALUES ('17', '生活', '0', '0', '94', '1');
INSERT INTO `tb_tags` VALUES ('18', '源码', '0', '0', '95', '1');
INSERT INTO `tb_tags` VALUES ('19', '测试', '0', '0', '176', '3');
INSERT INTO `tb_tags` VALUES ('20', '哇哇哇', '0', '0', '101', '1');
INSERT INTO `tb_tags` VALUES ('21', 'mysql', '0', '0', '104', '1');
INSERT INTO `tb_tags` VALUES ('22', 'test', '0', '1', '182', '3');
INSERT INTO `tb_tags` VALUES ('23', '呵呵', '0', '0', '109', '1');
INSERT INTO `tb_tags` VALUES ('24', '手工', '0', '0', '110', '1');
INSERT INTO `tb_tags` VALUES ('25', '设计', '0', '0', '110', '1');
INSERT INTO `tb_tags` VALUES ('26', '发饰', '0', '0', '110', '1');
INSERT INTO `tb_tags` VALUES ('27', '头饰', '0', '0', '110', '1');
INSERT INTO `tb_tags` VALUES ('28', '森系', '0', '0', '110', '1');
INSERT INTO `tb_tags` VALUES ('29', '手作', '0', '0', '110', '1');
INSERT INTO `tb_tags` VALUES ('30', '日系', '0', '0', '110', '1');
INSERT INTO `tb_tags` VALUES ('31', '配饰', '0', '0', '110', '1');
INSERT INTO `tb_tags` VALUES ('32', '发夹', '0', '0', '110', '1');
INSERT INTO `tb_tags` VALUES ('33', 'openfire', '0', '0', '113', '1');
INSERT INTO `tb_tags` VALUES ('34', '源码分析', '0', '0', '113', '1');
INSERT INTO `tb_tags` VALUES ('35', '图片', '0', '1', '114', '1');
INSERT INTO `tb_tags` VALUES ('36', '日志', '0', '0', '114', '1');
INSERT INTO `tb_tags` VALUES ('37', '132231', '0', '0', '115', '1');
INSERT INTO `tb_tags` VALUES ('38', '开源', '0', '0', '116', '1');
INSERT INTO `tb_tags` VALUES ('39', 'java', '0', '0', '116', '1');
INSERT INTO `tb_tags` VALUES ('40', 'bolg', '0', '0', '116', '1');
INSERT INTO `tb_tags` VALUES ('41', 'blog', '0', '1', '174', '2');
INSERT INTO `tb_tags` VALUES ('42', 'jordan', '0', '0', '165', '2');
INSERT INTO `tb_tags` VALUES ('43', 'da', '0', '0', '164', '1');
INSERT INTO `tb_tags` VALUES ('44', 'sdddfsd', '0', '0', '166', '1');
INSERT INTO `tb_tags` VALUES ('45', 's', '0', '0', '167', '1');
INSERT INTO `tb_tags` VALUES ('46', '要', '0', '0', '169', '1');
INSERT INTO `tb_tags` VALUES ('47', '顶替', '0', '0', '170', '1');
INSERT INTO `tb_tags` VALUES ('48', '11', '0', '0', '171', '1');
INSERT INTO `tb_tags` VALUES ('49', 'first', '0', '0', '172', '1');
INSERT INTO `tb_tags` VALUES ('50', 'pic', '0', '0', '173', '1');
INSERT INTO `tb_tags` VALUES ('51', 'aa', '0', '0', '175', '1');
INSERT INTO `tb_tags` VALUES ('52', 'asdfasdf', '0', '0', '179', '1');
INSERT INTO `tb_tags` VALUES ('53', 'ss', '0', '0', '183', '1');
INSERT INTO `tb_tags` VALUES ('54', 'hh', '0', '0', '183', '1');
