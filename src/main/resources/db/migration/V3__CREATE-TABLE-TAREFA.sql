CREATE TABLE IF NOT EXISTS tb_tarefas (

    idTarefa BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    descricao TEXT NOT NULL,
    dataCriacao DATETIME NOT NULL,
    dataConclusao DATETIME,
    idProjeto BIGINT NOT NULL,
    prioriadade VARCHAR(255) NOT NULL,
    idUsuario BIGINT NOT NULL,
    status VARCHAR(255) NOT NULL,
    PRIMARY KEY (idTarefa),
    FOREIGN KEY (idUsuario) REFERENCES tb_usuarios (idUsuario),
    FOREIGN KEY (idProjeto) REFERENCES tb_projetos (idProjeto)

);