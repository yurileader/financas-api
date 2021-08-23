package com.yurileader.financasapi.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class CategoriaDTO {

    private Long id;
    @NotEmpty
    @NotBlank
    @Size(min = 1, max = 50)
    private String nome;
}
