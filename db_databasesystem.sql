-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Dec 30, 2019 at 08:01 PM
-- Server version: 10.1.13-MariaDB
-- PHP Version: 5.6.23

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_databasesystem`
--

-- --------------------------------------------------------

--
-- Table structure for table `customers`
--

CREATE TABLE `customers` (
  `CustomerID` varchar(8) NOT NULL,
  `Name` varchar(30) NOT NULL,
  `PhoneNo` varchar(16) NOT NULL,
  `Email` varchar(30) DEFAULT NULL,
  `Member` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `customers`
--

INSERT INTO `customers` (`CustomerID`, `Name`, `PhoneNo`, `Email`, `Member`) VALUES
('CUS00001', 'Muchsin', '08123918391', 'muchsin@gmail.com', 1),
('CUS00002', 'Alifio', '08112376644', 'alifio@binus.ac.id', 1),
('CUS00003', 'Nicholas', '08122212289', 'nicholas@binus.ac.id', 1);

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `OrderID` varchar(8) NOT NULL,
  `CustomerID` varchar(8) NOT NULL,
  `OrderType` varchar(10) NOT NULL,
  `DeliveryAddress` text,
  `DeliveryPrice` int(10) UNSIGNED DEFAULT NULL,
  `OrderDate` date NOT NULL,
  `DeliveryDateTime` datetime NOT NULL,
  `OrderStatus` varchar(20) NOT NULL,
  `Payment` int(10) UNSIGNED NOT NULL,
  `Discount` int(10) UNSIGNED DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`OrderID`, `CustomerID`, `OrderType`, `DeliveryAddress`, `DeliveryPrice`, `OrderDate`, `DeliveryDateTime`, `OrderStatus`, `Payment`, `Discount`) VALUES
('ORD00001', 'CUS00002', 'Delivery', 'Resor Dago Pakar, Bandung', 10000, '2019-12-04', '2019-12-12 12:05:00', 'Pending', 300000, 0);

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE `products` (
  `ProductID` varchar(8) NOT NULL,
  `ProductName` varchar(50) NOT NULL,
  `TypeID` varchar(8) NOT NULL,
  `Price` int(10) UNSIGNED DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`ProductID`, `ProductName`, `TypeID`, `Price`) VALUES
('PRO00001', 'Cake - Small Circle', 'TYP00001', 150000),
('PRO00002', 'Cake - Medium Circle', 'TYP00001', 450000),
('PRO00003', 'Cake - Large Circle', 'TYP00001', 750000),
('PRO00004', 'Cupcake - Original Plain', 'TYP00002', 12000),
('PRO00005', 'Cupcake - Strawberry', 'TYP00002', 15000),
('PRO00006', 'Cupcake - Banana Choco', 'TYP00002', 16000),
('PRO00007', 'Muffin - Original', 'TYP00003', 12000);

-- --------------------------------------------------------

--
-- Table structure for table `product_type`
--

CREATE TABLE `product_type` (
  `TypeID` varchar(8) NOT NULL,
  `Type` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `product_type`
--

INSERT INTO `product_type` (`TypeID`, `Type`) VALUES
('TYP00001', 'Cake'),
('TYP00002', 'Cupcake'),
('TYP00003', 'Muffin');

-- --------------------------------------------------------

--
-- Table structure for table `suborders`
--

CREATE TABLE `suborders` (
  `OrderID` varchar(8) NOT NULL,
  `ProductID` varchar(8) NOT NULL,
  `Qty` int(11) NOT NULL,
  `Description` text,
  `DescriptionPhoto` blob
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `suborders`
--

INSERT INTO `suborders` (`OrderID`, `ProductID`, `Qty`, `Description`, `DescriptionPhoto`) VALUES
('ORD00001', 'PRO00006', 20, '', '');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `customers`
--
ALTER TABLE `customers`
  ADD PRIMARY KEY (`CustomerID`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`OrderID`),
  ADD KEY `CustomerID` (`CustomerID`);

--
-- Indexes for table `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`ProductID`),
  ADD KEY `TypeID` (`TypeID`);

--
-- Indexes for table `product_type`
--
ALTER TABLE `product_type`
  ADD PRIMARY KEY (`TypeID`);

--
-- Indexes for table `suborders`
--
ALTER TABLE `suborders`
  ADD KEY `OrderID` (`OrderID`),
  ADD KEY `ProductID` (`ProductID`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`CustomerID`) REFERENCES `customers` (`CustomerID`);

--
-- Constraints for table `products`
--
ALTER TABLE `products`
  ADD CONSTRAINT `products_ibfk_1` FOREIGN KEY (`TypeID`) REFERENCES `product_type` (`TypeID`);

--
-- Constraints for table `suborders`
--
ALTER TABLE `suborders`
  ADD CONSTRAINT `suborders_ibfk_1` FOREIGN KEY (`OrderID`) REFERENCES `orders` (`OrderID`) ON DELETE CASCADE,
  ADD CONSTRAINT `suborders_ibfk_2` FOREIGN KEY (`ProductID`) REFERENCES `products` (`ProductID`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
