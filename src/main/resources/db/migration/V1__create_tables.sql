-- -----------------------------------------------------
-- Table Person
-- -----------------------------------------------------
CREATE TABLE pessoa
(
    id             bigserial              NOT NULL,
    nome           character varying(100) NOT NULL,
    datanascimento date,
    cpf            character varying(14),
    funcionario    boolean,
    gerente        boolean,
    CONSTRAINT pk_pessoa PRIMARY KEY (id)
);

-- -----------------------------------------------------
-- Table Project
-- -----------------------------------------------------
CREATE TABLE projeto
(
    id                bigserial    NOT NULL,
    nome              VARCHAR(200) NOT NULL,
    data_inicio       DATE,
    data_previsao_fim DATE,
    data_fim          DATE,
    descricao         VARCHAR(5000),
    status            VARCHAR(45),
    orcamento         FLOAT,
    risco             VARCHAR(45),
    idgerente         bigint       NOT NULL,
    CONSTRAINT pk_projeto PRIMARY KEY (id),
    CONSTRAINT fk_gerente FOREIGN KEY (idgerente)
        REFERENCES pessoa (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION
);

-- -----------------------------------------------------
-- Table Membros
-- -----------------------------------------------------
CREATE TABLE membros
(
    idprojeto bigint NOT NULL,
    idpessoa  bigint NOT NULL,
    CONSTRAINT pk_membros_projeto PRIMARY KEY (idprojeto, idpessoa),
    CONSTRAINT fk_projeto FOREIGN KEY (idprojeto)
        REFERENCES projeto (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT fk_pessoa FOREIGN KEY (idpessoa)
        REFERENCES pessoa (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION
);