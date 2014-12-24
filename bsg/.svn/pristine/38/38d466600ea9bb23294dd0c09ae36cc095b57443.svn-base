-- MySQL dump 10.13  Distrib 5.5.29, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: belarus_attractions
-- ------------------------------------------------------
-- Server version	5.5.29-0ubuntu0.12.10.1

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
-- Table structure for table `Address`
--

DROP TABLE IF EXISTS `Address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Address` (
  `address_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `poi_id` bigint(20) DEFAULT NULL,
  `country` varchar(32) DEFAULT NULL,
  `city` varchar(32) DEFAULT NULL,
  `street` varchar(128) DEFAULT NULL,
  `building` varchar(8) DEFAULT NULL,
  PRIMARY KEY (`address_id`),
  UNIQUE KEY `address_id_UNIQUE` (`address_id`),
  KEY `Address_fk1` (`poi_id`),
  CONSTRAINT `Address_fk1` FOREIGN KEY (`poi_id`) REFERENCES `Point_of_Interest` (`poi_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Contact`
--

DROP TABLE IF EXISTS `Contact`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Contact` (
  `contact_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `poi_id` bigint(20) DEFAULT NULL,
  `contact_name` varchar(128) DEFAULT NULL,
  `contact_phone` varchar(32) DEFAULT NULL,
  `contact_fax` varchar(32) DEFAULT NULL,
  `contact_email` varchar(128) DEFAULT NULL,
  `contact_website` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`contact_id`),
  UNIQUE KEY `contact_id_UNIQUE` (`contact_id`),
  KEY `Contacts_fk1` (`poi_id`),
  CONSTRAINT `Contacts_fk1` FOREIGN KEY (`poi_id`) REFERENCES `Point_of_Interest` (`poi_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Content`
--

DROP TABLE IF EXISTS `Content`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Content` (
  `content_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `poi_id` bigint(20) DEFAULT NULL,
  `locale` varchar(5) DEFAULT NULL,
  `name` varchar(128) DEFAULT NULL,
  `short_description` text,
  `long_description` text,
  PRIMARY KEY (`content_id`),
  UNIQUE KEY `content_id_UNIQUE` (`content_id`),
  KEY `Content_fk1` (`poi_id`),
  CONSTRAINT `Content_fk1` FOREIGN KEY (`poi_id`) REFERENCES `Point_of_Interest` (`poi_id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Photo`
--

DROP TABLE IF EXISTS `Photo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Photo` (
  `photo_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `poi_id` bigint(20) DEFAULT NULL,
  `photo_url` varchar(255) DEFAULT NULL,
  `photo_title` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`photo_id`),
  UNIQUE KEY `photo_id_UNIQUE` (`photo_id`),
  KEY `Photo_fk1` (`poi_id`),
  CONSTRAINT `Photo_fk1` FOREIGN KEY (`poi_id`) REFERENCES `Point_of_Interest` (`poi_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Point_of_Interest`
--

DROP TABLE IF EXISTS `Point_of_Interest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Point_of_Interest` (
  `poi_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address_id` bigint(20) DEFAULT NULL,
  `poi_type` varchar(32) DEFAULT NULL,
  `poi_code` varchar(128) DEFAULT NULL,
  `pictogram_url` varchar(255) DEFAULT NULL,
  `latitude` double DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  PRIMARY KEY (`poi_id`),
  UNIQUE KEY `poi_id_UNIQUE` (`poi_id`),
  KEY `address_id_idx` (`address_id`),
  CONSTRAINT `Point_of_Interest_fk1` FOREIGN KEY (`address_id`) REFERENCES `Address` (`address_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Video`
--

DROP TABLE IF EXISTS `Video`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Video` (
  `video_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `poi_id` bigint(20) DEFAULT NULL,
  `video_url` varchar(255) DEFAULT NULL,
  `video_code` varchar(255) DEFAULT NULL,
  `video_type` varchar(5) DEFAULT NULL,
  `video_title` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`video_id`),
  UNIQUE KEY `video_id_UNIQUE` (`video_id`),
  KEY `Video_fk1` (`poi_id`),
  CONSTRAINT `Video_fk1` FOREIGN KEY (`poi_id`) REFERENCES `Point_of_Interest` (`poi_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping routines for database 'belarus_attractions'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-04-05  9:49:56
