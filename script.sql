CREATE SCHEMA `anymais` ;

CREATE  TABLE `anymais`.`pessoa` (

  `idPessoa` INT NOT NULL ,

  `tipoPessoa` INT NOT NULL ,

  `nome` VARCHAR(50) NOT NULL ,

  `sexo` VARCHAR(1) NULL ,

  `dataNascimento` DATE NULL ,

  `cpfCnpj` VARCHAR(14) NOT NULL ,

  `endereco` VARCHAR(150) NOT NULL ,

  `bairro` VARCHAR(45) NOT NULL ,

  `complemento` VARCHAR(45) NULL ,

  `cep` VARCHAR(8) NOT NULL ,

  `cidade` VARCHAR(45) NOT NULL ,

  `uf` VARCHAR(45) NOT NULL ,

  `telefone` VARCHAR(13) NULL ,

  `telefone2` VARCHAR(13) NULL ,

  `email` VARCHAR(70) NOT NULL ,

  `senha` VARCHAR(15) NOT NULL ,
  
  `imagem` VARCHAR(256) ,
  
  `descricao` VARCHAR(256) ,

  PRIMARY KEY (`idPessoa`) );


CREATE  TABLE `anymais`.`tipoAnimal` (

  `idTipoAnimal` INT NOT NULL ,

  `nomeTipoAnimal` VARCHAR(20) NOT NULL ,

  PRIMARY KEY (`idTipoAnimal`) );

INSERT INTO `anymais`.`tipoanimal` VALUES (1, 'Cachorro');
INSERT INTO `anymais`.`tipoanimal` VALUES (2, 'Gato');
  

CREATE  TABLE `anymais`.`raca` (

  `idRaca` INT NOT NULL ,

  `tipoAnimal` INT NOT NULL ,

  `nomeRaca` VARCHAR(60) NOT NULL ,

  `porte` VARCHAR(45) NOT NULL ,

  `observacao` VARCHAR(256) NULL ,

  PRIMARY KEY (`idRaca`) ,
  
  FOREIGN KEY (`tipoAnimal`) REFERENCES tipoAnimal(`idTipoAnimal`) 
  
  ON DELETE NO ACTION

  ON UPDATE NO ACTION ,
  
  UNIQUE KEY(tipoAnimal, nomeRaca)
  );


CREATE  TABLE `anymais`.`animal` (

  `idAnimal` INT NOT NULL ,

  `tipoAnimal` INT NOT NULL ,

  `nome` VARCHAR(45) NOT NULL ,

  `idRaca` INT(11) NOT NULL ,

  `dataNascimento` DATE NOT NULL ,

  `peso` FLOAT NOT NULL ,

  `tamanho` FLOAT NOT NULL ,

  `cor` VARCHAR(45) NOT NULL ,

  `sexo` VARCHAR(1) NOT NULL ,

  `descricao` VARCHAR(256) NULL ,

  `imagem` VARCHAR(256) NULL ,

  PRIMARY KEY (`idAnimal`) ,
  
  FOREIGN KEY (`idRaca`) REFERENCES `anymais`.`raca` (`idRaca`)

  ON DELETE NO ACTION

  ON UPDATE NO ACTION );
  
CREATE  TABLE `anymais`.`tipoVacinaMedicamento` (

  `idTipoVacinaMedicamento` INT NOT NULL ,

  `nomeTipoVacinaMedicamento` VARCHAR(20) NOT NULL ,

  PRIMARY KEY (`idTipoVacinaMedicamento`) );
  
INSERT INTO `anymais`.`tipoVacinaMedicamento` VALUES (1, 'Vacina');
INSERT INTO `anymais`.`tipoVacinaMedicamento` VALUES (2, 'Medicamento');



CREATE  TABLE `anymais`.`vacinasMedicamentos` (

  `idVacinasMedicamentos` INT NOT NULL ,

  `tipo` INT NOT NULL ,

  `tipoAnimal` INT NOT NULL ,

  `nome` VARCHAR(60) NOT NULL ,

  `periodicidade` INT NOT NULL ,

  `tempo` VARCHAR(45) NOT NULL ,

  `observacao` VARCHAR(256) NULL ,

  PRIMARY KEY (`idVacinasMedicamentos`) ,

  FOREIGN KEY (`tipo`) REFERENCES `anymais`.`tipoVacinaMedicamento` (`idTipoVacinaMedicamento`)

  ON DELETE NO ACTION

  ON UPDATE NO ACTION );

  
CREATE  TABLE `anymais`.`tipoServico` (

  `idtipoServico` INT NOT NULL ,

  `nomeTipoServico` VARCHAR(60) NOT NULL ,

  `duracao` INT NOT NULL ,
  
  `valor` DOUBLE NOT NULL ,

  `observacao` VARCHAR(256) NULL ,

  PRIMARY KEY (`idTipoServico`) ,
  
  UNIQUE KEY (`nomeTipoServico`)
  
  );


