CREATE DATABASE  IF NOT EXISTS `tesis_playon` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `tesis_playon`;
-- MySQL dump 10.13  Distrib 5.5.31, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: tesis_playon
-- ------------------------------------------------------
-- Server version	5.5.31-0ubuntu0.12.10.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `publicidad`
--

DROP TABLE IF EXISTS `publicidad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `publicidad` (
  `publicidadID` int(11) NOT NULL AUTO_INCREMENT,
  `estadoPublicidadID` int(11) DEFAULT NULL,
  `nombreEmpresa` varchar(45) DEFAULT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `apellido` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `telefono` varchar(45) DEFAULT NULL,
  `fechaDesde` date DEFAULT NULL,
  `fechaHasta` date DEFAULT NULL,
  `fotoPublicidadID` int(11) DEFAULT NULL,
  `precio` float DEFAULT NULL,
  PRIMARY KEY (`publicidadID`),
  KEY `fk_publicidad_foto_publicidad` (`fotoPublicidadID`),
  KEY `fk_publicidad_estado_publicidad` (`estadoPublicidadID`),
  CONSTRAINT `fk_publicidad_estado_publicidad` FOREIGN KEY (`estadoPublicidadID`) REFERENCES `estado_publicidad` (`estadoPublicidadID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_publicidad_foto_publicidad` FOREIGN KEY (`fotoPublicidadID`) REFERENCES `foto_publicidad` (`fotoPublicidadID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `publicidad`
--

