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
-- Table structure for table `product_specifications`
--

DROP TABLE IF EXISTS `product_specifications`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_specifications` (
  `id` int NOT NULL AUTO_INCREMENT,
  `pattern` varchar(255) DEFAULT NULL,
  `color` varchar(255) DEFAULT NULL,
  `count` int NOT NULL,
  `material` varchar(255) DEFAULT NULL,
  `size` varchar(255) DEFAULT NULL,
  `style_product` varchar(255) DEFAULT NULL,
  `product_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKbets5sov4bn9d2wy8vqathw6d` (`product_id`),
  CONSTRAINT `FKbets5sov4bn9d2wy8vqathw6d` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_specifications`
--

LOCK TABLES `product_specifications` WRITE;
/*!40000 ALTER TABLE `product_specifications` DISABLE KEYS */;
INSERT INTO `product_specifications` VALUES (1,'In','Trắng',14,'Vải thun','S','Cổ tròn','SP9357'),(2,'In','Đen',12,'Vải thun','L','Cổ tròn','SP9357'),(3,'Dập','Đen',3,'Vải dù','M','Cổ tròn','SP0827'),(4,'Dập','Xám',33,'Vải dù','S','Cổ tròn','SP0827'),(6,NULL,'Nâu',22,NULL,'S',NULL,'SP1313'),(7,NULL,'Xám',22,NULL,'S',NULL,'SP1313'),(8,NULL,'Đen',2,NULL,'S',NULL,'SP8995'),(9,NULL,'Nâu',4,NULL,'S',NULL,'SP8995'),(10,NULL,'Xanh Đen',10,NULL,'M',NULL,'SP9930'),(11,NULL,' Đen',2,NULL,'L',NULL,'SP9930'),(12,NULL,'Đen',1,NULL,'12 x 28 cm',NULL,'SP6879'),(13,NULL,'Đen',22,NULL,'Free Size',NULL,'SP9976'),(14,NULL,'Xám',3,NULL,'Free Size',NULL,'SP9976'),(15,NULL,'Trắng',19,NULL,'XL',NULL,'SP0393'),(16,NULL,'Trắng',22,NULL,'M',NULL,'SP0393'),(17,NULL,'Đỏ',21,NULL,'M',NULL,'SP3839'),(18,NULL,'Đen',22,NULL,'S',NULL,'SP3839'),(19,NULL,'Nâu',10,NULL,'S',NULL,'SP0198'),(20,NULL,'Nâu',2,NULL,'XL',NULL,'SP0198'),(23,NULL,'Xanh đậm',21,NULL,'M',NULL,'SP4522'),(24,NULL,'Xanh đậm',4,NULL,'S',NULL,'SP4522'),(25,NULL,'Xám',2,NULL,'M',NULL,'SP6045'),(26,NULL,'Xám',4,NULL,'L',NULL,'SP6045'),(27,NULL,'Xám chì',21,NULL,'M',NULL,'SP8465'),(28,NULL,'Xám',4,NULL,'L',NULL,'SP8465'),(29,NULL,'Đen',4,NULL,'XL',NULL,'SP8465'),(30,NULL,'Đen',10,NULL,'M',NULL,'SP3153'),(31,NULL,'Xám',5,NULL,'L',NULL,'SP3153'),(32,NULL,'Nâu',0,NULL,'M',NULL,'SP3199'),(33,NULL,' Đen',2,NULL,'L',NULL,'SP4389'),(34,NULL,' Đen',4,NULL,'S',NULL,'SP4389'),(35,NULL,'Xanh đậm',1,NULL,'S',NULL,'SP4389'),(36,NULL,'Đen',22,NULL,'M',NULL,'SP0613'),(37,NULL,'Xám',20,NULL,'XL',NULL,'SP0613');
/*!40000 ALTER TABLE `product_specifications` ENABLE KEYS */;
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
