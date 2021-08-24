package com.yurileader.financasapi.dto;

import com.yurileader.financasapi.model.Endereco;
import lombok.Data;

import javax.persistence.Embedded;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
public class PessoaDTOInput {

    @NotEmpty
    @NotBlank
    private String nome;
    @Embedded
    private Endereco endereco;
    private boolean ativo = true;
}