LOCK TABLES `publicidad` WRITE;
/*!40000 ALTER TABLE `publicidad` DISABLE KEYS */;
/*!40000 ALTER TABLE `publicidad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_pago`
--

DROP TABLE IF EXISTS `tipo_pago`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipo_pago` (
  `descripcion` text,
  `nombre` varchar(50) NOT NULL,
  `tipoPagoID` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`tipoPagoID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_pago`
--

LOCK TABLES `tipo_pago` WRITE;
/*!40000 ALTER TABLE `tipo_pago` DISABLE KEYS */;
INSERT INTO `tipo_pago` VALUES ('Contado Efectivo','Contado',1),('Tarjeta de débito','Tarjeta Débito',2),('Tarjeta de crédito','Tarjeta Crédito',3),('DineroMail','DineroMail',4),('Cheque','Cheque',5),('Pago con saldo de la cuenta del cliente','Cuenta',6);
/*!40000 ALTER TABLE `tipo_pago` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles_por_usuario`
--

DROP TABLE IF EXISTS `roles_por_usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roles_por_usuario` (
  `rolesPorUsuarioID` int(11) NOT NULL AUTO_INCREMENT,
  `usuario` varchar(50) NOT NULL,
  `rolUsuario` varchar(50) NOT NULL,
  PRIMARY KEY (`rolesPorUsuarioID`),
  KEY `usuario` (`usuario`),
  KEY `rolUsuario` (`rolUsuario`),
  CONSTRAINT `FK_roles_por_usuario_usuario` FOREIGN KEY (`usuario`) REFERENCES `usuario` (`usuario`),
  CONSTRAINT `FK_roles_por_usuario_rol_usuario` FOREIGN KEY (`rolUsuario`) REFERENCES `rol_usuario` (`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles_por_usuario`
--

LOCK TABLES `roles_por_usuario` WRITE;
/*!40000 ALTER TABLE `roles_por_usuario` DISABLE KEYS */;
INSERT INTO `roles_por_usuario` VALUES (1,'pablo','ROLE_PLAYA_GERENTE'),(2,'alejandro','ROLE_AUDITOR'),(3,'gonzalo','ROLE_CLIENT'),(4,'gmorales','ROLE_ADMIN'),(5,'eric','ROLE_PLAYA_EMPLEADO'),(6,'admin','ROLE_ADMIN'),(7,'sgomez','ROLE_CLIENT'),(8,'jjlopez','ROLE_CLIENT'),(9,'jperez','ROLE_CLIENT'),(10,'rsantoni','ROLE_PLAYA_GERENTE'),(11,'richardparking','ROLE_PLAYA_GERENTE'),(12,'ricardoempleado','ROLE_PLAYA_EMPLEADO'),(13,'pargento','ROLE_PLAYA_GERENTE');
/*!40000 ALTER TABLE `roles_por_usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `calificacion`
--

DROP TABLE IF EXISTS `calificacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `calificacion` (
  `calificacionID` int(11) NOT NULL AUTO_INCREMENT,
  `playaID` int(11) NOT NULL,
  `clienteID` int(11) NOT NULL,
  `calificacion` int(2) NOT NULL,
  PRIMARY KEY (`calificacionID`),
  KEY `playaID` (`playaID`),
  KEY `clienteID` (`clienteID`),
  CONSTRAINT `FK_calificacion_playa` FOREIGN KEY (`playaID`) REFERENCES `playa` (`playaID`),
  CONSTRAINT `FK_calificacion_cliente` FOREIGN KEY (`clienteID`) REFERENCES `cliente` (`clienteID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `calificacion`
--

LOCK TABLES `calificacion` WRITE;
/*!40000 ALTER TABLE `calificacion` DISABLE KEYS */;
INSERT INTO `calificacion` VALUES (1,11,1,9);
/*!40000 ALTER TABLE `calificacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaccion_cliente`
--

DROP TABLE IF EXISTS `transaccion_cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transaccion_cliente` (
  `fecha` datetime NOT NULL,
  `importe` float NOT NULL,
  `tipoPagoID` int(11) NOT NULL,
  `transaccionClienteID` int(11) NOT NULL AUTO_INCREMENT,
  `cuentaClienteID` int(11) NOT NULL,
  PRIMARY KEY (`transaccionClienteID`),
  KEY `cuentaClienteID` (`cuentaClienteID`),
  KEY `tipoPagoID` (`tipoPagoID`),
  CONSTRAINT `FK_transaccion_cliente_tipo_pago` FOREIGN KEY (`tipoPagoID`) REFERENCES `tipo_pago` (`tipoPagoID`),
  CONSTRAINT `FK_transaccion_cliente_cuenta_cliente` FOREIGN KEY (`cuentaClienteID`) REFERENCES `cuenta_cliente` (`cuentaClienteID`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaccion_cliente`
--

LOCK TABLES `transaccion_cliente` WRITE;
/*!40000 ALTER TABLE `transaccion_cliente` DISABLE KEYS */;
INSERT INTO `transaccion_cliente` VALUES ('2012-08-03 13:23:45',100,4,1,1),('2012-08-04 19:25:23',20,6,2,1),('2012-08-11 18:45:21',10,6,3,1),('2012-08-18 18:32:49',10,6,4,1),('2013-06-15 18:39:42',10,6,5,4),('2013-06-16 18:40:01',10,6,6,4),('2013-06-20 19:45:52',12,6,7,3),('2013-04-05 20:08:58',12,6,8,3),('2013-04-10 20:09:08',12,6,9,3),('2013-04-12 20:09:16',12,6,10,3),('2013-04-15 20:09:23',12,6,11,3),('2013-04-22 20:09:30',10,6,12,4),('2013-05-02 20:09:46',10,6,13,3),('2013-05-06 20:10:02',12,6,14,3),('2013-05-10 20:10:10',12,6,15,4),('2013-05-11 20:10:17',12,6,16,3),('2013-05-17 20:10:24',12,6,17,4),('2013-05-26 20:10:31',12,6,18,3),('2013-06-17 18:39:42',10,6,19,4),('2013-06-18 18:40:01',10,6,20,4),('2013-06-21 19:45:52',12,6,21,3),('2013-04-06 20:08:58',12,6,22,3),('2013-04-11 20:09:08',12,6,23,3),('2013-04-14 20:09:16',12,6,24,3),('2013-04-16 20:09:23',12,6,25,3),('2013-04-23 20:09:30',10,6,26,4),('2013-05-03 20:09:46',10,6,27,4),('2013-05-07 20:10:02',12,6,28,3),('2013-05-12 20:10:10',12,6,29,3),('2013-05-13 20:10:17',12,6,30,3),('2013-05-18 20:10:24',12,6,31,3),('2013-05-27 20:10:31',12,6,32,3),('2013-06-19 18:39:42',12,6,33,4),('2013-06-20 18:40:01',12,6,34,4),('2013-06-22 19:45:52',14,6,35,3),('2013-04-07 20:08:58',14,6,36,3),('2013-04-12 20:09:08',14,6,37,4),('2013-04-15 20:09:16',14,6,38,3),('2013-04-17 20:09:23',14,6,39,3),('2013-04-24 20:09:30',12,6,40,4),('2013-05-04 20:09:46',12,6,41,4),('2013-05-08 20:10:02',14,6,42,3),('2013-05-14 20:10:10',14,6,43,3),('2013-05-15 20:10:17',14,6,44,3),('2013-05-19 20:10:24',14,6,45,3),('2013-05-28 20:10:31',14,6,46,3),('2013-06-18 13:23:45',90,4,47,1),('2013-04-20 19:25:23',50,4,48,2),('2013-06-18 18:45:21',500,4,49,3),('2013-06-13 18:32:49',300,4,50,4);
/*!40000 ALTER TABLE `transaccion_cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cuenta_playa`
--

DROP TABLE IF EXISTS `cuenta_playa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cuenta_playa` (
  `fechaCreacion` datetime NOT NULL,
  `nroCuenta` int(11) DEFAULT NULL,
  `saldo` float NOT NULL DEFAULT '0',
  `cuentaPlayaID` int(11) NOT NULL AUTO_INCREMENT,
  `playaID` int(11) DEFAULT NULL,
  PRIMARY KEY (`cuentaPlayaID`),
  KEY `playaID` (`playaID`),
  CONSTRAINT `FK_cuenta_playa_playa` FOREIGN KEY (`playaID`) REFERENCES `playa` (`playaID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cuenta_playa`
--

LOCK TABLES `cuenta_playa` WRITE;
/*!40000 ALTER TABLE `cuenta_playa` DISABLE KEYS */;
INSERT INTO `cuenta_playa` VALUES ('2012-08-22 00:00:00',1,200,1,3),('2012-08-22 00:00:00',2,160,2,4),('2012-08-22 00:00:00',3,0,3,5),('2012-08-22 00:00:00',4,0,4,6),('2012-08-22 00:00:00',5,0,5,7),('2012-08-22 00:00:00',6,0,6,8),('2012-08-22 00:00:00',7,0,7,9),('2012-08-22 00:00:00',8,188,8,10);
/*!40000 ALTER TABLE `cuenta_playa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `denuncia_playa`
--

DROP TABLE IF EXISTS `denuncia_playa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `denuncia_playa` (
  `asunto` text,
  `fechaAlta` datetime NOT NULL,
  `playaID` int(11) NOT NULL,
  `denunciaPlayaID` int(11) NOT NULL AUTO_INCREMENT,
  `clienteID` int(11) NOT NULL,
  `estadoID` int(11) NOT NULL,
  PRIMARY KEY (`denunciaPlayaID`),
  KEY `playaID` (`playaID`),
  KEY `clienteID` (`clienteID`),
  KEY `FK_denuncia_playa_estado` (`estadoID`),
  CONSTRAINT `FK_denuncia_playa_cliente` FOREIGN KEY (`clienteID`) REFERENCES `cliente` (`clienteID`),
  CONSTRAINT `FK_denuncia_playa_playa` FOREIGN KEY (`playaID`) REFERENCES `playa` (`playaID`),
  CONSTRAINT `FK_denuncia_playa_estado` FOREIGN KEY (`estadoID`) REFERENCES `estado_denuncia` (`estadoDenunciaID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `denuncia_playa`
--

LOCK TABLES `denuncia_playa` WRITE;
/*!40000 ALTER TABLE `denuncia_playa` DISABLE KEYS */;
/*!40000 ALTER TABLE `denuncia_playa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `posicion`
--

DROP TABLE IF EXISTS `posicion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `posicion` (
  `tamanioKBMax` int(11) NOT NULL DEFAULT '0',
  `tamanioX` int(11) NOT NULL DEFAULT '0',
  `tamanioY` int(11) NOT NULL DEFAULT '0',
  `ubicacion` text,
  `posicionID` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`posicionID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Clase que contiene los datos sobre las ubicaciones y posiciones de las publicidades. (layout de publicidades)   ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `posicion`
--

LOCK TABLES `posicion` WRITE;
/*!40000 ALTER TABLE `posicion` DISABLE KEYS */;
/*!40000 ALTER TABLE `posicion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comentario`
--

DROP TABLE IF EXISTS `comentario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comentario` (
  `comentario` text NOT NULL,
  `fecha` datetime NOT NULL,
  `habilitado` tinyint(1) DEFAULT NULL,
  `playaID` int(11) NOT NULL,
  `comentarioID` int(11) NOT NULL AUTO_INCREMENT,
  `usuarioID` int(11) NOT NULL,
  PRIMARY KEY (`comentarioID`),
  KEY `playaID` (`playaID`),
  KEY `clienteID` (`usuarioID`),
  CONSTRAINT `FK_comentario_usuario` FOREIGN KEY (`usuarioID`) REFERENCES `usuario` (`usuarioID`),
  CONSTRAINT `FK_comentario_perfil_playa` FOREIGN KEY (`playaID`) REFERENCES `perfil_playa` (`perfilPlayaID`)
) ENGINE=InnoDB AUTO_INCREMENT=1002 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comentario`
--

LOCK TABLES `comentario` WRITE;
/*!40000 ALTER TABLE `comentario` DISABLE KEYS */;
INSERT INTO `comentario` VALUES ('No me gusto, hay que dejar la llave del auto','2012-09-18 09:55:22',1,10,1000,3),('La mejor playa de la zona!!','2013-06-10 01:52:05',1,11,1001,3);
/*!40000 ALTER TABLE `comentario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estado_promocion`
--

DROP TABLE IF EXISTS `estado_promocion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `estado_promocion` (
  `descripcion` text,
  `nombre` varchar(50) NOT NULL,
  `estadoPromocionID` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`estadoPromocionID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='ESTADOS:   *Vigente   *Vencida   *Cancelada   *Pendiente   ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estado_promocion`
--

LOCK TABLES `estado_promocion` WRITE;
/*!40000 ALTER TABLE `estado_promocion` DISABLE KEYS */;
INSERT INTO `estado_promocion` VALUES ('Rechazada o de baja','De baja',1),('Aprobada y publicandose','Vigente',2);
/*!40000 ALTER TABLE `estado_promocion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cargo_empleado`
--

DROP TABLE IF EXISTS `cargo_empleado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cargo_empleado` (
  `descripcion` text,
  `nombre` varchar(50) NOT NULL,
  `cargoEmpleadoID` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`cargoEmpleadoID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cargo_empleado`
--

LOCK TABLES `cargo_empleado` WRITE;
/*!40000 ALTER TABLE `cargo_empleado` DISABLE KEYS */;
INSERT INTO `cargo_empleado` VALUES ('','Gerente General',1),('','Playero',2);
/*!40000 ALTER TABLE `cargo_empleado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `provincia`
--

DROP TABLE IF EXISTS `provincia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `provincia` (
  `nombre` varchar(50) NOT NULL,
  `provinciaID` int(11) NOT NULL AUTO_INCREMENT,
  `paisID` int(11) NOT NULL,
  PRIMARY KEY (`provinciaID`),
  KEY `paisID` (`paisID`),
  CONSTRAINT `FK_provincia_pais` FOREIGN KEY (`paisID`) REFERENCES `pais` (`paisID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `provincia`
--

LOCK TABLES `provincia` WRITE;
/*!40000 ALTER TABLE `provincia` DISABLE KEYS */;
INSERT INTO `provincia` VALUES ('Córdoba',1,1);
/*!40000 ALTER TABLE `provincia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `promocion`
--

DROP TABLE IF EXISTS `promocion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `promocion` (
  `descripcion` text,
  `descuento` float DEFAULT NULL,
  `fechaAlta` datetime NOT NULL,
  `fechaFin` date DEFAULT NULL,
  `fechaInicio` date DEFAULT NULL,
  `horaFin` datetime DEFAULT NULL,
  `horaInicio` datetime DEFAULT NULL,
  `montoFijo` float DEFAULT NULL,
  `nombre` varchar(50) NOT NULL,
  `tarifaID` int(11) NOT NULL,
  `promocionID` int(11) NOT NULL AUTO_INCREMENT,
  `playaID` int(11) NOT NULL,
  `estadoPromocionID` int(11) NOT NULL,
  PRIMARY KEY (`promocionID`),
  KEY `estadoPromocionID` (`estadoPromocionID`),
  KEY `tarifaID` (`tarifaID`),
  KEY `playaID` (`playaID`),
  CONSTRAINT `FK_promocion_playa` FOREIGN KEY (`playaID`) REFERENCES `playa` (`playaID`),
  CONSTRAINT `FK_promocion_estado_promocion` FOREIGN KEY (`estadoPromocionID`) REFERENCES `estado_promocion` (`estadoPromocionID`),
  CONSTRAINT `FK_promocion_tarifa` FOREIGN KEY (`tarifaID`) REFERENCES `tarifa` (`tarifaID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `promocion`
--

LOCK TABLES `promocion` WRITE;
/*!40000 ALTER TABLE `promocion` DISABLE KEYS */;
/*!40000 ALTER TABLE `promocion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `color_vehiculo`
--

DROP TABLE IF EXISTS `color_vehiculo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `color_vehiculo` (
  `nombre` varchar(50) NOT NULL,
  `valorHexadecimal` varchar(6) NOT NULL,
  `colorVehiculoID` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`colorVehiculoID`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `color_vehiculo`
--

LOCK TABLES `color_vehiculo` WRITE;
/*!40000 ALTER TABLE `color_vehiculo` DISABLE KEYS */;
INSERT INTO `color_vehiculo` VALUES ('Blanco','FFFFFF',1),('Negro','000000',2),('Rojo','FF0000',3),('Amarillo','FFFF00',4),('Verde Claro','32CD32',5),('Azul','0000FF',6),('Celeste','1E90FF',7),('Gris Claro','C0C0C0',8),('Gris Oscuro','696969',9),('Naranja','FF4500',10),('Verde Oscuro','006400',11),('Bordó','65212C',12);
/*!40000 ALTER TABLE `color_vehiculo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `historial_de_cambio`
--

DROP TABLE IF EXISTS `historial_de_cambio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `historial_de_cambio` (
  `comentario` text,
  `estadoDenunciaID` int(11) NOT NULL,
  `fecha` datetime NOT NULL,
  `historialDeCambioID` int(11) NOT NULL AUTO_INCREMENT,
  `denunciaPlayaID` int(11) DEFAULT NULL,
  `denunciaVehiculoID` int(11) DEFAULT NULL,
  PRIMARY KEY (`historialDeCambioID`),
  KEY `estadoDenunciaID` (`estadoDenunciaID`),
  KEY `denunciaPlayaID` (`denunciaPlayaID`),
  KEY `denunciaVehiculoID` (`denunciaVehiculoID`),
  CONSTRAINT `FK_historial_de_cambio_denuncia_vehiculo` FOREIGN KEY (`denunciaVehiculoID`) REFERENCES `denuncia_vehiculo` (`denunciaVehiculoID`),
  CONSTRAINT `FK_historial_de_cambio_denuncia_playa` FOREIGN KEY (`denunciaPlayaID`) REFERENCES `denuncia_playa` (`denunciaPlayaID`),
  CONSTRAINT `FK_historial_de_cambio_estado_denuncia` FOREIGN KEY (`estadoDenunciaID`) REFERENCES `estado_denuncia` (`estadoDenunciaID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `historial_de_cambio`
--

LOCK TABLES `historial_de_cambio` WRITE;
/*!40000 ALTER TABLE `historial_de_cambio` DISABLE KEYS */;
/*!40000 ALTER TABLE `historial_de_cambio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `playa`
--

DROP TABLE IF EXISTS `playa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `playa` (
  `barrioID` int(11) DEFAULT NULL,
  `cuit` varchar(50) DEFAULT NULL,
  `disponibilidad` int(11) DEFAULT NULL,
  `domicilio` varchar(50) DEFAULT NULL,
  `telefono` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `url` varchar(50) DEFAULT NULL,
  `estadoPlayaID` int(11) NOT NULL,
  `nombreComercial` varchar(100) NOT NULL,
  `latitud` double DEFAULT NULL,
  `longitud` double DEFAULT NULL,
  `razonSocial` varchar(50) DEFAULT NULL,
  `playaID` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`playaID`),
  KEY `barrioID` (`barrioID`),
  KEY `estadoPlayaID` (`estadoPlayaID`),
  CONSTRAINT `FK_playa_barrio` FOREIGN KEY (`barrioID`) REFERENCES `barrio` (`barrioID`),
  CONSTRAINT `FK_playa_estado_playa` FOREIGN KEY (`estadoPlayaID`) REFERENCES `estado_playa` (`estadoPlayaID`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='Cada instancia representa los datos administrativos de una playa.   ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `playa`
--

LOCK TABLES `playa` WRITE;
/*!40000 ALTER TABLE `playa` DISABLE KEYS */;
INSERT INTO `playa` VALUES (2,'30-11111111-1',55,'Buenos Aires 548','4425852','playa@europa.com','http://www.cualquiera.com',2,'Estacionamiento Europa',-31.4229765,-64.18568859999999,'Estacionamiento Europa',3),(2,'30-11111111-1',100,'Independencia 770','4411888','playa@independencia.com','http://www.independencia.com',2,'Estacionamiento Independencia',-31.4251654,-64.1881208,'Estacionamiento Independencia',4),(2,'30-11111111-1',100,'Obispo Trejo 758','4442525','playa@parkingciti.com','http://www.city.com',2,'Parkingcity',-31.4246207,-64.1893771,'Parkingcity',5),(1,'30-11111111-1',100,'Bv. Ilia 156','44119789','playa@msur.com.ar','http://www.mercado.com',2,'Estacionamiento Mercado Sur',-31.421262,-64.18423299999999,'Estacionamiento Mercado Sur',6),(1,'30-11111111-1',100,'Santa Rosa 631','44112333','playa@poine.com','http://www.parking.com',1,'Parking One',-31.4099508,-64.19153299999999,'Parking One',7),(1,'30-11111111-1',100,'Bv. Chacabuco 728',NULL,NULL,NULL,2,'Playa Sur',-31.425904,-64.183791,'Playa Sur',8),(1,'30-11111111-1',100,'Bv. Illia 70',NULL,NULL,NULL,2,'Parquin Verde',-31.420956,-64.18535,'Parquin Verde',9),(1,'30-11111111-1',100,'Colon 756','4411214','','http://www.control.com',2,'Estacionamiento Colón',-31.4105014,-64.1936027,'Estacionamiento Colón',10),(3,'20-32855878-3',100,'velez sarsfield 2600','03514339851','gonzaloarribere@gmail.com','http://www.cualquiera.com',2,'Estacionamiento Jardin',-31.4451624,-64.1983095,'Playa jardin',11);
/*!40000 ALTER TABLE `playa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `liquidacion`
--

DROP TABLE IF EXISTS `liquidacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `liquidacion` (
  `fecha` datetime NOT NULL,
  `fechaHasta` datetime NOT NULL,
  `fechaDesde` datetime NOT NULL,
  `importeTotal` float NOT NULL DEFAULT '0',
  `liquidacionID` int(11) NOT NULL AUTO_INCREMENT,
  `estadiaID` int(11) NOT NULL,
  PRIMARY KEY (`liquidacionID`),
  KEY `estadiaID` (`estadiaID`),
  CONSTRAINT `FK_liquidacion_estadia` FOREIGN KEY (`estadiaID`) REFERENCES `estadia` (`estadiaID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `liquidacion`
--

LOCK TABLES `liquidacion` WRITE;
/*!40000 ALTER TABLE `liquidacion` DISABLE KEYS */;
/*!40000 ALTER TABLE `liquidacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `foto_usuario`
--

DROP TABLE IF EXISTS `foto_usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `foto_usuario` (
  `fotoUsuarioID` int(11) NOT NULL AUTO_INCREMENT,
  `fotoUsuario` mediumblob,
  PRIMARY KEY (`fotoUsuarioID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `foto_usuario`
--

LOCK TABLES `foto_usuario` WRITE;
/*!40000 ALTER TABLE `foto_usuario` DISABLE KEYS */;
/*!40000 ALTER TABLE `foto_usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `foto`
--

DROP TABLE IF EXISTS `foto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `foto` (
  `descripcion` text,
  `nombre` varchar(50) NOT NULL,
  `title` varchar(50) DEFAULT NULL,
  `image` mediumblob,
  `fotoID` int(11) NOT NULL AUTO_INCREMENT,
  `perfilPlayaID` int(11) NOT NULL,
  PRIMARY KEY (`fotoID`),
  KEY `perfilPlayaID` (`perfilPlayaID`),
  CONSTRAINT `FK_foto_perfil_playa` FOREIGN KEY (`perfilPlayaID`) REFERENCES `perfil_playa` (`perfilPlayaID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;



--
-- Table structure for table `vehiculo`
--

DROP TABLE IF EXISTS `vehiculo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vehiculo` (
  `anio` int(11) DEFAULT NULL,
  `codigoBarra` varchar(50) DEFAULT NULL,
  `colorVehiculoID` int(11) DEFAULT NULL,
  `habilitado` tinyint(1) DEFAULT '1',
  `modeloVehiculoID` int(11) NOT NULL,
  `patente` varchar(50) NOT NULL,
  `vehiculoID` int(11) NOT NULL AUTO_INCREMENT,
  `clienteID` int(11) NOT NULL,
  PRIMARY KEY (`vehiculoID`),
  KEY `modeloVehiculoID` (`modeloVehiculoID`),
  KEY `colorVehiculoID` (`colorVehiculoID`),
  KEY `clienteID` (`clienteID`),
  CONSTRAINT `FK_vehiculo_cliente` FOREIGN KEY (`clienteID`) REFERENCES `cliente` (`clienteID`),
  CONSTRAINT `FK_vehiculo_color_vehiculo` FOREIGN KEY (`colorVehiculoID`) REFERENCES `color_vehiculo` (`colorVehiculoID`),
  CONSTRAINT `FK_vehiculo_modelo_vehiculo` FOREIGN KEY (`modeloVehiculoID`) REFERENCES `modelo_vehiculo` (`modeloVehiculoID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vehiculo`
--

LOCK TABLES `vehiculo` WRITE;
/*!40000 ALTER TABLE `vehiculo` DISABLE KEYS */;
INSERT INTO `vehiculo` VALUES (2010,NULL,2,1,1,'ABC123',1,1),(2011,NULL,9,1,6,'DEF456',2,2),(2009,NULL,12,1,15,'GHI789',3,3),(2009,NULL,3,1,10,'JKL159',4,4);
/*!40000 ALTER TABLE `vehiculo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_estadia`
--

DROP TABLE IF EXISTS `tipo_estadia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipo_estadia` (
  `descripcion` text,
  `nombre` varchar(50) NOT NULL,
  `tipoEstadiaID` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`tipoEstadiaID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='TIPOS DE ESTADIA:   * Por hora   * Por día   * Por noche   * Por mes   * Por semana   ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_estadia`
--

LOCK TABLES `tipo_estadia` WRITE;
/*!40000 ALTER TABLE `tipo_estadia` DISABLE KEYS */;
INSERT INTO `tipo_estadia` VALUES ('','Por Hora',1),('','Por Mes',2),('','Por Noche',3),('','Por Día',4),('','Por Semana',5);
/*!40000 ALTER TABLE `tipo_estadia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `foto_publicidad`
--

DROP TABLE IF EXISTS `foto_publicidad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `foto_publicidad` (
  `fotoPublicidadID` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(75) DEFAULT NULL,
  `image` mediumblob,
  `url` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`fotoPublicidadID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `foto_publicidad`
--

LOCK TABLES `foto_publicidad` WRITE;
/*!40000 ALTER TABLE `foto_publicidad` DISABLE KEYS */;
/*!40000 ALTER TABLE `foto_publicidad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categoria_vehiculo`
--

DROP TABLE IF EXISTS `categoria_vehiculo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `categoria_vehiculo` (
  `descripcion` text,
  `nombre` varchar(50) NOT NULL,
  `categoriaVehiculoID` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`categoriaVehiculoID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='Son los tí­pos de vehí­culos. Moto, Auto, Utilitario, PickUp, etc.   ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoria_vehiculo`
--

LOCK TABLES `categoria_vehiculo` WRITE;
/*!40000 ALTER TABLE `categoria_vehiculo` DISABLE KEYS */;
INSERT INTO `categoria_vehiculo` VALUES ('','Auto',1),('','Moto',2),('','Utilitario',3),('','PickUp / 4X4',4);
/*!40000 ALTER TABLE `categoria_vehiculo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `perfil_playa`
--

DROP TABLE IF EXISTS `perfil_playa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `perfil_playa` (
  `descripcion` text,
  `perfilPlayaID` int(11) NOT NULL AUTO_INCREMENT,
  `playaID` int(11) NOT NULL,
  `fotoPerfil` mediumblob,
  `nombreFoto` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`perfilPlayaID`),
  KEY `playaID` (`playaID`),
  CONSTRAINT `FK_perfil_playa_playa` FOREIGN KEY (`playaID`) REFERENCES `playa` (`playaID`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='Esta clase va a contener todos los datos del perfil de la playa que se muestra en el sitio: fotos, nombre para mostrar, descripción, etc.    ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `perfil_playa`
--

LOCK TABLES `perfil_playa` WRITE;
/*!40000 ALTER TABLE `perfil_playa` DISABLE KEYS */;
INSERT INTO `perfil_playa` VALUES ('¡Los esperamos en Estacionamiento Europa, les ofrecemos una excelente atención con muy buenos precios!',3,3,NULL,NULL),('Playa \"Estacionamiento Independencia\"',4,4,NULL,NULL),('Playa \"Parkingcity',5,5,NULL,NULL),('Playa \"Estacionamiento Mercado Sur\"',6,6,NULL,NULL),('Playa \"Parking One\"',7,7,NULL,NULL),('Playa \"Playa Sur\"',8,8,NULL,NULL),('Playa \"Parquin Verde\"',9,9,NULL,NULL),('Playa \"Estacionamiento Colón\"',10,10,NULL,NULL),(NULL,11,11,NULL,NULL);
/*!40000 ALTER TABLE `perfil_playa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estado_publicidad`
--

DROP TABLE IF EXISTS `estado_publicidad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `estado_publicidad` (
  `descripcion` text,
  `nombre` varchar(50) NOT NULL,
  `estadoPublicidadID` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`estadoPublicidadID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='ESTADOS:   *Vigente   *Vencida   *Cancelada   *Pendiente   ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estado_publicidad`
--

LOCK TABLES `estado_publicidad` WRITE;
/*!40000 ALTER TABLE `estado_publicidad` DISABLE KEYS */;
INSERT INTO `estado_publicidad` VALUES ('Pendiente de Aprobación','Pendiente',1),('Aprobada y pendiente de publicación','Aprobada',2),('No Aprobada','Rechazada',3);
/*!40000 ALTER TABLE `estado_publicidad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `abono`
--

DROP TABLE IF EXISTS `abono`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `abono` (
  `fechaVigenciaDesde` datetime DEFAULT NULL,
  `fechaVigenciaHasta` datetime DEFAULT NULL,
  `tarifaID` int(11) NOT NULL,
  `abonoID` int(11) NOT NULL AUTO_INCREMENT,
  `vehiculoID` int(11) NOT NULL,
  `playaID` int(11) NOT NULL,
  `promocionID` int(11) DEFAULT NULL,
  PRIMARY KEY (`abonoID`),
  KEY `tarifaID` (`tarifaID`),
  KEY `vehiculoID` (`vehiculoID`),
  KEY `playaID` (`playaID`),
  KEY `promocionID` (`promocionID`),
  CONSTRAINT `FK_abono_playa` FOREIGN KEY (`playaID`) REFERENCES `playa` (`playaID`),
  CONSTRAINT `FK_abono_vehiculo` FOREIGN KEY (`vehiculoID`) REFERENCES `vehiculo` (`vehiculoID`),
  CONSTRAINT `FK_abono_tarifa` FOREIGN KEY (`tarifaID`) REFERENCES `tarifa` (`tarifaID`),
  CONSTRAINT `FK_abono_promocion` FOREIGN KEY (`promocionID`) REFERENCES `promocion` (`promocionID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `abono`
--

LOCK TABLES `abono` WRITE;
/*!40000 ALTER TABLE `abono` DISABLE KEYS */;
INSERT INTO `abono` VALUES ('2013-05-05 00:00:00','2012-11-05 00:00:00',3,1,1,3,NULL),('2013-05-04 00:00:00','2012-11-04 00:00:00',3,2,2,3,NULL),('2013-05-02 00:00:00','2012-11-02 00:00:00',3,3,3,3,NULL),('2013-05-03 00:00:00','2012-11-03 00:00:00',3,4,1,3,NULL);
/*!40000 ALTER TABLE `abono` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cuenta_cliente`
--

DROP TABLE IF EXISTS `cuenta_cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cuenta_cliente` (
  `fechaCreacion` datetime NOT NULL,
  `nroCuenta` int(11) DEFAULT NULL,
  `saldo` float NOT NULL DEFAULT '0',
  `cuentaClienteID` int(11) NOT NULL AUTO_INCREMENT,
  `clienteID` int(11) DEFAULT NULL,
  PRIMARY KEY (`cuentaClienteID`),
  UNIQUE KEY `nroCuenta` (`nroCuenta`),
  KEY `clienteID` (`clienteID`),
  CONSTRAINT `FK_cuenta_cliente_cliente` FOREIGN KEY (`clienteID`) REFERENCES `cliente` (`clienteID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cuenta_cliente`
--

LOCK TABLES `cuenta_cliente` WRITE;
/*!40000 ALTER TABLE `cuenta_cliente` DISABLE KEYS */;
INSERT INTO `cuenta_cliente` VALUES ('2012-08-21 20:29:37',1001,150,1,2),('2012-08-21 20:29:37',1002,50,2,3),('2012-08-21 20:29:37',1003,148,3,4),('2012-08-21 20:29:37',1004,144,4,1);
/*!40000 ALTER TABLE `cuenta_cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sesion`
--

DROP TABLE IF EXISTS `sesion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sesion` (
  `fechaFin` datetime DEFAULT NULL,
  `fechaInicio` datetime DEFAULT NULL,
  `idSesion` varchar(50) NOT NULL,
  `sesionID` int(11) NOT NULL AUTO_INCREMENT,
  `usuarioID` int(11) DEFAULT NULL,
  PRIMARY KEY (`sesionID`),
  KEY `usuarioID` (`usuarioID`),
  CONSTRAINT `FK_sesion_usuario` FOREIGN KEY (`usuarioID`) REFERENCES `usuario` (`usuarioID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sesion`
--

LOCK TABLES `sesion` WRITE;
/*!40000 ALTER TABLE `sesion` DISABLE KEYS */;
/*!40000 ALTER TABLE `sesion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pais`
--

DROP TABLE IF EXISTS `pais`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pais` (
  `nombre` varchar(50) NOT NULL,
  `paisID` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`paisID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pais`
--

LOCK TABLES `pais` WRITE;
/*!40000 ALTER TABLE `pais` DISABLE KEYS */;
INSERT INTO `pais` VALUES ('Argentina',1);
/*!40000 ALTER TABLE `pais` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tarifa`
--

DROP TABLE IF EXISTS `tarifa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tarifa` (
  `fechaAlta` datetime DEFAULT NULL,
  `fechaBaja` datetime DEFAULT NULL,
  `importe` float NOT NULL,
  `vigente` tinyint(1) DEFAULT NULL,
  `tarifaID` int(11) NOT NULL AUTO_INCREMENT,
  `playaID` int(11) NOT NULL,
  `tipoEstadiaID` int(11) NOT NULL,
  `categoriaVehiculoID` int(11) NOT NULL,
  PRIMARY KEY (`tarifaID`),
  KEY `categoriaVehiculoID` (`categoriaVehiculoID`),
  KEY `tipoEstadiaID` (`tipoEstadiaID`),
  KEY `FK_tarifa_playa` (`playaID`),
  CONSTRAINT `FK_tarifa_playa` FOREIGN KEY (`playaID`) REFERENCES `playa` (`playaID`),
  CONSTRAINT `FK_tarifa_tipo_estadia` FOREIGN KEY (`tipoEstadiaID`) REFERENCES `tipo_estadia` (`tipoEstadiaID`),
  CONSTRAINT `FK_tarifa_categoria_vehiculo` FOREIGN KEY (`categoriaVehiculoID`) REFERENCES `categoria_vehiculo` (`categoriaVehiculoID`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8 COMMENT='Cada instancia contiene un precio de la tarifa que depende del Categorí­a de vehí­culo, tipo de estadí­a (mensual, por hora, etc).      Categorí­aVehiculo: utilitario   TipoEstadí­a: Mensual   Precio/tarifa: $720      Categorí­aVehiculo: utilitario   TipoEstadí­a: Por hora   Precio/tarifa: $14      Categorí­aVehiculo: auto   TipoEstadí­a: Por hora   Precio/tarifa: $12   ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tarifa`
--

LOCK TABLES `tarifa` WRITE;
/*!40000 ALTER TABLE `tarifa` DISABLE KEYS */;
INSERT INTO `tarifa` VALUES ('2012-07-04 19:25:23',NULL,10,1,1,3,1,1),('2012-07-04 19:25:23',NULL,12,1,2,3,1,2),('2012-07-04 19:25:23',NULL,12,1,3,3,1,3),('2012-07-04 19:25:23',NULL,500,1,4,3,2,1),('2012-07-04 19:25:23',NULL,550,1,5,3,2,2),('2012-07-04 19:25:23',NULL,550,1,6,3,2,3),('2012-07-04 19:25:23',NULL,11,1,7,10,1,1),('2012-07-04 19:25:23',NULL,13,1,8,10,1,2),('2012-07-04 19:25:23',NULL,14,1,9,10,1,3),('2012-07-04 19:25:23',NULL,650,1,10,10,2,1),('2012-07-04 19:25:23',NULL,690,1,11,10,2,2),('2012-07-04 19:25:23',NULL,700,1,12,10,2,3),('2013-05-20 19:38:19',NULL,10,1,13,4,1,1),('2013-05-20 19:38:19',NULL,12,1,14,4,1,3),('2013-05-20 19:38:19',NULL,12,1,15,4,1,4),('2013-05-20 19:38:19',NULL,50,1,16,4,3,1),('2013-05-20 19:38:19',NULL,70,1,17,4,3,3),('2013-05-20 19:38:19',NULL,70,1,18,4,3,4),('2013-05-20 19:38:19',NULL,500,1,19,4,2,1),('2013-05-20 19:38:19',NULL,700,1,20,4,2,3),('2013-05-20 19:38:19',NULL,700,1,21,4,2,4),('2013-05-20 19:45:07',NULL,12,1,22,10,1,1),('2013-05-20 19:45:07',NULL,14,1,23,10,1,3),('2013-05-20 19:45:07',NULL,14,1,24,10,1,4),('2013-05-20 19:45:07',NULL,500,1,25,10,2,1),('2013-05-20 19:45:07',NULL,800,1,26,10,2,3),('2013-05-20 19:45:07',NULL,800,1,27,10,2,4),('2013-06-10 01:51:30',NULL,13,1,28,11,1,1),('2013-06-10 01:51:30',NULL,15,1,29,11,1,3),('2013-06-10 01:51:30',NULL,15,1,30,11,1,4),('2013-06-10 01:51:30',NULL,8,1,31,11,1,2),('2013-06-10 01:51:30',NULL,60,1,32,11,3,1),('2013-06-10 01:51:30',NULL,80,1,33,11,3,3),('2013-06-10 01:51:30',NULL,90,1,34,11,3,4),('2013-06-10 01:51:30',NULL,500,1,35,11,2,1),('2013-06-10 01:51:30',NULL,700,1,36,11,2,3);
/*!40000 ALTER TABLE `tarifa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `barrio`
--

DROP TABLE IF EXISTS `barrio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `barrio` (
  `nombre` varchar(50) NOT NULL,
  `barrioID` int(11) NOT NULL AUTO_INCREMENT,
  `localidadID` int(11) NOT NULL,
  PRIMARY KEY (`barrioID`),
  KEY `localidadID` (`localidadID`),
  CONSTRAINT `FK_barrio_localidad` FOREIGN KEY (`localidadID`) REFERENCES `localidad` (`localidadID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `barrio`
--

LOCK TABLES `barrio` WRITE;
/*!40000 ALTER TABLE `barrio` DISABLE KEYS */;
INSERT INTO `barrio` VALUES ('Centro',1,1),('Nueva Córdoba',2,1),('Jardín',3,1),('Alta Córdoba',4,1),('Alberdi',5,1);
/*!40000 ALTER TABLE `barrio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rol_usuario`
--

DROP TABLE IF EXISTS `rol_usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rol_usuario` (
  `descripcion` text,
  `nombre` varchar(50) NOT NULL,
  `rolUsuarioID` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`rolUsuarioID`),
  UNIQUE KEY `nombre` (`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rol_usuario`
--

LOCK TABLES `rol_usuario` WRITE;
/*!40000 ALTER TABLE `rol_usuario` DISABLE KEYS */;
INSERT INTO `rol_usuario` VALUES ('Administrador del sistema','ROLE_ADMIN',1),('Auditor del sistema','ROLE_AUDITOR',2),('Usuario del Area administrativa/contable','ROLE_CONTADOR',3),('Usuario sin permisos especiales','ROLE_USER',4),('Cliente de las playas de estacionamiento','ROLE_CLIENT',5),('Empleado de la playa de estacionamiento','ROLE_PLAYA_EMPLEADO',6),('Gerente de la playa de estacionamiento','ROLE_PLAYA_GERENTE',7);
/*!40000 ALTER TABLE `rol_usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `favorito`
--

DROP TABLE IF EXISTS `favorito`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `favorito` (
  `favoritoID` int(11) NOT NULL AUTO_INCREMENT,
  `clienteID` int(11) NOT NULL,
  `playaID` int(11) NOT NULL,
  PRIMARY KEY (`favoritoID`),
  KEY `clienteID` (`clienteID`),
  KEY `playaID` (`playaID`),
  CONSTRAINT `FK_favorito_playa` FOREIGN KEY (`playaID`) REFERENCES `playa` (`playaID`),
  CONSTRAINT `FK_favorito_cliente` FOREIGN KEY (`clienteID`) REFERENCES `cliente` (`clienteID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `favorito`
--

LOCK TABLES `favorito` WRITE;
/*!40000 ALTER TABLE `favorito` DISABLE KEYS */;
/*!40000 ALTER TABLE `favorito` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detalle_estadia`
--

DROP TABLE IF EXISTS `detalle_estadia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `detalle_estadia` (
  `transaccionClienteID` int(11) DEFAULT NULL,
  `empleadoID` int(11) NOT NULL,
  `fechaHoraEgreso` datetime DEFAULT NULL,
  `fechaHoraIngreso` datetime DEFAULT NULL,
  `importeTotal` float DEFAULT NULL,
  `cobrado` tinyint(1) NOT NULL,
  `promocionID` int(11) DEFAULT NULL,
  `tarifaID` int(11) DEFAULT NULL,
  `vehiculoID` int(11) NOT NULL,
  `detalleEstadiaID` int(11) NOT NULL AUTO_INCREMENT,
  `estadiaID` int(11) NOT NULL,
  PRIMARY KEY (`detalleEstadiaID`),
  KEY `empleadoID` (`empleadoID`),
  KEY `estadiaID` (`estadiaID`),
  KEY `promocionID` (`promocionID`),
  KEY `tarifaID` (`tarifaID`),
  KEY `transaccionClienteID` (`transaccionClienteID`),
  KEY `vehiculoID` (`vehiculoID`),
  CONSTRAINT `FK_detalle_estadia_vehiculo` FOREIGN KEY (`vehiculoID`) REFERENCES `vehiculo` (`vehiculoID`),
  CONSTRAINT `FK_detalle_estadia_empleado` FOREIGN KEY (`empleadoID`) REFERENCES `empleado` (`empleadoID`),
  CONSTRAINT `FK_detalle_estadia_estadia` FOREIGN KEY (`estadiaID`) REFERENCES `estadia` (`estadiaID`),
  CONSTRAINT `FK_detalle_estadia_promocion` FOREIGN KEY (`promocionID`) REFERENCES `promocion` (`promocionID`),
  CONSTRAINT `FK_detalle_estadia_tarifa` FOREIGN KEY (`tarifaID`) REFERENCES `tarifa` (`tarifaID`),
  CONSTRAINT `FK_detalle_estadia_transaccion_cliente` FOREIGN KEY (`transaccionClienteID`) REFERENCES `transaccion_cliente` (`transaccionClienteID`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detalle_estadia`
--

LOCK TABLES `detalle_estadia` WRITE;
/*!40000 ALTER TABLE `detalle_estadia` DISABLE KEYS */;
INSERT INTO `detalle_estadia` VALUES (2,1002,'2012-08-04 17:48:45','2012-08-04 19:25:23',20,1,NULL,1,1,1,3),(2,1002,'2012-08-11 17:50:35','2012-08-11 18:45:21',10,1,NULL,1,1,2,3),(2,1002,'2012-08-18 17:51:18','2012-08-18 18:32:49',10,1,NULL,1,1,3,3),(5,1001,'2013-06-15 18:00:00','2013-06-15 18:39:42',10,1,NULL,1,1,5,3),(6,1001,'2013-06-16 18:00:00','2013-06-16 18:40:01',10,1,NULL,1,1,6,3),(7,1001,'2013-06-20 19:00:00','2013-06-20 19:45:52',12,1,NULL,3,4,7,3),(8,1001,'2013-04-05 20:00:00','2013-04-05 20:08:58',12,1,NULL,3,4,8,3),(9,1001,'2013-04-10 20:00:00','2013-04-10 20:09:08',12,1,NULL,3,4,9,3),(10,1001,'2013-04-12 20:00:00','2013-04-12 20:09:16',12,1,NULL,3,4,10,3),(11,1001,'2013-04-15 20:00:00','2013-04-15 20:09:23',12,1,NULL,3,4,11,3),(12,1001,'2013-04-22 20:00:00','2013-04-22 20:09:30',10,1,NULL,1,1,12,3),(13,1001,'2013-05-02 20:00:00','2013-05-02 20:09:46',10,1,NULL,1,1,13,3),(14,1001,'2013-05-06 20:00:00','2013-05-06 20:10:02',12,1,NULL,3,4,14,3),(15,1001,'2013-05-10 20:00:00','2013-05-10 20:10:10',12,1,NULL,3,4,15,3),(16,1001,'2013-05-11 20:00:00','2013-05-11 20:10:17',12,1,NULL,3,4,16,3),(17,1001,'2013-05-17 20:00:00','2013-05-17 20:10:24',12,1,NULL,3,4,17,3),(18,1001,'2013-05-26 20:00:00','2013-05-26 20:10:31',12,1,NULL,3,4,18,3),(19,1007,'2013-06-17 18:00:00','2013-06-17 18:39:42',10,1,NULL,13,1,19,4),(20,1007,'2013-06-18 18:00:00','2013-06-18 18:40:01',10,1,NULL,13,1,20,4),(21,1007,'2013-06-21 19:00:00','2013-06-21 19:45:52',12,1,NULL,14,4,21,4),(22,1007,'2013-04-06 20:00:00','2013-04-06 20:08:58',12,1,NULL,14,4,22,4),(23,1007,'2013-04-11 20:00:00','2013-04-11 20:09:08',12,1,NULL,14,4,23,4),(24,1007,'2013-04-13 20:00:00','2013-04-13 20:09:16',12,1,NULL,14,4,24,4),(25,1007,'2013-04-16 20:00:00','2013-04-16 20:09:23',12,1,NULL,14,4,25,4),(26,1007,'2013-04-23 20:00:00','2013-04-23 20:09:30',10,1,NULL,13,1,26,4),(27,1007,'2013-05-03 20:00:00','2013-05-03 20:09:46',10,1,NULL,13,1,27,4),(28,1007,'2013-05-07 20:00:00','2013-05-07 20:10:02',12,1,NULL,14,4,28,4),(29,1007,'2013-05-12 20:00:00','2013-05-12 20:10:10',12,1,NULL,14,4,29,4),(30,1007,'2013-05-13 20:00:00','2013-05-13 20:10:17',12,1,NULL,14,4,30,4),(31,1007,'2013-05-18 20:00:00','2013-05-18 20:10:24',12,1,NULL,14,4,31,4),(32,1007,'2013-05-27 20:00:00','2013-05-27 20:10:31',12,1,NULL,14,4,32,4),(33,1008,'2013-06-19 18:00:00','2013-06-19 18:39:42',12,1,NULL,22,1,33,10),(34,1008,'2013-06-20 18:00:00','2013-06-20 18:40:01',12,1,NULL,22,1,34,10),(35,1008,'2013-06-22 19:00:00','2013-06-22 19:45:52',14,1,NULL,23,4,35,10),(36,1008,'2013-04-07 20:00:00','2013-04-07 20:08:58',14,1,NULL,23,4,36,10),(37,1008,'2013-04-12 20:00:00','2013-04-12 20:09:08',14,1,NULL,23,4,37,10),(38,1008,'2013-04-15 20:00:00','2013-04-15 20:09:16',14,1,NULL,23,4,38,10),(39,1008,'2013-04-17 20:00:00','2013-04-17 20:09:23',14,1,NULL,23,4,39,10),(40,1008,'2013-04-24 20:00:00','2013-04-24 20:09:30',12,1,NULL,22,1,40,10),(41,1008,'2013-05-04 20:00:00','2013-05-04 20:09:46',12,1,NULL,22,1,41,10),(42,1008,'2013-05-08 20:00:00','2013-05-07 20:10:02',14,1,NULL,23,4,42,10),(43,1008,'2013-05-14 20:00:00','2013-05-14 20:10:10',14,1,NULL,23,4,43,10),(44,1008,'2013-05-15 20:00:00','2013-05-15 20:10:17',14,1,NULL,23,4,44,10),(45,1008,'2013-05-19 20:00:00','2013-05-19 20:10:24',14,1,NULL,23,4,45,10),(46,1008,'2013-05-28 20:00:00','2013-05-28 20:10:31',14,1,NULL,23,4,46,10);
/*!40000 ALTER TABLE `detalle_estadia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `modelo_vehiculo`
--

DROP TABLE IF EXISTS `modelo_vehiculo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `modelo_vehiculo` (
  `descripcion` text,
  `nombre` varchar(50) NOT NULL,
  `modeloVehiculoID` int(11) NOT NULL AUTO_INCREMENT,
  `marcaVehiculoID` int(11) NOT NULL,
  `categoriaID` int(11) NOT NULL,
  PRIMARY KEY (`modeloVehiculoID`),
  KEY `categoriaID` (`categoriaID`),
  KEY `marcaVehiculoID` (`marcaVehiculoID`),
  CONSTRAINT `FK_modelo_vehiculo_marca_vehiculo` FOREIGN KEY (`marcaVehiculoID`) REFERENCES `marca_vehiculo` (`marcaVehiculoID`),
  CONSTRAINT `FK_modelo_vehiculo_categoria_vehiculo` FOREIGN KEY (`categoriaID`) REFERENCES `categoria_vehiculo` (`categoriaVehiculoID`)
) ENGINE=InnoDB AUTO_INCREMENT=215 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `modelo_vehiculo`
--

LOCK TABLES `modelo_vehiculo` WRITE;
/*!40000 ALTER TABLE `modelo_vehiculo` DISABLE KEYS */;
INSERT INTO `modelo_vehiculo` VALUES (NULL,'Clio',1,1,1),(NULL,'Sandero',2,1,1),(NULL,'Logan',3,1,1),(NULL,'Symbol',4,1,1),(NULL,'Fluence',5,1,1),(NULL,'Mégane',6,1,1),(NULL,'Latitude',7,1,1),(NULL,'Kangoo',8,1,3),(NULL,'Master Furgón',9,1,3),(NULL,'Master Minibus',10,1,3),(NULL,'Duster',11,1,4),(NULL,'Koleos',12,1,4),(NULL,'Scénic',13,1,1),(NULL,'Laguna',14,1,1),(NULL,'Twingo',15,1,1),(NULL,'Ka',16,2,1),(NULL,'Fiesta',17,2,1),(NULL,'Focus',18,2,1),(NULL,'Mondeo',19,2,1),(NULL,'S-Max',20,2,1),(NULL,'Falcon',21,2,1),(NULL,'Fairlane',22,2,1),(NULL,'Taunus',23,2,1),(NULL,'Taurus',24,2,1),(NULL,'Torino',25,2,1),(NULL,'Escort',26,2,1),(NULL,'C-Max',27,2,1),(NULL,'Galaxi',28,2,1),(NULL,'Mustang',29,2,1),(NULL,'Escape',30,2,1),(NULL,'Kuga',31,2,4),(NULL,'EcoSport',32,2,4),(NULL,'Ranger',33,2,4),(NULL,'Transit Furgon',34,2,3),(NULL,'Transit Minibus',35,2,3),(NULL,'F100',36,2,4),(NULL,'F150',37,2,4),(NULL,'F250',38,2,4),(NULL,'F350',39,2,4),(NULL,'F450',40,2,4),(NULL,'F650',41,2,4),(NULL,'Gol',42,3,1),(NULL,'Fox',43,3,1),(NULL,'Crossfox',44,3,1),(NULL,'Golf GTI',45,3,1),(NULL,'Voyage',46,3,1),(NULL,'Bora',47,3,1),(NULL,'Vento',48,3,1),(NULL,'Passat',49,3,1),(NULL,'Scirocco',50,3,1),(NULL,'CC',51,3,1),(NULL,'Tiguan',52,3,4),(NULL,'Touareg',53,3,4),(NULL,'Gol Country',54,3,1),(NULL,'Suran',55,3,1),(NULL,'Vento Variant',56,3,1),(NULL,'Sharan',57,3,1),(NULL,'Passat Variant',58,3,1),(NULL,'Saveiro',59,3,4),(NULL,'Amarok',60,3,4),(NULL,'Clase C Sedán',61,4,1),(NULL,'Clase E Sedán',62,4,1),(NULL,'Clase S Sedán',63,4,1),(NULL,'Clase C Coupé',64,4,1),(NULL,'Clase E Coupé',65,4,1),(NULL,'SLS AMG',66,4,1),(NULL,'Clase GLK',67,4,4),(NULL,'Clase SLK',68,4,1),(NULL,'Clase M',69,4,4),(NULL,'207',70,5,1),(NULL,'308',71,5,1),(NULL,'3008',72,5,1),(NULL,'5008',73,5,1),(NULL,'RCZ',74,5,1),(NULL,'408',75,5,1),(NULL,'Partner Furgón',76,5,3),(NULL,'Partner Patagónica',77,5,3),(NULL,'Hoggar',78,5,4),(NULL,'C3',79,6,1),(NULL,'C2',80,6,1),(NULL,'C1',81,6,1),(NULL,'Xsara',82,6,1),(NULL,'C4',83,6,1),(NULL,'C6',84,6,1),(NULL,'DS3',85,6,1),(NULL,'DS4',86,6,1),(NULL,'DS9',87,6,1),(NULL,'Berlingo',88,6,3),(NULL,'C5',89,6,1),(NULL,'C1',90,6,1),(NULL,'Serie 1',91,7,1),(NULL,'Serie 3',92,7,1),(NULL,'Serie 5',93,7,1),(NULL,'Serie 6',94,7,1),(NULL,'Serie 7',95,7,1),(NULL,'X1',96,7,4),(NULL,'X3',97,7,4),(NULL,'X5',98,7,4),(NULL,'X6',99,7,4),(NULL,'Z4',100,7,1),(NULL,'M3',101,7,1),(NULL,'A1',102,8,1),(NULL,'A3',103,8,1),(NULL,'A4',104,8,1),(NULL,'A5',105,8,1),(NULL,'A6',106,8,1),(NULL,'A7',107,8,1),(NULL,'A8',108,8,1),(NULL,'Q5',109,8,4),(NULL,'Q7',110,8,4),(NULL,'TT',111,8,1),(NULL,'R8',112,8,1),(NULL,'Marbella',113,9,1),(NULL,'Ibiza',114,9,1),(NULL,'Córdoba',115,9,1),(NULL,'León',116,9,1),(NULL,'Alhambra',117,9,4),(NULL,'K-180',118,10,1),(NULL,'Linea',119,11,1),(NULL,'Palio',120,11,1),(NULL,'Uno',121,11,1),(NULL,'Punto',122,11,1),(NULL,'Siena',123,11,1),(NULL,'500',124,11,1),(NULL,'Gama Strada',125,11,4),(NULL,'Idea',126,11,1),(NULL,'Palio Weekend',127,11,1),(NULL,'Qubo',128,11,3),(NULL,'Fiorino',129,11,3),(NULL,'Ducato',130,11,3),(NULL,'March',131,12,1),(NULL,'Tiida',132,12,1),(NULL,'Sentra',133,12,1),(NULL,'370Z',134,12,1),(NULL,'NP300',135,12,4),(NULL,'Frontier',136,12,4),(NULL,'X-Trail',137,12,4),(NULL,'Murano',138,12,4),(NULL,'Corolla',139,13,1),(NULL,'Camry',140,13,1),(NULL,'Prius',141,13,1),(NULL,'Rav4',142,13,4),(NULL,'SW4',143,13,4),(NULL,'Land Cruiser Prado',144,13,4),(NULL,'Land Cruise 200',145,13,4),(NULL,'Hilux',146,13,4),(NULL,'i10',147,14,1),(NULL,'i30',148,14,1),(NULL,'Genesis',149,14,1),(NULL,'Veloster',150,14,1),(NULL,'Tucson',151,14,4),(NULL,'Santa Fe',152,14,4),(NULL,'H1 Minibus',153,14,3),(NULL,'C30',154,15,1),(NULL,'S40',155,15,1),(NULL,'S80',156,15,1),(NULL,'V50',157,15,1),(NULL,'XC60',158,15,4),(NULL,'XC70',159,15,1),(NULL,'XC90',160,15,1),(NULL,'Picanto',161,16,1),(NULL,'Cerato Force',162,16,1),(NULL,'Soul',163,16,1),(NULL,'Sportage',164,16,4),(NULL,'Sorento',165,16,4),(NULL,'Lancer',166,17,1),(NULL,'Outlander',167,17,1),(NULL,'L200',168,17,4),(NULL,'Montero',169,17,4),(NULL,'Defender',170,18,4),(NULL,'Freelander',171,18,4),(NULL,'Range Rover',172,18,4),(NULL,'Discovery',173,18,4),(NULL,'Fun',174,19,1),(NULL,'Vitara',175,19,4),(NULL,'Swift',176,19,1),(NULL,'Boxster',177,20,1),(NULL,'Cayman',178,20,1),(NULL,'911',179,20,1),(NULL,'Panamera',180,20,1),(NULL,'Cayenne',181,20,4),(NULL,'Celta',182,21,1),(NULL,'Classic',183,21,1),(NULL,'Prisma',184,21,1),(NULL,'Agile',185,21,1),(NULL,'Aveo',186,21,1),(NULL,'Spatk',187,21,1),(NULL,'Cruze',188,21,1),(NULL,'Sonic',189,21,1),(NULL,'Meriva',190,21,1),(NULL,'Zafira',191,21,1),(NULL,'Montana',192,21,4),(NULL,'S10',193,21,4),(NULL,'Captiva',194,21,4),(NULL,'Cooper',195,22,1),(NULL,'Giulietta',196,23,1),(NULL,'MiTo',197,23,1),(NULL,'159',198,23,1),(NULL,'8C',199,23,1),(NULL,'4C',200,23,1),(NULL,'Civic',201,24,1),(NULL,'CR-V',202,24,4),(NULL,'City',203,24,1),(NULL,'S2000',204,24,1),(NULL,'Cherooke',205,25,4),(NULL,'Rangler',206,25,4),(NULL,'Armado',207,25,4),(NULL,'300C',208,26,1),(NULL,'PT Cruiser',209,26,1),(NULL,'Neon',210,26,1),(NULL,'Speed 6',211,27,1),(NULL,'RX-8',212,27,1),(NULL,'RX-7',213,27,1),(NULL,'l2000',214,27,1);
/*!40000 ALTER TABLE `modelo_vehiculo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `empleado`
--

DROP TABLE IF EXISTS `empleado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `empleado` (
  `cargoEmpleadoID` int(11) NOT NULL,
  `legajo` int(11) DEFAULT NULL,
  `empleadoID` int(11) NOT NULL AUTO_INCREMENT,
  `usuarioID` int(11) NOT NULL,
  PRIMARY KEY (`empleadoID`),
  KEY `cargoEmpleadoID` (`cargoEmpleadoID`),
  KEY `usuarioID` (`usuarioID`),
  CONSTRAINT `FK_empleado_usuario` FOREIGN KEY (`usuarioID`) REFERENCES `usuario` (`usuarioID`),
  CONSTRAINT `FK_empleado_cargo_empleado` FOREIGN KEY (`cargoEmpleadoID`) REFERENCES `cargo_empleado` (`cargoEmpleadoID`)
) ENGINE=InnoDB AUTO_INCREMENT=1010 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empleado`
--

LOCK TABLES `empleado` WRITE;
/*!40000 ALTER TABLE `empleado` DISABLE KEYS */;
INSERT INTO `empleado` VALUES (1,1001,1001,1),(2,1002,1002,5),(1,1003,1003,10),(1,1001,1007,11),(1,1005,1008,12),(1,1001,1009,13);
/*!40000 ALTER TABLE `empleado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_doc`
--

DROP TABLE IF EXISTS `tipo_doc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipo_doc` (
  `nombre` varchar(50) NOT NULL,
  `tipoDocID` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`tipoDocID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_doc`
--

LOCK TABLES `tipo_doc` WRITE;
/*!40000 ALTER TABLE `tipo_doc` DISABLE KEYS */;
INSERT INTO `tipo_doc` VALUES ('D.N.I.',1),('L.C.',2),('L.E.',3),('C.I.',4);
/*!40000 ALTER TABLE `tipo_doc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `denuncia_vehiculo`
--

DROP TABLE IF EXISTS `denuncia_vehiculo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `denuncia_vehiculo` (
  `asunto` text,
  `fechaAlta` datetime NOT NULL,
  `vehiculoID` int(11) NOT NULL,
  `denunciaVehiculoID` int(11) NOT NULL AUTO_INCREMENT,
  `playaID` int(11) NOT NULL,
  `estadoID` int(11) NOT NULL,
  PRIMARY KEY (`denunciaVehiculoID`),
  KEY `vehiculoID` (`vehiculoID`),
  KEY `playaID` (`playaID`),
  KEY `FK_denuncia_vehiculo_estado` (`estadoID`),
  CONSTRAINT `FK_denuncia_vehiculo_playa` FOREIGN KEY (`playaID`) REFERENCES `playa` (`playaID`),
  CONSTRAINT `FK_denuncia_vehiculo_vehiculo` FOREIGN KEY (`vehiculoID`) REFERENCES `vehiculo` (`vehiculoID`),
  CONSTRAINT `FK_denuncia_vehiculo_estado` FOREIGN KEY (`estadoID`) REFERENCES `estado_denuncia` (`estadoDenunciaID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `denuncia_vehiculo`
--

LOCK TABLES `denuncia_vehiculo` WRITE;
/*!40000 ALTER TABLE `denuncia_vehiculo` DISABLE KEYS */;
INSERT INTO `denuncia_vehiculo` VALUES ('No corresponde el auto declarado con el real','2013-06-01 00:00:00',1,1,3,1),('No corresponde el auto declarado con el real','2013-06-01 00:00:00',2,2,3,1),('No corresponde el auto declarado con el real','2013-06-01 00:00:00',1,3,3,1);
/*!40000 ALTER TABLE `denuncia_vehiculo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estadia`
--

DROP TABLE IF EXISTS `estadia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `estadia` (
  `estadiaID` int(11) NOT NULL AUTO_INCREMENT,
  `playaID` int(11) NOT NULL,
  PRIMARY KEY (`estadiaID`),
  KEY `playaID` (`playaID`),
  CONSTRAINT `FK_estadia_playa` FOREIGN KEY (`playaID`) REFERENCES `playa` (`playaID`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='Cada instancia de esta clase representa un conjunto de ingreso y egreso de los autos en cada playa.   ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estadia`
--

LOCK TABLES `estadia` WRITE;
/*!40000 ALTER TABLE `estadia` DISABLE KEYS */;
INSERT INTO `estadia` VALUES (3,3),(4,4),(5,5),(6,6),(7,7),(8,8),(9,9),(10,10),(11,11);
/*!40000 ALTER TABLE `estadia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaccion_playa`
--

DROP TABLE IF EXISTS `transaccion_playa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transaccion_playa` (
  `fecha` datetime DEFAULT NULL,
  `importe` float DEFAULT NULL,
  `tipoPagoID` int(11) DEFAULT NULL,
  `transaccionPlayaID` int(11) NOT NULL AUTO_INCREMENT,
  `cuentaPlayaID` int(11) DEFAULT NULL,
  `liquidacionID` int(11) DEFAULT NULL,
  `detalleEstadiaID` int(11) DEFAULT NULL,
  PRIMARY KEY (`transaccionPlayaID`),
  KEY `detalleEstadiaID` (`detalleEstadiaID`),
  KEY `tipoPagoID` (`tipoPagoID`),
  KEY `cuentaPlayaID` (`cuentaPlayaID`),
  KEY `liquidacionID` (`liquidacionID`),
  CONSTRAINT `FK_transaccion_playa_liquidacion` FOREIGN KEY (`liquidacionID`) REFERENCES `liquidacion` (`liquidacionID`),
  CONSTRAINT `FK_transaccion_playa_cuenta_playa` FOREIGN KEY (`cuentaPlayaID`) REFERENCES `cuenta_playa` (`cuentaPlayaID`),
  CONSTRAINT `FK_transaccion_playa_detalle_estadia` FOREIGN KEY (`detalleEstadiaID`) REFERENCES `detalle_estadia` (`detalleEstadiaID`),
  CONSTRAINT `FK_transaccion_playa_tipo_pago` FOREIGN KEY (`tipoPagoID`) REFERENCES `tipo_pago` (`tipoPagoID`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaccion_playa`
--

LOCK TABLES `transaccion_playa` WRITE;
/*!40000 ALTER TABLE `transaccion_playa` DISABLE KEYS */;
INSERT INTO `transaccion_playa` VALUES ('2012-08-04 19:25:23',20,4,1,1,NULL,1),('2012-08-11 18:45:21',10,4,2,1,NULL,2),('2012-08-18 18:32:49',10,4,3,1,NULL,3),('2013-06-15 18:39:42',10,6,5,1,NULL,5),('2013-06-16 18:40:01',10,6,6,1,NULL,6),('2013-06-20 19:45:52',12,6,7,1,NULL,7),('2013-04-05 20:08:58',12,6,8,1,NULL,8),('2013-04-10 20:09:08',12,6,9,1,NULL,9),('2013-04-12 20:09:16',12,6,10,1,NULL,10),('2013-04-15 20:09:23',12,6,11,1,NULL,11),('2013-04-22 20:09:30',10,6,12,1,NULL,12),('2013-05-02 20:09:46',10,6,13,1,NULL,13),('2013-05-06 20:10:02',12,6,14,1,NULL,14),('2013-05-10 20:10:10',12,6,15,1,NULL,15),('2013-05-11 20:10:17',12,6,16,1,NULL,16),('2013-05-17 20:10:24',12,6,17,1,NULL,17),('2013-05-26 20:10:31',12,6,18,1,NULL,18),('2013-06-17 18:39:42',10,6,19,2,NULL,19),('2013-06-18 18:40:01',10,6,20,2,NULL,20),('2013-06-21 19:45:52',12,6,21,2,NULL,21),('2013-04-06 20:08:58',12,6,22,2,NULL,22),('2013-04-11 20:09:08',12,6,23,2,NULL,23),('2013-04-13 20:09:16',12,6,24,2,NULL,24),('2013-04-16 20:09:23',12,6,25,2,NULL,25),('2013-04-23 20:09:30',10,6,26,2,NULL,26),('2013-05-03 20:09:46',10,6,27,2,NULL,27),('2013-05-07 20:10:02',12,6,28,2,NULL,28),('2013-05-12 20:10:10',12,6,29,2,NULL,29),('2013-05-13 20:10:17',12,6,30,2,NULL,30),('2013-05-18 20:10:24',12,6,31,2,NULL,31),('2013-05-27 20:10:31',12,6,32,2,NULL,32),('2013-06-19 18:39:42',12,6,33,8,NULL,33),('2013-06-20 18:40:01',12,6,34,8,NULL,34),('2013-06-22 19:45:52',14,6,35,8,NULL,35),('2013-04-07 20:08:58',14,6,36,8,NULL,36),('2013-04-12 20:09:08',14,6,37,8,NULL,37),('2013-04-15 20:09:16',14,6,38,8,NULL,38),('2013-04-17 20:09:23',14,6,39,8,NULL,39),('2013-04-24 20:09:30',12,6,40,8,NULL,40),('2013-05-04 20:09:46',12,6,41,8,NULL,41),('2013-05-08 20:10:02',14,6,42,8,NULL,42),('2013-05-14 20:10:10',14,6,43,8,NULL,43),('2013-05-15 20:10:17',14,6,44,8,NULL,44),('2013-05-19 20:10:24',14,6,45,8,NULL,45),('2013-05-28 20:10:31',14,6,46,8,NULL,46);
/*!40000 ALTER TABLE `transaccion_playa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `apellido` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `nombre` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `sesion` varchar(50) DEFAULT NULL,
  `usuario` varchar(50) NOT NULL,
  `usuarioID` int(11) NOT NULL AUTO_INCREMENT,
  `tipoDocID` int(11) DEFAULT NULL,
  `nroDoc` varchar(50) DEFAULT NULL,
  `enable` tinyint(1) DEFAULT '1',
  `playaID` int(11) DEFAULT NULL,
  `fotoUsuarioID` int(11) DEFAULT NULL,
  PRIMARY KEY (`usuarioID`),
  UNIQUE KEY `usuario` (`usuario`),
  UNIQUE KEY `email` (`email`),
  KEY `tipoDocID` (`tipoDocID`),
  KEY `fotoUsuarioID` (`fotoUsuarioID`),
  KEY `FK_usuario_playa` (`playaID`),
  CONSTRAINT `FK_usuario_tipo_doc` FOREIGN KEY (`tipoDocID`) REFERENCES `tipo_doc` (`tipoDocID`),
  CONSTRAINT `FK_usuario_foto_usuario` FOREIGN KEY (`fotoUsuarioID`) REFERENCES `foto_usuario` (`fotoUsuarioID`),
  CONSTRAINT `FK_usuario_playa` FOREIGN KEY (`playaID`) REFERENCES `playa` (`playaID`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES ('Moreno','pablo_la31@hotmail.com','Pablo','123456',NULL,'pablo',1,1,'32987654',1,3,NULL),('Bostico','alebostico@hotmail.com','Alejandro','123456',NULL,'alejandro',2,1,'11111111',1,NULL,NULL),('Arribere','gonzaloarribere@gmail.com','Gonzalo','123456',NULL,'gonzalo',3,1,'22222222',1,NULL,NULL),('Morales Batovski','morales.batovski@gmail.com','Raúl Gustavo','123456',NULL,'gmorales',4,1,'29966905',1,NULL,NULL),('Perez Villar','ericperezvillar@gmail.com','Eric','123456',NULL,'eric',5,1,'44444444',1,3,NULL),('Admin','admin@playon.com.ar','Super','123456',NULL,'admin',6,1,'66666666',1,NULL,NULL),('Gomez','sgomez@playon.com.ar','Silvina','123456',NULL,'sgomez',7,1,'10456654',1,NULL,NULL),('Lopez','jjlopez@playon.com.ar','Juana Josefa','123456',NULL,'jjlopez',8,1,'27600710',1,NULL,NULL),('Pérez','jperez@playon.com.ar','Juan','123456',NULL,'jperez',9,1,'31234234',1,NULL,NULL),('Santoni','rsantoni@playon.com.ar','Rafael','123456',NULL,'rsantoni',10,1,'15454597',1,NULL,NULL),('Rube','richardparking@hotmail.com','Ricardo','123456',NULL,'richardparking',11,1,'123456777',1,4,NULL),('Ricardo','ricardoempleado@hotmail.com','Ramirez','123456',NULL,'ricardoempleado',12,1,'34045824',1,10,NULL),('Argento','gerente@gerencia.com.ar','Pedro','123456',NULL,'pargento',13,1,'10252102',1,11,NULL);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `localidad`
--

DROP TABLE IF EXISTS `localidad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `localidad` (
  `nombre` varchar(50) NOT NULL,
  `localidadID` int(11) NOT NULL AUTO_INCREMENT,
  `provinciaID` int(11) NOT NULL,
  PRIMARY KEY (`localidadID`),
  KEY `provinciaID` (`provinciaID`),
  CONSTRAINT `FK_localidad_provincia` FOREIGN KEY (`provinciaID`) REFERENCES `provincia` (`provinciaID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `localidad`
--

LOCK TABLES `localidad` WRITE;
/*!40000 ALTER TABLE `localidad` DISABLE KEYS */;
INSERT INTO `localidad` VALUES ('Córdoba',1,1);
/*!40000 ALTER TABLE `localidad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estado_playa`
--

DROP TABLE IF EXISTS `estado_playa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `estado_playa` (
  `descripcion` text,
  `nombre` varchar(50) NOT NULL,
  `estadoPlayaID` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`estadoPlayaID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estado_playa`
--

LOCK TABLES `estado_playa` WRITE;
/*!40000 ALTER TABLE `estado_playa` DISABLE KEYS */;
INSERT INTO `estado_playa` VALUES ('Pendiente de Auditorí­a','Pendiente',1),('Aprobada luego de auditoría','Aprobada',2),('Rechazada luego de auditorí­a','Rechazada',3),('Dada de baja','De Baja',4);
/*!40000 ALTER TABLE `estado_playa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estado_denuncia`
--

DROP TABLE IF EXISTS `estado_denuncia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `estado_denuncia` (
  `descripcion` text,
  `nombre` varchar(50) NOT NULL,
  `estadoDenunciaID` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`estadoDenunciaID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estado_denuncia`
--

LOCK TABLES `estado_denuncia` WRITE;
/*!40000 ALTER TABLE `estado_denuncia` DISABLE KEYS */;
INSERT INTO `estado_denuncia` VALUES ('Pendiente de auditorí­a','Pendiente',1),('En proceso de investigación','En Proceso',2),('Acepatada','Aceptada',3),('Rechazada','Rechazada',4),('Anulada','Anulada',5),('Dada de Baja / Cancelada','De Baja',6);
/*!40000 ALTER TABLE `estado_denuncia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cliente` (
  `barrioID` int(11) DEFAULT NULL,
  `cuentaClienteID` int(11) NOT NULL,
  `domicilio` text,
  `nroCliente` int(11) NOT NULL,
  `telefono` text,
  `clienteID` int(11) NOT NULL AUTO_INCREMENT,
  `usuarioID` int(11) NOT NULL,
  PRIMARY KEY (`clienteID`),
  KEY `barrioID` (`barrioID`),
  KEY `usuarioID` (`usuarioID`),
  CONSTRAINT `FK_cliente_usuario` FOREIGN KEY (`usuarioID`) REFERENCES `usuario` (`usuarioID`),
  CONSTRAINT `FK_cliente_barrio` FOREIGN KEY (`barrioID`) REFERENCES `barrio` (`barrioID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (1,4,'Chile 160',1,'156123456',1,3),(2,1,'Colon 566',2,'152123456',2,7),(1,2,'Buenos Aires 75',3,'153123456',3,8),(3,3,'Av. Valparaiso 3125',4,NULL,4,9);
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `marca_vehiculo`
--

DROP TABLE IF EXISTS `marca_vehiculo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `marca_vehiculo` (
  `descripcion` text,
  `nombre` varchar(50) NOT NULL,
  `marcaVehiculoID` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`marcaVehiculoID`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `marca_vehiculo`
--

-- =================================================================== --

/**
 * Creación del procedure
 */

