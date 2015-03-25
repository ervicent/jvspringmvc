/*
 *  Script de generación de la estructura, carga de datos básicos, 
 *  creación del usuario y asignación de permisos
 *  de de la base de datos del proyecto tesis-playon.
 *
 *  Proyecto: Playon
 *  Autor: Alejandro Bostico
 *  Ultima modificacion: 22/08/2012
 */

/*  Usuario y password para la base de datos tesis_playon:
 *  Usuario:  'playonAdmin'
 *  Password: 'playon'
 */

/************************************************************ 
 ******* OJO QUE ESTO BORRA TODA LA BASE DE DATOS!!! ******** */
/* Comentar la siguiente lí­nea para NO borrar la base de datos
   antes de crear las tablas en caso de que fuera necesario.  */
 UNLOCK TABLES;
 DROP DATABASE IF EXISTS `tesis_playon`;
/************************************************************ */

/* Comentar la siguiente linea para NO borrar el usuario
   en caso de que fuera necesario. */
DROP USER  'playonAdmin'@'localhost';

CREATE DATABASE  IF NOT EXISTS `tesis_playon` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `tesis_playon`;
-- MySQL dump 10.13  Distrib 5.5.24, for debian-linux-gnu (x86_64)
--
-- Host: 127.0.0.1    Database: tesis_playon
-- ------------------------------------------------------
-- Server version       5.5.24-log

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
-- Table structure for table `cuenta_playa`
--

