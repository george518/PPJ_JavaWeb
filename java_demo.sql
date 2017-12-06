/*
 Navicat MySQL Data Transfer

 Source Server         : localhost
 Source Server Version : 50712
 Source Host           : localhost
 Source Database       : java_demo

 Target Server Version : 50712
 File Encoding         : utf-8

 Date: 12/06/2017 10:49:24 AM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `pp_uc_admin`
-- ----------------------------
DROP TABLE IF EXISTS `pp_uc_admin`;
CREATE TABLE `pp_uc_admin` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `login_name` varchar(20) NOT NULL DEFAULT '' COMMENT '用户名',
  `real_name` varchar(32) NOT NULL DEFAULT '0' COMMENT '真实姓名',
  `password` char(32) NOT NULL DEFAULT '' COMMENT '密码',
  `role_ids` varchar(255) NOT NULL DEFAULT '0' COMMENT '角色id字符串，如：2,3,4',
  `phone` varchar(20) NOT NULL DEFAULT '0' COMMENT '手机号码',
  `email` varchar(50) NOT NULL DEFAULT '' COMMENT '邮箱',
  `salt` char(10) NOT NULL DEFAULT '' COMMENT '密码盐',
  `last_login` int(11) NOT NULL DEFAULT '0' COMMENT '最后登录时间',
  `last_ip` char(15) NOT NULL DEFAULT '' COMMENT '最后登录IP',
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '状态，1-正常 0禁用',
  `create_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '创建者ID',
  `update_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '修改者ID',
  `create_time` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '创建时间',
  `update_time` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_user_name` (`login_name`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COMMENT='管理员表';

-- ----------------------------
--  Records of `pp_uc_admin`
-- ----------------------------
BEGIN;
INSERT INTO `pp_uc_admin` VALUES ('1', 'admin', '超级管理员', 'JRemBKoOTa+dzR/O49t9AA==', '0', '13888888889', 'haodaquan2008@163.com', 'lwdk', '1510894139', '', '1', '0', '1', '0', '1511857891'), ('2', 'george518', 'georgeHao', 'hA+h5fBJyGHH7SRSk6/PjQ==', '2', '13811558899', '12@11.com', 'kmcB', '1506125048', '127.0.0.1', '1', '0', '2', '0', '1511857963'), ('3', 'haodaquan', '郝大全', 'seiGDisqxtF/PWpGIroTUA==', '8', '13811559988', 'hao@123.com', 'bihn', '1505960085', '127.0.0.1', '1', '1', '1', '1505919245', '1511858051'), ('4', 'ceshizhanghao', '测试姓名', 'fa3fb5825c2e64bc764f29245dd1ec7a', '2', '13988009988', '232@124.com', 'i8Nf', '0', '', '0', '1', '0', '1506047337', '1506128397'), ('5', 'dfds', '测试dd22', 'EZQcDE5pdyvFGq+Xw8YR6Q==', '2,4,1', '13811551087', 'ddd@123.cc', 'cpva', '0', '0', '0', '1', '2', '1511662055', '1511839367');
COMMIT;

-- ----------------------------
--  Table structure for `pp_uc_auth`
-- ----------------------------
DROP TABLE IF EXISTS `pp_uc_auth`;
CREATE TABLE `pp_uc_auth` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `pid` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '上级ID，0为顶级',
  `auth_name` varchar(64) NOT NULL DEFAULT '0' COMMENT '权限名称',
  `auth_url` varchar(255) NOT NULL DEFAULT '0' COMMENT 'URL地址',
  `sort` int(1) unsigned NOT NULL DEFAULT '999' COMMENT '排序，越小越前',
  `icon` varchar(255) NOT NULL,
  `is_show` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否显示，0-隐藏，1-显示',
  `create_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '创建者ID',
  `update_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '修改者ID',
  `status` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '状态，1-正常，0-删除',
  `create_time` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '创建时间',
  `update_time` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8mb4 COMMENT='权限因子';

-- ----------------------------
--  Records of `pp_uc_auth`
-- ----------------------------
BEGIN;
INSERT INTO `pp_uc_auth` VALUES ('1', '0', '所有权限', '/', '1', '', '0', '1', '1', '1', '1505620970', '1505620970'), ('2', '1', '权限管理', '/', '999', 'fa-id-card', '1', '0', '1', '1', '0', '1505622360'), ('3', '2', '管理员', '/admin/index', '1', 'fa-user-o', '1', '1', '1', '1', '1505621186', '1505621186'), ('4', '2', '角色管理', '/role/index', '2', 'fa-user-circle-o', '1', '0', '1', '1', '0', '1511846413'), ('5', '3', 'ajax-保存管理员', '/admin/saveAdmin', '1', '', '0', '0', '1', '1', '0', '1511846703'), ('6', '3', 'ajax-获取全部管理员', '/admin/getList', '2', '', '0', '0', '1', '1', '0', '1511846748'), ('7', '3', 'ajax-修改状态', '/admin/changeAdminStatus', '3', '', '0', '1', '1', '1', '0', '1511846773'), ('8', '4', 'ajax-保存', '/role/saveRole', '1', '', '1', '0', '1', '1', '0', '1511846345'), ('9', '4', 'ajax-获取全部角色', '/role/getList', '2', '', '0', '1', '1', '1', '0', '1511858893'), ('10', '4', 'ajax-修改状态', '/role/changeRoleStatus', '3', '', '0', '1', '1', '1', '0', '1511846386'), ('11', '2', '权限节点', '/auth/index', '3', 'fa-list', '1', '1', '1', '1', '0', '1511850596'), ('12', '11', 'ajax-获取单个权限', '/auth/getNode', '1', '', '0', '1', '1', '1', '0', '1511846446'), ('13', '11', 'ajax-保存', '/auth/saveNode', '2', '', '0', '1', '1', '1', '0', '1511846464'), ('14', '11', 'ajax-删除', '/auth/deleteNode', '3', '', '0', '1', '1', '1', '0', '1511846477'), ('15', '1', '个人中心', 'profile/edit', '1001', 'fa-user-circle-o', '1', '0', '1', '1', '0', '1506001114'), ('16', '1', 'API管理', '/', '1', 'fa-cubes', '1', '0', '0', '0', '0', '1506125698'), ('17', '16', 'API接口', '/api/index', '1', 'fa-link', '1', '1', '1', '0', '0', '1511850616'), ('19', '16', 'API监控', '/apimonitor/list', '3', 'fa-bar-chart', '1', '0', '1', '0', '0', '1507700851'), ('20', '1', '基础设置', '/', '2', 'fa-cogs', '1', '1', '1', '1', '1505622601', '1505622601'), ('21', '20', '分组设置', '/group/index', '1', 'fa-object-ungroup', '1', '1', '1', '1', '0', '1511851942'), ('22', '20', '环境设置', '/env/index', '2', 'fa-tree', '1', '1', '1', '1', '0', '1511852339'), ('23', '20', '状态码设置', '/code/index', '3', 'fa-code', '1', '1', '1', '1', '0', '1511852361'), ('24', '15', '资料修改', '/user/userEdit', '1', 'fa-edit', '1', '0', '1', '1', '0', '1511856898'), ('25', '21', '新增', '/group/add', '1', 'n', '1', '0', '0', '1', '1506229739', '1506229739'), ('26', '21', '修改', '/group/edit', '2', 'fa', '0', '0', '0', '1', '1506237920', '1506237920'), ('27', '21', '删除', '/group/delete', '3', 'fa', '0', '0', '1', '1', '0', '1511851970'), ('28', '22', '新增', '/env/add', '1', 'fa', '0', '0', '0', '1', '1506316506', '1506316506'), ('29', '22', '修改', '/env/edit', '2', 'fa', '0', '0', '0', '1', '1506316532', '1506316532'), ('30', '22', '删除', '/env/delete', '3', 'fa', '0', '0', '1', '1', '0', '1511852012'), ('31', '23', '新增', '/code/add', '1', 'fa', '0', '0', '0', '1', '1506327812', '1506327812'), ('32', '23', '修改', '/code/edit', '2', 'fa', '0', '0', '0', '1', '1506327831', '1506327831'), ('33', '23', '删除', '/code/delete', '3', 'fa', '0', '0', '1', '1', '0', '1511852062'), ('34', '17', '新增资源', '/api/add', '2', 'fa-link', '0', '0', '1', '0', '0', '1511850630'), ('35', '17', '修改资源', '/api/edit', '2', 'fa-link', '1', '0', '1', '0', '0', '1507436042'), ('36', '17', '删除资源', '/api/delete', '3', 'fa-link', '1', '0', '1', '0', '0', '1511850648'), ('37', '17', '保存资源', '/api/save', '4', '', '0', '1', '1', '0', '0', '1511850683'), ('38', '17', '修改接口', '/api/editapi', '5', '', '0', '0', '1', '0', '0', '1507705049'), ('39', '34', 'dd22233444', 'dd22233444', '3', '2221', '0', '1', '1', '0', '0', '1511535987'), ('40', '34', 'sddsf', '/', '88', 'd', '1', '1', '1', '0', '0', '1511537223'), ('41', '39', 'ddfg', '/dd', '1', 'dd', '1', '1', '1', '0', '1511537178', '0'), ('42', '41', 'sdfs', '/dd', '2', 'dd', '0', '1', '1', '0', '1511537207', '0'), ('43', '34', 'eew', 'eew', '1', '', '1', '1', '1', '0', '0', '1511537807'), ('44', '17', 'sdfsd222sdssd', 'sdfsd222', '3', 'dd', '0', '1', '1', '0', '0', '1511592189'), ('45', '11', 'ajax-获取全部权限', '/auth/getNodes', '1', '', '0', '1', '1', '1', '0', '1511846455'), ('46', '4', 'page-新增角色页面', '/role/roleAdd', '4', '', '0', '1', '1', '1', '0', '1511846556'), ('47', '4', 'page-编辑角色页面', '/role/roleEdit', '5', '', '0', '1', '1', '1', '1511846599', '0'), ('48', '3', 'page-新增管理员', '/admin/adminAdd', '3', '', '0', '1', '1', '1', '1511846833', '0'), ('49', '3', 'page-编辑管理员', '/admin/adminEdit', '5', '', '0', '1', '1', '1', '1511846894', '0'), ('50', '21', '保存', '/group/save', '4', '', '0', '1', '1', '1', '1511851996', '0'), ('51', '22', '保存', '/env/save', '4', '', '0', '1', '1', '1', '1511852042', '0'), ('52', '23', '保存', '/code/save', '4', '', '0', '1', '1', '1', '1511852090', '0'), ('53', '24', '保存', '/user/save', '1', '', '0', '1', '1', '1', '1511857350', '0');
COMMIT;

-- ----------------------------
--  Table structure for `pp_uc_role`
-- ----------------------------
DROP TABLE IF EXISTS `pp_uc_role`;
CREATE TABLE `pp_uc_role` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `role_name` varchar(32) NOT NULL DEFAULT '0' COMMENT '角色名称',
  `detail` varchar(255) NOT NULL DEFAULT '0' COMMENT '备注',
  `auth_nodes` varchar(2000) NOT NULL DEFAULT '' COMMENT '权限节点字符串，英文逗号隔开',
  `create_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '创建者ID',
  `update_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '修改这ID',
  `status` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '状态1-正常，0-删除',
  `create_time` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '添加时间',
  `update_time` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
--  Records of `pp_uc_role`
-- ----------------------------
BEGIN;
INSERT INTO `pp_uc_role` VALUES ('1', '系统管理员', '权限修改', '1,2,3,5,6,7,48,49,4,8,9,10,46,47,11,12,45,13,14,15,24,', '0', '1', '1', '1505874156', '1511852513'), ('2', '环境-no保存', '不含保存权限', '1,20,22,29,30,23,31,32,33,52,15,24,53,', '0', '1', '1', '1506124114', '1511858014'), ('3', 'jjj', 'kkk', '', '1', '1', '0', '1511623332', '1511623332'), ('4', 'sdfds', 'sdfsdf', '1,16,17,34,35,36,37,38', '1', '1', '0', '1511623463', '1511628867'), ('5', 'dsfsd', 'sdfds', '', '1', '1', '0', '1511623572', '1511623572'), ('6', 'dsfsd', 'sdfds', '', '1', '1', '0', '1511623591', '1511623591'), ('7', 'sdfds', 'sdfds', '', '1', '1', '0', '1511623774', '1511623774'), ('8', '分组-不含删除', '不含删除', '1,20,21,25,26,50,22,28,29,51,', '1', '1', '1', '1511624451', '1511858030');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
