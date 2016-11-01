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

  `confirmaEmail` VARCHAR(70) NOT NULL ,

  `senha` VARCHAR(15) NOT NULL ,

  `confirmaSenha` VARCHAR(15) NOT NULL ,

  PRIMARY KEY (`idPessoa`) );


CREATE  TABLE `anymais`.`animal` (

  `idAnimal` INT NOT NULL ,

  `tipoAnimal` INT NOT NULL ,

  `nome` VARCHAR(45) NOT NULL ,

  `raca` VARCHAR(50) NOT NULL ,

  `dataNascimento` DATE NOT NULL ,

  `peso` INT NOT NULL ,

  `tamanho` INT NOT NULL ,

  `cor` VARCHAR(45) NOT NULL ,

  `sexo` VARCHAR(1) NOT NULL ,

  `descricao` VARCHAR(256) NULL ,

  `imagem` VARCHAR(256) NULL ,

  PRIMARY KEY (`idAnimal`) );


CREATE  TABLE `anymais`.`raca` (

  `idRaca` INT NOT NULL ,

  `tipoAnimal` INT NOT NULL ,

  `nomeRaca` VARCHAR(60) NOT NULL ,

  `porte` VARCHAR(45) NOT NULL ,

  `observacao` VARCHAR(256) NULL ,

  PRIMARY KEY (`idRaca`) );


CREATE  TABLE `anymais`.`vacinasMedicamentos` (

  `idVacinasMedicamentos` INT NOT NULL ,

  `tipo` INT NOT NULL ,

  `tipoAnimal` INT NOT NULL ,

  `nome` VARCHAR(60) NOT NULL ,

  `periodicidade` INT NOT NULL ,

  `tempo` VARCHAR(45) NOT NULL ,

  `observacao` VARCHAR(256) NULL ,

  PRIMARY KEY (`idVacinasMedicamentos`) );


