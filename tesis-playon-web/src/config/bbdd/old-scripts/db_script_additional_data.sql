USE `tesis_playon`;

-- Datos Generales

--
-- Dumping data for table `usuario`
--

INSERT INTO `usuario` (`apellido`,`email`,`nombre`,
  `password`,`sesion`,`usuario`,`usuarioID`,
  `tipoDocID`,`nroDoc`,`enable`,`playaID`)
VALUES ('Rube','richardparking@hotmail.com','Ricardo','123456',NULL,'richardparking',11,1,'123456777',1,4),
('Ricardo','ricardoempleado@hotmail.com','Ramirez','123456',NULL,'ricardoempleado',12,1,'34045824',1,10);

INSERT INTO `roles_por_usuario` (`rolesPorUsuarioID`,`usuario`,`rolUsuario`)
VALUES (11,'richardparking','ROLE_PLAYA_GERENTE'),
(12,'ricardoempleado','ROLE_PLAYA_EMPLEADO');

INSERT INTO `empleado` (`cargoEmpleadoID`,`legajo`,`empleadoID`,`usuarioID`)
VALUES (1,1001,1007,11),(1,1005,1008,12);

INSERT INTO `tarifa` (`fechaAlta`,`fechaBaja`,`importe`,
  `vigente`,`tarifaID`,`playaID`,`tipoEstadiaID`,`categoriaVehiculoID`) 
VALUES 
('2012-10-20 19:38:19',NULL,10,1,13,4,1,1),
('2012-10-20 19:38:19',NULL,12,1,14,4,1,3),
('2012-10-20 19:38:19',NULL,12,1,15,4,1,4),
('2012-10-20 19:38:19',NULL,50,1,16,4,3,1),
('2012-10-20 19:38:19',NULL,70,1,17,4,3,3),
('2012-10-20 19:38:19',NULL,70,1,18,4,3,4),
('2012-10-20 19:38:19',NULL,500,1,19,4,2,1),
('2012-10-20 19:38:19',NULL,700,1,20,4,2,3),
('2012-10-20 19:38:19',NULL,700,1,21,4,2,4),

('2012-10-20 19:45:07',NULL,12,1,22,10,1,1),
('2012-10-20 19:45:07',NULL,14,1,23,10,1,3),
('2012-10-20 19:45:07',NULL,14,1,24,10,1,4),
('2012-10-20 19:45:07',NULL,500,1,25,10,2,1),
('2012-10-20 19:45:07',NULL,800,1,26,10,2,3),
('2012-10-20 19:45:07',NULL,800,1,27,10,2,4);



-- !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
-- Carga de crédito de los clientes al final !!!!!!!!!!!!!!!!!!
-- !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!


-- ------------------------------------------------------------------
-- Carga de datos de prueba para la Playa "Estacionamiento Europa"

-- Dumping data for table `transaccion_cliente`

INSERT INTO `transaccion_cliente` (`fecha`,`importe`,`tipoPagoID`,
  `cuentaClienteID`,`transaccionClienteID`) VALUES 
('2012-08-15 18:39:42',10,6,4,5),
('2012-08-16 18:40:01',10,6,4,6),
('2012-08-20 19:45:52',12,6,3,7),
('2012-09-05 20:08:58',12,6,3,8),
('2012-09-10 20:09:08',12,6,3,9),
('2012-09-12 20:09:16',12,6,3,10),
('2012-09-15 20:09:23',12,6,3,11),
('2012-09-22 20:09:30',10,6,4,12),
('2012-10-02 20:09:46',10,6,3,13),
('2012-10-06 20:10:02',12,6,3,14),
('2012-10-10 20:10:10',12,6,4,15),
('2012-10-11 20:10:17',12,6,3,16),
('2012-10-17 20:10:24',12,6,4,17),
('2012-10-26 20:10:31',12,6,3,18);

-- Dumping data for table `detalle_estadia`

