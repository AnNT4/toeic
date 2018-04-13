-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: qllt_toeic
-- ------------------------------------------------------
-- Server version	5.7.21-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `cau_hoi`
--

DROP TABLE IF EXISTS `cau_hoi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cau_hoi` (
  `ma_cau_hoi` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `cau_hoi` text COLLATE utf8_unicode_ci NOT NULL,
  `ma_loai_cau_hoi` int(10) DEFAULT NULL,
  `dap_an_dung` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `image_data` blob,
  PRIMARY KEY (`ma_cau_hoi`),
  KEY `fk_ma_dap_an_idx` (`dap_an_dung`),
  KEY `fk_loai_cau_hoi_idx` (`ma_loai_cau_hoi`),
  CONSTRAINT `fk_dap_an_dung` FOREIGN KEY (`dap_an_dung`) REFERENCES `dap_an` (`ma_dap_an`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_loai_cau_hoi` FOREIGN KEY (`ma_loai_cau_hoi`) REFERENCES `loai_cau_hoi` (`ma_loai_cau_hoi`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cau_hoi`
--

LOCK TABLES `cau_hoi` WRITE;
/*!40000 ALTER TABLE `cau_hoi` DISABLE KEYS */;
INSERT INTO `cau_hoi` VALUES ('30c5fe4e-9567-4c61-86a9-89e8c9a56fd5','Peter earns more money than ........ do',1,'607cce57-f5be-405c-904c-365d8aab734b',NULL),('36724ff8-516e-47bb-a931-145c1409006e','Would you go out more often if you .... so much in the house?',2,'3e8efd57-97d9-4c30-be50-728ae084c1c3',NULL),('6cd8bc51-6d1e-439f-b9ff-fcae989fb612','We have worked …… hard on health insurance plans to enhance employee satisfaction',2,'fca0a6d4-4f18-495a-b42a-fd3e0eafdcea',NULL),('a2467fe1-09c2-427b-adf7-e5a6a5566efb','I will try  ..... best to help you',1,'65529b06-3612-41df-8d6e-def8428015d2',NULL),('a745cb4f-20ef-4531-8df3-f75cfa5687a1','The army .... eliminated this section of the training test',1,'c6604bcd-4503-4ad2-bc52-0ab723a47f64',NULL),('bfb6373f-e642-4412-a748-98bffae8b122','We need to reserve a restaurant …… a celebration party in honor of a newly appointed personnel manager.',2,'1b896c72-88bb-475e-a4f2-add9a2428085',NULL),('cf423360-db4e-44f7-9d59-fde0660aa4eb','I ..... student',1,'05a1e479-d389-40fa-b9ea-f5e1a80f93db',NULL),('db6ffd47-74fb-4da6-b53b-ee8d3f043e25','Each book ..... unique illustrations.',1,'c1030cdb-98a7-4f53-aac5-e2d33208371c',NULL),('e247051b-1611-41b2-b17d-81d96c0c2c25','The president of Tennom Advertising ....... announces the cancellation of plans to open an office in Toronto.',1,'24d81677-87ca-4f24-9b74-4fd434c510d9',NULL),('eeaadf77-deaf-43af-b202-c82bd5c0cd8f','Fordham Stationers recently decided to switch suppliers because Valley Paper has been .... late in shipping their orders',2,'974a6fe8-54f2-4ed7-aee1-ae204227c1b6',NULL),('f8ed3bbd-20de-4d88-b739-d15f47070289','John or you ..... our grandparents next weekend',1,'7bfd8d09-daae-40ef-95fc-e678ce35617e',NULL);
/*!40000 ALTER TABLE `cau_hoi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dap_an`
--

DROP TABLE IF EXISTS `dap_an`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dap_an` (
  `ma_dap_an` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `noi_dung_dap_an` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `ma_cau_hoi` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ma_dap_an`),
  KEY `fk_ma_cau_hoi_idx` (`ma_cau_hoi`),
  CONSTRAINT `fk_ma_cau_hoi` FOREIGN KEY (`ma_cau_hoi`) REFERENCES `cau_hoi` (`ma_cau_hoi`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dap_an`
--

LOCK TABLES `dap_an` WRITE;
/*!40000 ALTER TABLE `dap_an` DISABLE KEYS */;
INSERT INTO `dap_an` VALUES ('0354064f-2fe1-48a4-ab1d-836405794043','didn\'t have to do','36724ff8-516e-47bb-a931-145c1409006e'),('05a1e479-d389-40fa-b9ea-f5e1a80f93db','am','cf423360-db4e-44f7-9d59-fde0660aa4eb'),('0b599c10-abe8-48e4-8ba1-d35dfd471d13','exceptional','6cd8bc51-6d1e-439f-b9ff-fcae989fb612'),('1b896c72-88bb-475e-a4f2-add9a2428085','for','bfb6373f-e642-4412-a748-98bffae8b122'),('23f06e01-343e-44fd-a872-5291cf2165fb','regretfully','e247051b-1611-41b2-b17d-81d96c0c2c25'),('240ed716-f34b-4338-a893-93c40dffe300','haves','db6ffd47-74fb-4da6-b53b-ee8d3f043e25'),('24d81677-87ca-4f24-9b74-4fd434c510d9','regretting','e247051b-1611-41b2-b17d-81d96c0c2c25'),('2a07e5c4-8987-42a4-90a0-8004d6c1beaf','having','db6ffd47-74fb-4da6-b53b-ee8d3f043e25'),('2a9e8d70-2ee3-4ee1-8806-5dedc5057d88','are','a745cb4f-20ef-4531-8df3-f75cfa5687a1'),('3abdb19f-41d9-415c-a605-567c8d249b7f','is','cf423360-db4e-44f7-9d59-fde0660aa4eb'),('3e8efd57-97d9-4c30-be50-728ae084c1c3','don\'t have to do','36724ff8-516e-47bb-a931-145c1409006e'),('46cbb436-eb96-4441-932e-da132a9f1406','onto','bfb6373f-e642-4412-a748-98bffae8b122'),('4737d7cb-41af-4562-9eb3-e308326f50fe','regretted','e247051b-1611-41b2-b17d-81d96c0c2c25'),('56833324-bfa5-4eb1-80b6-11b59b55c7e7','visits','f8ed3bbd-20de-4d88-b739-d15f47070289'),('607cce57-f5be-405c-904c-365d8aab734b','I','30c5fe4e-9567-4c61-86a9-89e8c9a56fd5'),('63599350-86ca-47e4-9d23-f2249ce00d75','was','cf423360-db4e-44f7-9d59-fde0660aa4eb'),('64ea9943-f6b2-4428-93ad-7cb8e9c33367','visited','f8ed3bbd-20de-4d88-b739-d15f47070289'),('65424ea3-3c4a-4b8b-bd2f-0e7a7b298931','mine','a2467fe1-09c2-427b-adf7-e5a6a5566efb'),('65529b06-3612-41df-8d6e-def8428015d2','my','a2467fe1-09c2-427b-adf7-e5a6a5566efb'),('70923619-2431-4d92-a35d-7c9513f4cdae','steadily','eeaadf77-deaf-43af-b202-c82bd5c0cd8f'),('742ffe34-b38c-4099-9213-1359e27165a7','are','cf423360-db4e-44f7-9d59-fde0660aa4eb'),('76c9e753-36fe-437b-a3ec-2e2c29a46c7e','is','a745cb4f-20ef-4531-8df3-f75cfa5687a1'),('7bfd8d09-daae-40ef-95fc-e678ce35617e','is going to visit','f8ed3bbd-20de-4d88-b739-d15f47070289'),('88525425-1460-41c2-9e3e-319b32b79b3e','from','bfb6373f-e642-4412-a748-98bffae8b122'),('8d940718-f351-43aa-9427-7d051f608eff','regretful','e247051b-1611-41b2-b17d-81d96c0c2c25'),('974a6fe8-54f2-4ed7-aee1-ae204227c1b6','consistently','eeaadf77-deaf-43af-b202-c82bd5c0cd8f'),('9ac80d5f-e9f6-47a2-88e3-80552c8f90b6','hadn\'t had to do','36724ff8-516e-47bb-a931-145c1409006e'),('9bc408dc-a47b-48af-b68a-d01db36b0956','me','a2467fe1-09c2-427b-adf7-e5a6a5566efb'),('a6bfd7f2-612a-4991-bbd1-0f5fa351b824','me','30c5fe4e-9567-4c61-86a9-89e8c9a56fd5'),('af8c0688-4af3-4a40-8c17-be200edc1875','myself','30c5fe4e-9567-4c61-86a9-89e8c9a56fd5'),('c1030cdb-98a7-4f53-aac5-e2d33208371c','has','db6ffd47-74fb-4da6-b53b-ee8d3f043e25'),('c3ad3a74-d32a-46cb-8f73-8c22424ce611','exception','6cd8bc51-6d1e-439f-b9ff-fcae989fb612'),('c6604bcd-4503-4ad2-bc52-0ab723a47f64','have','a745cb4f-20ef-4531-8df3-f75cfa5687a1'),('c7418b1a-f3bd-4548-9739-bd1bb00c0bb4','haven’t had to do','36724ff8-516e-47bb-a931-145c1409006e'),('cc3ee833-ea62-4b02-bb5f-cccaf42bb80e','mine','30c5fe4e-9567-4c61-86a9-89e8c9a56fd5'),('d5ba102d-193d-4b10-b558-f390dab4e1e8','I','a2467fe1-09c2-427b-adf7-e5a6a5566efb'),('e0a1f116-34ed-4946-a1e8-fe1142dfb28d','exactly','eeaadf77-deaf-43af-b202-c82bd5c0cd8f'),('e0db94af-5b6d-477e-ac8c-c4401aa944df','have','db6ffd47-74fb-4da6-b53b-ee8d3f043e25'),('e2aceb14-5fb2-4de4-a565-e89af408fb1c','has','a745cb4f-20ef-4531-8df3-f75cfa5687a1'),('e7178543-f147-4770-bd69-fccf919d7742','sensibly','eeaadf77-deaf-43af-b202-c82bd5c0cd8f'),('f4a38a26-fd09-46b7-857e-5fa0fc174850','except','6cd8bc51-6d1e-439f-b9ff-fcae989fb612'),('fb7f8552-64f5-47e2-a981-95a6960ed415','are going to visit','f8ed3bbd-20de-4d88-b739-d15f47070289'),('fca0a6d4-4f18-495a-b42a-fd3e0eafdcea','exceptionally','6cd8bc51-6d1e-439f-b9ff-fcae989fb612'),('ffc21442-04b0-4fd0-a162-66ba278a0a7a','off','bfb6373f-e642-4412-a748-98bffae8b122');
/*!40000 ALTER TABLE `dap_an` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `loai_cau_hoi`
--

DROP TABLE IF EXISTS `loai_cau_hoi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `loai_cau_hoi` (
  `ma_loai_cau_hoi` int(10) NOT NULL,
  `ten_loai_cau_hoi` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`ma_loai_cau_hoi`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loai_cau_hoi`
--

LOCK TABLES `loai_cau_hoi` WRITE;
/*!40000 ALTER TABLE `loai_cau_hoi` DISABLE KEYS */;
INSERT INTO `loai_cau_hoi` VALUES (1,'Ngữ pháp'),(2,'Điền khuyết'),(3,'Nghe phần photo');
/*!40000 ALTER TABLE `loai_cau_hoi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `loai_nguoi_dung`
--

DROP TABLE IF EXISTS `loai_nguoi_dung`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `loai_nguoi_dung` (
  `ma_loai_nguoi_dung` int(11) NOT NULL,
  `ten_loai_nguoi_dung` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`ma_loai_nguoi_dung`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loai_nguoi_dung`
--

LOCK TABLES `loai_nguoi_dung` WRITE;
/*!40000 ALTER TABLE `loai_nguoi_dung` DISABLE KEYS */;
INSERT INTO `loai_nguoi_dung` VALUES (1,'Admin'),(2,'End user'),(3,'Network'),(4,'System');
/*!40000 ALTER TABLE `loai_nguoi_dung` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nguoi_dung`
--

DROP TABLE IF EXISTS `nguoi_dung`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `nguoi_dung` (
  `ma_nguoi_dung` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `ten_nguoi_dung` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `mat_khau` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `loai_nguoi_dung` int(10) DEFAULT '2',
  PRIMARY KEY (`ma_nguoi_dung`),
  KEY `fk_loai_nguoi_dung_idx` (`loai_nguoi_dung`),
  CONSTRAINT `fk_loai_nguoi_dung` FOREIGN KEY (`loai_nguoi_dung`) REFERENCES `loai_nguoi_dung` (`ma_loai_nguoi_dung`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nguoi_dung`
--

LOCK TABLES `nguoi_dung` WRITE;
/*!40000 ALTER TABLE `nguoi_dung` DISABLE KEYS */;
INSERT INTO `nguoi_dung` VALUES ('0d4941ef-425b-49e5-9057-56e6554bfec3','nhanld','nhanld@annt4.net','123456',2),('1','Admin','admin@annt4.net','123456',1),('37e28611-7ec1-4744-b92f-40107d5b6a6c','annt4','annt4@annt.net','123456',2);
/*!40000 ALTER TABLE `nguoi_dung` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `thuc_hien`
--

DROP TABLE IF EXISTS `thuc_hien`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `thuc_hien` (
  `ma_nguoi_dung` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `ma_cau_hoi` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `dap_an_chon` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`ma_nguoi_dung`,`ma_cau_hoi`),
  UNIQUE KEY `ma_nguoi_dung_UNIQUE` (`ma_nguoi_dung`),
  KEY `fr_cau_hoi_idx` (`ma_cau_hoi`),
  CONSTRAINT `fr_cau_hoi` FOREIGN KEY (`ma_cau_hoi`) REFERENCES `cau_hoi` (`ma_cau_hoi`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fr_ma_nguoi_dung` FOREIGN KEY (`ma_nguoi_dung`) REFERENCES `nguoi_dung` (`ma_nguoi_dung`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `thuc_hien`
--

LOCK TABLES `thuc_hien` WRITE;
/*!40000 ALTER TABLE `thuc_hien` DISABLE KEYS */;
/*!40000 ALTER TABLE `thuc_hien` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'qllt_toeic'
--

--
-- Dumping routines for database 'qllt_toeic'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-04-13 21:54:55
