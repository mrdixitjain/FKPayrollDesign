-- phpMyAdmin SQL Dump
-- version 4.9.5deb2
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: May 10, 2020 at 11:50 PM
-- Server version: 8.0.20-0ubuntu0.20.04.1
-- PHP Version: 7.4.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `Employee`
--

-- --------------------------------------------------------

--
-- Table structure for table `dues`
--

CREATE TABLE `dues` (
  `empId` varchar(100) NOT NULL,
  `due` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `dues`
--

INSERT INTO `dues` (`empId`, `due`) VALUES
('GQEL178656', 25806.5);

-- --------------------------------------------------------

--
-- Table structure for table `Employee`
--

CREATE TABLE `Employee` (
  `empId` varchar(100) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `mop` varchar(20) DEFAULT NULL,
  `password` varchar(200) DEFAULT NULL,
  `type` varchar(30) DEFAULT NULL,
  `commissionRate` float DEFAULT '0',
  `isInUnion` tinyint(1) DEFAULT '0',
  `working` tinyint(1) DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `Employee`
--

INSERT INTO `Employee` (`empId`, `name`, `mop`, `password`, `type`, `commissionRate`, `isInUnion`, `working`) VALUES
('DZXX313401', 'dixit', 'BankTransfer', 'OPLBUNRUDL', 'monthly', 4, 0, 1),
('GQEL178656', 'dixit jain', 'BankTransfer', 'BHAISAHAB', 'monthly', 8, 0, 1),
('OVEP882534', 'rahul', 'PaycheckPickup', 'hellojikeseho', 'hourly', 4, 0, 1),
('PHPM141656', 'rajat', 'BankTransfer', 'BIPDVOPUBU', 'hourly', 5, 0, 1),
('QKQS285492', 'dixit', 'BankTransfer', 'MBBENRCZFE', 'monthly', 5, 0, 1),
('VQNA579686', 'Dixit Kumar Jain', 'BankTransfer', 'dixitJain', 'admin', 0, 0, 1);

-- --------------------------------------------------------

--
-- Table structure for table `HourRate`
--

CREATE TABLE `HourRate` (
  `empId` varchar(100) NOT NULL,
  `rate` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `HourRate`
--

INSERT INTO `HourRate` (`empId`, `rate`) VALUES
('OVEP882534', 300),
('PHPM141656', 200);

-- --------------------------------------------------------

--
-- Table structure for table `MonthlySalary`
--

CREATE TABLE `MonthlySalary` (
  `empId` varchar(100) NOT NULL,
  `salary` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `MonthlySalary`
--

INSERT INTO `MonthlySalary` (`empId`, `salary`) VALUES
('DZXX313401', 50000),
('GQEL178656', 80000),
('QKQS285492', 50000);

-- --------------------------------------------------------

--
-- Table structure for table `SaleReciepts`
--

CREATE TABLE `SaleReciepts` (
  `recieptNumber` varchar(30) NOT NULL,
  `empId` varchar(100) DEFAULT NULL,
  `numberOfSales` int DEFAULT NULL,
  `totalAmount` float DEFAULT NULL,
  `entryDate` date DEFAULT NULL,
  `paid` tinyint(1) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `SaleReciepts`
--

INSERT INTO `SaleReciepts` (`recieptNumber`, `empId`, `numberOfSales`, `totalAmount`, `entryDate`, `paid`) VALUES
('2fjkj45', 'GQEL178656', 100, 20000, '2020-05-05', 1);

-- --------------------------------------------------------

--
-- Table structure for table `TimeCards`
--

CREATE TABLE `TimeCards` (
  `cardNumber` varchar(30) NOT NULL,
  `empId` varchar(100) DEFAULT NULL,
  `entryDate` date DEFAULT NULL,
  `workHours` float DEFAULT NULL,
  `paid` tinyint(1) DEFAULT '0',
  `extraHours` float DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `TimeCards`
--

INSERT INTO `TimeCards` (`cardNumber`, `empId`, `entryDate`, `workHours`, `paid`, `extraHours`) VALUES
('dkfjso39', 'OVEP882534', '2020-05-05', 8, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `UnionCharges`
--

CREATE TABLE `UnionCharges` (
  `empId` varchar(100) NOT NULL,
  `dues` float DEFAULT '1000',
  `membershipFee` float DEFAULT '1000',
  `regularFee` float DEFAULT '100'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `UnionCharges`
--

INSERT INTO `UnionCharges` (`empId`, `dues`, `membershipFee`, `regularFee`) VALUES
('GQEL178656', 1100, 1100, 100),
('OVEP882534', 1100, 1100, 100);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `dues`
--
ALTER TABLE `dues`
  ADD PRIMARY KEY (`empId`);

--
-- Indexes for table `Employee`
--
ALTER TABLE `Employee`
  ADD PRIMARY KEY (`empId`),
  ADD UNIQUE KEY `empId` (`empId`);

--
-- Indexes for table `HourRate`
--
ALTER TABLE `HourRate`
  ADD PRIMARY KEY (`empId`),
  ADD UNIQUE KEY `empId` (`empId`);

--
-- Indexes for table `MonthlySalary`
--
ALTER TABLE `MonthlySalary`
  ADD PRIMARY KEY (`empId`);

--
-- Indexes for table `SaleReciepts`
--
ALTER TABLE `SaleReciepts`
  ADD PRIMARY KEY (`recieptNumber`),
  ADD KEY `empId` (`empId`);

--
-- Indexes for table `TimeCards`
--
ALTER TABLE `TimeCards`
  ADD PRIMARY KEY (`cardNumber`),
  ADD KEY `empId` (`empId`);

--
-- Indexes for table `UnionCharges`
--
ALTER TABLE `UnionCharges`
  ADD PRIMARY KEY (`empId`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `dues`
--
ALTER TABLE `dues`
  ADD CONSTRAINT `dues_ibfk_1` FOREIGN KEY (`empId`) REFERENCES `Employee` (`empId`);

--
-- Constraints for table `HourRate`
--
ALTER TABLE `HourRate`
  ADD CONSTRAINT `HourRate_ibfk_1` FOREIGN KEY (`empId`) REFERENCES `Employee` (`empId`);

--
-- Constraints for table `MonthlySalary`
--
ALTER TABLE `MonthlySalary`
  ADD CONSTRAINT `MonthlySalary_ibfk_1` FOREIGN KEY (`empId`) REFERENCES `Employee` (`empId`);

--
-- Constraints for table `SaleReciepts`
--
ALTER TABLE `SaleReciepts`
  ADD CONSTRAINT `SaleReciepts_ibfk_1` FOREIGN KEY (`empId`) REFERENCES `Employee` (`empId`);

--
-- Constraints for table `TimeCards`
--
ALTER TABLE `TimeCards`
  ADD CONSTRAINT `TimeCards_ibfk_1` FOREIGN KEY (`empId`) REFERENCES `Employee` (`empId`);

--
-- Constraints for table `UnionCharges`
--
ALTER TABLE `UnionCharges`
  ADD CONSTRAINT `UnionCharges_ibfk_1` FOREIGN KEY (`empId`) REFERENCES `Employee` (`empId`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