INSERT INTO `detalle_estadia` (`detalleEstadiaID`,`transaccionClienteID`,`empleadoID`,
  `fechaHoraEgreso`,`fechaHoraIngreso`,`importeTotal`,`cobrado`,
  `promocionID`,`tarifaID`,`vehiculoID`,`estadiaID`) VALUES 
(5,5,1001,'2012-08-15 18:00:00','2012-08-15 18:39:42',10,1,NULL,1,1,3),
(6,6,1001,'2012-08-16 18:00:00','2012-08-16 18:40:01',10,1,NULL,1,1,3),
(7,7,1001,'2012-08-20 19:00:00','2012-08-20 19:45:52',12,1,NULL,3,4,3),
(8,8,1001,'2012-09-05 20:00:00','2012-09-05 20:08:58',12,1,NULL,3,4,3),
(9,9,1001,'2012-09-10 20:00:00','2012-09-10 20:09:08',12,1,NULL,3,4,3),
(10,10,1001,'2012-09-12 20:00:00','2012-09-12 20:09:16',12,1,NULL,3,4,3),
(11,11,1001,'2012-09-15 20:00:00','2012-09-15 20:09:23',12,1,NULL,3,4,3),
(12,12,1001,'2012-09-22 20:00:00','2012-09-22 20:09:30',10,1,NULL,1,1,3),
(13,13,1001,'2012-10-02 20:00:00','2012-10-02 20:09:46',10,1,NULL,1,1,3),
(14,14,1001,'2012-10-06 20:00:00','2012-10-06 20:10:02',12,1,NULL,3,4,3),
(15,15,1001,'2012-10-10 20:00:00','2012-10-10 20:10:10',12,1,NULL,3,4,3),
(16,16,1001,'2012-10-11 20:00:00','2012-10-11 20:10:17',12,1,NULL,3,4,3),
(17,17,1001,'2012-10-17 20:00:00','2012-10-17 20:10:24',12,1,NULL,3,4,3),
(18,18,1001,'2012-10-26 20:00:00','2012-10-26 20:10:31',12,1,NULL,3,4,3);

-- Dumping data for table `transaccion_playa`

INSERT INTO `transaccion_playa` (`fecha`,`importe`,`tipoPagoID`,
  `cuentaPlayaID`,`liquidacionID`,`detalleEstadiaID`,`transaccionPlayaID`)
VALUES 
('2012-08-15 18:39:42',10,6,1,NULL,5,5),
('2012-08-16 18:40:01',10,6,1,NULL,6,6),
('2012-08-20 19:45:52',12,6,1,NULL,7,7),
('2012-09-05 20:08:58',12,6,1,NULL,8,8),
('2012-09-10 20:09:08',12,6,1,NULL,9,9),
('2012-09-12 20:09:16',12,6,1,NULL,10,10),
('2012-09-15 20:09:23',12,6,1,NULL,11,11),
('2012-09-22 20:09:30',10,6,1,NULL,12,12),
('2012-10-02 20:09:46',10,6,1,NULL,13,13),
('2012-10-06 20:10:02',12,6,1,NULL,14,14),
('2012-10-10 20:10:10',12,6,1,NULL,15,15),
('2012-10-11 20:10:17',12,6,1,NULL,16,16),
('2012-10-17 20:10:24',12,6,1,NULL,17,17),
('2012-10-26 20:10:31',12,6,1,NULL,18,18);


-- Actualizar el saldo de la playa

UPDATE `tesis_playon`.`cuenta_playa`
SET `saldo` = (SELECT SUM(tp.`importe`) 
                FROM `transaccion_playa` AS tp 
                WHERE tp.`cuentaPlayaID` = 1)
WHERE `cuentaPlayaID` = 1;

-- ------------------------------------------------------------------
-- Carga de datos de prueba para la Playa "Estacionamiento Independencia"

-- Dumping data for table `transaccion_cliente`

