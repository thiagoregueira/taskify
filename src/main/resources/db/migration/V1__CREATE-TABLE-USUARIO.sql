CREATE TABLE IF NOT EXISTS tb_usuarios (

    idUsuario BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(50) NOT NULL,
    sobrenome VARCHAR(150) NOT NULL,
    email VARCHAR(255) NOT NULL,
    senha VARCHAR(10) NOT NULL,
    PRIMARY KEY (idUsuario)

);