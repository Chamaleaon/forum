-- MySQL dump 10.13  Distrib 8.0.21, for Win64 (x86_64)
--
-- Host: localhost    Database: forum
-- ------------------------------------------------------
-- Server version	8.0.21

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `essay`
--

DROP TABLE IF EXISTS `essay`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `essay` (
  `e_id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(128) DEFAULT NULL,
  `content` varchar(2048) DEFAULT NULL,
  `creation_time` varchar(64) DEFAULT NULL,
  `update_time` varchar(64) DEFAULT NULL,
  `publisher` int DEFAULT NULL,
  `label` varchar(10) DEFAULT NULL,
  `type` varchar(10) DEFAULT 'ESSAY',
  `info` varchar(2048) DEFAULT '{}',
  `privacy` varchar(10) DEFAULT 'PUBLIC',
  `support` int DEFAULT '0',
  `oppose` int DEFAULT '0',
  `comment` int DEFAULT '0',
  `browse` int DEFAULT '0',
  PRIMARY KEY (`e_id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `essay`
--

LOCK TABLES `essay` WRITE;
/*!40000 ALTER TABLE `essay` DISABLE KEYS */;
INSERT INTO `essay` VALUES (17,'Lorem','  Lorem ipsum dolor sit amet, consectetur adipisicing elit. Cumque labore accusamus enim vitae porro voluptate sit totam mollitia voluptatibus accusantium ducimus quis, maxime, provident quod soluta similique! Odio, maxime explicabo.','2020-09-08 19:45:26','2020-09-08 21:05:18',4,'test','ESSAY','{}','PUBLIC',0,0,0,0),(28,'苹果推送IOS 14正式版，谁动了你的隐私一目了然','不知道大家有没有和我一样的想法','2020-09-17 20:11:28','2020-09-17 20:11:28',2,'测试','ESSAY','{}','PUBLIC',0,0,0,0),(29,'消息称Uber正寻求出售所持滴滴部分股权','9月17日消息，据外媒报道，美国网约车巨头Uber正寻求出售部分所持的价值63亿美元的滴滴出行股权，以提高本公司股价。','2020-09-17 20:29:38','2020-09-17 20:29:38',2,'测试','ESSAY','{}','PUBLIC',0,0,0,0),(30,'张勇谈阿里新制造平台 “犀牛智造”：解决库存风险和资源浪费经年已久的问题','9 月 17 日晚间消息，世界经济论坛在线上举办首届 “全球灯塔网络”年会，世界经济论坛执行董事 Jeremy Jurgens 与阿里巴巴集团董事会主席兼首席执行官张勇对话，探讨全球制造业和供应链如何在挑战之下重塑新模式。对话中，张勇表达了他对新制造的观点：“我们做新制造的起点是客户需求”。  马云在四年前提出了五新战略，新制造作为其中之一。其背景是数字技术对传统制造业的深度重构，实现制造业的智能化、个性化和定制化。阿里巴巴将新制造的探索首先放在服装行业，9 月 16 日，其孵化 3 年的新制造平台 “犀牛智造”揭开面纱，作为专门为中小企业服务的数字化智能化制造平台，犀牛智造大量应用云计算、IoT、人工智能技术，其团队 80% 为工程师。阿里巴巴方面介绍，它跑通了小单起订、快速反应的柔性制造模式，实现了按需生产，在投产之前已有 200 多家淘宝中小商家试点合作。','2020-09-17 21:17:30','2020-09-17 21:17:30',9,'测试','ESSAY','{}','PUBLIC',0,0,0,0),(31,'比尔 · 盖茨反对芯片不卖给中国：这样真的有好处吗？','T之家 9 月 17 日消息 比尔 · 盖茨近日在接受彭博采访时表示，中美关系彼此受益。美国过去曾想卖昂贵的芯片创造高薪工作，现在强迫中国自己制造芯片，意味着将来不仅高薪工作没了，而且使中国完全自给自足。这样真的有好处吗？','2020-09-17 21:18:41','2020-09-17 21:18:41',9,'测试','ESSAY','{}','PUBLIC',0,0,0,0),(32,'自如回应房源甲醛超标：系原精装修房源，正与租客协商补偿','9 月 17 日晚间消息，近日，有媒体对南京的陈女士租住南京自如房源甲醛超标仅获赔 3 天租金事件进行了报道。  南京自如负责人表示，事件房源系原精装修房源，并非由南京自如装修。虽然房源并非公司装修，但前期媒体报道的赔付方案确实不符合公司规定和客户为先的价值观，会进一步核实产品品质，与租客诚恳沟通合理解决方案。  据悉，陈女士在租住期间，对房子里的空气质量产生了疑虑，并自行委托第三方检测机构进行检测，结果显示房源小卧室的甲醛和总挥发性有机物 TVOC 超出国家标准。对此，陈女士向南京自如方面提出赔偿申请，一位南京自如工作人员提出 “检测费退一半，租金退 3 天”的赔付方案，陈女士对于该工作人员的赔付方案表示无法接受。  南京自如相关负责人表示，公司一直坚持客户为先，租客关于租住相关问题的投诉，公司会在最大程度上尊重租客需求。而此前媒体报道的处理方案，与公司的相关规定及客户为先的价值观要求不相符。“非常抱歉给租客带来的不良体验，目前，我们正在与租客进行诚恳沟通，寻求合理解决方案。” 该负责人说。  同时，据了解，陈女士租住的是一套两居室整租，该房屋交付时为精装修房源，并非由南京自如进行装修。  该负责人表示，“虽然该房源并非由自如进行装修，但是我们也会积极加强产品质量管控，保障租客租住体验，同时积极跟进后续事宜”。','2020-09-17 21:57:59','2020-09-17 21:57:59',10,'测试','ESSAY','{}','PUBLIC',0,0,0,0);
/*!40000 ALTER TABLE `essay` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `floor`
--

DROP TABLE IF EXISTS `floor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `floor` (
  `f_id` int NOT NULL AUTO_INCREMENT,
  `essay` int DEFAULT NULL,
  `content` varchar(2048) DEFAULT NULL,
  `publisher` int DEFAULT NULL,
  `creation_time` varchar(64) DEFAULT NULL,
  `update_time` varchar(64) DEFAULT NULL,
  `level` int DEFAULT NULL,
  `info` varchar(2048) DEFAULT '{}',
  `support` int DEFAULT '0',
  `oppose` int DEFAULT '0',
  PRIMARY KEY (`f_id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `floor`
--

LOCK TABLES `floor` WRITE;
/*!40000 ALTER TABLE `floor` DISABLE KEYS */;
INSERT INTO `floor` VALUES (40,17,'Lorem, ipsum dolor sit amet consectetur adipisicing elit. Iste, corporis! Alias dolorum, nisi nesciunt nemo dicta quasi ullam at officia dignissimos ratione fugiat aut cumque vitae earum eligendi. In, dolores?',4,'2020-09-09 11:02:42','2020-09-09 11:02:42',1,'{}',0,0),(41,17,'搞鸡毛',4,'2020-09-09 19:42:20','2020-09-09 19:42:20',2,'{}',0,0);
/*!40000 ALTER TABLE `floor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `layer`
--

DROP TABLE IF EXISTS `layer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `layer` (
  `l_id` int NOT NULL AUTO_INCREMENT,
  `floor` int DEFAULT NULL,
  `content` varchar(2048) DEFAULT NULL,
  `publisher` int DEFAULT NULL,
  `responder` int DEFAULT NULL,
  `creation_time` varchar(64) DEFAULT NULL,
  `update_time` varchar(64) DEFAULT NULL,
  `level` int DEFAULT NULL,
  `replied_lid` int DEFAULT NULL,
  `info` varchar(2048) DEFAULT '{}',
  `support` int DEFAULT '0',
  `oppose` int DEFAULT '0',
  PRIMARY KEY (`l_id`)
) ENGINE=InnoDB AUTO_INCREMENT=92 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `layer`
--

LOCK TABLES `layer` WRITE;
/*!40000 ALTER TABLE `layer` DISABLE KEYS */;
INSERT INTO `layer` VALUES (87,40,'Lorem, ipsum dolor sit amet consectetur adipisicing elit. Iste, corporis! Alias dolorum, nisi nesciunt nemo dicta quasi ullam at officia dignissimos ratione fugiat aut cumque vitae earum eligendi. In, dolores?',4,4,'2020-09-09 11:02:51','2020-09-09 11:02:51',1,-1,'{}',0,0),(88,40,'Lorem, ipsum dolor sit amet consectetur adipisicing elit. Iste, corporis! Alias dolorum, nisi nesciunt nemo dicta quasi ullam at officia dignissimos ratione fugiat aut cumque vitae earum eligendi. In, dolores?',4,4,'2020-09-09 11:03:04','2020-09-09 11:03:04',2,87,'{}',0,0),(89,40,'搞鸡毛',4,4,'2020-09-09 19:42:01','2020-09-09 19:42:01',3,88,'{}',0,0),(90,40,'搞鸡毛+1',4,4,'2020-09-09 19:42:07','2020-09-09 19:42:07',4,89,'{}',0,0),(91,40,'搞鸡毛',4,4,'2020-09-09 19:42:13','2020-09-09 19:42:13',5,-1,'{}',0,0);
/*!40000 ALTER TABLE `layer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `relation`
--

DROP TABLE IF EXISTS `relation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `relation` (
  `id` int NOT NULL AUTO_INCREMENT,
  `recipient_id` int NOT NULL,
  `sender_id` int NOT NULL,
  `r_type` varchar(8) NOT NULL,
  `s_type` varchar(8) NOT NULL,
  `rt_id` int NOT NULL,
  `st_id` int NOT NULL,
  `time` varchar(64) DEFAULT NULL,
  `check` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT 'FALSE',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=87 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `relation`
--

LOCK TABLES `relation` WRITE;
/*!40000 ALTER TABLE `relation` DISABLE KEYS */;
INSERT INTO `relation` VALUES (79,4,4,'ESSAY','FLOOR',17,40,'2020-09-09 11:02:42','FALSE'),(80,4,4,'FLOOR','LAYER',40,87,'2020-09-09 11:02:51','FALSE'),(81,4,4,'LAYER','LAYER',87,88,'2020-09-09 11:03:04','FALSE'),(82,4,4,'LAYER','LAYER',88,89,'2020-09-09 19:42:01','FALSE'),(83,4,4,'LAYER','LAYER',89,90,'2020-09-09 19:42:07','FALSE'),(84,4,4,'FLOOR','LAYER',40,91,'2020-09-09 19:42:13','FALSE'),(85,4,4,'ESSAY','FLOOR',17,41,'2020-09-09 19:42:20','FALSE');
/*!40000 ALTER TABLE `relation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `u_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL,
  `email` varchar(64) DEFAULT NULL,
  `homePage` varchar(128) DEFAULT NULL,
  `password` varchar(128) NOT NULL,
  `avatar` varchar(512) DEFAULT NULL,
  `authority` int DEFAULT '0',
  `privacy` varchar(10) DEFAULT 'PUBLIC',
  `diary_privacy` varchar(10) DEFAULT 'PUBLIC',
  `info` varchar(2048) DEFAULT '{}',
  PRIMARY KEY (`u_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'rs',NULL,NULL,'rs',NULL,0,'PUBLIC','PUBLIC','{}'),(9,'王五','356@qq.com','howay.site','123',NULL,0,'PUBLIC','PUBLIC','{}');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-10-04 12:00:22
