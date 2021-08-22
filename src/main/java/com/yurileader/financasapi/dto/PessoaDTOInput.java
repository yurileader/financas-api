package com.yurileader.financasapi.dto;

import lombok.Data;

@Data
public class PessoaDTOInput {

    private String nome;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cep;
    private String cidade;
    private String estado;
}
