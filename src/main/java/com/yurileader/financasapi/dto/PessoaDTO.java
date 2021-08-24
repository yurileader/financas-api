package com.yurileader.financasapi.dto;

import com.yurileader.financasapi.model.Endereco;
import lombok.Data;

import javax.persistence.Embedded;

@Data
public class PessoaDTO {

    private String nome;
    @Embedded
    private Endereco endereco;
    private boolean ativo;
}
