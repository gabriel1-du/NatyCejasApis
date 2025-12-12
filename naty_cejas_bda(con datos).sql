-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 23-10-2025 a las 00:59:10
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `naty_cejas_bda`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `carrito`
--

CREATE TABLE `carrito` (
  `id_carrito` int(11) NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `fecha_creacion` timestamp NOT NULL DEFAULT current_timestamp(),
  `estado` varchar(50) DEFAULT 'ACTIVO'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `carrito`
--

INSERT INTO `carrito` (`id_carrito`, `id_usuario`, `fecha_creacion`, `estado`) VALUES
(1, 1, '2025-10-04 21:47:34', 'ACTIVO'),
(2, 2, '2025-10-05 00:40:18', 'Baneado'),
(4, 6, '2025-10-21 13:47:14', 'ACTIVO'),
(5, 6, '2025-10-21 13:52:36', 'ACTIVO'),
(9, 9, '2025-10-22 05:28:22', 'ACTIVO'),
(10, 9, '2025-10-22 05:28:34', 'ACTIVO'),
(11, 3, '2025-10-22 05:29:25', 'ACTIVO'),
(12, 8, '2025-10-22 15:23:28', 'ACTIVO'),
(13, 9, '2025-10-22 16:37:16', 'ACTIVO');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `carrito_producto`
--

CREATE TABLE `carrito_producto` (
  `id_carrito_producto` int(11) NOT NULL,
  `id_carrito` int(11) NOT NULL,
  `id_producto` int(11) NOT NULL,
  `cantidad` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `carrito_producto`
--

INSERT INTO `carrito_producto` (`id_carrito_producto`, `id_carrito`, `id_producto`, `cantidad`) VALUES
(1, 1, 4, 4),
(2, 1, 1, 2),
(3, 1, 1, 3),
(4, 4, 1, 2),
(5, 5, 1, 1),
(6, 9, 1, 1),
(7, 9, 4, 1),
(8, 9, 5, 1),
(9, 9, 6, 1),
(10, 10, 4, 1),
(11, 11, 1, 1),
(12, 12, 7, 1),
(13, 13, 7, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categoria_producto`
--

CREATE TABLE `categoria_producto` (
  `id` int(11) NOT NULL,
  `nombre_categoria_producto` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `categoria_producto`
--

INSERT INTO `categoria_producto` (`id`, `nombre_categoria_producto`) VALUES
(2, 'Jabon'),
(3, 'Crema facial'),
(4, 'Esmalte');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categoria_servicio`
--

CREATE TABLE `categoria_servicio` (
  `id_categoria_servicio` int(11) NOT NULL,
  `nombre_categoria_servicio` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `categoria_servicio`
--

INSERT INTO `categoria_servicio` (`id_categoria_servicio`, `nombre_categoria_servicio`) VALUES
(1, 'Depilacion'),
(2, 'Manicure / Pedicure'),
(3, 'Faciales'),
(4, 'Masajes');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cita`
--

CREATE TABLE `cita` (
  `id_cita` int(11) NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `id_servicio` int(11) NOT NULL,
  `fecha` date NOT NULL,
  `hora` time NOT NULL,
  `estado` varchar(50) DEFAULT 'PENDIENTE'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `cita`
--

INSERT INTO `cita` (`id_cita`, `id_usuario`, `id_servicio`, `fecha`, `hora`, `estado`) VALUES
(1, 1, 1, '2025-10-16', '15:32:22', 'Hola'),
(2, 1, 1, '2025-10-16', '15:32:50', 'Hola'),
(3, 1, 1, '2025-10-09', '18:28:00', 'PENDIENTE'),
(4, 2, 1, '2025-10-01', '02:35:00', 'PENDIENTE');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `comuna`
--

CREATE TABLE `comuna` (
  `id_comuna` int(11) NOT NULL,
  `nombre_comuna` varchar(100) NOT NULL,
  `id_region` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `comuna`
--

INSERT INTO `comuna` (`id_comuna`, `nombre_comuna`, `id_region`) VALUES
(1, 'Arica', 1),
(2, 'Camarones', 1),
(3, 'Putre', 1),
(4, 'General Lagos', 1),
(5, 'Iquique', 2),
(6, 'Alto Hospicio', 2),
(7, 'Pozo Almonte', 2),
(8, 'Pica', 2),
(9, 'Antofagasta', 3),
(10, 'Mejillones', 3),
(11, 'Taltal', 3),
(12, 'Calama', 3),
(13, 'San Pedro de Atacama', 3),
(14, 'Copiapó', 4),
(15, 'Caldera', 4),
(16, 'Vallenar', 4),
(17, 'Chañaral', 4),
(18, 'La Serena', 5),
(19, 'Coquimbo', 5),
(20, 'Ovalle', 5),
(21, 'Illapel', 5),
(22, 'Valparaíso', 6),
(23, 'Viña del Mar', 6),
(24, 'Quilpué', 6),
(25, 'Los Andes', 6),
(26, 'San Antonio', 6),
(27, 'Santiago', 7),
(28, 'Maipú', 7),
(29, 'Las Condes', 7),
(30, 'Puente Alto', 7),
(31, 'La Florida', 7),
(32, 'Ñuñoa', 7),
(33, 'Rancagua', 8),
(34, 'San Fernando', 8),
(35, 'Santa Cruz', 8),
(36, 'Machalí', 8),
(37, 'Talca', 9),
(38, 'Curicó', 9),
(39, 'Linares', 9),
(40, 'Cauquenes', 9),
(41, 'Chillán', 10),
(42, 'San Carlos', 10),
(43, 'Bulnes', 10),
(44, 'Quirihue', 10),
(45, 'Concepción', 11),
(46, 'Los Ángeles', 11),
(47, 'Talcahuano', 11),
(48, 'Coronel', 11),
(49, 'Lota', 11),
(50, 'Temuco', 12),
(51, 'Padre Las Casas', 12),
(52, 'Angol', 12),
(53, 'Villarrica', 12),
(54, 'Pucón', 12),
(55, 'Valdivia', 13),
(56, 'La Unión', 13),
(57, 'Río Bueno', 13),
(58, 'Panguipulli', 13),
(59, 'Puerto Montt', 14),
(60, 'Osorno', 14),
(61, 'Castro', 14),
(62, 'Ancud', 14),
(63, 'Chonchi', 14),
(64, 'Coyhaique', 15),
(65, 'Puerto Aysén', 15),
(66, 'Cochrane', 15),
(67, 'Chile Chico', 15),
(68, 'Punta Arenas', 16),
(69, 'Puerto Natales', 16),
(70, 'Porvenir', 16),
(71, 'Cabo de Hornos', 16),
(72, 'Arica', 1),
(73, 'Camarones', 1),
(74, 'Putre', 1),
(75, 'General Lagos', 1),
(76, 'Iquique', 2),
(77, 'Alto Hospicio', 2),
(78, 'Pozo Almonte', 2),
(79, 'Pica', 2),
(80, 'Huara', 2),
(81, 'Camiña', 2),
(82, 'Colchane', 2),
(83, 'Antofagasta', 3),
(84, 'Mejillones', 3),
(85, 'Sierra Gorda', 3),
(86, 'Taltal', 3),
(87, 'Calama', 3),
(88, 'San Pedro de Atacama', 3),
(89, 'María Elena', 3),
(90, 'Tocopilla', 3),
(91, 'Copiapó', 4),
(92, 'Caldera', 4),
(93, 'Tierra Amarilla', 4),
(94, 'Chañaral', 4),
(95, 'Diego de Almagro', 4),
(96, 'Vallenar', 4),
(97, 'Freirina', 4),
(98, 'Huasco', 4),
(99, 'Alto del Carmen', 4),
(100, 'La Serena', 5),
(101, 'Coquimbo', 5),
(102, 'Andacollo', 5),
(103, 'Vicuña', 5),
(104, 'Paiguano', 5),
(105, 'Ovalle', 5),
(106, 'Monte Patria', 5),
(107, 'Punitaqui', 5),
(108, 'Combarbalá', 5),
(109, 'Illapel', 5),
(110, 'Salamanca', 5),
(111, 'Los Vilos', 5),
(112, 'Valparaíso', 6),
(113, 'Viña del Mar', 6),
(114, 'Concón', 6),
(115, 'Quilpué', 6),
(116, 'Villa Alemana', 6),
(117, 'Casablanca', 6),
(118, 'San Antonio', 6),
(119, 'Cartagena', 6),
(120, 'El Quisco', 6),
(121, 'El Tabo', 6),
(122, 'Algarrobo', 6),
(123, 'La Ligua', 6),
(124, 'Papudo', 6),
(125, 'Zapallar', 6),
(126, 'Quillota', 6),
(127, 'La Calera', 6),
(128, 'Nogales', 6),
(129, 'Hijuelas', 6),
(130, 'Los Andes', 6),
(131, 'San Felipe', 6),
(132, 'Putaendo', 6),
(133, 'Llaillay', 6),
(134, 'Santiago', 7),
(135, 'Providencia', 7),
(136, 'Las Condes', 7),
(137, 'Vitacura', 7),
(138, 'Ñuñoa', 7),
(139, 'Maipú', 7),
(140, 'Cerrillos', 7),
(141, 'Estación Central', 7),
(142, 'Pedro Aguirre Cerda', 7),
(143, 'San Miguel', 7),
(144, 'Puente Alto', 7),
(145, 'La Florida', 7),
(146, 'Peñalolén', 7),
(147, 'La Reina', 7),
(148, 'Recoleta', 7),
(149, 'Huechuraba', 7),
(150, 'Conchalí', 7),
(151, 'Lo Barnechea', 7),
(152, 'San Bernardo', 7),
(153, 'El Bosque', 7),
(154, 'La Cisterna', 7),
(155, 'Lo Espejo', 7),
(156, 'Independencia', 7),
(157, 'Rancagua', 8),
(158, 'Machalí', 8),
(159, 'Graneros', 8),
(160, 'San Fernando', 8),
(161, 'Santa Cruz', 8),
(162, 'Palmilla', 8),
(163, 'Chimbarongo', 8),
(164, 'Nancagua', 8),
(165, 'Pichilemu', 8),
(166, 'Marchigüe', 8),
(167, 'San Vicente', 8),
(168, 'Requínoa', 8),
(169, 'Talca', 9),
(170, 'Maule', 9),
(171, 'San Clemente', 9),
(172, 'Curicó', 9),
(173, 'Molina', 9),
(174, 'Rauco', 9),
(175, 'Sagrada Familia', 9),
(176, 'Linares', 9),
(177, 'Yerbas Buenas', 9),
(178, 'Colbún', 9),
(179, 'Cauquenes', 9),
(180, 'Chanco', 9),
(181, 'Pelluhue', 9),
(182, 'Constitución', 9),
(183, 'Empedrado', 9),
(184, 'Chillán', 10),
(185, 'Chillán Viejo', 10),
(186, 'San Carlos', 10),
(187, 'Coihueco', 10),
(188, 'Bulnes', 10),
(189, 'Quirihue', 10),
(190, 'Ninhue', 10),
(191, 'Cobquecura', 10),
(192, 'Pinto', 10),
(193, 'El Carmen', 10),
(194, 'Concepción', 11),
(195, 'Talcahuano', 11),
(196, 'Hualpén', 11),
(197, 'Chiguayante', 11),
(198, 'San Pedro de la Paz', 11),
(199, 'Coronel', 11),
(200, 'Lota', 11),
(201, 'Los Ángeles', 11),
(202, 'Nacimiento', 11),
(203, 'Yumbel', 11),
(204, 'Cabrero', 11),
(205, 'Mulchén', 11),
(206, 'Arauco', 11),
(207, 'Cañete', 11),
(208, 'Curanilahue', 11),
(209, 'Temuco', 12),
(210, 'Padre Las Casas', 12),
(211, 'Vilcún', 12),
(212, 'Freire', 12),
(213, 'Pucón', 12),
(214, 'Villarrica', 12),
(215, 'Gorbea', 12),
(216, 'Toltén', 12),
(217, 'Angol', 12),
(218, 'Collipulli', 12),
(219, 'Traiguén', 12),
(220, 'Victoria', 12),
(221, 'Valdivia', 13),
(222, 'Corral', 13),
(223, 'Paillaco', 13),
(224, 'Lanco', 13),
(225, 'Los Lagos', 13),
(226, 'Máfil', 13),
(227, 'Panguipulli', 13),
(228, 'La Unión', 13),
(229, 'Río Bueno', 13),
(230, 'Futrono', 13),
(231, 'Puerto Montt', 14),
(232, 'Puerto Varas', 14),
(233, 'Llanquihue', 14),
(234, 'Frutillar', 14),
(235, 'Fresia', 14),
(236, 'Los Muermos', 14),
(237, 'Osorno', 14),
(238, 'Río Negro', 14),
(239, 'Purranque', 14),
(240, 'San Pablo', 14),
(241, 'Castro', 14),
(242, 'Ancud', 14),
(243, 'Chonchi', 14),
(244, 'Dalcahue', 14),
(245, 'Quellón', 14),
(246, 'Coyhaique', 15),
(247, 'Lago Verde', 15),
(248, 'Puerto Aysén', 15),
(249, 'Cisnes', 15),
(250, 'Guaitecas', 15),
(251, 'Cochrane', 15),
(252, 'O’Higgins', 15),
(253, 'Tortel', 15),
(254, 'Chile Chico', 15),
(255, 'Río Ibáñez', 15),
(256, 'Punta Arenas', 16),
(257, 'Río Verde', 16),
(258, 'Laguna Blanca', 16),
(259, 'San Gregorio', 16),
(260, 'Porvenir', 16),
(261, 'Primavera', 16),
(262, 'Timaukel', 16),
(263, 'Puerto Natales', 16),
(264, 'Torres del Paine', 16),
(265, 'Cabo de Hornos', 16);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `marca`
--

CREATE TABLE `marca` (
  `id_marca` int(11) NOT NULL,
  `nombre_marca` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `marca`
--

INSERT INTO `marca` (`id_marca`, `nombre_marca`) VALUES
(1, 'Loreal'),
(4, 'Natura'),
(2, 'Nivea');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pedido`
--

CREATE TABLE `pedido` (
  `id_pedido` int(11) NOT NULL,
  `id_carrito` int(11) NOT NULL,
  `fecha` timestamp NOT NULL DEFAULT current_timestamp(),
  `total` int(11) NOT NULL,
  `estado` varchar(50) DEFAULT 'PAGADO'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `pedido`
--

INSERT INTO `pedido` (`id_pedido`, `id_carrito`, `fecha`, `total`, `estado`) VALUES
(1, 1, '2025-10-04 21:48:05', 30000, 'Sin Entregar'),
(2, 1, '2025-10-04 22:10:50', 20000, 'PAGADO'),
(3, 4, '2025-10-21 13:47:16', 2, 'PAGADO'),
(4, 5, '2025-10-21 13:52:36', 1, 'PAGADO'),
(5, 9, '2025-10-22 05:28:22', 4, 'PAGADO'),
(6, 10, '2025-10-22 05:28:34', 1, 'PAGADO'),
(7, 11, '2025-10-22 05:29:25', 1, 'PAGADO'),
(8, 12, '2025-10-22 15:23:28', 1, 'PAGADO'),
(9, 13, '2025-10-22 16:37:16', 1, 'PAGADO');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto`
--

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

--
-- Volcado de datos para la tabla `producto`
--

INSERT INTO `producto` (`id_producto`, `nombre_producto`, `descripcion`, `precio`, `stock`, `categoria`, `id_marca`, `foto_url`) VALUES
(11, 'Jabón de Manos Aloe', 'Limpieza suave con extracto de aloe vera.', 2990, 40, 2, 1, 'https://clarel.imgix.net/media/catalog/product/2/8/286450_1_6478.jpg?auto=format&fit=max&w=480&q=100'),
(12, 'Jabón Exfoliante Avena', 'Exfoliación ligera para uso diario.', 3490, 30, 2, 2, 'https://unimarc.vtexassets.com/arquivos/ids/246239/000000000670648001-UN-01.jpg?v=638717646147630000'),
(13, 'Jabón Líquido Antibacterial', 'Elimina el 99.9% de bacterias, uso familiar.', 3990, 25, 2, 1, 'https://static.preunic.cl/09tlhljbrtnhs6szz80aug78y7ba'),
(14, 'Jabón de Glicerina', 'Hipoalergénico, apto para piel sensible.', 2790, 35, 2, 2, 'https://img.nivea.com/-/media/miscellaneous/media-center-items/8/4/1/684866015e1d48cc8cd9700aaee74a4d-web_1010x1180_transparent_png.webp?mw=640&hash=4CCD2BBCEE93E62F05829A8722ED5E8B'),
(15, 'Crema Hidratante Día', 'Hidratación 24h con ácido hialurónico.', 12990, 18, 3, 1, 'https://www.lorealparis.cl/-/media/project/loreal/brand-sites/oap/americas/latam/products/skin-care/face-care/ht5/ht5-dia/ht5-crema-dia-humectante-packshot.png'),
(16, 'Crema Antiage Noche', 'Con retinol y vitamina E, suaviza líneas finas.', 15990, 12, 3, 2, 'https://img.nivea.com/-/media/miscellaneous/media-center-items/9/c/6/2c33db27b7104488ad4bcbe5cb4007dd-web_1010x1180_transparent_png.webp?mw=640&hash=C999F0BD5AED13318EF59FEB49ED69E8'),
(17, 'Crema Matificante', 'Control de brillo para piel mixta/grasa.', 9990, 22, 3, 1, 'https://www.lorealparis.cl/-/media/project/loreal/brand-sites/oap/americas/latam/products/skin-care/face-care/ht5/ht5-matificante/ht5-crema-matificante-packshot.png'),
(18, 'Crema Calmante', 'Con aloe y manzanilla, reduce enrojecimiento.', 10990, 20, 3, 2, 'https://img.nivea.com/-/media/miscellaneous/media-center-items/5/d/0/a23ac3c634ee4af29860ae50662e6c20-screen.webp?mw=640&hash=5057A1913F06C76AEB6E746A6C99D6C6'),
(19, 'Esmalte Rojo Clásico', 'Color intenso, acabado brillante.', 3990, 30, 4, 1, 'https://cl-dam-resizer.ecomm.cencosud.com/unsafe/adaptive-fit-in/640x0/filters:quality(75)/paris/747386999/variant/images/688c9f9b-8809-4549-9264-6675621b8d79/747386999-0000-002.jpg'),
(20, 'Esmalte Nude Arena', 'Tono natural, secado rápido.', 3990, 28, 4, 2, 'https://http2.mlstatic.com/D_NQ_NP_2X_727971-MLA29674231320_032019-F.webp'),
(21, 'Esmalte Rosa Pastel', 'Fórmula hipoalergénica y durable.', 3990, 26, 4, 1, 'https://i.ebayimg.com/images/g/epkAAeSw759omAGm/s-l1600.webp'),
(22, 'Top Coat Brillo', 'Sellador de larga duración y alto brillo.', 4490, 35, 4, 2, 'https://dbs.cl/media/catalog/product/o/p/opi-opi-ist31.jpg?optimize=low&bg-color=255,255,255&fit=bounds&height=&width=&canvas=:');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `region`
--

CREATE TABLE `region` (
  `id_region` int(11) NOT NULL,
  `nombre_region` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `region`
--

INSERT INTO `region` (`id_region`, `nombre_region`) VALUES
(1, 'Región de Arica y Parinacota'),
(2, 'Región de Tarapacá'),
(3, 'Región de Antofagasta'),
(4, 'Región de Atacama'),
(5, 'Región de Coquimbo'),
(6, 'Región de Valparaíso'),
(7, 'Región Metropolitana de Santiago'),
(8, 'Región del Libertador General Bernardo O’Higgins'),
(9, 'Región del Maule'),
(10, 'Región de Ñuble'),
(11, 'Región del Biobío'),
(12, 'Región de La Araucanía'),
(13, 'Región de Los Ríos'),
(14, 'Región de Los Lagos'),
(15, 'Región de Aysén del General Carlos Ibáñez del Campo'),
(16, 'Región de Magallanes y de la Antártica Chilena'),
(17, 'Región de Arica y Parinacota'),
(18, 'Región de Tarapacá'),
(19, 'Región de Antofagasta'),
(20, 'Región de Atacama'),
(21, 'Región de Coquimbo'),
(22, 'Región de Valparaíso'),
(23, 'Región Metropolitana de Santiago'),
(24, 'Región del Libertador General Bernardo O’Higgins'),
(25, 'Región del Maule'),
(26, 'Región de Ñuble'),
(27, 'Región del Biobío'),
(28, 'Región de La Araucanía'),
(29, 'Región de Los Ríos'),
(30, 'Región de Los Lagos'),
(31, 'Región de Aysén del General Carlos Ibáñez del Campo'),
(32, 'Región de Magallanes y de la Antártica Chilena');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `servicio`
--

CREATE TABLE `servicio` (
  `id_servicio` int(11) NOT NULL,
  `id_categoria_servicio` int(11) NOT NULL,
  `nombre_servicio` varchar(100) NOT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `precio` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `servicio`
--

INSERT INTO `servicio` (`id_servicio`, `id_categoria_servicio`, `nombre_servicio`, `descripcion`, `precio`) VALUES
(1, 1, 'Depilacion Piernas', 'depilacion con cera a las pienas', 1500),
(2, 1, 'Depilacion Zona Genital Varon', 'depilacion para la zona genital del varon con cera', 13000),
(3, 1, 'Depilacion Zona corporal completo', 'depilacion completo exceptuando la zona genital', 26000),
(4, 1, 'Depilacion piernas baron', 'Depilacion completa de la zona de piernas', 12000),
(5, 1, 'Depilación Medias Piernas', 'Depilación con cera desde rodilla hacia abajo (ambas piernas).', 10000),
(6, 1, 'Depilación Axilas', 'Depilación con cera tibia en ambas axilas.', 7000),
(7, 2, 'Manicure Tradicional', 'Limpieza, limado, cutícula y esmaltado tradicional.', 9000),
(8, 2, 'Manicure Permanente (Gel)', 'Esmaltado permanente con lámpara LED; incluye limado y cutícula.', 14000),
(9, 2, 'Pedicure Tradicional', 'Remoción de durezas, limado y esmaltado tradicional.', 12000),
(10, 3, 'Limpieza Facial Profunda', 'Exfoliación, extracción, mascarilla y masaje facial.', 18000),
(11, 3, 'Hidratación Facial Intensiva', 'Tratamiento hidratante con ácido hialurónico.', 16000),
(12, 3, 'Lifting de Pestañas', 'Lifting + nutrición; duración aproximada 6–8 semanas.', 18000),
(13, 4, 'Masaje de Relajación 45’', 'Técnicas de relajación cuerpo completo.', 18000),
(14, 4, 'Masaje Descontracturante 60’', 'Foco en cuello, espalda y trapecios para liberar tensiones.', 24000);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

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

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id_usuario`, `nombre`, `apellido`, `rut`, `email`, `telefono`, `direccion`, `contrasena`, `admin`, `region_usuario`, `comuna_usuario`) VALUES
(1, 'Gabriel', 'Santana', '217659949', 'gabrielduran@gmail.com', '987987987987', 'direccion de gabriel', 'contrasena', 1, NULL, NULL),
(2, 'Josefa', 'Hernandez', '9099223', 'Josefa@gmail.com', '000999990', 'Josefa direccion', 'josefacontrasena', 0, NULL, NULL),
(3, 'Checkout', 'Tester', '99.999.999-9', 'checkout_tester_999@example.com', '123456789', 'Calle Falsa 123', 'secret', 0, NULL, NULL),
(4, 'Esperanza', 'Lizeth', '15.785.682-0', 'esperanza@gmail.com', '977887788', 'Región Metropolitana, Puente Alto, calle super bavan', 'contrasena', 0, NULL, NULL),
(6, 'Fukuro', 'Lady', '22.222.222-2', 'Fukuro@gmail.com', '987561423', 'Región Metropolitana, Maipú, casita donde vive fukuro', 'contrasena', 0, NULL, NULL),
(8, 'Riku', 'Minato', '222222222', 'Riku@gmail.com', '2', 'Casa de riku minato', 'Contrasena!2', 0, 12, 215),
(9, 'Bryan', 'Jimenez', '216653939', 'bryanjimenez76.bj@gmail.com', '', 'escritor pedro prado ', '195723bB+', 1, 7, 140),
(12, 'bryan', 'jimenez', '123123123', 'bryan123@gmail.com', '223123123', 'pedro prado', '195723bB+', 0, 15, 254);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `carrito`
--
ALTER TABLE `carrito`
  ADD PRIMARY KEY (`id_carrito`),
  ADD KEY `id_usuario` (`id_usuario`);

--
-- Indices de la tabla `carrito_producto`
--
ALTER TABLE `carrito_producto`
  ADD PRIMARY KEY (`id_carrito_producto`),
  ADD KEY `id_carrito` (`id_carrito`),
  ADD KEY `id_producto` (`id_producto`);

--
-- Indices de la tabla `categoria_producto`
--
ALTER TABLE `categoria_producto`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `categoria_servicio`
--
ALTER TABLE `categoria_servicio`
  ADD PRIMARY KEY (`id_categoria_servicio`);

--
-- Indices de la tabla `cita`
--
ALTER TABLE `cita`
  ADD PRIMARY KEY (`id_cita`),
  ADD KEY `id_usuario` (`id_usuario`),
  ADD KEY `id_servicio` (`id_servicio`);

--
-- Indices de la tabla `comuna`
--
ALTER TABLE `comuna`
  ADD PRIMARY KEY (`id_comuna`),
  ADD KEY `id_region` (`id_region`);

--
-- Indices de la tabla `marca`
--
ALTER TABLE `marca`
  ADD PRIMARY KEY (`id_marca`),
  ADD UNIQUE KEY `UKdvp887ivreeswvt44jpd50569` (`nombre_marca`);

--
-- Indices de la tabla `pedido`
--
ALTER TABLE `pedido`
  ADD PRIMARY KEY (`id_pedido`),
  ADD KEY `id_carrito` (`id_carrito`);

--
-- Indices de la tabla `producto`
--
ALTER TABLE `producto`
  ADD PRIMARY KEY (`id_producto`),
  ADD KEY `fk_producto_categoria` (`categoria`),
  ADD KEY `FKpmfw7ds3rfuwge05ehb216r82` (`id_marca`);

--
-- Indices de la tabla `region`
--
ALTER TABLE `region`
  ADD PRIMARY KEY (`id_region`);

--
-- Indices de la tabla `servicio`
--
ALTER TABLE `servicio`
  ADD PRIMARY KEY (`id_servicio`),
  ADD KEY `id_categoria_servicio_pk` (`id_categoria_servicio`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id_usuario`),
  ADD UNIQUE KEY `rut` (`rut`),
  ADD UNIQUE KEY `email` (`email`),
  ADD KEY `fk_region_usuario` (`region_usuario`),
  ADD KEY `fk_comuna_usuario` (`comuna_usuario`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `carrito`
--
ALTER TABLE `carrito`
  MODIFY `id_carrito` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT de la tabla `carrito_producto`
--
ALTER TABLE `carrito_producto`
  MODIFY `id_carrito_producto` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT de la tabla `categoria_producto`
--
ALTER TABLE `categoria_producto`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `categoria_servicio`
--
ALTER TABLE `categoria_servicio`
  MODIFY `id_categoria_servicio` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `cita`
--
ALTER TABLE `cita`
  MODIFY `id_cita` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `comuna`
--
ALTER TABLE `comuna`
  MODIFY `id_comuna` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=266;

--
-- AUTO_INCREMENT de la tabla `marca`
--
ALTER TABLE `marca`
  MODIFY `id_marca` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `pedido`
--
ALTER TABLE `pedido`
  MODIFY `id_pedido` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de la tabla `producto`
--
ALTER TABLE `producto`
  MODIFY `id_producto` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;

--
-- AUTO_INCREMENT de la tabla `region`
--
ALTER TABLE `region`
  MODIFY `id_region` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;

--
-- AUTO_INCREMENT de la tabla `servicio`
--
ALTER TABLE `servicio`
  MODIFY `id_servicio` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id_usuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `carrito`
--
ALTER TABLE `carrito`
  ADD CONSTRAINT `carrito_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`);

--
-- Filtros para la tabla `carrito_producto`
--
ALTER TABLE `carrito_producto`
  ADD CONSTRAINT `carrito_producto_ibfk_1` FOREIGN KEY (`id_carrito`) REFERENCES `carrito` (`id_carrito`),
  ADD CONSTRAINT `carrito_producto_ibfk_2` FOREIGN KEY (`id_producto`) REFERENCES `producto` (`id_producto`);

--
-- Filtros para la tabla `cita`
--
ALTER TABLE `cita`
  ADD CONSTRAINT `cita_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`),
  ADD CONSTRAINT `cita_ibfk_2` FOREIGN KEY (`id_servicio`) REFERENCES `servicio` (`id_servicio`);

--
-- Filtros para la tabla `comuna`
--
ALTER TABLE `comuna`
  ADD CONSTRAINT `comuna_ibfk_1` FOREIGN KEY (`id_region`) REFERENCES `region` (`id_region`);

--
-- Filtros para la tabla `pedido`
--
ALTER TABLE `pedido`
  ADD CONSTRAINT `pedido_ibfk_1` FOREIGN KEY (`id_carrito`) REFERENCES `carrito` (`id_carrito`);

--
-- Filtros para la tabla `producto`
--
ALTER TABLE `producto`
  ADD CONSTRAINT `FKpmfw7ds3rfuwge05ehb216r82` FOREIGN KEY (`id_marca`) REFERENCES `marca` (`id_marca`),
  ADD CONSTRAINT `fk_producto_categoria` FOREIGN KEY (`categoria`) REFERENCES `categoria_producto` (`id`);

--
-- Filtros para la tabla `servicio`
--
ALTER TABLE `servicio`
  ADD CONSTRAINT `categoria_servicio_relacion` FOREIGN KEY (`id_categoria_servicio`) REFERENCES `categoria_servicio` (`id_categoria_servicio`);

--
-- Filtros para la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD CONSTRAINT `fk_comuna_usuario` FOREIGN KEY (`comuna_usuario`) REFERENCES `comuna` (`id_comuna`),
  ADD CONSTRAINT `fk_region_usuario` FOREIGN KEY (`region_usuario`) REFERENCES `region` (`id_region`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
