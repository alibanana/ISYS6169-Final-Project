-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Dec 21, 2019 at 04:29 AM
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
('CUS00001', 'Muchsin', '08123918391', 'm@.com', 0);

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
  `OrderDateTime` date NOT NULL,
  `DeliveryDateTime` datetime NOT NULL,
  `OrderStatus` varchar(20) NOT NULL,
  `Payment` int(10) UNSIGNED NOT NULL,
  `Discount` int(10) UNSIGNED DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
('PRO00001', 'Nutella Cheesecake', 'TYP00001', 27000),
('PRO00002', 'Base Cake', 'TYP00004', 200000),
('PRO00003', 'Base Cupcake', 'TYP00003', 15000),
('PRO00004', 'Red Velvet', 'TYP00004', 20000);

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
('TYP00001', 'Cheesecake'),
('TYP00003', 'Cupcake'),
('TYP00004', 'Cake');

-- --------------------------------------------------------

--
-- Table structure for table `suborders`
--

CREATE TABLE `suborders` (
  `OrderID` varchar(8) NOT NULL,
  `ProductID` varchar(8) NOT NULL,
  `Qty` int(11) NOT NULL,
  `Description` text,
  `DescriptionPhoto` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
  ADD CONSTRAINT `suborders_ibfk_1` FOREIGN KEY (`OrderID`) REFERENCES `orders` (`OrderID`),
  ADD CONSTRAINT `suborders_ibfk_2` FOREIGN KEY (`ProductID`) REFERENCES `products` (`ProductID`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
