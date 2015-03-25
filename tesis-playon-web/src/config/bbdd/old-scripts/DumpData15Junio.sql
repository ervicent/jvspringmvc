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
  CONSTRAINT `FK_roles_por_usuario_rol_usuario` FOREIGN KEY (`rolUsuario`) REFERENCES `rol_usuario` (`nombre`),
  CONSTRAINT `FK_roles_por_usuario_usuario` FOREIGN KEY (`usuario`) REFERENCES `usuario` (`usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles_por_usuario`
--

LOCK TABLES `roles_por_usuario` WRITE;
/*!40000 ALTER TABLE `roles_por_usuario` DISABLE KEYS */;
INSERT INTO `roles_por_usuario` VALUES (1,'pablo','ROLE_PLAYA_GERENTE'),(2,'alejandro','ROLE_AUDITOR'),(3,'gonzalo','ROLE_CLIENT'),(4,'gmorales','ROLE_ADMIN'),(5,'eric','ROLE_PLAYA_EMPLEADO'),(6,'admin','ROLE_ADMIN'),(7,'sgomez','ROLE_CLIENT'),(8,'jjlopez','ROLE_CLIENT'),(9,'jperez','ROLE_CLIENT'),(10,'rsantoni','ROLE_PLAYA_GERENTE'),(11,'richardparking','ROLE_PLAYA_GERENTE'),(12,'ricardoempleado','ROLE_PLAYA_EMPLEADO'),(13,'pargento','ROLE_PLAYA_GERENTE'),(14,'jmeolanz','ROLE_PLAYA_GERENTE'),(15,'lposse','ROLE_PLAYA_GERENTE'),(16,'emorales','ROLE_PLAYA_GERENTE'),(17,'jalperovich','ROLE_PLAYA_GERENTE'),(18,'jpilatti','ROLE_PLAYA_GERENTE'),(19,'jlastra','ROLE_PLAYA_GERENTE'),(20,'jcristobal','ROLE_PLAYA_GERENTE'),(21,'gfundaro','ROLE_CLIENT'),(22,'flizzi','ROLE_CLIENT'),(23,'jromero','ROLE_CLIENT'),(24,'cposse','ROLE_CLIENT'),(25,'csantoni','ROLE_CLIENT'),(26,'mgripo','ROLE_CLIENT'),(27,'sdiaz','ROLE_CLIENT'),(28,'msantoni','ROLE_CLIENT');
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
  CONSTRAINT `FK_calificacion_cliente` FOREIGN KEY (`clienteID`) REFERENCES `cliente` (`clienteID`),
  CONSTRAINT `FK_calificacion_playa` FOREIGN KEY (`playaID`) REFERENCES `playa` (`playaID`)
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
  CONSTRAINT `FK_transaccion_cliente_cuenta_cliente` FOREIGN KEY (`cuentaClienteID`) REFERENCES `cuenta_cliente` (`cuentaClienteID`),
  CONSTRAINT `FK_transaccion_cliente_tipo_pago` FOREIGN KEY (`tipoPagoID`) REFERENCES `tipo_pago` (`tipoPagoID`)
) ENGINE=InnoDB AUTO_INCREMENT=187 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaccion_cliente`
--

LOCK TABLES `transaccion_cliente` WRITE;
/*!40000 ALTER TABLE `transaccion_cliente` DISABLE KEYS */;
INSERT INTO `transaccion_cliente` VALUES ('2012-08-03 13:23:45',100,4,1,1),('2012-08-04 19:25:23',20,6,2,1),('2012-08-11 18:45:21',10,6,3,1),('2012-08-18 18:32:49',10,6,4,1),('2013-06-15 18:39:42',10,6,5,4),('2013-06-16 18:40:01',10,6,6,4),('2013-06-20 19:45:52',12,6,7,3),('2013-04-05 20:08:58',12,6,8,3),('2013-04-10 20:09:08',12,6,9,3),('2013-04-12 20:09:16',12,6,10,3),('2013-04-15 20:09:23',12,6,11,3),('2013-04-22 20:09:30',10,6,12,4),('2013-05-02 20:09:46',10,6,13,3),('2013-05-06 20:10:02',12,6,14,3),('2013-05-10 20:10:10',12,6,15,4),('2013-05-11 20:10:17',12,6,16,3),('2013-05-17 20:10:24',12,6,17,4),('2013-05-26 20:10:31',12,6,18,3),('2013-06-17 18:39:42',10,6,19,4),('2013-06-18 18:40:01',10,6,20,4),('2013-06-21 19:45:52',12,6,21,3),('2013-04-06 20:08:58',12,6,22,3),('2013-04-11 20:09:08',12,6,23,3),('2013-04-14 20:09:16',12,6,24,3),('2013-04-16 20:09:23',12,6,25,3),('2013-04-23 20:09:30',10,6,26,4),('2013-05-03 20:09:46',10,6,27,4),('2013-05-07 20:10:02',12,6,28,3),('2013-05-12 20:10:10',12,6,29,3),('2013-05-13 20:10:17',12,6,30,3),('2013-05-18 20:10:24',12,6,31,3),('2013-05-27 20:10:31',12,6,32,3),('2013-06-19 18:39:42',12,6,33,4),('2013-06-20 18:40:01',12,6,34,4),('2013-06-22 19:45:52',14,6,35,3),('2013-04-07 20:08:58',14,6,36,3),('2013-04-12 20:09:08',14,6,37,4),('2013-04-15 20:09:16',14,6,38,3),('2013-04-17 20:09:23',14,6,39,3),('2013-04-24 20:09:30',12,6,40,4),('2013-05-04 20:09:46',12,6,41,4),('2013-05-08 20:10:02',14,6,42,3),('2013-05-14 20:10:10',14,6,43,3),('2013-05-15 20:10:17',14,6,44,3),('2013-05-19 20:10:24',14,6,45,3),('2013-05-28 20:10:31',14,6,46,3),('2013-06-18 13:23:45',90,4,47,1),('2013-04-20 19:25:23',50,4,48,2),('2013-06-18 18:45:21',500,4,49,3),('2013-06-13 18:32:49',300,4,50,4),('2013-01-11 02:11:33',-17,6,51,4),('2013-01-11 02:11:57',-17,6,52,1),('2013-06-13 19:34:40',800,4,53,5),('2013-06-13 19:37:48',1200,4,54,6),('2013-06-13 19:41:21',1100,4,55,7),('2013-06-13 19:45:25',3000,4,56,8),('2013-06-13 19:49:54',3500,4,57,9),('2013-06-13 19:55:31',3400,4,58,10),('2013-06-13 20:09:05',3200,4,59,11),('2013-06-13 20:09:05',3200,4,60,11),('2013-06-13 20:16:35',2999,4,61,12),('2013-06-13 20:28:55',-17,6,62,5),('2013-06-13 20:29:11',-17,6,63,9),('2013-06-13 20:29:35',-17,6,64,8),('2013-06-13 20:29:44',-17,6,65,6),('2013-06-13 20:29:57',-17,6,66,11),('2013-06-13 20:30:27',-17,6,67,10),('2013-01-07 21:47:52',-17,6,68,5),('2013-01-07 21:48:10',-17,6,69,6),('2013-01-07 21:48:28',-17,6,70,8),('2013-01-07 21:48:54',-17,6,71,10),('2013-01-04 21:52:12',-13,6,72,5),('2013-01-04 21:53:00',-13,6,73,10),('2013-01-04 21:55:51',-13,6,74,8),('2013-01-04 21:56:36',-13,6,75,9),('2013-01-04 21:56:42',-13,6,76,11),('2013-01-01 21:58:54',-19,6,77,5),('2013-01-01 21:59:08',-19,6,78,10),('2013-01-01 21:59:24',-19,6,79,8),('2013-01-01 21:59:37',-19,6,80,11),('2013-06-13 22:01:14',-19,6,81,5),('2013-06-13 22:01:19',-19,6,82,10),('2013-06-13 22:01:36',1166,6,83,6),('2013-06-13 22:01:36',48754,1,84,6),('2013-06-13 22:02:05',-19,6,85,11),('2013-01-16 22:04:36',-19,6,86,5),('2013-01-16 22:05:06',-19,6,87,10),('2013-01-16 22:07:33',-19,6,88,11),('2013-01-16 22:09:06',-19,6,89,5),('2013-01-17 22:09:22',-80,6,90,5),('2013-01-16 22:10:08',-19,6,91,11),('2013-06-13 22:16:25',-19,6,92,8),('2013-06-13 23:07:31',-12,6,93,5),('2013-01-11 23:09:50',-12,6,94,8),('2013-06-13 23:15:44',-12,6,95,10),('2013-01-12 23:17:05',-288,6,96,5),('2013-01-12 23:17:21',-12,6,97,10),('2013-01-12 23:23:07',-7,6,98,5),('2013-01-12 23:23:19',-7,6,99,8),('2013-01-12 23:23:24',-7,6,100,10),('2013-01-12 23:23:30',-300,6,101,11),('2013-01-12 23:26:24',-7,6,102,10),('2013-01-12 23:27:45',0,6,103,6),('2013-01-12 23:27:45',12,1,104,6),('2013-01-12 23:27:57',-8,6,105,10),('2013-01-12 23:28:03',-8,6,106,5),('2013-06-13 23:57:22',-11,6,107,5),('2013-06-13 23:57:36',-11,6,108,10),('2013-06-13 23:57:49',-11,6,109,8),('2013-01-07 23:59:06',-11,6,110,5),('2013-01-07 23:59:19',-11,6,111,10),('2013-01-07 23:59:31',-11,6,112,8),('2013-01-08 00:00:19',-11,6,113,10),('2013-01-08 00:00:36',-11,6,114,5),('2013-01-08 00:00:53',-11,6,115,8),('2013-06-14 19:49:32',2500,4,116,6),('2013-01-20 20:32:22',-11,6,117,5),('2013-01-20 20:32:42',-11,6,118,8),('2013-01-20 20:33:05',-11,6,119,7),('2013-01-20 20:34:28',-11,6,120,8),('2013-01-20 20:34:51',-11,6,121,7),('2013-02-18 20:39:23',-11,6,122,7),('2013-02-18 20:39:27',-11,6,123,8),('2013-02-18 20:39:47',-11,6,124,5),('2013-02-18 20:51:54',-11,6,125,7),('2013-02-18 20:53:14',-11,6,126,10),('2013-02-18 20:54:10',-15,6,127,7),('2013-02-18 20:54:15',-12,6,128,5),('2013-02-18 20:54:21',-12,6,129,10),('2013-02-18 20:54:40',-12,6,130,8),('2013-02-18 20:55:02',-12,6,131,5),('2013-02-18 20:55:13',-15,6,132,7),('2013-02-18 20:55:19',-12,6,133,10),('2013-02-18 20:57:28',-25,6,134,7),('2013-02-18 20:57:44',-19,6,135,8),('2013-02-18 20:57:56',-19,6,136,10),('2013-02-18 20:58:25',-25,6,137,7),('2013-02-18 20:58:30',-19,6,138,8),('2013-02-18 20:58:35',-19,6,139,5),('2013-02-18 20:58:53',-19,6,140,10),('2013-02-18 20:59:27',-13,6,141,8),('2013-02-18 21:05:55',-17,6,142,8),('2013-02-18 21:06:07',-17,6,143,5),('2013-02-18 21:06:25',-17,6,144,10),('2013-02-18 21:06:53',-17,6,145,8),('2013-02-18 21:07:13',-17,6,146,10),('2013-02-18 21:07:26',-17,6,147,8),('2013-03-18 23:30:36',-17,6,148,8),('2013-03-18 23:30:43',-17,6,149,10),('2013-03-18 23:31:03',-17,6,150,8),('2013-03-18 23:34:35',-17,6,151,6),('2013-03-18 23:34:50',-17,6,152,5),('2013-03-18 23:35:14',-17,6,153,10),('2013-03-18 23:35:39',-17,6,154,5),('2013-03-24 15:00:23',-11,6,155,5),('2013-03-24 15:00:36',-11,6,156,6),('2013-03-24 15:02:03',-11,6,157,10),('2013-06-15 15:37:38',-11,6,158,6),('2013-06-16 15:37:51',-60,6,159,6),('2013-06-15 15:43:29',-11,6,160,5),('2013-06-15 15:44:12',-11,6,161,10),('2013-06-15 15:44:18',-11,6,162,5),('2013-06-15 15:48:37',-8,6,163,5),('2013-04-05 15:54:20',-8,6,164,10),('2013-04-06 15:54:41',-78,6,165,10),('2013-04-05 15:55:26',-8,6,166,5),('2013-06-15 16:08:22',-11,6,167,6),('2013-06-15 16:08:43',-798,6,168,9),('2013-06-15 16:11:20',-11,6,169,6),('2013-06-15 16:12:14',-11,6,170,6),('2013-06-15 16:13:19',-11,6,171,5),('2013-05-09 16:20:08',-11,6,172,6),('2013-05-09 16:21:01',-11,6,173,9),('2013-05-10 16:21:38',-60,6,174,9),('2013-05-10 16:22:17',-60,6,175,6),('2013-05-09 16:34:06',-17,6,176,6),('2013-05-09 16:35:03',-11,6,177,5),('2013-05-10 16:35:13',-58,6,178,9),('2013-05-09 16:36:53',-17,6,179,6),('2013-05-09 16:37:06',-17,6,180,5),('2013-05-09 16:37:22',-11,6,181,10),('2013-03-18 16:47:48',-11,6,182,6),('2013-03-18 16:48:12',-11,6,183,10),('2013-03-19 16:55:29',-306,6,184,8),('2013-01-13 17:08:05',-126,6,185,11),('2013-03-24 17:50:29',-33,6,186,7);
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
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cuenta_playa`
--

