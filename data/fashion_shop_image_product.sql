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
-- Table structure for table `image_product`
--

DROP TABLE IF EXISTS `image_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `image_product` (
  `id` int NOT NULL AUTO_INCREMENT,
  `date` datetime(6) DEFAULT NULL,
  `id_cloud` varchar(255) DEFAULT NULL,
  `image_link` varchar(255) DEFAULT NULL,
  `size` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `product_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKr01evkpk9msgd6a4j55hv73ja` (`product_id`),
  CONSTRAINT `FKr01evkpk9msgd6a4j55hv73ja` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `image_product`
--

LOCK TABLES `image_product` WRITE;
/*!40000 ALTER TABLE `image_product` DISABLE KEYS */;
INSERT INTO `image_product` VALUES (4,'2025-04-11 00:59:22.000000','q5zu9sewik27mwdrjkbs','http://res.cloudinary.com/dyvetg39z/image/upload/v1744307962/q5zu9sewik27mwdrjkbs.jpg','40.639KB','jpg','SP0827'),(5,'2025-04-11 01:02:20.000000','g4pw7n4dhp4ogxtqhufw','http://res.cloudinary.com/dyvetg39z/image/upload/v1744308140/g4pw7n4dhp4ogxtqhufw.jpg','64.078KB','jpg','SP6845'),(7,'2025-04-11 01:04:58.000000','knq8cnbeuibdysoo0htb','http://res.cloudinary.com/dyvetg39z/image/upload/v1744308298/knq8cnbeuibdysoo0htb.jpg','27.238KB','jpg','SP3153'),(8,'2025-04-11 01:07:19.000000','eu5ttzv0jbrtzrj57ldw','http://res.cloudinary.com/dyvetg39z/image/upload/v1744308439/eu5ttzv0jbrtzrj57ldw.jpg','25.263KB','jpg','SP9357'),(9,'2025-04-11 01:19:51.000000','ek2fw7oxsi7dfksm1cur','http://res.cloudinary.com/dyvetg39z/image/upload/v1744309191/ek2fw7oxsi7dfksm1cur.jpg','30.115KB','jpg','SP9841'),(10,'2025-04-11 01:22:06.000000','y8ktpfmbg0jcn2pllkyc','http://res.cloudinary.com/dyvetg39z/image/upload/v1744309326/y8ktpfmbg0jcn2pllkyc.jpg','26.024KB','jpg','SP8805'),(11,'2025-05-09 00:49:05.000000','uxbpt0n7jydtgibu3hcq','http://res.cloudinary.com/dyvetg39z/image/upload/v1746726545/uxbpt0n7jydtgibu3hcq.jpg','46.509KB','jpg','SP0827'),(12,'2025-05-09 00:49:35.000000','zkizj97qwauwgy6vt7ou','http://res.cloudinary.com/dyvetg39z/image/upload/v1746726575/zkizj97qwauwgy6vt7ou.jpg','86.103KB','jpg','SP0827'),(18,'2025-05-09 01:13:13.000000','xd9p2nelj6jps8w0rjeb','http://res.cloudinary.com/dyvetg39z/image/upload/v1746727993/xd9p2nelj6jps8w0rjeb.jpg','21.929KB','jpg','SP0955'),(19,'2025-05-09 01:13:19.000000','ull8eleh8oxlivmtbcl7','http://res.cloudinary.com/dyvetg39z/image/upload/v1746727999/ull8eleh8oxlivmtbcl7.jpg','62.425KB','jpg','SP0955'),(20,'2025-05-09 01:13:24.000000','oi2los6tagvrtqxuda7o','http://res.cloudinary.com/dyvetg39z/image/upload/v1746728004/oi2los6tagvrtqxuda7o.jpg','24.397KB','jpg','SP0955'),(21,'2025-05-09 01:19:09.000000','utyw8sygi2zh48n3eb0v','http://res.cloudinary.com/dyvetg39z/image/upload/v1746728349/utyw8sygi2zh48n3eb0v.jpg','20.943KB','jpg','SP3199'),(22,'2025-05-09 01:19:29.000000','hvjdzztd7izyfik22hoq','http://res.cloudinary.com/dyvetg39z/image/upload/v1746728369/hvjdzztd7izyfik22hoq.jpg','73.622KB','jpg','SP3199'),(23,'2025-05-09 01:21:41.000000','pcg7cztutsjftqfcdpwl','http://res.cloudinary.com/dyvetg39z/image/upload/v1746728501/pcg7cztutsjftqfcdpwl.jpg','43.966KB','jpg','SP4010'),(24,'2025-05-09 01:21:45.000000','vimx2ixohorgnuy1fsf7','http://res.cloudinary.com/dyvetg39z/image/upload/v1746728505/vimx2ixohorgnuy1fsf7.jpg','91.193KB','jpg','SP4010'),(25,'2025-05-09 01:25:23.000000','aj63nyhbcdtqtpbmqgo9','http://res.cloudinary.com/dyvetg39z/image/upload/v1746728723/aj63nyhbcdtqtpbmqgo9.jpg','26.262KB','jpg','SP4389'),(26,'2025-05-09 01:25:28.000000','iumb6ds1bijivshqf5yh','http://res.cloudinary.com/dyvetg39z/image/upload/v1746728728/iumb6ds1bijivshqf5yh.jpg','82.096KB','jpg','SP4389'),(27,'2025-05-09 01:29:03.000000','vqnrnhdhzpatcwjnwhkb','http://res.cloudinary.com/dyvetg39z/image/upload/v1746728943/vqnrnhdhzpatcwjnwhkb.jpg','23.914KB','jpg','SP6217'),(28,'2025-05-09 01:29:08.000000','yamdheudfb2fqlzx1l5y','http://res.cloudinary.com/dyvetg39z/image/upload/v1746728948/yamdheudfb2fqlzx1l5y.jpg','60.826KB','jpg','SP6217');
/*!40000 ALTER TABLE `image_product` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-05-11 11:03:37
