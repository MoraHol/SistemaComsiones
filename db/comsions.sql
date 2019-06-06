-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost
-- Tiempo de generación: 06-06-2019 a las 19:56:43
-- Versión del servidor: 10.1.37-MariaDB
-- Versión de PHP: 7.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `comsions`
--
CREATE DATABASE IF NOT EXISTS `comsions` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `comsions`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categories_products`
--

DROP TABLE IF EXISTS `categories_products`;
CREATE TABLE IF NOT EXISTS `categories_products` (
  `id_categories_products` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id_categories_products`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cellphone_numbers`
--

DROP TABLE IF EXISTS `cellphone_numbers`;
CREATE TABLE IF NOT EXISTS `cellphone_numbers` (
  `id_cellphone_number` int(11) NOT NULL AUTO_INCREMENT,
  `cell_phone_number` bigint(10) NOT NULL,
  `clients_id_clients` int(11) NOT NULL,
  PRIMARY KEY (`id_cellphone_number`,`clients_id_clients`),
  KEY `fk_emails_clients1_idx` (`clients_id_clients`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `cellphone_numbers`
--

INSERT INTO `cellphone_numbers` (`id_cellphone_number`, `cell_phone_number`, `clients_id_clients`) VALUES
(1, 3222724734, 1),
(2, 2323454, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clients`
--

