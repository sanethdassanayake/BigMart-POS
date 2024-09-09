-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               8.0.39 - MySQL Community Server - GPL
-- Server OS:                    Win64
-- HeidiSQL Version:             12.8.0.6908
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for bigmart_db
CREATE DATABASE IF NOT EXISTS `bigmart_db` /*!40100 DEFAULT CHARACTER SET utf8mb3 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `bigmart_db`;

-- Dumping structure for table bigmart_db.acc_type
CREATE TABLE IF NOT EXISTS `acc_type` (
  `id` int NOT NULL AUTO_INCREMENT,
  `acc_name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table bigmart_db.acc_type: ~2 rows (approximately)
INSERT IGNORE INTO `acc_type` (`id`, `acc_name`) VALUES
	(1, 'ADMIN'),
	(2, 'CASHIER'),
	(3, 'INVENTORY MANAGER');

-- Dumping structure for table bigmart_db.category
CREATE TABLE IF NOT EXISTS `category` (
  `id` int NOT NULL AUTO_INCREMENT,
  `category_name` varchar(45) NOT NULL,
  `description` varchar(150) NOT NULL,
  `status_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_category_status1_idx` (`status_id`),
  CONSTRAINT `fk_category_status1` FOREIGN KEY (`status_id`) REFERENCES `status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table bigmart_db.category: ~4 rows (approximately)
INSERT IGNORE INTO `category` (`id`, `category_name`, `description`, `status_id`) VALUES
	(1, 'Electronics', 'Devices such as smartphones, laptops, and accessories', 1),
	(2, 'Home Appliances', 'Household items like microwaves, refrigerators, and vacuums', 1),
	(3, 'Groceries', 'Daily essentials like food, beverages, and household supplies', 1),
	(4, 'Toys', 'Children\'s toys, games, and educational materials', 2);

-- Dumping structure for table bigmart_db.city
CREATE TABLE IF NOT EXISTS `city` (
  `id` int NOT NULL AUTO_INCREMENT,
  `city_name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table bigmart_db.city: ~25 rows (approximately)
INSERT IGNORE INTO `city` (`id`, `city_name`) VALUES
	(1, 'Ampara'),
	(2, 'Anuradhapura'),
	(3, 'Badulla'),
	(4, 'Batticaloa'),
	(5, 'Colombo'),
	(6, 'Galle'),
	(7, 'Gampaha'),
	(8, 'Hambantota'),
	(9, 'Jaffna'),
	(10, 'Kalutara'),
	(11, 'Kandy'),
	(12, 'Kegalle'),
	(13, 'Kilinochchi'),
	(14, 'Kurunegala'),
	(15, 'Mannar'),
	(16, 'Matale'),
	(17, 'Matara'),
	(18, 'Monaragala'),
	(19, 'Mullaitivu'),
	(20, 'Nuwara Eliya'),
	(21, 'Polonnaruwa'),
	(22, 'Puttalam'),
	(23, 'Ratnapura'),
	(24, 'Trincomalee'),
	(25, 'Vavuniya');

-- Dumping structure for table bigmart_db.employee
CREATE TABLE IF NOT EXISTS `employee` (
  `id` int NOT NULL AUTO_INCREMENT,
  `fname` varchar(45) NOT NULL,
  `lname` varchar(45) NOT NULL,
  `nic` varchar(12) NOT NULL,
  `mobile` varchar(10) NOT NULL,
  `email` varchar(100) NOT NULL,
  `gender_id` int NOT NULL,
  `line_one` varchar(45) NOT NULL,
  `line_two` varchar(45) NOT NULL,
  `city_id` int NOT NULL,
  `province_id` int NOT NULL,
  `pcode` int NOT NULL,
  `job_id` int NOT NULL,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `acc_type_id` int DEFAULT NULL,
  `joined_date` date NOT NULL,
  `status_id` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `fk_employee_gender_idx` (`gender_id`),
  KEY `fk_employee_city1_idx` (`city_id`),
  KEY `fk_employee_province1_idx` (`province_id`),
  KEY `fk_employee_job1_idx` (`job_id`),
  KEY `fk_employee_acc_type1_idx` (`acc_type_id`),
  KEY `fk_employee_status1_idx` (`status_id`),
  CONSTRAINT `fk_employee_acc_type1` FOREIGN KEY (`acc_type_id`) REFERENCES `acc_type` (`id`),
  CONSTRAINT `fk_employee_city1` FOREIGN KEY (`city_id`) REFERENCES `city` (`id`),
  CONSTRAINT `fk_employee_gender` FOREIGN KEY (`gender_id`) REFERENCES `gender` (`id`),
  CONSTRAINT `fk_employee_job1` FOREIGN KEY (`job_id`) REFERENCES `job` (`id`),
  CONSTRAINT `fk_employee_province1` FOREIGN KEY (`province_id`) REFERENCES `province` (`id`),
  CONSTRAINT `fk_employee_status1` FOREIGN KEY (`status_id`) REFERENCES `status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table bigmart_db.employee: ~5 rows (approximately)
INSERT IGNORE INTO `employee` (`id`, `fname`, `lname`, `nic`, `mobile`, `email`, `gender_id`, `line_one`, `line_two`, `city_id`, `province_id`, `pcode`, `job_id`, `username`, `password`, `acc_type_id`, `joined_date`, `status_id`) VALUES
	(1, 'John', 'Doe', 'NIC123456', '0712345678', 'john.doe@example.com', 1, '123 Main Street', 'Apt 4B', 1, 3, 10000, 2, 'johndoe', 'password123', 1, '2023-08-01', 1),
	(2, 'Jane', 'Smith', 'NIC654321', '0718765432', 'jane.smith@example.com', 2, '456 Elm Street', '', 5, 7, 20000, 3, 'janesmith', 'securepass', 2, '2023-07-15', 1),
	(3, 'Alex', 'Johnson', 'NIC112233', '0778899000', 'alex.johnson@example.com', 1, '789 Oak Street', 'Unit 12', 12, 2, 30000, 4, 'alexjohnson', 'mypassword', 3, '2023-06-30', 2),
	(4, 'Emily', 'Brown', 'NIC998877', '0723344556', 'emily.brown@example.com', 2, '321 Maple Avenue', '', 9, 1, 40000, 1, 'emilybrown', 'password456', 1, '2023-05-20', 1),
	(5, 'Michael', 'Davis', 'NIC445566', '0712233445', 'michael.davis@example.com', 1, '654 Pine Road', 'Suite 101', 15, 6, 50000, 5, 'michaeldavis', 'qwerty123', 2, '2023-04-10', 2);

-- Dumping structure for table bigmart_db.gender
CREATE TABLE IF NOT EXISTS `gender` (
  `id` int NOT NULL AUTO_INCREMENT,
  `gender_name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table bigmart_db.gender: ~2 rows (approximately)
INSERT IGNORE INTO `gender` (`id`, `gender_name`) VALUES
	(1, 'MALE'),
	(2, 'FEMALE');

-- Dumping structure for table bigmart_db.invoice
CREATE TABLE IF NOT EXISTS `invoice` (
  `id` int NOT NULL AUTO_INCREMENT,
  `invoice_number` varchar(20) NOT NULL,
  `total` double NOT NULL,
  `date_time` datetime NOT NULL,
  `employee_id` int NOT NULL,
  `payment_id` int NOT NULL,
  `cash` double DEFAULT NULL,
  `change` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_Invoice_employee1_idx` (`employee_id`),
  KEY `fk_Invoice_payment1_idx` (`payment_id`),
  CONSTRAINT `fk_Invoice_employee1` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`),
  CONSTRAINT `fk_Invoice_payment1` FOREIGN KEY (`payment_id`) REFERENCES `payment` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table bigmart_db.invoice: ~11 rows (approximately)
INSERT IGNORE INTO `invoice` (`id`, `invoice_number`, `total`, `date_time`, `employee_id`, `payment_id`, `cash`, `change`) VALUES
	(1, 'INV10000000000019', 1515, '2024-08-29 16:25:29', 1, 1, 1550, 35),
	(2, 'INV94048199258304', 673310, '2024-08-29 16:26:59', 1, 1, 675000, 1690),
	(3, 'INV10000000000031', 17850, '2024-08-30 00:58:58', 1, 1, 18000, 150),
	(4, 'INV22421892557616', 138000, '2024-08-30 01:04:55', 1, 1, 140000, 2000),
	(5, 'INV10000000000009', 53212, '2024-08-30 01:18:04', 1, 1, 55000, 1788),
	(6, 'INV10000000000028', 22386, '2024-08-30 01:26:33', 1, 1, 22500, 114),
	(7, 'INV10000000000015', 3003, '2024-08-30 01:46:29', 1, 1, 3020, 17),
	(8, 'INV10000000000033', 281700, '2024-08-30 01:52:27', 1, 1, 282000, 300),
	(9, 'INV10000000000017', 106050, '2024-08-30 02:05:41', 1, 1, 106100, 50),
	(10, 'INV10000000000025', 118350, '2024-08-30 02:27:30', 1, 1, 120000, 1650),
	(11, 'INV03875652610806', 1312, '2024-08-30 12:29:59', 1, 1, 1320, 8);

-- Dumping structure for table bigmart_db.invoice_has_product
CREATE TABLE IF NOT EXISTS `invoice_has_product` (
  `invoice_id` int NOT NULL,
  `product_id` int NOT NULL,
  `qty` varchar(45) NOT NULL,
  `price` varchar(45) NOT NULL,
  PRIMARY KEY (`invoice_id`,`product_id`) USING BTREE,
  KEY `fk_Invoice_has_product_product1_idx` (`product_id`),
  KEY `fk_Invoice_has_product_Invoice1_idx` (`invoice_id`) USING BTREE,
  CONSTRAINT `fk_Invoice_has_product_Invoice1` FOREIGN KEY (`invoice_id`) REFERENCES `invoice` (`id`),
  CONSTRAINT `fk_Invoice_has_product_product1` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- Dumping data for table bigmart_db.invoice_has_product: ~25 rows (approximately)
INSERT IGNORE INTO `invoice_has_product` (`invoice_id`, `product_id`, `qty`, `price`) VALUES
	(1, 19, '2', '750.0'),
	(2, 3, '1', '1000.0'),
	(2, 22, '3', '270000.0'),
	(3, 1, '1500.0', '2'),
	(3, 2, '1200.0', '4'),
	(3, 3, '900.0', '2'),
	(3, 4, '600.0', '1'),
	(3, 5, '13500.0', '10'),
	(4, 1, '150000.0', '1'),
	(5, 1, '36000.0', '1'),
	(5, 2, '22500.0', '1'),
	(6, 1, '2400.0', '2'),
	(6, 2, '6300.0', '3'),
	(6, 3, '2250.0', '5'),
	(6, 4, '750.0', '1'),
	(6, 5, '900.0', '1'),
	(6, 6, '10500.0', '1'),
	(7, 1, '2400.0', '2'),
	(7, 2, '600.0', '1'),
	(8, 1, '180000.0', '1'),
	(8, 2, '135000.0', '1'),
	(9, 1, '105000.0', '1'),
	(10, 1, '36000.0', '1'),
	(10, 2, '90000.0', '2'),
	(11, 1, '1500.0', '2');

-- Dumping structure for table bigmart_db.job
CREATE TABLE IF NOT EXISTS `job` (
  `id` int NOT NULL AUTO_INCREMENT,
  `job_title` varchar(45) NOT NULL,
  `description` varchar(150) NOT NULL,
  `status_id` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `fk_job_status1_idx` (`status_id`),
  CONSTRAINT `fk_job_status1` FOREIGN KEY (`status_id`) REFERENCES `status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table bigmart_db.job: ~8 rows (approximately)
INSERT IGNORE INTO `job` (`id`, `job_title`, `description`, `status_id`) VALUES
	(1, 'Cashier', 'Handles customer transactions and processes payments.', 1),
	(2, 'Inventory Manager', 'Manages stock levels and inventory records.', 1),
	(3, 'Sales Associate', 'Assists customers with purchases and product information.', 1),
	(4, 'Customer Service Representative', 'Handles customer inquiries and complaints.', 1),
	(5, 'Store Manager', 'Oversees daily store operations and staff management.', 1),
	(6, 'Accountant', 'Manages financial records and processes payroll.', 1),
	(7, 'Cleaner', 'Maintains cleanliness of the store.', 2),
	(8, 'Security Guard', 'Ensures the safety and security of the store premises.', 1);

-- Dumping structure for table bigmart_db.payment
CREATE TABLE IF NOT EXISTS `payment` (
  `id` int NOT NULL AUTO_INCREMENT,
  `method_name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table bigmart_db.payment: ~2 rows (approximately)
INSERT IGNORE INTO `payment` (`id`, `method_name`) VALUES
	(1, 'CASH'),
	(2, 'DEBIT CARDS'),
	(3, 'APM');

-- Dumping structure for table bigmart_db.product
CREATE TABLE IF NOT EXISTS `product` (
  `id` int NOT NULL AUTO_INCREMENT,
  `barcode` varchar(14) NOT NULL,
  `product_name` varchar(45) NOT NULL,
  `description` varchar(150) NOT NULL,
  `category_id` int NOT NULL,
  `status_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_product_category1_idx` (`category_id`),
  KEY `fk_product_status1_idx` (`status_id`),
  CONSTRAINT `fk_product_category1` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`),
  CONSTRAINT `fk_product_status1` FOREIGN KEY (`status_id`) REFERENCES `status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table bigmart_db.product: ~36 rows (approximately)
INSERT IGNORE INTO `product` (`id`, `barcode`, `product_name`, `description`, `category_id`, `status_id`) VALUES
	(1, '22421892557616', 'Smartphone', 'Latest model with high resolution display.', 1, 1),
	(2, '82864843428549', 'Washing Machine', 'Front-loading washing machine with multiple settings.', 2, 1),
	(3, '94048199258304', 'Cooking Oil', 'Refined cooking oil for daily use.', 3, 1),
	(4, '03875652610806', 'Teddy Bear', 'Soft and cuddly teddy bear for children.', 4, 1),
	(5, '31158346353966', 'Laptop', 'High-performance laptop with SSD storage.', 1, 2),
	(6, '10000000000006', 'Microwave Oven', 'Compact microwave with defrost and reheat functions.', 2, 2),
	(7, '10000000000007', 'Rice', 'Premium quality rice for everyday meals.', 3, 2),
	(8, '10000000000008', 'Action Figure', 'Collectible action figure from popular series.', 4, 2),
	(9, '10000000000009', 'Headphones', 'Noise-cancelling headphones with wireless connectivity.', 1, 1),
	(10, '10000000000010', 'Refrigerator', 'Energy-efficient refrigerator with spacious interior.', 2, 1),
	(11, '10000000000011', 'Cereal', 'Breakfast cereal with vitamins and minerals.', 3, 1),
	(12, '10000000000012', 'Board Game', 'Fun board game for family game nights.', 4, 1),
	(13, '10000000000013', 'Smartwatch', 'Stylish smartwatch with fitness tracking features.', 1, 2),
	(14, '10000000000014', 'Blender', 'High-power blender for smoothies and shakes.', 2, 2),
	(15, '10000000000015', 'Pasta', 'Durum wheat pasta for a variety of recipes.', 3, 2),
	(16, '10000000000016', 'Dollhouse', 'Detailed dollhouse with furniture and accessories.', 4, 2),
	(17, '10000000000017', 'Tablet', 'Portable tablet with high-resolution screen.', 1, 1),
	(18, '10000000000018', 'Toaster', 'Two-slice toaster with adjustable browning settings.', 2, 1),
	(19, '10000000000019', 'Sugar', 'Granulated sugar for baking and sweetening.', 3, 1),
	(20, '10000000000020', 'Remote Control Car', 'Fast remote control car with rechargeable battery.', 4, 1),
	(21, '10000000000021', 'Bluetooth Speaker', 'Portable Bluetooth speaker with excellent sound quality.', 1, 2),
	(22, '10000000000022', 'Dishwasher', 'Efficient dishwasher with multiple wash cycles.', 2, 2),
	(23, '10000000000023', 'Flour', 'All-purpose flour for baking and cooking.', 3, 2),
	(24, '10000000000024', 'Building Blocks', 'Colorful building blocks for creative play.', 4, 2),
	(25, '10000000000025', 'E-Reader', 'E-Reader with glare-free screen for reading on the go.', 1, 1),
	(26, '10000000000026', 'Coffee Maker', 'Drip coffee maker with programmable timer.', 2, 1),
	(27, '10000000000027', 'Milk', 'Fresh milk with added vitamins and minerals.', 3, 1),
	(28, '10000000000028', 'Toy Train', 'Battery-operated toy train with realistic sounds.', 4, 1),
	(29, '10000000000029', 'Camera', 'Digital camera with high-resolution image sensor.', 1, 2),
	(30, '10000000000030', 'Electric Kettle', 'Cordless electric kettle with rapid boiling feature.', 2, 2),
	(31, '10000000000031', 'Tea Bags', 'Assorted tea bags for a variety of flavors.', 3, 2),
	(32, '10000000000032', 'Stuffed Animal', 'Adorable stuffed animal with soft fur.', 4, 2),
	(33, '10000000000033', 'Game Console', 'Latest gaming console with multiple games included.', 1, 1),
	(34, '10000000000034', 'Air Conditioner', 'Energy-efficient air conditioner for cooling rooms.', 2, 1),
	(35, '10000000000035', 'Honey', 'Pure honey with natural sweetness.', 3, 1),
	(36, '10000000000036', 'Puzzle', 'Challenging puzzle with colorful pieces.', 4, 1);

-- Dumping structure for table bigmart_db.province
CREATE TABLE IF NOT EXISTS `province` (
  `id` int NOT NULL AUTO_INCREMENT,
  `province_name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table bigmart_db.province: ~9 rows (approximately)
INSERT IGNORE INTO `province` (`id`, `province_name`) VALUES
	(1, 'Central Province'),
	(2, 'Eastern Province'),
	(3, 'Northern Province'),
	(4, 'North Western Province'),
	(5, 'Northern Central Province'),
	(6, 'Sabaragamuwa Province'),
	(7, 'Southern Province'),
	(8, 'Uva Province'),
	(9, 'Western Province');

-- Dumping structure for table bigmart_db.status
CREATE TABLE IF NOT EXISTS `status` (
  `id` int NOT NULL AUTO_INCREMENT,
  `status_name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table bigmart_db.status: ~2 rows (approximately)
INSERT IGNORE INTO `status` (`id`, `status_name`) VALUES
	(1, 'ACTIVE'),
	(2, 'DEACTIVE');

-- Dumping structure for table bigmart_db.stock
CREATE TABLE IF NOT EXISTS `stock` (
  `id` int NOT NULL AUTO_INCREMENT,
  `product_id` int NOT NULL,
  `price` double NOT NULL,
  `discount` int NOT NULL DEFAULT '0',
  `qty` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_stock_product1_idx` (`product_id`),
  CONSTRAINT `fk_stock_product1` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table bigmart_db.stock: ~36 rows (approximately)
INSERT IGNORE INTO `stock` (`id`, `product_id`, `price`, `discount`, `qty`) VALUES
	(1, 1, 150000, 10, 50),
	(2, 2, 450000, 5, 20),
	(3, 3, 1000, 0, 100),
	(4, 4, 750, 15, 30),
	(5, 5, 360000, 20, 15),
	(6, 6, 90000, 0, 40),
	(7, 7, 450, 0, 500),
	(8, 8, 13500, 10, 25),
	(9, 9, 22500, 5, 60),
	(10, 10, 300000, 0, 10),
	(11, 11, 1200, 0, 150),
	(12, 12, 6000, 10, 35),
	(13, 13, 60000, 25, 40),
	(14, 14, 15000, 0, 30),
	(15, 15, 600, 5, 200),
	(16, 16, 24000, 15, 20),
	(17, 17, 105000, 0, 18),
	(18, 18, 9000, 5, 40),
	(19, 19, 750, 0, 300),
	(20, 20, 18000, 10, 25),
	(21, 21, 36000, 15, 45),
	(22, 22, 270000, 20, 10),
	(23, 23, 900, 0, 250),
	(24, 24, 16500, 10, 35),
	(25, 25, 45000, 5, 30),
	(26, 26, 12000, 0, 20),
	(27, 27, 300, 0, 400),
	(28, 28, 10500, 10, 15),
	(29, 29, 180000, 15, 12),
	(30, 30, 6000, 5, 30),
	(31, 31, 1350, 0, 350),
	(32, 32, 5400, 0, 40),
	(33, 33, 135000, 10, 20),
	(34, 34, 360000, 25, 8),
	(35, 35, 2100, 0, 200),
	(36, 36, 3600, 5, 50);

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
