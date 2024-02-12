/*22-03-2022 REM Brosna - Create Database*/
CREATE DATABASE library;
-- ----------------------------
-- Table structure for appuser
-- ----------------------------
-- ----------------------------
DROP TABLE IF EXISTS `appuser`;
CREATE TABLE `appuser`  (
    `id` bigint NOT NULL AUTO_INCREMENT,
    `nonexpired` bit(1) NOT NULL,
    `nonlocked` bit(1) NOT NULL,
    `cannot_change_password` bit(1) NULL DEFAULT NULL,
    `nonexpired_credentials` bit(1) NOT NULL,
    `is_deleted` bit(1) NOT NULL,
    `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `enabled` bit(1) NOT NULL,
    `firsttime_login_remaining` bit(1) NOT NULL,
    `firstname` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `group_position` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
    `is_self_service_user` bit(1) NOT NULL,
    `last_time_password_updated` date NULL DEFAULT NULL,
    `lastname` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `password_never_expires` bit(1) NOT NULL,
    `username` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `chat_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `username_org`(`username` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

SET FOREIGN_KEY_CHECKS = 1;
-- ----------------------------
-- Table structure for appuser_aud
-- ----------------------------
DROP TABLE IF EXISTS `appuser_aud`;
CREATE TABLE `appuser_aud`  (
    `id` bigint NOT NULL,
    `rev` int NOT NULL,
    `revtype` tinyint NULL DEFAULT NULL,
    `nonexpired` bit(1) NULL DEFAULT NULL,
    `nonlocked` bit(1) NULL DEFAULT NULL,
    `cannot_change_password` bit(1) NULL DEFAULT NULL,
    `nonexpired_credentials` bit(1) NULL DEFAULT NULL,
    `is_deleted` bit(1) NULL DEFAULT NULL,
    `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
    `enabled` bit(1) NULL DEFAULT NULL,
    `firsttime_login_remaining` bit(1) NULL DEFAULT NULL,
    `firstname` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
    `group_position` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
    `is_self_service_user` bit(1) NULL DEFAULT NULL,
    `last_time_password_updated` date NULL DEFAULT NULL,
    `lastname` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
    `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
    `password_never_expires` bit(1) NULL DEFAULT NULL,
    `username` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
    `chat_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
    PRIMARY KEY (`id`, `rev`) USING BTREE,
    INDEX `FKghqkxo0r3pkq0cox04q6p4q6`(`rev`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for m_appuser_role
-- ----------------------------
DROP TABLE IF EXISTS `m_appuser_role`;
CREATE TABLE `m_appuser_role`  (
   `appuser_id` bigint NOT NULL,
   `role_id` bigint NOT NULL,
   PRIMARY KEY (`appuser_id`, `role_id`) USING BTREE,
   INDEX `FKmaey2s11yk52kfxjmjku0hevv`(`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for m_appuser_role_aud
-- ----------------------------
DROP TABLE IF EXISTS `m_appuser_role_aud`;
CREATE TABLE `m_appuser_role_aud`  (
   `rev` int NOT NULL,
   `appuser_id` bigint NOT NULL,
   `role_id` bigint NOT NULL,
   `revtype` tinyint NULL DEFAULT NULL,
   PRIMARY KEY (`rev`, `appuser_id`, `role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for m_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `m_role_permission`;
CREATE TABLE `m_role_permission`  (
  `role_id` bigint NOT NULL,
  `permission_id` bigint NOT NULL,
  PRIMARY KEY (`role_id`, `permission_id`) USING BTREE,
  INDEX `FKavoeo9jdp1dirsotodch1w5jo`(`permission_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for m_role_permission_aud
-- ----------------------------
DROP TABLE IF EXISTS `m_role_permission_aud`;
CREATE TABLE `m_role_permission_aud`  (
  `rev` int NOT NULL,
  `role_id` bigint NOT NULL,
  `permission_id` bigint NOT NULL,
  `revtype` tinyint NULL DEFAULT NULL,
  PRIMARY KEY (`rev`, `role_id`, `permission_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for password_validation_policy
-- ----------------------------
DROP TABLE IF EXISTS `password_validation_policy`;
CREATE TABLE `password_validation_policy`  (
   `id` bigint NOT NULL AUTO_INCREMENT,
   `active` bit(1) NOT NULL,
   `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
   `regex` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
   PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`  (
   `id` bigint NOT NULL AUTO_INCREMENT,
   `action_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
   `can_maker_checker` bit(1) NOT NULL,
   `code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
   `entity_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
   `grouping` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
   PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for permission_aud
-- ----------------------------
DROP TABLE IF EXISTS `permission_aud`;
CREATE TABLE `permission_aud`  (
   `id` bigint NOT NULL,
   `rev` int NOT NULL,
   `revtype` tinyint NULL DEFAULT NULL,
   `action_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
   `can_maker_checker` bit(1) NULL DEFAULT NULL,
   `code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
   `entity_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
   `grouping` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
   PRIMARY KEY (`id`, `rev`) USING BTREE,
   INDEX `FKi4gd0noquepvqt7vf4hu66hss`(`rev`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for revinfo
-- ----------------------------
DROP TABLE IF EXISTS `revinfo`;
CREATE TABLE `revinfo`  (
    `rev` int NOT NULL AUTO_INCREMENT,
    `revtstmp` bigint NULL DEFAULT NULL,
    PRIMARY KEY (`rev`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
     `id` bigint NOT NULL AUTO_INCREMENT,
     `description` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
     `is_disabled` bit(1) NOT NULL,
     `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
     PRIMARY KEY (`id`) USING BTREE,
     UNIQUE INDEX `UK_8sewwnpamngi6b1dwaa88askk`(`name`) USING BTREE,
     UNIQUE INDEX `unq_name`(`name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for role_aud
-- ----------------------------
DROP TABLE IF EXISTS `role_aud`;
CREATE TABLE `role_aud`  (
     `id` bigint NOT NULL,
     `rev` int NOT NULL,
     `revtype` tinyint NULL DEFAULT NULL,
     `description` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
     `is_disabled` bit(1) NULL DEFAULT NULL,
     `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
     PRIMARY KEY (`id`, `rev`) USING BTREE,
     INDEX `FKlbrv77rvmslt7xege0jd87p4q`(`rev`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

/**/
CREATE TABLE `T_APPLICATION_STATUS`  (
     `APS_CODE` varchar(35) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
     `APS_DESC` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
     `APS_DESC_OTH` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
     `APS_REC_STATUS` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
     `APS_UID_CREATE` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
     `APS_DT_CREATE` datetime NULL DEFAULT NULL,
     `APS_DT_LUPD` datetime NULL DEFAULT NULL,
     `APS_UID_LUPD` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
     PRIMARY KEY (`APS_CODE`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

SET FOREIGN_KEY_CHECKS = 1;

/**/
CREATE TABLE `T_APPLICATION_TYPE`  (
   `APT_CODE` varchar(35) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
   `APT_DESC` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
   `APT_DESC_OTH` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
   `APT_REC_STATUS` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
   `APT_UID_CREATE` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
   `APT_DT_CREATE` datetime NULL DEFAULT NULL,
   `APT_DT_LUPD` datetime NULL DEFAULT NULL,
   `APT_UID_LUPD` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
   PRIMARY KEY (`APT_CODE`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

SET FOREIGN_KEY_CHECKS = 1;

/**/
CREATE TABLE `T_APPLICATION`  (
  `APP_ID` varchar(35) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `APP_APP_TYPE` varchar(35) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `APP_APP_STATUS` varchar(35) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `APP_DT_CONFIRM` datetime NULL DEFAULT NULL,
  `APP_REC_STATUS` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `APP_UID_CREATE` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `APP_DT_CREATE` datetime NULL DEFAULT NULL,
  `APP_DT_LUPD` datetime NULL DEFAULT NULL,
  `APP_UID_LUPD` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`APP_ID`) USING BTREE,
  INDEX `APP_APP_TYPE`(`APP_APP_TYPE` ASC) USING BTREE,
  INDEX `APP_APP_STATUS`(`APP_APP_STATUS` ASC) USING BTREE,
  CONSTRAINT `T_APPLICATION_IBFK_1` FOREIGN KEY (`APP_APP_TYPE`) REFERENCES `T_APPLICATION_TYPE` (`APT_CODE`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `T_APPLICATION_IBFK_2` FOREIGN KEY (`APP_APP_STATUS`) REFERENCES `T_APPLICATION_STATUS` (`APS_CODE`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

SET FOREIGN_KEY_CHECKS = 1;

/**/
CREATE TABLE `T_BORROW_FORM`  (
  `BOR_ID` varchar(35) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `BOR_CUSTOMER` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `BOR_APPLICATION` varchar(35) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `BOR_TOTAL_QTY` int NULL DEFAULT NULL,
  `BOR_TOTAL_OWE` int NULL DEFAULT NULL,
  `BOR_PENALTY_AMOUNT` decimal(14, 3) NULL DEFAULT NULL,
  `BOR_DT_BORROW` datetime NULL DEFAULT NULL,
  `BOR_DT_RETURN` datetime NULL DEFAULT NULL,
  `BOR_REC_STATUS` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `BOR_UID_CREATE` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `BOR_DT_CREATE` datetime NULL DEFAULT NULL,
  `BOR_DT_LUPD` datetime NULL DEFAULT NULL,
  `BOR_UID_LUPD` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`BOR_ID`) USING BTREE,
  INDEX `BOR_CUSTOMER`(`BOR_CUSTOMER` ASC) USING BTREE,
  INDEX `BOR_APPLICATION`(`BOR_APPLICATION` ASC) USING BTREE,
  CONSTRAINT `T_BORROW_FORM_IBFK_1` FOREIGN KEY (`BOR_CUSTOMER`) REFERENCES `appuser` (`username`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `T_BORROW_FORM_IBFK_2` FOREIGN KEY (`BOR_APPLICATION`) REFERENCES `T_APPLICATION` (`APP_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

SET FOREIGN_KEY_CHECKS = 1;

/**/
DROP TABLE IF EXISTS `T_SYSTEM_PARAM`;
CREATE TABLE `T_SYSTEM_PARAM`  (
   `SYP_KEY` varchar(35) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
   `SYP_VAL` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
   `SYP_DESC` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
   `SYP_REC_STATUS` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
   PRIMARY KEY (`SYP_KEY`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;

/**/
DROP TABLE IF EXISTS `T_NOTIFICATION_LOGS`;
CREATE TABLE `T_NOTIFICATION_LOGS`  (
    `NOL_ID` bigint NOT NULL AUTO_INCREMENT,
    `NOL_TYPE` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
    `NOL_BODY` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
    `NOL_RETRY` int NOT NULL,
    `NOL_STATUS` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
    `NOL_REC_STATUS` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
    PRIMARY KEY (`NOL_ID`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;

/**/
DROP TABLE IF EXISTS `T_NOTIFICATION_TEMPLATE`;
CREATE TABLE `T_NOTIFICATION_TEMPLATE`  (
    `NOT_ID` bigint NOT NULL AUTO_INCREMENT,
    `NOT_CHANNEL_TYPE` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
    `NOT_CONTENT_TYPE` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
    `NOT_SUBJECT` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
    `NOT_CONTENT` varchar(4000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
    `NOT_DESC` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
    `NOT_REC_STATUS` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
    PRIMARY KEY (`NOT_ID`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;

/**/
DROP TABLE IF EXISTS `T_NOTIFICATION_APP`;
CREATE TABLE `T_NOTIFICATION_APP`  (
   `NOA_ID` bigint NOT NULL AUTO_INCREMENT,
   `NOA_APPLICATION_TYPE` varchar(35) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
   `NOA_ACTION` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
   `NOA_EMAIL_TEMPLATE` bigint NULL DEFAULT NULL,
   `NOA_EMAIL_REQUIRED` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
   `NOA_TELEGRAM_TEMPLATE` bigint NULL DEFAULT NULL,
   `NOA_TELEGRAM_REQUIRED` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
   `NOA_REC_STATUS` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
   `NOA_DT_CREATE` datetime NULL DEFAULT NULL,
   `NOA_DT_LUPD` datetime NULL DEFAULT NULL,
   `NOA_UID_CREATE` varchar(255) NULL DEFAULT NULL,
   `NOA_UID_LUPD` varchar(255) NULL DEFAULT NULL,
   PRIMARY KEY (`NOA_ID`) USING BTREE,
   INDEX `NOA_APPLICATION_TYPE`(`NOA_APPLICATION_TYPE` ASC) USING BTREE,
   CONSTRAINT `T_NOTIFICATION_APP_IBFK_1` FOREIGN KEY (`NOA_APPLICATION_TYPE`) REFERENCES `T_APPLICATION_TYPE` (`APT_CODE`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = MyISAM AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;

/**/
DROP TABLE IF EXISTS `T_REPORT_SERVICE_CONFIG`;
CREATE TABLE `T_REPORT_SERVICE_CONFIG`  (
    `RES_ID` bigint NOT NULL AUTO_INCREMENT,
    `RES_VAL` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `RES_SERVICE_NAME` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `RES_DESC` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
    `RES_REC_STATUS` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
    PRIMARY KEY (`RES_ID`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;

/**/
DROP TABLE IF EXISTS `T_REPORT_TEMPLATE_CONFIG`;
CREATE TABLE `T_REPORT_TEMPLATE_CONFIG`  (
     `RET_ID` bigint NOT NULL AUTO_INCREMENT,
     `RET_SERVICE` bigint NOT NULL,
     `RET_TEMPLATE_TYPE` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
     `RET_SUB_TEMPLATE` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
     `RET_TEMPLATE_PATH` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
     `RET_TEMPLATE_LOGO` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
     `RET_REC_STATUS` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
     PRIMARY KEY (`RET_ID`) USING BTREE,
     INDEX `FK9kcqp53g4li2niboyg0uqfbji`(`RET_SERVICE`) USING BTREE,
     CONSTRAINT `T_REPORT_TEMPLATE_CONFIG_IBFK_2` FOREIGN KEY (`RET_SERVICE`) REFERENCES `T_REPORT_SERVICE_CONFIG` (`RES_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = MyISAM AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;


/**/
DROP TABLE IF EXISTS `T_BOOK`;
CREATE TABLE `T_BOOK`  (
   `BOK_ID` varchar(35) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
   `BOK_TITLE` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
   `BOK_AUTHOR` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
   `BOK_PUBLIC_DATE`smallint NULL DEFAULT NULL,
   `BOK_QTY` int NULL DEFAULT NULL,
   `BOK_UNIT_PRICE` double NULL DEFAULT NULL,
   `BOK_COVER` longblob NULL,
   `BOK_BOOK_STATUS` varchar(35) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
   `BOK_REC_STATUS` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
   `BOK_DT_CREATE` datetime NULL DEFAULT NULL,
   `BOK_UID_CREATE` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
   `BOK_DT_LUPD` datetime NULL DEFAULT NULL,
   `BOK_UID_LUPD` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
   PRIMARY KEY (`BOK_ID`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;

-- ----------------------------
-- Table structure for t_mst_province
-- ----------------------------
DROP TABLE IF EXISTS `T_MST_PROVINCE`;
CREATE TABLE `T_MST_PROVINCE`  (
   `PRO_CODE` varchar(35) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
   `PRO_DESC` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
   `PRO_DESC_OTH` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
   `PRO_REC_STATUS` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
   `PRO_UID_CREATE` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
   `PRO_DT_CREATE` datetime NULL DEFAULT NULL,
   `PRO_DT_LUPD` datetime NULL DEFAULT NULL,
   `PRO_UID_LUPD` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
   PRIMARY KEY (`PRO_CODE`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

SET FOREIGN_KEY_CHECKS = 1;

-- ----------------------------
-- Table structure for t_mst_district
-- ----------------------------
DROP TABLE IF EXISTS `T_MST_DISTRICT`;
CREATE TABLE `T_MST_DISTRICT`  (
   `DIS_CODE` varchar(35) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
   `DIS_DESC` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
   `DIS_DESC_OTH` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
   `DIS_DT_CREATE` datetime(0) NULL DEFAULT NULL,
   `DIS_DT_LUPD` datetime(0) NULL DEFAULT NULL,
   `DIS_REC_STATUS` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
   `DIS_UID_CREATE` varchar(35) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
   `DIS_UID_LUPD` varchar(35) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
   PRIMARY KEY (`DIS_CODE`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;

-- ----------------------------
-- Table structure for t_mst_district
-- ----------------------------
DROP TABLE IF EXISTS `T_MST_COMMUNE`;
CREATE TABLE `T_MST_COMMUNE`  (
  `COM_CODE` varchar(35) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `COM_DESC` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `COM_DESC_OTH` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `COM_DT_CREATE` datetime(0) NULL DEFAULT NULL,
  `COM_DT_LUPD` datetime(0) NULL DEFAULT NULL,
  `COM_REC_STATUS` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `COM_UID_CREATE` varchar(35) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `COM_UID_LUPD` varchar(35) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`COM_CODE`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;

SET FOREIGN_KEY_CHECKS = 0;
-- ----------------------------
-- Table structure for t_items
-- ----------------------------
DROP TABLE IF EXISTS `T_ITEMS`;
CREATE TABLE `T_ITEMS`  (
    `ITM_ID` VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `ITM_APPLICATION` VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `ITM_BOOK` VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `ITM_OWE_QTY` INT(0) NOT NULL,
    `ITM_QTY` INT(0) NOT NULL,
    `ITM_REFERENCE` VARCHAR(35) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `ITM_REC_STATUS` CHAR(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
    `ITM_DT_CREATE` DATETIME(0) NULL DEFAULT NULL,
    `ITM_DT_LUPD` DATETIME(0) NULL DEFAULT NULL,
    `ITM_UID_LUPD` VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
    `ITM_UID_CREATE` VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
    PRIMARY KEY (`ITM_ID`) USING BTREE,
    INDEX `ITM_BOOK`(`ITM_BOOK` ASC) USING BTREE,
    INDEX `ITM_APPLICATION`(`ITM_APPLICATION` ASC) USING BTREE,
    CONSTRAINT `T_ITEMS_IBFK_1` FOREIGN KEY (`ITM_BOOK`) REFERENCES `T_BOOK` (`BOK_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT `TT_ITEMS_IBFK_2` FOREIGN KEY (`ITM_APPLICATION`) REFERENCES `T_APPLICATION` (`APP_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = INNODB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_items
-- ----------------------------

-- ----------------------------
-- TABLE STRUCTURE FOR T_RETURN_FORM
-- ----------------------------
DROP TABLE IF EXISTS `T_RETURN_FORM`;
CREATE TABLE `T_RETURN_FORM`  (
  `RET_ID` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `RET_REC_STATUS` CHAR(1) CHARACTER SET UTF8MB4 COLLATE UTF8MB4_0900_AI_CI NULL DEFAULT NULL,
  `RET_BORROW_DATE` DATETIME NULL DEFAULT NULL,
  `RET_DT_CREATE` DATETIME NULL DEFAULT NULL,
  `RET_DT_LUPD` DATETIME NULL DEFAULT NULL,
  `RET_PENALTY_AMOUNT` DOUBLE NULL DEFAULT NULL,
  `RET_UID_CREATE` VARCHAR(255) CHARACTER SET UTF8MB4 COLLATE UTF8MB4_0900_AI_CI NULL DEFAULT NULL,
  `RET_UID_LUPD` VARCHAR(255) CHARACTER SET UTF8MB4 COLLATE UTF8MB4_0900_AI_CI NULL DEFAULT NULL,
  `RET_QTY` INT NULL DEFAULT NULL,
  `RET_STATUS` VARCHAR(255) CHARACTER SET UTF8MB4 COLLATE UTF8MB4_0900_AI_CI NULL DEFAULT NULL,
  `RET_APPLICATION` VARCHAR(45) CHARACTER SET UTF8MB4 COLLATE UTF8MB4_0900_AI_CI NULL DEFAULT NULL,
  `RET_BORROW_APPLICATION` VARCHAR(45) CHARACTER SET UTF8MB4 COLLATE UTF8MB4_0900_AI_CI NULL DEFAULT NULL,
  PRIMARY KEY (`RET_ID`) USING BTREE,
  INDEX `FKS3F0IVX8888978PH4DO2RHT3P`(`RET_APPLICATION`) USING BTREE,
  INDEX `FK4R1R9V3CBQG07TWDTFTSPJRE3`(`RET_BORROW_APPLICATION`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- RECORDS OF T_RETURN_FORM
-- ----------------------------

-- ----------------------------
-- TABLE STRUCTURE FOR T_CUSTOMER
-- ----------------------------
CREATE TABLE `T_CUSTOMER`  (
   `CUS_ID`  varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
   `CUSTOMER_NAME` VARCHAR(255) CHARACTER SET UTF8MB4 COLLATE UTF8MB4_0900_AI_CI NULL DEFAULT NULL,
   `CUSTOMER_TYPE` VARCHAR(255) CHARACTER SET UTF8MB4 COLLATE UTF8MB4_0900_AI_CI NULL DEFAULT NULL,
   `DATE_OF_BIRTH` DATETIME NULL DEFAULT NULL,
   `CUS_DT_CREATE` DATETIME NULL DEFAULT NULL,
   `CUS_DT_LUPD` DATETIME NULL DEFAULT NULL,
   `CUSTOMER_GENDER` VARCHAR(255) CHARACTER SET UTF8MB4 COLLATE UTF8MB4_0900_AI_CI NULL DEFAULT NULL,
   `IDENTITY_CARD_NO` VARCHAR(255) CHARACTER SET UTF8MB4 COLLATE UTF8MB4_0900_AI_CI NULL DEFAULT NULL,
   `PHONE_NUMBER` VARCHAR(255) CHARACTER SET UTF8MB4 COLLATE UTF8MB4_0900_AI_CI NULL DEFAULT NULL,
   `REC_STATUS` CHAR(1) CHARACTER SET UTF8MB4 COLLATE UTF8MB4_0900_AI_CI NULL DEFAULT NULL,
   `CUS_UID_CREATE` VARCHAR(35) CHARACTER SET UTF8MB4 COLLATE UTF8MB4_0900_AI_CI NULL DEFAULT NULL,
   `CUS_UID_LUPD` VARCHAR(35) CHARACTER SET UTF8MB4 COLLATE UTF8MB4_0900_AI_CI NULL DEFAULT NULL,
   PRIMARY KEY (`CUS_ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- RECORDS OF T_CUSTOMER
-- ----------------------------

CREATE TABLE `T_AUDITLOG`  (
   `AUDT_ID` varchar(35) NOT NULL,
   `AUDT_EVENT` varchar(100) NOT NULL,
   `AUDT_TIMESTAMP` datetime NOT NULL,
   `AUDT_ACCNID` varchar(35) NULL DEFAULT NULL,
   `AUDT_UID` varchar(35) NOT NULL,
   `AUDT_UNAME` varchar(35) NULL DEFAULT NULL,
   `AUDT_REMOTE_IP` varchar(35) NULL DEFAULT NULL,
   `AUDT_RECKEY` varchar(225) NOT NULL,
   `AUDT_PARAM1` varchar(225) NULL DEFAULT NULL,
   `AUDT_PARAM2` varchar(225) NULL DEFAULT NULL,
   `AUDT_PARAM3` varchar(225) NULL DEFAULT NULL,
   `AUDT_REMARKS` varchar(4096) NULL DEFAULT NULL,
   PRIMARY KEY (`AUDT_ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

SET FOREIGN_KEY_CHECKS = 1;