DROP TABLE IF EXISTS `clients`;
CREATE TABLE IF NOT EXISTS `clients` (
  `id_clients` int(11) NOT NULL AUTO_INCREMENT,
  `nit` varchar(45) NOT NULL,
  `name_company` varchar(255) NOT NULL,
  `first_name` varchar(45) NOT NULL,
  `second_name` varchar(45) DEFAULT NULL,
  `fisrt_surname` varchar(45) NOT NULL,
  `second_surname` varchar(45) DEFAULT NULL,
  `address_personal` varchar(255) NOT NULL,
  `address_company` varchar(255) NOT NULL,
  PRIMARY KEY (`id_clients`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `clients`
--

INSERT INTO `clients` (`id_clients`, `nit`, `name_company`, `first_name`, `second_name`, `fisrt_surname`, `second_surname`, `address_personal`, `address_company`) VALUES
(1, '121123123', 'helados popsy', 'alexis', NULL, 'holguin', NULL, 'diagonal 2b #82-30', 'carrera 80b #75-47');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `emails`
--

DROP TABLE IF EXISTS `emails`;
CREATE TABLE IF NOT EXISTS `emails` (
  `id_email` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `clients_id_clients` int(11) NOT NULL,
  PRIMARY KEY (`id_email`,`clients_id_clients`),
  KEY `fk_emails_clients1_idx` (`clients_id_clients`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `emails`
--

INSERT INTO `emails` (`id_email`, `email`, `clients_id_clients`) VALUES
(1, 'holguinalexis30@gmail.com', 1),
(2, 'holguinalexis30@gmail.com', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `employees`
--

DROP TABLE IF EXISTS `employees`;
CREATE TABLE IF NOT EXISTS `employees` (
  `id_employee` int(11) NOT NULL AUTO_INCREMENT,
  `personal_id` varchar(45) NOT NULL,
  `first_name` varchar(45) NOT NULL,
  `second_name` varchar(45) DEFAULT NULL,
  `fisrt_surname` varchar(45) NOT NULL,
  `second_surname` varchar(45) DEFAULT NULL,
  `positions_id_positions` int(11) NOT NULL,
  `ext` varchar(45) NOT NULL,
  `dependency` varchar(45) NOT NULL,
  `password` varchar(256) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_employee`,`positions_id_positions`),
  KEY `fk_employees_positions1_idx` (`positions_id_positions`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `events`
--

DROP TABLE IF EXISTS `events`;
CREATE TABLE IF NOT EXISTS `events` (
  `id_event` int(11) NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL,
  `description` mediumtext NOT NULL,
  `clients_id_clients` int(11) NOT NULL,
  PRIMARY KEY (`id_event`,`clients_id_clients`),
  KEY `fk_events_clients1_idx` (`clients_id_clients`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `functions`
--

DROP TABLE IF EXISTS `functions`;
CREATE TABLE IF NOT EXISTS `functions` (
  `id_functions` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_functions`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `payrolls`
--

DROP TABLE IF EXISTS `payrolls`;
CREATE TABLE IF NOT EXISTS `payrolls` (
  `id_payroll` int(11) NOT NULL,
  `amount` int(11) NOT NULL,
  `comision` int(11) NOT NULL,
  `month` enum('january','february') NOT NULL,
  `year` year(4) NOT NULL,
  `employees_id_employee` int(11) NOT NULL,
  PRIMARY KEY (`id_payroll`,`employees_id_employee`,`month`,`year`),
  KEY `fk_payrolls_employees1_idx` (`employees_id_employee`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `phone_numbers`
--

DROP TABLE IF EXISTS `phone_numbers`;
CREATE TABLE IF NOT EXISTS `phone_numbers` (
  `id_phone_number` int(11) NOT NULL AUTO_INCREMENT,
  `phone_number` bigint(10) NOT NULL,
  `clients_id_clients` int(11) NOT NULL,
  PRIMARY KEY (`id_phone_number`,`clients_id_clients`),
  KEY `fk_emails_clients1_idx` (`clients_id_clients`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `phone_numbers`
--

INSERT INTO `phone_numbers` (`id_phone_number`, `phone_number`, `clients_id_clients`) VALUES
(1, 124323645, 1),
(2, 2143567, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `positions`
--

DROP TABLE IF EXISTS `positions`;
CREATE TABLE IF NOT EXISTS `positions` (
  `id_positions` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id_positions`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `positions_has_functions`
--

DROP TABLE IF EXISTS `positions_has_functions`;
CREATE TABLE IF NOT EXISTS `positions_has_functions` (
  `positions_id_positions` int(11) NOT NULL,
  `functions_id_functions` int(11) NOT NULL,
  PRIMARY KEY (`positions_id_positions`,`functions_id_functions`),
  KEY `fk_positions_has_functions_functions1_idx` (`functions_id_functions`),
  KEY `fk_positions_has_functions_positions_idx` (`positions_id_positions`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `products`
--

DROP TABLE IF EXISTS `products`;
CREATE TABLE IF NOT EXISTS `products` (
  `id_product` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` varchar(45) NOT NULL,
  `categories_products_id_categories_products` int(11) NOT NULL,
  `price` int(11) NOT NULL,
  PRIMARY KEY (`id_product`,`categories_products_id_categories_products`),
  KEY `fk_products_categories_products1_idx` (`categories_products_id_categories_products`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reports`
--

DROP TABLE IF EXISTS `reports`;
CREATE TABLE IF NOT EXISTS `reports` (
  `id_report` int(11) NOT NULL AUTO_INCREMENT,
  `report_file` blob NOT NULL,
  `month` enum('January') NOT NULL,
  `year` year(4) NOT NULL,
  PRIMARY KEY (`id_report`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sales`
--

DROP TABLE IF EXISTS `sales`;
CREATE TABLE IF NOT EXISTS `sales` (
  `id_sales` int(11) NOT NULL,
  `employees_id_employee` int(11) NOT NULL,
  `products_id_product` int(11) NOT NULL,
  `clients_id_clients` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_sales`,`employees_id_employee`,`products_id_product`,`clients_id_clients`),
  KEY `fk_sales_employees1_idx` (`employees_id_employee`),
  KEY `fk_sales_products1_idx` (`products_id_product`),
  KEY `fk_sales_clients1_idx` (`clients_id_clients`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `cellphone_numbers`
--
ALTER TABLE `cellphone_numbers`
  ADD CONSTRAINT `fk_emails_clients10` FOREIGN KEY (`clients_id_clients`) REFERENCES `clients` (`id_clients`) ON DELETE CASCADE ON UPDATE NO ACTION;

--
-- Filtros para la tabla `emails`
--
ALTER TABLE `emails`
  ADD CONSTRAINT `fk_emails_clients1` FOREIGN KEY (`clients_id_clients`) REFERENCES `clients` (`id_clients`) ON DELETE CASCADE ON UPDATE NO ACTION;

--
-- Filtros para la tabla `employees`
--
ALTER TABLE `employees`
  ADD CONSTRAINT `fk_employees_positions1` FOREIGN KEY (`positions_id_positions`) REFERENCES `positions` (`id_positions`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `events`
--
ALTER TABLE `events`
  ADD CONSTRAINT `fk_events_clients1` FOREIGN KEY (`clients_id_clients`) REFERENCES `clients` (`id_clients`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `payrolls`
--
ALTER TABLE `payrolls`
  ADD CONSTRAINT `fk_payrolls_employees1` FOREIGN KEY (`employees_id_employee`) REFERENCES `employees` (`id_employee`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `phone_numbers`
--
ALTER TABLE `phone_numbers`
  ADD CONSTRAINT `fk_emails_clients100` FOREIGN KEY (`clients_id_clients`) REFERENCES `clients` (`id_clients`) ON DELETE CASCADE ON UPDATE NO ACTION;

--
-- Filtros para la tabla `positions_has_functions`
--
ALTER TABLE `positions_has_functions`
  ADD CONSTRAINT `fk_positions_has_functions_functions1` FOREIGN KEY (`functions_id_functions`) REFERENCES `functions` (`id_functions`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_positions_has_functions_positions` FOREIGN KEY (`positions_id_positions`) REFERENCES `positions` (`id_positions`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `products`
--
ALTER TABLE `products`
  ADD CONSTRAINT `fk_products_categories_products1` FOREIGN KEY (`categories_products_id_categories_products`) REFERENCES `categories_products` (`id_categories_products`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `sales`
--
ALTER TABLE `sales`
  ADD CONSTRAINT `fk_sales_clients1` FOREIGN KEY (`clients_id_clients`) REFERENCES `clients` (`id_clients`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_sales_employees1` FOREIGN KEY (`employees_id_employee`) REFERENCES `employees` (`id_employee`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_sales_products1` FOREIGN KEY (`products_id_product`) REFERENCES `products` (`id_product`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