LOCK TABLES `cuenta_playa` WRITE;
/*!40000 ALTER TABLE `cuenta_playa` DISABLE KEYS */;
INSERT INTO `cuenta_playa` VALUES ('2012-08-22 00:00:00',1,72,1,3),('2012-08-22 00:00:00',2,32,2,4),('2012-08-22 00:00:00',3,0,3,5),('2012-08-22 00:00:00',4,0,4,6),('2012-08-22 00:00:00',5,0,5,7),('2012-08-22 00:00:00',6,0,6,8),('2012-08-22 00:00:00',7,0,7,9),('2012-08-22 00:00:00',8,37,8,10),('2013-01-11 02:11:08',1012,-454,9,12),('2013-01-07 21:49:56',1013,-78,10,13),('2013-01-01 21:58:44',1014,-396,11,14),('2013-01-16 22:13:52',1015,-402,12,15),('2013-01-12 23:19:44',1017,-328,13,17),('2013-01-12 23:27:05',NULL,-155,14,16),('2013-06-13 23:57:10',1018,-805,15,18);
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
  CONSTRAINT `FK_denuncia_playa_estado` FOREIGN KEY (`estadoID`) REFERENCES `estado_denuncia` (`estadoDenunciaID`),
  CONSTRAINT `FK_denuncia_playa_playa` FOREIGN KEY (`playaID`) REFERENCES `playa` (`playaID`)
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
  CONSTRAINT `FK_comentario_perfil_playa` FOREIGN KEY (`playaID`) REFERENCES `perfil_playa` (`perfilPlayaID`),
  CONSTRAINT `FK_comentario_usuario` FOREIGN KEY (`usuarioID`) REFERENCES `usuario` (`usuarioID`)
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
  CONSTRAINT `FK_promocion_estado_promocion` FOREIGN KEY (`estadoPromocionID`) REFERENCES `estado_promocion` (`estadoPromocionID`),
  CONSTRAINT `FK_promocion_playa` FOREIGN KEY (`playaID`) REFERENCES `playa` (`playaID`),
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
  CONSTRAINT `FK_historial_de_cambio_denuncia_playa` FOREIGN KEY (`denunciaPlayaID`) REFERENCES `denuncia_playa` (`denunciaPlayaID`),
  CONSTRAINT `FK_historial_de_cambio_denuncia_vehiculo` FOREIGN KEY (`denunciaVehiculoID`) REFERENCES `denuncia_vehiculo` (`denunciaVehiculoID`),
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
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COMMENT='Cada instancia representa los datos administrativos de una playa.   ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `playa`
--

LOCK TABLES `playa` WRITE;
/*!40000 ALTER TABLE `playa` DISABLE KEYS */;
INSERT INTO `playa` VALUES (2,'30-11111111-1',55,'Buenos Aires 548','4425852','playa@europa.com','http://www.cualquiera.com',2,'Estacionamiento Europa',-31.4229765,-64.18568859999999,'Estacionamiento Europa',3),(2,'30-11111111-1',100,'Independencia 770','4411888','playa@independencia.com','http://www.independencia.com',2,'Estacionamiento Independencia',-31.4251654,-64.1881208,'Estacionamiento Independencia',4),(2,'30-11111111-1',100,'Obispo Trejo 758','4442525','playa@parkingciti.com','http://www.city.com',2,'Parkingcity',-31.4246207,-64.1893771,'Parkingcity',5),(1,'30-11111111-1',100,'Bv. Ilia 156','44119789','playa@msur.com.ar','http://www.mercado.com',2,'Estacionamiento Mercado Sur',-31.421262,-64.18423299999999,'Estacionamiento Mercado Sur',6),(1,'30-11111111-1',100,'Santa Rosa 631','44112333','playa@poine.com','http://www.parking.com',1,'Parking One',-31.4099508,-64.19153299999999,'Parking One',7),(1,'30-11111111-1',100,'Bv. Chacabuco 728',NULL,NULL,NULL,2,'Playa Sur',-31.425904,-64.183791,'Playa Sur',8),(1,'30-11111111-1',100,'Bv. Illia 70',NULL,NULL,NULL,2,'Parquin Verde',-31.420956,-64.18535,'Parquin Verde',9),(1,'30-11111111-1',100,'Colon 756','4411214','','http://www.control.com',2,'Estacionamiento Colón',-31.4105014,-64.1936027,'Estacionamiento Colón',10),(3,'20-32855878-3',100,'velez sarsfield 2600','03514339851','gonzaloarribere@gmail.com','http://www.cualquiera.com',2,'Estacionamiento Jardin',-31.444738,-64.198247,'Playa jardin',11),(1,'10-26999888-3',151,'Ayacucho 45','4669858','ayacucho@gmail.com','http://ayacucho.com.ar',2,'Estacionamiento ayacucho',-31.4148413,-64.1898935,'Estacionamiento ayacucho',12),(1,'20-36999845-8',123,'Rivera Indarte 50','4585254','parking@center.com','http://pcenter.com.ar',2,'Parking center',-31.4143928,-64.1850821,'Parking center',13),(2,'20-28999545-8',49,'Bolivia 59','4587852','estacionam@newparking.com','http://newparking.com',2,'New Parking',-31.4311589,-64.1897251,'New Parking',14),(1,'20-25989525-8',198,'tucuman 400','4425859','info@ledesma.com','http://ledesma@ledesma.com',2,'Estacionamiento Ledesma',-31.41016359999999,-64.1864552,'Estacionamiento Ledesma',15),(1,'20-152254512-8',302,'Humberto Primo 45','4588895','info@playahumberto.com','http://www.humberoplayas.com',2,'Playa Humberto',-31.40994359999999,-64.182437,'Playa Humberto',16),(1,'20-20698586-8',100,'San Jeronimo 2000','45896635','info@sanjero.com','http://www.psanjero.com',2,'Playas San Jero',-31.4224468,-64.1580554,'Playa San Jero',17),(1,'20-15251441-8',149,'Colon 2500','4522589','info@playasanjorge.com','http://www.sanjorge.com',2,'Playa San Jorge',-31.4040985,-64.2150095,'Playa San Jorge',18);
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
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `liquidacion`
--

LOCK TABLES `liquidacion` WRITE;
/*!40000 ALTER TABLE `liquidacion` DISABLE KEYS */;
INSERT INTO `liquidacion` VALUES ('2013-02-01 00:00:00','2013-01-31 00:00:00','2013-01-01 00:00:00',91.8,1,12),('2013-02-01 00:00:00','2013-01-31 00:00:00','2013-01-01 00:00:00',58.5,2,13),('2013-02-01 00:00:00','2013-01-31 00:00:00','2013-01-01 00:00:00',225.9,3,14),('2013-02-01 00:00:00','2013-01-31 00:00:00','2013-01-01 00:00:00',280.8,4,15),('2013-02-01 00:00:00','2013-01-31 00:00:00','2013-01-01 00:00:00',25.2,5,16),('2013-02-01 00:00:00','2013-01-31 00:00:00','2013-01-01 00:00:00',295.2,6,17),('2013-02-01 00:00:00','2013-01-31 00:00:00','2013-01-01 00:00:00',222.3,7,18),('2013-03-01 00:00:00','2013-02-28 00:00:00','2013-02-01 00:00:00',91.8,8,12),('2013-03-01 00:00:00','2013-02-28 00:00:00','2013-02-01 00:00:00',11.7,9,13),('2013-03-01 00:00:00','2013-02-28 00:00:00','2013-02-01 00:00:00',130.5,10,14),('2013-03-01 00:00:00','2013-02-28 00:00:00','2013-02-01 00:00:00',81,11,15),('2013-03-01 00:00:00','2013-02-28 00:00:00','2013-02-01 00:00:00',49.5,12,18),('2013-04-01 00:00:00','2013-03-31 00:00:00','2013-03-01 00:00:00',107.1,13,12),('2013-04-01 00:00:00','2013-03-31 00:00:00','2013-03-01 00:00:00',29.7,14,16),('2013-04-01 00:00:00','2013-03-31 00:00:00','2013-03-01 00:00:00',324.9,15,18),('2013-05-01 00:00:00','2013-04-30 00:00:00','2013-04-01 00:00:00',52.2,16,3),('2013-05-01 00:00:00','2013-04-30 00:00:00','2013-04-01 00:00:00',52.2,17,4),('2013-05-01 00:00:00','2013-04-30 00:00:00','2013-04-01 00:00:00',61.2,18,10),('2013-05-01 00:00:00','2013-04-30 00:00:00','2013-04-01 00:00:00',84.6,19,16),('2013-06-01 00:00:00','2013-05-31 00:00:00','2013-05-01 00:00:00',117.9,20,12),('2013-06-01 00:00:00','2013-05-31 00:00:00','2013-05-01 00:00:00',127.8,21,18),('2013-06-01 00:00:00','2013-05-31 00:00:00','2013-05-01 00:00:00',63,22,3),('2013-06-01 00:00:00','2013-05-31 00:00:00','2013-05-01 00:00:00',63,23,4),('2013-06-01 00:00:00','2013-05-31 00:00:00','2013-05-01 00:00:00',73.8,24,10);
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `foto`
--

