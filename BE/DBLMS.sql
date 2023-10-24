-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: localhost    Database: do_an
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
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account` (
  `username` varchar(50) NOT NULL,
  `password` varchar(50) DEFAULT NULL,
  `role_id` int DEFAULT NULL,
  PRIMARY KEY (`username`),
  KEY `FKd4vb66o896tay3yy52oqxr9w0` (`role_id`),
  CONSTRAINT `FKd4vb66o896tay3yy52oqxr9w0` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES ('admin1234','12345678',1),('empl1234','12345678',2),('user1234','12345678',3);
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `author`
--

DROP TABLE IF EXISTS `author`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `author` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(250) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `author`
--

LOCK TABLES `author` WRITE;
/*!40000 ALTER TABLE `author` DISABLE KEYS */;
INSERT INTO `author` VALUES (1,'Albert Rutherford'),(2,'Giác Minh Luật'),(3,'Thích Nhất Hạnh'),(4,'David A. Phillips'),(5,'Minh Niệm'),(6,'Tống Mặc'),(7,'Ngô Đức Vượng'),(8,'Carol S. Dweck'),(9,'Dave Evans'),(10,'Bill Burnett'),(11,'Viktor Emil Frankl'),(12,'Nhiều tác giả');
/*!40000 ALTER TABLE `author` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `book` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(250) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `description` longtext,
  `image` text,
  `title` longtext,
  `copies` int DEFAULT NULL,
  `copies_available` int DEFAULT NULL,
  `date_import` date DEFAULT NULL,
  `language` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES (1,'Sách Đi Tìm Lẽ Sống','Đi tìm lẽ sống của Viktor Frankl là một trong những quyển sách kinh điển của thời đại. Thông thường, nếu một quyển sách chỉ có một đoạn văn, một ý tưởng có sức mạnh thay đổi cuộc sống của một người, thì chỉ riêng điều đó cũng đã đủ để chúng ta đọc đi đọc lại và dành cho nó một chỗ trên kệ sách của mình. Quyển sách này có nhiều đoạn văn như thế.','https://salt.tikicdn.com/cache/750x750/ts/product/80/14/8b/61fb657f347d14d9d7bf6fe901001a8e.jpg.webp','Sách Đi Tìm Lẽ Sống (Tái Bản )',10,20,'2023-10-23','Tiếng Việt'),(2,'Rèn Luyện Tư Duy Phản Biện','Như bạn có thể thấy, chìa khóa để trở thành một người có tư duy phản biện tốt chính là sự tự nhận thức. Bạn cần phải đánh giá trung thực những điều trước đây bạn nghĩ là đúng, cũng như quá trình suy nghĩ đã dẫn bạn tới những kết luận đó. Nếu bạn không có những lý lẽ hợp lý, hoặc nếu suy nghĩ của bạn bị ảnh hưởng bởi những kinh nghiệm và cảm xúc, thì lúc đó hãy cân nhắc sử dụng tư duy phản biện! Bạn cần phải nhận ra được rằng con người, kể từ khi sinh ra, rất giỏi việc đưa ra những lý do lý giải cho những suy nghĩ khiếm khuyết của mình. Nếu bạn đang có những kết luận sai lệch này thì có một sự thật là những đức tin của bạn thường mâu thuẫn với nhau và đó thường là kết quả của thiên kiến xác nhận, nhưng nếu bạn biết điều này, thì bạn đã tiến gần hơn tới sự thật rồi!','https://salt.tikicdn.com/cache/750x750/ts/product/22/cb/a9/524a27dcd45e8a13ae6eecb3dfacba7c.jpg.webp','Rèn Luyện Tư Duy Phản Biện',12,13,'2023-10-23','Tiếng Việt'),(3,'Sách Khi Mọi Điểm Tựa Đều Mất','Những trải nghiệm có thật trong cuộc sống cũng như những nghiên cứu của tác giả đã ảnh hưởng đến suy nghĩ của tác giả về hạnh phúc. Theo đó, có bảy bước rất đơn giản giúp bạn trải nghiệm niềm hạnh phúc đích thực. Với hình ảnh Ngôi nhà ẩn dụ cho cuộc sống con người, tác giả đã mượn hình ảnh này làm biểu tượng cho ý tưởng của mình: bảy bước để xây dựng “ngôi nhà hạnh phúc”. Bảy bước này có liên quan đến bảy đối tượng mà bạn cần quan tâm trong cuộc sống của mình: sức mạnh bản thân, tinh thần, con tim, cơ thể, tâm hồn, mục tiêu và con người.','https://salt.tikicdn.com/cache/750x750/ts/product/52/74/f9/9feeda8db2b7cdb517e86b89cd72245d.jpg.webp','Sách Khi Mọi Điểm Tựa Đều Mất',7,20,'2023-10-23','Tiếng Việt'),(4,'Vẻ Đẹp Của Sự Cô Đơn','Mọi lời nói, việc làm đều được chi phối bởi định luật nhân quả, nó là một quy luật rất tự nhiên. Trong tình yêu cũng vậy, nếu chúng ta chỉ vì tham vọng bản thân mà nói, mà làm cho thỏa mãn những ảo mộng ở hiện tại, thì kết quả nhận lại chỉ là sự ca tụng, niềm hoan lạc và cả sự hạnh phúc tạm thời. Và rồi ngay cả khi bạn ngỡ rằng mình đang sống một đời bình yên với những thứ hiện có, sự cô đơn vẫn xâm chiếm và khiến trái tim bạn khổ đau.','https://salt.tikicdn.com/cache/100x100/ts/product/12/a1/e4/6a17c5c98666033d68c75ab07d9496bd.jpg.webp','Vẻ Đẹp Của Sự Cô Đơn',5,8,'2023-10-23','Tiếng Việt'),(5,'Không Diệt Không Sinh Đừng Sợ Hãi','Nhiều người trong chúng ta tin rằng cuộc đời của ta bắt đầu từ lúc chào đời và kết thúc khi ta chết. Chúng ta tin rằng chúng ta tới từ cái Không, nên khi chết chúng ta cũng không còn lại gì hết. Và chúng ta lo lắng vì sẽ trở thành hư vô. Bụt có cái hiểu rất khác về cuộc đời. Ngài hiểu rằng sống và chết chỉ là những ý niệm không có thực. Coi đó là sự thực, chính là nguyên do gây cho chúng ta khổ não. Bụt dạy không có sinh, không có diệt, không tới cũng không đi, không giống nhau cũng không khác nhau, không có cái ngã thường hằng cũng không có hư vô. Chúng ta thì coi là Có hết mọi thứ. Khi chúng ta hiểu rằng mình không bị hủy diệt thì chúng ta không còn lo sợ. Đó là sự giải thoát. Chúng ta có thể an hưởng và thưởng thức đời sống một cách mới mẻ.','https://salt.tikicdn.com/cache/750x750/ts/product/a0/bf/43/267e59580ba829ef33eb1999199dafd5.jpg.webp','Không Diệt Không Sinh Đừng Sợ Hãi (TB5)',21,5,'2023-10-23','Tiếng Việt'),(6,'Sách Thay Đổi Cuộc Sống Với Nhân Số Học','Cuốn sách Thay đổi cuộc sống với Nhân số học là tác phẩm được chị Lê Đỗ Quỳnh Hương phát triển từ tác phẩm gốc “The Complete Book of Numerology” của tiến sỹ David A. Phillips, khiến bộ môn Nhân số học khởi nguồn từ nhà toán học Pythagoras trở nên gần gũi, dễ hiểu hơn với độc giả Việt Nam. Đầu năm 2020, chuỗi chương trình “Thay đổi cuộc sống với Nhân số học” của biên tập viên, người dẫn chương trình quen thuộc tại Việt Nam Lê Đỗ Quỳnh Hương ra đời trên Youtube, với mục đích chia sẻ kiến thức, giúp mọi người tìm hiểu và phát triển, hoàn thiện bản thân, các mối quan hệ xã hội thông qua bộ môn Nhân số học. Chương trình đã nhận được sự yêu mến và phản hồi tích cực của rất nhiều khán giả và độc giả sau mỗi tập phát sóng.','https://salt.tikicdn.com/cache/750x750/media/catalog/producttmp/25/4d/52/6e5a9b48c1316dc3ccc55df2c955ec24.jpg.webp','Sách Thay Đổi Cuộc Sống Với Nhân Số Học - Lê Đỗ Quỳnh Hương',7,11,'2023-10-23','Tiếng Việt'),(7,'Sách Hiểu Về Trái Tim','Xuất bản lần đầu tiên vào năm 2011, Hiểu Về Trái Tim trình làng cũng lúc với kỷ lục: cuốn sách có số lượng in lần đầu lớn nhất: 100.000 bản. Trung tâm sách kỷ lục Việt Nam công nhận kỳ tích ấy nhưng đến nay, con số phát hành của Hiểu về trái tim vẫn chưa có dấu hiệu chậm lại.','https://salt.tikicdn.com/cache/750x750/media/catalog/producttmp/1b/d0/54/d9b746933e8dc26b678f19e2dad6aebe.jpg.webp','Sách Hiểu Về Trái Tim (Tái Bản 2019) - Minh Niệm',5,7,'2023-10-23','Tiếng Việt'),(8,'Nóng Giận Là Bản Năng , Tĩnh Lặng Là Bản Lĩnh','Sai lầm lớn nhất của chúng ta là đem những tật xấu, những cảm xúc tiêu cực trút bỏ lên những người xung quanh, càng là người thân càng dễ gây thương tổn. Cái gì cũng nói toạc ra, cái gì cũng bộc lộ hết không phải là thẳng tính, mà là thiếu bản lĩnh. Suy cho cùng, tất cả những cảm xúc tiêu cực của con người đều là sự phẫn nộ dành cho bất lực của bản thân. Nếu bạn đúng, bạn không cần phải nổi giận. Nếu bạn sai, bạn không có tư cách nổi giận.','https://salt.tikicdn.com/cache/750x750/ts/product/70/9a/98/e6d54019a2079b9565114bce93b245b7.jpg.webp','Nóng Giận Là Bản Năng , Tĩnh Lặng Là Bản Lĩnh',4,7,'2023-10-23','Tiếng Việt'),(9,'Tâm Lý Học Thành Công','Nhà tâm lý học nổi tiếng Carol S. Dweck sau nhiều thập niên nghiên cứu về thành công đã khám phá ra một ý tưởng thực sự mang tính đột phá – sức mạnh tư duy của chúng ta. Cuốn sách sẽ cho bạn thấy không chỉ khả năng và tài trí mới mang lại thành công cho chúng ta, mà phần lớn do cách tiếp cận mục tiêu bằng lối tư duy nào. Việc tán dương trí thông minh và khả năng của con bạn không hề nuôi dưỡng lòng tự trọng và dẫn đến thành tựu, mà thậm chí còn phương hại đến thành công sau này. Với tư duy đứng đắn, chúng ta có thể tạo động lực cho con cái và giúp chúng phát triển trong trường học, cũng như đạt được mục tiêu của bản thân trong cuộc sống và sự nghiệp. Dweck đã giúp tất cả các bậc cha mẹ, giáo viên, CEO và vận động viên thấy một ý tưởng đơn giản về não bộ có thể tạo ra tình yêu học tập và sự kiên trì – cơ sở cho những thành tựu vĩ đại ở mọi lĩnh vực.','https://salt.tikicdn.com/cache/750x750/ts/product/98/7f/84/f1a1242d4b40788798179e94f16fe0bd.jpg.webp','Tâm Lý Học Thành Công',9,3,'2023-10-23','Tiếng Việt'),(10,'Minh Triết Trong Ăn Uống Của Phương Đông','Cơ thể được xây đắp bởi đồ ăn thức uống hàng ngày, nguồn năng lượng cho chúng ta hoạt động cũng từ đó. Vì vậy, hiểu biết Minh triết trong Ăn uống của phương Đông là điều vô cùng cần thiết. Tác giả Ngô Đức Vượng từ lâu đã quan tâm tới vấn đề này. Ông đã ăn chay trường, ăn gạo lứt muối vừng, nhịn ăn chữa bệnh cho chính mình và giúp cho nhiều người thực hành tự chữa bệnh, dưỡng sinh, nâng cao sức khỏe, thu nhiều kết quả tốt đẹp từ nhiều năm nay. Với thiện chí đem lại sức khỏe và trị bệnh không dùng thuốc, ít tốn kém, dễ thực hành…cho cộng đồng, tác giả đã viết cuốn sách này, nhằm cống hiến cho bạn đọc những lời khuyên quý giá và đáp ứng những nhu cầu cần thiết cho độc giả.','https://salt.tikicdn.com/cache/750x750/ts/product/3c/d2/4d/40ee15de35d9e7a5256e187da1159380.png.webp','Minh Triết Trong Ăn Uống Của Phương Đông (Tái Bản)',8,4,'2023-10-23','Tiếng Việt'),(11,'Sách Thiết Kế Một Cuộc Đời Đáng Sống','Bạn đang \"mắc kẹt\" trong một công việc không hạnh phúc? Bạn đau đầu vì chuyện tiền bạc? Bạn bế tắc vì cuộc sống gia đình? Cuộc sống này vốn nhiều thăng trầm, trắc trở. Ai cũng phải chật vật với vô số câu hỏi về cuộc đời, công việc, ý nghĩa và mục đích sống. Có những vấn đề kéo dài dai dẳng trong hàng năm trời, đôi khi khiến chúng ta cảm thấy như không thể tìm được lối','https://salt.tikicdn.com/cache/750x750/ts/product/ac/65/4e/e21a92395ae8a7a1c2af3da945d76944.jpg.webp','Sách Thiết Kế Một Cuộc Đời Đáng Sống',8,4,'2023-10-23','Tiếng Việt');
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book_author`
--

