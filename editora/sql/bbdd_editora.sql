CREATE DATABASE  IF NOT EXISTS `editora` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `editora`;

DROP TABLE IF EXISTS `tb_perfil`;

CREATE TABLE `tb_perfil` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `perfil` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `tb_categoria`;

CREATE TABLE `tb_categoria` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `perfil` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;    

DROP TABLE IF EXISTS `tb_produto`;

CREATE TABLE `tb_produto` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `logradouro` varchar(45) DEFAULT NULL,
  `numero` int(11) NOT NULL,
  `cep` varchar(45) DEFAULT NULL,
  `bairro` varchar(45) DEFAULT NULL,
  `tipoLogradouro` varchar(45) DEFAULT NULL,
  `municipio` varchar(45) DEFAULT NULL,
  `unidade` varchar(45) DEFAULT NULL,
  `complemento` varchar(45) DEFAULT NULL,  
  PRIMARY KEY (`id`)  
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `tb_venda`;

CREATE TABLE `tb_venda` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dataVenda` date DEFAULT NULL,
  `formaPagamento` int(11) DEFAULT NULL,
  `quantidadeParcela` int(11) DEFAULT NULL,
  `valorTotal` double(7,2) DEFAULT NULL,
  `valorTotalDesconto` double(7,2) DEFAULT NULL,
  `obs` varchar(45) DEFAULT NULL,
  `tituloObs` int(11) DEFAULT NULL,
  `operacao` int(11) DEFAULT NULL, 
  `ativado` boolean DEFAULT NULL,
  PRIMARY KEY (`id`)  
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `tb_usuario`;

CREATE TABLE `tb_usuario` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) DEFAULT NULL,
  `cpf` varchar(45) DEFAULT NULL,
  `nascimento` date DEFAULT NULL,
  `universitario` varchar(45) DEFAULT NULL,
  `local_trabalho` varchar(45) DEFAULT NULL,
  `telefone1` varchar(45) DEFAULT NULL,
  `telefone2` varchar(45) DEFAULT NULL,
  `login` varchar(45)  DEFAULT NULL, 
  `senha` varchar(45)  DEFAULT NULL,  
  `repetirSenha` varchar(45)  DEFAULT NULL,  
  `status` boolean DEFAULT NULL,
  `aceitaSolicitacao` boolean DEFAULT NULL,
  `esqueciSenha` boolean DEFAULT NULL,
  `perfil_id` int(11) DEFAULT NULL,
  `data` date DEFAULT NULL,
  PRIMARY KEY (`id`) 
  FOREIGN KEY (`perfil_id`)  REFERENCES `editora`.`tb_perfil` (`id`) ON DELETE NO ACTION  ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `tb_tipo_produto`;

CREATE TABLE `tb_tipo_produto` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`) 
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS tb_nota_fiscal;

CREATE TABLE `tb_nota_fiscal` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `lote` int(11) DEFAULT NULL,
  `numero` int(11) DEFAULT NULL,
  `valor` double(7,2) DEFAULT NULL,
  `dataEmissao` date DEFAULT NULL,
  `dataEntrada` date DEFAULT NULL,
  `status` boolean DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS tb_mensagem;

CREATE TABLE `tb_mensagem` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(45) DEFAULT NULL,
  `dataEnvio` date DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS tb_item_venda;

CREATE TABLE `tb_item_venda` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `quantidade` int(11) DEFAULT NULL,
  `quantidadeN` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS tb_item_devolvido;

CREATE TABLE `tb_item_devolvido` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `retorno` int(11) DEFAULT NULL,
  `dataVenda` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS tb_item;

CREATE TABLE `tb_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `quantidadeEntrada` int(11) DEFAULT NULL,
  `quantidadeSaida` int(11) DEFAULT NULL,
  `valorCusto` double(7,2) DEFAULT NULL,
  `totalValor` double(7,2) DEFAULT NULL,
  `valorVenda` double(7,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS tb_fornecedor;

CREATE TABLE `tb_fornecedor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cnpj_cpf` varchar(45) DEFAULT NULL,
  `telefone1` varchar(45) DEFAULT NULL,
  `telefone2` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `email2` varchar(45) DEFAULT NULL,
  `email3` varchar(45) DEFAULT NULL,
  `pis` int(11) DEFAULT NULL,
  `banco` int(11) DEFAULT NULL,
  `agencia` varchar(45) DEFAULT NULL,
  `conta` varchar(45) DEFAULT NULL,
  `titularConta` varchar(45) DEFAULT NULL,
  `tipoConta` varchar(45) DEFAULT NULL,
  `operacao` varchar(45) DEFAULT NULL,
  `endereco_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`endereco_id`)  REFERENCES `editora`.`tb_endereco` (`id`) ON DELETE NO ACTION  ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS tb_email;

CREATE TABLE `tb_email` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cliente_destino_id` int(11) DEFAULT NULL,
  `mensagem` varchar(45) DEFAULT NULL,
  `telefone2` varchar(45) DEFAULT NULL,
  `usuario_autor_id` int(11) DEFAULT NULL,
  `dataEnvio` date DEFAULT NULL,
  PRIMARY KEY (`id`)
  FOREIGN KEY (`usuario_autor_id`)  REFERENCES `editora`.`tb_usuario` (`id`) ON DELETE NO ACTION  ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS tb_endereco;

CREATE TABLE `tb_endereco` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `logradouro` varchar(45) DEFAULT NULL,
  `numero` int(11) DEFAULT NULL,
  `cep` varchar(45) DEFAULT NULL,
  `bairro` varchar(45) DEFAULT NULL,
  `tipoLogradouro` varchar(45) DEFAULT NULL,
  `municipio` varchar(45) DEFAULT NULL,
  `unidade` varchar(45) DEFAULT NULL,
  `complemento` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;


INSERT INTO `tb_endereco` VALUES (10,'BARCELONA',13,'12.132-132','INDIANS','Br','BARCELONA','BA','');
INSERT INTO `tb_perfil` VALUES (1,'USUARIO');
INSERT INTO `tb_perfil` VALUES (2,'MAESTRO');
INSERT INTO `tb_perfil` VALUES (3,'MAESTRO');
INSERT INTO `tb_perfil` VALUES (4,'SIN ACCESO');
INSERT INTO `tb_usuario` VALUES (10,'JOSE','123.456.478-97',NULL,'SERVIDOR UFRR','BARCELONA','(95)7789-7897',NULL,'prueba@prueba.es','c893bad68927b457dbed39460e6afd62',NULL,1,1,NULL,1,'2014-06-06','\0','1983-01-01 00:00:00',10,NULL);
