/*M!999999\- enable the sandbox mode */
-- MariaDB dump 10.19-12.1.2-MariaDB, for Win64 (AMD64)
--
-- Host: localhost    Database: classroom_rental
-- ------------------------------------------------------
-- Server version	12.1.2-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*M!100616 SET @OLD_NOTE_VERBOSITY=@@NOTE_VERBOSITY, NOTE_VERBOSITY=0 */;

--
-- Table structure for table `booking_comments`
--

DROP TABLE IF EXISTS `booking_comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `booking_comments` (
                                    `id` int(11) NOT NULL AUTO_INCREMENT,
                                    `booking_id` int(11) DEFAULT NULL,
                                    `comment` varchar(100) DEFAULT NULL,
                                    PRIMARY KEY (`id`),
                                    KEY `booking_comments_bookings_FK` (`booking_id`),
                                    CONSTRAINT `booking_comments_bookings_FK` FOREIGN KEY (`booking_id`) REFERENCES `bookings` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booking_comments`
--

LOCK TABLES `booking_comments` WRITE;
/*!40000 ALTER TABLE `booking_comments` DISABLE KEYS */;
set autocommit=0;
/*!40000 ALTER TABLE `booking_comments` ENABLE KEYS */;
UNLOCK TABLES;
commit;

--
-- Table structure for table `bookings`
--

DROP TABLE IF EXISTS `bookings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `bookings` (
                            `id` int(11) NOT NULL AUTO_INCREMENT,
                            `booked_by` int(11) DEFAULT NULL,
                            `booked_classroom` int(11) DEFAULT NULL,
                            `book_start` timestamp NULL DEFAULT NULL,
                            `book_end` timestamp NULL DEFAULT NULL,
                            `email` varchar(100) DEFAULT NULL,
                            PRIMARY KEY (`id`),
                            KEY `customer_id_idx` (`booked_classroom`),
                            KEY `classroom_id` (`booked_by`),
                            CONSTRAINT `classroom_id` FOREIGN KEY (`booked_by`) REFERENCES `customers` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
                            CONSTRAINT `customer_id` FOREIGN KEY (`booked_classroom`) REFERENCES `classroom` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci COMMENT='		';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bookings`
--

LOCK TABLES `bookings` WRITE;
/*!40000 ALTER TABLE `bookings` DISABLE KEYS */;
set autocommit=0;
INSERT INTO `bookings` VALUES
                           (1,1,1,'2026-08-25 22:00:00','2026-08-26 22:00:00',NULL),
                           (2,4,3,'2026-08-31 22:00:00','2026-09-09 22:00:00',NULL);
/*!40000 ALTER TABLE `bookings` ENABLE KEYS */;
UNLOCK TABLES;
commit;

--
-- Table structure for table `classroom`
--

DROP TABLE IF EXISTS `classroom`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `classroom` (
                             `id` int(11) NOT NULL AUTO_INCREMENT,
                             `name` varchar(45) DEFAULT NULL,
                             `capacity` int(11) DEFAULT NULL,
                             `accessibility` tinyint(1) DEFAULT NULL,
                             `equipment` text NOT NULL,
                             PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `classroom`
--

LOCK TABLES `classroom` WRITE;
/*!40000 ALTER TABLE `classroom` DISABLE KEYS */;
set autocommit=0;
INSERT INTO `classroom` VALUES
                            (1,'Solen',20,0,'Printer, Projector'),
                            (2,'Pilen',20,0,'Printer, Projector'),
                            (3,'Rosen',20,0,'Printer, Projector'),
                            (4,'Trollsländan',20,0,'Printer, Projector'),
                            (5,'Blåklocka',20,0,'Printer, Projector'),
                            (6,'Vitsippa',20,0,'Printer, Projector'),
                            (7,'Skogsstjäna',50,1,'Printer, Projector,Printer, Projector, Whiteboard'),
                            (8,'Måne',20,0,'Printer, Projector'),
                            (9,'Mars',20,0,'Printer, Projector'),
                            (10,'Jupiter',20,0,'Printer, Projector'),
                            (11,'Saturnus',20,0,'Printer, Projector'),
                            (12,'Pluto',10,0,'Printer, Projector,Printer, Projector, Whiteboard'),
                            (13,'Venus13',99,0,'Printer, Speakers'),
                            (14,'Nejlika',20,0,'Printer, Projector'),
                            (15,'Aprikos',20,0,'Printer, Projector'),
                            (16,'Apelsin',20,0,'Printer, Projector'),
                            (17,'Kiwi',20,0,'Printer, Projector'),
                            (18,'Satsumas',20,0,'Printer, Projector'),
                            (19,'Hallon',20,0,'Printer, Projector'),
                            (20,'Blåbär',30,0,'Printer, Projector, Whiteboard');
/*!40000 ALTER TABLE `classroom` ENABLE KEYS */;
UNLOCK TABLES;
commit;

--
-- Table structure for table `customers`
--

DROP TABLE IF EXISTS `customers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `customers` (
                             `id` int(11) NOT NULL AUTO_INCREMENT,
                             `name` varchar(45) DEFAULT NULL,
                             `type` enum('INDIVIDUAL','COMPANY') DEFAULT 'COMPANY',
                             `email` varchar(100) DEFAULT NULL,
                             PRIMARY KEY (`id`),
                             UNIQUE KEY `idcustomers_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customers`
--

LOCK TABLES `customers` WRITE;
/*!40000 ALTER TABLE `customers` DISABLE KEYS */;
set autocommit=0;
INSERT INTO `customers` VALUES
                            (1,'Christoffer','COMPANY',NULL),
                            (2,'Christoffer Karlsson','COMPANY','test1@gmail.com'),
                            (3,'Simon jonsson','INDIVIDUAL','test2@gmail.com'),
                            (4,'Christoffer Karlsson','COMPANY','test1@gmail.com'),
                            (5,'Christoffer Karlsson','COMPANY','test1@gmail.com'),
                            (6,'simon','COMPANY','simon'),
                            (7,'simon','COMPANY','simon');
/*!40000 ALTER TABLE `customers` ENABLE KEYS */;
UNLOCK TABLES;
commit;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*M!100616 SET NOTE_VERBOSITY=@OLD_NOTE_VERBOSITY */;

-- Dump completed on 2026-08-28 14:24:36
