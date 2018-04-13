-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: demo_toeic
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
-- Table structure for table `choice`
--

DROP TABLE IF EXISTS `choice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `choice` (
  `id` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `content` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `question_id` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_choice_question_idx` (`question_id`),
  CONSTRAINT `fk_choice_question` FOREIGN KEY (`question_id`) REFERENCES `question` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `choice`
--

LOCK TABLES `choice` WRITE;
/*!40000 ALTER TABLE `choice` DISABLE KEYS */;
INSERT INTO `choice` VALUES ('16a54abf-c673-48ee-8a60-840c285c58ac','DD','155b77c0-1a4a-4e8b-be94-ab0c959e035e'),('175f5a98-9700-46e3-9fb2-83aa8c49e8a5','d','168b8cf1-7109-46c9-91d2-69bf794d5f8c'),('3f5e337b-8bf4-435d-923e-4dd10cc5ac5e','tb','ed003ea6-c468-4cd0-9d59-a2a1881a9a9a'),('6340392a-72ab-478d-9f97-a9ef9d9e09b6','cc','5c7635b7-2a5a-48a1-b3a6-e73e9e1b4419'),('6e1f1ac3-807e-412d-a777-ca6f753edcd2','AA','155b77c0-1a4a-4e8b-be94-ab0c959e035e'),('7a9bf19d-b844-4100-89ba-d6e907282d56','CC','155b77c0-1a4a-4e8b-be94-ab0c959e035e'),('88e34f76-3a89-4dec-a063-fb4e7f39f59f','BB','155b77c0-1a4a-4e8b-be94-ab0c959e035e'),('941d6f49-5a80-416c-b310-9f9e6d29402f','dd','5c7635b7-2a5a-48a1-b3a6-e73e9e1b4419'),('b2d75666-f3b7-48d5-8086-aedbd69e1b5c','v','168b8cf1-7109-46c9-91d2-69bf794d5f8c'),('cd28801a-0600-4c47-8f2d-20f43dcdbf47','ta','ed003ea6-c468-4cd0-9d59-a2a1881a9a9a'),('dfca4681-1b1f-4bd7-a55e-052e7bd2fd0f','b','168b8cf1-7109-46c9-91d2-69bf794d5f8c'),('e4af9b1c-7342-436b-8286-eca7fab4323f','tc','ed003ea6-c468-4cd0-9d59-a2a1881a9a9a'),('f299fda2-4443-44f1-b8ec-ba2dc1592ece','a','168b8cf1-7109-46c9-91d2-69bf794d5f8c'),('f3875281-794c-4627-af83-f6c98c39e0e1','aa','5c7635b7-2a5a-48a1-b3a6-e73e9e1b4419'),('f4f9c04b-c2cb-4094-8d13-6e9aa5406227','td','ed003ea6-c468-4cd0-9d59-a2a1881a9a9a'),('f87a8fdc-e47b-4a70-acc2-a0dc8b575c7b','bb','5c7635b7-2a5a-48a1-b3a6-e73e9e1b4419');
/*!40000 ALTER TABLE `choice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question`
--

DROP TABLE IF EXISTS `question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `question` (
  `id` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `content` text COLLATE utf8_unicode_ci,
  `answer_id` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_question_choice_idx` (`answer_id`),
  CONSTRAINT `fk_question_choice` FOREIGN KEY (`answer_id`) REFERENCES `choice` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question`
--

LOCK TABLES `question` WRITE;
/*!40000 ALTER TABLE `question` DISABLE KEYS */;
INSERT INTO `question` VALUES ('155b77c0-1a4a-4e8b-be94-ab0c959e035e','AAAAAAA',NULL),('168b8cf1-7109-46c9-91d2-69bf794d5f8c','rrrrrr','dfca4681-1b1f-4bd7-a55e-052e7bd2fd0f'),('44cc0cb1-bda5-4ecc-aa5e-a724bdfc9e4c','I ... a pupil',NULL),('5c7635b7-2a5a-48a1-b3a6-e73e9e1b4419','aaaaaaa','6340392a-72ab-478d-9f97-a9ef9d9e09b6'),('ed003ea6-c468-4cd0-9d59-a2a1881a9a9a','tttttttttttttttttttttttt','3f5e337b-8bf4-435d-923e-4dd10cc5ac5e');
/*!40000 ALTER TABLE `question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'demo_toeic'
--

--
-- Dumping routines for database 'demo_toeic'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-04-13 13:34:17