INSERT INTO `transaccion_cliente` (`fecha`,`importe`,`tipoPagoID`,
  `cuentaClienteID`,`transaccionClienteID`) VALUES 
('2012-08-17 18:39:42',10,6,4,19),
('2012-08-18 18:40:01',10,6,4,20),
('2012-08-21 19:45:52',12,6,3,21),
('2012-09-06 20:08:58',12,6,3,22),
('2012-09-11 20:09:08',12,6,3,23),
('2012-09-14 20:09:16',12,6,3,24),
('2012-09-16 20:09:23',12,6,3,25),
('2012-09-23 20:09:30',10,6,4,26),
('2012-10-03 20:09:46',10,6,4,27),
('2012-10-07 20:10:02',12,6,3,28),
('2012-10-12 20:10:10',12,6,3,29),
('2012-10-13 20:10:17',12,6,3,30),
('2012-10-18 20:10:24',12,6,3,31),
('2012-10-27 20:10:31',12,6,3,32);

-- Dumping data for table `detalle_estadia`

INSERT INTO `detalle_estadia` (`detalleEstadiaID`,`transaccionClienteID`,`empleadoID`,
  `fechaHoraEgreso`,`fechaHoraIngreso`,`importeTotal`,`cobrado`,
  `promocionID`,`tarifaID`,`vehiculoID`,`estadiaID`) VALUES 
(19,19,1007,'2012-08-17 18:00:00','2012-08-17 18:39:42',10,1,NULL,13,1,4),
(20,20,1007,'2012-08-18 18:00:00','2012-08-18 18:40:01',10,1,NULL,13,1,4),
(21,21,1007,'2012-08-21 19:00:00','2012-08-21 19:45:52',12,1,NULL,14,4,4),
(22,22,1007,'2012-09-06 20:00:00','2012-09-06 20:08:58',12,1,NULL,14,4,4),
(23,23,1007,'2012-09-11 20:00:00','2012-09-11 20:09:08',12,1,NULL,14,4,4),
(24,24,1007,'2012-09-13 20:00:00','2012-09-13 20:09:16',12,1,NULL,14,4,4),
(25,25,1007,'2012-09-16 20:00:00','2012-09-16 20:09:23',12,1,NULL,14,4,4),
(26,26,1007,'2012-09-23 20:00:00','2012-09-23 20:09:30',10,1,NULL,13,1,4),
(27,27,1007,'2012-10-03 20:00:00','2012-10-03 20:09:46',10,1,NULL,13,1,4),
(28,28,1007,'2012-10-07 20:00:00','2012-10-07 20:10:02',12,1,NULL,14,4,4),
(29,29,1007,'2012-10-12 20:00:00','2012-10-12 20:10:10',12,1,NULL,14,4,4),
(30,30,1007,'2012-10-13 20:00:00','2012-10-13 20:10:17',12,1,NULL,14,4,4),
(31,31,1007,'2012-10-18 20:00:00','2012-10-18 20:10:24',12,1,NULL,14,4,4),
(32,32,1007,'2012-10-27 20:00:00','2012-10-27 20:10:31',12,1,NULL,14,4,4);

-- Dumping data for table `transaccion_playa`

INSERT INTO `transaccion_playa` (`fecha`,`importe`,`tipoPagoID`,
  `cuentaPlayaID`,`liquidacionID`,`detalleEstadiaID`,`transaccionPlayaID`)
