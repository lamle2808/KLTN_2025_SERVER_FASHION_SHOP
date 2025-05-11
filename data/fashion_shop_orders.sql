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
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `id` varchar(255) NOT NULL,
  `date` datetime(6) DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  `payment_type` varchar(255) DEFAULT NULL,
  `status_order` varchar(255) DEFAULT NULL,
  `status_payment` int NOT NULL,
  `customer_id` varchar(255) DEFAULT NULL,
  `employee_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKpxtb8awmi0dk6smoh2vp1litg` (`customer_id`),
  KEY `FKfhl8bv0xn3sj33q2f3scf1bq6` (`employee_id`),
  CONSTRAINT `FKfhl8bv0xn3sj33q2f3scf1bq6` FOREIGN KEY (`employee_id`) REFERENCES `employees` (`id`),
  CONSTRAINT `FKpxtb8awmi0dk6smoh2vp1litg` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES ('HDBH0219','2025-04-11 08:10:14.357000','','Thanh toán online','3',1,'KH2095','NV8634'),('HDBH0716','2025-05-05 21:32:32.071000','','Thanh toán online','3',1,'KH2095',NULL),('HDBH0762','2025-04-17 22:12:16.459000','','Thanh toán online','3',1,'KH2095',NULL),('HDBH0830','2025-04-04 08:05:29.052000','','Thanh toán online','3',1,'KH2095','NV8634'),('HDBH0964','2025-05-09 09:59:07.367000','','Thanh toán online','3',1,'KH2095',NULL),('HDBH1078','2025-04-11 08:16:27.079000','','Thanh toán online','3',1,'KH2095',NULL),('HDBH1180','2025-04-04 08:02:32.923000','','Thanh toán online','3',1,'KH2095',NULL),('HDBH1315','2025-04-24 00:13:38.163000','','Thanh toán online','3',1,'KH2095',NULL),('HDBH1491','2025-05-09 01:34:23.925000','','Thanh toán online','3',1,'KH2095',NULL),('HDBH1587','2025-05-09 01:34:14.474000','','Thanh toán online','3',1,'KH2095',NULL),('HDBH1601','2025-05-09 03:40:05.852000','','Thanh toán online','3',1,'KH2095',NULL),('HDBH1984','2025-04-04 08:02:06.067000','','Thanh toán online','1',1,'KH2095',NULL),('HDBH2787','2025-05-09 03:33:55.610000','','Thanh toán online','3',1,'KH2095',NULL),('HDBH3002','2025-04-11 08:12:58.402000','','Thanh toán online','3',1,'KH2095',NULL),('HDBH3164','2025-05-09 02:55:54.748000','','Thanh toán online','3',1,'KH2095',NULL),('HDBH3340','2025-05-09 10:09:33.130000','','Thanh toán khi nhận hàng','1',0,'KH2095',NULL),('HDBH3390','2025-04-04 08:04:31.751000','size L','Thanh toán khi nhận hàng','1',0,'KH2095',NULL),('HDBH3449','2025-04-25 00:21:47.466000','','Thanh toán khi nhận hàng','1',0,'KH2095',NULL),('HDBH3561','2025-05-05 21:35:20.148000','','Thanh toán online','3',1,'KH2095',NULL),('HDBH3866','2025-05-09 09:55:53.930000','','Thanh toán online','3',1,'KH2095',NULL),('HDBH3929','2025-05-09 03:43:21.746000','','Thanh toán online','3',1,'KH2095',NULL),('HDBH4064','2025-05-09 01:34:16.559000','','Thanh toán online','3',1,'KH2095',NULL),('HDBH4397','2025-04-04 07:59:05.982000','','Thanh toán online','3',1,'KH2095',NULL),('HDBH4400','2025-04-11 08:16:05.020000','','Thanh toán online','3',1,'KH2095',NULL),('HDBH4408','2025-05-09 01:34:17.018000','','Thanh toán online','3',1,'KH2095',NULL),('HDBH4493','2025-04-04 08:04:03.623000','','Thanh toán khi nhận hàng','1',0,'KH2095',NULL),('HDBH4591','2025-04-11 08:10:35.696000','','Thanh toán online','3',1,'KH2095',NULL),('HDBH4647','2025-04-04 08:03:22.583000','','Thanh toán online','1',1,'KH2095',NULL),('HDBH4706','2025-04-11 08:09:38.139000','','Thanh toán online','3',1,'KH2095',NULL),('HDBH4798','2025-05-09 02:56:39.913000','','Thanh toán online','3',1,'KH2095',NULL),('HDBH4809','2025-04-04 08:11:39.249000','','Thanh toán online','3',1,'KH2095',NULL),('HDBH4839','2025-04-04 08:05:27.674000','','Thanh toán online','1',1,'KH2095',NULL),('HDBH5322','2025-04-03 21:12:24.033000','','Thanh toán online','3',1,'KH2095',NULL),('HDBH5728','2025-05-09 02:03:38.099000','','Mua ở cửa hàng','3',1,'KH5944','NV8634'),('HDBH5833','2025-04-04 08:03:32.077000','','Thanh toán khi nhận hàng','1',0,'KH2095',NULL),('HDBH5867','2025-05-09 09:45:34.923000','','Thanh toán online','3',1,'KH2095',NULL),('HDBH6158','2025-05-09 10:07:25.956000','','Thanh toán online','1',1,'KH2095',NULL),('HDBH6371','2025-05-09 01:34:16.831000','','Thanh toán online','3',1,'KH2095',NULL),('HDBH6441','2025-05-09 02:55:56.261000','','Thanh toán online','3',1,'KH2095',NULL),('HDBH6516','2025-05-09 09:36:10.638000','','Thanh toán online','3',1,'KH2095',NULL),('HDBH6528','2025-05-09 09:54:55.370000','','Thanh toán online','3',1,'KH2095',NULL),('HDBH6572','2025-05-05 21:34:31.974000','','Thanh toán online','3',1,'KH2095',NULL),('HDBH6592','2025-04-04 08:11:39.469000','','Thanh toán online','3',1,'KH2095',NULL),('HDBH6716','2025-05-09 09:49:41.973000','','Thanh toán online','3',1,'KH2095',NULL),('HDBH6745','2025-04-04 08:02:17.574000','','Thanh toán online','1',1,'KH2095',NULL),('HDBH6777','2025-04-04 08:00:25.625000','','Thanh toán online','3',1,'KH2095',NULL),('HDBH6847','2025-04-03 21:10:55.181000','','Thanh toán online','3',1,'KH2095',NULL),('HDBH6875','2025-03-09 22:36:19.123000','','Mua ở cửa hàng','3',1,'KH5944','NV8634'),('HDBH7088','2025-05-09 10:00:52.412000','','Thanh toán online','1',1,'KH2095',NULL),('HDBH7305','2025-04-03 21:15:54.291000','','Thanh toán online','1',1,'KH2095',NULL),('HDBH7491','2025-04-04 08:00:27.476000','','Thanh toán online','3',1,'KH2095',NULL),('HDBH7716','2025-05-09 02:55:59.841000','','Thanh toán khi nhận hàng','1',0,'KH2095',NULL),('HDBH7761','2025-04-11 08:10:28.538000','','Thanh toán khi nhận hàng','1',0,'KH2095',NULL),('HDBH7975','2025-04-04 08:01:40.666000','','Thanh toán khi nhận hàng','1',0,'KH2095',NULL),('HDBH8146','2025-04-04 08:05:16.606000','','Thanh toán khi nhận hàng','1',0,'KH2095',NULL),('HDBH8191','2025-05-09 09:51:48.676000','','Thanh toán online','3',1,'KH2095',NULL),('HDBH8377','2025-04-04 08:05:28.873000','','Thanh toán online','1',1,'KH2095',NULL),('HDBH8535','2025-04-04 08:00:27.130000','','Thanh toán online','3',1,'KH2095',NULL),('HDBH8716','2025-05-09 01:34:17.218000','','Thanh toán online','3',1,'KH2095',NULL),('HDBH8758','2025-04-11 08:17:00.008000','','Thanh toán online','3',1,'KH2095',NULL),('HDBH8963','2025-04-04 08:12:21.075000','','Thanh toán khi nhận hàng','1',0,'KH2095',NULL),('HDBH9207','2025-04-03 21:10:42.812000','','Thanh toán khi nhận hàng','1',0,'KH2095',NULL),('HDBH9370','2025-04-04 08:02:04.242000','','Thanh toán online','1',1,'KH2095',NULL),('HDBH9489','2025-04-03 21:12:17.872000','','Thanh toán khi nhận hàng','1',0,'KH2095',NULL),('HDBH9605','2025-04-04 08:00:27.315000','','Thanh toán online','3',1,'KH2095',NULL),('HDBH9821','2025-04-23 23:25:36.728000','','Thanh toán online','1',1,'KH2095',NULL);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-05-11 11:03:38
