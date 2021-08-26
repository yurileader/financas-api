CREATE TABLE lancamento
(
    id              BIGINT(10) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    descricao       VARCHAR(30)            NOT NULL,
    data_vencimento DATE,
    data_pagamento  DATE,
    valor           DECIMAL(10, 2)         NOT NULL,
    observacao      VARCHAR(50),
    tipo            VARCHAR(30)            NOT NULL,
    fk_id_categoria BIGINT(10)             NOT NULL,
    fk_id_pessoa    BIGINT(10)             NOT NULL,
    FOREIGN KEY (fk_id_categoria) REFERENCES categorias (id),
    FOREIGN KEY (fk_id_pessoa) REFERENCES pessoas (id)
) DEFAULT CHARSET = utf8;