VALUES 
('2012-08-17 18:39:42',10,6,2,NULL,19,19),
('2012-08-18 18:40:01',10,6,2,NULL,20,20),
('2012-08-21 19:45:52',12,6,2,NULL,21,21),
('2012-09-06 20:08:58',12,6,2,NULL,22,22),
('2012-09-11 20:09:08',12,6,2,NULL,23,23),
('2012-09-13 20:09:16',12,6,2,NULL,24,24),
('2012-09-16 20:09:23',12,6,2,NULL,25,25),
('2012-09-23 20:09:30',10,6,2,NULL,26,26),
('2012-10-03 20:09:46',10,6,2,NULL,27,27),
('2012-10-07 20:10:02',12,6,2,NULL,28,28),
('2012-10-12 20:10:10',12,6,2,NULL,29,29),
('2012-10-13 20:10:17',12,6,2,NULL,30,30),
('2012-10-18 20:10:24',12,6,2,NULL,31,31),
('2012-10-27 20:10:31',12,6,2,NULL,32,32);


-- Dumping data for Denuncia a vehiculos

INSERT INTO `tesis_playon`.`denuncia_vehiculo` 
(`asunto`, `fechaAlta`, `vehiculoID`, `denunciaVehiculoID`, 
`playaID`, `estadoID`) VALUES 
('No corresponde el auto declarado con el real', '2012-08-01 00:00:00', 1, 1, 3, 1);
INSERT INTO `tesis_playon`.`denuncia_vehiculo` 
(`asunto`, `fechaAlta`, `vehiculoID`, `denunciaVehiculoID`, 
`playaID`, `estadoID`) VALUES 
('No corresponde el auto declarado con el real', '2012-08-01 00:00:00', 2, 2, 3, 1);
INSERT INTO `tesis_playon`.`denuncia_vehiculo` 
(`asunto`, `fechaAlta`, `vehiculoID`, `denunciaVehiculoID`, 
`playaID`, `estadoID`) VALUES 
('No corresponde el auto declarado con el real', '2012-08-01 00:00:00', 1, 3, 3, 1);


-- Actualizar el saldo de la playa

UPDATE `tesis_playon`.`cuenta_playa`
SET `saldo` = (SELECT SUM(tp.`importe`) 
                FROM `transaccion_playa` AS tp 
                WHERE tp.`cuentaPlayaID` = 2)
WHERE `cuentaPlayaID` = 2;

-- ------------------------------------------------------------------
-- Carga de datos de prueba para la Playa "Estacionamiento Colón"

-- Dumping data for table `transaccion_cliente`

INSERT INTO `transaccion_cliente` (`fecha`,`importe`,`tipoPagoID`,
  `cuentaClienteID`,`transaccionClienteID`) VALUES 
('2012-08-19 18:39:42',12,6,4,33),
('2012-08-20 18:40:01',12,6,4,34),
('2012-08-22 19:45:52',14,6,3,35),
('2012-09-07 20:08:58',14,6,3,36),
('2012-09-12 20:09:08',14,6,4,37),
('2012-09-15 20:09:16',14,6,3,38),
('2012-09-17 20:09:23',14,6,3,39),
('2012-09-24 20:09:30',12,6,4,40),
('2012-10-04 20:09:46',12,6,4,41),
('2012-10-08 20:10:02',14,6,3,42),
('2012-10-14 20:10:10',14,6,3,43),
('2012-10-15 20:10:17',14,6,3,44),
('2012-10-19 20:10:24',14,6,3,45),
('2012-10-28 20:10:31',14,6,3,46);

-- Dumping data for table `detalle_estadia`

INSERT INTO `detalle_estadia` (`detalleEstadiaID`,`transaccionClienteID`,`empleadoID`,
  `fechaHoraEgreso`,`fechaHoraIngreso`,`importeTotal`,`cobrado`,
  `promocionID`,`tarifaID`,`vehiculoID`,`estadiaID`) VALUES 