LOCK TABLES `foto` WRITE;
/*!40000 ALTER TABLE `foto` DISABLE KEYS */;
/*!40000 ALTER TABLE `foto` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vehiculo`
--

LOCK TABLES `vehiculo` WRITE;
/*!40000 ALTER TABLE `vehiculo` DISABLE KEYS */;
INSERT INTO `vehiculo` VALUES (2010,NULL,2,1,1,'ABC123',1,1),(2011,NULL,9,1,6,'DEF456',2,2),(2009,NULL,12,1,15,'GHI789',3,3),(2009,NULL,3,1,10,'JKL159',4,4),(2012,NULL,7,1,102,'GGG444',5,5),(2012,NULL,6,1,91,'HHH222',6,6),(2002,NULL,8,1,8,'CCC222',7,7),(2012,NULL,7,1,103,'HHH999',8,8),(2000,NULL,6,1,6,'BBB222',9,9),(1998,NULL,6,0,10,'AZN373',10,10),(1994,NULL,1,1,121,'SSS456',11,11),(2005,NULL,1,1,126,'DDD999',12,12),(1999,NULL,7,1,16,'AZN372',13,10);
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
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COMMENT='Esta clase va a contener todos los datos del perfil de la playa que se muestra en el sitio: fotos, nombre para mostrar, descripción, etc.    ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `perfil_playa`
--

LOCK TABLES `perfil_playa` WRITE;
/*!40000 ALTER TABLE `perfil_playa` DISABLE KEYS */;
INSERT INTO `perfil_playa` VALUES ('¡Los esperamos en Estacionamiento Europa, les ofrecemos una excelente atención con muy buenos precios!',3,3,NULL,NULL),('Playa \"Estacionamiento Independencia\"',4,4,NULL,NULL),('Playa \"Parkingcity',5,5,NULL,NULL),('Playa \"Estacionamiento Mercado Sur\"',6,6,NULL,NULL),('Playa \"Parking One\"',7,7,NULL,NULL),('Playa \"Playa Sur\"',8,8,NULL,NULL),('Playa \"Parquin Verde\"',9,9,NULL,NULL),('Playa \"Estacionamiento Colón\"',10,10,NULL,NULL),(NULL,11,11,NULL,NULL),(NULL,12,12,NULL,NULL),(NULL,13,13,NULL,NULL),(NULL,14,14,NULL,NULL),(NULL,15,15,NULL,NULL),(NULL,16,16,NULL,NULL),(NULL,17,17,NULL,NULL),(NULL,18,18,NULL,NULL);
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
  CONSTRAINT `FK_abono_promocion` FOREIGN KEY (`promocionID`) REFERENCES `promocion` (`promocionID`),
  CONSTRAINT `FK_abono_tarifa` FOREIGN KEY (`tarifaID`) REFERENCES `tarifa` (`tarifaID`),
  CONSTRAINT `FK_abono_vehiculo` FOREIGN KEY (`vehiculoID`) REFERENCES `vehiculo` (`vehiculoID`)
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
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cuenta_cliente`
--

LOCK TABLES `cuenta_cliente` WRITE;
/*!40000 ALTER TABLE `cuenta_cliente` DISABLE KEYS */;
INSERT INTO `cuenta_cliente` VALUES ('2012-08-21 20:29:37',1001,133,1,2),('2012-08-21 20:29:37',1002,50,2,3),('2012-08-21 20:29:37',1003,148,3,4),('2012-08-21 20:29:37',1004,127,4,1),('2013-06-13 19:30:50',1005,45,5,5),('2013-06-13 19:36:21',1006,2252,6,6),('2013-06-13 19:39:47',1007,943,7,7),('2013-06-13 19:43:40',1008,2376,8,8),('2013-06-13 19:47:14',1009,2543,9,9),('2013-06-13 19:52:46',1010,2946,10,10),('2013-06-13 20:08:07',1011,5868,11,11),('2013-06-13 20:15:50',1012,2999,12,12);
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
  CONSTRAINT `FK_tarifa_categoria_vehiculo` FOREIGN KEY (`categoriaVehiculoID`) REFERENCES `categoria_vehiculo` (`categoriaVehiculoID`),
  CONSTRAINT `FK_tarifa_playa` FOREIGN KEY (`playaID`) REFERENCES `playa` (`playaID`),
  CONSTRAINT `FK_tarifa_tipo_estadia` FOREIGN KEY (`tipoEstadiaID`) REFERENCES `tipo_estadia` (`tipoEstadiaID`)
) ENGINE=InnoDB AUTO_INCREMENT=105 DEFAULT CHARSET=utf8 COMMENT='Cada instancia contiene un precio de la tarifa que depende del Categorí­a de vehí­culo, tipo de estadí­a (mensual, por hora, etc).      Categorí­aVehiculo: utilitario   TipoEstadí­a: Mensual   Precio/tarifa: $720      Categorí­aVehiculo: utilitario   TipoEstadí­a: Por hora   Precio/tarifa: $14      Categorí­aVehiculo: auto   TipoEstadí­a: Por hora   Precio/tarifa: $12   ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tarifa`
--

