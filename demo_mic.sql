-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: demo_microservice
-- ------------------------------------------------------
-- Server version	8.0.28

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
-- Table structure for table `account_role`
--

DROP TABLE IF EXISTS `account_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account_role` (
  `UUID_ACCOUNT` varchar(255) NOT NULL,
  `UUID_ROLE` varchar(255) NOT NULL,
  KEY `FKptwwnfah5lr3rmrouko5erogj` (`UUID_ROLE`),
  KEY `FK14p0o92urlarxaedl5pj61n6m` (`UUID_ACCOUNT`),
  CONSTRAINT `FK14p0o92urlarxaedl5pj61n6m` FOREIGN KEY (`UUID_ACCOUNT`) REFERENCES `account_user` (`UUID_ACCOUNT`),
  CONSTRAINT `FKptwwnfah5lr3rmrouko5erogj` FOREIGN KEY (`UUID_ROLE`) REFERENCES `role` (`UUID_ROLE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account_role`
--

LOCK TABLES `account_role` WRITE;
/*!40000 ALTER TABLE `account_role` DISABLE KEYS */;
INSERT INTO `account_role` VALUES ('d741b95b-10bb-11ec-a112-dd1dd26c3f35','cdf5aebd-b984-449a-a2a7-7f4c7d6d2b57'),('d741b95b-10bb-11ec-a112-dd1dd26c3f35','cdf5aebd-b984-449a-a2a7-7f4c7d6d2b55');
/*!40000 ALTER TABLE `account_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `account_user`
--

DROP TABLE IF EXISTS `account_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account_user` (
  `UUID_ACCOUNT` varchar(255) NOT NULL,
  `BIRTHDAY` varchar(255) DEFAULT NULL,
  `CREDENTIALS_EXPIRED` tinyint DEFAULT NULL,
  `ENABLED` tinyint DEFAULT NULL,
  `EXPIRED` tinyint DEFAULT NULL,
  `FIRST_NAME` varchar(255) DEFAULT NULL,
  `FULL_NAME` varchar(255) DEFAULT NULL,
  `GENDER` varchar(255) DEFAULT NULL,
  `LAST_NAME` varchar(255) DEFAULT NULL,
  `LOCATION` varchar(255) DEFAULT NULL,
  `LOCKED` tinyint DEFAULT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  `PHONE_NUMBER` varchar(255) DEFAULT NULL,
  `PROVIDER` varchar(255) DEFAULT NULL,
  `USERNAME` varchar(255) DEFAULT NULL,
  `USING2FA` tinyint DEFAULT NULL,
  PRIMARY KEY (`UUID_ACCOUNT`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account_user`
--

LOCK TABLES `account_user` WRITE;
/*!40000 ALTER TABLE `account_user` DISABLE KEYS */;
INSERT INTO `account_user` VALUES ('d741b95b-10bb-11ec-a112-dd1dd26c3f35',NULL,0,1,0,'Trường',NULL,'MALE','Trần Quang',NULL,0,'$2a$12$p5JPLhi8HIVFLDJsD.4/e.irA6wPzitxgnytnZ2qS7GGYibhBDlw6',NULL,'GOOGLE','tranquangtruong.aha@gmail.com',0);
/*!40000 ALTER TABLE `account_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `confirmation_token`
--

DROP TABLE IF EXISTS `confirmation_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `confirmation_token` (
  `TOKEN_ID` bigint NOT NULL,
  `CONFIRMATION_TOKEN` varchar(255) DEFAULT NULL,
  `CREATED_DATE` datetime DEFAULT NULL,
  `UUID_ACCOUNT` varchar(255) NOT NULL,
  PRIMARY KEY (`TOKEN_ID`),
  KEY `FKcs8vqfwj0p2ukjn8q1vxfrnn6` (`UUID_ACCOUNT`),
  CONSTRAINT `FKcs8vqfwj0p2ukjn8q1vxfrnn6` FOREIGN KEY (`UUID_ACCOUNT`) REFERENCES `account_user` (`UUID_ACCOUNT`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `confirmation_token`
--

LOCK TABLES `confirmation_token` WRITE;
/*!40000 ALTER TABLE `confirmation_token` DISABLE KEYS */;
/*!40000 ALTER TABLE `confirmation_token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `forgot_confirmation_token`
--

DROP TABLE IF EXISTS `forgot_confirmation_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `forgot_confirmation_token` (
  `TOKEN_ID` bigint NOT NULL,
  `CONFIRMATION_TOKEN` varchar(255) DEFAULT NULL,
  `CREATED_DATE` datetime DEFAULT NULL,
  `UUID_ACCOUNT` varchar(255) NOT NULL,
  PRIMARY KEY (`TOKEN_ID`),
  KEY `FK5wl4ig701v37i3pwqw79u9fs` (`UUID_ACCOUNT`),
  CONSTRAINT `FK5wl4ig701v37i3pwqw79u9fs` FOREIGN KEY (`UUID_ACCOUNT`) REFERENCES `account_user` (`UUID_ACCOUNT`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `forgot_confirmation_token`
--

LOCK TABLES `forgot_confirmation_token` WRITE;
/*!40000 ALTER TABLE `forgot_confirmation_token` DISABLE KEYS */;
/*!40000 ALTER TABLE `forgot_confirmation_token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (1);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oauth_client_details`
--

DROP TABLE IF EXISTS `oauth_client_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `oauth_client_details` (
  `CLIENT_ID` varchar(256) DEFAULT NULL,
  `RESOURCE_IDS` varchar(256) DEFAULT NULL,
  `CLIENT_SECRET` varchar(256) DEFAULT NULL,
  `SCOPE` varchar(256) DEFAULT NULL,
  `AUTHORIZED_GRANT_TYPES` varchar(256) DEFAULT NULL,
  `WEB_SERVER_REDIRECT_URI` varchar(256) DEFAULT NULL,
  `AUTHORITIES` varchar(256) DEFAULT NULL,
  `ACCESS_TOKEN_VALIDITY` double DEFAULT NULL,
  `REFRESH_TOKEN_VALIDITY` double DEFAULT NULL,
  `ADDITIONAL_INFORMATION` varchar(4000) DEFAULT NULL,
  `AUTOAPPROVE` varchar(256) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oauth_client_details`
--

LOCK TABLES `oauth_client_details` WRITE;
/*!40000 ALTER TABLE `oauth_client_details` DISABLE KEYS */;
INSERT INTO `oauth_client_details` VALUES ('clientapp','restservice','$2a$10$EOs8VROb14e7ZnydvXECA.4LoIhPOoFHKvVF/iBZ/ker17Eocz4Vi','read','authorization_code,password,refresh_token,implicit,social',NULL,NULL,2592000,NULL,NULL,NULL),('adminapp','restservice','$2a$10$EOs8VROb14e7ZnydvXECA.4LoIhPOoFHKvVF/iBZ/ker17Eocz4Vi','read','authorization_code,password,refresh_token,implicit,refresh_token',NULL,'role_admin,role_superadmin',0,0,NULL,NULL),('adminapp1','restservice','$2a$10$EOs8VROb14e7ZnydvXECA.4LoIhPOoFHKvVF/iBZ/ker17Eocz4Vi','read','authorization_code,password,refresh_token,implicit,refresh_token',NULL,'role_admin,role_superadmin',2592000,2592000,NULL,NULL);
/*!40000 ALTER TABLE `oauth_client_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `privilege`
--

DROP TABLE IF EXISTS `privilege`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `privilege` (
  `UUID_PRIVILEGE` varchar(255) NOT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`UUID_PRIVILEGE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `privilege`
--

LOCK TABLES `privilege` WRITE;
/*!40000 ALTER TABLE `privilege` DISABLE KEYS */;
INSERT INTO `privilege` VALUES ('25fbabfe-431b-11e8-842f-0ed5f89f718b','ADMIN_MANAGE','ADMIN_MANAGE'),('25fbabfe-431b-11e8-842f-0ed5f89f718c','USER_AI','USER_AI'),('25fbabfe-431b-11e8-842f-0ed5f89f718d','VENDOR','VENDOR'),('cdf5aebd-b984-449a-a2a7-7f4c7d6d2b57','USER','USER');
/*!40000 ALTER TABLE `privilege` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `UUID_ROLE` varchar(255) NOT NULL,
  `UUID_APPILICATION` varchar(255) DEFAULT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`UUID_ROLE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES ('cdf5aebd-b984-449a-a2a7-7f4c7d6d2b55','ADMIN','ADMIN','VNPT'),('cdf5aebd-b984-449a-a2a7-7f4c7d6d2b56','USER_AI','USER_AI','VNPT'),('cdf5aebd-b984-449a-a2a7-7f4c7d6d2b57','USER','USER','USER'),('cdf5aebd-b984-449a-a2a7-7f4c7d6d2b58','VENDOR','VENDOR','VENDOR');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_privilege`
--

DROP TABLE IF EXISTS `role_privilege`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role_privilege` (
  `UUID_ROLE` varchar(255) NOT NULL,
  `UUID_PRIVILEGE` varchar(255) NOT NULL,
  KEY `FKpxaw4sielmxcpnn4x0f2ywg4` (`UUID_PRIVILEGE`),
  KEY `FKgw24r6t9t2h63nh6j92ftjmsc` (`UUID_ROLE`),
  CONSTRAINT `FKgw24r6t9t2h63nh6j92ftjmsc` FOREIGN KEY (`UUID_ROLE`) REFERENCES `role` (`UUID_ROLE`),
  CONSTRAINT `FKpxaw4sielmxcpnn4x0f2ywg4` FOREIGN KEY (`UUID_PRIVILEGE`) REFERENCES `privilege` (`UUID_PRIVILEGE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_privilege`
--

LOCK TABLES `role_privilege` WRITE;
/*!40000 ALTER TABLE `role_privilege` DISABLE KEYS */;
INSERT INTO `role_privilege` VALUES ('cdf5aebd-b984-449a-a2a7-7f4c7d6d2b58','25fbabfe-431b-11e8-842f-0ed5f89f718d'),('cdf5aebd-b984-449a-a2a7-7f4c7d6d2b57','cdf5aebd-b984-449a-a2a7-7f4c7d6d2b57'),('cdf5aebd-b984-449a-a2a7-7f4c7d6d2b55','25fbabfe-431b-11e8-842f-0ed5f89f718b'),('cdf5aebd-b984-449a-a2a7-7f4c7d6d2b56','25fbabfe-431b-11e8-842f-0ed5f89f718c');
/*!40000 ALTER TABLE `role_privilege` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-12-11 13:24:01
