/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.0.106-test
 Source Server Type    : Oracle
 Source Server Version : 110200
 Source Host           : 192.168.0.106:49161
 Source Schema         : TEST

 Target Server Type    : Oracle
 Target Server Version : 110200
 File Encoding         : 65001

 Date: 08/02/2020 22:27:40
*/


-- ----------------------------
-- Table structure for RESULT
-- ----------------------------
DROP TABLE "TEST"."RESULT";
CREATE TABLE "TEST"."RESULT" (
  "ID" NUMBER NOT NULL ,
  "SUBJECT" VARCHAR2(255 BYTE) ,
  "SCORE" NUMBER 
)
TABLESPACE "TEST"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;

-- ----------------------------
-- Records of RESULT
-- ----------------------------
INSERT INTO "TEST"."RESULT" VALUES ('1', 'english', '90');

-- ----------------------------
-- Checks structure for table RESULT
-- ----------------------------
ALTER TABLE "TEST"."RESULT" ADD CONSTRAINT "SYS_C007001" CHECK ("ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
