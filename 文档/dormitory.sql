/*
 Navicat Premium Data Transfer

 Source Server         : MySQL
 Source Server Type    : MySQL
 Source Server Version : 80029
 Source Host           : localhost:3306
 Source Schema         : dormitory

 Target Server Type    : MySQL
 Target Server Version : 80029
 File Encoding         : 65001

 Date: 27/07/2022 14:02:28
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for absent
-- ----------------------------
DROP TABLE IF EXISTS `absent`;
CREATE TABLE `absent`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `building_id` int NULL DEFAULT NULL COMMENT '楼栋ID',
  `dormitory_id` int NULL DEFAULT NULL COMMENT '宿舍',
  `student_id` int NULL DEFAULT NULL COMMENT '学生ID',
  `dormitory_admin_id` int NULL DEFAULT NULL COMMENT '宿管ID',
  `create_date` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '日期',
  `reason` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '缺寝原因',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '学生缺寝表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of absent
-- ----------------------------
INSERT INTO `absent` VALUES (13, 1, 2, 5, 16, '2022-04-16', '请假');
INSERT INTO `absent` VALUES (14, 1, 1, 1, 1, '2022-04-26', '请假');
INSERT INTO `absent` VALUES (15, 2, 5, 63, 1, '2022-04-26', '请假');

-- ----------------------------
-- Table structure for building
-- ----------------------------
DROP TABLE IF EXISTS `building`;
CREATE TABLE `building`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '楼栋名称',
  `introduction` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '楼栋介绍',
  `admin_id` int NULL DEFAULT NULL COMMENT '宿管ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '楼栋管理表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of building
-- ----------------------------
INSERT INTO `building` VALUES (1, '1号楼', '计算机学院宿舍楼', 16);
INSERT INTO `building` VALUES (2, '2号楼', '计算机学院宿舍楼', 16);
INSERT INTO `building` VALUES (16, '5号楼', '电信学院宿舍楼', 16);

-- ----------------------------
-- Table structure for dormitory
-- ----------------------------
DROP TABLE IF EXISTS `dormitory`;
CREATE TABLE `dormitory`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `building_id` int NULL DEFAULT NULL COMMENT '楼栋ID',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '宿舍号',
  `type` int NULL DEFAULT NULL COMMENT '房间类型',
  `available` int NULL DEFAULT NULL COMMENT '空余床位',
  `telephone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 39 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '宿舍表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dormitory
-- ----------------------------
INSERT INTO `dormitory` VALUES (1, 1, '101', 4, 0, '88230001');
INSERT INTO `dormitory` VALUES (2, 1, '102', 4, 0, '88230002');
INSERT INTO `dormitory` VALUES (3, 1, '211', 4, 0, '88230007');
INSERT INTO `dormitory` VALUES (4, 2, '212', 6, 1, '88230008');
INSERT INTO `dormitory` VALUES (5, 2, '321', 8, 6, '88230013');
INSERT INTO `dormitory` VALUES (6, 2, '322', 10, 10, '88230016');
INSERT INTO `dormitory` VALUES (36, 1, '666', 6, 6, '88136666');

-- ----------------------------
-- Table structure for dormitory_admin
-- ----------------------------
DROP TABLE IF EXISTS `dormitory_admin`;
CREATE TABLE `dormitory_admin`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '账号',
  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `gender` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别',
  `telephone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话号码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '宿舍管理员表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dormitory_admin
-- ----------------------------
INSERT INTO `dormitory_admin` VALUES (1, 'test', '123456', '宋玉', '女', '13312345678');
INSERT INTO `dormitory_admin` VALUES (2, 'ww', '123123', '王力', '男', '16666666666');
INSERT INTO `dormitory_admin` VALUES (16, 'com', '123123', '孙佰松', '男', '18988579427');

-- ----------------------------
-- Table structure for moveout
-- ----------------------------
DROP TABLE IF EXISTS `moveout`;
CREATE TABLE `moveout`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `student_id` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学号',
  `dormitory_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '宿舍ID',
  `reason` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '迁出原因',
  `create_date` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '迁出日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '学生迁出表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of moveout
-- ----------------------------
INSERT INTO `moveout` VALUES (15, '63', '6', '毕业', '2022-04-17');
INSERT INTO `moveout` VALUES (17, '18', '4', '毕业', '2022-04-27');

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `number` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学号',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `gender` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别',
  `dormitory_id` int NULL DEFAULT NULL COMMENT '宿舍ID',
  `state` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态',
  `create_date` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 67 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '学生信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES (1, '001', '王伟', '男', 1, '入住', '2022-04-14');
INSERT INTO `student` VALUES (2, '002', '曹海', '男', 1, '入住', '2022-04-14');
INSERT INTO `student` VALUES (3, '003', '李力', '男', 1, '入住', '2022-04-14');
INSERT INTO `student` VALUES (4, '004', '赵昭', '男', 1, '入住', '2022-04-14');
INSERT INTO `student` VALUES (5, '005', '王维利', '男', 2, '入住', '2022-04-14');
INSERT INTO `student` VALUES (6, '006', '李双', '女', 2, '入住', '2022-04-14');
INSERT INTO `student` VALUES (7, '007', '李小峰', '男', 2, '入住', '2022-04-14');
INSERT INTO `student` VALUES (8, '008', '孙奇', '男', 2, '入住', '2022-04-14');
INSERT INTO `student` VALUES (9, '009', '李立', '女', 3, '入住', '2022-04-14');
INSERT INTO `student` VALUES (10, '010', '周华发', '男', 3, '入住', '2022-04-14');
INSERT INTO `student` VALUES (11, '011', '赵正义', '男', 3, '入住', '2022-04-14');
INSERT INTO `student` VALUES (12, '012', '李明', '男', 3, '入住', '2022-04-14');
INSERT INTO `student` VALUES (13, '013', '许鹏飞', '男', 4, '入住', '2022-04-14');
INSERT INTO `student` VALUES (14, '014', '朱海', '男', 4, '入住', '2022-04-14');
INSERT INTO `student` VALUES (15, '015', '苏苏苏', '男', 4, '入住', '2022-04-14');
INSERT INTO `student` VALUES (16, '016', '李雪', '女', 4, '入住', '2022-04-14');
INSERT INTO `student` VALUES (17, '017', '李爽', '女', 4, '入住', '2022-04-14');
INSERT INTO `student` VALUES (18, '018', '王纯', '女', 4, '迁出', '2022-04-14');
INSERT INTO `student` VALUES (63, '019', '小明', '男', 5, '迁出', '2022-04-17');

-- ----------------------------
-- Table structure for system_admin
-- ----------------------------
DROP TABLE IF EXISTS `system_admin`;
CREATE TABLE `system_admin`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `telephone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统管理员表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of system_admin
-- ----------------------------
INSERT INTO `system_admin` VALUES (1, 'admin', '123456', '管理员1', '88132001');

SET FOREIGN_KEY_CHECKS = 1;