DROP TABLE IF EXISTS `cuenta_playa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cuenta_playa` (
  `fechaCreacion` datetime NOT NULL,
  `nroCuenta` int(11) NULL,
  `saldo` float NOT NULL DEFAULT 0,
  `cuentaPlayaID` int(11) NOT NULL auto_increment,
  `playaID` int(11) DEFAULT NULL,
  PRIMARY KEY (`cuentaPlayaID`),
  KEY `playaID` (`playaID`),
  CONSTRAINT `FK_cuenta_playa_playa` FOREIGN KEY (`playaID`) REFERENCES `playa` (`playaID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `denuncia_vehiculo`
--

DROP TABLE IF EXISTS `denuncia_vehiculo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `denuncia_vehiculo` (
  `asunto` text DEFAULT NULL,
  `fechaAlta` datetime NOT NULL,
  `vehiculoID` int(11) NOT NULL,
  `denunciaVehiculoID` int(11) NOT NULL auto_increment,
  `playaID` int(11) NOT NULL,
  `estadoID` int(11) NOT NULL,
  PRIMARY KEY (`denunciaVehiculoID`),
  KEY `vehiculoID` (`vehiculoID`),
  KEY `playaID` (`playaID`),
  CONSTRAINT `FK_denuncia_vehiculo_playa` FOREIGN KEY (`playaID`) REFERENCES `playa` (`playaID`),
  CONSTRAINT `FK_denuncia_vehiculo_vehiculo` FOREIGN KEY (`vehiculoID`) REFERENCES `vehiculo` (`vehiculoID`),
  CONSTRAINT `FK_denuncia_vehiculo_estado` FOREIGN KEY (`estadoID`) REFERENCES `estado_denuncia` (`estadoDenunciaID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `provincia`
--

DROP TABLE IF EXISTS `provincia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `provincia` (
  `nombre` varchar(50) NOT NULL,
  `provinciaID` int(11) NOT NULL auto_increment,
  `paisID` int(11) NOT NULL,
  PRIMARY KEY (`provinciaID`),
  KEY `paisID` (`paisID`),
  CONSTRAINT `FK_provincia_pais` FOREIGN KEY (`paisID`) REFERENCES `pais` (`paisID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `estado_publicidad`
--

DROP TABLE IF EXISTS `estado_publicidad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `estado_publicidad` (
  `descripcion` text DEFAULT NULL,
  `nombre` varchar(50) NOT NULL,
  `estadoPublicidadID` int(11) NOT NULL auto_increment,
  PRIMARY KEY (`estadoPublicidadID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='ESTADOS:   *Vigente   *Vencida   *Cancelada   *Pendiente   ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `promocion`
--

DROP TABLE IF EXISTS `promocion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `promocion` (
  `descripcion` text DEFAULT NULL,
  `descuento` float DEFAULT NULL,
  `fechaAlta` datetime NOT NULL,
  `fechaFin` date DEFAULT NULL,
  `fechaInicio` date DEFAULT NULL,
  `horaFin` datetime DEFAULT NULL,
  `horaInicio` datetime DEFAULT NULL,
  `montoFijo` float DEFAULT NULL,
  `nombre` varchar(50) NOT NULL,
  `tarifaID` int(11) NOT NULL,
  `promocionID` int(11) NOT NULL auto_increment,
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
-- Table structure for table `color_vehiculo`
--

DROP TABLE IF EXISTS `color_vehiculo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `color_vehiculo` (
  `nombre` varchar(50) NOT NULL,
  `valorHexadecimal` varchar(6) NOT NULL,
  `colorVehiculoID` int(11) NOT NULL auto_increment,
  PRIMARY KEY (`colorVehiculoID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `cuenta_cliente`
--

DROP TABLE IF EXISTS `cuenta_cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cuenta_cliente` (
  `fechaCreacion` datetime NOT NULL,
  `nroCuenta` int(11) NULL UNIQUE,
  `saldo` float NOT NULL DEFAULT 0,
  `cuentaClienteID` int(11) NOT NULL auto_increment,
  `clienteID` int(11) DEFAULT NULL,
  PRIMARY KEY (`cuentaClienteID`),
  KEY `clienteID` (`clienteID`),
  CONSTRAINT `FK_cuenta_cliente_cliente` FOREIGN KEY (`clienteID`) REFERENCES `cliente` (`clienteID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `estado_denuncia`
--

DROP TABLE IF EXISTS `estado_denuncia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `estado_denuncia` (
  `descripcion` text DEFAULT NULL,
  `nombre` varchar(50) NOT NULL,
  `estadoDenunciaID` int(11) NOT NULL auto_increment,
  PRIMARY KEY (`estadoDenunciaID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `denuncia_playa`
--

DROP TABLE IF EXISTS `denuncia_playa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `denuncia_playa` (
  `asunto` text DEFAULT NULL,
  `fechaAlta` datetime NOT NULL,
  `playaID` int(11) NOT NULL,
  `denunciaPlayaID` int(11) NOT NULL auto_increment,
  `clienteID` int(11) NOT NULL,
  `estadoID` int(11) NOT NULL,
  PRIMARY KEY (`denunciaPlayaID`),
  KEY `playaID` (`playaID`),
  KEY `clienteID` (`clienteID`),
  CONSTRAINT `FK_denuncia_playa_cliente` FOREIGN KEY (`clienteID`) REFERENCES `cliente` (`clienteID`),
  CONSTRAINT `FK_denuncia_playa_playa` FOREIGN KEY (`playaID`) REFERENCES `playa` (`playaID`),
  CONSTRAINT `FK_denuncia_playa_estado` FOREIGN KEY (`estadoID`) REFERENCES `estado_denuncia` (`estadoDenunciaID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

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
  `latitud` DOUBLE DEFAULT NULL,
  `longitud` DOUBLE DEFAULT NULL,
  `razonSocial` varchar(50) DEFAULT NULL,
  `playaID` int(11) NOT NULL auto_increment,
  PRIMARY KEY (`playaID`),
  KEY `barrioID` (`barrioID`),
  KEY `estadoPlayaID` (`estadoPlayaID`),
  CONSTRAINT `FK_playa_barrio` FOREIGN KEY (`barrioID`) REFERENCES `barrio` (`barrioID`),
  CONSTRAINT `FK_playa_estado_playa` FOREIGN KEY (`estadoPlayaID`) REFERENCES `estado_playa` (`estadoPlayaID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Cada instancia representa los datos administrativos de una playa.   ';
/*!40101 SET character_set_client = @saved_cs_client */;

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
  `importeTotal` float NOT NULL DEFAULT 0,
  `liquidacionID` int(11) NOT NULL auto_increment,
  `estadiaID` int(11) NOT NULL,
  PRIMARY KEY (`liquidacionID`),
  KEY `estadiaID` (`estadiaID`),
  CONSTRAINT `FK_liquidacion_estadia` FOREIGN KEY (`estadiaID`) REFERENCES `estadia` (`estadiaID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `categoria_vehiculo`
--

DROP TABLE IF EXISTS `categoria_vehiculo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `categoria_vehiculo` (
  `descripcion` text DEFAULT NULL,
  `nombre` varchar(50) NOT NULL,
  `categoriaVehiculoID` int(11) NOT NULL auto_increment,
  PRIMARY KEY (`categoriaVehiculoID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Son los tí­pos de vehí­culos. Moto, Auto, Utilitario, PickUp, etc.   ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `estado_playa`
--

DROP TABLE IF EXISTS `estado_playa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `estado_playa` (
  `descripcion` text DEFAULT NULL,
  `nombre` varchar(50) NOT NULL,
  `estadoPlayaID` int(11) NOT NULL auto_increment,
  PRIMARY KEY (`estadoPlayaID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

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
  `transaccionClienteID` int(11) NOT NULL auto_increment,
  `cuentaClienteID` int(11) NOT NULL,
  PRIMARY KEY (`transaccionClienteID`),
  KEY `cuentaClienteID` (`cuentaClienteID`),
  KEY `tipoPagoID` (`tipoPagoID`),
  CONSTRAINT `FK_transaccion_cliente_tipo_pago` FOREIGN KEY (`tipoPagoID`) REFERENCES `tipo_pago` (`tipoPagoID`),
  CONSTRAINT `FK_transaccion_cliente_cuenta_cliente` FOREIGN KEY (`cuentaClienteID`) REFERENCES `cuenta_cliente` (`cuentaClienteID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tipo_estadia`
--

DROP TABLE IF EXISTS `tipo_estadia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipo_estadia` (
  `descripcion` text DEFAULT NULL,
  `nombre` varchar(50) NOT NULL,
  `tipoEstadiaID` int(11) NOT NULL auto_increment,
  PRIMARY KEY (`tipoEstadiaID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='TIPOS DE ESTADIA:   * Por hora   * Por día   * Por noche   * Por mes   * Por semana   ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `historial_de_cambio`
--

DROP TABLE IF EXISTS `historial_de_cambio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `historial_de_cambio` (
  `comentario` text DEFAULT NULL,
  `estadoDenunciaID` int(11) NOT NULL,
  `fecha` datetime NOT NULL,
  `historialDeCambioID` int(11) NOT NULL auto_increment,
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
-- Table structure for table `foto`
--

DROP TABLE IF EXISTS `foto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `foto` (
  `descripcion` text DEFAULT NULL,
  `nombre` varchar(50) NOT NULL,
  `title` varchar(50) DEFAULT NULL,
  `image` MEDIUMBLOB DEFAULT NULL,
  `fotoID` int(11) NOT NULL auto_increment,
  `perfilPlayaID` int(11) NOT NULL,
  PRIMARY KEY (`fotoID`),
  KEY `perfilPlayaID` (`perfilPlayaID`),
  CONSTRAINT `FK_foto_perfil_playa` FOREIGN KEY (`perfilPlayaID`) REFERENCES `perfil_playa` (`perfilPlayaID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
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
  `colorVehiculoID` int(11) NULL,
  `habilitado` tinyint(1) DEFAULT 1,
  `modeloVehiculoID` int(11) NOT NULL,
  `patente` varchar(50) NOT NULL,
  `vehiculoID` int(11) NOT NULL auto_increment,
  `clienteID` int(11) NOT NULL,
  PRIMARY KEY (`vehiculoID`),
  KEY `modeloVehiculoID` (`modeloVehiculoID`),
  KEY `colorVehiculoID` (`colorVehiculoID`),
  KEY `clienteID` (`clienteID`),
  CONSTRAINT `FK_vehiculo_cliente` FOREIGN KEY (`clienteID`) REFERENCES `cliente` (`clienteID`),
  CONSTRAINT `FK_vehiculo_color_vehiculo` FOREIGN KEY (`colorVehiculoID`) REFERENCES `color_vehiculo` (`colorVehiculoID`),
  CONSTRAINT `FK_vehiculo_modelo_vehiculo` FOREIGN KEY (`modeloVehiculoID`) REFERENCES `modelo_vehiculo` (`modeloVehiculoID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `empleado`
--

DROP TABLE IF EXISTS `empleado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `empleado` (
  `cargoEmpleadoID` int(11) NOT NULL,
  `legajo` int(11) DEFAULT NULL,
  `empleadoID` int(11) NOT NULL auto_increment,
  `usuarioID` int(11) NOT NULL,
  PRIMARY KEY (`empleadoID`),
  KEY `cargoEmpleadoID` (`cargoEmpleadoID`),
  KEY `usuarioID` (`usuarioID`),
  CONSTRAINT `FK_empleado_usuario` FOREIGN KEY (`usuarioID`) REFERENCES `usuario` (`usuarioID`),
  CONSTRAINT `FK_empleado_cargo_empleado` FOREIGN KEY (`cargoEmpleadoID`) REFERENCES `cargo_empleado` (`cargoEmpleadoID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `cargo_empleado`
--

DROP TABLE IF EXISTS `cargo_empleado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cargo_empleado` (
  `descripcion` text DEFAULT NULL,
  `nombre` varchar(50) NOT NULL,
  `cargoEmpleadoID` int(11) NOT NULL auto_increment,
  PRIMARY KEY (`cargoEmpleadoID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `perfil_playa`
--

DROP TABLE IF EXISTS `perfil_playa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `perfil_playa` (
  `descripcion` text DEFAULT NULL,
  `perfilPlayaID` int(11) NOT NULL auto_increment,
  `playaID` int(11) NOT NULL,
  `fotoPerfil` MEDIUMBLOB DEFAULT NULL,
  `nombreFoto` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`perfilPlayaID`),
  KEY `playaID` (`playaID`),
  CONSTRAINT `FK_perfil_playa_playa` FOREIGN KEY (`playaID`) REFERENCES `playa` (`playaID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Esta clase va a contener todos los datos del perfil de la playa que se muestra en el sitio: fotos, nombre para mostrar, descripción, etc.    ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `calificacion`
--

DROP TABLE IF EXISTS `calificacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `calificacion` (
  `calificacionID` int(11) NOT NULL auto_increment,
  `playaID` int(11) NOT NULL,
  `clienteID` int(11) NOT NULL,
  `calificacion` int(2) NOT NULL,
  PRIMARY KEY (`calificacionID`),
  KEY `playaID` (`playaID`),
  KEY `clienteID` (`clienteID`),
  CONSTRAINT `FK_calificacion_playa` FOREIGN KEY (`playaID`) REFERENCES `playa` (`playaID`),
  CONSTRAINT `FK_calificacion_cliente` FOREIGN KEY (`clienteID`) REFERENCES `cliente` (`clienteID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

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
  `abonoID` int(11) NOT NULL auto_increment,
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `pais`
--

DROP TABLE IF EXISTS `pais`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pais` (
  `nombre` varchar(50) NOT NULL,
  `paisID` int(11) NOT NULL auto_increment,
  PRIMARY KEY (`paisID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

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
  `tarifaID` int(11) NOT NULL auto_increment,
  `playaID` int(11) NOT NULL,
  `tipoEstadiaID` int(11) NOT NULL,
  `categoriaVehiculoID` int(11) NOT NULL,
  PRIMARY KEY (`tarifaID`),
  KEY `categoriaVehiculoID` (`categoriaVehiculoID`),
  KEY `tipoEstadiaID` (`tipoEstadiaID`),
  CONSTRAINT `FK_tarifa_playa` FOREIGN KEY (`playaID`) REFERENCES `playa` (`playaID`),
  CONSTRAINT `FK_tarifa_tipo_estadia` FOREIGN KEY (`tipoEstadiaID`) REFERENCES `tipo_estadia` (`tipoEstadiaID`),
  CONSTRAINT `FK_tarifa_categoria_vehiculo` FOREIGN KEY (`categoriaVehiculoID`) REFERENCES `categoria_vehiculo` (`categoriaVehiculoID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Cada instancia contiene un precio de la tarifa que depende del Categorí­a de vehí­culo, tipo de estadí­a (mensual, por hora, etc).      Categorí­aVehiculo: utilitario   TipoEstadí­a: Mensual   Precio/tarifa: $720      Categorí­aVehiculo: utilitario   TipoEstadí­a: Por hora   Precio/tarifa: $14      Categorí­aVehiculo: auto   TipoEstadí­a: Por hora   Precio/tarifa: $12   ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `barrio`
--

DROP TABLE IF EXISTS `barrio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `barrio` (
  `nombre` varchar(50) NOT NULL,
  `barrioID` int(11) NOT NULL auto_increment,
  `localidadID` int(11) NOT NULL,
  PRIMARY KEY (`barrioID`),
  KEY `localidadID` (`localidadID`),
  CONSTRAINT `FK_barrio_localidad` FOREIGN KEY (`localidadID`) REFERENCES `localidad` (`localidadID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `favorito`
--

DROP TABLE IF EXISTS `favorito`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `favorito` (
  `favoritoID` int(11) NOT NULL auto_increment,
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
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `apellido` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL UNIQUE,
  `nombre` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `sesion` varchar(50) DEFAULT NULL,
  `usuario` varchar(50) NOT NULL UNIQUE,
  `usuarioID` int(11) NOT NULL auto_increment,
  `tipoDocID` int(11) DEFAULT NULL,
  `nroDoc` varchar(50) DEFAULT NULL,
  `enable` tinyint(1) DEFAULT 1,
  `playaID` int(11) DEFAULT NULL,
  `fotoUsuarioID` int(11) DEFAULT NULL,
  PRIMARY KEY (`usuarioID`),
  KEY `tipoDocID` (`tipoDocID`),
  KEY `fotoUsuarioID` (`fotoUsuarioID`),
  CONSTRAINT `FK_usuario_tipo_doc` FOREIGN KEY (`tipoDocID`) REFERENCES `tipo_doc` (`tipoDocID`),
  CONSTRAINT `FK_usuario_foto_usuario` FOREIGN KEY (`fotoUsuarioID`) REFERENCES `foto_usuario` (`fotoUsuarioID`),
  CONSTRAINT `FK_usuario_playa` FOREIGN KEY (`playaID`) REFERENCES `playa` (`playaID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `foto_usuario`
--

DROP TABLE IF EXISTS `foto_usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `foto_usuario` (
  `fotoUsuarioID` int(11) NOT NULL auto_increment,
  `fotoUsuario` MEDIUMBLOB DEFAULT NULL,
  PRIMARY KEY (`fotoUsuarioID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tipo_pago`
--

DROP TABLE IF EXISTS `tipo_pago`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipo_pago` (
  `descripcion` text DEFAULT NULL,
  `nombre` varchar(50) NOT NULL,
  `tipoPagoID` int(11) NOT NULL auto_increment,
  PRIMARY KEY (`tipoPagoID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

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
  `transaccionPlayaID` int(11) NOT NULL auto_increment,
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `marca_vehiculo`
--

DROP TABLE IF EXISTS `marca_vehiculo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `marca_vehiculo` (
  `descripcion` text DEFAULT NULL,
  `nombre` varchar(50) NOT NULL,
  `marcaVehiculoID` int(11) NOT NULL auto_increment,
  PRIMARY KEY (`marcaVehiculoID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `estadia`
--

DROP TABLE IF EXISTS `estadia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `estadia` (
  `estadiaID` int(11) NOT NULL auto_increment,
  `playaID` int(11) NOT NULL,
  PRIMARY KEY (`estadiaID`),
  KEY `playaID` (`playaID`),
  CONSTRAINT `FK_estadia_playa` FOREIGN KEY (`playaID`) REFERENCES `playa` (`playaID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Cada instancia de esta clase representa un conjunto de ingreso y egreso de los autos en cada playa.   ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `modelo_vehiculo`
--

DROP TABLE IF EXISTS `modelo_vehiculo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `modelo_vehiculo` (
  `descripcion` text DEFAULT NULL,
  `nombre` varchar(50) NOT NULL,
  `modeloVehiculoID` int(11) NOT NULL auto_increment,
  `marcaVehiculoID` int(11) NOT NULL,
  `categoriaID` int(11) NOT NULL,
  PRIMARY KEY (`modeloVehiculoID`),
  KEY `categoriaID` (`categoriaID`),
  KEY `marcaVehiculoID` (`marcaVehiculoID`),
  CONSTRAINT `FK_modelo_vehiculo_marca_vehiculo` FOREIGN KEY (`marcaVehiculoID`) REFERENCES `marca_vehiculo` (`marcaVehiculoID`),
  CONSTRAINT `FK_modelo_vehiculo_categoria_vehiculo` FOREIGN KEY (`categoriaID`) REFERENCES `categoria_vehiculo` (`categoriaVehiculoID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `localidad`
--

DROP TABLE IF EXISTS `localidad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `localidad` (
  `nombre` varchar(50) NOT NULL,
  `localidadID` int(11) NOT NULL auto_increment,
  `provinciaID` int(11) NOT NULL,
  PRIMARY KEY (`localidadID`),
  KEY `provinciaID` (`provinciaID`),
  CONSTRAINT `FK_localidad_provincia` FOREIGN KEY (`provinciaID`) REFERENCES `provincia` (`provinciaID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cliente` (
  `barrioID` int(11) DEFAULT NULL,
  `cuentaClienteID` int(11) NOT NULL,
  `domicilio` text DEFAULT NULL,
  `nroCliente` int(11) NOT NULL,
  `telefono` text DEFAULT NULL,
  `clienteID` int(11) NOT NULL auto_increment,
  `usuarioID` int(11) NOT NULL,
  PRIMARY KEY (`clienteID`),
  KEY `barrioID` (`barrioID`),
  KEY `usuarioID` (`usuarioID`),
  CONSTRAINT `FK_cliente_usuario` FOREIGN KEY (`usuarioID`) REFERENCES `usuario` (`usuarioID`),
  CONSTRAINT `FK_cliente_barrio` FOREIGN KEY (`barrioID`) REFERENCES `barrio` (`barrioID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tipo_doc`
--

DROP TABLE IF EXISTS `tipo_doc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipo_doc` (
  `nombre` varchar(50) NOT NULL,
  `tipoDocID` int(11) NOT NULL auto_increment,
  PRIMARY KEY (`tipoDocID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `publicidad`
--

DROP TABLE IF EXISTS `publicidad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `publicidad` (
  `publicidadID` int(11) NOT NULL auto_increment,
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
-- Table structure for table `foto_publicidad`
--

DROP TABLE IF EXISTS `foto_publicidad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `foto_publicidad` (
  `fotoPublicidadID` int(11) NOT NULL auto_increment,
  `nombre` varchar(75) DEFAULT NULL,
  `image` mediumblob,
  `url` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`fotoPublicidadID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

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
  `detalleEstadiaID` int(11) NOT NULL auto_increment,
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

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
  `sesionID` int(11) NOT NULL auto_increment,
  `usuarioID` int(11) DEFAULT NULL,
  PRIMARY KEY (`sesionID`),
  KEY `usuarioID` (`usuarioID`),
  CONSTRAINT `FK_sesion_usuario` FOREIGN KEY (`usuarioID`) REFERENCES `usuario` (`usuarioID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `posicion`
--

DROP TABLE IF EXISTS `posicion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `posicion` (
  `tamanioKBMax` int(11) NOT NULL DEFAULT 0,
  `tamanioX` int(11) NOT NULL DEFAULT 0,
  `tamanioY` int(11) NOT NULL DEFAULT 0,
  `ubicacion` text DEFAULT NULL,
  `posicionID` int(11) NOT NULL auto_increment,
  PRIMARY KEY (`posicionID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Clase que contiene los datos sobre las ubicaciones y posiciones de las publicidades. (layout de publicidades)   ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `estado_promocion`
--

DROP TABLE IF EXISTS `estado_promocion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `estado_promocion` (
  `descripcion` text DEFAULT NULL,
  `nombre` varchar(50) NOT NULL,
  `estadoPromocionID` int(11) NOT NULL auto_increment,
  PRIMARY KEY (`estadoPromocionID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='ESTADOS:   *Vigente   *Vencida   *Cancelada   *Pendiente   ';
/*!40101 SET character_set_client = @saved_cs_client */;

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
  `comentarioID` int(11) NOT NULL auto_increment,
  `usuarioID` int(11) NOT NULL,
  PRIMARY KEY (`comentarioID`),
  KEY `playaID` (`playaID`),
  KEY `clienteID` (`usuarioID`),
  CONSTRAINT `FK_comentario_usuario` FOREIGN KEY (`usuarioID`) REFERENCES `usuario` (`usuarioID`),
  CONSTRAINT `FK_comentario_perfil_playa` FOREIGN KEY (`playaID`) REFERENCES `perfil_playa` (`perfilPlayaID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `roles_por_usuario`
--

DROP TABLE IF EXISTS `roles_por_usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roles_por_usuario` (
  `rolesPorUsuarioID` int(11) NOT NULL auto_increment,
  `usuario` varchar(50) NOT NULL,
  `rolUsuario` varchar(50) NOT NULL,
  PRIMARY KEY (`rolesPorUsuarioID`),
  KEY `usuario` (`usuario`),
  KEY `rolUsuario` (`rolUsuario`),
  CONSTRAINT `FK_roles_por_usuario_usuario` FOREIGN KEY (`usuario`) REFERENCES `usuario` (`usuario`),
  CONSTRAINT `FK_roles_por_usuario_rol_usuario` FOREIGN KEY (`rolUsuario`) REFERENCES `rol_usuario` (`nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `rol_usuario`
--

DROP TABLE IF EXISTS `rol_usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rol_usuario` (
  `descripcion` text DEFAULT NULL,
  `nombre` varchar(50) NOT NULL UNIQUE,
  `rolUsuarioID` int(11) NOT NULL auto_increment,
  PRIMARY KEY (`rolUsuarioID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

-- =================================================================== --


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
                                                                AND (CURDATE() >= promo.fechaInicio )
                                                                AND (CURDATE() <= DATE_ADD(promo.fechaFin, INTERVAL 1 DAY) ))))
            AND (p.estadoPlayaID = 2)
            AND (p.disponibilidad > 0));
END $$

DELIMITER ;

-- =================================================================== --


-- =================================================================== --

/**
 * Crear el usuario 'playonAdmin' con password por defecto 'playon'.
 */

/* Descomentar la siguiente linea si el usuario existe */
-- DROP USER 'playonAdmin';
CREATE USER 'playonAdmin'@'localhost' 
        IDENTIFIED BY PASSWORD '*AEBE50B9926D86DA86D2B880AA0DF19F0EEDDEF0';

/**
 * Asignamos todos los permisos al usuario 'playonAdmin' para la BD `tesis_playon`.
 */
GRANT ALL PRIVILEGES ON TABLE `tesis_playon`.* TO 'playonAdmin'@'localhost';

-- =================================================================== --



-- =================================================================== --
/**
 * Carga de datos básicos
 */

--
-- Dumping data for table `estado_publicidad`
--
-- ORDER BY:  `estadoPublicidadID`

LOCK TABLES `estado_publicidad` WRITE;
/*!40000 ALTER TABLE `estado_publicidad` DISABLE KEYS */;
INSERT INTO `estado_publicidad` (`descripcion`, `nombre`, `estadoPublicidadID`) 
VALUES ('Pendiente de Aprobación','Pendiente',1),
	('Aprobada y pendiente de publicación','Aprobada',2),
	('No Aprobada','Rechazada',3);
/*!40000 ALTER TABLE `estado_publicidad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `color_vehiculo`
--
-- ORDER BY:  `colorVehiculoID`

LOCK TABLES `color_vehiculo` WRITE;
/*!40000 ALTER TABLE `color_vehiculo` DISABLE KEYS */;
INSERT INTO `color_vehiculo` (`nombre`, `valorHexadecimal`, `colorVehiculoID`) 
VALUES ('Blanco','FFFFFF',1),('Negro','000000',2),('Rojo','FF0000',3),('Amarillo','FFFF00',4),
	('Verde Claro','32CD32',5),('Azul','0000FF',6),('Celeste','1E90FF',7),('Gris Claro','C0C0C0',8),
	('Gris Oscuro','696969',9),('Naranja','FF4500',10),('Verde Oscuro','006400',11),('Bordó','65212C',12);
/*!40000 ALTER TABLE `color_vehiculo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `estado_denuncia`
--
-- ORDER BY:  `estadoDenunciaID`

LOCK TABLES `estado_denuncia` WRITE;
/*!40000 ALTER TABLE `estado_denuncia` DISABLE KEYS */;
INSERT INTO `estado_denuncia` (`descripcion`, `nombre`, `estadoDenunciaID`) 
VALUES ('Pendiente de auditorí­a','Pendiente',1),
	('En proceso de investigación','En Proceso',2),
	('Acepatada','Aceptada',3),('Rechazada','Rechazada',4),
	('Anulada','Anulada',5),('Dada de Baja / Cancelada','De Baja',6);
/*!40000 ALTER TABLE `estado_denuncia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `categoria_vehiculo`
--
-- ORDER BY:  `categoriaVehiculoID`

LOCK TABLES `categoria_vehiculo` WRITE;
/*!40000 ALTER TABLE `categoria_vehiculo` DISABLE KEYS */;
INSERT INTO `categoria_vehiculo` (`descripcion`, `nombre`, `categoriaVehiculoID`) 
VALUES ('','Auto',1),('','Moto',2),('','Utilitario',3),('','PickUp / 4X4',4);
/*!40000 ALTER TABLE `categoria_vehiculo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `estado_playa`
--
-- ORDER BY:  `estadoPlayaID`

LOCK TABLES `estado_playa` WRITE;
/*!40000 ALTER TABLE `estado_playa` DISABLE KEYS */;
INSERT INTO `estado_playa` (`descripcion`, `nombre`, `estadoPlayaID`) 
VALUES ('Pendiente de Auditorí­a','Pendiente',1),
	('Aprobada luego de auditoría','Aprobada',2),
	('Rechazada luego de auditorí­a','Rechazada',3),
	('Dada de baja','De Baja',4);
/*!40000 ALTER TABLE `estado_playa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `tipo_estadia`
--
-- ORDER BY:  `tipoEstadiaID`

LOCK TABLES `tipo_estadia` WRITE;
/*!40000 ALTER TABLE `tipo_estadia` DISABLE KEYS */;
INSERT INTO `tipo_estadia` (`descripcion`, `nombre`, `tipoEstadiaID`) 
VALUES ('','Por Hora',1),('','Por Mes',2),('','Por Noche',3),
	('','Por Día',4),('','Por Semana',5);
/*!40000 ALTER TABLE `tipo_estadia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `cargo_empleado`
--
-- ORDER BY:  `cargoEmpleadoID`

LOCK TABLES `cargo_empleado` WRITE;
/*!40000 ALTER TABLE `cargo_empleado` DISABLE KEYS */;
INSERT INTO `cargo_empleado` (`descripcion`, `nombre`, `cargoEmpleadoID`) 
VALUES ('','Gerente General',1),('','Playero',2);
/*!40000 ALTER TABLE `cargo_empleado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `tipo_pago`
--
-- ORDER BY:  `tipoPagoID`

LOCK TABLES `tipo_pago` WRITE;
/*!40000 ALTER TABLE `tipo_pago` DISABLE KEYS */;
INSERT INTO `tipo_pago` (`descripcion`, `nombre`, `tipoPagoID`) 
VALUES ('Contado Efectivo','Contado',1),
	('Tarjeta de débito','Tarjeta Débito',2),
	('Tarjeta de crédito','Tarjeta Crédito',3),
	('DineroMail','DineroMail',4),('Cheque','Cheque',5),
	('Pago con saldo de la cuenta del cliente','Cuenta',6);
/*!40000 ALTER TABLE `tipo_pago` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `tipo_doc`
--
-- ORDER BY:  `tipoDocID`

LOCK TABLES `tipo_doc` WRITE;
/*!40000 ALTER TABLE `tipo_doc` DISABLE KEYS */;
INSERT INTO `tipo_doc` (`nombre`, `tipoDocID`) 
VALUES ('D.N.I.',1),('L.C.',2),('L.E.',3),('C.I.',4);
/*!40000 ALTER TABLE `tipo_doc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `estado_promocion`
--
-- ORDER BY:  `estadoPromocionID`

LOCK TABLES `estado_promocion` WRITE;
/*!40000 ALTER TABLE `estado_promocion` DISABLE KEYS */;
INSERT INTO `estado_promocion` (`descripcion`, `nombre`, `estadoPromocionID`) 
VALUES ('Rechazada o de baja','De baja',1),
	('Aprobada y publicandose','Vigente',2);
/*!40000 ALTER TABLE `estado_promocion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `rol_usuario`
--
-- ORDER BY:  `rolUsuarioID`

LOCK TABLES `rol_usuario` WRITE;
/*!40000 ALTER TABLE `rol_usuario` DISABLE KEYS */;
INSERT INTO `rol_usuario` (`descripcion`, `nombre`, `rolUsuarioID`) 
VALUES ('Administrador del sistema','ROLE_ADMIN',1),
    ('Auditor del sistema','ROLE_AUDITOR',2),
    ('Usuario del Area administrativa/contable','ROLE_CONTADOR',3),
    ('Usuario sin permisos especiales','ROLE_USER',4),
    ('Cliente de las playas de estacionamiento','ROLE_CLIENT',5),
    ('Empleado de la playa de estacionamiento','ROLE_PLAYA_EMPLEADO',6),
    ('Gerente de la playa de estacionamiento','ROLE_PLAYA_GERENTE',7);
/*!40000 ALTER TABLE `rol_usuario` ENABLE KEYS */;
UNLOCK TABLES;

-- =================================================================== --


-- =================================================================== --
/**
 * Carga de datos de ejemplo
 */

--
-- Dumping data for table `marca_vehiculo`
--

LOCK TABLES `marca_vehiculo` WRITE;
/*!40000 ALTER TABLE `marca_vehiculo` DISABLE KEYS */;
INSERT INTO `marca_vehiculo` (`descripcion`, `nombre`, `marcaVehiculoID`) 
VALUES (NULL,'Renault',1),(NULL,'Ford',2),(NULL,'Volkswagen',3),(NULL,'Mercedes Benz',4),
    (NULL,'Peugeot',5),(NULL,'Citroen',6),(NULL,'BMW',7),(NULL,'Audi',8),(NULL,'Seat',9),
    (NULL,'Opel',10),(NULL,'Fiat',11),(NULL,'Nissan',12),(NULL,'Toyota',13),(NULL,'Hyundai',14),
    (NULL,'Volvo',15),(NULL,'Kia',16),(NULL,'Mitsubishi',17),(NULL,'Land Rover',18),
    (NULL,'Suzuki',19),(NULL,'Porche',20),(NULL,'Chevrolet',21),(NULL,'Mini',22),
    (NULL,'Alfa Romeo',23),(NULL,'Honda',24),(NULL,'Jeep',25),(NULL,'Chrysler',26),
    (NULL,'Mazda',27);
/*!40000 ALTER TABLE `marca_vehiculo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `modelo_vehiculo`
--

LOCK TABLES `modelo_vehiculo` WRITE;
/*!40000 ALTER TABLE `modelo_vehiculo` DISABLE KEYS */;
INSERT INTO `modelo_vehiculo` (`categoriaID`, `nombre`, `marcaVehiculoID`) 
VALUES (1,'Clio',1),(1,'Sandero',1),(1,'Logan',1),(1,'Symbol',1),(1,'Fluence',1),
(1,'Mégane',1),(1,'Latitude',1),(3,'Kangoo',1),(3,'Master Furgón',1),
(3,'Master Minibus',1),(4,'Duster',1),(4,'Koleos',1),(1,'Scénic',1),
(1,'Laguna',1),(1,'Twingo',1),(1,'Ka',2),(1,'Fiesta',2),(1,'Focus',2),
(1,'Mondeo',2),(1,'S-Max',2),(1,'Falcon',2),(1,'Fairlane',2),
(1,'Taunus',2),(1,'Taurus',2),(1,'Torino',2),
(1,'Escort',2),(1,'C-Max',2),(1,'Galaxi',2),(1,'Mustang',2),(1,'Escape',2),
(4,'Kuga',2),(4,'EcoSport',2),(4,'Ranger',2),(3,'Transit Furgon',2),
(3,'Transit Minibus',2),(4,'F100',2),(4,'F150',2),(4,'F250',2),
(4,'F350',2),(4,'F450',2),(4,'F650',2),(1,'Gol',3),(1,'Fox',3),(1,'Crossfox',3),
(1,'Golf GTI',3),(1,'Voyage',3),(1,'Bora',3),(1,'Vento',3),(1,'Passat',3),
(1,'Scirocco',3),(1,'CC',3),(4,'Tiguan',3),(4,'Touareg',3),(1,'Gol Country',3),
(1,'Suran',3),(1,'Vento Variant',3),(1,'Sharan',3),(1,'Passat Variant',3),
(4,'Saveiro',3),(4,'Amarok',3),(1,'Clase C Sedán',4),(1,'Clase E Sedán',4),
(1,'Clase S Sedán',4),(1,'Clase C Coupé',4),(1,'Clase E Coupé',4),(1,'SLS AMG',4),
(4,'Clase GLK',4),(1,'Clase SLK',4),(4,'Clase M',4),(1,'207',5),(1,'308',5),
(1,'3008',5),(1,'5008',5),(1,'RCZ',5),(1,'408',5),(3,'Partner Furgón',5),
(3,'Partner Patagónica',5),(4,'Hoggar',5),(1,'C3',6),(1,'C2',6),(1,'C1',6),
(1,'Xsara',6),(1,'C4',6),(1,'C6',6),(1,'DS3',6),(1,'DS4',6),(1,'DS9',6),
(3,'Berlingo',6),(1,'C5',6),(1,'C1',6),(1,'Serie 1',7),(1,'Serie 3',7),
(1,'Serie 5',7),(1,'Serie 6',7),(1,'Serie 7',7),(4,'X1',7),(4,'X3',7),
(4,'X5',7),(4,'X6',7),(1,'Z4',7),(1,'M3',7),(1,'A1',8),(1,'A3',8),(1,'A4',8),
(1,'A5',8),(1,'A6',8),(1,'A7',8),(1,'A8',8),(4,'Q5',8),(4,'Q7',8),(1,'TT',8),
(1,'R8',8),(1,'Marbella',9),(1,'Ibiza',9),(1,'Córdoba',9),(1,'León',9),(4,'Alhambra',9),
(1,'K-180',10),(1,'Linea',11),(1,'Palio',11),(1,'Uno',11),(1,'Punto',11),(1,'Siena',11),
(1,'500',11),(4,'Gama Strada',11),(1,'Idea',11),(1,'Palio Weekend',11),(3,'Qubo',11),
(3,'Fiorino',11),(3,'Ducato',11),(1,'March',12),(1,'Tiida',12),(1,'Sentra',12),(1,'370Z',12),
(4,'NP300',12),(4,'Frontier',12),(4,'X-Trail',12),(4,'Murano',12),(1,'Corolla',13),
(1,'Camry',13),(1,'Prius',13),(4,'Rav4',13),(4,'SW4',13),(4,'Land Cruiser Prado',13),
(4,'Land Cruise 200',13),(4,'Hilux',13),(1,'i10',14),(1,'i30',14),(1,'Genesis',14),
(1,'Veloster',14),(4,'Tucson',14),(4,'Santa Fe',14),(3,'H1 Minibus',14),(1,'C30',15),
(1,'S40',15),(1,'S80',15),(1,'V50',15),(4,'XC60',15),(1,'XC70',15),(1,'XC90',15),
(1,'Picanto',16),(1,'Cerato Force',16),(1,'Soul',16),(4,'Sportage',16),(4,'Sorento',16),
(1,'Lancer',17),(1,'Outlander',17),(4,'L200',17),(4,'Montero',17),(4,'Defender',18),
(4,'Freelander',18),(4,'Range Rover',18),(4,'Discovery',18),(1,'Fun',19),(4,'Vitara',19),
(1,'Swift',19),(1,'Boxster',20),(1,'Cayman',20),(1,'911',20),(1,'Panamera',20),
	(4,'Cayenne',20),(1,'Celta',21),(1,'Classic',21),(1,'Prisma',21),(1,'Agile',21),
	(1,'Aveo',21),(1,'Spatk',21),(1,'Cruze',21),(1,'Sonic',21),(1,'Meriva',21),
	(1,'Zafira',21),(4,'Montana',21),(4,'S10',21),(4,'Captiva',21),(1,'Cooper',22),(1,'Giulietta',23),
(1,'MiTo',23),(1,'159',23),(1,'8C',23),(1,'4C',23),(1,'Civic',24),(4,'CR-V',24),(1,'City',24),
(1,'S2000',24),(4,'Cherooke',25),(4,'Rangler',25),(4,'Armado',25),(1,'300C',26),(1,'PT Cruiser',26),
(1,'Neon',26),(1,'Speed 6',27),(1,'RX-8',27),(1,'RX-7',27),(1,'l2000',27);
/*!40000 ALTER TABLE `modelo_vehiculo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `pais`
--

LOCK TABLES `pais` WRITE;
/*!40000 ALTER TABLE `pais` DISABLE KEYS */;
INSERT INTO `pais` (`paisID`, `nombre`) 
VALUES (1,'Argentina');
/*!40000 ALTER TABLE `pais` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `provincia`
--

LOCK TABLES `provincia` WRITE;
/*!40000 ALTER TABLE `provincia` DISABLE KEYS */;
INSERT INTO `provincia` (`provinciaID`, `paisID`, `nombre`) 
VALUES (1,1,'Córdoba');
/*!40000 ALTER TABLE `provincia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `localidad`
--

LOCK TABLES `localidad` WRITE;
/*!40000 ALTER TABLE `localidad` DISABLE KEYS */;
INSERT INTO `localidad` (`localidadID`, `provinciaID`, `nombre`) 
VALUES (1,1,'Córdoba');
/*!40000 ALTER TABLE `localidad` ENABLE KEYS */;
UNLOCK TABLES;


--
-- Dumping data for table `barrio`
--

LOCK TABLES `barrio` WRITE;
/*!40000 ALTER TABLE `barrio` DISABLE KEYS */;
INSERT INTO `barrio` (`barrioID`, `localidadID`, `nombre`) 
VALUES (1,1,'Centro'),(2,1,'Nueva Córdoba'),(3,1,'Jardín'),(4,1,'Alta Córdoba'),(5,1,'Alberdi');
/*!40000 ALTER TABLE `barrio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `playa`
--

LOCK TABLES `playa` WRITE;
/*!40000 ALTER TABLE `playa` DISABLE KEYS */;
INSERT INTO `playa` (`barrioID`, `cuit`, `disponibilidad`, `domicilio`, `estadoPlayaID`, `nombreComercial`, `latitud`, `longitud`, `razonSocial`, `playaID`) 
VALUES (2,'30-11111111-1',55,'Buenos Aires 548',2,'Estacionamiento Europa',-31.422895,-64.185659,'Estacionamiento Europa',3),
	(2,'30-11111111-1',100,'Independencia 770',2,'Estacionamiento Independencia',-31.425024,-64.188105,'Estacionamiento Independencia',4),
	(2,'30-11111111-1',100,'Obispo Trejo 758',2,'Parkingcity',-31.424615,-64.189357,'Parkingcity',5),
	(1,'30-11111111-1',100,'Bv. Ilia 156',2,'Estacionamiento Mercado Sur',-31.421251,-64.184234,'Estacionamiento Mercado Sur',6),
	(1,'30-11111111-1',100,'Santa Rosa 631',1,'Parking One',-31.409921,-64.191527,'Parking One',7),
	(1,'30-11111111-1',100,'Bv. Chacabuco 728',2,'Playa Sur',-31.425904,-64.183791,'Playa Sur',8),
	(1,'30-11111111-1',100,'Bv. Illia 70',2,'Parquin Verde',-31.420956,-64.18535,'Parquin Verde',9),
	(1,'30-11111111-1',100,'Colon 756',2,'Estacionamiento Colón',-31.4105156,-64.1936078,'Estacionamiento Colón',10);
/*!40000 ALTER TABLE `playa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `perfil_playa`
--
-- ORDER BY:  `perfilPlayaID`

LOCK TABLES `perfil_playa` WRITE;
/*!40000 ALTER TABLE `perfil_playa` DISABLE KEYS */;
INSERT INTO `tesis_playon`.`perfil_playa` (`descripcion`, `perfilPlayaID`, `playaID`, `fotoPerfil`)
VALUES ('¡Los esperamos en Estacionamiento Europa, les ofrecemos una excelente atención con muy buenos precios!', 3, 3, NULL),
('Playa "Estacionamiento Independencia"', 4, 4, NULL),
('Playa "Parkingcity', 5, 5, NULL),
('Playa "Estacionamiento Mercado Sur"', 6, 6, NULL),
('Playa "Parking One"', 7, 7, NULL),
('Playa "Playa Sur"', 8, 8, NULL),
('Playa "Parquin Verde"', 9, 9, NULL),
('Playa "Estacionamiento Colón"', 10, 10, NULL);
/*!40000 ALTER TABLE `perfil_playa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `tesis_playon`.`usuario` (`apellido`,`email`,`nombre`,`password`,`sesion`,`usuario`,`usuarioID`,`tipoDocID`,`nroDoc`,`enable`,`playaID`) 
VALUES ('Moreno','pablo_la31@hotmail.com','Pablo','123456',NULL,'pablo',1,1,'32987654',1,3),
 ('Bostico','alebostico@hotmail.com','Alejandro','123456',NULL,'alejandro',2,1,'11111111',1,NULL),
 ('Arribere','gonzaloarribere@gmail.com','Gonzalo','123456',NULL,'gonzalo',3,1,'22222222',1,NULL),
 ('Morales Batovski','morales.batovski@gmail.com','Raúl Gustavo','123456',NULL,'gmorales',4,1,'29966905',1,NULL),
 ('Perez Villar','ericperezvillar@gmail.com','Eric','123456',NULL,'eric',5,1,'44444444',1,3),
 ('Admin','admin@playon.com.ar','Super','123456',NULL,'admin',6,1,'66666666',1,NULL),
 ('Gomez','sgomez@playon.com.ar','Silvina','123456',NULL,'sgomez',7,1,'10456654',1,NULL),
 ('Lopez','jjlopez@playon.com.ar','Juana Josefa','123456',NULL,'jjlopez',8,1,'27600710',1,NULL),
 ('Pérez','jperez@playon.com.ar','Juan','123456',NULL,'jperez',9,1,'31234234',1,NULL),
 ('Santoni','rsantoni@playon.com.ar','Rafael','123456',NULL,'rsantoni',10,1,'15454597',1,NULL);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `roles_por_usuario`
--

LOCK TABLES `roles_por_usuario` WRITE;
/*!40000 ALTER TABLE `roles_por_usuario` DISABLE KEYS */;
INSERT INTO `tesis_playon`.`roles_por_usuario` (`rolesPorUsuarioID`,`usuario`,`rolUsuario`) 
VALUES (1,'pablo','ROLE_PLAYA_GERENTE'),
 (2,'alejandro','ROLE_AUDITOR'),
 (3,'gonzalo','ROLE_CLIENT'),
 (4,'gmorales','ROLE_ADMIN'),
 (5,'eric','ROLE_PLAYA_EMPLEADO'),
 (6,'admin','ROLE_ADMIN'),
 (7,'sgomez','ROLE_CLIENT'),
 (8,'jjlopez','ROLE_CLIENT'),
 (9,'jperez','ROLE_CLIENT'),
 (10,'rsantoni','ROLE_PLAYA_GERENTE');  
/*!40000 ALTER TABLE `roles_por_usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `empleado`
--

LOCK TABLES `empleado` WRITE;
/*!40000 ALTER TABLE `empleado` DISABLE KEYS */;
INSERT INTO `empleado` (`cargoEmpleadoID`, `legajo`, `empleadoID`, `usuarioID`) 
VALUES (1,1001,1001,1),(2,1002,1002,5),(1,1003,1003,10);
/*!40000 ALTER TABLE `empleado` ENABLE KEYS */;
UNLOCK TABLES;




--
-- Dumping data for table `tesis_playon`.`cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `tesis_playon`.`cliente` (`barrioID`,`cuentaClienteID`,`domicilio`,`nroCliente`,`telefono`,`clienteID`,`usuarioID`) VALUES 
 (1,4,'Chile 160',1,'156123456',1,3),
 (2,1,'Colon 566',2,'152123456',2,7),
 (1,2,'Buenos Aires 75',3,'153123456',3,8),
 (3,3,'Av. Valparaiso 3125',4,NULL,4,9);
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `tesis_playon`.`cuenta_cliente`
--

LOCK TABLES `cuenta_cliente` WRITE;
/*!40000 ALTER TABLE `cuenta_cliente` DISABLE KEYS */;
INSERT INTO `tesis_playon`.`cuenta_cliente` (`fechaCreacion`,`nroCuenta`,`saldo`,`cuentaClienteID`,`clienteID`) VALUES 
 ('2012-08-21 20:29:37',1001,150,1,2),
 ('2012-08-21 20:29:37',1002,50,2,3),
 ('2012-08-21 20:29:37',1003,500,3,4),
 ('2012-08-21 20:29:37',1004,100,4,1);
/*!40000 ALTER TABLE `cuenta_cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `tesis_playon`.`cuenta_playa`
--

LOCK TABLES `cuenta_playa` WRITE;
/*!40000 ALTER TABLE `cuenta_playa` DISABLE KEYS */;
INSERT INTO `tesis_playon`.`cuenta_playa` (`fechaCreacion`,`nroCuenta`,
`saldo`,`cuentaPlayaID`,`playaID`) VALUES 
 ('2012-08-22 00:00:00',1,40,1,3), ('2012-08-22 00:00:00',2,0,2,4),
 ('2012-08-22 00:00:00',3,0,3,5), ('2012-08-22 00:00:00',4,0,4,6),
 ('2012-08-22 00:00:00',5,0,5,7), ('2012-08-22 00:00:00',6,0,6,8),
 ('2012-08-22 00:00:00',7,0,7,9), ('2012-08-22 00:00:00',8,0,8,10);
/*!40000 ALTER TABLE `cuenta_playa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `tesis_playon`.`detalle_estadia`
--

LOCK TABLES `detalle_estadia` WRITE;
/*!40000 ALTER TABLE `detalle_estadia` DISABLE KEYS */;
INSERT INTO `tesis_playon`.`detalle_estadia` (`transaccionClienteID`,`empleadoID`,
`fechaHoraEgreso`,`fechaHoraIngreso`,`importeTotal`,`cobrado`,`promocionID`,
`tarifaID`,`vehiculoID`,`detalleEstadiaID`,`estadiaID`) VALUES 
 (2,1002,'2012-08-04 17:48:45','2012-08-04 19:25:23',20,1,NULL,1,1,1,3),
 (2,1002,'2012-08-11 17:50:35','2012-08-11 18:45:21',10,1,NULL,1,1,2,3),
 (2,1002,'2012-08-18 17:51:18','2012-08-18 18:32:49',10,1,NULL,1,1,3,3);
/*!40000 ALTER TABLE `detalle_estadia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `tesis_playon`.`estadia`
--

LOCK TABLES `estadia` WRITE;
/*!40000 ALTER TABLE `estadia` DISABLE KEYS */;
INSERT INTO `tesis_playon`.`estadia` (`estadiaID`,`playaID`) VALUES 
 (3,3), (4,4), (5,5), (6,6), (7,7), (8,8), (9,9), (10,10);
/*!40000 ALTER TABLE `estadia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `tesis_playon`.`tarifa`
--

LOCK TABLES `tarifa` WRITE;
/*!40000 ALTER TABLE `tarifa` DISABLE KEYS */;
INSERT INTO `tesis_playon`.`tarifa` (`fechaAlta`,`fechaBaja`,`importe`,`vigente`,`tarifaID`,
    `playaID`,`tipoEstadiaID`,`categoriaVehiculoID`) VALUES 
 ('2012-07-04 19:25:23',NULL,10 ,1 ,1,3 ,1,1),
 ('2012-07-04 19:25:23',NULL,12 ,1 ,2,3 ,1,2),
 ('2012-07-04 19:25:23',NULL,12 ,1 ,3,3 ,1,3),
 ('2012-07-04 19:25:23',NULL,500,1 ,4,3 ,2,1),
 ('2012-07-04 19:25:23',NULL,550,1 ,5,3 ,2,2),
 ('2012-07-04 19:25:23',NULL,550,1 ,6,3 ,2,3),
 ('2012-07-04 19:25:23',NULL,11 ,1 ,7,10,1,1),
 ('2012-07-04 19:25:23',NULL,13 ,1 ,8,10,1,2),
 ('2012-07-04 19:25:23',NULL,14 ,1 ,9,10,1,3),
 ('2012-07-04 19:25:23',NULL,650,1,10,10,2,1),
 ('2012-07-04 19:25:23',NULL,690,1,11,10,2,2),
 ('2012-07-04 19:25:23',NULL,700,1,12,10,2,3);
/*!40000 ALTER TABLE `tarifa` ENABLE KEYS */;
UNLOCK TABLES;


--
-- Dumping data for table `tesis_playon`.`transaccion_cliente`
--

LOCK TABLES `transaccion_cliente` WRITE;
/*!40000 ALTER TABLE `transaccion_cliente` DISABLE KEYS */;
INSERT INTO `tesis_playon`.`transaccion_cliente` (`fecha`,`importe`,`tipoPagoID`,
	`transaccionClienteID`,`cuentaClienteID`) VALUES 
 ('2012-08-03 13:23:45',100,4,1,1),
 ('2012-08-04 19:25:23',20,6,2,1),
 ('2012-08-11 18:45:21',10,6,3,1),
 ('2012-08-18 18:32:49',10,6,4,1);
/*!40000 ALTER TABLE `transaccion_cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `tesis_playon`.`transaccion_playa`
--

/*!40000 ALTER TABLE `transaccion_playa` DISABLE KEYS */;
LOCK TABLES `transaccion_playa` WRITE;
INSERT INTO `tesis_playon`.`transaccion_playa` (`fecha`,`importe`,`tipoPagoID`,`transaccionPlayaID`,
	`cuentaPlayaID`,`liquidacionID`,`detalleEstadiaID`) VALUES 
 ('2012-08-04 19:25:23',20,4,1,1,NULL,1),
 ('2012-08-11 18:45:21',10,4,2,1,NULL,2),
 ('2012-08-18 18:32:49',10,4,3,1,NULL,3);
/*!40000 ALTER TABLE `transaccion_playa` ENABLE KEYS */;
UNLOCK TABLES;

/*!40000 ALTER TABLE `comentario` DISABLE KEYS */;
LOCK TABLES `comentario` WRITE;
INSERT INTO `tesis_playon`.`comentario` (`comentario`, `fecha`, `habilitado`, `playaID`,
`comentarioID`, `usuarioID`) 
VALUES ('No me gusto, hay que dejar la llave del auto', '2012-09-18 09:55:22', 1, 10, 1000, 3);
/*!40000 ALTER TABLE `comentario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `tesis_playon`.`vehiculo`
--

/*!40000 ALTER TABLE `vehiculo` DISABLE KEYS */;
LOCK TABLES `vehiculo` WRITE;
INSERT INTO `tesis_playon`.`vehiculo` (`anio`,`codigoBarra`,`colorVehiculoID`,`habilitado`,`modeloVehiculoID`,`patente`,`vehiculoID`,`clienteID`) 
VALUES (2010,NULL,2,1,1,'ABC123',1,1), 
 (2011,NULL,9,1,6,'DEF456',2,2),
 (2009,NULL,12,1,15,'GHI789',3,3),
 (2009,NULL,3,1,10,'JKL159',4,4);
/*!40000 ALTER TABLE `vehiculo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `denuncia_vehiculo`
--
-- ORDER BY:  `denunciaVehiculoID`

LOCK TABLES `denuncia_vehiculo` WRITE;
/*!40000 ALTER TABLE `denuncia_vehiculo` DISABLE KEYS */;
/*!40000 ALTER TABLE `denuncia_vehiculo` ENABLE KEYS */;
UNLOCK TABLES;



--
-- Dumping data for table `promocion`
--
-- ORDER BY:  `promocionID`

LOCK TABLES `promocion` WRITE;
/*!40000 ALTER TABLE `promocion` DISABLE KEYS */;
/*!40000 ALTER TABLE `promocion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `denuncia_playa`
--
-- ORDER BY:  `denunciaPlayaID`

LOCK TABLES `denuncia_playa` WRITE;
/*!40000 ALTER TABLE `denuncia_playa` DISABLE KEYS */;
/*!40000 ALTER TABLE `denuncia_playa` ENABLE KEYS */;
UNLOCK TABLES;


--
-- Dumping data for table `liquidacion`
--
-- ORDER BY:  `liquidacionID`

LOCK TABLES `liquidacion` WRITE;
/*!40000 ALTER TABLE `liquidacion` DISABLE KEYS */;
/*!40000 ALTER TABLE `liquidacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `historial_de_cambio`
--
-- ORDER BY:  `historialDeCambioID`

LOCK TABLES `historial_de_cambio` WRITE;
/*!40000 ALTER TABLE `historial_de_cambio` DISABLE KEYS */;
/*!40000 ALTER TABLE `historial_de_cambio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `foto`
--
-- ORDER BY:  `fotoID`

LOCK TABLES `foto` WRITE;
/*!40000 ALTER TABLE `foto` DISABLE KEYS */;
/*!40000 ALTER TABLE `foto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `favorito`
--
-- ORDER BY:  `clienteID`,`playaID`

LOCK TABLES `favorito` WRITE;
/*!40000 ALTER TABLE `favorito` DISABLE KEYS */;
/*!40000 ALTER TABLE `favorito` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `publicidad`
--
-- ORDER BY:  `publicidadID`

LOCK TABLES `publicidad` WRITE;
/*!40000 ALTER TABLE `publicidad` DISABLE KEYS */;
/*!40000 ALTER TABLE `publicidad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `sesion`
--
-- ORDER BY:  `sesionID`

LOCK TABLES `sesion` WRITE;
/*!40000 ALTER TABLE `sesion` DISABLE KEYS */;
/*!40000 ALTER TABLE `sesion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `posicion`
--
-- ORDER BY:  `posicionID`

LOCK TABLES `posicion` WRITE;
/*!40000 ALTER TABLE `posicion` DISABLE KEYS */;
/*!40000 ALTER TABLE `posicion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `comentario`
--
-- ORDER BY:  `comentarioID`

LOCK TABLES `comentario` WRITE;
/*!40000 ALTER TABLE `comentario` DISABLE KEYS */;
/*!40000 ALTER TABLE `comentario` ENABLE KEYS */;
UNLOCK TABLES;



-- -- =================================================================== --


/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2012-06-20  1:16:33