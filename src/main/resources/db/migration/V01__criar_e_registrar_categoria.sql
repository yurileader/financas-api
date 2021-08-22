CREATE TABLE categorias
(
    id   BIGINT(20) AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(50) NOT NULL
);


CREATE TABLE pessoas
(
    id          BIGINT(20) AUTO_INCREMENT PRIMARY KEY,
    nome        VARCHAR(100) NOT NULL,
    logradouro  VARCHAR(50),
    numero      VARCHAR(10),
    complemento VARCHAR(30),
    bairro      VARCHAR(20),
    cep         VARCHAR(13),
    cidade      VARCHAR(20),
    estado      VARCHAR(20),
    ativo       BOOLEAN
);