(33,33,1008,'2012-08-19 18:00:00','2012-08-19 18:39:42',12,1,NULL,22,1,10),
(34,34,1008,'2012-08-20 18:00:00','2012-08-20 18:40:01',12,1,NULL,22,1,10),
(35,35,1008,'2012-08-22 19:00:00','2012-08-22 19:45:52',14,1,NULL,23,4,10),
(36,36,1008,'2012-09-07 20:00:00','2012-09-07 20:08:58',14,1,NULL,23,4,10),
(37,37,1008,'2012-09-12 20:00:00','2012-09-12 20:09:08',14,1,NULL,23,4,10),
(38,38,1008,'2012-09-15 20:00:00','2012-09-15 20:09:16',14,1,NULL,23,4,10),
(39,39,1008,'2012-09-17 20:00:00','2012-09-17 20:09:23',14,1,NULL,23,4,10),
(40,40,1008,'2012-09-24 20:00:00','2012-09-24 20:09:30',12,1,NULL,22,1,10),
(41,41,1008,'2012-10-04 20:00:00','2012-10-04 20:09:46',12,1,NULL,22,1,10),
(42,42,1008,'2012-10-08 20:00:00','2012-10-07 20:10:02',14,1,NULL,23,4,10),
(43,43,1008,'2012-10-14 20:00:00','2012-10-14 20:10:10',14,1,NULL,23,4,10),
(44,44,1008,'2012-10-15 20:00:00','2012-10-15 20:10:17',14,1,NULL,23,4,10),
(45,45,1008,'2012-10-19 20:00:00','2012-10-19 20:10:24',14,1,NULL,23,4,10),
(46,46,1008,'2012-10-28 20:00:00','2012-10-28 20:10:31',14,1,NULL,23,4,10);
    

-- Dumping data for table `Abono`

INSERT INTO `tesis_playon`.`abono` 
(`fechaVigenciaDesde`, `fechaVigenciaHasta`, `tarifaID`, 
`abonoID`, `vehiculoID`, `playaID`, `promocionID`) VALUES 
('2012-10-04 00:00:00', '2012-11-04 00:00:00', 3, 2, 2, 3,null);
INSERT INTO `tesis_playon`.`abono` 
(`fechaVigenciaDesde`, `fechaVigenciaHasta`, `tarifaID`, 
`abonoID`, `vehiculoID`, `playaID`, `promocionID`) VALUES 
('2012-10-02 00:00:00', '2012-11-02 00:00:00', 3, 3, 3, 3,null);
INSERT INTO `tesis_playon`.`abono` 
(`fechaVigenciaDesde`, `fechaVigenciaHasta`, `tarifaID`, 
`abonoID`, `vehiculoID`, `playaID`, `promocionID`) VALUES 
('2012-10-03 00:00:00', '2012-11-03 00:00:00', 3, 4, 1, 3,null);
INSERT INTO `tesis_playon`.`abono`
(`fechaVigenciaDesde`, `fechaVigenciaHasta`, `tarifaID`,
`abonoID`, `vehiculoID`, `playaID`, `promocionID`) VALUES 
('2012-10-05 00:00:00', '2012-11-05 00:00:00', 3, 1, 1, 3, null);


-- Dumping data for table `transaccion_playa`

INSERT INTO `transaccion_playa` (`fecha`,`importe`,`tipoPagoID`,
  `cuentaPlayaID`,`liquidacionID`,`detalleEstadiaID`,`transaccionPlayaID`)
VALUES 
('2012-08-19 18:39:42',12,6,8,NULL,33,33),
('2012-08-20 18:40:01',12,6,8,NULL,34,34),
('2012-08-22 19:45:52',14,6,8,NULL,35,35),
('2012-09-07 20:08:58',14,6,8,NULL,36,36),
('2012-09-12 20:09:08',14,6,8,NULL,37,37),
('2012-09-15 20:09:16',14,6,8,NULL,38,38),
('2012-09-17 20:09:23',14,6,8,NULL,39,39),
('2012-09-24 20:09:30',12,6,8,NULL,40,40),
('2012-10-04 20:09:46',12,6,8,NULL,41,41),
('2012-10-08 20:10:02',14,6,8,NULL,42,42),
('2012-10-14 20:10:10',14,6,8,NULL,43,43),
('2012-10-15 20:10:17',14,6,8,NULL,44,44),
('2012-10-19 20:10:24',14,6,8,NULL,45,45),
('2012-10-28 20:10:31',14,6,8,NULL,46,46);