LOCK TABLES `tarifa` WRITE;
/*!40000 ALTER TABLE `tarifa` DISABLE KEYS */;
INSERT INTO `tarifa` VALUES ('2012-07-04 19:25:23',NULL,10,1,1,3,1,1),('2012-07-04 19:25:23',NULL,12,1,2,3,1,2),('2012-07-04 19:25:23',NULL,12,1,3,3,1,3),('2012-07-04 19:25:23',NULL,500,1,4,3,2,1),('2012-07-04 19:25:23',NULL,550,1,5,3,2,2),('2012-07-04 19:25:23',NULL,550,1,6,3,2,3),('2012-07-04 19:25:23',NULL,11,1,7,10,1,1),('2012-07-04 19:25:23',NULL,13,1,8,10,1,2),('2012-07-04 19:25:23',NULL,14,1,9,10,1,3),('2012-07-04 19:25:23',NULL,650,1,10,10,2,1),('2012-07-04 19:25:23',NULL,690,1,11,10,2,2),('2012-07-04 19:25:23',NULL,700,1,12,10,2,3),('2013-05-20 19:38:19',NULL,10,1,13,4,1,1),('2013-05-20 19:38:19',NULL,12,1,14,4,1,3),('2013-05-20 19:38:19',NULL,12,1,15,4,1,4),('2013-05-20 19:38:19',NULL,50,1,16,4,3,1),('2013-05-20 19:38:19',NULL,70,1,17,4,3,3),('2013-05-20 19:38:19',NULL,70,1,18,4,3,4),('2013-05-20 19:38:19',NULL,500,1,19,4,2,1),('2013-05-20 19:38:19',NULL,700,1,20,4,2,3),('2013-05-20 19:38:19',NULL,700,1,21,4,2,4),('2013-05-20 19:45:07',NULL,12,1,22,10,1,1),('2013-05-20 19:45:07',NULL,14,1,23,10,1,3),('2013-05-20 19:45:07',NULL,14,1,24,10,1,4),('2013-05-20 19:45:07',NULL,500,1,25,10,2,1),('2013-05-20 19:45:07',NULL,800,1,26,10,2,3),('2013-05-20 19:45:07',NULL,800,1,27,10,2,4),('2013-06-10 01:51:30',NULL,13,1,28,11,1,1),('2013-06-10 01:51:30',NULL,15,1,29,11,1,3),('2013-06-10 01:51:30',NULL,15,1,30,11,1,4),('2013-06-10 01:51:30',NULL,8,1,31,11,1,2),('2013-06-10 01:51:30',NULL,60,1,32,11,3,1),('2013-06-10 01:51:30',NULL,80,1,33,11,3,3),('2013-06-10 01:51:30',NULL,90,1,34,11,3,4),('2013-06-10 01:51:30',NULL,500,1,35,11,2,1),('2013-06-10 01:51:30',NULL,700,1,36,11,2,3),('2013-06-13 00:39:10',NULL,17,1,37,12,1,1),('2013-06-13 00:39:10',NULL,20,1,38,12,1,4),('2013-06-13 00:39:11',NULL,58,1,39,12,4,1),('2013-06-13 00:39:11',NULL,600,1,40,12,2,1),('2013-06-13 00:39:11',NULL,800,1,41,12,2,4),('2013-06-13 00:58:25',NULL,13,1,42,13,1,1),('2013-06-13 00:58:25',NULL,15,1,43,13,1,3),('2013-06-13 00:58:25',NULL,19,1,44,13,1,4),('2013-06-13 00:58:25',NULL,8,1,45,13,1,2),('2013-06-13 00:58:25',NULL,65,1,46,13,4,1),('2013-06-13 00:58:26',NULL,50,1,47,13,4,2),('2013-06-13 00:58:26',NULL,900,1,48,13,2,1),('2013-06-13 00:58:26',NULL,1000,1,49,13,2,3),('2013-06-13 01:18:31',NULL,19,1,50,14,1,1),('2013-06-13 01:18:32',NULL,25,1,51,14,1,3),('2013-06-13 01:18:32',NULL,25,1,52,14,1,4),('2013-06-13 01:18:32',NULL,10,1,53,14,1,2),('2013-06-13 01:18:32',NULL,80,1,54,14,4,1),('2013-06-13 01:18:32',NULL,89,1,55,14,4,4),('2013-06-13 01:18:32',NULL,890,1,56,14,2,1),('2013-06-13 01:18:32',NULL,1200,1,57,14,2,3),('2013-06-13 01:18:32',NULL,1200,1,58,14,2,4),('2013-06-13 01:18:32',NULL,400,1,59,14,2,2),('2013-06-13 01:31:50',NULL,12,1,60,15,1,1),('2013-06-13 01:31:51',NULL,15,1,61,15,1,3),('2013-06-13 01:31:51',NULL,18,1,62,15,1,4),('2013-06-13 01:31:51',NULL,9,1,63,15,1,2),('2013-06-13 01:31:51',NULL,78,1,64,15,4,1),('2013-06-13 01:31:51',NULL,98,1,65,15,4,3),('2013-06-13 01:31:51',NULL,100,1,66,15,4,4),('2013-06-13 01:31:51',NULL,60,1,67,15,4,2),('2013-06-13 01:31:51',NULL,700,1,68,15,2,1),('2013-06-13 01:31:51',NULL,900,1,69,15,2,3),('2013-06-13 01:31:51',NULL,900,1,70,15,2,4),('2013-06-13 01:31:51',NULL,400,1,71,15,2,2),('2013-06-13 01:41:43',NULL,8,1,72,16,1,1),('2013-06-13 01:41:43',NULL,10,1,73,16,1,3),('2013-06-13 01:41:43',NULL,12,1,74,16,1,4),('2013-06-13 01:41:43',NULL,8,1,75,16,1,2),('2013-06-13 01:41:43',NULL,78,1,76,16,4,1),('2013-06-13 01:41:43',NULL,600,1,77,16,2,1),('2013-06-13 01:41:43',NULL,800,1,78,16,2,3),('2013-06-13 01:41:43',NULL,800,1,79,16,2,4),('2013-06-13 01:41:43',NULL,300,1,80,16,2,2),('2013-06-13 01:52:59',NULL,7,1,81,17,1,1),('2013-06-13 01:52:59',NULL,9,1,82,17,1,3),('2013-06-13 01:52:59',NULL,9,1,83,17,1,4),('2013-06-13 01:52:59',NULL,4,1,84,17,1,2),('2013-06-13 01:52:59',NULL,400,1,85,17,2,1),('2013-06-13 01:52:59',NULL,500,1,86,17,2,3),('2013-06-13 01:52:59',NULL,500,1,87,17,2,4),('2013-06-13 01:52:59',NULL,200,1,88,17,2,2),('2013-06-13 19:18:54',NULL,11,1,89,18,1,1),('2013-06-13 19:18:54',NULL,11,1,90,18,1,3),('2013-06-13 19:18:54',NULL,11,1,91,18,1,4),('2013-06-13 19:18:54',NULL,11,1,92,18,1,2),('2013-06-13 19:18:54',NULL,60,1,93,18,4,1),('2013-06-13 19:18:54',NULL,70,1,94,18,4,3),('2013-06-13 19:18:54',NULL,70,1,95,18,4,4),('2013-06-13 19:18:54',NULL,40,1,96,18,4,2),('2013-06-13 19:18:54',NULL,35,1,97,18,3,1),('2013-06-13 19:18:54',NULL,45,1,98,18,3,3),('2013-06-13 19:18:55',NULL,45,1,99,18,3,4),('2013-06-13 19:18:55',NULL,45,1,100,18,3,2),('2013-06-13 19:18:55',NULL,550,1,101,18,2,1),('2013-06-13 19:18:55',NULL,680,1,102,18,2,3),('2013-06-13 19:18:55',NULL,700,1,103,18,2,4),('2013-06-13 19:18:55',NULL,750,1,104,18,2,2);
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
  CONSTRAINT `FK_favorito_cliente` FOREIGN KEY (`clienteID`) REFERENCES `cliente` (`clienteID`),
  CONSTRAINT `FK_favorito_playa` FOREIGN KEY (`playaID`) REFERENCES `playa` (`playaID`)
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
  CONSTRAINT `FK_detalle_estadia_empleado` FOREIGN KEY (`empleadoID`) REFERENCES `empleado` (`empleadoID`),
  CONSTRAINT `FK_detalle_estadia_estadia` FOREIGN KEY (`estadiaID`) REFERENCES `estadia` (`estadiaID`),
  CONSTRAINT `FK_detalle_estadia_promocion` FOREIGN KEY (`promocionID`) REFERENCES `promocion` (`promocionID`),
  CONSTRAINT `FK_detalle_estadia_tarifa` FOREIGN KEY (`tarifaID`) REFERENCES `tarifa` (`tarifaID`),
  CONSTRAINT `FK_detalle_estadia_transaccion_cliente` FOREIGN KEY (`transaccionClienteID`) REFERENCES `transaccion_cliente` (`transaccionClienteID`),
  CONSTRAINT `FK_detalle_estadia_vehiculo` FOREIGN KEY (`vehiculoID`) REFERENCES `vehiculo` (`vehiculoID`)
) ENGINE=InnoDB AUTO_INCREMENT=172 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detalle_estadia`
--

LOCK TABLES `detalle_estadia` WRITE;
/*!40000 ALTER TABLE `detalle_estadia` DISABLE KEYS */;
INSERT INTO `detalle_estadia` VALUES (2,1002,'2012-08-04 17:48:45','2012-08-04 19:25:23',20,1,NULL,1,1,1,3),(2,1002,'2012-08-11 17:50:35','2012-08-11 18:45:21',10,1,NULL,1,1,2,3),(2,1002,'2012-08-18 17:51:18','2012-08-18 18:32:49',10,1,NULL,1,1,3,3),(5,1001,'2013-06-15 18:00:00','2013-06-15 18:39:42',10,1,NULL,1,1,5,3),(6,1001,'2013-06-16 18:00:00','2013-06-16 18:40:01',10,1,NULL,1,1,6,3),(7,1001,'2013-06-20 19:00:00','2013-06-20 19:45:52',12,1,NULL,3,4,7,3),(8,1001,'2013-04-05 20:00:00','2013-04-05 20:08:58',12,1,NULL,3,4,8,3),(9,1001,'2013-04-10 20:00:00','2013-04-10 20:09:08',12,1,NULL,3,4,9,3),(10,1001,'2013-04-12 20:00:00','2013-04-12 20:09:16',12,1,NULL,3,4,10,3),(11,1001,'2013-04-15 20:00:00','2013-04-15 20:09:23',12,1,NULL,3,4,11,3),(12,1001,'2013-04-22 20:00:00','2013-04-22 20:09:30',10,1,NULL,1,1,12,3),(13,1001,'2013-05-02 20:00:00','2013-05-02 20:09:46',10,1,NULL,1,1,13,3),(14,1001,'2013-05-06 20:00:00','2013-05-06 20:10:02',12,1,NULL,3,4,14,3),(15,1001,'2013-05-10 20:00:00','2013-05-10 20:10:10',12,1,NULL,3,4,15,3),(16,1001,'2013-05-11 20:00:00','2013-05-11 20:10:17',12,1,NULL,3,4,16,3),(17,1001,'2013-05-17 20:00:00','2013-05-17 20:10:24',12,1,NULL,3,4,17,3),(18,1001,'2013-05-26 20:00:00','2013-05-26 20:10:31',12,1,NULL,3,4,18,3),(19,1007,'2013-06-17 18:00:00','2013-06-17 18:39:42',10,1,NULL,13,1,19,4),(20,1007,'2013-06-18 18:00:00','2013-06-18 18:40:01',10,1,NULL,13,1,20,4),(21,1007,'2013-06-21 19:00:00','2013-06-21 19:45:52',12,1,NULL,14,4,21,4),(22,1007,'2013-04-06 20:00:00','2013-04-06 20:08:58',12,1,NULL,14,4,22,4),(23,1007,'2013-04-11 20:00:00','2013-04-11 20:09:08',12,1,NULL,14,4,23,4),(24,1007,'2013-04-13 20:00:00','2013-04-13 20:09:16',12,1,NULL,14,4,24,4),(25,1007,'2013-04-16 20:00:00','2013-04-16 20:09:23',12,1,NULL,14,4,25,4),(26,1007,'2013-04-23 20:00:00','2013-04-23 20:09:30',10,1,NULL,13,1,26,4),(27,1007,'2013-05-03 20:00:00','2013-05-03 20:09:46',10,1,NULL,13,1,27,4),(28,1007,'2013-05-07 20:00:00','2013-05-07 20:10:02',12,1,NULL,14,4,28,4),(29,1007,'2013-05-12 20:00:00','2013-05-12 20:10:10',12,1,NULL,14,4,29,4),(30,1007,'2013-05-13 20:00:00','2013-05-13 20:10:17',12,1,NULL,14,4,30,4),(31,1007,'2013-05-18 20:00:00','2013-05-18 20:10:24',12,1,NULL,14,4,31,4),(32,1007,'2013-05-27 20:00:00','2013-05-27 20:10:31',12,1,NULL,14,4,32,4),(33,1008,'2013-06-19 18:00:00','2013-06-19 18:39:42',12,1,NULL,22,1,33,10),(34,1008,'2013-06-20 18:00:00','2013-06-20 18:40:01',12,1,NULL,22,1,34,10),(35,1008,'2013-06-22 19:00:00','2013-06-22 19:45:52',14,1,NULL,23,4,35,10),(36,1008,'2013-04-07 20:00:00','2013-04-07 20:08:58',14,1,NULL,23,4,36,10),(37,1008,'2013-04-12 20:00:00','2013-04-12 20:09:08',14,1,NULL,23,4,37,10),(38,1008,'2013-04-15 20:00:00','2013-04-15 20:09:16',14,1,NULL,23,4,38,10),(39,1008,'2013-04-17 20:00:00','2013-04-17 20:09:23',14,1,NULL,23,4,39,10),(40,1008,'2013-04-24 20:00:00','2013-04-24 20:09:30',12,1,NULL,22,1,40,10),(41,1008,'2013-05-04 20:00:00','2013-05-04 20:09:46',12,1,NULL,22,1,41,10),(42,1008,'2013-05-08 20:00:00','2013-05-07 20:10:02',14,1,NULL,23,4,42,10),(43,1008,'2013-05-14 20:00:00','2013-05-14 20:10:10',14,1,NULL,23,4,43,10),(44,1008,'2013-05-15 20:00:00','2013-05-15 20:10:17',14,1,NULL,23,4,44,10),(45,1008,'2013-05-19 20:00:00','2013-05-19 20:10:24',14,1,NULL,23,4,45,10),(46,1008,'2013-05-28 20:00:00','2013-05-28 20:10:31',14,1,NULL,23,4,46,10),(51,1010,'2013-01-11 02:11:33','2013-01-11 02:11:25',17,1,NULL,37,1,47,12),(52,1010,'2013-01-11 02:11:57','2013-01-11 02:11:50',17,1,NULL,37,2,48,12),(62,1010,'2013-06-13 20:28:55','2013-06-13 20:23:25',17,1,NULL,37,5,49,12),(65,1010,'2013-06-13 20:29:44','2013-06-13 20:23:36',17,1,NULL,37,6,50,12),(64,1010,'2013-06-13 20:29:35','2013-06-13 20:24:00',17,1,NULL,37,8,51,12),(63,1010,'2013-06-13 20:29:11','2013-06-13 20:24:13',17,1,NULL,37,9,52,12),(67,1010,'2013-06-13 20:30:27','2013-06-13 20:28:38',17,1,NULL,37,13,53,12),(66,1010,'2013-06-13 20:29:57','2013-06-13 20:28:49',17,1,NULL,37,11,54,12),(68,1010,'2013-01-07 21:47:52','2013-01-07 21:47:47',17,1,NULL,37,5,55,12),(69,1010,'2013-01-07 21:48:10','2013-01-07 21:48:04',17,1,NULL,37,6,56,12),(70,1010,'2013-01-07 21:48:28','2013-01-07 21:48:21',17,1,NULL,37,8,57,12),(71,1010,'2013-01-07 21:48:54','2013-01-07 21:48:46',17,1,NULL,37,13,58,12),(72,1011,'2013-01-04 21:52:12','2013-01-04 21:52:07',13,1,NULL,42,5,59,13),(73,1011,'2013-01-04 21:53:00','2013-01-04 21:52:22',13,1,NULL,42,13,60,13),(83,1011,'2013-06-13 22:01:36','2013-01-04 21:53:13',1166,1,NULL,42,6,61,13),(74,1011,'2013-01-04 21:55:51','2013-01-04 21:55:45',13,1,NULL,42,8,62,13),(76,1011,'2013-01-04 21:56:42','2013-01-04 21:56:15',13,1,NULL,42,11,63,13),(75,1011,'2013-01-04 21:56:36','2013-01-04 21:56:29',13,1,NULL,42,9,64,13),(77,1012,'2013-01-01 21:58:54','2013-01-01 21:58:50',19,1,NULL,50,5,65,14),(78,1012,'2013-01-01 21:59:08','2013-01-01 21:59:03',19,1,NULL,50,13,66,14),(79,1012,'2013-01-01 21:59:24','2013-01-01 21:59:20',19,1,NULL,50,8,67,14),(80,1012,'2013-01-01 21:59:37','2013-01-01 21:59:33',19,1,NULL,50,11,68,14),(81,1012,'2013-06-13 22:01:14','2013-06-13 22:01:04',19,1,NULL,50,5,69,14),(82,1012,'2013-06-13 22:01:19','2013-06-13 22:01:10',19,1,NULL,50,13,70,14),(92,1012,'2013-06-13 22:16:25','2013-06-13 22:01:26',19,1,NULL,50,8,71,14),(168,1012,'2013-06-15 16:08:43','2013-06-13 22:01:32',798,1,NULL,50,9,72,14),(85,1012,'2013-06-13 22:02:05','2013-06-13 22:02:00',19,1,NULL,50,11,73,14),(86,1012,'2013-01-16 22:04:36','2013-01-16 22:04:32',19,1,NULL,50,5,74,14),(87,1012,'2013-01-16 22:05:06','2013-01-16 22:05:01',19,1,NULL,50,13,75,14),(88,1012,'2013-01-16 22:07:33','2013-01-16 22:07:25',19,1,NULL,50,11,76,14),(89,1012,'2013-01-16 22:09:06','2013-01-16 22:08:25',19,1,NULL,50,5,77,14),(90,1012,'2013-01-17 22:09:22','2013-01-16 22:09:22',80,1,NULL,54,5,78,14),(91,1012,'2013-01-16 22:10:08','2013-01-16 22:10:01',19,1,NULL,50,11,79,14),(93,1013,'2013-06-13 23:07:31','2013-06-13 23:07:27',12,1,NULL,60,5,80,15),(95,1013,'2013-06-13 23:15:44','2013-06-13 23:07:38',12,1,NULL,60,13,81,15),(96,1013,'2013-01-12 23:17:05','2013-01-11 23:09:07',288,1,NULL,60,5,82,15),(101,1013,'2013-01-12 23:23:30','2013-01-11 23:09:19',300,1,NULL,60,11,83,15),(94,1013,'2013-01-11 23:09:50','2013-01-11 23:09:44',12,1,NULL,60,8,84,15),(97,1013,'2013-01-12 23:17:21','2013-01-12 23:17:18',12,1,NULL,60,13,85,15),(103,1013,'2013-01-12 23:27:45','2013-01-12 23:17:37',0,1,NULL,60,6,86,15),(98,1015,'2013-01-12 23:23:07','2013-01-12 23:22:52',7,1,NULL,81,5,87,17),(99,1015,'2013-01-12 23:23:19','2013-01-12 23:23:02',7,1,NULL,81,8,88,17),(100,1015,'2013-01-12 23:23:24','2013-01-12 23:23:15',7,1,NULL,81,13,89,17),(185,1015,'2013-01-13 17:08:05','2013-01-12 23:25:53',126,1,NULL,81,11,90,17),(102,1015,'2013-01-12 23:26:24','2013-01-12 23:26:14',7,1,NULL,81,13,91,17),(106,1014,'2013-01-12 23:28:03','2013-01-12 23:27:21',8,1,NULL,72,5,92,16),(105,1014,'2013-01-12 23:27:57','2013-01-12 23:27:34',8,1,NULL,72,13,93,16),(107,1016,'2013-06-13 23:57:22','2013-06-13 23:57:18',11,1,NULL,89,5,94,18),(108,1016,'2013-06-13 23:57:36','2013-06-13 23:57:31',11,1,NULL,89,13,95,18),(109,1016,'2013-06-13 23:57:49','2013-06-13 23:57:44',11,1,NULL,89,8,96,18),(110,1016,'2013-01-07 23:59:06','2013-01-07 23:59:01',11,1,NULL,89,5,97,18),(111,1016,'2013-01-07 23:59:19','2013-01-07 23:59:14',11,1,NULL,89,13,98,18),(112,1016,'2013-01-07 23:59:31','2013-01-07 23:59:26',11,1,NULL,89,8,99,18),(113,1016,'2013-01-08 00:00:19','2013-01-08 00:00:13',11,1,NULL,89,13,100,18),(114,1016,'2013-01-08 00:00:36','2013-01-08 00:00:29',11,1,NULL,89,5,101,18),(115,1016,'2013-01-08 00:00:53','2013-01-08 00:00:46',11,1,NULL,89,8,102,18),(117,1016,'2013-01-20 20:32:22','2013-01-20 20:31:46',11,1,NULL,89,5,103,18),(118,1016,'2013-01-20 20:32:42','2013-01-20 20:32:38',11,1,NULL,89,8,104,18),(119,1016,'2013-01-20 20:33:05','2013-01-20 20:32:58',11,1,NULL,90,7,105,18),(120,1016,'2013-01-20 20:34:28','2013-01-20 20:34:20',11,1,NULL,89,8,106,18),(121,1016,'2013-01-20 20:34:51','2013-01-20 20:34:45',11,1,NULL,90,7,107,18),(123,1016,'2013-02-18 20:39:27','2013-02-18 20:39:04',11,1,NULL,89,8,108,18),(122,1016,'2013-02-18 20:39:23','2013-02-18 20:39:12',11,1,NULL,90,7,109,18),(124,1016,'2013-02-18 20:39:47','2013-02-18 20:39:19',11,1,NULL,89,5,110,18),(125,1016,'2013-02-18 20:51:54','2013-02-18 20:51:47',11,1,NULL,90,7,111,18),(126,1016,'2013-02-18 20:53:14','2013-02-18 20:53:00',11,1,NULL,89,13,112,18),(127,1013,'2013-02-18 20:54:10','2013-02-18 20:53:48',15,1,NULL,61,7,113,15),(128,1013,'2013-02-18 20:54:15','2013-02-18 20:53:57',12,1,NULL,60,5,114,15),(129,1013,'2013-02-18 20:54:21','2013-02-18 20:54:05',12,1,NULL,60,13,115,15),(130,1013,'2013-02-18 20:54:40','2013-02-18 20:54:32',12,1,NULL,60,8,116,15),(131,1013,'2013-02-18 20:55:02','2013-02-18 20:54:49',12,1,NULL,60,5,117,15),(133,1013,'2013-02-18 20:55:19','2013-02-18 20:54:58',12,1,NULL,60,13,118,15),(132,1013,'2013-02-18 20:55:13','2013-02-18 20:55:09',15,1,NULL,61,7,119,15),(134,1012,'2013-02-18 20:57:28','2013-02-18 20:57:20',25,1,NULL,51,7,120,14),(135,1012,'2013-02-18 20:57:44','2013-02-18 20:57:38',19,1,NULL,50,8,121,14),(136,1012,'2013-02-18 20:57:56','2013-02-18 20:57:52',19,1,NULL,50,13,122,14),(138,1012,'2013-02-18 20:58:30','2013-02-18 20:58:05',19,1,NULL,50,8,123,14),(139,1012,'2013-02-18 20:58:35','2013-02-18 20:58:13',19,1,NULL,50,5,124,14),(137,1012,'2013-02-18 20:58:25','2013-02-18 20:58:20',25,1,NULL,51,7,125,14),(140,1012,'2013-02-18 20:58:53','2013-02-18 20:58:49',19,1,NULL,50,13,126,14),(141,1011,'2013-02-18 20:59:27','2013-02-18 20:59:22',13,1,NULL,42,8,127,13),(142,1010,'2013-02-18 21:05:55','2013-02-18 21:05:50',17,1,NULL,37,8,128,12),(143,1010,'2013-02-18 21:06:07','2013-02-18 21:06:03',17,1,NULL,37,5,129,12),(144,1010,'2013-02-18 21:06:25','2013-02-18 21:06:19',17,1,NULL,37,13,130,12),(145,1010,'2013-02-18 21:06:53','2013-02-18 21:06:32',17,1,NULL,37,8,131,12),(146,1010,'2013-02-18 21:07:13','2013-02-18 21:06:42',17,1,NULL,37,13,132,12),(147,1010,'2013-02-18 21:07:26','2013-02-18 21:07:22',17,1,NULL,37,8,133,12),(148,1010,'2013-03-18 23:30:36','2013-03-18 23:30:02',17,1,NULL,37,8,134,12),(149,1010,'2013-03-18 23:30:43','2013-03-18 23:30:09',17,1,NULL,37,13,135,12),(150,1010,'2013-03-18 23:31:03','2013-03-18 23:30:59',17,1,NULL,37,8,136,12),(152,1010,'2013-03-18 23:34:50','2013-03-18 23:33:09',17,1,NULL,37,5,137,12),(184,1010,'2013-03-19 16:55:29','2013-03-18 23:33:16',306,1,NULL,37,8,138,12),(153,1010,'2013-03-18 23:35:14','2013-03-18 23:33:30',17,1,NULL,37,13,139,12),(151,1010,'2013-03-18 23:34:35','2013-03-18 23:34:22',17,1,NULL,37,6,140,12),(154,1010,'2013-03-18 23:35:39','2013-03-18 23:35:34',17,1,NULL,37,5,141,12),(155,1016,'2013-03-24 15:00:23','2013-03-24 15:00:07',11,1,NULL,89,5,142,18),(156,1016,'2013-03-24 15:00:36','2013-03-24 15:00:31',11,1,NULL,89,6,143,18),(186,1016,'2013-03-24 17:50:29','2013-03-24 15:01:16',33,1,NULL,90,7,144,18),(157,1016,'2013-03-24 15:02:03','2013-03-24 15:01:58',11,1,NULL,89,13,145,18),(158,1016,'2013-06-15 15:37:38','2013-06-15 15:37:33',11,1,NULL,89,6,146,18),(159,1016,'2013-06-16 15:37:51','2013-06-15 15:37:51',60,1,NULL,93,6,147,18),(167,1016,'2013-06-15 16:08:22','2013-06-15 15:41:48',11,1,NULL,89,6,148,18),(160,1016,'2013-06-15 15:43:29','2013-06-15 15:43:24',11,1,NULL,89,5,149,18),(162,1016,'2013-06-15 15:44:18','2013-06-15 15:43:46',11,1,NULL,89,5,150,18),(161,1016,'2013-06-15 15:44:12','2013-06-15 15:44:05',11,1,NULL,89,13,151,18),(163,1014,'2013-06-15 15:48:37','2013-06-15 15:48:28',8,1,NULL,72,5,152,16),(164,1014,'2013-04-05 15:54:20','2013-04-05 15:54:12',8,1,NULL,72,13,153,16),(165,1014,'2013-04-06 15:54:41','2013-04-05 15:54:41',78,1,NULL,76,13,154,16),(166,1014,'2013-04-05 15:55:26','2013-04-05 15:55:21',8,1,NULL,72,5,155,16),(169,1016,'2013-06-15 16:11:20','2013-06-15 16:09:43',11,1,NULL,89,6,156,18),(170,1016,'2013-06-15 16:12:14','2013-06-15 16:12:07',11,1,NULL,89,6,157,18),(171,1016,'2013-06-15 16:13:19','2013-06-15 16:13:14',11,1,NULL,89,5,158,18),(172,1016,'2013-05-09 16:20:08','2013-05-09 16:20:04',11,1,NULL,89,6,159,18),(177,1016,'2013-05-09 16:35:03','2013-05-09 16:20:16',11,1,NULL,89,5,160,18),(181,1016,'2013-05-09 16:37:22','2013-05-09 16:20:30',11,1,NULL,89,13,161,18),(173,1016,'2013-05-09 16:21:01','2013-05-09 16:20:53',11,1,NULL,89,9,162,18),(174,1016,'2013-05-10 16:21:38','2013-05-09 16:21:38',60,1,NULL,93,9,163,18),(175,1016,'2013-05-10 16:22:17','2013-05-09 16:22:17',60,1,NULL,93,6,164,18),(176,1010,'2013-05-09 16:34:06','2013-05-09 16:34:02',17,1,NULL,37,6,165,12),(178,1010,'2013-05-10 16:35:13','2013-05-09 16:35:13',58,1,NULL,39,9,166,12),(179,1010,'2013-05-09 16:36:53','2013-05-09 16:36:46',17,1,NULL,37,6,167,12),(180,1010,'2013-05-09 16:37:06','2013-05-09 16:37:01',17,1,NULL,37,5,168,12),(182,1016,'2013-03-18 16:47:48','2013-03-18 16:47:44',11,1,NULL,89,6,169,18),(NULL,1016,NULL,'2013-03-18 16:47:57',0,0,NULL,93,5,170,18),(183,1016,'2013-03-18 16:48:12','2013-03-18 16:48:06',11,1,NULL,89,13,171,18);
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
  CONSTRAINT `FK_modelo_vehiculo_categoria_vehiculo` FOREIGN KEY (`categoriaID`) REFERENCES `categoria_vehiculo` (`categoriaVehiculoID`),
  CONSTRAINT `FK_modelo_vehiculo_marca_vehiculo` FOREIGN KEY (`marcaVehiculoID`) REFERENCES `marca_vehiculo` (`marcaVehiculoID`)
) ENGINE=InnoDB AUTO_INCREMENT=215 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `modelo_vehiculo`
--

LOCK TABLES `modelo_vehiculo` WRITE;
/*!40000 ALTER TABLE `modelo_vehiculo` DISABLE KEYS */;
INSERT INTO `modelo_vehiculo` VALUES (NULL,'Clio',1,1,1),(NULL,'Sandero',2,1,1),(NULL,'Logan',3,1,1),(NULL,'Symbol',4,1,1),(NULL,'Fluence',5,1,1),(NULL,'Mégane',6,1,1),(NULL,'Latitude',7,1,1),(NULL,'Kangoo',8,1,3),(NULL,'Master Furgón',9,1,3),(NULL,'Master Minibus',10,1,3),(NULL,'Duster',11,1,4),(NULL,'Koleos',12,1,4),(NULL,'Scénic',13,1,1),(NULL,'Laguna',14,1,1),(NULL,'Twingo',15,1,1),(NULL,'Ka',16,2,1),(NULL,'Fiesta',17,2,1),(NULL,'Focus',18,2,1),(NULL,'Mondeo',19,2,1),(NULL,'S-Max',20,2,1),(NULL,'Falcon',21,2,1),(NULL,'Fairlane',22,2,1),(NULL,'Taunus',23,2,1),(NULL,'Taurus',24,2,1),(NULL,'Torino',25,2,1),(NULL,'Escort',26,2,1),(NULL,'C-Max',27,2,1),(NULL,'Galaxi',28,2,1),(NULL,'Mustang',29,2,1),(NULL,'Escape',30,2,1),(NULL,'Kuga',31,2,4),(NULL,'EcoSport',32,2,4),(NULL,'Ranger',33,2,4),(NULL,'Transit Furgon',34,2,3),(NULL,'Transit Minibus',35,2,3),(NULL,'F100',36,2,4),(NULL,'F150',37,2,4),(NULL,'F250',38,2,4),(NULL,'F350',39,2,4),(NULL,'F450',40,2,4),(NULL,'F650',41,2,4),(NULL,'Gol',42,3,1),(NULL,'Fox',43,3,1),(NULL,'Crossfox',44,3,1),(NULL,'Golf GTI',45,3,1),(NULL,'Voyage',46,3,1),(NULL,'Bora',47,3,1),(NULL,'Vento',48,3,1),(NULL,'Passat',49,3,1),(NULL,'Scirocco',50,3,1),(NULL,'CC',51,3,1),(NULL,'Tiguan',52,3,4),(NULL,'Touareg',53,3,4),(NULL,'Gol Country',54,3,1),(NULL,'Suran',55,3,1),(NULL,'Vento Variant',56,3,1),(NULL,'Sharan',57,3,1),(NULL,'Passat Variant',58,3,1),(NULL,'Saveiro',59,3,4),(NULL,'Amarok',60,3,4),(NULL,'Clase C Sedán',61,4,1),(NULL,'Clase E Sedán',62,4,1),(NULL,'Clase S Sedán',63,4,1),(NULL,'Clase C Coupé',64,4,1),(NULL,'Clase E Coupé',65,4,1),(NULL,'SLS AMG',66,4,1),(NULL,'Clase GLK',67,4,4),(NULL,'Clase SLK',68,4,1),(NULL,'Clase M',69,4,4),(NULL,'207',70,5,1),(NULL,'308',71,5,1),(NULL,'3008',72,5,1),(NULL,'5008',73,5,1),(NULL,'RCZ',74,5,1),(NULL,'408',75,5,1),(NULL,'Partner Furgón',76,5,3),(NULL,'Partner Patagónica',77,5,3),(NULL,'Hoggar',78,5,4),(NULL,'C3',79,6,1),(NULL,'C2',80,6,1),(NULL,'C1',81,6,1),(NULL,'Xsara',82,6,1),(NULL,'C4',83,6,1),(NULL,'C6',84,6,1),(NULL,'DS3',85,6,1),(NULL,'DS4',86,6,1),(NULL,'DS9',87,6,1),(NULL,'Berlingo',88,6,3),(NULL,'C5',89,6,1),(NULL,'C1',90,6,1),(NULL,'Serie 1',91,7,1),(NULL,'Serie 3',92,7,1),(NULL,'Serie 5',93,7,1),(NULL,'Serie 6',94,7,1),(NULL,'Serie 7',95,7,1),(NULL,'X1',96,7,4),(NULL,'X3',97,7,4),(NULL,'X5',98,7,4),(NULL,'X6',99,7,4),(NULL,'Z4',100,7,1),(NULL,'M3',101,7,1),(NULL,'A1',102,8,1),(NULL,'A3',103,8,1),(NULL,'A4',104,8,1),(NULL,'A5',105,8,1),(NULL,'A6',106,8,1),(NULL,'A7',107,8,1),(NULL,'A8',108,8,1),(NULL,'Q5',109,8,4),(NULL,'Q7',110,8,4),(NULL,'TT',111,8,1),(NULL,'R8',112,8,1),(NULL,'Marbella',113,9,1),(NULL,'Ibiza',114,9,1),(NULL,'Córdoba',115,9,1),(NULL,'León',116,9,1),(NULL,'Alhambra',117,9,4),(NULL,'K-180',118,10,1),(NULL,'Linea',119,11,1),(NULL,'Palio',120,11,1),(NULL,'Uno',121,11,1),(NULL,'Punto',122,11,1),(NULL,'Siena',123,11,1),(NULL,'500',124,11,1),(NULL,'Gama Strada',125,11,4),(NULL,'Idea',126,11,1),(NULL,'Palio Weekend',127,11,1),(NULL,'Qubo',128,11,3),(NULL,'Fiorino',129,11,3),(NULL,'Ducato',130,11,3),(NULL,'March',131,12,1),(NULL,'Tiida',132,12,1),(NULL,'Sentra',133,12,1),(NULL,'370Z',134,12,1),(NULL,'NP300',135,12,4),(NULL,'Frontier',136,12,4),(NULL,'X-Trail',137,12,4),(NULL,'Murano',138,12,4),(NULL,'Corolla',139,13,1),(NULL,'Camry',140,13,1),(NULL,'Prius',141,13,1),(NULL,'Rav4',142,13,4),(NULL,'SW4',143,13,4),(NULL,'Land Cruiser Prado',144,13,4),(NULL,'Land Cruise 200',145,13,4),(NULL,'Hilux',146,13,4),(NULL,'i10',147,14,1),(NULL,'i30',148,14,1),(NULL,'Genesis',149,14,1),(NULL,'Veloster',150,14,1),(NULL,'Tucson',151,14,4),(NULL,'Santa Fe',152,14,4),(NULL,'H1 Minibus',153,14,3),(NULL,'C30',154,15,1),(NULL,'S40',155,15,1),(NULL,'S80',156,15,1),(NULL,'V50',157,15,1),(NULL,'XC60',158,15,4),(NULL,'XC70',159,15,1),(NULL,'XC90',160,15,1),(NULL,'Picanto',161,16,1),(NULL,'Cerato Force',162,16,1),(NULL,'Soul',163,16,1),(NULL,'Sportage',164,16,4),(NULL,'Sorento',165,16,4),(NULL,'Lancer',166,17,1),(NULL,'Outlander',167,17,1),(NULL,'L200',168,17,4),(NULL,'Montero',169,17,4),(NULL,'Defender',170,18,4),(NULL,'Freelander',171,18,4),(NULL,'Range Rover',172,18,4),(NULL,'Discovery',173,18,4),(NULL,'Fun',174,19,1),(NULL,'Vitara',175,19,4),(NULL,'Swift',176,19,1),(NULL,'Boxster',177,20,1),(NULL,'Cayman',178,20,1),(NULL,'911',179,20,1),(NULL,'Panamera',180,20,1),(NULL,'Cayenne',181,20,4),(NULL,'Celta',182,21,1),(NULL,'Classic',183,21,1),(NULL,'Prisma',184,21,1),(NULL,'Agile',185,21,1),(NULL,'Aveo',186,21,1),(NULL,'Spatk',187,21,1),(NULL,'Cruze',188,21,1),(NULL,'Sonic',189,21,1),(NULL,'Meriva',190,21,1),(NULL,'Zafira',191,21,1),(NULL,'Montana',192,21,4),(NULL,'S10',193,21,4),(NULL,'Captiva',194,21,4),(NULL,'Cooper',195,22,1),(NULL,'Giulietta',196,23,1),(NULL,'MiTo',197,23,1),(NULL,'159',198,23,1),(NULL,'8C',199,23,1),(NULL,'4C',200,23,1),(NULL,'Civic',201,24,1),(NULL,'CR-V',202,24,4),(NULL,'City',203,24,1),(NULL,'S2000',204,24,1),(NULL,'Cherooke',205,25,4),(NULL,'Rangler',206,25,4),(NULL,'Armado',207,25,4),(NULL,'300C',208,26,1),(NULL,'PT Cruiser',209,26,1),(NULL,'Neon',210,26,1),(NULL,'Speed 6',211,27,1),(NULL,'RX-8',212,27,1),(NULL,'RX-7',213,27,1),(NULL,'l2000',214,27,1),(NULL,'Otro/Auto',215,28,1),(NULL,'Otro/Utilitario',216,28,3),(NULL,'Otro/Moto',217,28,2),(NULL,'Otro/Pick-up',218,28,4);
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
  CONSTRAINT `FK_empleado_cargo_empleado` FOREIGN KEY (`cargoEmpleadoID`) REFERENCES `cargo_empleado` (`cargoEmpleadoID`),
  CONSTRAINT `FK_empleado_usuario` FOREIGN KEY (`usuarioID`) REFERENCES `usuario` (`usuarioID`)
) ENGINE=InnoDB AUTO_INCREMENT=1017 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empleado`
--

