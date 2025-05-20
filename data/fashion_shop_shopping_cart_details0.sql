-- MySQL dump 10.13  Distrib 8.0.41, for Win64 (x86_64)
--
-- Host: localhost    Database: fashion_shop
-- ------------------------------------------------------
-- Server version	8.0.41

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
-- Table structure for table `shopping_cart_details`
--

DROP TABLE IF EXISTS `shopping_cart_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shopping_cart_details` (
  `id` int NOT NULL AUTO_INCREMENT,
  `date` datetime(6) DEFAULT NULL,
  `quantity` int NOT NULL,
  `product_id` varchar(255) DEFAULT NULL,
  `shopping_cart_id` int DEFAULT NULL,
  `product_specification_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK85g3w701sgvvdrq53lqps1gb0` (`product_id`),
  KEY `FK1s7rdbn8ok90sglhjejvwtj5d` (`shopping_cart_id`),
  KEY `FKcs37s10oicjnos4iws0eh5q43` (`product_specification_id`),
  CONSTRAINT `FK1s7rdbn8ok90sglhjejvwtj5d` FOREIGN KEY (`shopping_cart_id`) REFERENCES `shopping_carts` (`id`),
  CONSTRAINT `FK85g3w701sgvvdrq53lqps1gb0` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`),
  CONSTRAINT `FKcs37s10oicjnos4iws0eh5q43` FOREIGN KEY (`product_specification_id`) REFERENCES `product_specifications` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shopping_cart_details`
--

LOCK TABLES `shopping_cart_details` WRITE;
/*!40000 ALTER TABLE `shopping_cart_details` DISABLE KEYS */;
INSERT INTO `shopping_cart_details` VALUES (45,'2025-05-20 12:04:15.445000',1,'SP0827',1,3),(54,'2025-05-19 22:56:08.732000',1,'SP6879',2,12),(55,'2025-05-20 01:03:42.675000',1,'SP0393',2,15),(56,'2025-05-20 01:03:45.989000',1,'SP0827',2,3),(57,'2025-05-20 12:04:21.242000',1,'SP0393',1,15);
/*!40000 ALTER TABLE `shopping_cart_details` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-05-20 12:23:03
