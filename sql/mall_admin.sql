/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50735
 Source Host           : localhost:3306
 Source Schema         : mall_admin

 Target Server Type    : MySQL
 Target Server Version : 50735
 File Encoding         : 65001

 Date: 24/05/2022 23:31:22
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for qrtz_blob_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_blob_triggers`;
CREATE TABLE `qrtz_blob_triggers`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `BLOB_DATA` blob NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  INDEX `SCHED_NAME`(`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `qrtz_blob_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of qrtz_blob_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_calendars
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_calendars`;
CREATE TABLE `qrtz_calendars`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `CALENDAR_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `CALENDAR` blob NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `CALENDAR_NAME`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of qrtz_calendars
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_cron_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_cron_triggers`;
CREATE TABLE `qrtz_cron_triggers`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `CRON_EXPRESSION` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TIME_ZONE_ID` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of qrtz_cron_triggers
-- ----------------------------
INSERT INTO `qrtz_cron_triggers` VALUES ('RenrenScheduler', 'TASK_1', 'DEFAULT', '0 0/30 * * * ?', 'Asia/Shanghai');

-- ----------------------------
-- Table structure for qrtz_fired_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_fired_triggers`;
CREATE TABLE `qrtz_fired_triggers`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `ENTRY_ID` varchar(95) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `INSTANCE_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `FIRED_TIME` bigint(13) NOT NULL,
  `SCHED_TIME` bigint(13) NOT NULL,
  `PRIORITY` int(11) NOT NULL,
  `STATE` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `JOB_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `JOB_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `IS_NONCONCURRENT` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `REQUESTS_RECOVERY` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`, `ENTRY_ID`) USING BTREE,
  INDEX `IDX_QRTZ_FT_TRIG_INST_NAME`(`SCHED_NAME`, `INSTANCE_NAME`) USING BTREE,
  INDEX `IDX_QRTZ_FT_INST_JOB_REQ_RCVRY`(`SCHED_NAME`, `INSTANCE_NAME`, `REQUESTS_RECOVERY`) USING BTREE,
  INDEX `IDX_QRTZ_FT_J_G`(`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_FT_JG`(`SCHED_NAME`, `JOB_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_FT_T_G`(`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_FT_TG`(`SCHED_NAME`, `TRIGGER_GROUP`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of qrtz_fired_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_job_details
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_job_details`;
CREATE TABLE `qrtz_job_details`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `JOB_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `JOB_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `DESCRIPTION` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `JOB_CLASS_NAME` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `IS_DURABLE` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `IS_NONCONCURRENT` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `IS_UPDATE_DATA` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `REQUESTS_RECOVERY` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `JOB_DATA` blob NULL,
  PRIMARY KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_J_REQ_RECOVERY`(`SCHED_NAME`, `REQUESTS_RECOVERY`) USING BTREE,
  INDEX `IDX_QRTZ_J_GRP`(`SCHED_NAME`, `JOB_GROUP`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of qrtz_job_details
-- ----------------------------
INSERT INTO `qrtz_job_details` VALUES ('RenrenScheduler', 'TASK_1', 'DEFAULT', NULL, 'io.renren.modules.job.utils.ScheduleJob', '0', '0', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000D4A4F425F504152414D5F4B45597372002E696F2E72656E72656E2E6D6F64756C65732E6A6F622E656E746974792E5363686564756C654A6F62456E7469747900000000000000010200074C00086265616E4E616D657400124C6A6176612F6C616E672F537472696E673B4C000A63726561746554696D657400104C6A6176612F7574696C2F446174653B4C000E63726F6E45787072657373696F6E71007E00094C00056A6F6249647400104C6A6176612F6C616E672F4C6F6E673B4C0006706172616D7371007E00094C000672656D61726B71007E00094C00067374617475737400134C6A6176612F6C616E672F496E74656765723B7870740008746573745461736B7372000E6A6176612E7574696C2E44617465686A81014B5974190300007870770800000172687412E87874000E3020302F3330202A202A202A203F7372000E6A6176612E6C616E672E4C6F6E673B8BE490CC8F23DF0200014A000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B0200007870000000000000000174000672656E72656E74000CE58F82E695B0E6B58BE8AF95737200116A6176612E6C616E672E496E746567657212E2A0A4F781873802000149000576616C75657871007E0013000000007800);

-- ----------------------------
-- Table structure for qrtz_locks
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_locks`;
CREATE TABLE `qrtz_locks`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `LOCK_NAME` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `LOCK_NAME`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of qrtz_locks
-- ----------------------------
INSERT INTO `qrtz_locks` VALUES ('RenrenScheduler', 'STATE_ACCESS');
INSERT INTO `qrtz_locks` VALUES ('RenrenScheduler', 'TRIGGER_ACCESS');

-- ----------------------------
-- Table structure for qrtz_paused_trigger_grps
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_paused_trigger_grps`;
CREATE TABLE `qrtz_paused_trigger_grps`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_GROUP`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of qrtz_paused_trigger_grps
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_scheduler_state
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_scheduler_state`;
CREATE TABLE `qrtz_scheduler_state`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `INSTANCE_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `LAST_CHECKIN_TIME` bigint(13) NOT NULL,
  `CHECKIN_INTERVAL` bigint(13) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `INSTANCE_NAME`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of qrtz_scheduler_state
-- ----------------------------
INSERT INTO `qrtz_scheduler_state` VALUES ('RenrenScheduler', 'RawChen1653015417621', 1653048735220, 15000);

-- ----------------------------
-- Table structure for qrtz_simple_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simple_triggers`;
CREATE TABLE `qrtz_simple_triggers`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `REPEAT_COUNT` bigint(7) NOT NULL,
  `REPEAT_INTERVAL` bigint(12) NOT NULL,
  `TIMES_TRIGGERED` bigint(10) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `qrtz_simple_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of qrtz_simple_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_simprop_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simprop_triggers`;
CREATE TABLE `qrtz_simprop_triggers`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `STR_PROP_1` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `STR_PROP_2` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `STR_PROP_3` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `INT_PROP_1` int(11) NULL DEFAULT NULL,
  `INT_PROP_2` int(11) NULL DEFAULT NULL,
  `LONG_PROP_1` bigint(20) NULL DEFAULT NULL,
  `LONG_PROP_2` bigint(20) NULL DEFAULT NULL,
  `DEC_PROP_1` decimal(13, 4) NULL DEFAULT NULL,
  `DEC_PROP_2` decimal(13, 4) NULL DEFAULT NULL,
  `BOOL_PROP_1` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `BOOL_PROP_2` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `qrtz_simprop_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of qrtz_simprop_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_triggers`;
CREATE TABLE `qrtz_triggers`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `JOB_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `JOB_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `DESCRIPTION` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `NEXT_FIRE_TIME` bigint(13) NULL DEFAULT NULL,
  `PREV_FIRE_TIME` bigint(13) NULL DEFAULT NULL,
  `PRIORITY` int(11) NULL DEFAULT NULL,
  `TRIGGER_STATE` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_TYPE` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `START_TIME` bigint(13) NOT NULL,
  `END_TIME` bigint(13) NULL DEFAULT NULL,
  `CALENDAR_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `MISFIRE_INSTR` smallint(2) NULL DEFAULT NULL,
  `JOB_DATA` blob NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_T_J`(`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_T_JG`(`SCHED_NAME`, `JOB_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_T_C`(`SCHED_NAME`, `CALENDAR_NAME`) USING BTREE,
  INDEX `IDX_QRTZ_T_G`(`SCHED_NAME`, `TRIGGER_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_T_STATE`(`SCHED_NAME`, `TRIGGER_STATE`) USING BTREE,
  INDEX `IDX_QRTZ_T_N_STATE`(`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`, `TRIGGER_STATE`) USING BTREE,
  INDEX `IDX_QRTZ_T_N_G_STATE`(`SCHED_NAME`, `TRIGGER_GROUP`, `TRIGGER_STATE`) USING BTREE,
  INDEX `IDX_QRTZ_T_NEXT_FIRE_TIME`(`SCHED_NAME`, `NEXT_FIRE_TIME`) USING BTREE,
  INDEX `IDX_QRTZ_T_NFT_ST`(`SCHED_NAME`, `TRIGGER_STATE`, `NEXT_FIRE_TIME`) USING BTREE,
  INDEX `IDX_QRTZ_T_NFT_MISFIRE`(`SCHED_NAME`, `MISFIRE_INSTR`, `NEXT_FIRE_TIME`) USING BTREE,
  INDEX `IDX_QRTZ_T_NFT_ST_MISFIRE`(`SCHED_NAME`, `MISFIRE_INSTR`, `NEXT_FIRE_TIME`, `TRIGGER_STATE`) USING BTREE,
  INDEX `IDX_QRTZ_T_NFT_ST_MISFIRE_GRP`(`SCHED_NAME`, `MISFIRE_INSTR`, `NEXT_FIRE_TIME`, `TRIGGER_GROUP`, `TRIGGER_STATE`) USING BTREE,
  CONSTRAINT `qrtz_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) REFERENCES `qrtz_job_details` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of qrtz_triggers
-- ----------------------------
INSERT INTO `qrtz_triggers` VALUES ('RenrenScheduler', 'TASK_1', 'DEFAULT', 'TASK_1', 'DEFAULT', NULL, 1653049800000, 1653048000000, 5, 'WAITING', 'CRON', 1590919162000, 0, NULL, 2, 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000D4A4F425F504152414D5F4B45597372002E696F2E72656E72656E2E6D6F64756C65732E6A6F622E656E746974792E5363686564756C654A6F62456E7469747900000000000000010200074C00086265616E4E616D657400124C6A6176612F6C616E672F537472696E673B4C000A63726561746554696D657400104C6A6176612F7574696C2F446174653B4C000E63726F6E45787072657373696F6E71007E00094C00056A6F6249647400104C6A6176612F6C616E672F4C6F6E673B4C0006706172616D7371007E00094C000672656D61726B71007E00094C00067374617475737400134C6A6176612F6C616E672F496E74656765723B7870740008746573745461736B7372000E6A6176612E7574696C2E44617465686A81014B5974190300007870770800000172687412E87874000E3020302F3330202A202A202A203F7372000E6A6176612E6C616E672E4C6F6E673B8BE490CC8F23DF0200014A000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B020000787000000000000000017400077261776368656E74000CE58F82E695B0E6B58BE8AF95737200116A6176612E6C616E672E496E746567657212E2A0A4F781873802000149000576616C75657871007E0013000000007800);

-- ----------------------------
-- Table structure for schedule_job
-- ----------------------------
DROP TABLE IF EXISTS `schedule_job`;
CREATE TABLE `schedule_job`  (
  `job_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务id',
  `bean_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'spring bean名称',
  `params` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '参数',
  `cron_expression` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'cron表达式',
  `status` tinyint(4) NULL DEFAULT NULL COMMENT '任务状态  0：正常  1：暂停',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`job_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '定时任务' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of schedule_job
-- ----------------------------
INSERT INTO `schedule_job` VALUES (1, 'testTask', 'rawchen', '0 0/30 * * * ?', 0, '参数测试', '2020-05-31 09:58:57');

-- ----------------------------
-- Table structure for schedule_job_log
-- ----------------------------
DROP TABLE IF EXISTS `schedule_job_log`;
CREATE TABLE `schedule_job_log`  (
  `log_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务日志id',
  `job_id` bigint(20) NOT NULL COMMENT '任务id',
  `bean_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'spring bean名称',
  `params` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '参数',
  `status` tinyint(4) NOT NULL COMMENT '任务状态    0：成功    1：失败',
  `error` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '失败信息',
  `times` int(11) NOT NULL COMMENT '耗时(单位：毫秒)',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`log_id`) USING BTREE,
  INDEX `job_id`(`job_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 522 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '定时任务日志' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of schedule_job_log
-- ----------------------------
INSERT INTO `schedule_job_log` VALUES (326, 1, 'testTask', 'rawchen', 0, NULL, 1, '2022-02-23 18:30:00');
INSERT INTO `schedule_job_log` VALUES (327, 1, 'testTask', 'rawchen', 0, NULL, 0, '2022-02-23 19:00:00');
INSERT INTO `schedule_job_log` VALUES (328, 1, 'testTask', 'rawchen', 0, NULL, 1, '2022-02-23 19:30:00');
INSERT INTO `schedule_job_log` VALUES (329, 1, 'testTask', 'rawchen', 0, NULL, 1, '2022-02-23 20:00:00');
INSERT INTO `schedule_job_log` VALUES (330, 1, 'testTask', 'rawchen', 0, NULL, 2, '2022-02-23 20:30:00');
INSERT INTO `schedule_job_log` VALUES (331, 1, 'testTask', 'rawchen', 0, NULL, 1, '2022-02-23 21:00:00');
INSERT INTO `schedule_job_log` VALUES (332, 1, 'testTask', 'rawchen', 0, NULL, 0, '2022-02-23 21:30:00');
INSERT INTO `schedule_job_log` VALUES (333, 1, 'testTask', 'rawchen', 0, NULL, 0, '2022-02-23 22:00:00');
INSERT INTO `schedule_job_log` VALUES (334, 1, 'testTask', 'rawchen', 0, NULL, 0, '2022-02-23 22:30:00');
INSERT INTO `schedule_job_log` VALUES (335, 1, 'testTask', 'rawchen', 0, NULL, 1, '2022-03-10 20:30:00');
INSERT INTO `schedule_job_log` VALUES (336, 1, 'testTask', 'rawchen', 0, NULL, 1, '2022-03-10 21:00:00');
INSERT INTO `schedule_job_log` VALUES (337, 1, 'testTask', 'rawchen', 0, NULL, 1, '2022-03-10 21:30:00');
INSERT INTO `schedule_job_log` VALUES (338, 1, 'testTask', 'rawchen', 0, NULL, 0, '2022-03-10 22:00:00');
INSERT INTO `schedule_job_log` VALUES (339, 1, 'testTask', 'rawchen', 0, NULL, 1, '2022-03-10 22:30:00');
INSERT INTO `schedule_job_log` VALUES (340, 1, 'testTask', 'rawchen', 0, NULL, 2, '2022-03-10 23:00:00');
INSERT INTO `schedule_job_log` VALUES (341, 1, 'testTask', 'rawchen', 0, NULL, 2, '2022-03-10 23:30:00');
INSERT INTO `schedule_job_log` VALUES (342, 1, 'testTask', 'rawchen', 0, NULL, 11, '2022-03-11 00:00:00');
INSERT INTO `schedule_job_log` VALUES (343, 1, 'testTask', 'rawchen', 0, NULL, 2, '2022-03-11 00:30:00');
INSERT INTO `schedule_job_log` VALUES (344, 1, 'testTask', 'rawchen', 0, NULL, 0, '2022-03-11 01:00:00');
INSERT INTO `schedule_job_log` VALUES (345, 1, 'testTask', 'rawchen', 0, NULL, 2, '2022-04-10 17:00:00');
INSERT INTO `schedule_job_log` VALUES (346, 1, 'testTask', 'rawchen', 0, NULL, 0, '2022-04-12 17:00:00');
INSERT INTO `schedule_job_log` VALUES (347, 1, 'testTask', 'rawchen', 0, NULL, 3, '2022-04-12 17:30:00');
INSERT INTO `schedule_job_log` VALUES (348, 1, 'testTask', 'rawchen', 0, NULL, 0, '2022-04-12 18:00:00');
INSERT INTO `schedule_job_log` VALUES (349, 1, 'testTask', 'rawchen', 0, NULL, 3, '2022-04-12 18:30:00');
INSERT INTO `schedule_job_log` VALUES (350, 1, 'testTask', 'rawchen', 0, NULL, 1, '2022-04-12 19:00:00');
INSERT INTO `schedule_job_log` VALUES (351, 1, 'testTask', 'rawchen', 0, NULL, 0, '2022-04-12 19:30:00');
INSERT INTO `schedule_job_log` VALUES (352, 1, 'testTask', 'rawchen', 0, NULL, 0, '2022-04-12 20:00:00');
INSERT INTO `schedule_job_log` VALUES (353, 1, 'testTask', 'rawchen', 0, NULL, 1, '2022-04-12 20:30:00');
INSERT INTO `schedule_job_log` VALUES (354, 1, 'testTask', 'rawchen', 0, NULL, 0, '2022-04-12 21:00:00');
INSERT INTO `schedule_job_log` VALUES (355, 1, 'testTask', 'rawchen', 0, NULL, 0, '2022-04-12 21:30:00');
INSERT INTO `schedule_job_log` VALUES (356, 1, 'testTask', 'rawchen', 0, NULL, 1, '2022-04-12 22:00:00');
INSERT INTO `schedule_job_log` VALUES (357, 1, 'testTask', 'rawchen', 0, NULL, 0, '2022-04-12 22:30:00');
INSERT INTO `schedule_job_log` VALUES (358, 1, 'testTask', 'rawchen', 0, NULL, 1, '2022-04-12 23:00:00');
INSERT INTO `schedule_job_log` VALUES (359, 1, 'testTask', 'rawchen', 0, NULL, 1, '2022-04-13 18:30:00');
INSERT INTO `schedule_job_log` VALUES (360, 1, 'testTask', 'rawchen', 0, NULL, 0, '2022-04-13 19:00:00');
INSERT INTO `schedule_job_log` VALUES (361, 1, 'testTask', 'rawchen', 0, NULL, 1, '2022-04-13 19:30:00');
INSERT INTO `schedule_job_log` VALUES (362, 1, 'testTask', 'rawchen', 0, NULL, 1, '2022-04-13 20:00:00');
INSERT INTO `schedule_job_log` VALUES (363, 1, 'testTask', 'rawchen', 0, NULL, 1, '2022-04-13 20:30:00');
INSERT INTO `schedule_job_log` VALUES (364, 1, 'testTask', 'rawchen', 0, NULL, 0, '2022-04-13 21:00:00');
INSERT INTO `schedule_job_log` VALUES (365, 1, 'testTask', 'rawchen', 0, NULL, 1, '2022-04-13 21:30:00');
INSERT INTO `schedule_job_log` VALUES (366, 1, 'testTask', 'rawchen', 0, NULL, 0, '2022-04-13 22:00:00');
INSERT INTO `schedule_job_log` VALUES (367, 1, 'testTask', 'rawchen', 0, NULL, 0, '2022-04-13 22:30:00');
INSERT INTO `schedule_job_log` VALUES (368, 1, 'testTask', 'rawchen', 0, NULL, 0, '2022-04-13 23:00:00');
INSERT INTO `schedule_job_log` VALUES (369, 1, 'testTask', 'rawchen', 0, NULL, 1, '2022-04-13 23:30:00');
INSERT INTO `schedule_job_log` VALUES (370, 1, 'testTask', 'rawchen', 0, NULL, 13, '2022-04-14 00:00:00');
INSERT INTO `schedule_job_log` VALUES (371, 1, 'testTask', 'rawchen', 0, NULL, 5, '2022-04-14 00:30:00');
INSERT INTO `schedule_job_log` VALUES (372, 1, 'testTask', 'rawchen', 0, NULL, 2, '2022-04-14 01:00:00');
INSERT INTO `schedule_job_log` VALUES (373, 1, 'testTask', 'rawchen', 0, NULL, 10, '2022-04-14 01:30:00');
INSERT INTO `schedule_job_log` VALUES (374, 1, 'testTask', 'rawchen', 0, NULL, 10, '2022-04-14 02:00:00');
INSERT INTO `schedule_job_log` VALUES (375, 1, 'testTask', 'rawchen', 0, NULL, 2, '2022-04-14 02:30:00');
INSERT INTO `schedule_job_log` VALUES (376, 1, 'testTask', 'rawchen', 0, NULL, 2, '2022-04-14 03:00:00');
INSERT INTO `schedule_job_log` VALUES (377, 1, 'testTask', 'rawchen', 0, NULL, 30, '2022-04-14 10:30:00');
INSERT INTO `schedule_job_log` VALUES (378, 1, 'testTask', 'rawchen', 0, NULL, 3, '2022-04-14 11:00:00');
INSERT INTO `schedule_job_log` VALUES (379, 1, 'testTask', 'rawchen', 0, NULL, 4, '2022-04-14 11:30:00');
INSERT INTO `schedule_job_log` VALUES (380, 1, 'testTask', 'rawchen', 0, NULL, 1, '2022-04-14 12:00:00');
INSERT INTO `schedule_job_log` VALUES (381, 1, 'testTask', 'rawchen', 0, NULL, 3, '2022-04-14 12:30:00');
INSERT INTO `schedule_job_log` VALUES (382, 1, 'testTask', 'rawchen', 0, NULL, 21, '2022-04-14 13:00:00');
INSERT INTO `schedule_job_log` VALUES (383, 1, 'testTask', 'rawchen', 0, NULL, 5, '2022-04-14 13:30:00');
INSERT INTO `schedule_job_log` VALUES (384, 1, 'testTask', 'rawchen', 0, NULL, 0, '2022-04-14 14:00:00');
INSERT INTO `schedule_job_log` VALUES (385, 1, 'testTask', 'rawchen', 0, NULL, 2, '2022-04-14 14:30:00');
INSERT INTO `schedule_job_log` VALUES (386, 1, 'testTask', 'rawchen', 0, NULL, 0, '2022-04-14 15:00:00');
INSERT INTO `schedule_job_log` VALUES (387, 1, 'testTask', 'rawchen', 0, NULL, 15, '2022-04-14 15:30:00');
INSERT INTO `schedule_job_log` VALUES (388, 1, 'testTask', 'rawchen', 0, NULL, 11, '2022-04-14 16:00:00');
INSERT INTO `schedule_job_log` VALUES (389, 1, 'testTask', 'rawchen', 0, NULL, 7, '2022-04-14 16:30:00');
INSERT INTO `schedule_job_log` VALUES (390, 1, 'testTask', 'rawchen', 0, NULL, 13, '2022-04-14 17:00:00');
INSERT INTO `schedule_job_log` VALUES (391, 1, 'testTask', 'rawchen', 0, NULL, 2, '2022-04-14 17:30:00');
INSERT INTO `schedule_job_log` VALUES (392, 1, 'testTask', 'rawchen', 0, NULL, 13, '2022-04-14 18:00:00');
INSERT INTO `schedule_job_log` VALUES (393, 1, 'testTask', 'rawchen', 0, NULL, 2, '2022-04-14 18:30:00');
INSERT INTO `schedule_job_log` VALUES (394, 1, 'testTask', 'rawchen', 0, NULL, 0, '2022-04-14 19:00:00');
INSERT INTO `schedule_job_log` VALUES (395, 1, 'testTask', 'rawchen', 0, NULL, 6, '2022-04-14 19:30:00');
INSERT INTO `schedule_job_log` VALUES (396, 1, 'testTask', 'rawchen', 0, NULL, 1, '2022-04-14 20:00:00');
INSERT INTO `schedule_job_log` VALUES (397, 1, 'testTask', 'rawchen', 0, NULL, 9, '2022-04-14 20:30:00');
INSERT INTO `schedule_job_log` VALUES (398, 1, 'testTask', 'rawchen', 0, NULL, 0, '2022-04-14 21:00:00');
INSERT INTO `schedule_job_log` VALUES (399, 1, 'testTask', 'rawchen', 0, NULL, 8, '2022-04-14 21:30:00');
INSERT INTO `schedule_job_log` VALUES (400, 1, 'testTask', 'rawchen', 0, NULL, 12, '2022-04-14 22:00:00');
INSERT INTO `schedule_job_log` VALUES (401, 1, 'testTask', 'rawchen', 0, NULL, 4, '2022-04-14 22:30:00');
INSERT INTO `schedule_job_log` VALUES (402, 1, 'testTask', 'rawchen', 0, NULL, 11, '2022-04-14 23:00:00');
INSERT INTO `schedule_job_log` VALUES (403, 1, 'testTask', 'rawchen', 0, NULL, 8, '2022-04-14 23:30:00');
INSERT INTO `schedule_job_log` VALUES (404, 1, 'testTask', 'rawchen', 0, NULL, 123, '2022-04-15 00:00:00');
INSERT INTO `schedule_job_log` VALUES (405, 1, 'testTask', 'rawchen', 0, NULL, 9, '2022-05-01 20:30:00');
INSERT INTO `schedule_job_log` VALUES (406, 1, 'testTask', 'rawchen', 0, NULL, 4, '2022-05-01 21:00:00');
INSERT INTO `schedule_job_log` VALUES (407, 1, 'testTask', 'rawchen', 0, NULL, 1, '2022-05-01 21:30:00');
INSERT INTO `schedule_job_log` VALUES (408, 1, 'testTask', 'rawchen', 0, NULL, 3, '2022-05-01 22:00:00');
INSERT INTO `schedule_job_log` VALUES (409, 1, 'testTask', 'rawchen', 0, NULL, 17, '2022-05-01 22:30:00');
INSERT INTO `schedule_job_log` VALUES (410, 1, 'testTask', 'rawchen', 0, NULL, 8, '2022-05-01 23:00:00');
INSERT INTO `schedule_job_log` VALUES (411, 1, 'testTask', 'rawchen', 0, NULL, 3, '2022-05-12 18:30:00');
INSERT INTO `schedule_job_log` VALUES (412, 1, 'testTask', 'rawchen', 0, NULL, 2, '2022-05-12 19:00:00');
INSERT INTO `schedule_job_log` VALUES (413, 1, 'testTask', 'rawchen', 0, NULL, 2, '2022-05-12 19:30:00');
INSERT INTO `schedule_job_log` VALUES (414, 1, 'testTask', 'rawchen', 0, NULL, 0, '2022-05-12 20:00:00');
INSERT INTO `schedule_job_log` VALUES (415, 1, 'testTask', 'rawchen', 0, NULL, 7, '2022-05-12 20:30:00');
INSERT INTO `schedule_job_log` VALUES (416, 1, 'testTask', 'rawchen', 0, NULL, 11, '2022-05-12 21:00:00');
INSERT INTO `schedule_job_log` VALUES (417, 1, 'testTask', 'rawchen', 0, NULL, 0, '2022-05-13 12:00:00');
INSERT INTO `schedule_job_log` VALUES (418, 1, 'testTask', 'rawchen', 0, NULL, 2, '2022-05-13 12:30:00');
INSERT INTO `schedule_job_log` VALUES (419, 1, 'testTask', 'rawchen', 0, NULL, 2, '2022-05-13 13:00:00');
INSERT INTO `schedule_job_log` VALUES (420, 1, 'testTask', 'rawchen', 0, NULL, 2, '2022-05-13 13:30:00');
INSERT INTO `schedule_job_log` VALUES (421, 1, 'testTask', 'rawchen', 0, NULL, 1, '2022-05-13 14:00:00');
INSERT INTO `schedule_job_log` VALUES (422, 1, 'testTask', 'rawchen', 0, NULL, 3, '2022-05-13 14:30:00');
INSERT INTO `schedule_job_log` VALUES (423, 1, 'testTask', 'rawchen', 0, NULL, 4, '2022-05-13 15:00:00');
INSERT INTO `schedule_job_log` VALUES (424, 1, 'testTask', 'rawchen', 0, NULL, 0, '2022-05-13 15:30:00');
INSERT INTO `schedule_job_log` VALUES (425, 1, 'testTask', 'rawchen', 0, NULL, 3, '2022-05-13 16:00:00');
INSERT INTO `schedule_job_log` VALUES (426, 1, 'testTask', 'rawchen', 0, NULL, 1, '2022-05-13 16:30:00');
INSERT INTO `schedule_job_log` VALUES (427, 1, 'testTask', 'rawchen', 0, NULL, 0, '2022-05-13 17:00:00');
INSERT INTO `schedule_job_log` VALUES (428, 1, 'testTask', 'rawchen', 0, NULL, 1, '2022-05-13 17:30:00');
INSERT INTO `schedule_job_log` VALUES (429, 1, 'testTask', 'rawchen', 0, NULL, 1, '2022-05-13 18:00:00');
INSERT INTO `schedule_job_log` VALUES (430, 1, 'testTask', 'rawchen', 0, NULL, 0, '2022-05-13 18:30:00');
INSERT INTO `schedule_job_log` VALUES (431, 1, 'testTask', 'rawchen', 0, NULL, 0, '2022-05-13 19:00:00');
INSERT INTO `schedule_job_log` VALUES (432, 1, 'testTask', 'rawchen', 0, NULL, 5, '2022-05-13 19:30:00');
INSERT INTO `schedule_job_log` VALUES (433, 1, 'testTask', 'rawchen', 0, NULL, 1, '2022-05-13 20:00:00');
INSERT INTO `schedule_job_log` VALUES (434, 1, 'testTask', 'rawchen', 0, NULL, 0, '2022-05-13 20:30:00');
INSERT INTO `schedule_job_log` VALUES (435, 1, 'testTask', 'rawchen', 0, NULL, 0, '2022-05-13 21:00:00');
INSERT INTO `schedule_job_log` VALUES (436, 1, 'testTask', 'rawchen', 0, NULL, 1, '2022-05-13 21:30:00');
INSERT INTO `schedule_job_log` VALUES (437, 1, 'testTask', 'rawchen', 0, NULL, 1, '2022-05-13 22:00:00');
INSERT INTO `schedule_job_log` VALUES (438, 1, 'testTask', 'rawchen', 0, NULL, 0, '2022-05-13 22:30:00');
INSERT INTO `schedule_job_log` VALUES (439, 1, 'testTask', 'rawchen', 0, NULL, 0, '2022-05-13 23:00:00');
INSERT INTO `schedule_job_log` VALUES (440, 1, 'testTask', 'rawchen', 0, NULL, 1, '2022-05-13 23:30:00');
INSERT INTO `schedule_job_log` VALUES (441, 1, 'testTask', 'rawchen', 0, NULL, 7, '2022-05-14 17:00:00');
INSERT INTO `schedule_job_log` VALUES (442, 1, 'testTask', 'rawchen', 0, NULL, 8, '2022-05-14 17:30:00');
INSERT INTO `schedule_job_log` VALUES (443, 1, 'testTask', 'rawchen', 0, NULL, 12, '2022-05-14 18:00:00');
INSERT INTO `schedule_job_log` VALUES (444, 1, 'testTask', 'rawchen', 0, NULL, 6, '2022-05-14 18:30:00');
INSERT INTO `schedule_job_log` VALUES (445, 1, 'testTask', 'rawchen', 0, NULL, 4, '2022-05-14 19:00:00');
INSERT INTO `schedule_job_log` VALUES (446, 1, 'testTask', 'rawchen', 0, NULL, 9, '2022-05-14 19:30:00');
INSERT INTO `schedule_job_log` VALUES (447, 1, 'testTask', 'rawchen', 0, NULL, 2, '2022-05-14 20:00:00');
INSERT INTO `schedule_job_log` VALUES (448, 1, 'testTask', 'rawchen', 0, NULL, 0, '2022-05-14 20:30:00');
INSERT INTO `schedule_job_log` VALUES (449, 1, 'testTask', 'rawchen', 0, NULL, 2, '2022-05-14 21:00:00');
INSERT INTO `schedule_job_log` VALUES (450, 1, 'testTask', 'rawchen', 0, NULL, 13, '2022-05-14 21:30:00');
INSERT INTO `schedule_job_log` VALUES (451, 1, 'testTask', 'rawchen', 0, NULL, 13, '2022-05-14 22:00:00');
INSERT INTO `schedule_job_log` VALUES (452, 1, 'testTask', 'rawchen', 0, NULL, 20, '2022-05-18 11:00:00');
INSERT INTO `schedule_job_log` VALUES (453, 1, 'testTask', 'rawchen', 0, NULL, 3, '2022-05-18 11:30:00');
INSERT INTO `schedule_job_log` VALUES (454, 1, 'testTask', 'rawchen', 0, NULL, 1, '2022-05-18 12:00:00');
INSERT INTO `schedule_job_log` VALUES (455, 1, 'testTask', 'rawchen', 0, NULL, 7, '2022-05-18 12:30:00');
INSERT INTO `schedule_job_log` VALUES (456, 1, 'testTask', 'rawchen', 0, NULL, 1, '2022-05-18 13:00:00');
INSERT INTO `schedule_job_log` VALUES (457, 1, 'testTask', 'rawchen', 0, NULL, 1, '2022-05-18 13:30:00');
INSERT INTO `schedule_job_log` VALUES (458, 1, 'testTask', 'rawchen', 0, NULL, 0, '2022-05-18 14:00:00');
INSERT INTO `schedule_job_log` VALUES (459, 1, 'testTask', 'rawchen', 0, NULL, 0, '2022-05-18 14:30:00');
INSERT INTO `schedule_job_log` VALUES (460, 1, 'testTask', 'rawchen', 0, NULL, 15, '2022-05-18 15:00:00');
INSERT INTO `schedule_job_log` VALUES (461, 1, 'testTask', 'rawchen', 0, NULL, 7, '2022-05-18 15:30:00');
INSERT INTO `schedule_job_log` VALUES (462, 1, 'testTask', 'rawchen', 0, NULL, 1, '2022-05-18 16:00:00');
INSERT INTO `schedule_job_log` VALUES (463, 1, 'testTask', 'rawchen', 0, NULL, 20, '2022-05-18 16:30:00');
INSERT INTO `schedule_job_log` VALUES (464, 1, 'testTask', 'rawchen', 0, NULL, 7, '2022-05-18 17:00:00');
INSERT INTO `schedule_job_log` VALUES (465, 1, 'testTask', 'rawchen', 0, NULL, 1, '2022-05-18 17:30:00');
INSERT INTO `schedule_job_log` VALUES (466, 1, 'testTask', 'rawchen', 0, NULL, 5, '2022-05-18 18:00:00');
INSERT INTO `schedule_job_log` VALUES (467, 1, 'testTask', 'rawchen', 0, NULL, 8, '2022-05-18 18:30:00');
INSERT INTO `schedule_job_log` VALUES (468, 1, 'testTask', 'rawchen', 0, NULL, 2, '2022-05-18 19:00:00');
INSERT INTO `schedule_job_log` VALUES (469, 1, 'testTask', 'rawchen', 0, NULL, 6, '2022-05-18 19:30:00');
INSERT INTO `schedule_job_log` VALUES (470, 1, 'testTask', 'rawchen', 0, NULL, 1, '2022-05-18 20:00:00');
INSERT INTO `schedule_job_log` VALUES (471, 1, 'testTask', 'rawchen', 0, NULL, 6, '2022-05-18 20:30:00');
INSERT INTO `schedule_job_log` VALUES (472, 1, 'testTask', 'rawchen', 0, NULL, 1, '2022-05-18 21:00:00');
INSERT INTO `schedule_job_log` VALUES (473, 1, 'testTask', 'rawchen', 0, NULL, 7, '2022-05-18 21:30:00');
INSERT INTO `schedule_job_log` VALUES (474, 1, 'testTask', 'rawchen', 0, NULL, 1, '2022-05-18 22:00:00');
INSERT INTO `schedule_job_log` VALUES (475, 1, 'testTask', 'rawchen', 0, NULL, 6, '2022-05-18 22:30:00');
INSERT INTO `schedule_job_log` VALUES (476, 1, 'testTask', 'rawchen', 0, NULL, 0, '2022-05-18 23:00:00');
INSERT INTO `schedule_job_log` VALUES (477, 1, 'testTask', 'rawchen', 0, NULL, 5, '2022-05-19 11:30:00');
INSERT INTO `schedule_job_log` VALUES (478, 1, 'testTask', 'rawchen', 0, NULL, 4, '2022-05-19 12:00:00');
INSERT INTO `schedule_job_log` VALUES (479, 1, 'testTask', 'rawchen', 0, NULL, 2, '2022-05-19 12:30:00');
INSERT INTO `schedule_job_log` VALUES (480, 1, 'testTask', 'rawchen', 0, NULL, 2, '2022-05-19 13:00:00');
INSERT INTO `schedule_job_log` VALUES (481, 1, 'testTask', 'rawchen', 0, NULL, 1, '2022-05-19 13:30:00');
INSERT INTO `schedule_job_log` VALUES (482, 1, 'testTask', 'rawchen', 0, NULL, 12, '2022-05-19 14:00:00');
INSERT INTO `schedule_job_log` VALUES (483, 1, 'testTask', 'rawchen', 0, NULL, 1, '2022-05-19 14:30:00');
INSERT INTO `schedule_job_log` VALUES (484, 1, 'testTask', 'rawchen', 0, NULL, 1, '2022-05-19 15:00:00');
INSERT INTO `schedule_job_log` VALUES (485, 1, 'testTask', 'rawchen', 0, NULL, 1, '2022-05-19 15:30:00');
INSERT INTO `schedule_job_log` VALUES (486, 1, 'testTask', 'rawchen', 0, NULL, 1, '2022-05-19 16:00:00');
INSERT INTO `schedule_job_log` VALUES (487, 1, 'testTask', 'rawchen', 0, NULL, 6, '2022-05-19 16:30:00');
INSERT INTO `schedule_job_log` VALUES (488, 1, 'testTask', 'rawchen', 0, NULL, 1, '2022-05-19 17:00:00');
INSERT INTO `schedule_job_log` VALUES (489, 1, 'testTask', 'rawchen', 0, NULL, 4, '2022-05-19 17:30:00');
INSERT INTO `schedule_job_log` VALUES (490, 1, 'testTask', 'rawchen', 0, NULL, 1, '2022-05-19 18:00:00');
INSERT INTO `schedule_job_log` VALUES (491, 1, 'testTask', 'rawchen', 0, NULL, 1, '2022-05-19 18:30:00');
INSERT INTO `schedule_job_log` VALUES (492, 1, 'testTask', 'rawchen', 0, NULL, 4, '2022-05-19 19:00:00');
INSERT INTO `schedule_job_log` VALUES (493, 1, 'testTask', 'rawchen', 0, NULL, 2, '2022-05-19 19:30:00');
INSERT INTO `schedule_job_log` VALUES (494, 1, 'testTask', 'rawchen', 0, NULL, 1, '2022-05-19 20:00:00');
INSERT INTO `schedule_job_log` VALUES (495, 1, 'testTask', 'rawchen', 0, NULL, 1, '2022-05-19 20:30:00');
INSERT INTO `schedule_job_log` VALUES (496, 1, 'testTask', 'rawchen', 0, NULL, 4, '2022-05-19 21:00:00');
INSERT INTO `schedule_job_log` VALUES (497, 1, 'testTask', 'rawchen', 0, NULL, 2, '2022-05-19 21:30:00');
INSERT INTO `schedule_job_log` VALUES (498, 1, 'testTask', 'rawchen', 0, NULL, 1, '2022-05-19 22:00:00');
INSERT INTO `schedule_job_log` VALUES (499, 1, 'testTask', 'rawchen', 0, NULL, 0, '2022-05-19 22:30:00');
INSERT INTO `schedule_job_log` VALUES (500, 1, 'testTask', 'rawchen', 0, NULL, 1, '2022-05-19 23:00:00');
INSERT INTO `schedule_job_log` VALUES (501, 1, 'testTask', 'rawchen', 0, NULL, 0, '2022-05-19 23:30:00');
INSERT INTO `schedule_job_log` VALUES (502, 1, 'testTask', 'rawchen', 0, NULL, 106, '2022-05-20 00:00:00');
INSERT INTO `schedule_job_log` VALUES (503, 1, 'testTask', 'rawchen', 0, NULL, 1, '2022-05-20 11:00:00');
INSERT INTO `schedule_job_log` VALUES (504, 1, 'testTask', 'rawchen', 0, NULL, 2, '2022-05-20 11:30:00');
INSERT INTO `schedule_job_log` VALUES (505, 1, 'testTask', 'rawchen', 0, NULL, 1, '2022-05-20 12:00:00');
INSERT INTO `schedule_job_log` VALUES (506, 1, 'testTask', 'rawchen', 0, NULL, 1, '2022-05-20 12:30:00');
INSERT INTO `schedule_job_log` VALUES (507, 1, 'testTask', 'rawchen', 0, NULL, 3, '2022-05-20 13:00:00');
INSERT INTO `schedule_job_log` VALUES (508, 1, 'testTask', 'rawchen', 0, NULL, 0, '2022-05-20 13:30:00');
INSERT INTO `schedule_job_log` VALUES (509, 1, 'testTask', 'rawchen', 0, NULL, 1, '2022-05-20 14:00:00');
INSERT INTO `schedule_job_log` VALUES (510, 1, 'testTask', 'rawchen', 0, NULL, 2, '2022-05-20 14:30:00');
INSERT INTO `schedule_job_log` VALUES (511, 1, 'testTask', 'rawchen', 0, NULL, 21, '2022-05-20 15:00:00');
INSERT INTO `schedule_job_log` VALUES (512, 1, 'testTask', 'rawchen', 0, NULL, 14, '2022-05-20 15:30:00');
INSERT INTO `schedule_job_log` VALUES (513, 1, 'testTask', 'rawchen', 0, NULL, 7, '2022-05-20 16:00:00');
INSERT INTO `schedule_job_log` VALUES (514, 1, 'testTask', 'rawchen', 0, NULL, 11, '2022-05-20 16:30:00');
INSERT INTO `schedule_job_log` VALUES (515, 1, 'testTask', 'rawchen', 0, NULL, 0, '2022-05-20 17:00:00');
INSERT INTO `schedule_job_log` VALUES (516, 1, 'testTask', 'rawchen', 0, NULL, 5, '2022-05-20 17:30:00');
INSERT INTO `schedule_job_log` VALUES (517, 1, 'testTask', 'rawchen', 0, NULL, 1, '2022-05-20 18:00:00');
INSERT INTO `schedule_job_log` VALUES (518, 1, 'testTask', 'rawchen', 0, NULL, 3, '2022-05-20 18:30:00');
INSERT INTO `schedule_job_log` VALUES (519, 1, 'testTask', 'rawchen', 0, NULL, 6, '2022-05-20 19:00:00');
INSERT INTO `schedule_job_log` VALUES (520, 1, 'testTask', 'rawchen', 0, NULL, 7, '2022-05-20 19:30:00');
INSERT INTO `schedule_job_log` VALUES (521, 1, 'testTask', 'rawchen', 0, NULL, 18, '2022-05-20 20:00:00');

-- ----------------------------
-- Table structure for sys_captcha
-- ----------------------------
DROP TABLE IF EXISTS `sys_captcha`;
CREATE TABLE `sys_captcha`  (
  `uuid` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'uuid',
  `code` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '验证码',
  `expire_time` datetime(0) NULL DEFAULT NULL COMMENT '过期时间',
  PRIMARY KEY (`uuid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统验证码' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_captcha
-- ----------------------------
INSERT INTO `sys_captcha` VALUES ('027ea200-df77-4fd4-8eea-cf78f7fb2023', 'pwexg', '2020-06-04 18:34:49');
INSERT INTO `sys_captcha` VALUES ('03324fa8-36af-439e-840b-30fcad65f6b1', 'e568f', '2020-05-31 19:33:35');
INSERT INTO `sys_captcha` VALUES ('0c159600-76da-49c7-8313-872d6d2802b7', '7wyxw', '2020-06-04 12:52:51');
INSERT INTO `sys_captcha` VALUES ('0fdd65db-ae00-4803-8e48-faa91c654d88', 'mf2xx', '2022-05-13 12:03:11');
INSERT INTO `sys_captcha` VALUES ('123', '277xg', '2022-02-20 16:45:02');
INSERT INTO `sys_captcha` VALUES ('1341f11a-9044-4a17-8e10-6200c1a6c4f9', '3wym7', '2020-05-31 19:54:24');
INSERT INTO `sys_captcha` VALUES ('142702cc-b370-4ee3-83de-9c10cdefe938', 'wwdng', '2020-06-23 15:56:11');
INSERT INTO `sys_captcha` VALUES ('14fd5939-5b26-4489-8d56-c46a2e4b28f9', 'xwnp2', '2020-06-23 15:34:57');
INSERT INTO `sys_captcha` VALUES ('172c58fc-0b0e-41ab-8ef7-9bfa76ccd773', 'pdanw', '2022-04-10 16:47:26');
INSERT INTO `sys_captcha` VALUES ('1b10ff7f-d12e-4381-8cc9-79febd353970', '7n7gm', '2020-05-31 19:33:27');
INSERT INTO `sys_captcha` VALUES ('1d406841-28f0-4ad0-8042-07417a62e1fd', 'c8axn', '2020-06-04 18:34:15');
INSERT INTO `sys_captcha` VALUES ('21dc26fc-306d-4243-85e8-de8157dcf2eb', '8a8an', '2022-05-13 16:55:03');
INSERT INTO `sys_captcha` VALUES ('294b1c97-f8df-4244-88c4-47141c8d56af', 'bgcyw', '2020-07-09 13:18:19');
INSERT INTO `sys_captcha` VALUES ('2a9bd577-4d81-4cb0-8d6b-64bcc535cf5e', 'wmn7x', '2022-05-12 21:23:56');
INSERT INTO `sys_captcha` VALUES ('2d015e1f-6d24-49d5-8e10-363f4eb5a153', 'mxxg3', '2022-05-18 16:24:40');
INSERT INTO `sys_captcha` VALUES ('3c82fc6b-31f5-48a5-83a4-ceca5f598179', '6w52n', '2020-06-23 15:56:14');
INSERT INTO `sys_captcha` VALUES ('42f6f840-fc40-4e8a-8528-b79a4a2b8f2a', '654ed', '2020-06-04 18:34:33');
INSERT INTO `sys_captcha` VALUES ('43cd5131-d40b-4a0e-8ba8-f7f68df6f849', '4dem4', '2020-06-04 18:34:49');
INSERT INTO `sys_captcha` VALUES ('4c75fcbd-3a2d-4ab5-85ff-ca3a87380006', 'endnm', '2022-02-23 17:55:19');
INSERT INTO `sys_captcha` VALUES ('55779b39-e638-4a2f-8462-c20297531925', 'x3bpb', '2020-06-02 22:50:47');
INSERT INTO `sys_captcha` VALUES ('574d27c5-2441-4859-8bc9-a628240cf8d4', 'b74nf', '2022-05-18 15:57:39');
INSERT INTO `sys_captcha` VALUES ('640dbcfb-f3cd-421c-8d81-eeac7b11bb87', '27n4g', '2020-05-31 19:55:20');
INSERT INTO `sys_captcha` VALUES ('73031619-e44a-40f7-8ac8-baf960e8df6d', 'gcawe', '2020-06-04 12:55:50');
INSERT INTO `sys_captcha` VALUES ('737d045c-d156-46f9-899a-83aec5846c69', 'gdaxy', '2020-06-22 13:39:52');
INSERT INTO `sys_captcha` VALUES ('7bdfb155-9e27-446c-8e0d-89bc1ff519ed', '54dfb', '2020-05-31 20:27:33');
INSERT INTO `sys_captcha` VALUES ('9b2990ef-98c2-4865-83d3-c04286488498', 'npec2', '2020-06-03 15:05:49');
INSERT INTO `sys_captcha` VALUES ('a0c259cd-9a98-4e6f-893b-f0d203803548', 'xbm2n', '2020-06-04 18:34:49');
INSERT INTO `sys_captcha` VALUES ('a3ae36dd-0cc3-434c-870e-692785911564', 'c664y', '2020-06-13 12:31:03');
INSERT INTO `sys_captcha` VALUES ('a6819c8e-3e7e-44de-8721-0db9d915fbf1', '7n246', '2020-06-04 18:34:49');
INSERT INTO `sys_captcha` VALUES ('ac99ae8c-ec94-4c23-8cce-550570983503', 'fe7en', '2020-06-04 18:34:46');
INSERT INTO `sys_captcha` VALUES ('b8ffa0df-2964-4929-8cec-b7c20f3117fe', 'y6e73', '2020-05-31 18:04:52');
INSERT INTO `sys_captcha` VALUES ('bb4feb32-5500-4f1c-8def-193d5d3470c8', 'b5cnx', '2022-02-23 17:51:28');
INSERT INTO `sys_captcha` VALUES ('bc588733-8369-4fe0-84d3-ec536a3304a4', 'n5cbf', '2020-05-31 19:53:44');
INSERT INTO `sys_captcha` VALUES ('c2b49735-63aa-4d13-8a8f-f3f325e94f38', 'dbmb8', '2020-06-03 15:05:42');
INSERT INTO `sys_captcha` VALUES ('c79c06bf-1724-4c16-8172-b78106fb86bf', 'f8db4', '2020-06-13 11:06:43');
INSERT INTO `sys_captcha` VALUES ('dd3ab546-a671-4fcc-8b9c-11c0d6254b72', '8g5f6', '2020-06-23 13:39:27');
INSERT INTO `sys_captcha` VALUES ('e2a6d2a0-7be1-4a90-8628-18c26b5c5da4', '5yy77', '2022-02-20 20:03:20');
INSERT INTO `sys_captcha` VALUES ('e3499e80-4a73-4de0-81dd-aca5a353f7a1', 'aw44f', '2022-04-12 16:58:08');
INSERT INTO `sys_captcha` VALUES ('eca2e235-a25c-43fb-8226-10b1f14fc2e1', 'fbn6n', '2022-05-18 15:55:21');
INSERT INTO `sys_captcha` VALUES ('f35c11b4-7522-46be-8417-4dd5049f61b5', 'dpcp6', '2022-05-13 12:03:14');
INSERT INTO `sys_captcha` VALUES ('f4c57fb0-a2cb-4355-8198-3d4ba215c575', 'ancc5', '2020-06-12 18:30:33');
INSERT INTO `sys_captcha` VALUES ('f69bd42b-4f2a-4d96-8176-6701e3621b13', '6g237', '2020-05-31 19:55:17');
INSERT INTO `sys_captcha` VALUES ('f842da82-1fcf-477b-866b-2a18dcbb84ad', 'awdmg', '2022-05-18 12:11:16');
INSERT INTO `sys_captcha` VALUES ('fa4b224c-503d-4661-8bdc-50d935333347', 'w5nc6', '2022-05-18 17:32:56');
INSERT INTO `sys_captcha` VALUES ('ff8267ba-eb4b-49f1-8fe5-62188f93b8cb', 'abxcn', '2020-06-09 00:30:58');

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `param_key` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'key',
  `param_value` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'value',
  `status` tinyint(4) NULL DEFAULT 1 COMMENT '状态   0：隐藏   1：显示',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `param_key`(`param_key`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统配置信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_config
-- ----------------------------
INSERT INTO `sys_config` VALUES (1, 'CLOUD_STORAGE_CONFIG_KEY', '{\"type\":2,\"qiniuDomain\":\"http://7xqbwh.dl1.z0.glb.clouddn.com\",\"qiniuPrefix\":\"upload\",\"qiniuAccessKey\":\"NrgMfABZxWLo5B-YYSjoE8-AZ1EISdi1Z3ubLOeZ\",\"qiniuSecretKey\":\"uIwJHevMRWU0VLxFvgy0tAcOdGqasdtVlJkdy6vV\",\"qiniuBucketName\":\"ios-app\",\"aliyunDomain\":\"https://cdn.rawchen.com\",\"aliyunPrefix\":\"\",\"aliyunEndPoint\":\"oss-cn-hangzhou.aliyuncs.com\",\"aliyunAccessKeyId\":\"LTAI4FdzjYNqmtsVffdVmdBc\",\"aliyunAccessKeySecret\":\"4WedSXJ6630pxUWDc5wmHvyOQ6iNlG\",\"aliyunBucketName\":\"rawchen\",\"qcloudDomain\":\"\",\"qcloudPrefix\":\"\",\"qcloudSecretId\":\"\",\"qcloudSecretKey\":\"\",\"qcloudBucketName\":\"\"}', 0, '云存储配置信息');

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `operation` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户操作',
  `method` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求方法',
  `params` varchar(5000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求参数',
  `time` bigint(20) NOT NULL COMMENT '执行时长(毫秒)',
  `ip` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'IP地址',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统日志' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO `sys_log` VALUES (5, 'admin', '修改用户', 'io.renren.modules.sys.controller.SysUserController.update()', '[{\"userId\":2,\"username\":\"rawchen\",\"salt\":\"18lAY9Gul41yGzBkSTpT\",\"email\":\"2221999792@qq.com\",\"mobile\":\"18255555555\",\"status\":1,\"roleIdList\":[],\"createUserId\":1}]', 136, '0:0:0:0:0:0:0:1', '2022-02-23 17:52:00');
INSERT INTO `sys_log` VALUES (6, 'admin', '修改定时任务', 'io.renren.modules.job.controller.ScheduleJobController.update()', '[{\"jobId\":1,\"beanName\":\"testTask\",\"params\":\"rawchen\",\"cronExpression\":\"0 0/30 * * * ?\",\"status\":0,\"remark\":\"参数测试\"}]', 47, '0:0:0:0:0:0:0:1', '2022-02-23 17:53:14');
INSERT INTO `sys_log` VALUES (7, 'admin', '立即执行任务', 'io.renren.modules.job.controller.ScheduleJobController.run()', '[[1]]', 21, '0:0:0:0:0:0:0:1', '2022-02-23 17:53:47');
INSERT INTO `sys_log` VALUES (8, 'admin', '立即执行任务', 'io.renren.modules.job.controller.ScheduleJobController.run()', '[[1]]', 12, '0:0:0:0:0:0:0:1', '2022-02-23 17:53:58');
INSERT INTO `sys_log` VALUES (9, 'admin', '立即执行任务', 'io.renren.modules.job.controller.ScheduleJobController.run()', '[[1]]', 14, '0:0:0:0:0:0:0:1', '2022-02-23 18:06:38');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) NULL DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单URL',
  `perms` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
  `type` int(11) NULL DEFAULT NULL COMMENT '类型   0：目录   1：菜单   2：按钮',
  `icon` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单图标',
  `order_num` int(11) NULL DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 76 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '菜单管理' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, 0, '系统管理', NULL, NULL, 0, 'system', 0);
INSERT INTO `sys_menu` VALUES (2, 1, '管理员列表', 'sys/user', NULL, 1, 'admin', 1);
INSERT INTO `sys_menu` VALUES (3, 1, '角色管理', 'sys/role', NULL, 1, 'role', 2);
INSERT INTO `sys_menu` VALUES (4, 1, '菜单管理', 'sys/menu', NULL, 1, 'menu', 3);
INSERT INTO `sys_menu` VALUES (5, 1, 'SQL监控', 'http://localhost:8080/renren-fast/druid/sql.html', NULL, 1, 'sql', 4);
INSERT INTO `sys_menu` VALUES (6, 1, '定时任务', 'job/schedule', NULL, 1, 'job', 5);
INSERT INTO `sys_menu` VALUES (7, 6, '查看', NULL, 'sys:schedule:list,sys:schedule:info', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (8, 6, '新增', NULL, 'sys:schedule:save', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (9, 6, '修改', NULL, 'sys:schedule:update', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (10, 6, '删除', NULL, 'sys:schedule:delete', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (11, 6, '暂停', NULL, 'sys:schedule:pause', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (12, 6, '恢复', NULL, 'sys:schedule:resume', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (13, 6, '立即执行', NULL, 'sys:schedule:run', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (14, 6, '日志列表', NULL, 'sys:schedule:log', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (15, 2, '查看', NULL, 'sys:user:list,sys:user:info', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (16, 2, '新增', NULL, 'sys:user:save,sys:role:select', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (17, 2, '修改', NULL, 'sys:user:update,sys:role:select', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (18, 2, '删除', NULL, 'sys:user:delete', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (19, 3, '查看', NULL, 'sys:role:list,sys:role:info', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (20, 3, '新增', NULL, 'sys:role:save,sys:menu:list', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (21, 3, '修改', NULL, 'sys:role:update,sys:menu:list', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (22, 3, '删除', NULL, 'sys:role:delete', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (23, 4, '查看', NULL, 'sys:menu:list,sys:menu:info', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (24, 4, '新增', NULL, 'sys:menu:save,sys:menu:select', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (25, 4, '修改', NULL, 'sys:menu:update,sys:menu:select', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (26, 4, '删除', NULL, 'sys:menu:delete', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (27, 1, '参数管理', 'sys/config', 'sys:config:list,sys:config:info,sys:config:save,sys:config:update,sys:config:delete', 1, 'config', 6);
INSERT INTO `sys_menu` VALUES (29, 1, '系统日志', 'sys/log', 'sys:log:list', 1, 'log', 7);
INSERT INTO `sys_menu` VALUES (30, 1, '文件上传', 'oss/oss', 'sys:oss:all', 1, 'oss', 6);
INSERT INTO `sys_menu` VALUES (31, 0, '商品系统', '', '', 0, 'editor', 0);
INSERT INTO `sys_menu` VALUES (32, 31, '分类维护', 'product/category', '', 1, 'menu', 0);
INSERT INTO `sys_menu` VALUES (34, 31, '品牌管理', 'product/brand', '', 1, 'editor', 0);
INSERT INTO `sys_menu` VALUES (37, 31, '平台属性', '', '', 0, 'system', 0);
INSERT INTO `sys_menu` VALUES (38, 37, '属性分组', 'product/attrgroup', '', 1, 'tubiao', 0);
INSERT INTO `sys_menu` VALUES (39, 37, '规格参数', 'product/baseattr', '', 1, 'log', 0);
INSERT INTO `sys_menu` VALUES (40, 37, '销售属性', 'product/saleattr', '', 1, 'zonghe', 0);
INSERT INTO `sys_menu` VALUES (41, 31, '商品维护', 'product/spu', '', 0, 'zonghe', 0);
INSERT INTO `sys_menu` VALUES (42, 0, '优惠营销', '', '', 0, 'mudedi', 0);
INSERT INTO `sys_menu` VALUES (43, 0, '库存系统', '', '', 0, 'shouye', 0);
INSERT INTO `sys_menu` VALUES (44, 0, '订单系统', '', '', 0, 'config', 0);
INSERT INTO `sys_menu` VALUES (45, 0, '用户系统', '', '', 0, 'admin', 0);
INSERT INTO `sys_menu` VALUES (46, 0, '内容管理', '', '', 0, 'sousuo', 0);
INSERT INTO `sys_menu` VALUES (47, 42, '优惠券管理', 'coupon/coupon', '', 1, 'zhedie', 0);
INSERT INTO `sys_menu` VALUES (48, 42, '发放记录', 'coupon/history', '', 1, 'sql', 0);
INSERT INTO `sys_menu` VALUES (49, 42, '专题活动', 'coupon/subject', '', 1, 'tixing', 0);
INSERT INTO `sys_menu` VALUES (50, 42, '秒杀活动', 'coupon/seckill', '', 1, 'daohang', 0);
INSERT INTO `sys_menu` VALUES (51, 42, '积分维护', 'coupon/bounds', '', 1, 'geren', 0);
INSERT INTO `sys_menu` VALUES (52, 42, '满减折扣', 'coupon/full', '', 1, 'shoucang', 0);
INSERT INTO `sys_menu` VALUES (53, 43, '仓库维护', 'ware/wareinfo', '', 1, 'shouye', 0);
INSERT INTO `sys_menu` VALUES (54, 43, '库存工作单', 'ware/task', '', 1, 'log', 0);
INSERT INTO `sys_menu` VALUES (55, 43, '商品库存', 'ware/sku', '', 1, 'jiesuo', 0);
INSERT INTO `sys_menu` VALUES (56, 44, '订单查询', 'order/order', '', 1, 'zhedie', 0);
INSERT INTO `sys_menu` VALUES (57, 44, '退货单处理', 'order/return', '', 1, 'shanchu', 0);
INSERT INTO `sys_menu` VALUES (58, 44, '等级规则', 'order/settings', '', 1, 'system', 0);
INSERT INTO `sys_menu` VALUES (59, 44, '支付流水查询', 'order/payment', '', 1, 'job', 0);
INSERT INTO `sys_menu` VALUES (60, 44, '退款流水查询', 'order/refund', '', 1, 'mudedi', 0);
INSERT INTO `sys_menu` VALUES (61, 45, '会员列表', 'member/member', '', 1, 'geren', 0);
INSERT INTO `sys_menu` VALUES (62, 45, '会员等级', 'member/level', '', 1, 'tubiao', 0);
INSERT INTO `sys_menu` VALUES (63, 45, '积分变化', 'member/growth', '', 1, 'bianji', 0);
INSERT INTO `sys_menu` VALUES (64, 45, '统计信息', 'member/statistics', '', 1, 'sql', 0);
INSERT INTO `sys_menu` VALUES (65, 46, '首页推荐', 'content/index', '', 1, 'shouye', 0);
INSERT INTO `sys_menu` VALUES (66, 46, '分类热门', 'content/category', '', 1, 'zhedie', 0);
INSERT INTO `sys_menu` VALUES (67, 46, '评论管理', 'content/comments', '', 1, 'pinglun', 0);
INSERT INTO `sys_menu` VALUES (68, 41, 'spu管理', 'product/spu', '', 1, 'config', 0);
INSERT INTO `sys_menu` VALUES (69, 41, '发布商品', 'product/spuadd', '', 1, 'bianji', 0);
INSERT INTO `sys_menu` VALUES (70, 43, '采购单维护', '', '', 0, 'tubiao', 0);
INSERT INTO `sys_menu` VALUES (71, 70, '采购需求', 'ware/purchaseitem', '', 1, 'editor', 0);
INSERT INTO `sys_menu` VALUES (72, 70, '采购单', 'ware/purchase', '', 1, 'menu', 0);
INSERT INTO `sys_menu` VALUES (73, 41, '商品管理', 'product/manager', '', 1, 'zonghe', 0);
INSERT INTO `sys_menu` VALUES (74, 42, '会员价格', 'coupon/memberprice', '', 1, 'admin', 0);
INSERT INTO `sys_menu` VALUES (75, 42, '每日秒杀', 'coupon/seckillsession', '', 1, 'job', 0);

-- ----------------------------
-- Table structure for sys_oss
-- ----------------------------
DROP TABLE IF EXISTS `sys_oss`;
CREATE TABLE `sys_oss`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `url` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'URL地址',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '文件上传' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_oss
-- ----------------------------
INSERT INTO `sys_oss` VALUES (3, 'https://cdn.rawchen.com/20220311/74dfe987d4d745b98315b49a1bb234e9.jpg', '2022-03-11 01:11:27');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `remark` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_user_id` bigint(20) NULL DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) NULL DEFAULT NULL COMMENT '角色ID',
  `menu_id` bigint(20) NULL DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色与菜单对应关系' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
  `salt` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '盐',
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `status` tinyint(4) NULL DEFAULT NULL COMMENT '状态  0：禁用   1：正常',
  `create_user_id` bigint(20) NULL DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统用户' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', '9ec9750e709431dad22365cabc5c625482e574c74adaebba7dd02f1129e4ce1d', 'YzcmCZNvbXocrsz9dm8e', '1046762075@qq.com', '18173516309', 1, 1, '2022-01-04 05:20:00');
INSERT INTO `sys_user` VALUES (2, 'rawchen', 'f35b148a296ded29376259f9e7e57650a606be557973c67e7e6807a612d3fdfd', '18lAY9Gul41yGzBkSTpT', '2221999792@qq.com', '18255555555', 1, 1, '2022-01-06 23:17:16');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户ID',
  `role_id` bigint(20) NULL DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户与角色对应关系' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user_token
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_token`;
CREATE TABLE `sys_user_token`  (
  `user_id` bigint(20) NOT NULL,
  `token` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'token',
  `expire_time` datetime(0) NULL DEFAULT NULL COMMENT '过期时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `token`(`token`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统用户Token' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user_token
-- ----------------------------
INSERT INTO `sys_user_token` VALUES (1, 'ecec6800c1f8bf46716bb06432018887', '2022-05-20 23:05:19', '2022-05-20 11:05:19');

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user`  (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `mobile` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '手机号',
  `password` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES (1, 'mark', '13612345678', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', '2017-03-23 22:37:41');

-- ----------------------------
-- Table structure for undo_log
-- ----------------------------
DROP TABLE IF EXISTS `undo_log`;
CREATE TABLE `undo_log`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `branch_id` bigint(20) NOT NULL,
  `xid` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `context` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `rollback_info` longblob NOT NULL,
  `log_status` int(11) NOT NULL,
  `log_created` datetime(0) NOT NULL,
  `log_modified` datetime(0) NOT NULL,
  `ext` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `ux_undo_log`(`xid`, `branch_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of undo_log
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