LOCK TABLES `empleado` WRITE;
/*!40000 ALTER TABLE `empleado` DISABLE KEYS */;
INSERT INTO `empleado` VALUES (1,1001,1001,1),(2,1002,1002,5),(1,1003,1003,10),(1,1001,1007,11),(1,1005,1008,12),(1,1001,1009,13),(1,1001,1010,14),(1,1001,1011,15),(1,1001,1012,16),(1,1001,1013,17),(1,1001,1014,18),(1,1001,1015,19),(1,1001,1016,20);
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
  CONSTRAINT `FK_denuncia_vehiculo_estado` FOREIGN KEY (`estadoID`) REFERENCES `estado_denuncia` (`estadoDenunciaID`),
  CONSTRAINT `FK_denuncia_vehiculo_playa` FOREIGN KEY (`playaID`) REFERENCES `playa` (`playaID`),
  CONSTRAINT `FK_denuncia_vehiculo_vehiculo` FOREIGN KEY (`vehiculoID`) REFERENCES `vehiculo` (`vehiculoID`)
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
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COMMENT='Cada instancia de esta clase representa un conjunto de ingreso y egreso de los autos en cada playa.   ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estadia`
--

LOCK TABLES `estadia` WRITE;
/*!40000 ALTER TABLE `estadia` DISABLE KEYS */;
INSERT INTO `estadia` VALUES (3,3),(4,4),(5,5),(6,6),(7,7),(8,8),(9,9),(10,10),(11,11),(12,12),(13,13),(14,14),(15,15),(16,16),(17,17),(18,18);
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
  CONSTRAINT `FK_transaccion_playa_cuenta_playa` FOREIGN KEY (`cuentaPlayaID`) REFERENCES `cuenta_playa` (`cuentaPlayaID`),
  CONSTRAINT `FK_transaccion_playa_detalle_estadia` FOREIGN KEY (`detalleEstadiaID`) REFERENCES `detalle_estadia` (`detalleEstadiaID`),
  CONSTRAINT `FK_transaccion_playa_liquidacion` FOREIGN KEY (`liquidacionID`) REFERENCES `liquidacion` (`liquidacionID`),
  CONSTRAINT `FK_transaccion_playa_tipo_pago` FOREIGN KEY (`tipoPagoID`) REFERENCES `tipo_pago` (`tipoPagoID`)
) ENGINE=InnoDB AUTO_INCREMENT=197 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaccion_playa`
--

LOCK TABLES `transaccion_playa` WRITE;
/*!40000 ALTER TABLE `transaccion_playa` DISABLE KEYS */;
INSERT INTO `transaccion_playa` VALUES ('2012-08-04 19:25:23',20,4,1,1,NULL,1),('2012-08-11 18:45:21',10,4,2,1,NULL,2),('2012-08-18 18:32:49',10,4,3,1,NULL,3),('2013-06-15 18:39:42',10,6,5,1,NULL,5),('2013-06-16 18:40:01',10,6,6,1,NULL,6),('2013-06-20 19:45:52',12,6,7,1,NULL,7),('2013-04-05 20:08:58',12,6,8,1,16,8),('2013-04-10 20:09:08',12,6,9,1,16,9),('2013-04-12 20:09:16',12,6,10,1,16,10),('2013-04-15 20:09:23',12,6,11,1,16,11),('2013-04-22 20:09:30',10,6,12,1,16,12),('2013-05-02 20:09:46',10,6,13,1,22,13),('2013-05-06 20:10:02',12,6,14,1,22,14),('2013-05-10 20:10:10',12,6,15,1,22,15),('2013-05-11 20:10:17',12,6,16,1,22,16),('2013-05-17 20:10:24',12,6,17,1,22,17),('2013-05-26 20:10:31',12,6,18,1,22,18),('2013-06-17 18:39:42',10,6,19,2,NULL,19),('2013-06-18 18:40:01',10,6,20,2,NULL,20),('2013-06-21 19:45:52',12,6,21,2,NULL,21),('2013-04-06 20:08:58',12,6,22,2,17,22),('2013-04-11 20:09:08',12,6,23,2,17,23),('2013-04-13 20:09:16',12,6,24,2,17,24),('2013-04-16 20:09:23',12,6,25,2,17,25),('2013-04-23 20:09:30',10,6,26,2,17,26),('2013-05-03 20:09:46',10,6,27,2,23,27),('2013-05-07 20:10:02',12,6,28,2,23,28),('2013-05-12 20:10:10',12,6,29,2,23,29),('2013-05-13 20:10:17',12,6,30,2,23,30),('2013-05-18 20:10:24',12,6,31,2,23,31),('2013-05-27 20:10:31',12,6,32,2,23,32),('2013-06-19 18:39:42',12,6,33,8,NULL,33),('2013-06-20 18:40:01',12,6,34,8,NULL,34),('2013-06-22 19:45:52',14,6,35,8,NULL,35),('2013-04-07 20:08:58',14,6,36,8,18,36),('2013-04-12 20:09:08',14,6,37,8,18,37),('2013-04-15 20:09:16',14,6,38,8,18,38),('2013-04-17 20:09:23',14,6,39,8,18,39),('2013-04-24 20:09:30',12,6,40,8,18,40),('2013-05-04 20:09:46',12,6,41,8,24,41),('2013-05-08 20:10:02',14,6,42,8,24,42),('2013-05-14 20:10:10',14,6,43,8,24,43),('2013-05-15 20:10:17',14,6,44,8,24,44),('2013-05-19 20:10:24',14,6,45,8,24,45),('2013-05-28 20:10:31',14,6,46,8,24,46),('2013-01-11 02:11:25',17,6,47,9,1,47),('2013-01-11 02:11:50',17,6,48,9,1,48),('2013-06-13 20:23:25',17,6,49,9,NULL,49),('2013-06-13 20:24:13',17,6,50,9,NULL,52),('2013-06-13 20:24:00',17,6,51,9,NULL,51),('2013-06-13 20:23:36',17,6,52,9,NULL,50),('2013-06-13 20:28:49',17,6,53,9,NULL,54),('2013-06-13 20:28:38',17,6,54,9,NULL,53),('2013-01-07 21:47:47',17,6,55,9,1,55),('2013-01-07 21:48:04',17,6,56,9,1,56),('2013-01-07 21:48:21',17,6,57,9,1,57),('2013-01-07 21:48:46',17,6,58,9,1,58),('2013-01-04 21:52:07',13,6,59,10,2,59),('2013-01-04 21:52:22',13,6,60,10,2,60),('2013-01-04 21:55:45',13,6,61,10,2,62),('2013-01-04 21:56:29',13,6,62,10,2,64),('2013-01-04 21:56:15',13,6,63,10,2,63),('2013-01-01 21:58:50',19,6,64,11,3,65),('2013-01-01 21:59:03',19,6,65,11,3,66),('2013-01-01 21:59:20',19,6,66,11,3,67),('2013-01-01 21:59:33',19,6,67,11,3,68),('2013-06-13 22:01:04',19,6,68,11,NULL,69),('2013-06-13 22:01:10',19,6,69,11,NULL,70),('2013-06-13 22:01:36',1166,6,70,11,NULL,61),('2013-06-13 22:01:36',48754,1,71,11,NULL,61),('2013-06-13 22:02:00',19,6,72,11,NULL,73),('2013-01-16 22:04:32',19,6,73,11,3,74),('2013-01-16 22:05:01',19,6,74,11,3,75),('2013-01-16 22:07:25',19,6,75,11,3,76),('2013-01-16 22:08:25',19,6,76,11,3,77),('2013-01-16 22:09:22',80,6,77,11,3,78),('2013-01-16 22:10:01',19,6,78,11,3,79),('2013-06-13 22:01:26',19,6,79,11,NULL,71),('2013-06-13 23:07:27',12,6,80,12,NULL,80),('2013-01-11 23:09:44',12,6,81,12,4,84),('2013-06-13 23:07:38',12,6,82,12,NULL,81),('2013-01-11 23:09:07',288,6,83,12,4,82),('2013-01-12 23:17:18',12,6,84,12,4,85),('2013-01-12 23:22:52',7,6,85,13,6,87),('2013-01-12 23:23:02',7,6,86,13,6,88),('2013-01-12 23:23:15',7,6,87,13,6,89),('2013-01-11 23:09:19',300,6,88,13,6,83),('2013-01-12 23:26:14',7,6,89,13,6,91),('2013-01-12 23:27:45',0,6,90,14,5,86),('2013-01-12 23:27:45',12,1,91,14,5,86),('2013-01-12 23:27:34',8,6,92,14,5,93),('2013-01-12 23:27:21',8,6,93,14,5,92),('2013-06-13 23:57:18',11,6,94,15,NULL,94),('2013-06-13 23:57:31',11,6,95,15,NULL,95),('2013-06-13 23:57:44',11,6,96,15,NULL,96),('2013-01-07 23:59:01',11,6,97,15,7,97),('2013-01-07 23:59:14',11,6,98,15,7,98),('2013-01-07 23:59:26',11,6,99,15,7,99),('2013-01-08 00:00:13',11,6,100,15,7,100),('2013-01-08 00:00:29',11,6,101,15,7,101),('2013-01-08 00:00:46',11,6,102,15,7,102),('2013-01-20 20:31:46',11,6,103,15,7,103),('2013-01-20 20:32:38',11,6,104,15,7,104),('2013-01-20 20:32:58',11,6,105,15,7,105),('2013-01-20 20:34:20',11,6,106,15,7,106),('2013-01-20 20:34:45',11,6,107,15,7,107),('2013-02-18 20:39:12',11,6,108,15,12,109),('2013-02-18 20:39:04',11,6,109,15,12,108),('2013-02-18 20:39:19',11,6,110,15,12,110),('2013-02-18 20:51:47',11,6,111,15,12,111),('2013-02-18 20:53:00',11,6,112,15,12,112),('2013-02-18 20:53:48',15,6,113,12,11,113),('2013-02-18 20:53:57',12,6,114,12,11,114),('2013-02-18 20:54:05',12,6,115,12,11,115),('2013-02-18 20:54:32',12,6,116,12,11,116),('2013-02-18 20:54:49',12,6,117,12,11,117),('2013-02-18 20:55:09',15,6,118,12,11,119),('2013-02-18 20:54:58',12,6,119,12,11,118),('2013-02-18 20:57:20',25,6,120,11,10,120),('2013-02-18 20:57:38',19,6,121,11,10,121),('2013-02-18 20:57:52',19,6,122,11,10,122),('2013-02-18 20:58:20',25,6,123,11,10,125),('2013-02-18 20:58:05',19,6,124,11,10,123),('2013-02-18 20:58:13',19,6,125,11,10,124),('2013-02-18 20:58:49',19,6,126,11,10,126),('2013-02-18 20:59:22',13,6,127,10,9,127),('2013-02-18 21:05:50',17,6,128,9,8,128),('2013-02-18 21:06:03',17,6,129,9,8,129),('2013-02-18 21:06:19',17,6,130,9,8,130),('2013-02-18 21:06:32',17,6,131,9,8,131),('2013-02-18 21:06:42',17,6,132,9,8,132),('2013-02-18 21:07:22',17,6,133,9,8,133),('2013-03-18 23:30:02',17,6,134,9,13,134),('2013-03-18 23:30:09',17,6,135,9,13,135),('2013-03-18 23:30:59',17,6,136,9,13,136),('2013-03-18 23:34:22',17,6,137,9,13,140),('2013-03-18 23:33:09',17,6,138,9,13,137),('2013-03-18 23:33:30',17,6,139,9,13,139),('2013-03-18 23:35:34',17,6,140,9,13,141),('2013-03-24 15:00:07',11,6,141,15,15,142),('2013-03-24 15:00:31',11,6,142,15,15,143),('2013-03-24 15:01:58',11,6,143,15,15,145),('2013-06-15 15:37:33',11,6,144,15,NULL,146),('2013-06-15 15:37:51',60,6,145,15,NULL,147),('2013-06-15 15:43:24',11,6,146,15,NULL,149),('2013-06-15 15:44:05',11,6,147,15,NULL,151),('2013-06-15 15:43:46',11,6,148,15,NULL,150),('2013-06-15 15:48:28',8,6,149,14,NULL,152),('2013-04-05 15:54:12',8,6,150,14,19,153),('2013-04-05 15:54:41',78,6,151,14,19,154),('2013-04-05 15:55:21',8,6,152,14,19,155),('2013-06-15 15:41:48',11,6,153,15,NULL,148),('2013-06-13 22:01:32',798,6,154,15,NULL,72),('2013-06-15 16:09:43',11,6,155,15,NULL,156),('2013-06-15 16:12:07',11,6,156,15,NULL,157),('2013-06-15 16:13:14',11,6,157,15,NULL,158),('2013-05-09 16:20:04',11,6,158,15,21,159),('2013-05-09 16:20:53',11,6,159,15,21,162),('2013-05-09 16:21:38',60,6,160,15,21,163),('2013-05-09 16:22:17',60,6,161,15,21,164),('2013-05-09 16:34:02',17,6,162,9,20,165),('2013-05-09 16:20:16',11,6,163,9,20,160),('2013-05-09 16:35:13',58,6,164,9,20,166),('2013-05-09 16:36:46',17,6,165,9,20,167),('2013-05-09 16:37:01',17,6,166,9,20,168),('2013-05-09 16:20:30',11,6,167,9,20,161),('2013-03-18 16:47:44',11,6,168,15,15,169),('2013-03-18 16:48:06',11,6,169,15,15,171),('2013-03-18 23:33:16',306,6,170,15,15,138),('2013-01-12 23:25:53',126,6,171,15,7,90),('2013-03-24 15:01:16',33,6,172,14,14,144),('2013-06-15 18:04:49',-10.2,6,173,9,1,NULL),('2013-06-15 18:04:50',-6.5,6,174,10,2,NULL),('2013-06-15 18:04:51',-25.1,6,175,11,3,NULL),('2013-06-15 18:04:51',-31.2,6,176,12,4,NULL),('2013-06-15 18:04:52',-2.8,6,177,14,5,NULL),('2013-06-15 18:04:53',-32.8,6,178,13,6,NULL),('2013-06-15 18:04:53',-24.7,6,179,15,7,NULL),('2013-06-15 18:05:18',-10.2,6,180,9,8,NULL),('2013-06-15 18:05:19',-1.3,6,181,10,9,NULL),('2013-06-15 18:05:20',-14.5,6,182,11,10,NULL),('2013-06-15 18:05:21',-9,6,183,12,11,NULL),('2013-06-15 18:05:21',-5.5,6,184,15,12,NULL),('2013-06-15 18:09:12',-11.9,6,185,9,13,NULL),('2013-06-15 18:09:13',-3.3,6,186,14,14,NULL),('2013-06-15 18:09:14',-36.1,6,187,15,15,NULL),('2013-06-15 18:09:31',-5.8,6,188,1,16,NULL),('2013-06-15 18:09:31',-5.8,6,189,2,17,NULL),('2013-06-15 18:09:32',-6.8,6,190,8,18,NULL),('2013-06-15 18:09:33',-9.4,6,191,14,19,NULL),('2013-06-15 18:09:57',-13.1,6,192,9,20,NULL),('2013-06-15 18:09:58',-14.2,6,193,15,21,NULL),('2013-06-15 18:09:58',-7,6,194,1,22,NULL),('2013-06-15 18:09:59',-7,6,195,2,23,NULL),('2013-06-15 18:10:00',-8.2,6,196,8,24,NULL);
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
  CONSTRAINT `FK_usuario_foto_usuario` FOREIGN KEY (`fotoUsuarioID`) REFERENCES `foto_usuario` (`fotoUsuarioID`),
  CONSTRAINT `FK_usuario_playa` FOREIGN KEY (`playaID`) REFERENCES `playa` (`playaID`),
  CONSTRAINT `FK_usuario_tipo_doc` FOREIGN KEY (`tipoDocID`) REFERENCES `tipo_doc` (`tipoDocID`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES ('Moreno','pablo_la31@hotmail.com','Pablo','123456',NULL,'pablo',1,1,'32987654',1,3,NULL),('Bostico','alebostico@hotmail.com','Alejandro','123456',NULL,'alejandro',2,1,'11111111',1,NULL,NULL),('Arribere','gonzaloarribere@gmail.com','Gonzalo','123456',NULL,'gonzalo',3,1,'22222222',1,NULL,NULL),('Morales Batovski','morales.batovski@gmail.com','Raúl Gustavo','123456',NULL,'gmorales',4,1,'29966905',1,NULL,NULL),('Perez Villar','ericperezvillar@gmail.com','Eric','123456',NULL,'eric',5,1,'44444444',1,3,NULL),('Admin','admin@playon.com.ar','Super','123456',NULL,'admin',6,1,'66666666',1,NULL,NULL),('Gomez','sgomez@playon.com.ar','Silvina','123456',NULL,'sgomez',7,1,'10456654',1,NULL,NULL),('Lopez','jjlopez@playon.com.ar','Juana Josefa','123456',NULL,'jjlopez',8,1,'27600710',1,NULL,NULL),('Pérez','jperez@playon.com.ar','Juan','123456',NULL,'jperez',9,1,'31234234',1,NULL,NULL),('Santoni','rsantoni@playon.com.ar','Rafael','123456',NULL,'rsantoni',10,1,'15454597',1,NULL,NULL),('Rube','richardparking@hotmail.com','Ricardo','123456',NULL,'richardparking',11,1,'123456777',1,4,NULL),('Ricardo','ricardoempleado@hotmail.com','Ramirez','123456',NULL,'ricardoempleado',12,1,'34045824',1,10,NULL),('Argento','gerente@gerencia.com.ar','Pedro','123456',NULL,'pargento',13,1,'10252102',1,11,NULL),('Meolanz','jmeolanz@estac.com.ar','Jose','123456',NULL,'jmeolanz',14,1,'29888777',1,12,NULL),('Posse','lposse@pcenter.com','Luciano','123456',NULL,'lposse',15,1,'31250455',1,13,NULL),('Morales','emorales@newparking.com','Evo','123456',NULL,'emorales',16,1,'19878525',1,14,NULL),('Alperovich','jalperovich@ledesma.com','Jose','123456',NULL,'jalperovich',17,1,'28565874',1,15,NULL),('Pilatti','jpilatti@humberto.com','Joaquin','123456',NULL,'jpilatti',18,1,'33989522',1,16,NULL),('Lastra','jlastra@sanjero.com','Juan','123456',NULL,'jlastra',19,1,'29898526',1,17,NULL),('Cristobal','jcristobal@sanjorge.com','Jorge','123456',NULL,'jcristobal',20,1,'16989525',1,18,NULL),('Fundaro','gfundaro@cliente.com','Gonzalo','123456',NULL,'gfundaro',21,1,'32585887',1,NULL,NULL),('Lizzi','francisco@lizzi.com','Francisco','123456',NULL,'flizzi',22,1,'33985211',1,NULL,NULL),('Romero','jul@romero.com','Julian','123456',NULL,'jromero',23,1,'29878522',1,NULL,NULL),('Posse','c@posse.com.ar','Cristopher','123456',NULL,'cposse',24,1,'29999656',1,NULL,NULL),('Santoni','csantoni@sonyu.com','Carla','123456',NULL,'csantoni',25,1,'33823369',1,NULL,NULL),('Grippo','mgripo@gmail.com','Maria','123456',NULL,'mgripo',26,1,'11652021',1,NULL,NULL),('diaz','sdiaz@deloi.com','Santiago','123456',NULL,'sdiaz',27,1,'2554121',1,NULL,NULL),('Santoni','msantoni@seg.com','Mauro','123456',NULL,'msantoni',28,1,'10758885',1,NULL,NULL);
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
  CONSTRAINT `FK_cliente_barrio` FOREIGN KEY (`barrioID`) REFERENCES `barrio` (`barrioID`),
  CONSTRAINT `FK_cliente_usuario` FOREIGN KEY (`usuarioID`) REFERENCES `usuario` (`usuarioID`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (1,4,'Chile 160',1,'156123456',1,3),(2,1,'Colon 566',2,'152123456',2,7),(1,2,'Buenos Aires 75',3,'153123456',3,8),(3,3,'Av. Valparaiso 3125',4,NULL,4,9),(2,5,'Chile 441',74,'4585858',5,21),(1,6,'Colon 445',523,'4541425',6,22),(4,7,'Oncativo 155',370,'153336582',7,23),(1,8,'colon 10',304,'15222545',8,24),(4,9,'23 de septiembre 55',78,'15588452',9,25),(1,10,'Colon 445',260,'155222365',10,26),(2,11,'Bolivia 212',503,'155699632',11,27),(1,12,'Indarte 55',221,'156698954',12,28);
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

LOCK TABLES `marca_vehiculo` WRITE;
/*!40000 ALTER TABLE `marca_vehiculo` DISABLE KEYS */;
INSERT INTO `marca_vehiculo` VALUES (NULL,'Renault',1),(NULL,'Ford',2),(NULL,'Volkswagen',3),(NULL,'Mercedes Benz',4),(NULL,'Peugeot',5),(NULL,'Citroen',6),(NULL,'BMW',7),(NULL,'Audi',8),(NULL,'Seat',9),(NULL,'Opel',10),(NULL,'Fiat',11),(NULL,'Nissan',12),(NULL,'Toyota',13),(NULL,'Hyundai',14),(NULL,'Volvo',15),(NULL,'Kia',16),(NULL,'Mitsubishi',17),(NULL,'Land Rover',18),(NULL,'Suzuki',19),(NULL,'Porche',20),(NULL,'Chevrolet',21),(NULL,'Mini',22),(NULL,'Alfa Romeo',23),(NULL,'Honda',24),(NULL,'Jeep',25),(NULL,'Chrysler',26),(NULL,'Mazda',27),(NULL,'Otra Marca',28);
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

-- Dump completed on 2013-06-15 18:30:56
