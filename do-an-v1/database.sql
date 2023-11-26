-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: localhost    Database: organica
-- ------------------------------------------------------
-- Server version	8.0.33

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
-- Table structure for table `author`
--

DROP TABLE IF EXISTS `author`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `author` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `author`
--

LOCK TABLES `author` WRITE;
/*!40000 ALTER TABLE `author` DISABLE KEYS */;
INSERT INTO `author` VALUES (1,'author 1','asdhjsadsjahdsajh  cxzbcxz zx casbc nzcbnzdcsd cx df g xzcz csd','image/1/1/1/1/1'),(2,'author 2','asdhjsadsjahdsajh  cxzbcxz zx casbc nzcbnzdcsd cx df g xzcz csd','image/1/1/1/1/1'),(3,'author 3','asdhjsadsjahdsajh  cxzbcxz zx casbc nzcbnzdcsd cx df g xzcz csd','image/1/1/1/1/1'),(4,'author 4','asdhjsadsjahdsajh  cxzbcxz zx casbc nzcbnzdcsd cx df g xzcz csd','image/1/1/1/1/1'),(5,'author 5','asdhjsadsjahdsajh  cxzbcxz zx casbc nzcbnzdcsd cx df g xzcz csd','image/1/1/1/1/1'),(6,'author 6','asdhjsadsjahdsajh  cxzbcxz zx casbc nzcbnzdcsd cx df g xzcz csd','image/1/1/1/1/1'),(7,'author 7','asdhjsadsjahdsajh  cxzbcxz zx casbc nzcbnzdcsd cx df g xzcz csd','image/1/1/1/1/1'),(8,'author 8','asdhjsadsjahdsajh  cxzbcxz zx casbc nzcbnzdcsd cx df g xzcz csd','image/1/1/1/1/1'),(9,'author 9','asdhjsadsjahdsajh  cxzbcxz zx casbc nzcbnzdcsd cx df g xzcz csd','image/1/1/1/1/1');
/*!40000 ALTER TABLE `author` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `book` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `copies` int NOT NULL,
  `copies_available` int NOT NULL,
  `create_at` datetime DEFAULT NULL,
  `description` longtext,
  `for_user` bit(1) NOT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  `language` varchar(255) DEFAULT NULL,
  `page` int NOT NULL,
  `price` double DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `publisher_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKgtvt7p649s4x80y6f4842pnfq` (`publisher_id`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES (1,0,5,'2023-11-08 09:15:58','asdjhcnzxcbbnsdchjszcbsdmahfbdnabvmsdhfbamjsdfvs dsfsdnbf jb,zx chsdcbnzczx, jsad',_binary '','image1/1/1/1','english',100,5000,'book1',4),(2,0,10,'2023-11-08 09:16:15','asdjhcnzxcbbnsdchjszcbsdmahfbdnabvmsdhfbamjsdfvs dsfsdnbf jb,zx chsdcbnzczx, jsad',_binary '','image1/1/1/1','english',100,7000,'book2',2),(3,0,12,'2023-11-08 09:16:51','asdjhcnzxcbbnsdchjszcbsdmahfbdnabvmsdhfbamjsdfvs dsfsdnbf jb,zx chsdcbnzczx, jsad',_binary '','image1/1/1/1','english',200,9000,'book3',1),(4,5,6,'2023-11-08 09:17:27','Cứ nhìn thẳng phía trước, nắm chặt tay những người bạn tin tưởng. Trên đầu là Mặt Trời, bạn sẽ băng qua những cạm bẫy bày dưới chân. Ai cũng có một câu chuyện để kể, ai cũng có một cuộc đời nhiều ý nghĩa. Mỗi ngày hãy viết nên những chương mới, thật hứng khởi, thật mê say, cho cuốn sách đời mình. Đó là ngọn lửa để bạn xuyên qua mọi đêm tối.',_binary '','image1/1/1/1','vietnamese',300,9000,'co cai cc j',4),(5,0,15,'2023-11-09 20:27:32','asdjhcnzxcbbnsdchjszcbsdmahfbdnabvmsdhfbamjsdfvs dsfsdnbf jb,zx chsdcbnzczx, jsad',_binary '','image1/1/1/1','english',200,20000,'book5',4),(6,0,6,'2023-11-15 15:50:58','Cứ nhìn thẳng phía trước, nắm chặt tay những người bạn tin tưởng. Trên đầu là Mặt Trời, bạn sẽ băng qua những cạm bẫy bày dưới chân. Ai cũng có một câu chuyện để kể, ai cũng có một cuộc đời nhiều ý nghĩa. Mỗi ngày hãy viết nên những chương mới, thật hứng khởi, thật mê say, cho cuốn sách đời mình. Đó là ngọn lửa để bạn xuyên qua mọi đêm tối.',_binary '','http://res.cloudinary.com/dboo9wwlk/image/upload/v1700036011/book.jpg.jpg','vietnamese',300,9000,'Đồng Tiền Đi Liền Nỗ Lực',7),(7,0,50,'2023-11-15 16:13:12','Cứ nhìn thẳng phía trước, nắm chặt tay những người bạn tin tưởng. Trên đầu là Mặt Trời, bạn sẽ băng qua những cạm bẫy bày dưới chân. Ai cũng có một câu chuyện để kể, ai cũng có một cuộc đời nhiều ý nghĩa. Mỗi ngày hãy viết nên những chương mới, thật hứng khởi, thật mê say, cho cuốn sách đời mình. Đó là ngọn lửa để bạn xuyên qua mọi đêm tối.',_binary '','http://res.cloudinary.com/dboo9wwlk/image/upload/v1700036011/book.jpg.jpg','vietnamese',300,18000,'book6',1),(8,0,50,'2023-11-15 16:34:18','Cứ nhìn thẳng phía trước, nắm chặt tay những người bạn tin tưởng. Trên đầu là Mặt Trời, bạn sẽ băng qua những cạm bẫy bày dưới chân. Ai cũng có một câu chuyện để kể, ai cũng có một cuộc đời nhiều ý nghĩa. Mỗi ngày hãy viết nên những chương mới, thật hứng khởi, thật mê say, cho cuốn sách đời mình. Đó là ngọn lửa để bạn xuyên qua mọi đêm tối.',_binary '','http://res.cloudinary.com/dboo9wwlk/image/upload/v1700040856/imageUrl.jpg','vietnamese',300,18000,'book7',1),(9,0,50,'2023-11-23 08:35:42','Cứ nhìn thẳng phía trước, nắm chặt tay những người bạn tin tưởng. Trên đầu là Mặt Trời, bạn sẽ băng qua những cạm bẫy bày dưới chân. Ai cũng có một câu chuyện để kể, ai cũng có một cuộc đời nhiều ý nghĩa. Mỗi ngày hãy viết nên những chương mới, thật hứng khởi, thật mê say, cho cuốn sách đời mình. Đó là ngọn lửa để bạn xuyên qua mọi đêm tối.',_binary '','http://res.cloudinary.com/dboo9wwlk/image/upload/v1700036011/book.jpg.jpg','vietnamese',300,18000,'book 10',1);
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book_author`
--

DROP TABLE IF EXISTS `book_author`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `book_author` (
  `book_id` bigint NOT NULL,
  `author_id` bigint NOT NULL,
  KEY `FKbjqhp85wjv8vpr0beygh6jsgo` (`author_id`),
  KEY `FKhwgu59n9o80xv75plf9ggj7xn` (`book_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book_author`
--

LOCK TABLES `book_author` WRITE;
/*!40000 ALTER TABLE `book_author` DISABLE KEYS */;
INSERT INTO `book_author` VALUES (1,2),(1,4),(2,1),(2,3),(3,4),(3,2),(4,2),(5,4),(5,5),(6,7),(7,7),(7,1),(4,3),(8,7),(8,1),(9,7),(9,1);
/*!40000 ALTER TABLE `book_author` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book_department`
--

DROP TABLE IF EXISTS `book_department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `book_department` (
  `book_id` bigint NOT NULL,
  `department_id` bigint NOT NULL,
  KEY `FKh0d1ecbd79lb6buxsn5j53b2d` (`department_id`),
  KEY `FKqpognoqfyn3xl4elviomqj2ao` (`book_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book_department`
--

LOCK TABLES `book_department` WRITE;
/*!40000 ALTER TABLE `book_department` DISABLE KEYS */;
INSERT INTO `book_department` VALUES (1,2),(1,3),(2,1),(2,2),(3,1),(4,5),(5,5),(5,6),(6,7),(7,2),(7,3),(4,6),(8,2),(8,3),(9,2),(9,3);
/*!40000 ALTER TABLE `book_department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `discounte` double NOT NULL,
  `total_discounted_price` double NOT NULL,
  `total_item` int DEFAULT NULL,
  `total_price` double DEFAULT NULL,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKl70asp4l4w0jmbm1tqyofho4o` (`user_id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
INSERT INTO `cart` VALUES (1,0,14000,2,14000,2),(2,0,12000,2,12000,3);
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart_item`
--

DROP TABLE IF EXISTS `cart_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart_item` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `discounted_price` double NOT NULL,
  `price` double NOT NULL,
  `quantity` int NOT NULL,
  `book_id` bigint DEFAULT NULL,
  `cart_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  `voucher_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKis5hg85qbs5d91etr4mvd4tx6` (`book_id`),
  KEY `FK1uobyhgl1wvgt1jpccia8xxs3` (`cart_id`),
  KEY `FKjnaj4sjyqjkr4ivemf9gb25w` (`user_id`),
  KEY `FKbimhqirch02p3mwqsi6rtcu3w` (`voucher_id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart_item`
--

LOCK TABLES `cart_item` WRITE;
/*!40000 ALTER TABLE `cart_item` DISABLE KEYS */;
INSERT INTO `cart_item` VALUES (1,5000,5000,1,1,1,2,NULL),(2,9000,9000,1,3,1,2,NULL),(3,5000,5000,1,1,2,3,NULL),(4,7000,7000,1,2,2,3,NULL);
/*!40000 ALTER TABLE `cart_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `departmment`
--

DROP TABLE IF EXISTS `departmment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `departmment` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `departmment`
--

LOCK TABLES `departmment` WRITE;
/*!40000 ALTER TABLE `departmment` DISABLE KEYS */;
INSERT INTO `departmment` VALUES (1,'departmment 1'),(2,'departmment 2'),(3,'departmment 3'),(4,'departmment 4'),(5,'departmment 5'),(6,'departmment 6'),(7,'departmment 7'),(8,'departmment 8'),(9,'departmment 9');
/*!40000 ALTER TABLE `departmment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `favorite`
--

DROP TABLE IF EXISTS `favorite`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `favorite` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `is_favorite` bit(1) NOT NULL,
  `book_id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3c9qhul48t7s6uc34fl7avfp8` (`book_id`),
  KEY `FKh3f2dg11ibnht4fvnmx60jcif` (`user_id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `favorite`
--

LOCK TABLES `favorite` WRITE;
/*!40000 ALTER TABLE `favorite` DISABLE KEYS */;
INSERT INTO `favorite` VALUES (1,_binary '',1,1),(2,_binary '',1,2),(3,_binary '\0',2,2),(4,_binary '',3,2);
/*!40000 ALTER TABLE `favorite` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_item`
--

DROP TABLE IF EXISTS `order_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_item` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `discounted_price` double NOT NULL,
  `price` double NOT NULL,
  `quantity` int NOT NULL,
  `book_id` bigint DEFAULT NULL,
  `employee_id` bigint DEFAULT NULL,
  `order_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  `voucher_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKb033an1f8qmpbnfl0a6jb5njs` (`book_id`),
  KEY `FK87oi78dip7g6hd2qmor1pq6ol` (`employee_id`),
  KEY `FK74x5y5r0ye1atlufw3s4xlhu0` (`order_id`),
  KEY `FKt5mosdtftirppcdhv4wk963m` (`user_id`),
  KEY `FKjjj5amd7mdc395e51aoatiunx` (`voucher_id`)
) ENGINE=MyISAM AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_item`
--

LOCK TABLES `order_item` WRITE;
/*!40000 ALTER TABLE `order_item` DISABLE KEYS */;
INSERT INTO `order_item` VALUES (11,5000,5000,1,1,1,4,2,NULL),(12,9000,9000,1,3,1,4,2,NULL),(13,5000,5000,1,1,NULL,5,2,NULL),(14,9000,9000,1,3,NULL,5,2,NULL),(15,5000,5000,1,1,NULL,6,3,NULL),(16,7000,7000,1,2,NULL,6,3,NULL);
/*!40000 ALTER TABLE `order_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orderbook`
--

DROP TABLE IF EXISTS `orderbook`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orderbook` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `checkout_date` datetime DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `discounte` double NOT NULL,
  `order_status` bit(1) NOT NULL,
  `return_date` datetime DEFAULT NULL,
  `total_discounted_price` double NOT NULL,
  `total_item` int NOT NULL,
  `total_price` double NOT NULL,
  `employee_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  `extend_order` int NOT NULL,
  `return_order` bit(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKnqu8nmwqy5ajj2qd4h2mi2o79` (`employee_id`),
  KEY `FKdexgf1yy7uty6icfgutpo5mjb` (`user_id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderbook`
--

LOCK TABLES `orderbook` WRITE;
/*!40000 ALTER TABLE `orderbook` DISABLE KEYS */;
INSERT INTO `orderbook` VALUES (4,'2023-11-08 00:00:00','2023-11-08 13:04:34',0,_binary '\0','2023-11-15 00:00:00',0,2,14000,1,2,0,_binary '\0'),(5,'2023-11-08 00:00:00','2023-11-08 15:04:44',0,_binary '\0','2023-11-15 00:00:00',0,2,14000,NULL,2,0,_binary '\0'),(6,'2023-11-08 00:00:00','2023-11-08 15:05:01',0,_binary '\0','2023-11-15 00:00:00',0,2,12000,NULL,3,0,_binary '\0');
/*!40000 ALTER TABLE `orderbook` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `publisher`
--

DROP TABLE IF EXISTS `publisher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `publisher` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `publisher`
--

LOCK TABLES `publisher` WRITE;
/*!40000 ALTER TABLE `publisher` DISABLE KEYS */;
INSERT INTO `publisher` VALUES (1,'publisher 1'),(2,'publisher 2'),(3,'publisher 3'),(4,'publisher 4'),(5,'publisher 5'),(6,'publisher 6'),(7,'publisher 7'),(8,'publisher 8'),(9,'publisher 9');
/*!40000 ALTER TABLE `publisher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ROLE_ADMIN'),(2,'ROLE_EMPLOYEE'),(3,'ROLE_USER'),(4,'ROLE_GUEST');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_at` datetime DEFAULT NULL,
  `dob` datetime DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `gender` bit(1) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) NOT NULL,
  `user_status` bit(1) NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  `active_code` varchar(255) DEFAULT NULL,
  `avatar_url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'2023-11-08 09:13:59','2023-11-08 09:13:59','employee1@gamil.com','first name',_binary '','last name','$2a$10$oU7GhiJZ73df3aN3YqZh0ue54oLhqiR5/RMV85f1Xumdxr0EAfT4q','1234567890',_binary '','employee1',NULL,'https://res.cloudinary.com/dboo9wwlk/image/upload/v1700021153/cld-sample.jpg'),(2,'2023-11-08 09:14:16','2023-11-08 09:14:16','longcv210720@gmail.com','first name',_binary '','last name','$2a$10$1rppldzOStndjzKUQ6vmMuAql9HMqyZ7SpI8j7lbAiuZawomu.K42','1234567890',_binary '','user123','3b31f715-fe7f-4b3e-b9bc-3a51dcfd9c8b','http://res.cloudinary.com/dboo9wwlk/image/upload/v1700028461/1700028461214_sWBmBcSpsX.jpg'),(3,'2023-11-08 09:14:27','2023-11-08 09:14:27','user1234@gamil.com','first name',_binary '','last name','$2a$10$1QSv/0upc3DVCedeCF/4Z.vEigNXGpStKxm6L3V0s8l6h7VdDKqn2','1234567890',_binary '','user1234',NULL,'https://res.cloudinary.com/dboo9wwlk/image/upload/v1700021153/cld-sample.jpg'),(4,'2023-11-08 09:14:49','2023-11-08 09:14:49','employee2@gamil.com','first name',_binary '','last name','$2a$10$sON0eC6Qot6sjRfKosSQ0.RjyPr.UP3MPN4iHFBRkLlTesslDo7Ra','1234567890',_binary '','employee2',NULL,'https://res.cloudinary.com/dboo9wwlk/image/upload/v1700021153/cld-sample.jpg'),(5,'2023-11-08 19:43:12','2023-11-08 19:43:12','employee3@gamil.com','first name',_binary '','last name','$2a$10$0P6k6sCq72w4cyQB6NqFnODoz8LCArsOSKh5E7PV71eGWtW2JBmYy','1234567890',_binary '','employee3',NULL,'https://res.cloudinary.com/dboo9wwlk/image/upload/v1700021153/cld-sample.jpg');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_role` (
  `role_id` bigint DEFAULT NULL,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`user_id`),
  KEY `FKa68196081fvovjhkek5m97n3y` (`role_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (2,1),(3,2),(3,3),(2,4),(2,5);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `voucher`
--

DROP TABLE IF EXISTS `voucher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `voucher` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `due_day` int DEFAULT NULL,
  `percent` int DEFAULT NULL,
  `start_day` date DEFAULT NULL,
  `status` int DEFAULT NULL,
  `cart_id` bigint DEFAULT NULL,
  `employee_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKfi7shrbmkuxg9pkec3vwc105r` (`cart_id`),
  KEY `FK5a2jebis31mofheltuonn88ql` (`employee_id`),
  KEY `FK4h57plnf4easro9xialxph4yy` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `voucher`
--

LOCK TABLES `voucher` WRITE;
/*!40000 ALTER TABLE `voucher` DISABLE KEYS */;
/*!40000 ALTER TABLE `voucher` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-11-23  9:06:46
