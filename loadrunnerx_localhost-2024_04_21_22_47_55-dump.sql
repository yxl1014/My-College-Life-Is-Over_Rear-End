-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: loadrunnerx
-- ------------------------------------------------------
-- Server version	8.0.36

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `sys_announcement`
--

DROP TABLE IF EXISTS `sys_announcement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_announcement` (
  `anno_id` varchar(135) NOT NULL COMMENT '公告单号 包含创建时间 UUID',
  `anno_publisher_id` varchar(135) NOT NULL COMMENT '发布者ID',
  `anno_create_time` datetime NOT NULL COMMENT '创建时间',
  `anno_publish_time` datetime DEFAULT NULL COMMENT '发布时间',
  `anno_expire_time` datetime DEFAULT NULL COMMENT '过期时间',
  `anno_title` varchar(130) NOT NULL COMMENT '公告标题',
  `anno_content_body` varchar(5000) NOT NULL COMMENT '公告内容',
  `anno_priority` tinyint NOT NULL COMMENT '公告显示优先级: 0代表最普通 1代表vip  2代表s_vip 3代表ss_vip',
  `anno_link` varchar(255) DEFAULT NULL COMMENT '可选的公告链接',
  PRIMARY KEY (`anno_id`),
  KEY `anno_publisher_id` (`anno_publisher_id`),
  CONSTRAINT `sys_announcement_ibfk_1` FOREIGN KEY (`anno_publisher_id`) REFERENCES `sys_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='公告单';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_announcement`
--

LOCK TABLES `sys_announcement` WRITE;
/*!40000 ALTER TABLE `sys_announcement` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_announcement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_mail_inner`
--

DROP TABLE IF EXISTS `sys_mail_inner`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_mail_inner` (
  `mail_id` varchar(135) NOT NULL COMMENT 'javaUUID生成共有135位 包含有发送时间',
  `mail_sender_id` varchar(135) NOT NULL COMMENT '发件人的用户ID',
  `mail_receiver_id` varchar(135) NOT NULL COMMENT '收件人的用户ID',
  `mail_subject` varchar(255) NOT NULL COMMENT '邮件主题',
  `mail_message` varchar(5000) NOT NULL COMMENT '邮件内容',
  `mail_attachment` json DEFAULT NULL COMMENT '附件信息（可以存储物品、金币等）',
  `mail_sent_date` datetime NOT NULL COMMENT '发送日期和时间  用此时间来做排序',
  `mail_type` tinyint NOT NULL COMMENT '0系统邮件 1好友邮件(如王者中送砖石金币等信息) 3消息记录(如王者中组队邀请) 4礼物记录(如王者中赠送皮肤) 5感谢信(如王者中礼物记录里面的感谢信板块)',
  `mail_status` tinyint NOT NULL COMMENT '-1已删除 0未读 1已读',
  PRIMARY KEY (`mail_id`),
  KEY `mail_sender_id` (`mail_sender_id`),
  KEY `mail_receiver_id` (`mail_receiver_id`),
  CONSTRAINT `sys_mail_inner_ibfk_1` FOREIGN KEY (`mail_sender_id`) REFERENCES `sys_user` (`user_id`),
  CONSTRAINT `sys_mail_inner_ibfk_2` FOREIGN KEY (`mail_receiver_id`) REFERENCES `sys_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='邮件表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_mail_inner`
--

LOCK TABLES `sys_mail_inner` WRITE;
/*!40000 ALTER TABLE `sys_mail_inner` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_mail_inner` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_power`
--

DROP TABLE IF EXISTS `sys_power`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_power` (
  `power_id` int NOT NULL AUTO_INCREMENT COMMENT '权限ID 200位 两百种权限',
  `power_name` varchar(30) NOT NULL COMMENT '权限名称',
  `power_type` tinyint NOT NULL COMMENT '权限类型 1个字节 8位 8种类型 比如1000 0000是可操作 0100 0000是可读',
  `power_create_time` datetime DEFAULT NULL COMMENT '创建时间 用于排序',
  `power_notes` varchar(500) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`power_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1060 DEFAULT CHARSET=utf8mb3 COMMENT='权限表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_power`
--

LOCK TABLES `sys_power` WRITE;
/*!40000 ALTER TABLE `sys_power` DISABLE KEYS */;
INSERT INTO `sys_power` VALUES (1059,'角色管理',0,'2024-02-05 14:48:13','');
/*!40000 ALTER TABLE `sys_power` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role`
--

DROP TABLE IF EXISTS `sys_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_role` (
  `role_id` smallint NOT NULL AUTO_INCREMENT COMMENT '角色ID 2个字节 32位 32种角色 ',
  `role_name` varchar(30) NOT NULL COMMENT '角色名称',
  `role_create_time` datetime NOT NULL COMMENT '角色创建时间用来排序',
  `role_status_flag` tinyint(1) NOT NULL COMMENT '角色状态（0正常 1停用比如需要patch 2删除）',
  `role_remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`role_id`),
  UNIQUE KEY `role_create_time` (`role_create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role`
--

LOCK TABLES `sys_role` WRITE;
/*!40000 ALTER TABLE `sys_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role_power_ref`
--

DROP TABLE IF EXISTS `sys_role_power_ref`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_role_power_ref` (
  `ref_role_id` smallint NOT NULL COMMENT '角色ID',
  `ref_power_id` int NOT NULL COMMENT '权限ID',
  PRIMARY KEY (`ref_role_id`,`ref_power_id`),
  KEY `sys_role_power_sys_power_power_id_fk` (`ref_power_id`),
  CONSTRAINT `sys_role_power_sys_power_power_id_fk` FOREIGN KEY (`ref_power_id`) REFERENCES `sys_power` (`power_id`),
  CONSTRAINT `sys_role_power_sys_role_role_id_fk` FOREIGN KEY (`ref_role_id`) REFERENCES `sys_role` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='角色和权限关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role_power_ref`
--

LOCK TABLES `sys_role_power_ref` WRITE;
/*!40000 ALTER TABLE `sys_role_power_ref` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_role_power_ref` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_task`
--

DROP TABLE IF EXISTS `sys_task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_task` (
  `task_id` varchar(135) NOT NULL,
  `task_author_id` varchar(135) NOT NULL COMMENT '发布者ID',
  `task_name` varchar(200) DEFAULT NULL COMMENT '任务名',
  `task_money` double NOT NULL DEFAULT '0' COMMENT '报酬',
  `task_create_time` bigint NOT NULL COMMENT '发布时间',
  `task_type` int NOT NULL DEFAULT '0' COMMENT '（0、一直发，1、某个时间点发，2、间隔时间发）',
  `task_shell` mediumtext COMMENT '脚本（Json序列化后的List<TaskShell>）',
  `task_state` int DEFAULT NULL COMMENT '状态 (0、规划中，1、报名中，2、测试中，3、暂停，4、结束)',
  `task_start_time` bigint DEFAULT NULL,
  `task_end_time` bigint DEFAULT NULL,
  `task_test_time` bigint DEFAULT NULL COMMENT '任务持续时间',
  `task_test_result` mediumtext COMMENT '任务测试结果',
  PRIMARY KEY (`task_id`),
  KEY `sys_task_task_author_id_index` (`task_author_id`),
  KEY `sys_task_task_state_index` (`task_state`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_task`
--

LOCK TABLES `sys_task` WRITE;
/*!40000 ALTER TABLE `sys_task` DISABLE KEYS */;
INSERT INTO `sys_task` VALUES ('0000018f-0111-4370-91c0-29b2040c53f5','0000018e-5bb6-bb16-9651-ffc19428eaa7',NULL,256.1,1713709859697,1,NULL,0,NULL,NULL,NULL,NULL),('0000018f-0118-030c-b7b9-b7c5a7da27e7','0000018e-5bb6-bb16-9651-ffc19428eaa7','TestTask918385',256.1,1713710301964,1,NULL,0,NULL,NULL,NULL,NULL),('0000018f-0118-116e-b8c1-07f32d7fd622','0000018e-5bb6-bb16-9651-ffc19428eaa7','TestTask809598',256.1,1713710305646,1,NULL,1,NULL,NULL,NULL,NULL),('0000018f-0118-92f7-b9b1-f3f1c16b85ca','0000018e-5bb6-bb16-9651-ffc19428eaa7','TestTask628748',256.1,1713710338807,1,NULL,0,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `sys_task` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_task_user_ref`
--

DROP TABLE IF EXISTS `sys_task_user_ref`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_task_user_ref` (
  `ref_id` int NOT NULL AUTO_INCREMENT,
  `ref_user_id` varchar(135) NOT NULL,
  `ref_task_id` varchar(135) NOT NULL,
  `ref_all_req` bigint DEFAULT '0',
  `ref_success_req` bigint DEFAULT '0',
  `ref_start_time` bigint DEFAULT NULL,
  `ref_end_time` bigint DEFAULT NULL,
  `ref_test_time` bigint DEFAULT NULL,
  `ref_state` int DEFAULT '0' COMMENT '（0、等待中，1、测试中，2、暂停，3、结束测试）',
  PRIMARY KEY (`ref_id`),
  KEY `sys_task_user_ref_8_ref_state_index` (`ref_state`),
  KEY `sys_task_user_ref_8_ref_task_id_index` (`ref_task_id`),
  KEY `sys_task_user_ref_8_ref_user_id_index` (`ref_user_id`),
  KEY `sys_task_user_ref_8_ref_user_id_ref_task_id_index` (`ref_user_id`,`ref_task_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_task_user_ref`
--

LOCK TABLES `sys_task_user_ref` WRITE;
/*!40000 ALTER TABLE `sys_task_user_ref` DISABLE KEYS */;
INSERT INTO `sys_task_user_ref` VALUES (1,'0000018f-0108-7762-9aac-b108e377a276','0000018f-0118-116e-b8c1-07f32d7fd622',0,0,0,0,0,0);
/*!40000 ALTER TABLE `sys_task_user_ref` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_transaction_record`
--

DROP TABLE IF EXISTS `sys_transaction_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_transaction_record` (
  `transaction_id` varchar(135) NOT NULL COMMENT '交易单号唯一标识符号 UUID生成 包含交易时间',
  `sender_id` varchar(135) NOT NULL COMMENT '发款方ID',
  `receiver_id` varchar(135) NOT NULL COMMENT '收款方ID',
  `changed_money` double NOT NULL COMMENT '变动金额 单位RMB',
  `transaction_time` datetime NOT NULL COMMENT '交易时间',
  `rest_money` double NOT NULL COMMENT '余额 单位RMB',
  `pay_way` varchar(40) NOT NULL COMMENT '支付来源：余额 zfb  wx  bankCard 管理员充值',
  `transaction_type` varchar(30) NOT NULL COMMENT '交易类型：充值 消费 转账 提现',
  `transaction_state` enum('pending','success','failure') NOT NULL COMMENT '交易状态 ：正处理 成功 失败 ',
  `transaction_notes` varchar(500) DEFAULT NULL COMMENT '交易备注：如：来自老赵给你的农机租赁款',
  PRIMARY KEY (`transaction_id`),
  KEY `sender_id` (`sender_id`),
  KEY `receiver_id` (`receiver_id`),
  CONSTRAINT `sys_transaction_record_ibfk_1` FOREIGN KEY (`sender_id`) REFERENCES `sys_user` (`user_id`),
  CONSTRAINT `sys_transaction_record_ibfk_2` FOREIGN KEY (`receiver_id`) REFERENCES `sys_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='交易记录';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_transaction_record`
--

LOCK TABLES `sys_transaction_record` WRITE;
/*!40000 ALTER TABLE `sys_transaction_record` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_transaction_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user`
--

DROP TABLE IF EXISTS `sys_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_user` (
  `user_id` varchar(135) NOT NULL COMMENT '不作为用户登录的id 只是唯一标识用户id 包含有用户建立账号时间,javaUUID类生成的UUID为128位 多7位扩展',
  `user_name` varchar(25) NOT NULL COMMENT '用户用户名 邮箱 电话都可作为用户账号登录',
  `user_telephone` varchar(18) DEFAULT NULL COMMENT '用户电话:11位纯数字  电话必须填写',
  `user_sys_email` varchar(25) DEFAULT NULL COMMENT '用户邮箱:xxx@qq.com 邮箱可不填写 不通过邮箱登录',
  `user_password` varchar(160) NOT NULL COMMENT '用户密码 20位英文+字符+数字组合  经过hash过后的密码长度为149个字节',
  `user_nick_name` varchar(40) DEFAULT NULL COMMENT '用户昵称 最多40个字符',
  `user_gender` varchar(10) DEFAULT NULL COMMENT '用户性别：男 女',
  `user_born_day` date DEFAULT NULL COMMENT '用户出生年月日: 2000/12/1',
  `user_id_card` varchar(20) DEFAULT NULL COMMENT '用户身份证号:18位',
  `user_money` double DEFAULT '0' COMMENT '用户余额 ',
  `user_company` varchar(100) DEFAULT NULL COMMENT '用户单位',
  `user_home` varchar(100) DEFAULT NULL COMMENT '用户住址',
  `user_ip` varchar(20) DEFAULT NULL COMMENT '用户IP',
  `user_flag` tinyint NOT NULL DEFAULT '0' COMMENT '0000 0000 首位是1:1xxx xxxx代表已删除  第二位是1:x1xx xxxx代表停用 后六位：xx 000000代表管理员  000001普通用户 000010VIP用户',
  `user_personal_profile` varchar(500) DEFAULT NULL COMMENT '用户个人简介',
  `user_create_time` datetime NOT NULL COMMENT '用户账号的创立日期 用此字段给user们排序呈现 并作为UUID生成的一个参数',
  `user_sec_problem_one` varchar(100) DEFAULT NULL COMMENT '密保问题1  鉴于密保问题都是10几个字20个字左右的问题 unicode编码一个汉字三个字节，就用100个字符varchar',
  `user_sec_answer_one` varchar(60) DEFAULT NULL COMMENT '密保问题1答案 答案一般都比较简短10几个字左右 就用varchar60',
  `user_sec_problem_two` varchar(100) DEFAULT NULL COMMENT '密保问题2',
  `user_sec_answer_two` varchar(60) DEFAULT NULL COMMENT '密保问题2答案',
  `user_sec_problem_three` varchar(100) DEFAULT NULL COMMENT '密保问题3',
  `user_sec_answer_three` varchar(60) DEFAULT NULL COMMENT '密保问题3答案',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_name` (`user_name`),
  UNIQUE KEY `user_telephone` (`user_telephone`),
  UNIQUE KEY `user_sys_email` (`user_sys_email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user`
--

LOCK TABLES `sys_user` WRITE;
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;
INSERT INTO `sys_user` VALUES ('0000018c-15ca-9551-815c-8f276e84cbf7','HammerRay','18666666666','nnn.aaa@kkk.edu.cn','Wy0xMTYsIC0xMTgsIDI1LCAtMzksIDg1LCAtMzksIC0xMjcsIC0xMTAsIDU0LCA2MSwgLTEwMCwgLTEsIDg3LCAtNjgsIDQ4LCA1Ml0=:6M8IIkdVpE2gLfH3ktvKWCHw66W2i9qQu57C30OdJGE=','HammerRay','男','1970-01-01','410522333355558888',NULL,'tute','河南','192.168.56.1',0,'66666666666666','2023-11-28 19:57:27','你在哪上学？','tute','你的名字？','zy','你的学号','0304210226'),('0000018c-24aa-c0e8-86b5-7950b37cd707','John',NULL,NULL,'Wy0xMTYsIC0xMTgsIDI1LCAtMzksIDg1LCAtMzksIC0xMjcsIC0xMTAsIDU0LCA2MSwgLTEwMCwgLTEsIDg3LCAtNjgsIDQ4LCA1Ml0=:QhWaoLYX/Cy602Xt+7S3HhR7KKs6Aco/efIylzxSJqU=',NULL,NULL,'2003-09-10',NULL,NULL,NULL,NULL,NULL,0,NULL,'2023-12-01 17:16:59',NULL,NULL,NULL,NULL,NULL,NULL),('0000018c-24de-fba3-8c72-be182a4d8055','zhangsan1',NULL,NULL,'Wy0xMTYsIC0xMTgsIDI1LCAtMzksIDg1LCAtMzksIC0xMjcsIC0xMTAsIDU0LCA2MSwgLTEwMCwgLTEsIDg3LCAtNjgsIDQ4LCA1Ml0=:Y2/TzAFnqYDlnJRO33LMre0iTpsqEA+9gZS0Fj6xfUM=',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,'2023-12-01 18:14:02','1+1','2','2+2','4',NULL,NULL),('0000018c-251b-4676-bd25-17bad9ba3899','zhangsan2','13333333333',NULL,'Wy0xMTYsIC0xMTgsIDI1LCAtMzksIDg1LCAtMzksIC0xMjcsIC0xMTAsIDU0LCA2MSwgLTEwMCwgLTEsIDg3LCAtNjgsIDQ4LCA1Ml0=:fqZz5s/8MZsfjluPlnh7ZLX9itHw/ed7ZXngHJFhRMc=',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,'2023-12-01 19:19:54','1+1','2','2+2','4',NULL,NULL),('0000018c-251c-61fa-afdc-bf912500d0f0','zhangsan3',NULL,'133@qq.com','Wy0xMTYsIC0xMTgsIDI1LCAtMzksIDg1LCAtMzksIC0xMjcsIC0xMTAsIDU0LCA2MSwgLTEwMCwgLTEsIDg3LCAtNjgsIDQ4LCA1Ml0=:fqZz5s/8MZsfjluPlnh7ZLX9itHw/ed7ZXngHJFhRMc=',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,'2023-12-01 19:21:06','1+1','2','2+2','4',NULL,NULL),('0000018e-5bb6-bb16-9651-ffc19428eaa7','yxl1014','17551097327',NULL,'Wy0xMTYsIC0xMTgsIDI1LCAtMzksIDg1LCAtMzksIC0xMjcsIC0xMTAsIDU0LCA2MSwgLTEwMCwgLTEsIDg3LCAtNjgsIDQ4LCA1Ml0=:zWKf2mIyGC8D7XtkBzZUR5WNECDstOIWI6CDcOqKzkc=',NULL,NULL,NULL,NULL,98975.59999999998,NULL,NULL,NULL,1,NULL,'2024-03-20 19:54:46',NULL,NULL,NULL,NULL,NULL,NULL),('0000018f-0108-7762-9aac-b108e377a276','test','13340744119',NULL,'Wy0xMTYsIC0xMTgsIDI1LCAtMzksIDg1LCAtMzksIC0xMjcsIC0xMTAsIDU0LCA2MSwgLTEwMCwgLTEsIDg3LCAtNjgsIDQ4LCA1Ml0=:zWKf2mIyGC8D7XtkBzZUR5WNECDstOIWI6CDcOqKzkc=',NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,0,NULL,'2024-04-21 22:21:23',NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user_role_ref`
--

DROP TABLE IF EXISTS `sys_user_role_ref`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_user_role_ref` (
  `ref_user_id` varchar(135) NOT NULL COMMENT '用户ID',
  `ref_role_id` smallint NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`ref_user_id`,`ref_role_id`),
  KEY `sys_user_role_sys_role_id_fk` (`ref_role_id`),
  CONSTRAINT `sys_user_role_sys_role_id_fk` FOREIGN KEY (`ref_role_id`) REFERENCES `sys_role` (`role_id`),
  CONSTRAINT `sys_user_role_sys_user_id_fk` FOREIGN KEY (`ref_user_id`) REFERENCES `sys_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='用户和角色关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user_role_ref`
--

LOCK TABLES `sys_user_role_ref` WRITE;
/*!40000 ALTER TABLE `sys_user_role_ref` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_user_role_ref` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-04-21 22:47:55
