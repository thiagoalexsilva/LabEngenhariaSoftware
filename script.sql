-- Centro Federal de Educação Tecnológica de Minas Gerais
-- Departamento de Engenharia da Computação - Lab. Engenharia de Software
-- Any+
-- SQL Database Script

create table Usuario (
  idUSUARIO INT NOT NULL,
  cpfUSUARIO VARCHAR(11),
  generoUSUARIO VARCHAR(1),
  dataNascimentoUSUARIO VARCHAR(10),
  celularUSUARIO VARCHAR(12),
  mensagemUSUARIO VARCHAR(255),

  CONSTRAINT pk_Usuario PRIMARY KEY (idUSUARIO)
);


create table Pessoa (
  idPESSOA INT NOT NULL,
  emailPESSOA VARCHAR(45) NOT NULL,
  senhaPESSOA VARCHAR(45) NOT NULL,
  nomePESSOA VARCHAR (45) NOT NULL,
  enderecoPESSOA VARCHAR(255) NOT NULL,
  bairroPESSOA VARCHAR(45) NOT NULL,
  complementoPESSOA VARCHAR(45),
  cidadePESSOA VARCHAR(45) NOT NULL,
  cepPESSOA VARCHAR(8) NOT NULL,
  ufPESSOA VARCHAR(2) NOT NULL,
  telefonePESSOA VARCHAR(10) NOT NULL,
  tipoPESSOA INT NOT NULL,
  idUSUARIO INT,
  idPET INT,
  
  CONSTRAINT pk_Pessoa PRIMARY KEY (idPessoa),
  CONSTRAINT fk_UsuarioPessoa FOREIGN KEY (idUSUARIO) REFERENCES Usuario(idUSUARIO) ON DELETE CASCADE
);

create table Admin (
  idADMIN INT NOT NULL,
  idPESSOA INT,
  
  CONSTRAINT pk_Admin PRIMARY KEY (idADMIN),
  CONSTRAINT fk_AdminPessoa FOREIGN KEY (idPESSOA) REFERENCES Pessoa(idPESSOA) ON DELETE CASCADE
);

create table Petshop (
  idPET INT NOT NULL,
  telefonePET VARCHAR(12),
  cnpjPET VARCHAR(14) NOT NULL,
  
  CONSTRAINT pk_Petshop PRIMARY KEY (idPET)
);

alter table Pessoa 
ADD CONSTRAINT fk_PetShop FOREIGN KEY (idPET) references Petshop(idPET) ON DELETE CASCADE;

create table Servico (
  idSERVICO INT NOT NULL,
  nomeSERVICO VARCHAR(255),
  descSERVICO VARCHAR(255),
  
  CONSTRAINT pk_Servico PRIMARY KEY (idSERVICO)
);

create table Pet_Servico (
  idPET INT NOT NULL,
  idSERVICO INT NOT NULL,
  
  CONSTRAINT fk_PetServPetshop FOREIGN KEY (idPET) REFERENCES Petshop(idPET) ON DELETE CASCADE,
  CONSTRAINT fk_PetServServico FOREIGN KEY (idSERVICO) REFERENCES Servico(idSERVICO) ON DELETE CASCADE,
  CONSTRAINT pk_Pet_Servico PRIMARY KEY (idPET, idSERVICO)  
);

create table Raca_Animal (
  idRACA INT NOT NULL,
  nomeRACA VARCHAR(255),
  tipoAnimalRACA VARCHAR(45),
  porteRACA VARCHAR(45) NOT NULL,
  observacaoRACA VARCHAR(255),
  CONSTRAINT pk_Raca_Animal PRIMARY KEY (idRACA)  
);

create table Animal (
  idANIMAL INT NOT NULL,
  idDONO INT,
  idRACA INT,
  nomeANIMAL VARCHAR(45),
  generoANIMAL VARCHAR(1) NOT NULL,
  
  CONSTRAINT pk_Animal PRIMARY KEY (idANIMAL),
  CONSTRAINT fk_AnimalPessoa FOREIGN KEY (idDONO) REFERENCES Pessoa(idPESSOA) ON DELETE CASCADE,
  CONSTRAINT fk_RacaAnimal FOREIGN KEY (idRACA) REFERENCES Raca_Animal(idRACA) ON DELETE CASCADE
);

create table Medicamento_Vacina (
  idMED_VAC INT NOT NULL,
  tipoMED_VAC VARCHAR(45),
  tipoAnimalMED_VAC VARCHAR(45),
  nomeMED_VAC VARCHAR(255),
  descMED_VAC VARCHAR(255),
  
  
  CONSTRAINT pk_Medicamento_Vacina PRIMARY KEY (idMED_VAC)  
);

create table Animal_MedVac (
  idANIMAL INT NOT NULL,
  idMED_VAC INT NOT NULL,
  periodicidadeMED_VAC INT,
  
  CONSTRAINT fk_AniMedAnimal FOREIGN KEY (idANIMAL) REFERENCES Animal(idANIMAL) ON DELETE CASCADE,
  CONSTRAINT fk_AniMedMedicamento FOREIGN KEY (idMED_VAC) REFERENCES Medicamento_Vacina(idMED_VAC) ON DELETE CASCADE,
  CONSTRAINT pk_Animal_Medicamento PRIMARY KEY (idANIMAL, idMED_VAC) 
);