-- Actualizar el saldo de la playa

UPDATE `tesis_playon`.`cuenta_playa`
SET `saldo` = (SELECT SUM(tp.`importe`) 
                FROM `transaccion_playa` AS tp 
                WHERE tp.`cuentaPlayaID` = 8)
WHERE `cuentaPlayaID` = 8;


-- -------------------------------------------------------------------------
-- Carga de crédito en lo clientes

INSERT INTO `tesis_playon`.`transaccion_cliente` (`fecha`,`importe`,`tipoPagoID`,
	`transaccionClienteID`,`cuentaClienteID`) VALUES 
 ('2012-08-18 13:23:45',90,4,47,1),
 ('2012-09-20 19:25:23',50,4,48,2),
 ('2012-08-18 18:45:21',500,4,49,3),
 ('2012-08-13 18:32:49',300,4,50,4);


-- Actualizar el saldo de los clientes

UPDATE `tesis_playon`.`cuenta_cliente`
SET `saldo` = COALESCE((SELECT SUM(tc1.`importe`) 
        FROM `transaccion_cliente` AS tc1 
        WHERE tc1.`cuentaClienteID` = 1 and tipoPagoID = 4),0) -
    COALESCE((SELECT SUM(tc2.`importe`) 
        FROM `transaccion_cliente` AS tc2
        WHERE tc2.`cuentaClienteID` = 1 and tipoPagoID = 6),0)
WHERE `cuentaClienteID` = 1;

UPDATE `tesis_playon`.`cuenta_cliente`
SET `saldo` = COALESCE((SELECT SUM(tc1.`importe`) 
        FROM `transaccion_cliente` AS tc1 
        WHERE tc1.`cuentaClienteID` = 2 and tipoPagoID = 4),0) -
    COALESCE((SELECT SUM(tc2.`importe`) 
        FROM `transaccion_cliente` AS tc2
        WHERE tc2.`cuentaClienteID` = 2 and tipoPagoID = 6),0)
WHERE `cuentaClienteID` = 2;

UPDATE `tesis_playon`.`cuenta_cliente`
SET `saldo` = COALESCE((SELECT SUM(tc1.`importe`) 
        FROM `transaccion_cliente` AS tc1 
        WHERE tc1.`cuentaClienteID` = 3 and tipoPagoID = 4),0) -
    COALESCE((SELECT SUM(tc2.`importe`) 
        FROM `transaccion_cliente` AS tc2
        WHERE tc2.`cuentaClienteID` = 3 and tipoPagoID = 6),0)
WHERE `cuentaClienteID` = 3;

UPDATE `tesis_playon`.`cuenta_cliente`
SET `saldo` = COALESCE((SELECT SUM(tc1.`importe`) 
        FROM `transaccion_cliente` AS tc1 
        WHERE tc1.`cuentaClienteID` = 4 and tipoPagoID = 4),0) -
    COALESCE((SELECT SUM(tc2.`importe`) 
        FROM `transaccion_cliente` AS tc2
        WHERE tc2.`cuentaClienteID` = 4 and tipoPagoID = 6),0)
WHERE `cuentaClienteID` = 4;

/*
UPDATE `tesis_playon`.`cuenta_cliente`
SET `saldo` = (SELECT SUM(tc.`importe`) 
                FROM `transaccion_cliente` AS tc 
                WHERE tc.`cuentaClienteID` = 4)
WHERE `cuentaClienteID` = 4;

UPDATE `tesis_playon`.`cuenta_cliente`
SET `saldo` = (SELECT SUM(tc.`importe`) 
                FROM `transaccion_cliente` AS tc 
                WHERE tc.`cuentaClienteID` = 3)
WHERE `cuentaClienteID` = 3;
*/