DROP TABLE IF EXISTS `book_author`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `book_author` (
  `book_id` int NOT NULL,
  `author_id` int NOT NULL,
  PRIMARY KEY (`book_id`,`author_id`),
  KEY `FKbjqhp85wjv8vpr0beygh6jsgo` (`author_id`),
  CONSTRAINT `book_author_ibfk_1` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`),
  CONSTRAINT `book_author_ibfk_2` FOREIGN KEY (`author_id`) REFERENCES `author` (`id`),
  CONSTRAINT `FKbjqhp85wjv8vpr0beygh6jsgo` FOREIGN KEY (`author_id`) REFERENCES `author` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book_author`
--

LOCK TABLES `book_author` WRITE;
/*!40000 ALTER TABLE `book_author` DISABLE KEYS */;
INSERT INTO `book_author` VALUES (2,1),(4,2),(5,3),(6,4),(7,5),(8,6),(10,7),(9,8),(11,9),(11,10),(1,11),(3,12);
/*!40000 ALTER TABLE `book_author` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book_department`
--

DROP TABLE IF EXISTS `book_department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `book_department` (
  `book_id` int NOT NULL,
  `department_id` int NOT NULL,
  PRIMARY KEY (`book_id`,`department_id`),
  KEY `department_id` (`department_id`),
  CONSTRAINT `book_department_ibfk_1` FOREIGN KEY (`department_id`) REFERENCES `department` (`id`),
  CONSTRAINT `book_department_ibfk_2` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book_department`
--

LOCK TABLES `book_department` WRITE;
/*!40000 ALTER TABLE `book_department` DISABLE KEYS */;
INSERT INTO `book_department` VALUES (3,1),(6,6),(2,7),(4,7),(5,7),(6,7),(7,7),(8,7),(9,7),(10,7),(11,7),(1,12),(11,12);
/*!40000 ALTER TABLE `book_department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bookcase`
--

DROP TABLE IF EXISTS `bookcase`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bookcase` (
  `id` int NOT NULL AUTO_INCREMENT,
  `No` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bookcase`
--

LOCK TABLES `bookcase` WRITE;
/*!40000 ALTER TABLE `bookcase` DISABLE KEYS */;
INSERT INTO `bookcase` VALUES (1,1),(2,2),(3,3),(4,4),(5,5);
/*!40000 ALTER TABLE `bookcase` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bookcase_book`
--

DROP TABLE IF EXISTS `bookcase_book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bookcase_book` (
  `Bookcase_id` int NOT NULL,
  `Book_id` int NOT NULL,
  `Line_id` int NOT NULL,
  PRIMARY KEY (`Bookcase_id`,`Book_id`,`Line_id`),
  KEY `Line_id` (`Line_id`),
  CONSTRAINT `bookcase_book_ibfk_1` FOREIGN KEY (`Bookcase_id`) REFERENCES `bookcase` (`id`),
  CONSTRAINT `bookcase_book_ibfk_2` FOREIGN KEY (`Line_id`) REFERENCES `line` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bookcase_book`
--

LOCK TABLES `bookcase_book` WRITE;
/*!40000 ALTER TABLE `bookcase_book` DISABLE KEYS */;
INSERT INTO `bookcase_book` VALUES (1,1,1),(1,8,1),(1,9,1),(1,10,1),(1,11,1),(2,6,1),(1,2,2),(2,7,2),(1,3,3),(1,4,4),(1,5,5);
/*!40000 ALTER TABLE `bookcase_book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `department` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` VALUES (1,'Tiểu thuyết'),(2,'Truyện ngắn'),(3,'Light novel'),(4,'Ngôn tình'),(5,'Manga-Comic'),(6,'Kiến thức bách khoa'),(7,'Quản trị-Lãnh Đạo'),(8,'Nhân vật-Bài học kinh doanh'),(9,'Marketing-Bán hàng'),(10,'Chính trị'),(11,'Kinh Tế'),(12,'Câu truyện cuộc đời'),(13,'Tâm lý');
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `line`
--

DROP TABLE IF EXISTS `line`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `line` (
  `id` int NOT NULL AUTO_INCREMENT,
  `No` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `line`
--

LOCK TABLES `line` WRITE;
/*!40000 ALTER TABLE `line` DISABLE KEYS */;
INSERT INTO `line` VALUES (1,1),(2,2),(3,3),(4,4),(5,5);
/*!40000 ALTER TABLE `line` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order` (
  `id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(250) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `checkout_date` date DEFAULT NULL,
  `return_date` date DEFAULT NULL,
  `amount` int DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  `price` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `order_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user_voucher` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
INSERT INTO `order` VALUES (1,'lmssplus@gmail.com','2023-09-21','2023-09-28',3,1,63000);
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_book`
--

DROP TABLE IF EXISTS `order_book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_book` (
  `Order_id` int NOT NULL,
  `Book_id` int NOT NULL,
  PRIMARY KEY (`Order_id`,`Book_id`),
  KEY `Book_id` (`Book_id`),
  CONSTRAINT `order_book_ibfk_1` FOREIGN KEY (`Order_id`) REFERENCES `order` (`id`),
  CONSTRAINT `order_book_ibfk_2` FOREIGN KEY (`Book_id`) REFERENCES `book` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_book`
--

LOCK TABLES `order_book` WRITE;
/*!40000 ALTER TABLE `order_book` DISABLE KEYS */;
INSERT INTO `order_book` VALUES (1,1),(1,3),(1,4);
/*!40000 ALTER TABLE `order_book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_detail`
--

DROP TABLE IF EXISTS `order_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_detail` (
  `id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(250) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `checkout_date` date DEFAULT NULL,
  `return_date` date DEFAULT NULL,
  `title` longtext,
  `author` varchar(250) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `order_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `order_id` (`order_id`),
  CONSTRAINT `order_detail_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `order` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_detail`
--

LOCK TABLES `order_detail` WRITE;
/*!40000 ALTER TABLE `order_detail` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ADMIN'),(2,'EMPLOYEE'),(3,'USER');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service`
--

DROP TABLE IF EXISTS `service`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `service` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(250) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `description` longtext,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service`
--

LOCK TABLES `service` WRITE;
/*!40000 ALTER TABLE `service` DISABLE KEYS */;
/*!40000 ALTER TABLE `service` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `last_name` varchar(50) DEFAULT NULL,
  `email` varchar(250) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `phonenumber` int DEFAULT NULL,
  `username` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `gender` tinyint(1) DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `QR` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'holyshit','man','holy@gmail.com',1234567890,'user1234',1,'2000-12-24',''),(2,'emplfirst','hahaha','empl1@gmail.com',1234567890,'empl1234',1,'2000-12-24',''),(3,'adminfirst','hoicham','admin1@gmail.com',1234567890,'admin1234',1,'2000-12-24','');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_book_favorite`
--

DROP TABLE IF EXISTS `user_book_favorite`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_book_favorite` (
  `user_id` int NOT NULL,
  `book_id` int NOT NULL,
  `isFavorite` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`user_id`,`book_id`),
  KEY `book_id` (`book_id`),
  CONSTRAINT `user_book_favorite_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `user_book_favorite_ibfk_2` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_book_favorite`
--

LOCK TABLES `user_book_favorite` WRITE;
/*!40000 ALTER TABLE `user_book_favorite` DISABLE KEYS */;
INSERT INTO `user_book_favorite` VALUES (1,1,1),(1,2,1),(1,5,1),(1,6,1),(1,7,1),(1,8,1),(1,11,1);
/*!40000 ALTER TABLE `user_book_favorite` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_voucher`
--

DROP TABLE IF EXISTS `user_voucher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_voucher` (
  `user_id` int NOT NULL,
  `voucher_id` int NOT NULL,
  `quanitity` int DEFAULT NULL,
  PRIMARY KEY (`user_id`,`voucher_id`),
  KEY `voucher_id` (`voucher_id`),
  CONSTRAINT `user_voucher_ibfk_1` FOREIGN KEY (`voucher_id`) REFERENCES `voucher` (`id`),
  CONSTRAINT `user_voucher_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_voucher`
--

LOCK TABLES `user_voucher` WRITE;
/*!40000 ALTER TABLE `user_voucher` DISABLE KEYS */;
INSERT INTO `user_voucher` VALUES (1,1,1),(1,3,1),(1,4,1);
/*!40000 ALTER TABLE `user_voucher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `voucher`
--

DROP TABLE IF EXISTS `voucher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `voucher` (
  `id` int NOT NULL AUTO_INCREMENT,
  `discountPercent` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `voucher`
--

LOCK TABLES `voucher` WRITE;
/*!40000 ALTER TABLE `voucher` DISABLE KEYS */;
INSERT INTO `voucher` VALUES (1,10),(2,15),(3,20),(4,25),(5,30),(6,35),(7,40);
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

-- Dump completed on 2023-10-23 20:56:35