DELIMITER $$

DROP PROCEDURE IF EXISTS `tesis_playon`.`busquedaAvanzada` $$
CREATE PROCEDURE `tesis_playon`.`busquedaAvanzada` (
IN platitud DOUBLE,
IN plongitud DOUBLE,
IN pTipoEstadia INT,
IN pCategoriaVehiculo INT,
IN pNombreComercial MEDIUMTEXT,
IN pPromociones TINYINT
)
BEGIN
    SELECT p.barrioID, p.cuit, p.disponibilidad, p.domicilio, p.telefono, p.email,
        p.estadoPlayaID, p.nombreComercial, p.latitud, p.longitud, p.razonSocial,
        p.playaID, p.url
    FROM playa p
    WHERE ((p.playaID IN (SELECT DISTINCT t.playaID
                            FROM tarifa t
                            WHERE (pTipoEstadia = 0 OR t.tipoEstadiaID = pTipoEstadia)
                                AND (pCategoriaVehiculo = 0 OR t.categoriaVehiculoID = pCategoriaVehiculo)))
            AND( pPromociones = 0 OR (p.playaID IN (SELECT DISTINCT promo.playaID
                                                        FROM promocion promo
                                                        WHERE pPromociones = 1
                                                                AND promo.estadoPromocionID=2
                                                                AND (now() >= promo.fechaInicio )
                                                                AND (now() <= promo.fechaFin ))))
            AND (p.estadoPlayaID = 2)
            AND (p.disponibilidad > 0));
END $$

DELIMITER ;

-- =================================================================== --


LOCK TABLES `marca_vehiculo` WRITE;
/*!40000 ALTER TABLE `marca_vehiculo` DISABLE KEYS */;
INSERT INTO `marca_vehiculo` VALUES (NULL,'Renault',1),(NULL,'Ford',2),(NULL,'Volkswagen',3),(NULL,'Mercedes Benz',4),(NULL,'Peugeot',5),(NULL,'Citroen',6),(NULL,'BMW',7),(NULL,'Audi',8),(NULL,'Seat',9),(NULL,'Opel',10),(NULL,'Fiat',11),(NULL,'Nissan',12),(NULL,'Toyota',13),(NULL,'Hyundai',14),(NULL,'Volvo',15),(NULL,'Kia',16),(NULL,'Mitsubishi',17),(NULL,'Land Rover',18),(NULL,'Suzuki',19),(NULL,'Porche',20),(NULL,'Chevrolet',21),(NULL,'Mini',22),(NULL,'Alfa Romeo',23),(NULL,'Honda',24),(NULL,'Jeep',25),(NULL,'Chrysler',26),(NULL,'Mazda',27);
/*!40000 ALTER TABLE `marca_vehiculo` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-06-10  1:53:19
