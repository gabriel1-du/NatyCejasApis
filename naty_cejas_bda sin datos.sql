SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

CREATE DATABASE IF NOT EXISTS `naty_cejas_bda` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `naty_cejas_bda`;

CREATE TABLE `carrito` (
  `id_carrito` int(11) NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `fecha_creacion` timestamp NOT NULL DEFAULT current_timestamp(),
  `estado` varchar(50) DEFAULT 'ACTIVO'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `carrito_producto` (
  `id_carrito_producto` int(11) NOT NULL,
  `id_carrito` int(11) NOT NULL,
  `id_producto` int(11) NOT NULL,
  `cantidad` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `categoria_producto` (
  `id` int(11) NOT NULL,
  `nombre_categoria_producto` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `categoria_servicio` (
  `id_categoria_servicio` int(11) NOT NULL,
  `nombre_categoria_servicio` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `cita` (
  `id_cita` int(11) NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `id_servicio` int(11) NOT NULL,
  `fecha` date NOT NULL,
  `hora` time NOT NULL,
  `estado` varchar(50) DEFAULT 'PENDIENTE'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `comuna` (
  `id_comuna` int(11) NOT NULL,
  `nombre_comuna` varchar(100) NOT NULL,
  `id_region` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `marca` (
  `id_marca` int(11) NOT NULL,
  `nombre_marca` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `pedido` (
  `id_pedido` int(11) NOT NULL,
  `id_carrito` int(11) NOT NULL,
  `fecha` timestamp NOT NULL DEFAULT current_timestamp(),
  `total` int(11) NOT NULL,
  `estado` varchar(50) DEFAULT 'PAGADO'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `producto` (
  `id_producto` int(11) NOT NULL,
  `nombre_producto` varchar(255) NOT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `precio` int(11) NOT NULL,
  `stock` int(11) NOT NULL,
  `categoria` int(11) DEFAULT NULL,
  `id_marca` int(11) NOT NULL,
  `foto_url` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `region` (
  `id_region` int(11) NOT NULL,
  `nombre_region` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `servicio` (
  `id_servicio` int(11) NOT NULL,
  `id_categoria_servicio` int(11) NOT NULL,
  `nombre_servicio` varchar(100) NOT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `precio` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `usuario` (
  `id_usuario` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `apellido` varchar(100) NOT NULL,
  `rut` varchar(20) NOT NULL,
  `email` varchar(100) NOT NULL,
  `telefono` varchar(20) DEFAULT NULL,
  `direccion` varchar(200) DEFAULT NULL,
  `contrasena` varchar(255) NOT NULL,
  `admin` tinyint(1) DEFAULT 0,
  `region_usuario` int(11) DEFAULT NULL,
  `comuna_usuario` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


ALTER TABLE `carrito`
  ADD PRIMARY KEY (`id_carrito`),
  ADD KEY `id_usuario` (`id_usuario`);

ALTER TABLE `carrito_producto`
  ADD PRIMARY KEY (`id_carrito_producto`),
  ADD KEY `id_carrito` (`id_carrito`),
  ADD KEY `id_producto` (`id_producto`);

ALTER TABLE `categoria_producto`
  ADD PRIMARY KEY (`id`);

ALTER TABLE `categoria_servicio`
  ADD PRIMARY KEY (`id_categoria_servicio`);

ALTER TABLE `cita`
  ADD PRIMARY KEY (`id_cita`),
  ADD KEY `id_usuario` (`id_usuario`),
  ADD KEY `id_servicio` (`id_servicio`);

ALTER TABLE `comuna`
  ADD PRIMARY KEY (`id_comuna`),
  ADD KEY `id_region` (`id_region`);

ALTER TABLE `marca`
  ADD PRIMARY KEY (`id_marca`),
  ADD UNIQUE KEY `UKdvp887ivreeswvt44jpd50569` (`nombre_marca`);

ALTER TABLE `pedido`
  ADD PRIMARY KEY (`id_pedido`),
  ADD KEY `id_carrito` (`id_carrito`);

ALTER TABLE `producto`
  ADD PRIMARY KEY (`id_producto`),
  ADD KEY `fk_producto_categoria` (`categoria`),
  ADD KEY `FKpmfw7ds3rfuwge05ehb216r82` (`id_marca`);

ALTER TABLE `region`
  ADD PRIMARY KEY (`id_region`);

ALTER TABLE `servicio`
  ADD PRIMARY KEY (`id_servicio`),
  ADD KEY `id_categoria_servicio_pk` (`id_categoria_servicio`);

ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id_usuario`),
  ADD UNIQUE KEY `rut` (`rut`),
  ADD UNIQUE KEY `email` (`email`),
  ADD KEY `fk_region_usuario` (`region_usuario`),
  ADD KEY `fk_comuna_usuario` (`comuna_usuario`);


ALTER TABLE `carrito`
  MODIFY `id_carrito` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `carrito_producto`
  MODIFY `id_carrito_producto` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `categoria_producto`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `categoria_servicio`
  MODIFY `id_categoria_servicio` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `cita`
  MODIFY `id_cita` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `comuna`
  MODIFY `id_comuna` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `marca`
  MODIFY `id_marca` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `pedido`
  MODIFY `id_pedido` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `producto`
  MODIFY `id_producto` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `region`
  MODIFY `id_region` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `servicio`
  MODIFY `id_servicio` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `usuario`
  MODIFY `id_usuario` int(11) NOT NULL AUTO_INCREMENT;


ALTER TABLE `carrito`
  ADD CONSTRAINT `carrito_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`);

ALTER TABLE `carrito_producto`
  ADD CONSTRAINT `carrito_producto_ibfk_1` FOREIGN KEY (`id_carrito`) REFERENCES `carrito` (`id_carrito`),
  ADD CONSTRAINT `carrito_producto_ibfk_2` FOREIGN KEY (`id_producto`) REFERENCES `producto` (`id_producto`);

ALTER TABLE `cita`
  ADD CONSTRAINT `cita_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`),
  ADD CONSTRAINT `cita_ibfk_2` FOREIGN KEY (`id_servicio`) REFERENCES `servicio` (`id_servicio`);

ALTER TABLE `comuna`
  ADD CONSTRAINT `comuna_ibfk_1` FOREIGN KEY (`id_region`) REFERENCES `region` (`id_region`);

ALTER TABLE `pedido`
  ADD CONSTRAINT `pedido_ibfk_1` FOREIGN KEY (`id_carrito`) REFERENCES `carrito` (`id_carrito`);

ALTER TABLE `producto`
  ADD CONSTRAINT `FKpmfw7ds3rfuwge05ehb216r82` FOREIGN KEY (`id_marca`) REFERENCES `marca` (`id_marca`),
  ADD CONSTRAINT `fk_producto_categoria` FOREIGN KEY (`categoria`) REFERENCES `categoria_producto` (`id`);

ALTER TABLE `servicio`
  ADD CONSTRAINT `categoria_servicio_relacion` FOREIGN KEY (`id_categoria_servicio`) REFERENCES `categoria_servicio` (`id_categoria_servicio`);

ALTER TABLE `usuario`
  ADD CONSTRAINT `fk_comuna_usuario` FOREIGN KEY (`comuna_usuario`) REFERENCES `comuna` (`id_comuna`),
  ADD CONSTRAINT `fk_region_usuario` FOREIGN KEY (`region_usuario`) REFERENCES `region` (`id_region`);
COMMIT;

CREATE TABLE `boleta` (
  `id_boleta` INT(11) NOT NULL,
  `id_pedido` INT(11) NOT NULL,
  `id_usuario` INT(11) NOT NULL,
  `fecha_emision` TIMESTAMP NOT NULL DEFAULT current_timestamp(),
  `total` INT(11) NOT NULL,
  `estado` VARCHAR(50) DEFAULT 'EMITIDA'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `detalle_boleta` (
  `id_detalle_boleta` INT(11) NOT NULL,
  `id_boleta` INT(11) NOT NULL,
  `id_producto` INT(11) NOT NULL,
  `cantidad` INT(11) NOT NULL,
  `precio_unitario` INT(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

ALTER TABLE `boleta`
  ADD PRIMARY KEY (`id_boleta`),
  ADD KEY `id_pedido` (`id_pedido`),
  ADD KEY `id_usuario` (`id_usuario`);

ALTER TABLE `detalle_boleta`
  ADD PRIMARY KEY (`id_detalle_boleta`),
  ADD KEY `id_boleta` (`id_boleta`),
  ADD KEY `id_producto` (`id_producto`);

ALTER TABLE `boleta`
  MODIFY `id_boleta` INT(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `detalle_boleta`
  MODIFY `id_detalle_boleta` INT(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `boleta`
  ADD CONSTRAINT `boleta_ibfk_1` FOREIGN KEY (`id_pedido`) REFERENCES `pedido` (`id_pedido`),
  ADD CONSTRAINT `boleta_ibfk_2` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`);

ALTER TABLE `detalle_boleta`
  ADD CONSTRAINT `detalle_boleta_ibfk_1` FOREIGN KEY (`id_boleta`) REFERENCES `boleta` (`id_boleta`),
  ADD CONSTRAINT `detalle_boleta_ibfk_2` FOREIGN KEY (`id_producto`) REFERENCES `producto` (`id_producto`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
