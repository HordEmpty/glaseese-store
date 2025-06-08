/*
 Navicat MySQL Data Transfer

 Source Server         : hjj_sm
 Source Server Type    : MySQL
 Source Server Version : 80032
 Source Host           : localhost:3306
 Source Schema         : glasses_store

 Target Server Type    : MySQL
 Target Server Version : 80032
 File Encoding         : 65001

 Date: 12/07/2024 09:31:57
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for brand
-- ----------------------------
DROP TABLE IF EXISTS `brand`;
CREATE TABLE `brand`  (
  `brand_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '品牌名称',
  `brand_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '品牌编号',
  `brand_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '品牌类别',
  PRIMARY KEY (`brand_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of brand
-- ----------------------------
INSERT INTO `brand` VALUES ('a1', 'a1', 'a1');

-- ----------------------------
-- Table structure for custom
-- ----------------------------
DROP TABLE IF EXISTS `custom`;
CREATE TABLE `custom`  (
  `custom_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '客户姓名',
  `custom_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '客户编号',
  `custom_ph` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '客户手机号码',
  PRIMARY KEY (`custom_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of custom
-- ----------------------------
INSERT INTO `custom` VALUES ('仙贝', '114', '514');
INSERT INTO `custom` VALUES ('丁真', '15', '137666');

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order`  (
  `order_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '订单编号',
  `order_type` enum('未完成','已完成') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '订单状态',
  `to_custom_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '客户编号',
  `product_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '产品名称',
  `order_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '订单价格',
  `factory` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '工厂',
  PRIMARY KEY (`order_id`) USING BTREE,
  INDEX `to_custom_id`(`to_custom_id`) USING BTREE,
  INDEX `product_name`(`product_name`) USING BTREE,
  CONSTRAINT `order_ibfk_1` FOREIGN KEY (`to_custom_id`) REFERENCES `custom` (`custom_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `order_ibfk_2` FOREIGN KEY (`product_name`) REFERENCES `product` (`product_name`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of order
-- ----------------------------
INSERT INTO `order` VALUES ('2024071067306917', '已完成', '15', 'b1', 1919810.00, '悦刻');
INSERT INTO `order` VALUES ('2024071074913405\r\n2024071074913405\r\n2024071074913405', '未完成', '15', 'b1', 114514.00, '悦刻');

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `order_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '订单编号',
  `order_type` enum('未完成','已完成') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '订单状态',
  `to_custom_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '客户编号',
  `product_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '产品名称',
  `order_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '订单价格',
  `factory` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '工厂',
  PRIMARY KEY (`order_id`) USING BTREE,
  INDEX `to_custom_id`(`to_custom_id`) USING BTREE,
  INDEX `product_name`(`product_name`) USING BTREE,
  CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`to_custom_id`) REFERENCES `custom` (`custom_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `orders_ibfk_2` FOREIGN KEY (`product_name`) REFERENCES `product` (`product_name`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES ('2024071074913405', '未完成', '15', '悦刻五代', 1919810.00, '悦刻');
INSERT INTO `orders` VALUES ('2024071133749538', '已完成', '114', '悦刻五代', 11114444.00, '下北泽');
INSERT INTO `orders` VALUES ('2024071180473342', '已完成', '15', 'b1', 1919810.00, ' 悦刻');
INSERT INTO `orders` VALUES ('2024071192051111', '未完成', '15', 'b1', 1453.00, '理塘');
INSERT INTO `orders` VALUES ('2024071194693301', '已完成', '15', '悦刻五代', 66.00, '精致世界');

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`  (
  `to_brand_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '该产品归属的品牌编号',
  `product_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '产品编号',
  `product_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '产品名称',
  `product_price` decimal(10, 2) NOT NULL COMMENT '产品单价',
  `product_num` int NULL DEFAULT NULL COMMENT '销售数量',
  PRIMARY KEY (`product_id`) USING BTREE,
  INDEX `to_brand_id`(`to_brand_id`) USING BTREE,
  INDEX `product_name`(`product_name`) USING BTREE,
  INDEX `product_price`(`product_price`) USING BTREE,
  INDEX `product_num`(`product_num`) USING BTREE,
  CONSTRAINT `product_ibfk_1` FOREIGN KEY (`to_brand_id`) REFERENCES `brand` (`brand_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES ('a1', '15', '悦刻五代', 137.00, 666);
INSERT INTO `product` VALUES ('a1', 'b1', 'b1', 12.00, 12);

-- ----------------------------
-- Table structure for staff
-- ----------------------------
DROP TABLE IF EXISTS `staff`;
CREATE TABLE `staff`  (
  `staff_id` int NOT NULL COMMENT '销售人员编号',
  `staff_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '销售人员姓名',
  `staff_sex` enum('男','女') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '性别',
  `staff_age` int NULL DEFAULT NULL COMMENT '年龄',
  `to_store_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '归属门店编号',
  `staff_ph` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '销售人员手机号码',
  `bonus` int NULL DEFAULT NULL COMMENT '提成',
  PRIMARY KEY (`staff_id`) USING BTREE,
  INDEX `to_store_id`(`to_store_id`) USING BTREE,
  CONSTRAINT `staff_ibfk_1` FOREIGN KEY (`to_store_id`) REFERENCES `store` (`store_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of staff
-- ----------------------------

-- ----------------------------
-- Table structure for store
-- ----------------------------
DROP TABLE IF EXISTS `store`;
CREATE TABLE `store`  (
  `store_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '门店名称',
  `store_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '门店编号',
  `store_loc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '门店所在位置',
  `store_manage` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '负责人姓名',
  `store_ph` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '门店电话',
  PRIMARY KEY (`store_id`) USING BTREE,
  INDEX `store_id`(`store_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of store
-- ----------------------------

-- ----------------------------
-- Table structure for title
-- ----------------------------
DROP TABLE IF EXISTS `title`;
CREATE TABLE `title`  (
  `to_product_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '产品编号',
  `to_product_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '产品名称',
  `one_prize` decimal(10, 2) NULL DEFAULT NULL COMMENT '单价',
  `sum_prize` decimal(10, 2) NULL DEFAULT NULL COMMENT '总销售额',
  `to_product_num` int NULL DEFAULT NULL COMMENT '总销售数量',
  `product_sate` enum('盈利','亏损') CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '是否盈利',
  INDEX `to_product_id`(`to_product_id`) USING BTREE,
  INDEX `to_product_name`(`to_product_name`) USING BTREE,
  INDEX `one_prize`(`one_prize`) USING BTREE,
  INDEX `to_product_num`(`to_product_num`) USING BTREE,
  CONSTRAINT `title_ibfk_1` FOREIGN KEY (`to_product_id`) REFERENCES `product` (`product_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `title_ibfk_2` FOREIGN KEY (`to_product_name`) REFERENCES `product` (`product_name`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `title_ibfk_3` FOREIGN KEY (`one_prize`) REFERENCES `product` (`product_price`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `title_ibfk_4` FOREIGN KEY (`to_product_num`) REFERENCES `product` (`product_num`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of title
-- ----------------------------
INSERT INTO `title` VALUES ('b1', 'b1', 12.00, 12.00, 12, '盈利');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户编号',
  `user_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '0为管理，1为正常员工',
  `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名称',
  `user_password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户密码',
  `user_state` int NOT NULL COMMENT '0为正常，1为冻结',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
