/*
 Navicat Premium Data Transfer

 Source Server         : Software-HW-123.249.21.100
 Source Server Type    : MySQL
 Source Server Version : 80400
 Source Host           : 123.249.21.100:3306
 Source Schema         : fm

 Target Server Type    : MySQL
 Target Server Version : 80400
 File Encoding         : 65001

 Date: 02/11/2024 21:15:25
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `deleted` bit(1) NULL DEFAULT b'0',
  PRIMARY KEY (`id`, `username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'zhang san', '123456', NULL, b'0');
INSERT INTO `user` VALUES (2, 'zhangsan', '123', NULL, b'0');
INSERT INTO `user` VALUES (3, 'lisi', '123', NULL, b'0');
INSERT INTO `user` VALUES (4, 'zhang', 'guo', NULL, b'0');

SET FOREIGN_KEY_CHECKS = 1;
